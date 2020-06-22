package com.example.web.config;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * @author 10696
 */

@Configuration
public class MyBatisConfig {

    private final static String TYPE_ALIASES_PACKAGE = "com.example.business.entity";

    private final static String MAPPER_PACKAGE = "com.example.business.mapper";

    private final static String COMMON_MAPPER = "com.example.core.utils.tk.mapper.Mapper";


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //mapper *.xml文件路径
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        //model 实体类文件路径
        sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        //驼峰式命名
        org.apache.ibatis.session.Configuration configuration =
                Objects.requireNonNull(sqlSessionFactoryBean.getObject()).getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        //sql 日志
        configuration.setLogImpl(StdOutImpl.class);
        //主键
        configuration.setUseGeneratedKeys(true);
        return sqlSessionFactoryBean.getObject();

    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //Mapper文件包
        mapperScannerConfigurer.setBasePackage(MAPPER_PACKAGE);
        //通用Mapper的配置
        Properties properties = new Properties();
        properties.setProperty("mappers", COMMON_MAPPER);
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;

    }
}
