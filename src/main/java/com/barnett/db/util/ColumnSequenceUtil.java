package com.barnett.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.barnett.db.base.exception.InnerException;
import com.barnett.db.engine.inner.sql.DicoSql;

public class ColumnSequenceUtil {
    
    static Pattern p = Pattern.compile("\\#\\w+\\#");

    /**
     * convert params map to sorted list.
     * @param currSql
     * @param params
     * @return
     * @throws InnerException
     */
    public static List<Object> sort(DicoSql currSql, 
            Map<?, ?> params) throws InnerException {
        String sql = currSql.getSql();
        java.util.regex.Matcher m = p.matcher(sql);
        List<Object> keylist = new ArrayList<Object>();
        String tmp = null;
        while(m.find()){
            tmp = m.group();
            Object val = params.get(tmp.substring(1, tmp.length()-1));
            if(val == null){
                throw new InnerException( "column value" + tmp + " is null.");
            }
            keylist.add(val);
        }
        currSql.setSql(currSql.getSql().replaceAll("\\#\\w+\\#", " ? "));
        return keylist;
    }

    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\#\\w+\\#");
        java.util.regex.Matcher m = p.matcher("hhskldfk #hHS#SDHF#_s# ");
        List<String> keylist = new ArrayList<String>();
        String tmp = null;
        while(m.find()){
            tmp = m.group();
            System.out.println(tmp);
            if(tmp != null){
                keylist.add(tmp);
            }
        }
        System.out.println(m.groupCount());
    }

    /**
     * preparedstmt set values.
     * @param currSql
     * @param sortParms
     */
    public static void setValue(DicoSql currSql, List<Object> sortParms) {
        Connection conn = currSql.getConn();
        String sql = currSql.getSql();
        PreparedStatement executor = null;
        try {
            executor = conn.prepareStatement(sql);
            java.util.regex.Matcher m = Pattern.compile("\\?").matcher(sql);
            int count = 1;
            while(m.find()){
                Object obj = sortParms.get(count-1);
                executor.setObject(count++, obj);
            }
            currSql.setExecutor(executor);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

}
