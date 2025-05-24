package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.productDetailsMapper;
import com.example.simple.mall.api.service.ProductDetailsService;
import com.example.simple.mall.common.entity.ProductDetails;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailsServiceImpl extends ServiceImpl<productDetailsMapper, ProductDetails> implements ProductDetailsService {


}
