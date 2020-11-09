package com.hzl.exception;

import com.hzl.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义系统异常
 * @date : 2020-11-09 14:53
 */
@Data
@ApiModel(description = "自定义系统异常")
public class SysException extends RuntimeException{

    @ApiModelProperty(value ="异常状态码")
    private String errorCode;

    public SysException(String message,String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SysException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }

}
