package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;

/**
 * 用户基本信息
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
@TableName("user")
public class User implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户邮箱（唯一主键，一个邮箱只能申请一个用户）
     */
    @TableField("email")
    private String email;

    /**
     * 用户密码
     */
    @TableField("password")
    private String password;

    /**
     * 性别：1男，2女，0未知
     */
    @TableField("user_gender")
    private Integer userGender;
}