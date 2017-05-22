package com.muskteer.db.engine.proxy.cutpoint;

import com.muskteer.db.base.exception.InnerException;

public interface CutPointInterceptor {
    void execute(Object obj) throws InnerException;
}
