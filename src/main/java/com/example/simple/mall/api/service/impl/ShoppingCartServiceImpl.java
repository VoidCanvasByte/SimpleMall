package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ShoppingCartMapper;
import com.example.simple.mall.api.service.ShoppingCartService;
import com.example.simple.mall.common.entity.ShoppingCartEntity;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCartEntity> implements ShoppingCartService {
}
