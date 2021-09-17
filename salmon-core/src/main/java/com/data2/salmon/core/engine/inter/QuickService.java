package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.data2.salmon.core.engine.manager.Cooperator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    protected Cooperator cooperator;
    protected Object currParams;
    private Map<String, Object> context;

    private void initContext() {
        context = Maps.newConcurrentMap();
        context.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @Override
    public void startTrans() {
        cooperator.clear();
        cooperator.init();
    }

    @Override
    public void commitTrans() {
        Set<Connection> connectionSet = Sets.newHashSet();
        AtomicBoolean except = new AtomicBoolean(false);
        cooperator.sqls().forEach(executeSql -> {
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

        cooperator.clear();

    }

    @Override
    public QuickService selectTrans(String sqlId) {
        cooperator.sqls().add(currSql = new ExecuteSql(OperationKeys.SELECT, sqlId, true));
        return this;
    }

    @Override
    public QuickService insertTrans(String sqlId) {
        cooperator.sqls().add(currSql = new ExecuteSql(OperationKeys.INSERT, sqlId, true));
        return this;
    }

    @Override
    public QuickService updateTrans(String sqlId) {
        cooperator.sqls().add(currSql = new ExecuteSql(OperationKeys.UPDATE, sqlId, true));
        return this;
    }

    @Override
    public QuickService deleteTrans(String sqlId) {
        cooperator.sqls().add(currSql = new ExecuteSql(OperationKeys.DELETE, sqlId, true));
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
        if (currSql.isTrans()) {
            cooperator.params().add(map);
        }
        return this;
    }

    @Override
    public QuickService param(Object object) {
        this.currParams = object;
        if (currSql.isTrans()) {
            cooperator.params().add(object);
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
