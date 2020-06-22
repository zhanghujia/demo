package com.example.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JIA
 * 阿里云 连接池 配置
 */

@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource druid() {
       return new DruidDataSource();
    }

    /**
     * 配置一个druid 的监控
     * 1. 配置一个druid 的后台管理servlet
     * 2. 配置一个druid 的filter
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        // 注意: 请求 /druid/*
        ServletRegistrationBean<StatViewServlet> bean =
                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParam = new HashMap<>(16);
        initParam.put(StatViewServlet.PARAM_NAME_USERNAME, "root");
        initParam.put(StatViewServlet.PARAM_NAME_PASSWORD, "hcinfos");

        //如果不写,默认所有ip都可以访问
        initParam.put(StatViewServlet.PARAM_NAME_ALLOW, "");
        initParam.put(StatViewServlet.PARAM_NAME_DENY, "192.168.1.1");
        bean.setInitParameters(initParam);

        return bean;
    }

    /**
     * 配置一个druid 的 filter
     *
     * @return
     */
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParam = new HashMap<>(16);
        bean.setInitParameters(initParam);
        initParam.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
        //设置拦截请求
        bean.setUrlPatterns(Collections.singletonList("*"));
        return bean;
    }


}
