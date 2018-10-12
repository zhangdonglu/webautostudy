package com.browser.start;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ChromeStart {
//外界传入  driver
    public static void startBrowser(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        全局等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
