package com.hzl.common.vo;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.hzl.common.constants.ErrorCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sun.awt.geom.AreaOp;

import java.io.Serializable;
import java.security.cert.TrustAnchor;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-06 15:50
 */
@ApiModel(description = "统一返回结果集")
@Data
public class RetrunResult implements Serializable {

    private static final long serialVersionUID = -7198848656809015750L;

    @ApiModelProperty(value = "是否成功")
    private boolean success;
    @ApiModelProperty(value = "错误码")
    private String errorCode;
    @ApiModelProperty(value = "返回消息")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private Object data;

    public RetrunResult(boolean success, String errorCode, String msg, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 操作成功
     * @return
     */
    public static RetrunResult ok() {
        return ok(null);
    }

    /**
     * 操作成功,返回响应数据
     * @param data 响应数据
     * @return
     */
    public static RetrunResult ok(Object data) {
        return ok(ErrorCodeEnum.OK.getErrorCode(),data);
    }

    /**
     * 操作成功，返回指定信息
     * @param msg 返回消息
     * @param data 响应数据
     * @return
     */
    public static RetrunResult ok(String msg, Object data) {
        return new RetrunResult(true, ErrorCodeEnum.OK.getErrorCode(),msg,data);
    }

    /**
     * 操作失败,指定错误码,响应消息,响应数据
     * @param errorCode 错误码
     * @param msg 响应消息
     * @param data 消息数据
     * @return
     */
    public static RetrunResult error(String errorCode,String msg,Object data) {
        return new RetrunResult(false, errorCode, msg, data);
    }

    /**
     * 操作失败,错误码枚举对象
     * @param errorCodeEnum 错误码枚举对象
     * @return
     */
    public static RetrunResult error(ErrorCodeEnum errorCodeEnum) {
        return error(errorCodeEnum.getErrorCode(), errorCodeEnum.getMsg(), null);
    }

    public static RetrunResult error() {
        return error(ErrorCodeEnum.FAILED);
    }
}
