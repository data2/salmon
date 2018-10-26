package com.muskteer.dico.engine.proxy.cutpoint;

import com.muskteer.dico.base.exception.InnerException;

public interface CutPointInterceptor {
    void execute(Object obj) throws InnerException;
}
