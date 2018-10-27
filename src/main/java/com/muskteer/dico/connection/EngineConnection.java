package com.muskteer.dico.connection;

import java.sql.Connection;

public interface EngineConnection {
    void iniliazation();

    Connection getConnection();
}
