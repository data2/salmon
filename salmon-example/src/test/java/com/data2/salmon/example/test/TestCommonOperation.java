package com.data2.salmon.example.test;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.inter.Mapper;
import com.data2.salmon.core.engine.inter.Salmon;
import com.data2.salmon.example.SalmonExampleApplication;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalmonExampleApplication.class)
public class TestCommonOperation {
    @Resource(name = "simpleDao")
    @Mapper(file = "mapper", database = DataBase.jdbc, name = "simpleDao")
    public Salmon salmon;

    @Test
    public void test() throws SalmonException, SQLException {
        System.out.println(salmon.select("easy").execute());
    }

    @Test
    public void qry() throws SalmonException, SQLException {
        System.out.println((Map) salmon.select("query").execute(Collections.singletonMap("id", "101a7cb6-0aff-11ec-b26b-88e9fe840b9a")));
    }

    @Test
    public void qry2() throws SalmonException, SQLException {
        System.out.println((Map) salmon.select("query2").execute("101a7cb6-0aff-11ec-b26b-88e9fe840b9a"));
    }

    @Test
    public void insert() throws SalmonException, SQLException {
        Map param = Maps.newConcurrentMap();
        param.put("id", UUID.randomUUID().toString());
        param.put("namespace", "test");
        param.put("path", "spring.port");
        param.put("value", "9091");
        salmon.insert("insert").execute(param);
    }

    @Test
    public void insertTrans() throws SalmonException, SQLException {

        salmon.startTrans();
        Map param = Maps.newConcurrentMap();
        param.put("id", UUID.randomUUID().toString());
        param.put("namespace", "test");
        param.put("path", "spring.port");
        param.put("value", "9091");
        salmon.insertTrans("insert").execute(param);

        Map param2 = Maps.newConcurrentMap();
        param2.put("id", UUID.randomUUID().toString());
        param2.put("namespace", "test");
        param2.put("path", "spring.port");
        param2.put("value", "9092");
        salmon.insertTrans("insert").execute(param2);

        salmon.commitTrans();

    }
}
