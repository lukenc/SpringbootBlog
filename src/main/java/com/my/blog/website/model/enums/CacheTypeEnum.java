package com.my.blog.website.model.enums;

public enum CacheTypeEnum {
    ARTICLE("article:detail", 60 * 60 * 24 * 7),
    OPTION("option",60 * 60 * 24 * 7),
    COMMENT("comment",60 * 60 * 24 * 7),
    ARTICLE_LIST("article:list", 60 * 60 * 12);
    /**
     * 缓存名称
     */
    private String name;
    /**
     * 过期时间
     */
    private  int expires;

    /**
     * 构造
     */
     CacheTypeEnum(String name, int expires) {
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public int getExpires() {
        return expires;
    }

}
