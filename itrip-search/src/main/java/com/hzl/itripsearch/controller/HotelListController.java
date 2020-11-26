package com.hzl.itripsearch.controller;

import com.hzl.common.vo.ReturnResult;
import com.hzl.itripsearch.condition.SearchHotCityCondition;
import com.hzl.itripsearch.service.SearchHotelService;
import com.hzl.itripsearch.vo.HotelVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description :  酒店列表控制器
 * @date : 2020-11-23 09:19
 */
@RestController
@RequestMapping(value = "/api/hotellist")
public class HotelListController {

    @Resource
    private SearchHotelService searchHotelService;
    @ApiOperation(value = "根据热门城市查询酒店", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "根据热门城市查询酒店")
    @RequestMapping(value = "/searchItripHotelListByHotCity",
            produces = MediaType.APPLICATION_JSON_VALUE, method =
            RequestMethod.POST)
    public ReturnResult searchHotelListByHotCity(@RequestBody SearchHotCityCondition condition) {
        List<HotelVO> hotelVOList =
                searchHotelService.searchHotelByHotCity(condition.getCityId(),
                        condition.getCount());
        return ReturnResult.ok(hotelVOList);
    }

    /**
     * 搜索酒店分页
     *
     * @param condition
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "查询酒店分页", httpMethod = "POST",
            protocols = "HTTP", produces = "application/json",
            response = ReturnResult.class, notes = "查询酒店分页(用于查询酒店列表)")
    @RequestMapping(value = "/searchItripHotelPage",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    public ReturnResult searchHotelPage(@RequestBody SearchHotCityCondition condition)
    {
        Page<HotelVO> hotelVOPage = searchHotelService.searchHotelPage(condition);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("rows", hotelVOPage.getContent());
        return ReturnResult.ok(returnMap);
    }

}
