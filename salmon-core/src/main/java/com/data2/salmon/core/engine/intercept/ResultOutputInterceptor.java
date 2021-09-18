package com.data2.salmon.core.engine.intercept;

import com.data2.salmon.core.engine.inter.QuickService;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author data2
 */
@Slf4j
public class ResultOutputInterceptor implements CutPointInterceptor {

    @Override
    public void execute(Object obj) {
//        log.info("[{}]execute sql end, sql content:{}",
//                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), ((QuickService) obj).getCurrSql().get().getSql());
    }

}
