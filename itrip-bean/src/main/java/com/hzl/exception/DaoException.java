package com.hzl.exception;

import com.hzl.common.constants.ErrorCodeEnum;
import com.sun.javaws.exceptions.ErrorCodeResponseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 自定义Dao层异常
 * @date : 2020-11-09 14:45
 */
@Data
@ApiModel(description = "自定义Dao层异常")
public class DaoException extends RuntimeException {

    @ApiModelProperty(value ="异常状态码")
    private String errorCode;

    public DaoException(String msg,String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public DaoException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }

}
