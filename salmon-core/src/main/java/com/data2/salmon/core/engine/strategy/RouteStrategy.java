package com.data2.salmon.core.engine.strategy;

import com.data2.salmon.core.common.util.ArrUtils;
import com.data2.salmon.core.engine.enums.Pair;
import com.data2.salmon.core.engine.except.SalmonException;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

public abstract class RouteStrategy implements RouteStrategyMaster {

    protected Pair partionPair = new Pair();

    protected String locatePartionVal(Object params) {
        String partionVal = null;
        Object obj = getValueIgnoreCase(params, partionPair.getKey());
        if (obj != null) {
            partionVal = (String) obj;
        }
        partionPair.setValue(partionVal);
        return partionVal;

    }

    private Object getValueIgnoreCase(Object params, String key) {
        if (params instanceof Map) {
            Map paramMap = (Map) params;
            Iterator<String> it = (Iterator<String>) paramMap.keySet().iterator();
            String keyTmp;
            while (it.hasNext()) {
                keyTmp = it.next();
                if (ArrUtils.inArray(key, keyTmp.toUpperCase(), keyTmp)) {
                    return paramMap.get(keyTmp);
                }
            }
            return paramMap.get(MatchBestStrategy.exec(paramMap, key));
        } else {
            return params;
        }
    }

    protected String isNullThrowEX(String str) throws SalmonException {
        if (StringUtils.isEmpty(str)) {
            throw new SalmonException("partion value is null.");
        }
        return str;
    }

    protected void initPair(String column) {
        partionPair.setKey(column);
    }

}
