package com.muskteer.db.config;

public class RuleKeys {
    private static String RULE_PRE = "rule";
    private static String TPYE_CONFIG = "type";
    private static String TABLE_CONFIG = "table";

    public static String getTypeConfig() {
        return RULE_PRE.concat(".").concat(TPYE_CONFIG);
    }

    public static String getTableRuleConfig() {
        return RULE_PRE.concat(".").concat(TABLE_CONFIG);
    }
}
