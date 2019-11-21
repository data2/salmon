salmon
====
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/data2/dico.svg?branch=master)](https://travis-ci.org/data2/dico)
[![Coverage Status](https://coveralls.io/repos/github/bingoohuang/eql/badge.svg?branch=master)](https://coveralls.io/github/bingoohuang/eql?branch=master)

salmon , an easy framework which support distributed db system.
```java
@Autowired
@Mapper(file = "mapper1", database = "PARTITION")
public Dico testDico;
```        
    spring:
      dico:
        scan:package
        database:
          jdbc:
            url: jdbc:mysql://ip:3306/udp
            username: username
            password: pwdd
            
     --<test>
        select 1 from dual

