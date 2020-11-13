package com.hzl.itripauth.service;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 短信service层接口
 * @date : 2020-11-13 09:58
 */
public interface SmsService {

    /*** 用于发送短信
     *  @param to 短信发送给谁
     *  @param code 验证码
     */
    void sendMsg(String to, String code);
}
