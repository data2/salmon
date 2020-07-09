package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.engine.config.ParseConfig;
import com.data2.salmon.core.engine.inter.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leewow
 */
@Component
@Slf4j
public class SingleWorker extends QuickService {
    @Autowired
    public BuildFactory buildFactory;
    @Autowired
    private ParseConfig parseConfig;

    @Override
    public Object execute() {
        try {
            buildFactory.build(database, currSql.setSql(parseConfig.parse(file, currSql)), currParams);
            sqlArr.add(currSql);
            return currSql.exec();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute err,{}", e.getMessage());
            exception = true;
            return null;
        }
    }

}
