package com.hzl.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : Jwt工具类，用于生成和校验jwt
 * @date : 2020-11-17 09:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Token响应对象")
public class TokenVo implements Serializable {
    private static final long serialVersionUID = 4480847780159323587L;
    @ApiModelProperty(value = "用户认证凭证")
    private String token;
    @ApiModelProperty(value = "过期时间，单位毫秒")
    private Long expTime;
    @ApiModelProperty(value = "生成时间，单位毫秒")
    private Long genTime;
}
