package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.dto.product.ProductInfoDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 商品分类控制层
 *
 * @author sunny
 * @since 2025/05/09
 */
@RestController
@RequestMapping("/apply/product/category")
@Tag(name = "ProductCategoryController", description = "商品分类控制层")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 添加商品分类
     *
     * @param productInfoDTO productInfoDTO
     * @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @PostMapping("/add")
    @Operation(summary = "添加商品分类", description = "添加商品分类")
    public ResponseResult<ProductDTO> addCategory(@Valid @RequestBody ProductInfoDTO productInfoDTO) {
        productCategoryService.addCategory(productInfoDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 更新商品分类
     *
     * @param productUpdateDTO productDTO
     * @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @PostMapping("/update")
    @Operation(summary = "更新商品分类", description = "更新商品分类")
    public ResponseResult<ProductDTO> updateCategory(@Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        productCategoryService.updateCategory(productUpdateDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除分类（管理员）
     *
     * @param id id
     * @return @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @DeleteMapping("/delete/{id}")
    public ResponseResult<ProductDTO> deleteCategory(@PathVariable String id) {
        productCategoryService.removeById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}