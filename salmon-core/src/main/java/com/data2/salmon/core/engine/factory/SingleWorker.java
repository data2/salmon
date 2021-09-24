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
import java.util.Objects;

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
                if (current.getLooker().equals(cooperator.sqls().get(0).getLooker())) {
                    buildFactory.copySource(cooperator.sqls().get(0), current);
                } else {
                    buildFactory.giveSource(current);
                }
            }
            Object result = buildFactory.run(current);
            log.info("execute sql:{} success, result:{}, thread:{}", current.getSql(), result, Thread.currentThread().getId());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute sql:{} fail, err:{}, thread:{}", current.getSql(), e.getMessage(), Thread.currentThread().getId());
            current.setExcept(true);
            commitTrans();
            throw e;
        }
    }

    private Object handle() throws SalmonException, SQLException {
        ExecuteSql current = null;
        try {
            current = currSql.get();
            if (Objects.isNull(current)) {
                throw new SalmonException("execute error");
            }
            buildFactory.build(database, current.setSql(parseConfig.parse(file, current)));
            buildFactory.giveSource(current);

            Object result = buildFactory.run(current);
            log.info("execute sql:{} success, result:{}, thread:{}", current.getSql(), result, Thread.currentThread().getId());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("execute sql:{} fail, err:{}, thread:{}", current.getSql(), e.getMessage(), Thread.currentThread().getId());
            if (Objects.nonNull(current)) {
                current.setExcept(true);
                close(current);
            }
            throw e;
        }
    }
}
