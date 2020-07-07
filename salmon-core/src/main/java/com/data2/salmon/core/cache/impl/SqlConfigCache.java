package com.data2.salmon.core.cache.impl;

import com.data2.salmon.core.ConfigurationLoader;
import com.data2.salmon.core.TextFunction;
import com.data2.salmon.core.cache.ExecuteSqlCache;
import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author leewow
 */
@Component
@Slf4j
public class SqlConfigCache implements ExecuteSqlCache {

    private Cache<String, Object> cache = CacheBuilder.newBuilder().build();

    @Autowired
    private ConfigCache configCache;

    @Override
    public Object getSource(final String file, final String key) {
        try {
            return cache.get(key, () -> {
                String sql = null;
                try {
                    String contents = (String) configCache.getSource(file);
                    if (contents == null) {
                        contents = Resources.toString(ConfigurationLoader.getClassLoader().getResource(key),
                                Charsets.UTF_8);
                    }
                    sql = TextFunction.excute(contents, key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return sql;
            });
        } catch (ExecutionException e) {
            log.error("exception :{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void remove(String obj) {
        if (cache != null) {
            cache.invalidate(obj);
        }

    }

    @Override
    public void removeAll() {
        if (cache != null) {
            cache.invalidateAll();
            ;
        }
    }

}
