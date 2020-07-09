package com.data2.salmon.core.engine.intercept;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author leewow
 */
@Slf4j
public class ParamsInterceptor implements CutPointInterceptor {

    @Override
    public void execute(Object obj) {
        log.info("[{}] execute sql start.", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
