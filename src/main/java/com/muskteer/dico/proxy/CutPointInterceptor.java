package com.muskteer.dico.proxy;

import com.muskteer.dico.common.exception.DicoException;

public interface CutPointInterceptor {
    void execute(Object obj) throws DicoException;
}
