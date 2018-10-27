package com.muskteer.dico1.engine.proxy.cutpoint;

import com.muskteer.dico1.base.exception.InnerException;

public interface CutPointInterceptor {
    void execute(Object obj) throws InnerException;
}
