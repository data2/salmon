package com.data2.salmon.core.engine.factory;

import com.data2.salmon.core.common.util.ArrUtils;
import com.data2.salmon.core.engine.config.Parser;
import com.data2.salmon.core.engine.config.PatternMatcherUnit;
import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.domain.ExecuteSql;
import com.data2.salmon.core.engine.domain.Looker;
import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.enums.OperationKeys;
import com.data2.salmon.core.engine.enums.Pair;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.strategy.Router;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

/**
 * @author data2
 */
@Slf4j
@Component
public class BuildFactory {

    @Autowired
    public ConnectFactory connectFactory;

    @Autowired
    public Router router;

    /**
     * search for tab name.
     *
     * @param sql
     * @return
     */
    private String calcuTabnameFromSqlStr(String sql) {
        String[] res = PatternMatcherUnit.split(sql, "\\s+");
        boolean tag = false;
        for (String sub : res) {
            if (ArrUtils.inArray(sub.trim().toUpperCase(), OperationKeys.INTO.name(), OperationKeys.DELETE.name(), OperationKeys.UPDATE.name(), OperationKeys.FROM.name())) {
                tag = true;
                continue;
            }
            if (tag) {
                if (!StringUtils.isEmpty(sub.trim())) {
                    return sub;
                }
            }
        }

        return null;
    }

    /**
     * if partition
     * loading ruler.
     * calcu ruler for where data goes.
     *
     * @param dbase
     * @param sql
     * @param params
     * @throws SalmonException
     */
    public void build(DataBase dbase, ExecuteSql sql, Object params) throws SalmonException {
        setTabName(sql);
        sql.setLooker(new Looker(dbase, target(dbase, sql, params)));
    }

    public ExecuteSql giveSource(ExecuteSql sql) throws SalmonException {
        return connectFactory.makeConnect(sql);
    }

    public void copySource(ExecuteSql sourceSql, ExecuteSql sql) throws SalmonException {
        sql.setConn(sourceSql.getConn());
    }

    public Object run(ExecuteSql sql, Object params) throws SalmonException, SQLException {
        return connectFactory.preparedStmt(sql, params).exec();
    }


    private void setTabName(ExecuteSql sql) {
        sql.setTableName(calcuTabnameFromSqlStr(sql.getSql()));
    }

    private String target(DataBase dbase, ExecuteSql sql, Object params) throws SalmonException {
        if (dbase == DataBase.partition) {
            loadFromConfig(sql);
            if (params == null) {
                log.error("partition need value not null");
            }
            return Parser.parse(sql.getRuler(), params);
        }
        return null;
    }

    /**
     * decorate sql.
     *
     * @param currSql
     */
    private void loadFromConfig(ExecuteSql currSql) {
        TableConfig tabRuler = router.config(currSql.getTableName());
        currSql.setRuler(tabRuler);
        currSql.setPartionKey(new Pair(tabRuler.getColumn(), null));
    }


}
