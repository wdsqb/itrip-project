package com.hzl.itripauth.service;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-auth
 * @description : 邮箱业务类接口
 * @date : 2020-11-12 14:08
 */
public interface MailService {

    /**1
     *发送包括激活码的邮件，用于激活用户账号
     * @param mailTo 收件人的邮箱地址
     * @param activationCode 验证码
     */
    void sendActivationMail(String mailTo,String activationCode);


}
