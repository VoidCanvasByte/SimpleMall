package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductFavoritesMapper;
import com.example.simple.mall.api.service.ProductFavoritesService;
import com.example.simple.mall.common.entity.ProductFavoritesEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductFavoritesServiceImpl extends ServiceImpl<ProductFavoritesMapper, ProductFavoritesEntity> implements ProductFavoritesService {


}
