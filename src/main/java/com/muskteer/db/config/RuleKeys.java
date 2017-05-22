package com.muskteer.db.config;

public class RuleKeys {
    private static String classType = "rule";
    private static String type = "type";
    private static String table = "table";

    public static String getType() {
        return classType.concat(".").concat(type);
    }

    public static String getTable() {
        return classType.concat(".").concat(table);
    }
}
