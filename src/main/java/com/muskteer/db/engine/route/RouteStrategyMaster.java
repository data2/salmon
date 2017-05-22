package com.muskteer.db.engine.route;

import java.util.Map;

import com.muskteer.db.base.exception.InnerException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws InnerException;

    RouteStrategyMaster config(TableRulerBean rule) throws InnerException;
}
