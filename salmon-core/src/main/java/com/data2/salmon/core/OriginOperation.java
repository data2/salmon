package com.data2.salmon.core;

import java.util.Map;

/**
 * @author leewow
 */
public interface OriginOperation {
    void getTrans();

    OriginOperation select(String id);

    OriginOperation insert(String id);

    OriginOperation update(String id);

    OriginOperation delete(String id);

    OriginOperation param(Map<?, ?> obj);

    Object execute();

    String returnInfo();

}
