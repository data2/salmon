package com.muskteer.dico;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class ConnectFactory {

    @Autowired
    public DicoPartitionConfig dicoPartitionConfig;

    private DicoExecuteSql dicosql;

    public String doURL(String dbId) {
        String uri = dicoPartitionConfig.getUrl();
        String[] mapping = dicoPartitionConfig.getMapping().split(",");
        String[] tmp = null;
        for (String sub : mapping) {
            tmp = sub.split("\\|");
            if (tmp.length == 2) {
                if (tmp[0].equals("db" + dbId)) {
                    uri = uri.replace("XXX", tmp[1]);
                    break;
                }
            }
        }
        return uri;

    }

    public ConnectFactory makeConnect(String dbId) {
        String uri = doURL(dbId);
        String username = dicoPartitionConfig.getUsername();
        String password = dicoPartitionConfig.getPassword();
        try {
            //con = DriverManager.getConnection(uri, username, password);
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(uri);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setDriverClassName(uri.contains("jdbc") ? "com.mysql.jdbc.Driver" : "oracle.jdbc.driver.OracleDriver");
            dataSource.setInitialSize(5);
            Connection con = dataSource.getConnection();
            con.setAutoCommit(false);
            dicosql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    public void preparedStmt(Map<?, ?> params) throws DicoException {
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

    public ConnectFactory setSql(DicoExecuteSql currSql) {
        this.dicosql = currSql;
        return this;
    }
}
