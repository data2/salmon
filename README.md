
DICO , an easy framework which support distributed db system.
    
    @Autowired
    @Mapper(file = "mapper1", database = "PARTITION")
    public Dico testDico;
        
    spring:
      dico:
        scan:package
        database:
          jdbc:
            url: jdbc:mysql://10.40.32.14:3306/udp
            username: udp
            password: udp2016
            
      --<test>
        select 1 from dual
