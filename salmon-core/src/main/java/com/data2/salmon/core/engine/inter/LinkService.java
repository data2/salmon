package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.trans.Transaction;
import lombok.Data;

/**
 * @author data2
 */
@Data
public abstract class LinkService extends CloseSource implements AbstractService, Transaction {
    public String name;
    public DataBase database;
    public String file;
}
