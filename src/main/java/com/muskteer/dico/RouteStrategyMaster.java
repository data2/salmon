package com.muskteer.dico;

import java.util.Map;

public interface RouteStrategyMaster {

    String route(Map<?, ?> map) throws DicoException;

    RouteStrategyMaster config(TableRulerConfig rule) throws DicoException;
}
