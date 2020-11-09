package com.hzl.exception;

import com.hzl.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义Service层异常
 * @date : 2020-11-09 14:50
 */
@Data
@ApiModel(description = "自定义service层异常")
public class ServiceException extends RuntimeException {

    @ApiModelProperty(value ="异常状态码")
    private String errorCode;

    public ServiceException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServiceException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }

}
