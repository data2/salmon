package com.data2.salmon.core.engine.intercept;

import com.data2.salmon.core.engine.inter.QuickService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leewow
 */
public class ResultOutputInterceptor implements CutPointInterceptor {

    @Override
    public void execute(Object obj) {
        QuickService col = (QuickService) obj;
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql end.}{sql content:" + col.getCurrSql().getSql() + "}");
    }

}
