package com.data2.salmon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author leewow
 */
@Component
public class Router {

    @Autowired
    public PartitionConfig partitionConfig;

    public TableConfig config(String tableName) {
        TableConfig bean = new TableConfig();
        String tabs = partitionConfig.getTable();
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
