package com.muskteer.dico.controller;

import com.muskteer.dico.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppControllerTest {
    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public String test() {
        return testService.test();
    }
}
