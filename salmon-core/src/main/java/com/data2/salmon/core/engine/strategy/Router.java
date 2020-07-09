package com.data2.salmon.core.engine.strategy;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.config.TextFunction;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author leewow
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.salmon.database.partition")
public class Router {
    private String table;

    public TableConfig config(String tableName) {
        TableConfig bean = new TableConfig();
        Map<?, ?> map = TextFunction.contain(table, tableName);
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
