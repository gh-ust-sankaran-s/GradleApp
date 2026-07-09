package com.test.selenium.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private DriverFactory(){}
    public static WebDriver createChromeDriver()
    {
        return createDriver();
    }
    public static WebDriver createDriver(){
        ChromeOptions options = new ChromeOptions();
        if(Config.headless()){
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--remote-allow-origins=*");
        }
        options.addArguments("--window-size=1440,900");
//        if (Config.gridEnabled()){
//            try{
//                return new RemoteWebDriver(URI.create(Config.gridUrl()).toURL(),options);
//            }
//            catch (MalformedURLException e){
//                throw new IllegalArgumentException("Invalid Selenium Grid URL:" +Config.gridUrl(),e);
//            }
//        }
        return new ChromeDriver(options);
    }
}
