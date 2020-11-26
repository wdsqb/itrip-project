package com.hzl.itripbiz.service;

import com.hzl.common.vo.SearchDetailHotelVO;
import com.hzl.common.vo.SearchFacilitiesHotelVO;
import com.hzl.entity.Hotel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HotelService extends IService<Hotel> {

    /**
     * 根据酒店Id查询酒店设施
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO getHotelFacilitiesById(Long hotelId);

    /**
     *根据酒店的id查询酒店的特色和介绍
     * @param hotelId
     * @return
     */
    List<SearchDetailHotelVO> getHotelDetailVOById(Long hotelId);
}


