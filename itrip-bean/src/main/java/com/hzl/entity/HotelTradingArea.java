package com.hzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "com-hzl-entiy-HotelTradingArea")
@Data
@Builder
@TableName(value = "itrip_hotel_trading_area")
@AllArgsConstructor
@NoArgsConstructor
public class HotelTradingArea implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    /**
     * 酒店id
     */
    @TableField(value = "hotel_id")
    @ApiModelProperty(value = "酒店id")
    private Long hotelId;

    /**
     * 商圈id
     */
    @TableField(value = "area_id")
    @ApiModelProperty(value = "商圈id")
    private Long areaId;

    @TableField(value = "creation_date")
    @ApiModelProperty(value = "")
    private Date creationDate;

    @TableField(value = "created_by")
    @ApiModelProperty(value = "")
    private Long createdBy;

    @TableField(value = "modify_date")
    @ApiModelProperty(value = "")
    private Date modifyDate;

    @TableField(value = "modified_by")
    @ApiModelProperty(value = "")
    private Long modifiedBy;

    /**
     * 逻辑删除（0:未删除；1：删除）
     */
    @TableLogic
    @TableField(value = "is_deleted")
    @ApiModelProperty(value = "逻辑删除（0:未删除；1：删除）")
    private Integer isDeleted;


    private static final long serialVersionUID = -5565522104052526117L;

    public static HotelTradingAreaBuilder builder() {
        return new HotelTradingAreaBuilder();
    }
}