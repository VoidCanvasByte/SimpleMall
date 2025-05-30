package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductVariantsMapper;
import com.example.simple.mall.api.service.ProductVariantsService;
import com.example.simple.mall.common.entity.ProductVariantsEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantsServiceImpl extends ServiceImpl<ProductVariantsMapper, ProductVariantsEntity> implements ProductVariantsService {
}
