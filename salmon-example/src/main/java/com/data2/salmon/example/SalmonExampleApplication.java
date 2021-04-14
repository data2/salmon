package com.data2.salmon.example;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.inter.Mapper;
import com.data2.salmon.core.engine.inter.Salmon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * @author leewow
 */
@SpringBootApplication(scanBasePackages = "com.data2")
@RestController
public class SalmonExampleApplication {

    /**
     * this can also work
     *
     * @Autowired(required = false)
     * @Qualifier("simpleDao")
     * @Mapper(file = "mapper", database = DataBase.JDBC, name = "simpleDap")
     */
    @Resource(name = "simpleDao")
    @Mapper(file = "mapper", database = DataBase.jdbc, name = "simpleDao")
    public Salmon salmon;

    public static void main(String[] args) {
        SpringApplication.run(SalmonExampleApplication.class, args);
    }

    @RequestMapping("/easy")
    public Object testEasy() {
        return salmon.select("easy").execute();
    }

    @RequestMapping("/single")
    public Object testSingle() {
        Map map = (Map) salmon.select("single").execute(Collections.singletonMap("code", "cityCode"));
        return map;
    }

    @RequestMapping("/single2")
    public Object testSingle2() {
        Map map = (Map) salmon.select("single2").execute("cityCode");
        return map;
    }


}
