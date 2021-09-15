package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.except.SalmonException;

import java.sql.SQLException;

/**
 * @author data2
 */
public interface Executor {
    /**
     * @return
     */
    Object execute(Object object) throws SalmonException, SQLException;
}
