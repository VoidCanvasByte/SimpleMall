package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.common.annotation.UserVerification;
import com.example.simple.mall.common.dto.OrderAddDTO;
import com.example.simple.mall.common.dto.OrderDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
     * 添加订单
     *
     * @param orderAddDTO orderAddDTO
     * @return @return {@code ResponseResult<OrderDTO> }
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    @PostMapping("/add")
    @UserVerification
    @Operation(summary = "添加订单", description = "添加订单")
    public ResponseResult<OrderDTO> addOrder(@Valid @RequestBody OrderAddDTO orderAddDTO) {
        orderService.addOrder(orderAddDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 更新订单
     *
     * @param orderDTO orderDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    @PostMapping("/update")
    @Operation(summary = "更新订单", description = "更新订单")
    public ResponseResult<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO) {
        return null;
    }

    /**
     * 删除订单信息
     *
     * @param id id
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }@return @return {@code ResponseResult<OrderDTO> }
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除订单信息", description = "删除订单信息")
    public ResponseResult<OrderDTO> deleteOrder(@PathVariable Long id) {
        orderService.removeById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}