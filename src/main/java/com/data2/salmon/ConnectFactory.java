package com.data2.salmon;

import com.data2.salmon.common.util.ColumnSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class ConnectFactory {

    @Autowired
    public PartitionConfig dicoPartitionConfig;

    @Autowired
    public DataSourceConn dataSourceConn;

    private ExecuteSql dicosql;

    public ConnectFactory makeConnect(DataSourceLooker looker) {
        try {
            Connection con = dataSourceConn.getSource(looker).getConnection();
            con.setAutoCommit(false);
            dicosql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void preparedStmt(Map<?, ?> params) throws SalmonException {
        List<Object> sortParms = ColumnSequenceUtil.sort(dicosql, params);
        ColumnSequenceUtil.setValue(dicosql, sortParms);
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConnectFactory setSql(ExecuteSql currSql) {
        this.dicosql = currSql;
        return this;
    }
}
