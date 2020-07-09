package com.data2.salmon.core.engine.domain;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.data2.salmon.core.engine.enums.Pair;
import com.data2.salmon.core.engine.factory.ReturnFactory;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author leewow
 */
@Data
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

}
