package com.example.web.aspect;

import com.example.core.exception.ResponseException;
import com.example.core.utils.redis.RedisUtil;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author 10696
 * @since 2020/11/23 14:24
 */

//@Aspect
@Component
public class TimeAspect {

    private static final long EXPIRE_TIME_OUT = 15 * 1000 * 60;

    private static final String REDIS_NONCE_KEY = "nonce:key-";

    private final RedisUtil redisUtil;

    public TimeAspect(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut("execution (public * com.example.web.controller.*.*.*.*(..))")

    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {

        //获取请求信息
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");

        boolean nNull = Objects.isNull(nonce);
        boolean tNull = Objects.isNull(timestamp);

        if (nNull && tNull) {
            throw new ResponseException(-1, "参数丢失");
        }

        long now = System.currentTimeMillis();
        long timeout = Long.parseLong(timestamp);

        if ((now - timeout) > EXPIRE_TIME_OUT) {
            throw new ResponseException(-1, "请求已过期");
        }

        if (redisUtil.hasKey(REDIS_NONCE_KEY)) {
            if (redisUtil.setHasKey(REDIS_NONCE_KEY, nonce)) {
                throw new ResponseException(-1, "表单重复提交失败");
            }
        }

        redisUtil.setSetAndTime(REDIS_NONCE_KEY, 5, nonce);

    }
}
