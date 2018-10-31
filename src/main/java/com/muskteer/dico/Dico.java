package com.muskteer.dico;

import java.util.Map;

public interface Dico {

    void getTrans();

    Dico select(String id);

    Dico insert(String id);

    Dico update(String id);

    Dico delete(String id);

    Dico param(Map<?, ?> obj);

    Object execute();

}
