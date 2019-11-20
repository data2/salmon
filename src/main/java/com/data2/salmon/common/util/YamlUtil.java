package com.data2.salmon.common.util;

import com.google.common.collect.Maps;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Map;

public class YamlUtil {
    public static Map<String,Object> yaml(String file){
        Map<String,Object> maps = Maps.newHashMap();
        try{
            Yaml yaml = new Yaml();
            URL url = YamlUtil.class.getClassLoader().getResource(file);
            if (url != null) {
                maps =(Map)yaml.load(new FileInputStream(url.getFile()));
                System.out.println(maps);
            }

        }catch (Exception e){}
        return maps;
    }

}
