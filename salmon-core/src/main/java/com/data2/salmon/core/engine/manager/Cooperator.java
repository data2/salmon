package com.data2.salmon.core.engine.manager;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cooperator {
    private ThreadLocal<List<ExecuteSql>> sqlQueue = new ThreadLocal<>();
    private ThreadLocal<List<Object>> paramQueue = new ThreadLocal<>();

    public void clear() {
        sqlQueue.remove();
        paramQueue.remove();

    }

    public void init() {
        sqlQueue.set(Lists.newArrayList());
        paramQueue.set(Lists.newArrayList());
    }

    public List<ExecuteSql> sqls() {
        return sqlQueue.get();
    }

    public List<Object> params() {
        return paramQueue.get();
    }


}
