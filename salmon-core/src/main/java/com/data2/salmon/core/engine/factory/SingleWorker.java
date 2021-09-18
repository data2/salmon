package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.engine.config.ParseConfig;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.inter.QuickService;
import com.data2.salmon.core.engine.manager.Cooperator;
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

    SingleWorker(Cooperator cooperator) {
        super.cooperator = cooperator;
    }

    @Override
    public Object execute(Object object) throws SalmonException, SQLException {
        param(object);
        return handle();
    }

    @Override
    public Object execute() throws SalmonException, SQLException {
        return execute(null);
    }

    @Override
    public Object executeTrans(Object object) throws SalmonException, SQLException {
        paramTrans(object);
        return handleTrans();
    }

    @Override
    public Object executeTrans() throws SalmonException, SQLException {
        return executeTrans(null);
    }

    private Object handleTrans() throws SalmonException, SQLException {
        ExecuteSql current = null;
        try {
            current = cooperator.sqls().getLast();
            buildFactory.build(database, current.setSql(parseConfig.parse(file, current)));
            if (cooperator.sqls().size() == 1) {
                buildFactory.giveSource(current);
            } else {
                if (current.getLooker().toString().equals(cooperator.sqls().get(0).getLooker().toString())) {
                    buildFactory.copySource(cooperator.sqls().get(0), current);
                } else {
                    buildFactory.giveSource(current);
                }
            }
            return buildFactory.run(current);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute err,{}", e.getMessage());
            current.setExcept(true);
            commitTrans();
            throw e;
        }

    }


    private Object handle() throws SalmonException, SQLException {
        try {
            buildFactory.build(database, currSql.get().setSql(parseConfig.parse(file, currSql.get())));
            buildFactory.giveSource(currSql.get());
            return buildFactory.run(currSql.get());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute err,{}", e.getMessage());
            currSql.get().setExcept(true);
            throw e;
        }
    }


}
