package com.example.simple.mall.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsInfoDTO;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsReDTO;
import com.example.simple.mall.common.entity.OrderMainLogisticsEntity;
import org.springframework.stereotype.Service;

/**
 * 订单物流信息表 服务类
 *
 * @author sunny
 * @since 2025-06-03
 */
public interface OrderMainLogisticsService extends IService<OrderMainLogisticsEntity> {

    /**
     * 添加订单物流信息
     *
     * @param orderMainLogisticsInfoDTO orderMainLogisticsInfoDTO
     * @author sunny
     * @since 2025/06/03
     */
    void addLogistics(OrderMainLogisticsInfoDTO orderMainLogisticsInfoDTO);

    /**
     * 查询物流订单信息
     *
     * @param id id
     * @return @return {@code OrderMainLogisticsReDTO }
     * @author sunny
     * @since 2025/06/03
     */
    OrderMainLogisticsReDTO getLogistics(Long id);
}
