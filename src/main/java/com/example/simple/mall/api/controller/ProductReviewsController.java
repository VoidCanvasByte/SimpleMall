package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductReviewsService;
import com.example.simple.mall.common.annotation.UserVerification;
import com.example.simple.mall.common.dto.product.ProductReviewsInfoDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsReDTO;
import com.example.simple.mall.common.dto.product.ProductReviewsUpdateDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品评价控制器
 *
 * @author sunny
 * @since 2025-06-03
 */
@RestController
@RequestMapping("/apply/product/reviews")
@Tag(name = "ProductReviewsControllerAPI", description = "商品评价控制器")
public class ProductReviewsController {

    @Autowired
    public ProductReviewsService productReviewsService;


    /**
     * 添加商品评论
     *
     * @param productReviewsInfoDTO productReviewsInfoDTO
     * @author sunny
     * @since 2025/05/08@return @return {@code ResponseResult<ProductAddInfoDTO> }
     */
    @UserVerification
    @PostMapping(value = "/add")
    @Operation(summary = "添加商品评论", description = "添加商品评论")
    public ResponseResult<ProductReviewsReDTO> addProductReviews(@Validated @RequestBody ProductReviewsInfoDTO productReviewsInfoDTO) {
        productReviewsService.addProductReviews(productReviewsInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 更新商品评论
     *
     * @param productReviewsUpdateDTO productReviewsUpdateDTO
     * @author sunny
     * @since 2025/05/08@return @return {@code ResponseResult<ProductAddInfoDTO> }@return @return {@code ResponseResult<ProductReviewsReDTO> }
     */
    @UserVerification
    @PostMapping(value = "/update")
    @Operation(summary = "更新商品评论", description = "更新商品评论")
    public ResponseResult<ProductReviewsReDTO> updateProductReviews(@Validated @RequestBody ProductReviewsUpdateDTO productReviewsUpdateDTO) {
        productReviewsService.updateProductReviews(productReviewsUpdateDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }


    /**
     * 删除商品评价
     *
     * @param productReviewsId 商品评价表ID
     * @return @return {@code ResponseResult<ProductReviewsReDTO> }
     * @author sunny
     * @since 2025/06/03
     */
    @UserVerification
    @PostMapping(value = "/delete")
    @Operation(summary = "删除商品评论", description = "删除商品评论")
    public ResponseResult<ProductReviewsReDTO> deleteProductReviews(@RequestBody Long productReviewsId) {
        productReviewsService.deleteProductReviews(productReviewsId);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

}
