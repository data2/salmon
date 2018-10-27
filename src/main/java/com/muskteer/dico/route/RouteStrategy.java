package com.muskteer.dico.route;

import java.util.Iterator;
import java.util.Map;

import com.muskteer.dico.base.exception.InnerException;
import com.muskteer.dico.config.DicoPair;
import com.muskteer.dico.common.util.ArrUtils;
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

    protected String isNullThrowEX(String str) throws InnerException {
        if (StringUtils.isEmpty(str)) {
            throw new InnerException("partion value is null.");
        }
        return str;
    }

    protected void initPair(String column) {
        partionPair.setKey(column);
    }

}
