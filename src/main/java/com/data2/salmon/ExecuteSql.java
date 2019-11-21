package com.data2.salmon;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class ExecuteSql {

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
    private OperationKeys operation;
    private String sqlId;
    private String sql;
    private String tableName;
    private Pair partionKey;
    private Object res;
    private TableConfig ruler;

    public ExecuteSql setSql(String sql) {
        this.sql = sql;
        return this;
    }

}
