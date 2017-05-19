package com.barnett.db.config;

public class DBaseKeys {
    public static String classType = "database";
    private static String url = "url";
    private static String mapping = "mapping";
    private static String username = "username";
    private static String password = "password";

    public static String getUrl() {
        return classType.concat(".").concat(url);
    }

    public static String getUsername() {
        return classType.concat(".").concat(username);
    }

    public static String getPassword() {
        return classType.concat(".").concat(password);
    }
    
    public static String getMapping(){
        return classType.concat(".").concat(mapping);
    }
}
