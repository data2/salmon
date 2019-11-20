package com.data2.salmon;

import java.util.Map;

public interface Salmon {

    void getTrans();

    Salmon select(String id);

    Salmon insert(String id);

    Salmon update(String id);

    Salmon delete(String id);

    Salmon param(Map<?, ?> obj);

    Object execute();

    String returnInfo();

}
