package com.data2.salmon.core;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.util.ColumnSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author leewow
 */
@Component
public class ConnectFactory {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public PartitionConfig partitionConfig;
    @Autowired
    public DataSourceConn dataSourceConn;
    private ExecuteSql executeSql;

    public ConnectFactory makeConnect(DataSourceLooker looker) {
        try {
            DruidDataSource dds = dataSourceConn.getSource(looker);
            Connection con = dds.getConnection();
            con.setAutoCommit(false);
            executeSql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void preparedStmt(Map<?, ?> params) throws SalmonException {
        List<Object> sortParms = ColumnSequenceUtil.sort(executeSql, params);
        ColumnSequenceUtil.setValue(executeSql, sortParms);
    }

    public ConnectFactory setSql(ExecuteSql currSql) {
        this.executeSql = currSql;
        return this;
    }
}
