package com.data2.salmon.core.engine.strategy;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.except.SalmonException;

import java.util.Map;

/**
 * @author leewow
 */
public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws SalmonException;

    RouteStrategyMaster config(TableConfig rule) throws SalmonException;
}
