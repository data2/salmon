package com.muskteer.dico.engine.parser;

import java.util.Map;

import com.muskteer.dico.base.exception.InnerException;
import com.muskteer.dico.engine.route.RouteStrategyMaster;
import com.muskteer.dico.engine.route.TableRulerConfig;
import com.muskteer.dico.engine.route.PartitionSet;

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
