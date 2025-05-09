package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类控制层
 *
 * @author sunny
 * @since 2025/05/09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("product_category")
public class ProductCategory extends BaseEntity {

    /**
     * 分类名称
     */
    @TableField("name")
    public String name;

    /**
     * 排序值，越小越靠前
     */
    @TableField("sort")
    public String sort;

}