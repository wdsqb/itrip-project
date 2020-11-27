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

    /**
     * 根据类型和目标查询图片
     * @param typeId 图片类型
     * @param targetId 目标Id
     * @return
     */
    List<ImageVO> getImageVOByTypeAndTarget(Integer typeId,Long targetId);
}


