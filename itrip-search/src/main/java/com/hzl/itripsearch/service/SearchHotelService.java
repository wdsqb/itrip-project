package com.hzl.itripsearch.service;

import com.hzl.itripsearch.condition.SearchHotCityCondition;
import com.hzl.itripsearch.vo.HotelVO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 酒店业务类接口
 * @date : 2020-11-21 15:55
 */
public interface SearchHotelService {
    /**
     * 根据热门城市查询酒店
     * @param cityId
     * @param pageSize
     * @return
     */
    List<HotelVO> searchHotelByHotCity(Long cityId, Long pageSize);

    /**
     * 搜索酒店
     * @param condition
     * @return
     */
    Page<HotelVO> searchHotelPage(SearchHotCityCondition condition);

}
