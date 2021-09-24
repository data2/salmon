package com.data2.salmon.example.test;

import com.data2.salmon.core.engine.enums.DataBase;
import com.data2.salmon.core.engine.except.SalmonException;
import com.data2.salmon.core.engine.inter.Mapper;
import com.data2.salmon.core.engine.inter.Salmon;
import com.data2.salmon.example.SalmonExampleApplication;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalmonExampleApplication.class)
public class TestSafeTransaction {

    @Resource(name = "simpleDao")
    @Mapper(file = "mapper", database = DataBase.jdbc, name = "simpleDao")
    public Salmon salmon;

    @Test
    public void test() throws SalmonException, SQLException, InterruptedException {

        ThreadPoolExecutor threads = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
        for (int i = 0; i < 10; i++) {
            threads.execute(new Run(salmon, i));//异步线程
        }
        new Run(salmon,100).run();// 当前线程

        Thread.sleep(400000);
    }

    static class Run implements Runnable {
        Salmon salmon;
        int random;

        Run(Salmon salmon, int random) {
            this.salmon = salmon;
            this.random = random;
        }

        @SneakyThrows
        public void run() {
            salmon.startTrans();
            Map param = Maps.newConcurrentMap();
            param.put("id", UUID.randomUUID().toString());
            param.put("namespace", "test");
            param.put("path", "spring.port");
            param.put("value", random);
            salmon.insertTrans("insert").executeTrans(param);

            Thread.sleep(2000);

            Map param2 = Maps.newConcurrentMap();
            param2.put("id", UUID.randomUUID().toString());
            param2.put("namespace", "test");
            param2.put("path", "spring.port");
            param2.put("value", random);
            salmon.insertTrans("insert").executeTrans(param2);

            salmon.commitTrans();
        }
    }
}
