package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 用户基本信息
 *
 * @author sunny
 * @since 2025/05/05
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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

    /**
     * 用户状态：0正常，1封禁'
     */
    @TableField("status")
    private Integer status;

    /**
     * 用户权限控制（USER 或 ADMIN）
     */
    @TableField("role")
    private String role;
}