package com.muskteer.db.engine.parser;

import java.util.Map;

import com.muskteer.db.base.exception.InnerException;
import com.muskteer.db.engine.route.RouteStrategyMaster;
import com.muskteer.db.engine.route.TableRulerBean;
import com.muskteer.db.engine.route.ThrowDataSet;

public class Parser {

    private static Object newObject(@SuppressWarnings("rawtypes") Class cls) {
        try {
            return cls.newInstance();
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static String parse(TableRulerBean rule, Map<?, ?> params) throws InnerException {
        Class<? extends RouteStrategyMaster> cls = (Class<? extends RouteStrategyMaster>) ThrowDataSet
                .getParseClass(rule.getPartionMethod());
        RouteStrategyMaster master = (RouteStrategyMaster) (cls != null ? newObject(cls) : null);
        return master.config(rule).route(params);
    }

}
