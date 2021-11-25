package com.my.blog.website.conf.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.my.blog.website.model.enums.CacheTypeEnum;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {


    @Bean
    @Primary
    public CacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        List<String> cacheNameList = getCacheNameList();

        Caffeine<Object, Object> caffeine = Caffeine.newBuilder()
                .softValues()
                .initialCapacity(100)
                .expireAfterWrite(2, TimeUnit.DAYS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setAllowNullValues(true);
        cacheManager.setCacheNames(cacheNameList);
        return cacheManager;
    }


    private List<String> getCacheNameList() {
        List<String> cacheNameList = new ArrayList<>(CacheTypeEnum.values().length);
        for (CacheTypeEnum cacheTypeEnum : CacheTypeEnum.values()) {
            cacheNameList.add(cacheTypeEnum.getName());
        }
        return cacheNameList;
    }
}
