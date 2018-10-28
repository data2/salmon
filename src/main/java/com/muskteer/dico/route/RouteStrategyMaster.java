package com.muskteer.dico.route;

import java.util.Map;

import com.muskteer.dico.common.exception.DicoException;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws DicoException;

    RouteStrategyMaster config(TableRulerConfig rule) throws DicoException;
}
