package com.muskteer.dico.engine.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.muskteer.dico.base.exception.InnerException;
import com.muskteer.dico.engine.config.DBaseKeys;
import com.muskteer.dico.engine.inner.DicoSql;
import com.muskteer.dico.common.util.ColumnSequenceUtil;

public class ConnectFactory {

    private DicoSql dicosql;

    public ConnectFactory(DicoSql sql) {
        this.dicosql = sql;
    }

    public DicoSql getDicosql() {
        return dicosql;
    }

    public void setDicosql(DicoSql dicosql) {
        this.dicosql = dicosql;
    }

    /**
     * parse connect uri.
     * 
     * @param dbId
     * @return
     */
    public String doURL(String dbId) {
        String uri = ConfigurationFactory.getProperty(DBaseKeys.url);
        String[] mapping = ConfigurationFactory.getProperty(DBaseKeys.mapping).split(",");
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

    /**
     * create sql conn.
     * 
     * @param currSql
     * @param dbId
     */
    public ConnectFactory makeConnect(String dbId) {
        String uri = doURL(dbId);
        String username = ConfigurationFactory.getProperty(DBaseKeys.username);
        String password = ConfigurationFactory.getProperty(DBaseKeys.password);
        Connection con = null;
        try {
            con = DriverManager.getConnection(uri, username, password);
            con.setAutoCommit(false);
            dicosql.setConn(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * prepared stmt.
     * 
     * @param currSql
     * @param params
     * @throws InnerException
     */
    public void preparedStmt(Map<?, ?> params) throws InnerException {
        List<Object> sortParms = ColumnSequenceUtil.sort(dicosql, params);
        ColumnSequenceUtil.setValue(dicosql, sortParms);
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
