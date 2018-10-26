package com.muskteer.dico.engine.inner.trans;

public interface Transaction {

    void start();

    void commit();
}
