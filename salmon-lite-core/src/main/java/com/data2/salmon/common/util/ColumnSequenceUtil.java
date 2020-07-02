package com.data2.salmon.common.util;

import com.data2.salmon.ExecuteSql;
import com.data2.salmon.SalmonException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author leewow
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
    public static List<Object> sort(ExecuteSql currSql, Map<?, ?> params) throws SalmonException {
        String sql = currSql.getSql();
        java.util.regex.Matcher m = p.matcher(sql);
        List<Object> keylist = new ArrayList<>();
        String tmp;
        while (m.find()) {
            tmp = m.group();
            Object val = params.get(tmp.substring(1, tmp.length() - 1));
            if (val == null) {
                throw new SalmonException("column value" + tmp + " is null.");
            }
            keylist.add(val);
        }
        currSql.setSql(currSql.getSql().replaceAll("\\#\\w+\\#", " ? "));
        return keylist;
    }

    public static void main(String[] args) {
        java.util.regex.Matcher m = p.matcher("hhskldfk #hHS#SDHF#_s# ");
        List<String> keylist = new ArrayList<String>();
        String tmp;
        while (m.find()) {
            tmp = m.group();
            System.out.println(tmp);
            if (tmp != null) {
                keylist.add(tmp);
            }
        }
        System.out.println(m.groupCount());
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
                Object obj = sortParms.get(count - 1);
                executor.setObject(count++, obj);
            }
            currSql.setExecutor(executor);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
