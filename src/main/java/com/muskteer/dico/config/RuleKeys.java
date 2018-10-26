package com.muskteer.dico.config;

public class RuleKeys {
    private static String RULE_PRE = "rule";
    private static String TABLE_CONFIG = "table";

    public static String getTableRuleConfig() {
        return RULE_PRE.concat(".").concat(TABLE_CONFIG);
    }
}
