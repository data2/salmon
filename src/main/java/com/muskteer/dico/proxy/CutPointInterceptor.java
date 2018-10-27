package com.muskteer.dico.proxy;

import com.muskteer.dico.common.exception.InnerException;

public interface CutPointInterceptor {
    void execute(Object obj) throws InnerException;
}
