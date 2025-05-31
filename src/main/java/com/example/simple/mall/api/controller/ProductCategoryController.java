package com.example.simple.mall.api.controller;

import com.example.simple.mall.api.service.ProductCategoryService;
import com.example.simple.mall.common.annotation.UserVerification;
import com.example.simple.mall.common.dto.product.ProductCategoryInfoDTO;
import com.example.simple.mall.common.dto.product.ProductCategoryReturnDTO;
import com.example.simple.mall.common.dto.product.ProductDTO;
import com.example.simple.mall.common.dto.product.ProductUpdateDTO;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
     * 查询当前用户下全部商品分类列表
     *
     * @param userBaseDTO userBaseDTO
     * @return {@code List<ProductCategoryReturnDTO> }
     * @author sunny
     * @since 2025/05/31@return
     */
    @UserVerification
    @PostMapping("/listAll")
    @Operation(summary = "查询当前用户下全部商品分类列表", description = "查询当前用户下全部商品分类列表")
    public ResponseResult<List<ProductCategoryReturnDTO>> listAll(@RequestBody UserBaseDTO userBaseDTO) {
        List<ProductCategoryReturnDTO> list = productCategoryService.listAll(userBaseDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS, list);
    }

    /**
     * 添加商品分类
     *
     * @param productCategoryInfoDTO productCategoryInfoDTO
     * @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @UserVerification
    @PostMapping("/add")
    @Operation(summary = "添加商品分类", description = "添加商品分类")
    public ResponseResult<ProductDTO> addCategory(@Valid @RequestBody ProductCategoryInfoDTO productCategoryInfoDTO) {
        productCategoryService.addCategory(productCategoryInfoDTO);
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
    @UserVerification
    @PostMapping("/update")
    @Operation(summary = "更新商品分类", description = "更新商品分类")
    public ResponseResult<ProductDTO> updateCategory(@Valid @RequestBody ProductUpdateDTO productUpdateDTO) {
        productCategoryService.updateCategory(productUpdateDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除分类
     *
     * @param id id
     * @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/09
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除分类", description = "删除分类")
    public ResponseResult<ProductDTO> deleteCategory(@PathVariable String id) {
        productCategoryService.removeById(id);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }
}