Salmon
====
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/data2/salmon.svg?branch=master)](https://travis-ci.org/data2/salmon)
[![Coverage Status](https://coveralls.io/repos/github/data2/salmon/badge.svg)](https://coveralls.io/github/data2/salmon)

Salmon , an easy framework which support distributed db system.
```java
@Autowired
@Mapper(file = "mapper1", database = DataBase.JDBC)
public Salmon salmon;

//查询
Map map = (Map) salmon.select("query2").execute("101a7cb6-0aff-11ec-b26b-88e9fe840b9a");

//查询
Map map = (Map) salmon.select("query").execute(Collections.singletonMap("id", "101a7cb6-0aff-11ec-b26b-88e9fe840b9a"));

// 插入
Map param3 = Maps.newConcurrentMap();
param3.put("id", UUID.randomUUID().toString());
param3.put("namespace", "test");
param3.put("path", "spring.port");
param3.put("value", "9093");
salmon.insert("insert").execute(param);

//开启事务

salmon.startTrans();
Map param = Maps.newConcurrentMap();
param.put("id", UUID.randomUUID().toString());
param.put("namespace", "test");
param.put("path", "spring.port");
param.put("value", "9091");
salmon.insertTrans("insert").execute(param);

Map param2 = Maps.newConcurrentMap();
param2.put("id", UUID.randomUUID().toString());
param2.put("namespace", "test");
param2.put("path", "spring.port");
param2.put("value", "9092");
salmon.insertTrans("insert").execute(param2);

salmon.commitTrans();


```        
    spring:
      salmon:
        scan:package
        database:
          jdbc:
            url: jdbc:mysql://ip:3306/udp
            username: username
            password: pwd
            
     --<test>
        select 1 from dual

