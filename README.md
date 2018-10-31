
DICO , an easy framework which support distributed db system.

    in component
    
    @Autowired
    @Mapper(file = "mapper1", database = "PARTITION")
    public Dico testDico;
    
    in config
    
    spring:
      dico:
        scan:package
        database:
          jdbc:
            url: jdbc:mysql://10.40.32.14:3306/udp
            username: udp
            password: udp2016
