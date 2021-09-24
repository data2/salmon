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

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * ACID
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalmonExampleApplication.class)
public class TestVerifyTrans {
    @Resource(name = "simpleDao")
    @Mapper(file = "mapper", database = DataBase.jdbc, name = "simpleDao")
    public Salmon salmon;

    /**
     * verify atomic
     * @throws SalmonException
     * @throws SQLException
     */
    @Test
    public void insertTransForAtomic() throws SalmonException, SQLException {

        salmon.startTrans();
        String id = UUID.randomUUID().toString();
        Map param = Maps.newConcurrentMap();
        param.put("id", id);
        param.put("namespace", "test");
        param.put("path", "spring.port");
        param.put("value", "9091");
        salmon.insertTrans("insert").executeTrans(param);

        Map param2 = Maps.newConcurrentMap();
        param2.put("id", id);//主键冲突 事务失败
        param2.put("namespace", "121");
        param2.put("path", "spring.port");
        param2.put("value", "9092");
        salmon.insertTrans("insert").executeTrans(param2);

        salmon.commitTrans();

    }
}
