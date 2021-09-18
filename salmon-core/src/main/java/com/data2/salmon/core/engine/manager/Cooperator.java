package com.data2.salmon.core.engine.manager;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Cooperator {
    private ThreadLocal<LinkedList<ExecuteSql>> sqlQueue = new ThreadLocal<>();

    public void clear() {
        sqlQueue.remove();
    }

    public void init() {
        sqlQueue.set(Lists.newLinkedList());
    }

    public LinkedList<ExecuteSql> sqls() {
        return sqlQueue.get();
    }

}
