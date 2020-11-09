package com.hzl.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.mapper.HotelOrderMapper;
import com.hzl.entiy.HotelOrder;
import com.hzl.service.HotelOrderService;

@Service
public class HotelOrderServiceImpl extends ServiceImpl<HotelOrderMapper, HotelOrder> implements HotelOrderService {

}


