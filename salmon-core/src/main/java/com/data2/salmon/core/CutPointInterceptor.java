package com.data2.salmon.core;

/**
 * @author leewow
 */
public interface CutPointInterceptor {
    /**
     * @param obj
     * @throws SalmonException
     */
    void execute(Object obj) throws SalmonException;
}
