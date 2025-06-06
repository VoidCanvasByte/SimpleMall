package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.common.annotation.UserVerification;
import com.example.simple.mall.common.dto.order.OrderAddInfoDTO;
import com.example.simple.mall.common.dto.order.OrderReDTO;
import com.example.simple.mall.common.dto.order.OrderPayInfoDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.exception.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 *
 * @author sunny
 * @since 2025/05/09
 */
@RestController
@RequestMapping("/apply/order")
@Tag(name = "OrderController", description = "订单控制器")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param orderAddInfoDTO orderAddInfoDTO
     * @author sunny
     * @since 2025/05/09@return@return {@code ResponseResult<OrderReDTO> }
     */
    @UserVerification
    @PostMapping("/add")
    @Operation(summary = "创建订单", description = "创建订单")
    public ResponseResult<OrderReDTO> addOrder(@Valid @RequestBody List<OrderAddInfoDTO> orderAddInfoDTO) throws Exception {
        orderService.addOrder(orderAddInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 订单支付
     *
     * @param orderPayInfoDTO orderPayInfoDTO
     * @return @return {@code ResponseResult<OrderReDTO> }
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductAddInfoDTO> }
     */
    @UserVerification
    @PostMapping("/pay")
    @Operation(summary = "订单支付", description = "订单支付")
    public ResponseResult<OrderReDTO> orderPay(@Valid @RequestBody OrderPayInfoDTO orderPayInfoDTO) {
        orderService.orderPay(orderPayInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 订单信息更新(支付三方回调接口)
     *
     * @param orderReDTO orderReDTO
     * @author sunny
     * @since 2025/05/25@return @return {@code ResponseResult<OrderReDTO> }
     */
    @UserVerification
    @PostMapping("/update")
    @Operation(summary = "订单信息更新(支付三方回调接口)", description = "订单信息更新(支付三方回调接口)")
    public ResponseResult<OrderReDTO> orderUpdate(@Valid @RequestBody OrderReDTO orderReDTO) {
        orderService.orderUpdate(orderReDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除订单信息
     *
     * @param id id
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductAddInfoDTO> }@return @return {@code ResponseResult<OrderReDTO> }
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除订单信息", description = "删除订单信息")
    public ResponseResult<OrderReDTO> deleteOrder(@PathVariable String id) {
        orderService.deleteOrderById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}