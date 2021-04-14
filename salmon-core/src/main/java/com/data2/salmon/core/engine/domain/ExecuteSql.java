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
 * @author leewow
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

    public ExecuteSql(OperationKeys operation, String sqlID) {
        this.operation = operation;
        this.sqlId = sqlID;
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
        conn.commit();
        executor.close();
        conn.close();
        return res;
    }

    public ExecuteSql setSql(String sql) {
        this.sql = sql;
        return this;
    }

    public String getSql() {
        return sql;
    }

    public void setRuler(TableConfig ruler) {
        this.ruler = ruler;
    }

    public TableConfig getRuler() {
        return ruler;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
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


}
