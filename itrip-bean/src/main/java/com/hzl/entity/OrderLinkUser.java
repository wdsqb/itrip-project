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

@ApiModel(value = "com-hzl-entiy-OrderLinkUser")
@Data
@Builder
@TableName(value = "itrip_order_link_user")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLinkUser implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value = "订单id")
    private Long orderId;

    /**
     * 联系人id
     */
    @TableField(value = "link_user_id")
    @ApiModelProperty(value = "联系人id")
    private Long linkUserId;

    /**
     * 入住人姓名逗号分隔
     */
    @TableField(value = "link_user_name")
    @ApiModelProperty(value = "入住人姓名逗号分隔")
    private String linkUserName;

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


    private static final long serialVersionUID = -247825625142884013L;

    public static OrderLinkUserBuilder builder() {
        return new OrderLinkUserBuilder();
    }
}