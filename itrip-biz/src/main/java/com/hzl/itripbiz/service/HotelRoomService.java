package com.hzl.itripbiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.common.condition.HotelRoomCondition;
import com.hzl.common.vo.HotelRoomVO;
import com.hzl.entity.HotelRoom;

import java.util.List;

public interface HotelRoomService extends IService<HotelRoom> {
    /**
     * 根据条件查询酒店房型
     * @param condition
     * @return
     */
    List<HotelRoomVO> getHotelRoomVOByCondition(HotelRoomCondition condition);

}


