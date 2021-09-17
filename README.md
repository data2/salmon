Salmon
====
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/data2/salmon.svg?branch=master)](https://travis-ci.org/data2/salmon)
[![Coverage Status](https://coveralls.io/repos/github/data2/salmon/badge.svg)](https://coveralls.io/github/data2/salmon)

Salmon , an easy framework which support distributed db system.

```java
@Autowired
@Mapper(file = "mapper1", database = DataBase.PARTITION)
public Salmon salmon;
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

