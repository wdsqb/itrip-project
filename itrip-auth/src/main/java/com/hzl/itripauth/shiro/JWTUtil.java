package com.hzl.itripauth.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : Jwt工具类，用于生成和校验jwt
 * @date : 2020-11-17 09:15
 */
public class JWTUtil {

    /**过期时间为2小时*/
    private static final long EXPRIE_TIME = 2*60*60*1000;

    /**
     * 校验用户名和密码
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token中的信息无需secret解密也能获得
     * @param token
    MyRealm 自定义认证器
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").toString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成JWTToken
     * @param username
     * @param secret
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sign(String username, String secret) throws UnsupportedEncodingException {
        Date date = new Date(System.currentTimeMillis()+EXPRIE_TIME);
        Algorithm algorithm = Algorithm.HMAC512(secret);
        // 附带username信息
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }
}
