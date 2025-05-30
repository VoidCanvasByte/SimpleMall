package com.example.simple.mall.common.aspect;


import com.example.simple.mall.api.service.UserService;
import com.example.simple.mall.common.dto.user.UserBaseDTO;
import com.example.simple.mall.common.enu.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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
        Object[] args = joinPoint.getArgs();
        Object argInfo = args[0];
        UserBaseDTO userAspectDTO = new UserBaseDTO();
        if (argInfo instanceof List) {
            Collection<?> collection = (Collection<?>) argInfo;
            for (Object item : collection) {
                BeanUtils.copyProperties(item, userAspectDTO);
            }
        } else if (argInfo instanceof UserBaseDTO) {
            BeanUtils.copyProperties(argInfo, userAspectDTO);
        }
        if (userService.judgeUserIfNull(userAspectDTO.getUserId())) {
            throw new RuntimeException(ResponseEnum.USER_NOT_EXIST.getMessage());
        }
        return joinPoint.proceed();
    }
}
