package com.muskteer.dico1.engine.route.impl;

import java.util.Map;

import com.muskteer.dico1.base.exception.InnerException;
import com.muskteer.dico1.engine.route.TableRulerConfig;
import org.springframework.util.StringUtils;

import com.muskteer.dico1.engine.route.RouteStrategy;

public class HashStrategyRouter extends RouteStrategy {
    private int start;
    private int len;
    private int cluster;

    @Override
    public String route(Map<?, ?> map) throws InnerException {
        String val = locatePartionVal(map);
        if (StringUtils.isEmpty(val)) {
            throw new InnerException("do not find partion val.");
        }
        String tem = null;
        if (start > val.length()) {
            throw new InnerException("partion parse exception.");
        }
        if (start == val.length() && len != 1) {
            throw new InnerException("partion parse exception.");
        }
        if (len > val.length()) {
            throw new InnerException("partion parse exception.");
        }
        if ((start + len) > (val.length() + 1)) {
            throw new InnerException("partion parse exception.");
        }
        tem = val.substring(start - 1, start - 1 + len);
        return (tem.hashCode() % cluster) + "";
    }

    @Override
    public HashStrategyRouter config(TableRulerConfig rule) throws InnerException {
        String[] params = rule.getParams();
        if (params.length != 3) {
            throw new InnerException("back hash partion route needs three param");
        }
        this.start = Integer.parseInt(isNullThrowEX(params[0]));
        if (start <= 0) {
            throw new InnerException("back hash firsr param start should be > 0");
        }
        this.len = Integer.parseInt(isNullThrowEX(params[1]));
        if (len <= 0) {
            throw new InnerException("back hash second param start should be > 0");
        }
        this.cluster = Integer.parseInt(isNullThrowEX(params[2]));
        if (cluster <= 0) {
            throw new InnerException("back hash third param start should be > 0");
        }
        initPair(rule.getColumn());
        return this;
    }

}
