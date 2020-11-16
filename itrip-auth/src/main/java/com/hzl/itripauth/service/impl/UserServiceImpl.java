package com.hzl.itripauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzl.common.constants.ErrorCodeEnum;
import com.hzl.common.constants.SystemConstants;
import com.hzl.exception.ServiceException;
import com.hzl.itripauth.condition.UserRegisterCondition;
import com.hzl.itripauth.service.MailService;
import com.hzl.itripauth.service.SmsService;
import com.hzl.itripauth.service.UserService;
import com.hzl.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.mapper.UserMapper;
import com.hzl.entiy.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    String activeCodeKeyPre = "active:";

    @Value(value = "${email.send.enable}")
    private boolean enableSendEmail;

    @Resource
    private MailService mailService;

    @Resource
    private SmsService smsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean active(@NotNull String userCode, @NotNull String code) {
        if (stringRedisTemplate.hasKey(activeCodeKeyPre +userCode)) {
            //先验证激活码是否有效
            String activeCode = stringRedisTemplate.opsForValue()
                    .get(activeCodeKeyPre + userCode);
            if (StringUtils.hasText(activeCode)) {
                if (code.equals(activeCode)) {
                    //当验证通过后，进行用户的修改
                    User user = User.builder().userCode(userCode)
                            .activated(SystemConstants.IS_ACTIVE)
                            .build();
                    boolean updateResult = this.updateById(user);
                    return updateResult;
                }
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean userRegister(UserRegisterCondition condition, String registerType) {
        try {
            //先根据用户名进行二次判断用户是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_code", condition.getUserCode());
            User userExist = this.getOne(queryWrapper);
            if (userExist != null) {
                //用户已存在，直接抛出异常
                throw new ServiceException(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
            }
            //先完成实体类的转换
            condition.setUserPassword(MD5.getMd5(condition.getUserPassword(), 32));
            User user = new User();
            BeanUtils.copyProperties(condition, user);
            // 初始化其他数据,指定用户类型(默认为自注册用户)
            user.setUserType(SystemConstants.REGISTRATION);
            //1、将用户信息存入数据库
            this.save(user);
            /*两种不同的注册方式只有验证码的生成规则和验证不一样*/
            switch (registerType) {
                //邮箱注册
                case "email":
                    //2、生成激活码， 通过当前系统时间缀进行 32 位 MD5 加密，
                    String activationCode = MD5.getMd5(String.valueOf(System.currentTimeMillis()), 32);
                    log.info("激活码：{}", activationCode);
                    //3、发送邮件,
                    //可以通过定义一个发送邮件的开关，根据不同的环境来确定是否需要发送，
                    // 譬如开发环境和测试环境就可以不用发送，只需要在生产环境
                    if (enableSendEmail) {
                        mailService.sendActivationMail(user.getUserCode(), activationCode);
                    }//4、激活码存入Redis中 过期时间为 30 分
                    stringRedisTemplate.opsForValue().set(activeCodeKeyPre + user.getUserCode(), activationCode, 30, TimeUnit.MINUTES);
                    break;
                    //手机注册
                case "phone":
                    //生成验证码
                    int randomCode = MD5.getRandomCode();
                    //存入到redis
                    stringRedisTemplate.opsForValue()
                            .set(activeCodeKeyPre + user.getUserCode(), String.valueOf(randomCode), 5, TimeUnit.MINUTES);
                    log.info("短信验证码：{}",randomCode);
                    //与上面的邮件是否发送同理，短信发一条就是一条的钱
                    if (enableSendEmail) {
                        //发送验证码
                        smsService.sendMsg(user.getUserCode(), String.valueOf(randomCode));
                    }
                    break;
                    default:
                        break;
            }
        } catch (BeansException e) {
            TransactionAspectSupport.currentTransactionStatus().flush();
            throw new ServiceException(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
        }
        return true;
    }

    @Override
    public void insertUserByPhone(User user) {
        this.save(user);
        //生成验证码
        int randomCode = MD5.getRandomCode();
        //发送验证码
//        smsService.sendMsg(user.getUserCode(), "1",
//                new String[] {String.valueOf(randomCode),"1"});
        // 存入到redis
        stringRedisTemplate.opsForValue()
                .set("activition:" + user.getUserCode(),
                        String.valueOf(randomCode), 5, TimeUnit.MINUTES);
    }

    @Override
    public boolean validatePhone(String phoneNum, String code) {
        //1、对比验证码
        String key = "activation:" + phoneNum;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value != null && value.equals(code)) {
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("user_code", phoneNum);
            User user = this.getOne(userWrapper);
            if (user != null) {
                //2、更新用户激活状态
                user.setActivated(SystemConstants.IS_ACTIVE);
                user.setFlatId(user.getId());
                user.setUserType(SystemConstants.REGISTRATION);
                this.updateById(user);
                return true;
            }
        }
        return false;
    }

}


