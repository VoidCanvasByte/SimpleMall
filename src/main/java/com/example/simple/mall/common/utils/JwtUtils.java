package com.example.simple.mall.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

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

    public static final long EXPIRE = 86400000;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * 生成token
     *
     * @param userId userId
     * @return @return {@code String }
     * @author sunny
     * @since 2025/05/15
     */
    public String generateToken(Integer userId) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE);
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(key)
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
                .setSigningKey(key)
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
