package com.hzl.common.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-27 09:18
 */
@Data
@Api(value = "酒店房型响应对象")
public class HotelRoomVO implements Serializable {
    private static final long serialVersionUID = 1159727189552239873L;
    @ApiModelProperty(value = "户型Id")
    private Long id;
    @ApiModelProperty(value = "对应酒店Id")
    private Long hotelId;
    @ApiModelProperty(value = "户型名称")
    private String roomTitle;
    @ApiModelProperty(value = "房间价格")
    private BigDecimal roomPrice;
    @ApiModelProperty(value = "酒店床型")
    private Long roomBedTypeId;
    @ApiModelProperty(value = "是否包含早餐")
    private Integer isHavingBreakfast;
    @ApiModelProperty(value = "付款方式，1:在线付 2:到店付 3:不限")
    private Integer payType;
    @ApiModelProperty(value = "满意度（冗余字段，在用户评论后更新）")
    private Double satisfaction;
    @ApiModelProperty(value = "是否可预订(0:不可以 1:可以)")
    private Integer isBook;
    @ApiModelProperty(value = "是否可取消(0:不可 1:可以)")
    private Integer isCancel;
    @ApiModelProperty(value = "是否及时响应(0:不及时 1:及时)")
    private Integer isTimelyResponse;
}
