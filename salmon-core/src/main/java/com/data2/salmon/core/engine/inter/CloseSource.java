package com.data2.salmon.core.engine.inter;

import com.data2.salmon.core.engine.domain.ExecuteSql;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class CloseSource {
    public void close(ExecuteSql executeSql) {
        if (Objects.nonNull(executeSql)) {
            if (Objects.nonNull(executeSql.getConn())) {
                try {
                    executeSql.getConn().close();
                } catch (Exception e) {
                    try {
                        executeSql.getConn().close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        log.error("close connection fail.");
                    }
                }
            }
            if (Objects.nonNull(executeSql.getExecutor())) {
                try {
                    executeSql.getExecutor().close();
                } catch (Exception e) {
                    try {
                        executeSql.getExecutor().close();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        log.error("close PreparedStatement fail.");
                    }
                }
            }

        }
    }
}
