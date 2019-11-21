package com.data2.salmon;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class PartitionSet {

    public static Class<?> getParseClass(String name) {
        return strategy.get(Strategy.valueOf(name.toUpperCase()));
    }

    static HashMap<Strategy, Class> strategy = Maps.newHashMap();

    static {
        strategy.put(Strategy.HASH, HashStrategyRouter.class);
        strategy.put(Strategy.NORMAL, NormalStrategyRouter.class);
    }


}
