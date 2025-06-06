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
public class ProductCategoryEntity extends BaseEntity {

    /**
     * 分类名称
     */
    @TableField("name")
    public String name;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 父级分类，顶级为0
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序值，越小越靠前
     */
    @TableField("sort_order")
    public Integer sortOrder;

    /**
     * 分类描述
     */
    @TableField("remark")
    public String remark;

}