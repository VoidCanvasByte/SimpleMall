package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.OrderService;
import com.example.simple.mall.common.dto.OrderDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param orderDTO orderDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    @PostMapping("/add")
    public ResponseResult<OrderDTO> addOrder(@RequestBody OrderDTO orderDTO) {
        return null;
    }

    /**
     * 更新订单
     *
     * @param orderDTO orderDTO
     * @author sunny
     * @since 2025/05/09@return @return {@code ResponseResult<ProductDTO> }
     */
    @PostMapping("/update")
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
    public ResponseResult<OrderDTO> deleteOrder(@PathVariable Long id) {
        orderService.removeById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}