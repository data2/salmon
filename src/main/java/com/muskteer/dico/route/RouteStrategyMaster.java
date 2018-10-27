package com.muskteer.dico.route;

import java.util.Map;

import com.muskteer.dico.common.exception.InnerException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws InnerException;

    RouteStrategyMaster config(TableRulerConfig rule) throws InnerException;
}
