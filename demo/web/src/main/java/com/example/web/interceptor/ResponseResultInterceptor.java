package com.example.web.interceptor;

import com.example.core.utils.result.Result;
import com.example.web.annotation.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author JIA
 */

@Slf4j
@ControllerAdvice
@Configuration
public class ResponseResultInterceptor implements ResponseBodyAdvice<Object> {

    /**
     * 标记名称
     */
    public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(servletRequestAttributes).getRequest();
        //判断请求是否有包装标记
        ResponseResult responseResult = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info(" 进入 返回体 重写格式 处理中。。。。。。。。。。");
        if (body instanceof String) {
            return String.valueOf(body);
        }
        if (body instanceof Result) {
            Result result = (Result) body;
            log.info("这里是自定义异常的处理 " + result.getMsg());
            result.setData(null);
            return Result.exception(result.getCode(), result.getMsg());
        }
        return Result.success(body);
    }
}
