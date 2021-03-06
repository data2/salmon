package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.enums.DataBase;
import lombok.Data;

/**
 * @author leewow
 */
@Data
public abstract class LinkService implements AbstractService {
    public String name;
    public DataBase database;
    public String file;
}
