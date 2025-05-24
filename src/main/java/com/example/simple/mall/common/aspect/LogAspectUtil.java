package com.example.simple.mall.common.aspect;


import cn.hutool.core.date.StopWatch;
import com.example.simple.mall.common.utils.HttpServletUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 切面日志记录
 *
 * @author sunny
 * @since 2025/05/10
 */
@Slf4j
@Aspect
@Component
public class LogAspectUtil {

    /**
     * 在接口的前后都切入日志的记录
     *
     * @param joinPoint joinPoint
     * @return @return {@code Object }
     * @author sunny
     * @since 2025/05/10
     */
    @Around("execution(* com.example.simple.mall.api.controller..*.*(..))")
    public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = HttpServletUtil.getRequest();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        List<Object> paramList = Stream.of(joinPoint.getArgs())
                .filter(args -> !(args instanceof ServletRequest))
                .filter(args -> !(args instanceof ServletResponse))
                .collect(Collectors.toList());
        log.info("-------------method：[{}]，url：[{}]，params：[{}]-------------", method, uri, paramList);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();
        long cost = stopWatch.getTotalTimeMillis();
        log.info("-------------url：[{}]，response：[{}]，time：[{}ms]-------------", uri, result, cost);
        return result;
    }
}
