package com.data2.salmon;

import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Resources;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
public class ConfigCache implements FileConfigCache {

    private Cache<String, Object> cache = CacheBuilder.newBuilder().build();;

    public Object getSource(final String key) {
        try {
            return cache.get(key, new Callable<Object>() {

                @Override
                public Object call()  {
                    try {
                        URL URL = ConfigurationLoader.getClassLoader().getResource(key);
                        String sqlContents = Resources.toString(URL, Charsets.UTF_8);
                        return sqlContents;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
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
