package com.muskteer.dico.engine.route;

import com.muskteer.dico.engine.factory.ConfigurationFactory;
import com.muskteer.dico.engine.parser.TextFunction;

import java.util.Map;

public class Router {

    public final static String RULE_TABLE = "rule.table";

    public static TableRulerConfig config(String tableName) {
        TableRulerConfig bean = new TableRulerConfig();
        String tabs = ConfigurationFactory.getProperty(RULE_TABLE);
        Map<?, ?> map = TextFunction.contain(tabs, tableName);
        bean.setPartionMethod(((String) map.get("Method")).trim());
        String[] tmp = (String[]) map.get("Ruler");
        String[] params = new String[tmp.length - 2];
        System.arraycopy(tmp, 2, params, 0, 3);
        bean.setName(tmp[0].trim());
        bean.setColumn(tmp[1].trim());
        bean.setParams(params);
        return bean;
    }

}
