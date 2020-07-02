package com.data2.salmon;

import com.data2.salmon.common.util.ArrUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

public abstract class RouteStrategy implements RouteStrategyMaster {

    protected Pair partionPair = new Pair();

    protected String locatePartionVal(Map<?, ?> map) {
        String partionVal = null;
        Object obj = getValueIgnoreCase(map, partionPair.getKey());
        if (obj != null) {
            partionVal = (String) obj;
        }
        partionPair.setValue(partionVal);
        return partionVal;

    }

    private Object getValueIgnoreCase(Map<?, ?> map, String key) {
        @SuppressWarnings("unchecked")
        Iterator<String> it = (Iterator<String>) map.keySet().iterator();
        String keyTmp = null;
        while (it.hasNext()) {
            keyTmp = (String) it.next();
            if (ArrUtils.inArray(key, keyTmp.toUpperCase(), keyTmp)) {
                return map.get(keyTmp);
            }
        }

        return map.get(MatchBestStrategy.exec(map, key));
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
