package com.muskteer.dico;

import java.util.Iterator;
import java.util.Map;

import org.springframework.util.StringUtils;

public abstract class RouteStrategy implements RouteStrategyMaster {

    protected DicoPair partionPair = new DicoPair();

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

    protected String isNullThrowEX(String str) throws DicoException {
        if (StringUtils.isEmpty(str)) {
            throw new DicoException("partion value is null.");
        }
        return str;
    }

    protected void initPair(String column) {
        partionPair.setKey(column);
    }

}
