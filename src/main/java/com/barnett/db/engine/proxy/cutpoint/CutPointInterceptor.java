package com.barnett.db.engine.proxy.cutpoint;

import com.barnett.db.base.exception.InnerException;

public interface CutPointInterceptor {
    void execute(Object obj) throws InnerException;
}
