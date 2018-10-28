package com.muskteer.dico.controller;

import com.muskteer.dico.inner.Dico;
import com.muskteer.dico.inner.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppControllerTest {
    @Autowired
    @Mapper(file = "AppControllerTest")
    private Dico dico;

    @RequestMapping("/test")
    public String test() {
        return (String) dico.select("test").execute();
    }
}
