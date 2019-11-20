package com.data2.salmon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DicoExecuteSql {

    public DicoExecuteSql(String operation, String sqlID) {
        this.operation = operation;
        this.sqlId = sqlID;
    }

    public Object exec() throws SQLException {
        Object res = null;
        switch (operation) {
        case OperationKeys.INSERT :
            res = executor.execute();
            break;
        case OperationKeys.DELETE:
            res = executor.execute();
            break;
        case OperationKeys.UPDATE:
            res = executor.executeUpdate();
            break;
        case OperationKeys.SELECT:
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
    private DicoPair partionKey;
    private Object res;
    private TableRulerConfig ruler;
    public DicoExecuteSql setSql(String sql){
        this.sql = sql;
        return this;
    }

}
