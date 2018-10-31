package com.muskteer.dico;

import com.muskteer.dico.DicoException;

public interface CutPointInterceptor {
    void execute(Object obj) throws DicoException;
}
