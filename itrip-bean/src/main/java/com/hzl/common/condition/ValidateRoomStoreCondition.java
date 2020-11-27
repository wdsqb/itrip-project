package com.hzl.common.condition;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 验证房间库存条件实体类
 * @date : 2020-11-27 09:43
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@ApiModel(value = "ValidateRoomStoreCondition",description = "验证房间库存条件实体类")
public class ValidateRoomStoreCondition implements Serializable {

        private static final long serialVersionUID = 2981971254272757867L;
        @ApiModelProperty("[必填，注：接收数字类型 酒店ID")
        private Long hotelId;
        @ApiModelProperty("[必填，注：接收数字类型 房间ID")
        private Long roomId;
        @ApiModelProperty("[必填，注：接收日期类型 入住时间")
        private LocalDate checkInDate;
        @ApiModelProperty("[必填，注：接收日期类型 退房时间")
        private LocalDate checkOutDate;
        @ApiModelProperty("[必填，默认请传1")
        private Integer count;

}
