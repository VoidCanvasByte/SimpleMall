package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.OrderMainEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单mapper
 *
 * @author sunny
 * @since 2025/05/09
 */
@Mapper
public interface OrderMainMapper extends BaseMapper<OrderMainEntity> {

    /**
     * 添加订单
     *
     * @param orderMainEntity orderMainEntity
     * @author sunny
     * @since 2025/05/31
     */
    void insertOrderMain(OrderMainEntity orderMainEntity);
}