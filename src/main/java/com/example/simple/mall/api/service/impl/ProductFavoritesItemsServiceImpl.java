package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductFavoritesItemsMapper;
import com.example.simple.mall.api.service.ProductFavoritesItemsService;
import com.example.simple.mall.common.entity.ProductFavoritesItemsEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductFavoritesItemsServiceImpl extends ServiceImpl<ProductFavoritesItemsMapper, ProductFavoritesItemsEntity> implements ProductFavoritesItemsService {
}
