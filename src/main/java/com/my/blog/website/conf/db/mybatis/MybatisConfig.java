package com.my.blog.website.conf.db.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

public class MybatisConfig {


    /**
     * Mybatis配置
     *
     * @author liuchuan
     * @date 2021/11/26 3:58 下午
     * @param sqlSessionFactory
     * @return org.mybatis.spring.SqlSessionTemplate
     */
    @Bean("mainSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("mainSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    /**
     * Mybatis配置
     *
     * @author liuchuan
     * @date 2021/11/26 3:58 下午
     * @param dataSource
     * @return org.mybatis.spring.SqlSessionFactory
     */
    @Bean("mainSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("mainDbSource") DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
