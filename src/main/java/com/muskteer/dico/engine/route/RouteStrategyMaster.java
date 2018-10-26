package com.muskteer.dico.engine.route;

import java.util.Map;

import com.muskteer.dico.base.exception.InnerException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws InnerException;

    RouteStrategyMaster config(TableRulerBean rule) throws InnerException;
}
