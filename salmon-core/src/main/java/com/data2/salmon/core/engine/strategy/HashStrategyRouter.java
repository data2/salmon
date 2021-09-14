package com.data2.salmon.core.engine.strategy;

import com.data2.salmon.core.engine.config.TableConfig;
import com.data2.salmon.core.engine.except.SalmonException;
import org.springframework.util.StringUtils;

/**
 * @author data2
 */
public class HashStrategyRouter extends RouteStrategy {
    private int start;
    private int len;
    private int cluster;

    @Override
    public String route(Object params) throws SalmonException {
        String val = locatePartionVal(params);
        if (StringUtils.isEmpty(val)) {
            throw new SalmonException("do not find partion val.");
        }
        String tem;
        if (start > val.length()) {
            throw new SalmonException("partion parse exception.");
        }
        if (start == val.length() && len != 1) {
            throw new SalmonException("partion parse exception.");
        }
        if (len > val.length()) {
            throw new SalmonException("partion parse exception.");
        }
        if ((start + len) > (val.length() + 1)) {
            throw new SalmonException("partion parse exception.");
        }
        tem = val.substring(start, start + len);
        return (tem.hashCode() % cluster) + "";
    }

    @Override
    public HashStrategyRouter config(TableConfig rule) throws SalmonException {
        String[] params = rule.getParams();
        if (params.length != 3) {
            throw new SalmonException("back hash partion route needs three param");
        }
        this.start = Integer.parseInt(isNullThrowEX(params[0]));
        if (start < 0) {
            throw new SalmonException("back hash firsr param start should be >= 0");
        }
        this.len = Integer.parseInt(isNullThrowEX(params[1]));
        if (len <= 0) {
            throw new SalmonException("back hash second param start should be > 0");
        }
        this.cluster = Integer.parseInt(isNullThrowEX(params[2]));
        if (cluster <= 0) {
            throw new SalmonException("back hash third param start should be > 0");
        }
        initPair(rule.getColumn());
        return this;
    }

}
