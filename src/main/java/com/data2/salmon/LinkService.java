package com.data2.salmon;

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
