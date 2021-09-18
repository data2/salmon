package com.data2.salmon.core.engine.domain;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.data2.salmon.core.engine.enums.Pair;
import com.data2.salmon.core.engine.factory.ReturnFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author data2
 */
public class ExecuteSql {

    private Connection conn;
    private PreparedStatement executor;
    private OperationKeys operation;
    private String sqlId;
    private String sql;
    private String tableName;
    private Pair partionKey;
    private Object res;
    private TableConfig ruler;
    private boolean trans = false;
    private Looker looker;
    private boolean except;
    private Object currParams;

    public ExecuteSql(OperationKeys operation, String sqlID) {
        this.operation = operation;
        this.sqlId = sqlID;
    }

    public ExecuteSql(OperationKeys operation, String sqlID, boolean trans) {
        this.operation = operation;
        this.sqlId = sqlID;
        this.trans = trans;
    }

    public Object exec() throws SQLException {
        Object res = null;
        switch (operation) {
            case INSERT:
                res = executor.execute();
                break;
            case DELETE:
                res = executor.execute();
                break;
            case UPDATE:
                res = executor.executeUpdate();
                break;
            case SELECT:
                res = executor.executeQuery();
                res = ReturnFactory.turnRsToMap((ResultSet) res);
                break;
            default:
                break;
        }
        if (!trans) {
            conn.commit();
            executor.close();
            conn.close();
        } else {
            executor.close();
        }
        return res;
    }

    public String getSql() {
        return sql;
    }

    public ExecuteSql setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public TableConfig getRuler() {
        return ruler;
    }

    public void setRuler(TableConfig ruler) {
        this.ruler = ruler;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void setExecutor(PreparedStatement executor) {
        this.executor = executor;
    }

    public void setPartionKey(Pair partionKey) {
        this.partionKey = partionKey;
    }

    public Looker getLooker() {
        return looker;
    }

    public void setLooker(Looker looker) {
        this.looker = looker;
    }

    public boolean isTrans() {
        return trans;
    }

    public boolean isExcept() {
        return except;
    }

    public void setExcept(boolean except) {
        this.except = except;
    }

    public void setCurrParams(Object currParams) {
        this.currParams = currParams;
    }

    public Object getCurrParams() {
        return currParams;
    }

}
