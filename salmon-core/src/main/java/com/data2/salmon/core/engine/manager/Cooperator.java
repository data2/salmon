package com.data2.salmon.core.engine.manager;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.inter.CloseSource;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @author data2
 * @date
 */
@Component
@Slf4j
public class Cooperator extends CloseSource {
    private ThreadLocal<LinkedList<ExecuteSql>> sqlQueue = new ThreadLocal<>();

    public void clear() {
        if (Objects.isNull(sqlQueue.get())){
            return;
        }
        sqlQueue.get().forEach(sql -> {
            close(sql);
        });
        sqlQueue.remove();
    }

    public void init() {
        sqlQueue.set(Lists.newLinkedList());
    }

    public LinkedList<ExecuteSql> sqls() {
        return sqlQueue.get();
    }

}
