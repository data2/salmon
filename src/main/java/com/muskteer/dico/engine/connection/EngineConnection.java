package com.muskteer.dico.engine.connection;

import java.sql.Connection;

public interface EngineConnection {
    void iniliazation();

    Connection getConnection();
}
