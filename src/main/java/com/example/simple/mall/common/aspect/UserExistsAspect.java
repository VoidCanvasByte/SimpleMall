package com.example.simple.mall.common.aspect;


import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.enu.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 当前用户是否存在
 *
 * @author sunny
 * @since 2025/05/24
 */
@Slf4j
@Aspect
@Component
public class UserExistsAspect {

    private final UserService userService;

    public UserExistsAspect(UserService userService) {
        this.userService = userService;
    }

    @Around("@annotation(com.example.simple.mall.common.annotation.UserVerification)")
    public Object checkUserExists(ProceedingJoinPoint joinPoint) throws Throwable {

        String currentUserId = null;

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if ("userId".equals(arg)) {
                currentUserId = (String) arg;
                break;
            }
        }

        if (userService.judgeUserIfNull(currentUserId)) {
            throw new RuntimeException(ResponseEnum.USER_NOT_EXIST.getMessage());
        }

        return joinPoint.proceed();
    }
}
