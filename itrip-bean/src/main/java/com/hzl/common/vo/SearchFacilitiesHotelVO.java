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
 * @date : 2020-11-21 11:19
 */
@Data
@ApiModel(description = "酒店设施展示实体类")
public class SearchFacilitiesHotelVO implements Serializable {
    private static final long serialVersionUID = -4896309496175191725L;
    @ApiModelProperty(value = "[必填] 酒店设施")
    private String facilities;
}
