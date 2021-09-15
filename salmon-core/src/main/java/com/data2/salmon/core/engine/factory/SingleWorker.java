package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.engine.config.ParseConfig;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.inter.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

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
    public Object execute(Object object) throws SalmonException, SQLException {
        param(object);
        return handle();
    }

    @Override
    public Object execute() throws SalmonException, SQLException {
        return execute(null);
    }

    private Object handle() throws SalmonException, SQLException {
        try {
            buildFactory.build(database, currSql.setSql(parseConfig.parse(file, currSql)), currParams);
            if (!currSql.isTrans()) {
                buildFactory.giveSource(currSql);
            } else {
                if (sqlQueue.get().size() == 1) {
                    buildFactory.giveSource(currSql);
                } else {
                    if (currSql.getLooker().toString().equals(sqlQueue.get().get(0).getLooker().toString())) {
                        buildFactory.copySource(sqlQueue.get().get(0), currSql);
                    } else {
                        buildFactory.giveSource(currSql);
                    }
                }
            }
            return buildFactory.run(currSql, currParams);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute err,{}", e.getMessage());
            currSql.setExcept(true);
            commitTrans();
            throw e;
        }
    }


}
