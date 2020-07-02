package com.data2.salmon;

import java.util.Map;

/**
 * @author leewow
 */
public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws SalmonException;

    RouteStrategyMaster config(TableConfig rule) throws SalmonException;
}
