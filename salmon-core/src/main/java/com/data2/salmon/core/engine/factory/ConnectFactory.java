package com.data2.salmon.core.engine.factory;

import com.alibaba.druid.pool.DruidDataSource;
import com.data2.salmon.core.common.util.ColumnSequenceUtil;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.druid.DataSourceFactory;
import com.data2.salmon.core.engine.except.SalmonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author data2
 */
@Component
public class ConnectFactory {

    @Autowired
    public DataSourceFactory dataSourceFactory;

    public ExecuteSql makeConnect(ExecuteSql sql) {
        try {
            DruidDataSource druidDataSource = dataSourceFactory.getSource(sql.getLooker());
            Connection con = druidDataSource.getConnection();
            con.setAutoCommit(false);
            sql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return sql;
    }

    public ExecuteSql preparedStmt(ExecuteSql sql, Object params) throws SalmonException {
        List<Object> sortParams = ColumnSequenceUtil.sort(sql, params);
        ColumnSequenceUtil.setValue(sql, sortParams);
        return sql;
    }


}
