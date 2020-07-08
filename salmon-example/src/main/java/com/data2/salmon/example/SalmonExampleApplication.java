package com.data2.salmon.example;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.inter.Mapper;
import com.data2.salmon.core.engine.inter.Salmon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author leewow
 */
@SpringBootApplication(scanBasePackages = "com.data2")
@RestController
public class SalmonExampleApplication {

//    @Autowired(required = false)
//    @Mapper(file = "mapper1", database = DataBase.PARTITION, name = "salman_partition_dao")
//    public Salmon salmon;

    @Autowired(required = false)
    @Mapper(file = "mapper1", database = DataBase.JDBC, name = "salman_jdbc_dao")
    public Salmon salmon2;

    public static void main(String[] args) {
        SpringApplication.run(SalmonExampleApplication.class, args);

    }

    @RequestMapping("/")
    public void test() {
        System.out.println("test....");
        Object obj2 = salmon2.select("test")
                .param(Collections.singletonMap("articleID", "243992049376706560")).execute();
        System.out.println(obj2);
    }

    @RequestMapping("/test2")
    public void test2() {
        System.out.println("test2....");
        Object obj = salmon2.select("easy").execute();
        System.out.println(obj);
    }


}
