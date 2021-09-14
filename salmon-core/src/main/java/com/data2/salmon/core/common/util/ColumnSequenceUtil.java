package com.data2.salmon.core.common.util;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.except.SalmonException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author data2
 */
public class ColumnSequenceUtil {

    static Pattern p = Pattern.compile("\\#\\w+\\#");
    static Pattern p2 = Pattern.compile("\\?");

    /**
     * convert params map to sorted list.
     *
     * @param currSql
     * @param params
     * @return
     * @throws SalmonException
     */
    public static List<Object> sort(ExecuteSql currSql, Object params) throws SalmonException {
        List<Object> keylist = new ArrayList<>();
        if (params == null) {
            return keylist;
        }
        if (params instanceof Map) {
            Map paramMap = (Map) params;
            String sql = currSql.getSql();
            java.util.regex.Matcher m = p.matcher(sql);
            String tmp;
            while (m.find()) {
                tmp = m.group();
                Object val = paramMap.get(tmp.substring(1, tmp.length() - 1));
                if (val == null) {
                    throw new SalmonException("column value" + tmp + " is null.");
                }
                keylist.add(val);
            }
            currSql.setSql(currSql.getSql().replaceAll("\\#\\w+\\#", " ? "));
        } else {
            currSql.setSql(currSql.getSql().replaceAll("\\#\\#", " ? "));
            keylist.add(params);
        }
        return keylist;
    }

    /**
     * preparedstmt set values.
     *
     * @param currSql
     * @param sortParms
     */
    public static void setValue(ExecuteSql currSql, List<Object> sortParms) {
        Connection conn = currSql.getConn();
        String sql = currSql.getSql();
        PreparedStatement executor;
        try {
            executor = conn.prepareStatement(sql);
            java.util.regex.Matcher m = p2.matcher(sql);
            int count = 1;
            while (m.find()) {
                if (sortParms.size() > 0) {
                    Object obj = sortParms.get(count - 1);
                    executor.setObject(count++, obj);
                }
            }
            currSql.setExecutor(executor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
