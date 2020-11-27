package com.hzl.common.condition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :
 * @date : 2020-11-27 09:19
 */
@Data
@ApiModel(value = "酒店户型查询条件实体类")
public class HotelRoomCondition implements Serializable {
    private static final long serialVersionUID = -4030522947863312906L;
    @ApiModelProperty("[必填] 酒店ID")
    private Long hotelId;
    @ApiModelProperty("[非必填] 是否预订(0:未预定 1:预订)")
    private Integer isBook;
    @ApiModelProperty("[非必填] 是否有早餐(0:没有 1:有)")
    private Integer isHavingBreakfast;
    @ApiModelProperty("[非必填] 是否及时响应(0:不及时 1:及时)")
    private Integer isTimelyResponse;
    @ApiModelProperty("[非必填] 床型ID")
    private Long roomBedTypeId;
    @ApiModelProperty("[必填] 入住日期")
    private LocalDateTime startDate;
    @ApiModelProperty("[必填] 退房日期")
    private LocalDateTime endDate;
    @ApiModelProperty("[非必填] 是否可取消(0:不可以 1:可以)")
    private Integer isCancel;
    @ApiModelProperty("[非必填] 支付类型(1:在线付 2:到店付 3:不限)")
    private Integer payType;
}

