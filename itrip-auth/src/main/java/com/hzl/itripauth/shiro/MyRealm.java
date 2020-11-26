package com.hzl.itripauth.shiro;

import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.entity.User;
import com.hzl.exception.ServiceException;
import com.hzl.itripauth.service.UserService;
import com.hzl.util.JWTToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义认证及鉴权处理器
 * @date : 2020-11-17 09:18
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 此期没有涉及到用户的角色，所只不需要实现鉴权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 默认使用此方法进行用户正确与否验证，错误抛出异常即可
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            throw new ServiceException(ErrorCodeEnum.AUTH_TOKEN_INVALID);
        }
        // 解密获得username，用于和数据库进行对比
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new ServiceException(ErrorCodeEnum.AUTH_UNKNOWN);
        }
        User user = userService.findUserByUserCode(username);
        if (user == null) {
            throw new ServiceException(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        if (!JWTUtil.verify(token, username, user.getUserPassword())) {
            throw new ServiceException(ErrorCodeEnum.AUTH_AUTHENTICATION_FAILED);
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
