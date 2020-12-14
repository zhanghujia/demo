package com.example.web.interceptor;

import com.example.web.annotation.ResponseResult;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author JIA
 */
public class ResultInterceptor implements HandlerInterceptor {

    /**
     * 标记名称
     */
    public static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //请求的方法
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            //判断是否在类对象上面加了注解
            if (clazz.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体 需要包装,往下传递 在 ResponseBodyAdvise接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN, clazz.getAnnotation(ResponseResult.class));
            } else if (method.isAnnotationPresent(ResponseResult.class)) {
                //设置此请求返回体 需要包装,往下传递 在 ResponseBodyAdvise接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN, method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }


}
