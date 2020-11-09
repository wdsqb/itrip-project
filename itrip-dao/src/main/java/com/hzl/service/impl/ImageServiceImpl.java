package com.hzl.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entiy.Image;
import com.hzl.mapper.ImageMapper;
import com.hzl.service.ImageService;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

}


