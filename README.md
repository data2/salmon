![](https://img.shields.io/github/license/data2/dico?style=plastic)
![](https://img.shields.io/github/issues/data2/dico?style=plastic)


dico , an easy framework which support distributed db system.
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

