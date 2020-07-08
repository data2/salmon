package com.data2.salmon.core.engine.config;

import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.strategy.PartitionSet;
import com.data2.salmon.core.engine.strategy.RouteStrategyMaster;

/**
 * @author leewow
 */
public class Parser {

    private static Object newObject(@SuppressWarnings("rawtypes") Class cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String parse(TableConfig rule, Object params) throws SalmonException {
        Class<? extends RouteStrategyMaster> cls = (Class<? extends RouteStrategyMaster>) PartitionSet
                .getParseClass(rule.getPartionMethod());
        RouteStrategyMaster master = (RouteStrategyMaster) (cls != null ? newObject(cls) : null);
        return master.config(rule).route(params);
    }

}
