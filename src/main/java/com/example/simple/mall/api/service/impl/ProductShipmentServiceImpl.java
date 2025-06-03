package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.ProductShipmentMapper;
import com.example.simple.mall.api.service.ProductShipmentService;
import com.example.simple.mall.common.entity.ProductShipmentEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductShipmentServiceImpl extends ServiceImpl<ProductShipmentMapper, ProductShipmentEntity> implements ProductShipmentService {
}
