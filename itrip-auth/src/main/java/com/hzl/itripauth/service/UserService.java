package com.hzl.itripauth.service;

import com.hzl.entiy.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.itripauth.condition.UserRegisterCondition;

public interface UserService extends IService<User> {


//    /**
//     *  用户注册
//     *  @param condition
//     *  @return
//     */
//    boolean userRegister(UserRegisterCondition condition);


    /**
     *  激活用户
     *  @param userCode
     *  @param code
     *  @return
     */
    boolean active(String userCode, String code);

    /**
     * 用户注册
     *  @param condition 注册数据
     *  @param registerType 注册类型，邮箱或手机
     *  @return true
     */
    boolean userRegister(UserRegisterCondition condition,String registerType);

    /**
     *  通过手机注册完成用户的新增操作
     *  @param user 用户对象
     */
    void insertUserByPhone(User user);

    /**
     *短信验证
     *  @param phoneNum 手机号码
     *  @param code 验证码
     *  @return true 表示验证成功，false表示验证失败。
     */
    boolean validatePhone(String phoneNum, String code);
}


