package com.data2.salmon.core.engine.cache;

import com.google.common.cache.CacheBuilder;

/**
 * @author data2
 * @description
 * @date 2021/4/14 下午3:52
 */
public abstract class AbstractCache implements Cache {
    protected com.google.common.cache.Cache<String, String> cache = CacheBuilder.newBuilder().build();
}
