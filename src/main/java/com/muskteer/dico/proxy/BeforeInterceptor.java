package com.muskteer.dico.proxy;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.muskteer.dico.common.exception.InnerException;
import com.muskteer.dico.inner.BootDico;
import com.muskteer.dico.common.util.DicoClassLoader;

public class BeforeInterceptor implements CutPointInterceptor {

    public void execute(Object obj) throws InnerException {
        BootDico col = (BootDico) obj;
        InputStream colFile = DicoClassLoader.getContextLoader().getResourceAsStream(col.getClassloaderFile());
        if (colFile == null) {
            throw new InnerException("col file not exist.");
        }
        System.out.println("[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]"
                + "{execute sql start.}");
    }

}
