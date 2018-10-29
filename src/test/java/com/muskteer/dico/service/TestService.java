package com.muskteer.dico.service;

import com.muskteer.dico.inner.Dico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestService {

    @Autowired
    public Dico dico;

    public String test() {
        Map m = new HashMap<>();
        m.put("payorderid","45678345678905679");
        System.out.println(dico.select("test").param(m).execute());
        return "success" ;
    }
}
