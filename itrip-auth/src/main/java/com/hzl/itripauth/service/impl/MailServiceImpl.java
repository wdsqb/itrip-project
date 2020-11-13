package com.hzl.itripauth.service.impl;

import com.hzl.itripauth.service.MailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-12 14:10
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private MailSender mailSender;

    @Override public void sendActivationMail(String mailTo, String activationCode) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText("您的激活码是：" + activationCode);
        mailSender.send(simpleMailMessage);
    }

}
