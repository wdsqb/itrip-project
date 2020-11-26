package com.hzl.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店特色对象
 * @date : 2020-11-21 11:11
 */
@Data
@ApiModel(description = "酒店特色对象")
public class LabelDicVO implements Serializable {

    private static final long serialVersionUID = 4829563094834677946L;
    @ApiModelProperty(value = "酒店特色id")
    private Long id;
    @ApiModelProperty(value = "酒店特色名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "标签图片地址")
    private String pic;
}
