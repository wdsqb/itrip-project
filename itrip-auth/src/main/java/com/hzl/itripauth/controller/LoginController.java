package com.hzl.itripauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.ReturnResult;
import com.hzl.common.vo.TokenVo;
import com.hzl.entity.User;
import com.hzl.itripauth.service.UserService;
import com.hzl.itripauth.shiro.JWTUtil;
import com.hzl.util.JsonUtil;
import com.hzl.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :  登录控制器
 * @date : 2020-11-17 09:33
 */
@Api(tags = "登录相关控制器")
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/dologin", produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult login(@ApiParam(name = "name", value = "登录用户名")
                              @RequestParam("name") String userCode,
                              @ApiParam(name = "password", value = "登录密码")
                              @RequestParam("password") String userPassword,
                              HttpServletRequest request) {
        if (StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userPassword)) {
            //当用户编码或用户密码为空的时候
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_ERROR);
        }
        User user = userService.findUserByUserCode(userCode);
        if (user == null) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_UNKNOWN);
        }
        if (!MD5.getMd5(userPassword, 32).equals(user.getUserPassword())) {
            //当密码不匹配时返回错误信息
            return ReturnResult.error(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        String jwtToken = null;
        try {
            jwtToken = JWTUtil.sign(userCode, MD5.getMd5(userPassword, 32));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        TokenVo tokenVo = new TokenVo(jwtToken, System.currentTimeMillis() + 2 *
                3600 * 1000, System.currentTimeMillis());
        //将用户信息存储到redis中，以jwtToken做为key，跟需求有一些出入，不区分移动端和PC端
        try {
            stringRedisTemplate.opsForValue()
                    .set(jwtToken, JsonUtil.objectToJsonString(user), 2, TimeUnit.HOURS);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ReturnResult.ok(tokenVo);
    }
}