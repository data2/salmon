package com.muskteer.dico.route;

import com.muskteer.dico.config.DicoDatabseConfig;
import com.muskteer.dico.parser.TextFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Router {

    @Autowired
    public DicoDatabseConfig dicoDatabseConfig;

    public TableRulerConfig config(String tableName) {
        TableRulerConfig bean = new TableRulerConfig();
        String tabs = dicoDatabseConfig.getTable();
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
