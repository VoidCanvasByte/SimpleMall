package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.OrderItemsMapper;
import com.example.simple.mall.api.service.OrderItemsService;
import com.example.simple.mall.common.entity.OrderItems;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl extends ServiceImpl<OrderItemsMapper, OrderItems> implements OrderItemsService {
}
