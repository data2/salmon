package com.data2.salmon.core.engine.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.common.util.ColumnSequenceUtil;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.domain.Looker;
import com.data2.salmon.core.engine.druid.DataSourceFactory;
import com.data2.salmon.core.engine.except.SalmonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author leewow
 */
@Component
public class ConnectFactory {

    @Autowired
    public DataSourceFactory dataSourceFactory;
    private ExecuteSql executeSql;

    public ConnectFactory makeConnect(Looker looker) {
        try {
            DruidDataSource druidDataSource = dataSourceFactory.getSource(looker);
            Connection con = druidDataSource.getConnection();
            con.setAutoCommit(false);
            executeSql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void preparedStmt(Object params) throws SalmonException {
        List<Object> sortParms = ColumnSequenceUtil.sort(executeSql, params);
        ColumnSequenceUtil.setValue(executeSql, sortParms);
    }

    public ConnectFactory setSql(ExecuteSql currSql) {
        this.executeSql = currSql;
        return this;
    }
}
