package com.example.simple.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * user_addresses
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
@TableName("user_addresses")
public class UserAddressesEntity {

    /**
     * 主表ID
     */
    public Long id;

    /**
     * 用户ID
     */
    @TableField("user_id")
    public Long userId;

    /**
     * 收件人姓名
     */
    @TableField("receiver_name")
    public String receiverName;

    /**
     * 收件人手机号
     */
    @TableField("phone")
    public String phone;

    /**
     * 都道府県
     */
    @TableField("prefecture")
    public String prefecture;

    /**
     * 市区町村
     */
    @TableField("city")
    public String city;

    /**
     * 町名
     */
    @TableField("town")
    public String town;

    /**
     * 详细地址
     */
    @TableField("street_address")
    public String streetAddress;

    /**
     * 邮编
     */
    @TableField("postal_code")
    public String postalCode;

    /**
     * 是否默认地址（1:是；2:不是）
     */
    @TableField("is_default")
    public Integer isDefault;
}