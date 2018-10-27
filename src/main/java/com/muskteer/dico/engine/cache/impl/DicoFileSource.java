package com.muskteer.dico.engine.cache.impl;

import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Resources;
import com.muskteer.dico.engine.cache.FileSource;
import com.muskteer.dico.util.DicoClassLoader;

public class DicoFileSource implements FileSource {

    private static Cache<String, Object> cache;

    public Object getSource(final String key) {
        try {
            return cache.get(key, new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    try {
                        URL URL = DicoClassLoader.getClassLoader().getResource(key);
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

    static {
        cache = CacheBuilder.newBuilder().build();
    }

}
