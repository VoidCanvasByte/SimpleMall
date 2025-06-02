package com.example.simple.mall.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.simple.mall.api.mapStruct.OrderMapperStruct;
import com.example.simple.mall.api.mapper.ShoppingCartItemMapper;
import com.example.simple.mall.api.mapper.OrderMainMapper;
import com.example.simple.mall.api.mapper.ShoppingCartMapper;
import com.example.simple.mall.api.service.*;
import com.example.simple.mall.api.service.virtual.SimulatedPayService;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderPayInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.entity.*;
import com.example.simple.mall.common.enu.OrderStatusEnum;
import com.example.simple.mall.common.enu.ProductStatusEnum;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.enu.StatusEnum;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


/**
 * 订单实现层
 *
 * @author sunny
 * @since 2025/05/09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMainMapper, OrderMainEntity> implements OrderService {

    @Autowired
    private ProductMainService productMainService;

    @Autowired
    private ProductDetailsService productDetailsService;

    @Autowired
    private OrderMainMapper orderMainMapper;

    @Autowired
    private ProductVariantsServiceImpl productVariantsServiceImpl;

    @Autowired
    private OrderItemsService orderItemsService;

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private SimulatedPayService simulatedPayService;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @author sunny
     * @since 2025/05/24
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(List<OrderAddInfoDTO> orderAddInfoDTO) throws Exception {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderAddInfoDTO itemDto : orderAddInfoDTO) {
            BigDecimal subtotalTemp = itemDto.getUnitPrice().multiply(new BigDecimal(itemDto.getQuantity()));
            subtotal = subtotal.add(subtotalTemp);
        }
        boolean locked = false;
        //为了防止死锁添加排序
        List<Long> productIdList = orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getProductId)
                .distinct()
                .sorted()
                .toList();
        Long userId = 0L;
        if (orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getUserId).findFirst().isPresent()) {
            userId = orderAddInfoDTO.get(0).getUserId();
        }
        Long shippingAddressId = 0L;
        if (orderAddInfoDTO.stream()
                .map(OrderAddInfoDTO::getUserId).findFirst().isPresent()) {
            shippingAddressId = orderAddInfoDTO.get(0).getShippingAddressId();
        }
        //TODO  订单防重校验（幂等性处理），如幂等token/唯一业务号，可扩展
        // 幂等性可以用业务号+唯一索引，防止重复扣库存
        //商品锁
        List<RLock> lockList = new ArrayList<>();
        for (Long productId : productIdList) {
            RLock lock = redissonClient.getLock("order:product:" + productId);
            lockList.add(lock);
        }
        RLock multiLock = new RedissonMultiLock(lockList.toArray(new RLock[0]));
        try {
            locked = multiLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (locked) {
                QueryWrapper<ProductEntity> productMainWrapper = new QueryWrapper<>();
                productMainWrapper.in("id", productIdList);
                productMainWrapper.eq("status", ProductStatusEnum.NORMAL.getCode());
                List<ProductEntity> produceMainInfoList = productMainService.list(productMainWrapper);
                if (ObjectUtil.isEmpty(produceMainInfoList)) {
                    throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
                }
                List<String> productCodeList = produceMainInfoList.stream().map(ProductEntity::getProductCode).toList();
                QueryWrapper<ProductDetailsEntity> productDetailsWrapper = new QueryWrapper<>();
                productDetailsWrapper.in("product_code", productCodeList);
                List<ProductDetailsEntity> productDetailEntities = productDetailsService.list(productDetailsWrapper);
                if (ObjectUtil.isEmpty(productDetailEntities)) {
                    throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
                }
                QueryWrapper<ProductVariantsEntity> productVariantsWrapper = new QueryWrapper<>();
                productVariantsWrapper.in("product_id", productIdList);
                List<ProductVariantsEntity> productVariantEntities = productVariantsServiceImpl.list(productVariantsWrapper);
                if (ObjectUtil.isEmpty(productVariantEntities)) {
                    throw new RuntimeException(ResponseEnum.PRODUCT_VARIANTS_NOT_EXIST.getMessage());
                }
                boolean hasZeroQuantity = productDetailEntities.stream()
                        .map(ProductDetailsEntity::getProductQuantity)
                        .anyMatch(quantity -> quantity == 0);
                if (hasZeroQuantity) {
                    throw new RuntimeException(ResponseEnum.PRODUCT_NOT_EXIST.getMessage());
                }
                //处理数据
                List<OrderItemsEntity> insertOrderItemList = new ArrayList<>();
                ///订单编号 + userName + 下单时间戳
                String orderNumber = String.valueOf(userId) + System.currentTimeMillis();
                OrderMainEntity orderMainEntity = new OrderMainEntity();
                orderMainEntity.setUserId(userId);
                orderMainEntity.setOrderNumber(orderNumber);
                orderMainEntity.setShippingAddressId(shippingAddressId);
                orderMainEntity.setStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
                orderMainEntity.setPaymentStatus(OrderStatusEnum.PENDING_PAYMENT.getCode());
                orderMainEntity.setShippingStatus(OrderStatusEnum.PENDING_SHIPMENT.getCode());
                orderMainEntity.setStatus(StatusEnum.NEW.getCode());
                orderMainEntity.setTotalAmount(subtotal);
                orderMainMapper.insertOrderMain(orderMainEntity);
                Long orderId = orderMainEntity.getId();
                for (OrderAddInfoDTO item : orderAddInfoDTO) {
                    String itemId = UUID.randomUUID().toString();
                    OrderItemsEntity orderItem = OrderMapperStruct.INSTANCE.orderAddDTOToOrderInfo(item);
                    orderItem.setId(itemId);
                    orderItem.setOrderId(orderId);
                    Optional<String> productName = produceMainInfoList.stream()
                            .filter(mainInfo -> mainInfo.getId().equals(item.getProductId()))
                            .map(ProductEntity::getProductName)
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
                    //给商品添加乐观锁
                    List<ProductDetailsEntity> filteredProducts = productDetailEntities.stream()
                            .filter(product -> product.getProductCode().equals(item.getProductCode()))
                            .toList();
                    if (CollectionUtils.isNotEmpty(filteredProducts)) {
                        ProductDetailsEntity productCode = filteredProducts.get(0);
                        int rows = productDetailsService.updateQuantity(item.getProductId(),
                                item.getQuantity(), productCode.getVersion());
                        if (rows == 0) {
                            throw new RuntimeException(ResponseEnum.PRODUCT_INSUFFICIENT_INVENTORY.getMessage());
                        }
                    }

                    List<ProductVariantsEntity> list = productVariantEntities.stream()
                            .filter(product -> product.getProductId().equals(item.getProductId()))
                            .toList();
                    if (CollectionUtils.isNotEmpty(list)) {
                        orderItem.setSku(list.get(0).getSku());
                    }
                }
                List<Long> listId = new ArrayList<>();
                //删除购物车中的货物信息
                QueryWrapper<ShoppingCartEntity> shoppingCartQueryWrapper = new QueryWrapper<>();
                shoppingCartQueryWrapper.eq("user_id", userId);
                List<ShoppingCartEntity> shoppingCartEntities = shoppingCartMapper.selectList(shoppingCartQueryWrapper);
                if (CollectionUtils.isNotEmpty(shoppingCartEntities)) {
                    listId.addAll(shoppingCartEntities.stream().map(ShoppingCartEntity::getId).toList());
                }
                if (!insertOrderItemList.isEmpty()) {
                    orderItemsService.saveBatch(insertOrderItemList);
                }
                //删除购物车中的货物信息
                if (!ObjectUtil.isEmpty(userId)) {
                    QueryWrapper<ShoppingCartEntity> shoppingCartWrapper = new QueryWrapper<>();
                    shoppingCartWrapper.eq("user_id", userId);
                    shoppingCartMapper.delete(shoppingCartWrapper);
                }

                if (!listId.isEmpty()) {
                    QueryWrapper<ShoppingCartItemEntity> cartItemQueryWrapper = new QueryWrapper<>();
                    cartItemQueryWrapper.in("cart_id", listId);
                    shoppingCartItemMapper.delete(cartItemQueryWrapper);
                }
            } else {
                throw new RuntimeException("商品正被抢购中，请稍后再试");
            }
        } finally {
            if (locked) {
                multiLock.unlock();
            }
        }
    }

    /**
     * 订单支付
     *
     * @param orderPayInfoDTO orderPayInfoDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductAddInfoDTO> }
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderPay(OrderPayInfoDTO orderPayInfoDTO) {
        Long orderId = orderPayInfoDTO.getOrderId();
        QueryWrapper<OrderMainEntity> orderMainWrapper = new QueryWrapper<>();
        orderMainWrapper.eq("id", orderId);
        OrderMainEntity orderMainEntityInfo = this.getOne(orderMainWrapper);
        if (ObjectUtil.isEmpty(orderMainEntityInfo)) {
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getMessage());
        }
        //只有状态是代付款的订单才可以进行付款支付
        if (ObjectUtil.equals(orderMainEntityInfo.getStatus(), OrderStatusEnum.PENDING_PAYMENT.getCode())) {
            //TODO 异步发走 调用模拟支付 发送消息队列发走，是失败还是成功，后续三方支付接口会给返回结果
            Boolean pay = simulatedPayService.pay(orderId);
            //更新订单状态
            QueryWrapper<OrderMainEntity> orderMainStatusWrapper = new QueryWrapper<>();
            orderMainStatusWrapper.eq("id", orderId);
            orderMainStatusWrapper.eq("status", OrderStatusEnum.PAID.getCode());
            orderMainMapper.update(orderMainStatusWrapper);
        }
    }

    /**
     * 订单信息更新(支付三方回调接口)
     *
     * @param orderReDTO orderReDTO
     * @author sunny
     * @since 2025/05/25@return@return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void orderUpdate(OrderReDTO orderReDTO) {
        QueryWrapper<OrderMainEntity> orderMainWrapper = new QueryWrapper<>();
        orderMainWrapper.eq("id", orderReDTO.getOrderId());
        OrderMainEntity orderMainEntityInfo = this.getOne(orderMainWrapper);
        if (ObjectUtil.isEmpty(orderMainEntityInfo)) {
            throw new RuntimeException(ResponseEnum.ORDER_NOT_EXIST.getMessage());
        }
        if (ObjectUtil.equals(orderMainEntityInfo.getStatus(), OrderStatusEnum.PAID.getCode())) {
            //更新订单状态
            QueryWrapper<OrderMainEntity> orderMainStatusWrapper = new QueryWrapper<>();
            orderMainStatusWrapper.eq("id", orderReDTO.getOrderId());
            orderMainStatusWrapper.eq("status", OrderStatusEnum.PAID.getCode());
            orderMainMapper.update(orderMainStatusWrapper);
        }
    }

    /**
     * 删除订单
     *
     * @param id id
     * @author sunny
     * @since 2025/06/02
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrderById(String id) {
        QueryWrapper<OrderItemsEntity> orderItemsWrapper = new QueryWrapper<>();
        orderItemsWrapper.eq("order_id", id);
        List<OrderItemsEntity> list = orderItemsService.list(orderItemsWrapper);
        if (CollUtil.isNotEmpty(list)) {
            List<String> idList = list.stream().map(OrderItemsEntity::getId).toList();
            orderItemsService.removeByIds(idList);
        }
        this.removeById(id);
    }
}
