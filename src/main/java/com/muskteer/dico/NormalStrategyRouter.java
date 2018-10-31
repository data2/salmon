package com.muskteer.dico;

import java.util.Map;

import org.springframework.util.StringUtils;

public class NormalStrategyRouter extends RouteStrategy {
    private int start;
    private int len;
    private int cluster;

    @Override
    public String route(Map<?, ?> map) throws DicoException {
        String val = locatePartionVal(map);
        if (StringUtils.isEmpty(val)) {
            throw new DicoException("do not find partion val.");
        }
        String tem = null;
        try {
            if (start > val.length()) {
                throw new DicoException("partion parse exception.");
            }
            if (start == val.length() && len != 1) {
                throw new DicoException("partion parse exception.");
            }
            if (len > val.length()) {
                throw new DicoException("partion parse exception.");
            }
            if ((start + len) > (val.length() + 1)) {
                throw new DicoException("partion parse exception.");
            }
            tem = val.substring(start - 1, start - 1 + len);
        } catch (Exception e) {
            tem = val;
        }
        return tem.hashCode() % cluster + "";
    }

    @Override
    public NormalStrategyRouter config(TableRulerConfig rule) throws DicoException {
        String[] params = rule.getParams();
        if (params.length != 3) {
            throw new DicoException("back hash partion route needs three param");
        }
        this.start = Integer.parseInt(isNullThrowEX(params[0]));
        if (start <= 0) {
            throw new DicoException("back hash firsr param start should be > 0");
        }
        this.len = Integer.parseInt(isNullThrowEX(params[1]));
        if (len <= 0) {
            throw new DicoException("back hash second param start should be > 0");
        }
        this.cluster = Integer.parseInt(isNullThrowEX(params[0]));
        if (cluster <= 0) {
            throw new DicoException("back hash third param start should be > 0");
        }
        initPair(rule.getColumn());
        return this;
    }

}