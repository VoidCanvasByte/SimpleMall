package com.example.simple.mall.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


/**
 * JwtUtils
 *
 * @author sunny
 * @since 2025/05/15
 */
@Component
public class JwtUtils {

    private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRE = 1000 * 60 * 60 * 24;


    /**
     * 生成token
     *
     * @param userId userId
     * @return @return {@code String }
     * @author sunny
     * @since 2025/05/15
     */
    public static String generateToken(Long userId) {
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRE);
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * 解析token
     *
     * @param token token
     * @return @return {@code Claims }
     * @author sunny
     * @since 2025/05/15
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取用户ID
     *
     * @param token token
     * @return @return {@code Long }
     * @author sunny
     * @since 2025/05/15
     */
    public Long getUserId(String token) {
        return Long.parseLong(getClaimsFromToken(token).getSubject());
    }

    /**
     * 判断 token 是否过期
     *
     * @param token token
     * @return @return boolean
     * @author sunny
     * @since 2025/05/15
     */
    public boolean isTokenExpired(String token) {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }
}
