package com.hzl.itripbiz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.common.vo.ImageVO;
import com.hzl.entity.Image;
import com.hzl.itripbiz.service.ImageService;
import com.hzl.mapper.ImageMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Override
    public List<ImageVO> getImageVOByHotelId(Long hotelId) {
    //使用lambda表达式的条件
        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new LambdaQueryWrapper<>
                ();
        imageLambdaQueryWrapper.select(Image::getPosition, Image::getImgUrl)
                .eq(Image::getTargetId, hotelId)
                // 图片类型(0:酒店图片1:房间图片2:评论图片)
                .eq(Image::getType, "0");
        //执行条件查询
        List<Image> imageList = this.list(imageLambdaQueryWrapper);
        //执行数据类型转换 Image -> ImageVO
        List<ImageVO> imageVOList = imageList.stream().map(
                image -> {
                    ImageVO imageVO = new ImageVO();
                    imageVO.setPosition(image.getPosition());
                    imageVO.setImgUrl(image.getImgUrl());
                    return imageVO;
                }
        ).collect(Collectors.toList());
        return imageVOList;
    }

    @Override
    public List<ImageVO> getImageVOByTypeAndTarget(Integer typeId, Long targetId) {
        LambdaQueryWrapper<Image> imageLambdaQueryWrapper = new QueryWrapper<Image>().lambda();
        imageLambdaQueryWrapper.select(Image::getPosition, Image::getImgUrl)
                .eq(Image::getTargetId, targetId)
                .eq(Image::getType, typeId);
        List<Image> imageList = this.list(imageLambdaQueryWrapper);
        List<ImageVO> imageVOList = imageList.stream().map(
                image -> {
                    ImageVO imageVO = new ImageVO();
                    imageVO.setImgUrl(image.getImgUrl());
                    imageVO.setPosition(image.getPosition());
                    return imageVO;
                }
        ).collect(Collectors.toList());
        return imageVOList;
    }
}


