Salmon
====
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Coverage Status](https://coveralls.io/repos/github/data2/salmon/badge.svg)](https://coveralls.io/github/data2/salmon)

Salmon, an easy framework which support distributed db system.

Self-developed and controllable distributed database framework
+ Simplify dao layer development, more controllable and flexible than mybatis
+ Support regular transactions, cross-database transactions based on two-phase protocol commit and retry mechanism
+ Support multiple strategies for sub-database sub-table
+ Support mainstream databases such as jdbc and oracle
+ Seamless connection with spring

### Inquire 

```java
@Autowired
@Mapper(file = "mapper1", database = DataBase.JDBC)
public Salmon salmon;

//Inquire
Map map = (Map) salmon.select("query2").execute("101a7cb6-0aff-11ec-b26b-88e9fe840b9a");

//Inquire
Map map = (Map) salmon.select("query").execute(Collections.singletonMap("id", "101a7cb6-0aff-11ec-b26b-88e9fe840b9a"));

```

### Insert

```
// insert
Map param3 = Maps.newConcurrentMap();
param3.put("id", UUID.randomUUID().toString());
param3.put("namespace", "test");
param3.put("path", "spring.port");
param3.put("value", "9093");
salmon.insert("insert").execute(param);

```

### Affairs

```
//Open the transaction

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

### Configuration

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
```
