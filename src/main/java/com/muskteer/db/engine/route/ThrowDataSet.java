package com.muskteer.db.engine.route;

import java.util.HashMap;

import com.google.common.collect.Maps;
import com.muskteer.db.engine.route.impl.HashStrategyRouter;
import com.muskteer.db.engine.route.impl.NormalStrategyRouter;

public class ThrowDataSet {

    @SuppressWarnings("rawtypes")
    static HashMap<String, Class> strategy = Maps.newHashMap();

    static {
        strategy.put("hash", HashStrategyRouter.class);
        strategy.put("normal", NormalStrategyRouter.class);
    }

    public static Class<?> getParseClass(String name) {
        return strategy.get(name);
    }
}
