package com.hzl.itripsearch.condition;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店查询条件类
 * @date : 2020-11-23 09:21
 */
@Data
@Api(tags = "酒店查询类")
public class SearchHotCityCondition {
    @ApiModelProperty(value = "城市id")
    private Long CityId;
    @ApiModelProperty(value = "详情")
    private String Destination;
    @ApiModelProperty(value = "酒店级别")
    private Integer HotelLevel;
    @ApiModelProperty(value = "关键词，全文搜索")
    private String Keywords;
    @ApiModelProperty(value = "最低价")
    private Double MinPrice;
    @ApiModelProperty(value = "最高价")
    private Double MaxPrice;
    @ApiModelProperty(value = "酒店特色")
    private String FeatureIds;
    @ApiModelProperty(value = "指定排序,Asc")
    private String AscSort;
    @ApiModelProperty(value = "指定排序,Desc")
    private String DescSort;
    @ApiModelProperty(value = "总页数")
    private Integer PageSize;
    @ApiModelProperty(value = "当前页")
    private Integer PageNo;
    @ApiModelProperty(value = "数据总数量")
    private Long Count;
}
