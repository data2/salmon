package com.muskteer.db.engine.inner;

import java.util.HashMap;

import com.muskteer.db.engine.factory.Factory;
import com.muskteer.db.engine.inner.co.Co;
import com.muskteer.db.engine.inner.co.impl.Dico;

public class Test {

    public static void main(String[] args) {
        Dico c = new Dico();
        Co co = Factory.getSource(c);
        // HashMap map = new HashMap();
        // map.put("SERVICEORDERID", "1234567891");
        // map.put("RISKTYPE", "20");
        // map.put("OCCURTIME", new Date());
        // map.put("RISKDETAIL", "无风险");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("SERVICEORDERID", "1234567890");

        Object obj = co.select("queryUdpPayRisk").param(map).execute();
        System.out.println(obj);
    }
}
