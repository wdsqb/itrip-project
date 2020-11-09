package com.hzl.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entiy.Hotel;
import com.hzl.mapper.HotelMapper;
import com.hzl.service.HotelService;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

}


