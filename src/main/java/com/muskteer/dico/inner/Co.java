package com.muskteer.dico.inner;

import java.util.Map;

import com.muskteer.dico.config.CoConfig;

public interface Co {

    void getTrans();

    Co select(String id);

    Co insert(String id);

    Co update(String id);

    Co delete(String id);

    Co param(Map<?, ?> obj);

    Object execute();

    CoConfig getConfig();
}
