package com.data2.salmon.example;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.inter.Mapper;
import com.data2.salmon.core.engine.inter.Salmon;
import com.google.common.collect.Maps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * @author data2
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
    public Object testEasy() throws SalmonException, SQLException {
        return salmon.select("easy").execute();
    }

    @RequestMapping("/query")
    public Object query() throws SalmonException, SQLException {
        Map map = (Map) salmon.select("query").execute(Collections.singletonMap("id", "101a7cb6-0aff-11ec-b26b-88e9fe840b9a"));
        return map;
    }

    @RequestMapping("/query2")
    public Object query2() throws SalmonException, SQLException {
        Map map = (Map) salmon.select("query2").execute("101a7cb6-0aff-11ec-b26b-88e9fe840b9a");
        return map;
    }

    @RequestMapping("/insert")
    public void insert() throws SalmonException, SQLException {

        salmon.startTrans();
        Map param = Maps.newConcurrentMap();
        param.put("id", UUID.randomUUID().toString());
        param.put("namespace", "test");
        param.put("path", "spring.port");
        param.put("value", "9091");
        salmon.insertTrans("insert").execute(param);

        Map param2 = Maps.newConcurrentMap();
//        param2.put("id", UUID.randomUUID().toString());
        param2.put("namespace", "test");
        param2.put("path", "spring.port");
        param2.put("value", "9092");
        salmon.insertTrans("insert").execute(param2);

        salmon.commitTrans();

        Map param3 = Maps.newConcurrentMap();
        param3.put("id", UUID.randomUUID().toString());
        param3.put("namespace", "test");
        param3.put("path", "spring.port");
        param3.put("value", "9093");
        salmon.insert("insert").execute(param);


    }


}
