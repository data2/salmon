package com.data2.example;

import com.data2.salmon.BootSalmon;
import com.data2.salmon.Mapper;
import com.data2.salmon.Salmon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@SpringBootApplication
@RestController
@BootSalmon(database = "jdbc")
@ComponentScan(basePackages = {"com.data2"})
public class ExampleApplication {

    @Autowired(required = false)
    @Mapper(file = "mapper1", database = "PARTITION")
    public Salmon salmon;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @RequestMapping("/")
    public void test(){
        System.out.println("test....");
        Object obj = salmon.select("test")
                .param(Collections.singletonMap("articleID","243992049376706560")).execute();
        System.out.println(obj);
    }


}
