package com.data2.salmon.core.engine.strategy;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.except.SalmonException;

/**
 * @author data2
 */
public interface RouteStrategyMaster {

    String route(Object params) throws SalmonException;

    RouteStrategyMaster config(TableConfig rule) throws SalmonException;
}
