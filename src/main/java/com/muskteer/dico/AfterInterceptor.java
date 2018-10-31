package com.muskteer.dico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AfterInterceptor implements CutPointInterceptor {

    @Override
    public void execute(Object obj) {
        QuickDicoService col = (QuickDicoService) obj;
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql end.}{sql content:" + col.getCurrSql().getSql() + "}");
    }

}
