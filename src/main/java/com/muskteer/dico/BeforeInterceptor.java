package com.muskteer.dico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BeforeInterceptor implements CutPointInterceptor {

    public void execute(Object obj) throws DicoException {
//        BootDico col = (BootDico) obj;
//        InputStream colFile = DicoClassLoader.getContextLoader().getResourceAsStream(col.getClassloaderFile());
//        if (colFile == null) {
//            throw new DicoException("col file not exist.");
//        }
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql start.}");
    }

}
