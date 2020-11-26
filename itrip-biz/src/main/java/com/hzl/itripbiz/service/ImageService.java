package com.hzl.itripbiz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzl.common.vo.ImageVO;
import com.hzl.entity.Image;

import java.util.List;

public interface ImageService extends IService<Image> {

    /**
     * 根据酒店Id查询图片
     * @param hotelId
     * @return
     */
    List<ImageVO> getImageVOByHotelId(Long hotelId);
}


