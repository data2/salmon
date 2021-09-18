package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.trans.Transaction;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author data2
 */
public interface OriginOperation extends Transaction {

    OriginOperation select(String id);

    OriginOperation insert(String id);

    OriginOperation update(String id);

    OriginOperation delete(String id);

    OriginOperation selectTrans(String id);

    OriginOperation insertTrans(String id);

    OriginOperation updateTrans(String id);

    OriginOperation deleteTrans(String id);

    OriginOperation param(Map<String, Object> obj);

    OriginOperation param(Object obj);

    OriginOperation paramTrans(Map<String, Object> obj);

    OriginOperation paramTrans(Object obj);

    Object execute(Object object) throws SalmonException, SQLException;

    Object execute() throws SalmonException, SQLException;

    Object executeTrans(Object object) throws SalmonException, SQLException;

    Object executeTrans() throws SalmonException, SQLException;

    String returnInfo();

}
