package com.example.simple.mall.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.simple.mall.common.entity.OrderItems;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemsMapper extends BaseMapper<OrderItems> {

    @Insert({
            "<script>",
            "INSERT INTO order_items (product_id, quantity, unit_price) VALUES ",
            "<foreach collection='orderItems' item='item' separator=','>",
            "(#{item.productId}, #{item.quantity}, #{item.unitPrice})",
            "</foreach>",
            "</script>"
    })
    void batchInsert(@Param("orderItems") List<OrderItems> orderItems);
}