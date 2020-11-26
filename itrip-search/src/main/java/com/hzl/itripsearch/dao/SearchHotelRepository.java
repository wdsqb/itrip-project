package com.hzl.itripsearch.dao;


import com.hzl.itripsearch.vo.HotelVO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author : hzl
 * @version : 4.0
 * @project : itrip-project
 * @description : 搜索酒店dao层接口
 * @date : 2020-11-21 15:51
 */
public interface SearchHotelRepository extends ElasticsearchRepository<HotelVO, Long> {
    /**
     * 根据城市Id查询酒店信息
     * @param cityId 酒店id
     * @return 酒店信息列表
     */
    List<HotelVO> findByCityId(Long cityId);
}

