package com.muskteer.dico.engine.route;

import java.util.Map;

import com.muskteer.dico.config.RuleKeys;
import com.muskteer.dico.engine.factory.ConfigurationFactory;
import com.muskteer.dico.engine.parser.TextFunction;

public class Router {

    public static TableRulerBean config(String tableName) {
        TableRulerBean bean = new TableRulerBean();
        String tabs = ConfigurationFactory.getProperty(RuleKeys.getTableRuleConfig());
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

    public static void main(String[] args) {
        TableRulerBean bean = config("TF_B_TRADE_LOG");
        System.out.println(bean.toString());
    }

}
