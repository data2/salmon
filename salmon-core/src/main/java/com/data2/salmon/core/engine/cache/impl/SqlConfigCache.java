package com.data2.salmon.core.engine.cache.impl;

import com.data2.salmon.core.engine.cache.AbstractExecuteSqlCache;
import com.data2.salmon.core.engine.config.ConfigurationLoader;
import com.data2.salmon.core.engine.config.TextFunction;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

/**
 * @author leewow
 */
@Component
@Slf4j
public class SqlConfigCache extends AbstractExecuteSqlCache {

    @Autowired
    private ConfigCache configCache;

    @Override
    public String getSource(String file, String key) {
        try {
            return cache.get(key, () -> {
                String sql = null;
                try {
                    String contents = configCache.getSource(file);
                    if (StringUtils.isEmpty(contents)) {
                        contents = Resources.toString(ConfigurationLoader.getClassLoader().getResource(file),
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
        }
    }

    @Override
    public void destroy() throws Exception {
        removeAll();
    }

}
