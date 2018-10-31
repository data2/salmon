package com.muskteer.dico;

import java.util.Map;

public class Parser {

    private static Object newObject(@SuppressWarnings("rawtypes") Class cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String parse(TableRulerConfig rule, Map<?, ?> params) throws DicoException {
        Class<? extends RouteStrategyMaster> cls = (Class<? extends RouteStrategyMaster>) PartitionSet
                .getParseClass(rule.getPartionMethod());
        RouteStrategyMaster master = (RouteStrategyMaster) (cls != null ? newObject(cls) : null);
        return master.config(rule).route(params);
    }

}
