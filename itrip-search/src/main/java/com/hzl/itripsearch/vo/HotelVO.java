package com.hzl.itripsearch.vo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索酒店展示实体类
 * @date : 2020-11-21 13:45
 */
@Data
@Document(indexName = "hotel", type = "_doc")
public class HotelVO implements Serializable {

    private static final long serialVersionUID = -5614573087306371327L;

    @Field("id")
    private Long id;
    @Field(name = "hotelName")
    private String hotelName;
    @Field(name = "cityId")
    private Long cityId;
    @Field(name = "address")
    private String address;
    @Field(name = "hotelLevel")
    private Integer hotelLevel;
    @Field(name = "redundantCityName")
    private String redundantCityName;
    @Field(name = "redundantProvinceName")
    private String redundantProvinceName;
    @Field(name = "redundantCountryName")
    private String redundantCountryName;
    @Field(name = "details")
    private String details;
    @Field(value = "facilities")
    private String facilities;
    @Field(value = "hotelPolicy")
    private String hotelPolicy;

}
