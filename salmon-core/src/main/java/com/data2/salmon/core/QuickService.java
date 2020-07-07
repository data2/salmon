package com.data2.salmon.core;

import com.google.common.collect.Maps;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author leewow
 */
@Data
public abstract class QuickService extends LinkService {

    protected SalmonTrans coTrans;
    protected List<ExecuteSql> sqlArr = new ArrayList<>();
    protected ExecuteSql currSql;
    protected Map<?, ?> currParams;
    protected Map<String, Object> context;
    protected boolean exception = false;


    private void initContext() {
        context = Maps.newConcurrentMap();
        context.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    @Override
    public void getTrans() {
        coTrans = new SalmonTrans();
    }

    @Override
    public QuickService select(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.SELECT, sqlId);
        return this;
    }

    @Override
    public QuickService insert(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.INSERT, sqlId);
        return this;
    }

    @Override
    public QuickService update(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.UPDATE, sqlId);
        return this;
    }

    @Override
    public QuickService delete(String sqlId) {
        this.currSql = new ExecuteSql(OperationKeys.DELETE, sqlId);
        return this;
    }

    @Override
    public QuickService param(Map<?, ?> map) {
        this.currParams = map;
        return this;
    }


    @Override
    public void afterPropertiesSet() {
        initContext();
    }

    @Override
    public String returnInfo() {
        return name + database + file;
    }
}
