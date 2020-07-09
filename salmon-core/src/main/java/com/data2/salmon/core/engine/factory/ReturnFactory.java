package com.data2.salmon.core.engine.factory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leewow
 */
public class ReturnFactory {

    public static Map<String, Object> turnRsToMap(ResultSet res) throws SQLException {
        try {
            return getResultMap(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> getResultMap(ResultSet rs) throws SQLException {
        Map<String, Object> hm = new HashMap<String, Object>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 0; i < count; i++) {
                String key = rsmd.getColumnLabel(i + 1);
                Object value = rs.getObject(i + 1);
                hm.put(key, value);
            }
        }
        return hm;
    }

    public static Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> row = new HashMap<String, Object>();
        ResultSetMetaData metaData = rs.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); ++i) {
            System.out.println(rs.getObject(0));
            String column = lookupColumnName(metaData, i);
            Object value = getResultSetValue(rs, i);
            row.put(column, value);
        }

        return row;
    }

    public static String lookupColumnName(ResultSetMetaData resultSetMetaData, int columnIndex) throws SQLException {
        String name = resultSetMetaData.getColumnLabel(columnIndex);
        if (name == null || name.length() < 1) {
            name = resultSetMetaData.getColumnName(columnIndex);
        }
        return name;
    }

    public static Object getResultSetValue(ResultSet rs, int index) throws SQLException {
        Object obj = rs.getObject(index);
        String className = null;
        if (obj != null) {
            className = obj.getClass().getName();
        }
        if (obj instanceof Blob) {
            obj = rs.getBytes(index);
        } else if (obj instanceof Clob) {
            obj = rs.getString(index);
        } else if (className != null
                && ("oracle.sql.TIMESTAMP".equals(className) || "oracle.sql.TIMESTAMPTZ".equals(className))) {
            obj = rs.getTimestamp(index);
        } else if (className != null && className.startsWith("oracle.sql.DATE")) {
            String metaDataClassName = rs.getMetaData().getColumnClassName(index);
            if ("java.sql.Timestamp".equals(metaDataClassName) || "oracle.sql.TIMESTAMP".equals(metaDataClassName)) {
                obj = rs.getTimestamp(index);
            } else {
                obj = rs.getDate(index);
            }
        } else if (obj instanceof java.sql.Date) {
            if ("java.sql.Timestamp".equals(rs.getMetaData().getColumnClassName(index))) {
                obj = rs.getTimestamp(index);
            }
        } else if (obj instanceof String) {
            obj = ((String) obj).trim();
        }
        return obj;
    }

}
