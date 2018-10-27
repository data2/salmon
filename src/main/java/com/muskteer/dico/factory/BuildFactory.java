package com.muskteer.dico.factory;

import com.muskteer.dico.common.exception.InnerException;
import com.muskteer.dico.common.util.ArrUtils;
import com.muskteer.dico.common.util.PatternMatcherUnit;
import com.muskteer.dico.config.DicoPair;
import com.muskteer.dico.config.OperationKeys;
import com.muskteer.dico.inner.DicoExecuteSql;
import com.muskteer.dico.parser.Parser;
import com.muskteer.dico.route.Router;
import com.muskteer.dico.route.TableRulerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class BuildFactory {

    @Autowired
    public ConnectFactory connectFactory;

    @Autowired
    public Router router;

    public void build(DicoExecuteSql currSql, Map<?, ?> params) throws InnerException {
        // loading ruler.
        loadFromConfig(currSql);
        // calcu ruler for where data goes.
        TableRulerConfig ruler = currSql.getRuler();
        String dbId = Parser.parse(ruler, params);
        // create connection && prepared sql param.
        connectFactory.setSql(currSql).makeConnect(dbId).preparedStmt(params);

    }

    /**
     * decorate sql.
     *
     * @param currSql
     */
    private void loadFromConfig(DicoExecuteSql currSql) {
        currSql.setTableName(calcuTabnameFromSqlStr(currSql.getSql()));
        TableRulerConfig tabRuler = router.config(currSql.getTableName());
        currSql.setRuler(tabRuler);
        currSql.setPartionKey(new DicoPair(tabRuler.getColumn(), null));
    }

    /**
     * search for tab name.
     *
     * @param sql
     * @return
     */
    private static String calcuTabnameFromSqlStr(String sql) {
        String[] res = PatternMatcherUnit.split(sql, "\\s+");
        boolean tag = false;
        for (String sub : res) {
            if (ArrUtils.inArray(sub.trim(), OperationKeys.INTO, OperationKeys.DELETE, OperationKeys.UPDATE, OperationKeys.FROM)) {
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

}
