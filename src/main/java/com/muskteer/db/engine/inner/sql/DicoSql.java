package com.muskteer.db.engine.inner.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.muskteer.db.engine.inner.Pair;
import com.muskteer.db.engine.route.TableRulerBean;
import com.muskteer.db.engine.factory.SqlFactory;

public class DicoSql {
    public DicoSql(String sql) {
        this(null, sql);
    }

    public DicoSql(String operation, String sqlID) {
        this.operation = operation;
        this.sqlId = sqlID;
    }

    public Object exec() throws SQLException {
        Object res = null;
        switch (operation) {
        case "insert":
            res = executor.execute();
            break;
        case "delete":
            res = executor.execute();
            break;
        case "update":
            res = executor.executeUpdate();
            break;
        case "select":
            res = executor.executeQuery();
            res = SqlFactory.turnRsToMap((ResultSet) res);
            break;
        default:
            break;
        }
        conn.commit();
        executor.close();
        conn.close();
        return res;
    }

    private Connection conn;
    private PreparedStatement executor;
    private String operation;
    private String sqlId;
    private String sql;
    private String tableName;
    private Pair partionKey;
    private Object res;
    private TableRulerBean ruler;

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public PreparedStatement getExecutor() {
        return executor;
    }

    public void setExecutor(PreparedStatement executor) {
        this.executor = executor;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public Object getRes() {
        return res;
    }

    public void setRes(Object res) {
        this.res = res;
    }

    public Pair getPartionKey() {
        return partionKey;
    }

    public void setPartionKey(Pair partionKey) {
        this.partionKey = partionKey;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableRulerBean getRuler() {
        return ruler;
    }

    public void setRuler(TableRulerBean ruler) {
        this.ruler = ruler;
    }

}
