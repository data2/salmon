package com.data2.salmon;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultOutputInterceptor implements CutPointInterceptor {

    @Override
    public void execute(Object obj) {
        QuickService col = (QuickService) obj;
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql end.}{sql content:" + col.getCurrSql().getSql() + "}");
    }

}
