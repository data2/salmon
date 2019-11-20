package com.data2.salmon;

import java.util.Map;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws SalmonException;

    RouteStrategyMaster config(TableRulerConfig rule) throws SalmonException;
}
