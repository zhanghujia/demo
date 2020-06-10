package com.example.web.config;

import com.example.web.interceptor.ResultInterceptor;
import com.example.web.myconfig.MyFilter;
import com.example.web.myconfig.MyListener;
import com.example.web.myconfig.MyServlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.EventListener;

/**
 * @author JIA
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//        //定制Servlet嵌入式容器规则
//        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableWebServerFactory factory) {
//                factory.setPort(8081);
//            }
//        };
//    }
//
//
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        // 自定义Servlet
//        ServletRegistrationBean<Servlet> servletServletRegistrationBean =
//                new ServletRegistrationBean<>(new MyServlet(), "/myServlet");
//        return servletServletRegistrationBean;
//
//    }
//
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        // 自定义过滤器
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(new MyFilter());
//        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/myServlet"));
//        return filterFilterRegistrationBean;
//    }
//
//
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//        // 自定义监听器
//        ServletListenerRegistrationBean<EventListener> eventListenerServletListenerRegistrationBean
//                = new ServletListenerRegistrationBean<>(new MyListener());
//        return eventListenerServletListenerRegistrationBean;
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(resultInterceptor())
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html", "/api/druid/*")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public ResultInterceptor resultInterceptor() {
        return new ResultInterceptor();
    }


}
