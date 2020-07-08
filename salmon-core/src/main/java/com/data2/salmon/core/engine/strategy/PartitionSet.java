package com.data2.salmon.core.engine.strategy;

import com.google.common.collect.Maps;

import java.util.HashMap;

/**
 * @author leewow
 */
public class PartitionSet {

    static HashMap<Strategy, Class> strategy = Maps.newHashMap();

    static {
        strategy.put(Strategy.HASH, HashStrategyRouter.class);
        strategy.put(Strategy.NORMAL, NormalStrategyRouter.class);
    }

    public static Class<?> getParseClass(String name) {
        return strategy.get(Strategy.valueOf(name.toUpperCase()));
    }


}
