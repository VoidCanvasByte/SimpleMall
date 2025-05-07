package com.example.simple.mall.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.simple.mall.api.service.ProductMainService;
import com.example.simple.mall.common.dto.ProductDTO;
import com.example.simple.mall.common.entity.ProductMain;
import com.example.simple.mall.common.enu.ResponseEnum;
import com.example.simple.mall.common.response.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理层
 *
 * @author sunny
 * @since 2025/05/08
 */
@RestController
@RequestMapping("/apply/product")
@Tag(name = "ProductControllerAPI", description = "商品管理层控制层")
public class ProductController {

    @Autowired
    private ProductMainService productMainService;

    /**
     * 分页查询
     *
     * @param page        page
     * @param size        size
     * @param productMain productMain
     * @author sunny
     * @since 2025/05/08@return @return {@code PageResult<ProductDTO> }@return @return {@code Page<ProductMain> }
     */

    @Operation(summary = "商品列表-分页", description = "商品列表-分页")
    @GetMapping(value = "/queryPageList")
    public Page<ProductMain> queryPageList(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue = "10") Integer size,
                                           @RequestBody ProductMain productMain) {
        return productMainService.queryPageList(page, size, productMain);

    }


    /**
     * 添加商品
     *
     * @param productDTO productDTO
     * @return @return {@code ResponseResult<ProductDTO> }
     * @author sunny
     * @since 2025/05/08
     */
    @PostMapping
    public ResponseResult<ProductDTO> addProduct(@Validated
                                                 @RequestBody ProductDTO productDTO) {
        productMainService.addProduct(productDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 商品信息更新
     *
     * @param productDTO productDTO
     * @author sunny
     * @since 2025/05/08@return @return {@code ResponseResult<ProductDTO> }
     */
    @PutMapping
    public ResponseResult<ProductDTO> updateProduct(@Validated @RequestBody ProductDTO productDTO) {
        productMainService.updateProduct(productDTO);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }

    /**
     * 删除商品信息
     *
     * @param productCode 商品货号
     * @author sunny
     * @since 2025/05/08@return @return {@code ResponseResult<ProductDTO> }
     */
    @DeleteMapping("/{productCode}")
    public ResponseResult<ProductDTO> deleteProduct(@PathVariable String productCode) {
        productMainService.deleteProduct(productCode);
        return ResponseResult.out(ResponseEnum.SUCCESS);
    }


}
