package com.muskteer.dico.factory;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.muskteer.dico.common.InnerException;
import com.muskteer.dico.config.DicoPair;
import com.muskteer.dico.config.OperationKeys;
import com.muskteer.dico.inner.DicoSql;
import com.muskteer.dico.parser.Parser;
import com.muskteer.dico.route.Router;
import com.muskteer.dico.route.TableRulerConfig;
import com.muskteer.dico.common.util.ArrUtils;
import com.muskteer.dico.common.util.PatternMatcherUnit;
import org.springframework.util.StringUtils;

public class BuildFactory {

    public static void build(DicoSql currSql, Map<?, ?> params) throws InnerException {
        // loading ruler.
        loadFromConfig(currSql);
        // calcu ruler for where data goes.
        TableRulerConfig ruler = currSql.getRuler();
        String dbId = Parser.parse(ruler, params);
        // create connection && prepared sql param.
        new ConnectFactory(currSql).makeConnect(dbId).preparedStmt(params);

    }

    /**
     * decorate sql.
     * 
     * @param currSql
     */
    private static void loadFromConfig(DicoSql currSql) {
        currSql.setTableName(calcuTabnameFromSqlStr(currSql.getSql()));
        TableRulerConfig tabRuler = Router.config(currSql.getTableName());
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

    public static void main(String[] args) {
        Pattern p = Pattern.compile("\\#\\w+\\#");
        Matcher m = p.matcher("sfssf   #aiW_# sdf  #sd#");
        while (m.find()) {
            System.out.println(m.group());
        }

    }

}
