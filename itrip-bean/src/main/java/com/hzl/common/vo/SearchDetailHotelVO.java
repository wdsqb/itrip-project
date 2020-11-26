package com.hzl.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店设施展示实体类
 * @date : 2020-11-21 11:24
 */
@Data
@ApiModel(description = "酒店设施展示实体类")
public class SearchDetailHotelVO implements Serializable {
    private static final long serialVersionUID = 4125968702049349003L;
    @ApiModelProperty(value = "酒店特色id")
    private Long id;
    @ApiModelProperty(value = "酒店特色名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
}
