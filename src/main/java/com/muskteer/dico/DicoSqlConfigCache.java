package com.muskteer.dico;

import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@Component
public class DicoSqlConfigCache implements ExecuteSqlCache {

    private Cache<String, Object> cache = CacheBuilder.newBuilder().build();;

    @Autowired
    private DicoFileConfigCache dicoFileConfigCache;


    public Object getSource(final String file, final String key) {
        try {
            return cache.get(key, new Callable<Object>() {

                @Override
                public Object call() {
                    String sql = null;
                    try {
                        String contents = (String) dicoFileConfigCache.getSource(file);
                        if (contents == null) {
                            contents = Resources.toString(DicoClassLoader.getClassLoader().getResource(key),
                                    Charsets.UTF_8);
                        }
                        sql = TextFunction.excute(contents, key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return sql;
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
