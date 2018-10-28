package com.muskteer.dico.inner;

import com.google.common.collect.Maps;
import com.muskteer.dico.config.OperationKeys;
import com.muskteer.dico.factory.BuildFactory;
import com.muskteer.dico.parser.ParseConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
public class BootDico extends OriginDico implements InitializingBean, Dico {

    protected DicoTrans coTrans;
    protected List<DicoExecuteSql> sqlArr = new ArrayList<>();
    protected DicoExecuteSql currSql;
    protected Map<?, ?> currParams;
    protected Map<String, Object> context;
    protected boolean exception = false;

    @Autowired
    public BuildFactory buildFactory;
    @Autowired
    private ParseConfig parseConfig;

    private void initContext() {
        context = Maps.newConcurrentMap();
        context.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    public void getTrans() {
        coTrans = new DicoTrans();
    }

    public BootDico select(String sqlId) {
        this.currSql = new DicoExecuteSql(OperationKeys.SELECT, sqlId);
        return this;
    }

    public BootDico insert(String sqlId) {
        this.currSql = new DicoExecuteSql(OperationKeys.INSERT, sqlId);
        return this;
    }

    public BootDico update(String sqlId) {
        this.currSql = new DicoExecuteSql(OperationKeys.UPDATE, sqlId);
        return this;
    }

    public BootDico delete(String sqlId) {
        this.currSql = new DicoExecuteSql(OperationKeys.DELETE, sqlId);
        return this;
    }

    public BootDico param(Map<?, ?> map) {
        this.currParams = map;
        return this;
    }

    public Object execute() {
        try {
            System.out.println(this.getClass().getField("Dico").getAnnotation(Mapper.class).file());
            buildFactory.build(currSql.setSql(parseConfig.parse(this.getClass()
                    .getField("Dico").getAnnotation(Mapper.class).file(), currSql)), currParams);
            sqlArr.add(currSql);
            return currSql.exec();
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
        }
        return currSql.getSqlId();

    }

    @Override
    public void afterPropertiesSet() {
        initContext();
    }
}
