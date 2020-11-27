package com.hzl.itripsearch.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @date : 2020-11-26 13:46
 */
@Data
@ApiModel(description = "搜索酒店条件实体类")
public class SearchHotelCondition implements Serializable {

    private static final long serialVersionUID = -7420390484954094999L;

    @ApiModelProperty(value = "[必填] 目的地")
    private String destination;
    @ApiModelProperty(value = "[非必填] 酒店级别(1-5)")
    private Integer hotelLevel;
    @ApiModelProperty(value = "[非必填] 关键词")
    private String keywords;
    @ApiModelProperty(value = "[非必填] 商圈id(每次只能选一个)")
    private String tradeAreaIds;
    @ApiModelProperty(value = "[非必填] 最低价")
    private Double minPrice;
    @ApiModelProperty(value = "[非必填] 最高价")
    private Double maxPrice;
    @ApiModelProperty(value = "[非必填] 酒店特色id(每次只能选一个)")
    private String featureIds;
    @ApiModelProperty(value = "[非必填] 按照某个字段升序,取值为(isOkCount或avgScore或minPrice或hotelLevel)")
    private String ascSort;
    @ApiModelProperty(value = "[非必填] 按照某个字段降序,取值为(isOkCount或avgScore或minPrice或hotelLevel)")
    private String  descSort;
    @ApiModelProperty(value = "[非必填] 入住日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkInDate;
    @ApiModelProperty(value = "[非必填] 退房日期")
    private LocalDateTime checkOutDate;
    @ApiModelProperty(value = "每页数量")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "页数")
    private Integer pageNo = 0;
}