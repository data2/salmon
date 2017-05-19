package com.barnett.db.engine.proxy.cutpoint.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.barnett.db.base.exception.InnerException;
import com.barnett.db.engine.inner.co.impl.Dico;
import com.barnett.db.engine.proxy.cutpoint.CutPointInterceptor;
import com.barnett.db.util.DicoClassLoader;

public class BeforeInterceptor implements CutPointInterceptor {

    public void execute(Object obj) throws InnerException {
        Dico col = (Dico) obj;
        InputStream colFile = DicoClassLoader.getContextLoader()
                .getResourceAsStream(col.getClassloaderfile());
        if (colFile == null) {
            throw new InnerException("col file not exist.");
        }
        System.out.println("["
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                + "]" + "{execute sql start.}");
    }

}
