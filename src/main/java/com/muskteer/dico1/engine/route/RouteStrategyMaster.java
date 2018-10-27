package com.muskteer.dico1.engine.route;

import java.util.Map;

import com.muskteer.dico1.base.exception.InnerException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws InnerException;

    RouteStrategyMaster config(TableRulerConfig rule) throws InnerException;
}
