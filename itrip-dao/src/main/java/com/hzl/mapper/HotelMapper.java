package com.hzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzl.common.vo.SearchFacilitiesHotelVO;
import com.hzl.entity.Hotel;
import com.hzl.entity.LabelDic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMapper extends BaseMapper<Hotel> {

    /**
     * 根据酒店Id查询酒店设施
     * @param hotelId
     * @return
     */
    SearchFacilitiesHotelVO selectFacilitiesHotelVOByHotelId(Long hotelId);

    /**
     * 根据酒店Id查询酒店特色
     * @param hotelId
     * @return
     */
    List<LabelDic> selectDetailHotelVOByHotelId(@Param("hotelId") Long hotelId);



}