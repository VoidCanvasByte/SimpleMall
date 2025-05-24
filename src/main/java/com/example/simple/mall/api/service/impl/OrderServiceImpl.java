package com.example.simple.mall.api.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.OrderMapperStruct;
import com.example.simple.mall.api.mapper.CartItemMapper;
import com.example.simple.mall.api.mapper.OrderMainMapper;
import com.example.simple.mall.api.service.OrderInfoService;
import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.api.service.ProductDetailsService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.OrderAddDTO;
import com.example.simple.mall.common.entity.OrderInfo;
import com.example.simple.mall.common.entity.OrderMain;
import com.example.simple.mall.common.entity.ProductDetails;
import com.example.simple.mall.common.entity.ProductMain;
import com.example.simple.mall.common.enu.OrderStatusEnum;
import com.example.simple.mall.common.enu.ProductStatusEnum;
import com.example.simple.mall.common.enu.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 订单实现层
 *
 * @author sunny
 * @since 2025/05/09
 */
@Service

public class OrderServiceImpl extends ServiceImpl<OrderMainMapper, OrderMain> implements OrderService {

    @Autowired
    private ProductMainService productMainService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private OrderInfoService OrderInfoService;

    @Autowired
    private CartItemMapper cartItemMapper;

    /**
     * 添加订单
     *
     * @param orderAddDTO orderAddDTO
     * @author sunny
     * @since 2025/05/24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(OrderAddDTO orderAddDTO) {
        String productId = orderAddDTO.getProductId();
        BigDecimal quantity = orderAddDTO.getQuantity();

        QueryWrapper<ProductMain> productMainWrapper = new QueryWrapper<>();
        productMainWrapper.eq("product_id", productId);
        productMainWrapper.eq("status", ProductStatusEnum.NORMAL.getCode());
        ProductMain produceMainInfo = productMainService.getOne(productMainWrapper);
        if (ObjectUtil.isEmpty(produceMainInfo)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        QueryWrapper<ProductDetails> productDetailsWrapper = new QueryWrapper<>();
        productDetailsWrapper.ge("product_quantity", quantity);
        productDetailsWrapper.eq("product_code", produceMainInfo.getProductCode());
        ProductDetails productDetails = productDetailsService.getOne(productDetailsWrapper);
        if (ObjectUtil.isEmpty(productDetails)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        BigDecimal multiply = productDetails.getProductPrice().multiply(orderAddDTO.getQuantity()).setScale(2, RoundingMode.HALF_UP);
        OrderMain orderMain = new OrderMain();
        String orderId = UUID.randomUUID().toString();
        orderMain.setId(orderId);
        orderMain.setUserId(orderAddDTO.getUserId());
        orderMain.setStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
        this.save(orderMain);
        OrderInfo orderInfo = OrderMapperStruct.INSTANCE.orderAddDTOToOrderInfo(orderAddDTO);
        orderInfo.setOrderId(orderId);
        orderInfo.setProductPrice(multiply);
        OrderInfoService.save(orderInfo);
    }
}
