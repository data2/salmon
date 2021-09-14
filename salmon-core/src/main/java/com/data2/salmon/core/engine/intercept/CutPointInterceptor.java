package com.data2.salmon.core.engine.intercept;

import com.data2.salmon.core.engine.except.SalmonException;

/**
 * @author data2
 */
public interface CutPointInterceptor {
    /**
     * @param obj
     * @throws SalmonException
     */
    void execute(Object obj) throws SalmonException;
}
