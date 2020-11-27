package com.hzl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzl.common.condition.ValidateRoomStoreCondition;
import com.hzl.common.vo.RoomStoreVO;
import com.hzl.entity.HotelOrder;

public interface HotelOrderMapper extends BaseMapper<HotelOrder> {

    /**
     * 根据条件预生成订单
     * @param condition
     * @return
     */
    RoomStoreVO selectRoomStoreVOByCondition(ValidateRoomStoreCondition condition);

}