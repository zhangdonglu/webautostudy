package com.testng.day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class OpenWebersoer {
    @Test
    public void OpenFirefoxWebrowser(){
//       有些火狐浏览器版本，自带了驱动，因此不用加载驱动
//      如果火狐浏览器通过文件路径不能打开，就是用驱动。 前面的格式：webdriver.gecko.driver
        System.setProperty("webdriver.gecko.driver","E:\\ideaProject\\drivers\\geckodriver.exe");
        WebDriver w1= new FirefoxDriver();
        w1.get("https://www.baidu.com");
        w1.quit();
    }
    @Test
    public void OpenChromeWebrowser(){
//    打开Chrome浏览器，需要驱动，和火狐浏览器设置方法类似
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        WebDriver w2 = new ChromeDriver();
        w2.get("https://www.baidu.com");
        w2.quit();
    }
    @Test
    public void OpenIEWebrowser() throws InterruptedException {
//        打开IE浏览器
        System.setProperty("webdriver.ie.driver","E:\\ideaProject\\drivers\\IEDriverServer.exe");
        WebDriver w3= new InternetExplorerDriver();
        w3.get("https://www.baidu.com");
        Thread.sleep(3000);    //线程等待3秒 ，抛出
        w3.quit();     //关闭所有窗口，并退出浏览器
//      w3.close();    关闭当前窗口，注意：只是窗口，不是关闭整个浏览器
    }

}
