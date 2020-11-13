package com.hzl.itripauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.vo.ReturnResult;
import com.hzl.entiy.User;
import com.hzl.itripauth.condition.UserRegisterCondition;
import com.hzl.itripauth.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-12 09:20
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping(value = "/ckusr")
    public ReturnResult checkUser(String name) {
        if (StringUtils.isEmpty(name)) {
            //参数为空
             return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code", name);
        User user = userService.getOne(queryWrapper);
        if (user != null) {
            //当用户数据不为空的时候，校验不通过
            return ReturnResult.error(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
        }
        return ReturnResult.ok();
    }

    @PostMapping(value = "/doregister", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReturnResult doRegister(@RequestBody UserRegisterCondition condition) {
        //校验邮箱地址是否符合要求
        if (!validEmail(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition, "email");
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }
    /*** 通过正则表达式校验邮箱地址是否符合要求
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     * @param email * @return */
    private boolean validEmail(String email){
        String regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$" ; return Pattern.compile(regex).matcher(email).find();
    }

    @PutMapping(value = "/activate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReturnResult activate(
            @ApiParam(name = "user", value = "注册邮箱地址", defaultValue = "test@bdqn.cn")
            @RequestParam String user,
            @ApiParam(name = "code", value = "激活码", defaultValue = "018f9a8b2381839ee6f40ab2207c0cfe")
            @RequestParam String code)
    {
        //判断参数是否为空
        if (StringUtils.isEmpty(user)||StringUtils.isEmpty(code)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        boolean activeResult = userService.active(user, code);
        if (activeResult) {
            return ReturnResult.ok();
        } else {
            return ReturnResult.error();
        }
    }

    @RequestMapping(value="/doRegisterByPhone",method= RequestMethod.POST)
    @ResponseBody
    public ReturnResult doRegisterByPhone(@RequestBody UserRegisterCondition condition) {
        //1、手机号格式验证
        if (!this.validPhone(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition, "phone");
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    /**
     *  验证手机号码的格式是否正确
     *  @param phone 手机号码
     *  @return 返回true表示手机号码验证通过。否则返回false
     */
    public boolean validPhone(String phone) {
        String regex = "^1[34578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

    @RequestMapping(value="/activateByPhone",method=RequestMethod.PUT)
    @ResponseBody
    public ReturnResult activateByPhone(
            @ApiParam(name="user",value="手机号码")@RequestParam String user,
            @ApiParam(name="code",value="激活码") @RequestParam String code){
        try {
            if(userService.validatePhone(user, code)) {
                return ReturnResult.ok();
            }else{
                return ReturnResult.error();
            }
        } catch (Exception e) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ACTIVATE_FAILED);
        }
    }
}
