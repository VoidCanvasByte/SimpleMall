package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.OrderMainLogisticsService;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsInfoDTO;
import com.example.simple.mall.common.dto.order.OrderMainLogisticsReDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单物流信息
 *
 * @author sunny
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/apply/product/order/logistics")
@Tag(name = "OrderMainLogisticsController", description = "订单物流信息表")
public class OrderMainLogisticsController {

    @Autowired
    public OrderMainLogisticsService orderMainLogisticsService;


    /**
     * 添加订单物流信息
     *
     * @param orderMainLogisticsInfoDTO orderMainLogisticsInfoDTO
     * @author sunny
     * @since 2025/05/08@return @return {@code ResponseResult<ProductAddInfoDTO> }
     */
    @PostMapping(value = "/add")
    @Operation(summary = "添加订单物流信息", description = "添加订单物流信息")
    public ResponseResult<OrderMainLogisticsReDTO> addLogistics(@Validated @RequestBody OrderMainLogisticsInfoDTO orderMainLogisticsInfoDTO) {
        orderMainLogisticsService.addLogistics(orderMainLogisticsInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 查询物流订单信息
     *
     * @param id id
     * @return {@code ResponseResult<OrderMainLogisticsReDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @GetMapping(value = "/get/{id}")
    @Operation(summary = "查询订单物流信息", description = "查询订单物流信息")
    public ResponseResult<OrderMainLogisticsReDTO> getLogistics(@PathVariable Long id) {
        OrderMainLogisticsReDTO orderMainLogisticsReDTO = orderMainLogisticsService.getLogistics(id);
        return ResponseResult.out(ResponseEnum.SUCCESS, orderMainLogisticsReDTO);
    }

}