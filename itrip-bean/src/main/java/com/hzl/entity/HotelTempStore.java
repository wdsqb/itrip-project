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

/**
 * 实时库存表
 */
@ApiModel(value = "com-hzl-entiy-HotelTempStore")
@Data
@Builder
@TableName(value = "itrip_hotel_temp_store")
@AllArgsConstructor
@NoArgsConstructor
public class HotelTempStore implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    @TableField(value = "hotel_id")
    @ApiModelProperty(value = "")
    private Integer hotelId;

    /**
     * 商品id
     */
    @TableField(value = "room_id")
    @ApiModelProperty(value = "商品id")
    private Long roomId;

    /**
     * 记录时间
     */
    @TableField(value = "record_date")
    @ApiModelProperty(value = "记录时间")
    private Date recordDate;

    /**
     * 库存
     */
    @TableField(value = "store")
    @ApiModelProperty(value = "库存")
    private Integer store;

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


    private static final long serialVersionUID = -7035886363133043769L;

    public static HotelTempStoreBuilder builder() {
        return new HotelTempStoreBuilder();
    }
}