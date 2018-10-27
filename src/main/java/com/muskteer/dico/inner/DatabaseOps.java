package com.muskteer.dico.inner;

import java.util.Map;

public interface DatabaseOps {

    void getTrans();

    DatabaseOps select(String id);

    DatabaseOps insert(String id);

    DatabaseOps update(String id);

    DatabaseOps delete(String id);

    DatabaseOps param(Map<?, ?> obj);

    Object execute();

}
