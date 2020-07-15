package com.example.web.aspect;


import com.example.core.exception.ResponseException;
import com.example.core.utils.redis.RedisUtil;
import com.example.core.utils.tool.Verification;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class FrontUserAspect {

    private final RedisUtil redisUtil;

    @Autowired
    public FrontUserAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut(" execution (public * com.example.web.controller..*.*(..))"
    )

    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        //获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        //从请求头中获取token
        String token = request.getHeader("token");
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("URI: {}, Method: {}", uri, method);

        if (!Objects.isNull(token)) {

            //获取redis中的token 查看token是否过期
            if (Verification.verificationString((String) redisUtil.get(token))) {
                log.info("Front 请求-token正常");
            } else {
                log.info("Front 请求-redis中的token已过期，请重新登录");
                throw new ResponseException(-1,"front-token the signature has expired, please log in again");
            }
        } else {
            log.info("Front 请求-token为空");
            throw new ResponseException(-1,"front request-token is null");
        }
    }
    
}
