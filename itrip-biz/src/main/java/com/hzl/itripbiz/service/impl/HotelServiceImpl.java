package com.hzl.itripbiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzl.common.vo.SearchDetailHotelVO;
import com.hzl.common.vo.SearchFacilitiesHotelVO;
import com.hzl.entity.LabelDic;
import com.hzl.itripbiz.service.HotelService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entity.Hotel;
import com.hzl.mapper.HotelMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Resource
    private HotelMapper hotelMapper;

    @Override
    public SearchFacilitiesHotelVO getHotelFacilitiesById(Long hotelId) {
        return hotelMapper.selectFacilitiesHotelVOByHotelId(hotelId);
    }

    @Override
    public List<SearchDetailHotelVO> getHotelDetailVOById(Long hotelId) {
        //根据需求，酒店特色和介绍是分别存储在不同的表里面的，所以需要在代码中处理
        //1、特色需要通过多表联查的方式实现
        List<LabelDic> labelDicList =
                hotelMapper.selectDetailHotelVOByHotelId(hotelId);
        //2、酒店介绍，可以直接查询
        LambdaQueryWrapper<Hotel> hotelLambdaQueryWrapper = new LambdaQueryWrapper<>
                ();
        hotelLambdaQueryWrapper.eq(Hotel::getId, hotelId)
                .select(Hotel::getHotelName, Hotel::getDetails);
        Hotel hotel = this.getOne(hotelLambdaQueryWrapper);
        //数据封装，特色
        List<SearchDetailHotelVO> detailHotelVOList = labelDicList.stream().map(
                labelDic -> {
                    SearchDetailHotelVO searchDetailHotelVO = new
                            SearchDetailHotelVO();
                    searchDetailHotelVO.setDescription(labelDic.getDescription());
                    searchDetailHotelVO.setName(labelDic.getName());
                    return searchDetailHotelVO;
                }
        ).collect(Collectors.toList());
        //将介绍封装进来
        SearchDetailHotelVO searchDetailHotelVO = new SearchDetailHotelVO();
        searchDetailHotelVO.setName("酒店介绍");
        searchDetailHotelVO.setDescription(hotel.getDetails());
        detailHotelVOList.add(searchDetailHotelVO);
        return detailHotelVOList;
    }

}