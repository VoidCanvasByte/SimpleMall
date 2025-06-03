package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

/**
 * 收藏夹表
 *
 * @author sunny
 * @since 2025/06/03
 */
@Data
@TableName("product_favorites")
public class ProductFavoritesEntity{

    /**
     * id
     */
    @TableField("id")
    public Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    public Long userId;

    /**
     * 添加时间
     */
    @TableField("create_time")
    public Date createTime;

}
