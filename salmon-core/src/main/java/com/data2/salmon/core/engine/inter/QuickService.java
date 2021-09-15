package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * @author data2
 */
@Data
@Slf4j
public abstract class QuickService extends LinkService {

    protected ExecuteSql currSql;
    protected boolean exception = false;
    protected ThreadLocal<List<ExecuteSql>> sqlQueue = new ThreadLocal<>();
    protected ThreadLocal<List<Object>> paramQueue = new ThreadLocal<>();
    protected Object currParams;
    private Map<String, Object> context;

    private void initContext() {
        context = Maps.newConcurrentMap();
        context.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    private void clear() {
        sqlQueue.remove();
        paramQueue.remove();

    }

    @Override
    public void startTrans() {
        clear();
        sqlQueue.set(Lists.newArrayList());
        paramQueue.set(Lists.newArrayList());
    }

    @Override
    public void commitTrans() {
        Set<Connection> connectionSet = Sets.newHashSet();
        AtomicBoolean except = new AtomicBoolean(false);
        sqlQueue.get().forEach(executeSql -> {
            if (executeSql.isExcept()) {
                except.set(true);
            }
            connectionSet.add(executeSql.getConn());
        });

        connectionSet.forEach(new Consumer<Connection>() {
            @Override
            public void accept(Connection connection) {
                try {
                    if (except.get()) {
                        connection.rollback();
                    } else {
                        connection.commit();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                    log.info("rollback or commit except");
                }
            }
        });

        if (except.get()) {
            log.info("transaction fail.");
        } else {
            if (connectionSet.size() == 1) {
                log.info("transaction commit success!");
            } else {
                log.info("not same transaction! but all commit!");
            }
        }

        clear();

    }

    @Override
    public QuickService selectTrans(String sqlId) {
        sqlQueue.get().add(currSql = new ExecuteSql(OperationKeys.SELECT, sqlId, true));
        return this;
    }

    @Override
    public QuickService insertTrans(String sqlId) {
        sqlQueue.get().add(currSql = new ExecuteSql(OperationKeys.INSERT, sqlId, true));
        return this;
    }

    @Override
    public QuickService updateTrans(String sqlId) {
        sqlQueue.get().add(currSql = new ExecuteSql(OperationKeys.UPDATE, sqlId, true));
        return this;
    }

    @Override
    public QuickService deleteTrans(String sqlId) {
        sqlQueue.get().add(currSql = new ExecuteSql(OperationKeys.DELETE, sqlId, true));
        return this;
    }

    @Override
    public QuickService select(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.SELECT, sqlId);
        return this;
    }

    @Override
    public QuickService insert(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.INSERT, sqlId);
        return this;
    }

    @Override
    public QuickService update(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.UPDATE, sqlId);
        return this;
    }

    @Override
    public QuickService delete(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.DELETE, sqlId);
        return this;
    }

    @Override
    public QuickService param(Map<String, Object> map) {
        this.currParams = map;
        if (currSql.isTrans()){
            paramQueue.get().add(map);
        }
        return this;
    }

    @Override
    public QuickService param(Object object) {
        this.currParams = object;
        if (currSql.isTrans()){
            paramQueue.get().add(object);
        }
        return this;
    }


    @Override
    public void afterPropertiesSet() {
        initContext();
    }

    @Override
    public String returnInfo() {
        return name + database + file;
    }
}
