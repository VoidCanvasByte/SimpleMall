package com.example.simple.mall.common.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码校验类
 *
 * @author sunny
 * @since 2025/05/05
 */
public class PasswordRelatedUtil {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 密码加密
     *
     * @param password 加密前密码
     * @author sunny
     * @since 2025/05/05@return @return {@code String }
     */
    public static String enCode(String password) {
        return ENCODER.encode(password);
    }

    /**
     * 密码校验
     *
     * @param rawPassword    密码
     * @param hashedPassword hashedPassword
     * @return @return {@code String }
     * @author sunny
     * @since 2025/05/05
     */
    public static Boolean matches(String rawPassword, String hashedPassword) {
        return ENCODER.matches(rawPassword, hashedPassword);
    }
}
