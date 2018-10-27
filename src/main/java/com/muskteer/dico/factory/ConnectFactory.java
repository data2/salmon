package com.muskteer.dico.factory;

import com.muskteer.dico.common.exception.InnerException;
import com.muskteer.dico.common.util.ColumnSequenceUtil;
import com.muskteer.dico.config.DicoDatabseConfig;
import com.muskteer.dico.inner.DicoExecuteSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
public class ConnectFactory {

    @Autowired
    public DicoDatabseConfig dicoDatabseConfig;

    private DicoExecuteSql dicosql;

    public String doURL(String dbId) {
        String uri = dicoDatabseConfig.getUrl();
        String[] mapping = dicoDatabseConfig.getMapping().split(",");
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
        String username = dicoDatabseConfig.getUsername();
        String password = dicoDatabseConfig.getPassword();
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

    public ConnectFactory setSql(DicoExecuteSql currSql) {
        this.dicosql = currSql;
        return this;
    }
}
