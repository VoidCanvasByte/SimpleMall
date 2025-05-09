package com.example.simple.mall.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapper.OrderMainMapper;
import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.common.entity.OrderMain;
import org.springframework.stereotype.Service;

/**
 * 订单实现层
 *
 * @author sunny
 * @since 2025/05/09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMainMapper, OrderMain> implements OrderService {

}
