package com.muskteer.dico.route;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouterTest {

    @Autowired
    private Router router;

    @Test
    public void config() {
//        TableRulerConfig bean = router.config("UDP_PAYRISK");
//        System.out.println(bean.toString());
    }
}