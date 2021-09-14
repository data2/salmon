package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.engine.config.ParseConfig;
import com.data2.salmon.core.engine.inter.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author data2
 */
@Component
@Slf4j
public class SingleWorker extends QuickService {
    @Autowired
    public BuildFactory buildFactory;
    @Autowired
    private ParseConfig parseConfig;

    @Override
    public Object execute(Object object) {
        return handle(object);
    }

    @Override
    public Object execute() {
        return handle(null);
    }

    public Object handle(Object object) {
        try {
            return buildFactory.build(database, currSql.setSql(parseConfig.parse(file, currSql)), object);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute err,{}", e.getMessage());
            exception = true;
            return null;
        }
    }

}
