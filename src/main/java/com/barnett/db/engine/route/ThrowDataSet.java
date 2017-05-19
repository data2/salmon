package com.barnett.db.engine.route;

import java.util.HashMap;

import com.barnett.db.engine.route.impl.HashStrategyRouter;
import com.barnett.db.engine.route.impl.NormalStrategyRouter;
import com.google.common.collect.Maps;

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
