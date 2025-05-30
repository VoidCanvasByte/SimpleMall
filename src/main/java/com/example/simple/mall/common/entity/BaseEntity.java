package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 基础实体
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
public class BaseEntity {

    /**
     * 用户ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;
}
