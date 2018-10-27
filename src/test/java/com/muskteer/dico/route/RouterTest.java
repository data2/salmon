package com.muskteer.dico.route;

import org.junit.Test;


public class RouterTest {

    @Test
    public void config() {
        TableRulerConfig bean = Router.config("UDP_PAYRISK");
        System.out.println(bean.toString());
    }
}