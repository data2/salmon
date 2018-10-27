package com.muskteer.dico.engine.inner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.muskteer.dico.engine.config.DBaseKeys;
import com.muskteer.dico.engine.config.DicoConfig;
import com.muskteer.dico.engine.parser.ParseConfig;
import com.muskteer.dico.engine.factory.BuildFactory;
import com.muskteer.dico.common.util.DicoClassLoader;

/**
 * execute engine.
 */
public class Dico implements Co {

    protected DicoConfig dicoConfig;
    protected DicoTrans coTrans;
    protected List<DicoSql> sqlArr = new ArrayList<DicoSql>();
    protected DicoSql currSql;
    protected Map<?, ?> currParams;
    protected String classloaderfile;
    protected Map<String, Object> context;
    protected boolean exception = false;

    public Dico() {
        this.dicoConfig = new DicoConfig(DBaseKeys.DEFAULT_DICO_FILE);
        initContext();
    }

    public Dico(DicoConfig DicoConfig) {
        this.dicoConfig = DicoConfig;
        initContext();
    }

    private void initContext() {
        context = new HashMap<String, Object>();
        context.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        classloaderfile = getDicoFile();
    }

    private String getDicoFile() {
        return DicoClassLoader.getCurrClassName().replaceAll("\\.", "/").concat(".co");
    }

    public void getTrans() {
        coTrans = new DicoTrans();
    }

    public Dico select(String sqlId) {
        this.currSql = new DicoSql("select", sqlId);
        return this;
    }

    public Dico insert(String sqlId) {
        this.currSql = new DicoSql("insert", sqlId);
        return this;
    }

    public Dico update(String sqlId) {
        this.currSql = new DicoSql("update", sqlId);
        return this;
    }

    public Dico delete(String sqlId) {
        this.currSql = new DicoSql("delete", sqlId);
        return this;
    }

    public Dico param(Map<?, ?> map) {
        this.currParams = map;
        return this;
    }

    public Object execute() {
        try {
            // find the sql in the file.
            String sql = ParseConfig.parse(classloaderfile, currSql);
            currSql.setSql(sql);
            // build current dicoSql.
            BuildFactory.build(currSql, currParams);
            // record.
            sqlArr.add(currSql);
            // sql exec.
            return currSql.exec();
        } catch (Exception e) {
            exception = true;
        }
        return classloaderfile;

    }

    public String getClassloaderfile() {
        return classloaderfile;
    }

    public DicoSql getCurrSql() {
        return currSql;
    }

    public DicoConfig getConfig() {
        return dicoConfig;
    }

}
