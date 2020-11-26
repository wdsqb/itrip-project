package com.hzl.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value = "com-hzl-entiy-HotelFeature")
@Data
@Builder
@TableName(value = "itrip_hotel_feature")
public class HotelFeature implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "")
    private Long id;

    @TableField(value = "hotel_id")
    @ApiModelProperty(value = "")
    private Long hotelId;

    @TableField(value = "feature_id")
    @ApiModelProperty(value = "")
    private Long featureId;

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

    private static final long serialVersionUID = -198158346895722902L;

    public static HotelFeatureBuilder builder() {
        return new HotelFeatureBuilder();
    }
}