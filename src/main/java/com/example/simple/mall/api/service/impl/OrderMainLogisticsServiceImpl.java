package com.example.simple.mall.api.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.OrderMapperStruct;
import com.example.simple.mall.api.mapper.OrderMainLogisticsMapper;
import com.example.simple.mall.api.service.OrderMainLogisticsService;
import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsInfoDTO;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsReDTO;
import com.example.simple.mall.common.entity.OrderMainEntity;
import com.example.simple.mall.common.entity.OrderMainLogisticsEntity;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.enu.ShippingStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单物流信息表 服务实现类
 *
 * @author sunny
 * @since 2025-06-03
 */
@Service
public class OrderMainLogisticsServiceImpl extends ServiceImpl<OrderMainLogisticsMapper, OrderMainLogisticsEntity> implements OrderMainLogisticsService {

    @Autowired
    public OrderService orderService;

    /**
     * 添加订单物流信息
     *
     * @param orderMainLogisticsInfoDTO orderMainLogisticsInfoDTO
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addLogistics(OrderMainLogisticsInfoDTO orderMainLogisticsInfoDTO) {
        Long orderId = orderMainLogisticsInfoDTO.getOrderId();
        QueryWrapper<OrderMainEntity> orderMainEntityQueryWrapper = new QueryWrapper<>();
        orderMainEntityQueryWrapper.eq("id", orderId);
        OrderMainEntity orderMainEntityTemp = orderService.getOne(orderMainEntityQueryWrapper);
        if (ObjectUtil.isEmpty(orderMainEntityTemp)) {
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getMessage());
        }
        //更新订单发货状态
        UpdateWrapper<OrderMainEntity> orderMainEntityUpdateWrapper = new UpdateWrapper<>();
        orderMainEntityUpdateWrapper.eq("id", orderId);
        orderMainEntityUpdateWrapper.set("shipping_status", ShippingStatusEnum.SHIPPED.getCode());
        orderService.update(null, orderMainEntityUpdateWrapper);
        //添加物流信息
        OrderMainLogisticsEntity orderMainLogisticsEntity = OrderMapperStruct.INSTANCE.OrderMainLogisticsEntityToOrderMainLogisticsInfoDTO(orderMainLogisticsInfoDTO);
        this.save(orderMainLogisticsEntity);
    }

    /**
     * 查询物流订单信息
     *
     * @param id id
     * @return @return {@code OrderMainLogisticsReDTO }
     * @author sunny
     * @since 2025/06/03
     */
    @Override
    public OrderMainLogisticsReDTO getLogistics(Long id) {
        QueryWrapper<OrderMainLogisticsEntity> orderMainLogisticsEntityQueryWrapper = new QueryWrapper<>();
        orderMainLogisticsEntityQueryWrapper.eq("id", id);
        OrderMainLogisticsEntity one = this.getOne(orderMainLogisticsEntityQueryWrapper);
        return OrderMapperStruct.INSTANCE.OrderMainLogisticsEntityToOrderMainLogisticsReDTO(one);
    }
}
