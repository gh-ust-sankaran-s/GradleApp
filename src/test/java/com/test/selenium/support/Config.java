package com.test.selenium.support;

public class Config {
    private Config(){}

    public static String baseUrl(){
        return System.getProperty("baseUrl","http://localhost:5173").replaceAll("/$","");
    }

    public static String Catalog(){
        return baseUrl() + "/catalog";
    }
    public static String home()
    {
        return baseUrl()+"/home";
    }

    public static boolean headless(){
        return Boolean.parseBoolean(System.getProperty("headless", String.valueOf(Boolean.parseBoolean(System.getenv("CI")))));
    }

}
