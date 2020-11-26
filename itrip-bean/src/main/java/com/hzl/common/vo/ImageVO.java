package com.hzl.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店图片展示实体类
 * @date : 2020-11-21 11:57
 */
@Data
@ApiModel(description = "酒店图片展示实体类")
public class ImageVO implements Serializable {
    private static final long serialVersionUID = -6655502870378857809L;
    @ApiModelProperty(value = "页面图片展现顺序")
    private Integer position;
    @ApiModelProperty(value = "图片的URL访问路径")
    private String imgUrl;
}
