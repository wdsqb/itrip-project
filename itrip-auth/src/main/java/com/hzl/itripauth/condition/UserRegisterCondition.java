package com.hzl.itripauth.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 用户注册条件实体类
 * @date : 2020-11-12 11:35
 */
@Data
@ApiModel(value = "UserRegisterCondition",description = "用户注册条件实体类")
public class UserRegisterCondition implements Serializable {

    private static final long serialVersionUID = -3929050888935396734L;

    @ApiModelProperty(value = "用户账号")
    private String userName;
    @ApiModelProperty(value = "用户名")
    private String userCode;
    @ApiModelProperty(value = "用户密码")
    private String userPassword;
}
