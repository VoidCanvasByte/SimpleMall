package com.example.simple.mall.common.dto.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户信息返回DTO
 *
 * @author sunny
 * @since 2025/05/31
 */
@Data
public class UserReturnInfoDTO {

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户邮箱（唯一主键，一个邮箱只能申请一个用户）
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 性别：1男，2女，0未知
     */
    private Integer userGender;

    /**
     * 用户状态：0正常，1封禁
     */
    private Integer status;

    /**
     * 用户地址信息
     */
    private List<UserAddressesReturnDTO> userAddressesReturnDTOList = new ArrayList<>();

    @Data
    public static class UserAddressesReturnDTO {
        /**
         * 主表ID
         */
        public Long id;

        /**
         * 用户ID
         */
        public Long userId;

        /**
         * 收件人姓名
         */
        public String receiverName;

        /**
         * 收件人手机号
         */
        public String phone;

        /**
         * 都道府県
         */
        public String prefecture;

        /**
         * 市区町村
         */
        public String city;

        /**
         * 町名
         */
        public String town;

        /**
         * 详细地址
         */
        public String streetAddress;

        /**
         * 邮编
         */
        public String postalCode;

        /**
         * 是否默认地址（1:是；2:不是）
         */
        public Integer isDefault;
    }
}