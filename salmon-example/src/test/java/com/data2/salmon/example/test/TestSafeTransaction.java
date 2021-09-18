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
    public void test() throws SalmonException, SQLException {

        ThreadPoolExecutor threads = new ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(10));

//        System.out.println(salmon);
//        for (int i = 0; i < 2; i++) {
//            threads.execute(new Run(salmon));
//        }

        threads.execute(new Run(salmon));//异步线程

        new Run(salmon).run();// 当前线程
    }

    static class Run implements Runnable {
        Salmon salmon;

        Run(Salmon salmon) {
            this.salmon = salmon;
        }

        @SneakyThrows
        public void run() {
            salmon.startTrans();
            Map param = Maps.newConcurrentMap();
            param.put("id", UUID.randomUUID().toString());
            param.put("namespace", "test");
            param.put("path", "spring.port");
            param.put("value", "8888" + Thread.currentThread().getId());
            salmon.insertTrans("insert").execute(param);

            Thread.sleep(4000);

            Map param2 = Maps.newConcurrentMap();
            param2.put("id", UUID.randomUUID().toString());
            param2.put("namespace", "test");
            param2.put("path", "spring.port");
            param2.put("value", "8888" + Thread.currentThread().getId());
            salmon.insertTrans("insert").execute(param2);

            salmon.commitTrans();
        }
    }
}
