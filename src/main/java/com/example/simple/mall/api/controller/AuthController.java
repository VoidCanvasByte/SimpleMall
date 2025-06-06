package com.example.simple.mall.api.controller;

import com.example.simple.mall.common.dto.R;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.Key;


/**
 * 用户验证token
 *
 * @author sunny
 * @since 2025/06/06
 */
@RestController
@RequestMapping("/apply/auth")
@Tag(name = "用户验证控制器", description = "用户验证控制器")
public class AuthController {

    /**
     * checkToken
     *
     * @param request request
     * @author sunny
     * @since 2025/06/06@return @return {@code R<String> }
     */
    @GetMapping("/check-token")
    public R<String> checkToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return R.error("缺少 token 或格式错误");
        }
        String token = authHeader.substring(7);
        try {
            String userId = JwtUtil.parseToken(token);
            return R.ok("有效 token，用户ID：" + userId);
        } catch (ExpiredJwtException e) {
            return R.error("token 已过期");
        } catch (JwtException e) {
            return R.error("token 非法：" + e.getMessage());
        } catch (Exception e) {
            return R.error("token 校验失败：" + e.getMessage());
        }
    }


    /**
     * JwtUtil
     *
     * @author sunny
     * @since 2025/06/06
     */
    public static class JwtUtil {
        private static final String EXPIRE = "your-256-bit-secret-your-256-bit-secret";
        private static final Key KEY = Keys.hmacShaKeyFor(EXPIRE.getBytes(StandardCharsets.UTF_8));

        /**
         * parseToken
         *
         * @param token token
         * @return @return {@code String }
         * @author sunny
         * @since 2025/06/06
         */
        public static String parseToken(String token) {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);

            return jws.getBody().getSubject();
        }
    }
}