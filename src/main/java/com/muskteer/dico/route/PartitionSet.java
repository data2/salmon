package com.muskteer.dico.route;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class PartitionSet {

    static HashMap<String, Class> strategy = Maps.newHashMap();

    static {
        strategy.put("hash", HashStrategyRouter.class);
        strategy.put("normal", NormalStrategyRouter.class);
    }

    public static Class<?> getParseClass(String name) {
        return strategy.get(name);
    }
}
