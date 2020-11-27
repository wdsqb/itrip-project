package com.hzl.itripbiz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.common.condition.HotelRoomCondition;
import com.hzl.common.vo.HotelRoomVO;
import com.hzl.entity.HotelRoom;
import com.hzl.itripbiz.service.HotelRoomService;
import com.hzl.mapper.HotelRoomMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomServiceImpl extends ServiceImpl<HotelRoomMapper, HotelRoom> implements HotelRoomService {

    @Override
    public List<HotelRoomVO> getHotelRoomVOByCondition(HotelRoomCondition condition) {
        List<HotelRoomVO> hotelRoomVOS = baseMapper.selectHotelRoomVOByCondition(condition);
        return hotelRoomVOS;
    }
}


