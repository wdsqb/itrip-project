package com.hzl.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 热门城市响应对象
 * @date : 2020-11-21 10:18
 */
@Data
@ApiModel(description = "热门城市响应对象")
public class AreaDicVO implements Serializable {

    private static final long serialVersionUID = 6813835817234849697L;
    @ApiModelProperty(name = "id", value = "热门城市id")
    private Long id;
    @ApiModelProperty(name = "name", value = "热门城市名称")
    private String name;
}
