package com.barnett.db.engine.route;

import java.util.Map;

import com.barnett.db.base.exception.InnerException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws InnerException;

    RouteStrategyMaster config(TableRulerBean rule) throws InnerException;
}
