package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("creat_time")
    private String creatTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private String updateTime;
}
