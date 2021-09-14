package com.data2.salmon.core.engine.inter;

import java.util.Map;

/**
 * @author data2
 */
public interface OriginOperation {
    void getTrans();

    OriginOperation select(String id);

    OriginOperation insert(String id);

    OriginOperation update(String id);

    OriginOperation delete(String id);

    OriginOperation param(Map<String, Object> obj);

    OriginOperation param(Object obj);

    Object execute(Object object);

    Object execute();

    String returnInfo();

}
