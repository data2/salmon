package com.muskteer.dico;

import java.sql.Connection;

public interface EngineConnection {
    void iniliazation();

    Connection getConnection();
}
