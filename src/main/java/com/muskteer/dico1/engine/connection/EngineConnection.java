package com.muskteer.dico1.engine.connection;

import java.sql.Connection;

public interface EngineConnection {
    void iniliazation();

    Connection getConnection();
}
