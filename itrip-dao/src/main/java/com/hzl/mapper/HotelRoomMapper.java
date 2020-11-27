package com.hzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzl.common.vo.HotelRoomVO;
import com.hzl.common.condition.HotelRoomCondition;
import com.hzl.entity.HotelRoom;

import java.util.List;

public interface HotelRoomMapper extends BaseMapper<HotelRoom> {

    /**
     * 根据条件查询酒店房型
     * @param condition
     * @return
     */
    List<HotelRoomVO> selectHotelRoomVOByCondition(HotelRoomCondition condition);

}