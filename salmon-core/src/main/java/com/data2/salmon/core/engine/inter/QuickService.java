package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.data2.salmon.core.engine.manager.Cooperator;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author data2
 */
@Data
@Slf4j
public abstract class QuickService extends LinkService {

    protected ThreadLocal<ExecuteSql> currSql;
    protected Cooperator cooperator;
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
        try {
            Set<Connection> connectionSet = Sets.newHashSet();
            AtomicBoolean except = new AtomicBoolean(false);
            cooperator.sqls().forEach(executeSql -> {
                if (executeSql.isExcept()) {
                    except.set(true);
                }
                connectionSet.add(executeSql.getConn());
            });

            connectionSet.forEach(connection -> {
                try {
                    execResult(except.get(), connection);
                } catch (SQLException e1) {
                    try{
                        execResult(except.get(), connection);
                    }catch (SQLException e2){
                        e2.printStackTrace();
                        log.info("rollback or commit except");
                    }
                }
            });

            if (except.get()) {
                log.info("transaction commit fail.");
            } else {
                if (connectionSet.size() == 1) {
                    log.info("transaction commit success!");
                } else {
                    log.info("fake transaction all commit!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("transaction commit except:{}", e.getMessage());
        } finally {
            cooperator.clear();
        }

    }

    private void execResult(boolean b, Connection connection) throws SQLException {
        if (connection == null || connection.isClosed()){
            return;
        }
        if (b) {
            connection.rollback();
        } else {
            connection.commit();
        }
    }

    @Override
    public QuickService selectTrans(String sqlId) {
        cooperator.sqls().add(new ExecuteSql(OperationKeys.SELECT, sqlId, true));
        return this;
    }

    @Override
    public QuickService insertTrans(String sqlId) {
        cooperator.sqls().add(new ExecuteSql(OperationKeys.INSERT, sqlId, true));
        return this;
    }

    @Override
    public QuickService updateTrans(String sqlId) {
        cooperator.sqls().add(new ExecuteSql(OperationKeys.UPDATE, sqlId, true));
        return this;
    }

    @Override
    public QuickService deleteTrans(String sqlId) {
        cooperator.sqls().add(new ExecuteSql(OperationKeys.DELETE, sqlId, true));
        return this;
    }

    @Override
    public QuickService select(String sqlId) {
        this.currSql.set(new ExecuteSql(OperationKeys.SELECT, sqlId));
        return this;
    }

    @Override
    public QuickService insert(String sqlId) {
        this.currSql.set(new ExecuteSql(OperationKeys.INSERT, sqlId));
        return this;
    }

    @Override
    public QuickService update(String sqlId) {
        this.currSql.set(new ExecuteSql(OperationKeys.UPDATE, sqlId));
        return this;
    }

    @Override
    public QuickService delete(String sqlId) {
        this.currSql.set(new ExecuteSql(OperationKeys.DELETE, sqlId));
        return this;
    }

    @Override
    public QuickService paramTrans(Map<String, Object> map) {
        return paramTrans(map);
    }

    @Override
    public QuickService paramTrans(Object object) {
        if (!CollectionUtils.isEmpty(cooperator.sqls()) && Objects.nonNull(cooperator.sqls().getLast())) {
            cooperator.sqls().getLast().setCurrParams(object);
        }
        return this;
    }

    @Override
    public QuickService param(Map<String, Object> map) {
        currSql.get().setCurrParams(map);
        return this;
    }

    @Override
    public QuickService param(Object object) {
        currSql.get().setCurrParams(object);
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
