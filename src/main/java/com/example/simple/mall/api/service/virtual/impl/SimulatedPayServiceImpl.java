package com.example.simple.mall.api.service.virtual.impl;

import com.example.simple.mall.api.service.virtual.SimulatedPayService;
import org.springframework.stereotype.Service;


@Service
public class SimulatedPayServiceImpl implements SimulatedPayService {


    /**
     * 虚拟支付接口
     *
     * @param orderId 订单ID
     * @return @return {@code Boolean }
     * @author sunny
     * @since 2025/05/25
     */
    @Override
    public Boolean pay(String orderId) {
        return null;
    }
}
