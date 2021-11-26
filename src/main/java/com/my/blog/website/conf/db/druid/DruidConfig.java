package com.my.blog.website.conf.db.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean("mainDbSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourceConfig.getUrl());
        dataSource.setUsername(dataSourceConfig.getUsername());
        dataSource.setPassword(dataSourceConfig.getPassword());
        dataSource.setMaxActive(dataSourceConfig.getMaxActive());
        dataSource.setMinIdle(dataSourceConfig.getMinIdle());
        dataSource.setInitialSize(dataSourceConfig.getInitialSize());
        dataSource.setKeepAlive(true);
        dataSource.setDriver(dataSource.getDriver());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("mainDbSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
