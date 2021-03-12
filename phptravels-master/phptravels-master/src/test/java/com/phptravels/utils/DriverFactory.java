package com.phptravels.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.io.File;

public class DriverFactory {
    private static WebDriver driverInstance;

    public static WebDriver getDriver(){
        if(driverInstance==null) {
            File driverExe = new File("C:\\Users\\laptop\\IdeaProjects\\phptravels\\src\\test\\resources\\executables\\drivers\\chromedriver.exe");
            ChromeDriverService driverService = new ChromeDriverService.Builder()
                                                                        .usingDriverExecutable(driverExe)
                                                                        .usingAnyFreePort().build();
            driverInstance = new ChromeDriver(driverService);
            driverInstance.manage().window().maximize();
        }
        return driverInstance;
    }

    public static void resetDriver() {
        driverInstance = null;
    }
}
