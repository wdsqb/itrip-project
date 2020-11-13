package com.hzl.itripauth.condition;

import lombok.Data;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-12 11:35
 */
@Data
public class UserRegisterCondition {

    private String userName;
    private String userCode;
    private String userPassword;
}
