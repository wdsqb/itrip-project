package com.hzl.util;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : JwtToken类
 * @date : 2020-11-17 10:51
 */
public class JWTToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
