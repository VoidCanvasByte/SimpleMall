package com.example.simple.mall.api.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.OrderMapperStruct;
import com.example.simple.mall.api.mapper.CartItemMapper;
import com.example.simple.mall.api.mapper.OrderItemsMapper;
import com.example.simple.mall.api.mapper.OrderMainMapper;
import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.api.service.ProductDetailsService;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.api.service.virtual.SimulatedPayService;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderPayInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.entity.*;
import com.example.simple.mall.common.enu.OrderStatusEnum;
import com.example.simple.mall.common.enu.ProductStatusEnum;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.enu.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 订单实现层
 *
 * @author sunny
 * @since 2025/05/09
 */
@Service

public class OrderServiceImpl extends ServiceImpl<OrderMainMapper, Order> implements OrderService {

    @Autowired

    private ProductMainService productMainService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private SimulatedPayService simulatedPayService;

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @author sunny
     * @since 2025/05/24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(List<OrderAddInfoDTO> orderAddInfoDTO) {
        //校验
        List<Integer> productIdList = orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getProductId)
                .toList();
        QueryWrapper<Product> productMainWrapper = new QueryWrapper<>();
        productMainWrapper.in("id", productIdList);
        productMainWrapper.eq("status", ProductStatusEnum.NORMAL.getCode());
        List<Product> produceMainInfoList = productMainService.list(productMainWrapper);
        if (ObjectUtil.isEmpty(produceMainInfoList)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        List<String> productCodeList = produceMainInfoList.stream().map(Product::getProductCode).toList();
        QueryWrapper<ProductDetails> productDetailsWrapper = new QueryWrapper<>();
        productDetailsWrapper.in("product_code", productCodeList);
        List<ProductDetails> productDetails = productDetailsService.list(productDetailsWrapper);
        if (ObjectUtil.isEmpty(productDetails)) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }
        boolean hasZeroQuantity = productDetails.stream()
                .map(ProductDetails::getProductQuantity)
                .anyMatch(quantity -> quantity == 0);
        if (hasZeroQuantity) {
            throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
        }

        Integer userId = 0;
        if (orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getUserId).findFirst().isPresent()) {
            userId = orderAddInfoDTO.stream()
                    .map(OrderAddInfoDTO::getUserId).toList().get(1);
        }

        Integer shippingAddressId = 0;
        if (orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getUserId).findFirst().isPresent()) {
            shippingAddressId = orderAddInfoDTO.stream()
                    .map(OrderAddInfoDTO::getShippingAddressId).toList().get(1);
        }
        List<OrderItems> insertOrderItemList = new ArrayList<>();
        List<Integer> productIdsToDelete = new ArrayList<>();
        BigDecimal subtotal = new BigDecimal("0.00");
        //订单
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
        //订单编号 + userName + 下单时间戳
        String orderNumber = String.valueOf(userId) + System.currentTimeMillis();
        order.setOrderNumber(orderNumber);
        order.setUserId(userId);
        order.setShippingAddressId(shippingAddressId);
        order.setPaymentStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
        order.setShippingStatus(OrderStatusEnum.PENDING_SHIPMENT.getCode());
        order.setStatus(StatusEnum.NEW.getCode());
        orderMainMapper.insert(order);
        Integer orderId = order.getId();
        //循环处理订单
        for (OrderAddInfoDTO item : orderAddInfoDTO) {
            OrderItems orderItem = OrderMapperStruct.INSTANCE.orderAddDTOToOrderInfo(item);
            orderItem.setOrderId(order.getId());
            Optional<String> productName = produceMainInfoList.stream()
                    .filter(mainInfo -> mainInfo.getId().equals(item.getProductId()))
                    .map(Product::getProductName)
                    .findFirst();
            productName.ifPresent(orderItem::setProductName);
            orderItem.setVariantId(item.getVariantId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(item.getUnitPrice());
            BigDecimal subtotalTemp = item.getUnitPrice().multiply(new BigDecimal(item.getQuantity()));
            orderItem.setSubtotal(subtotalTemp);
            subtotal = subtotal.add(subtotalTemp);
            insertOrderItemList.add(orderItem);
            orderItem.setOrderId(orderId);
            orderItem.setSubtotal(subtotal);
            productIdsToDelete.add(item.getProductId());
        }
        if (!insertOrderItemList.isEmpty()) {
            orderItemsMapper.batchInsert(insertOrderItemList);
        }
        //删除购物车中的货物信息
        QueryWrapper<CartItem> cartItemQueryWrapper = new QueryWrapper<>();
        cartItemQueryWrapper.eq("user_id", userId);
        cartItemQueryWrapper.in("product_id", productIdsToDelete);
        cartItemMapper.delete(cartItemQueryWrapper);
        QueryWrapper<Order> orderWrapper = new QueryWrapper<>();
        orderWrapper.eq("id", orderId);
        orderWrapper.eq("total_amount", subtotal);
        orderMainMapper.update(orderWrapper);
    }

    /**
     * 订单支付
     *
     * @param orderPayInfoDTO orderPayInfoDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderPay(OrderPayInfoDTO orderPayInfoDTO) {
        String orderId = orderPayInfoDTO.getOrderId();
        QueryWrapper<Order> orderMainWrapper = new QueryWrapper<>();
        orderMainWrapper.eq("id", orderId);
        Order orderInfo = this.getOne(orderMainWrapper);
        if (ObjectUtil.isEmpty(orderInfo)) {
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getMessage());
        }
        //只有状态是代付款的订单才可以进行付款支付
        if (ObjectUtil.equals(orderInfo.getStatus(), OrderStatusEnum.PENDING_PAYMENT.getCode())) {
            //TODO 异步发走 调用模拟支付 发送消息队列发走，是失败还是成功，后续三方支付接口会给返回结果
            Boolean pay = simulatedPayService.pay(orderId);

            //更新订单状态
            QueryWrapper<Order> orderMainStatusWrapper = new QueryWrapper<>();
            orderMainStatusWrapper.eq("id", orderId);
            orderMainStatusWrapper.eq("status", OrderStatusEnum.PAID.getCode());
            orderMainMapper.update(orderMainStatusWrapper);
        }
    }

    /**
     * 订单信息更新
     *
     * @param orderReDTO orderReDTO
     * @author sunny
     * @since 2025/05/25@return@return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderUpdate(OrderReDTO orderReDTO) {
        QueryWrapper<Order> orderMainWrapper = new QueryWrapper<>();
        orderMainWrapper.eq("id", orderReDTO.getOrderId());
        Order orderInfo = this.getOne(orderMainWrapper);
        if (ObjectUtil.isEmpty(orderInfo)) {
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getMessage());
        }
        if (ObjectUtil.equals(orderInfo.getStatus(), OrderStatusEnum.PAID.getCode())) {
            //更新订单状态
            QueryWrapper<Order> orderMainStatusWrapper = new QueryWrapper<>();
            orderMainStatusWrapper.eq("id", orderReDTO.getOrderId());
            orderMainStatusWrapper.eq("status", OrderStatusEnum.PAID.getCode());
            orderMainMapper.update(orderMainStatusWrapper);
        }
    }
}
