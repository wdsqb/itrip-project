package com.hzl.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzl.entiy.ProductStore;
import com.hzl.mapper.ProductStoreMapper;
import com.hzl.service.ProductStoreService;

@Service
public class ProductStoreServiceImpl extends ServiceImpl<ProductStoreMapper, ProductStore> implements ProductStoreService {

}


