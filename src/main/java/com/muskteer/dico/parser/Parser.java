package com.muskteer.dico.parser;

import java.util.Map;

import com.muskteer.dico.common.exception.InnerException;
import com.muskteer.dico.route.RouteStrategyMaster;
import com.muskteer.dico.route.TableRulerConfig;
import com.muskteer.dico.route.PartitionSet;

public class Parser {

    private static Object newObject(@SuppressWarnings("rawtypes") Class cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String parse(TableRulerConfig rule, Map<?, ?> params) throws InnerException {
        Class<? extends RouteStrategyMaster> cls = (Class<? extends RouteStrategyMaster>) PartitionSet
                .getParseClass(rule.getPartionMethod());
        RouteStrategyMaster master = (RouteStrategyMaster) (cls != null ? newObject(cls) : null);
        return master.config(rule).route(params);
    }

}
