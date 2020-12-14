package com.example.web.config.webmvc;

import com.example.web.filter.space.SpaceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;

/**
 * @author 10696
 */
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean spaceFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new SpaceFilter());
        filter.addUrlPatterns("/*");
        filter.setName("SpaceFilter");
        filter.setDispatcherTypes(DispatcherType.REQUEST);
        return filter;
    }
}
