package com.data2.salmon;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParamsInterceptor implements CutPointInterceptor {

    public void execute(Object obj) throws SalmonException {
//        BootDico col = (BootDico) obj;
//        InputStream colFile = DicoClassLoader.getContextLoader().getResourceAsStream(col.getClassloaderFile());
//        if (colFile == null) {
//            throw new SalmonException("col file not exist.");
//        }
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql start.}");
    }

}
