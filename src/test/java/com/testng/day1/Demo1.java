package com.testng.day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;
//     浏览器    后退/前进   案例
//  浏览器后退： driver.navigate().back();
//  浏览器前进： driver.navigate().forward();
public class Demo1 {
//    浏览器常用操作
    @Test    //浏览器后退：   driver.navigate().back();
    public void FirefoxBack() throws InterruptedException {
//        1、打开firefox浏览器
        System.setProperty("webdriver.gecko.driver","E:\\ideaProject\\drivers\\geckodriver.exe");
//        2、创建火狐浏览器
        WebDriver w1 = new FirefoxDriver();
        Thread.sleep(6000);
//        3、浏览器中输入百度
        w1.get("https://www.baidu.com");
//       或者： w1.navigate().to("https://www.baidu.com");
//        4、线程等待5秒
        Thread.sleep(5000);
        w1.navigate().to("http://news.baidu.com/");
        Thread.sleep(3000);
//        5、浏览器后退
         w1.navigate().back();
         Thread.sleep(6000);
//        6、关闭浏览器
         w1.quit();
    }
//    Chrome浏览器后退
    @Test
    public void ChromeBack() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        WebDriver w2 = new ChromeDriver();
        Thread.sleep(8000);
        w2.navigate().to("https://www.baidu.com");
        Thread.sleep(8000);
        w2.navigate().back();
        Thread.sleep(6000);
//        浏览器   前进
        w2.navigate().forward();
        Thread.sleep(6000);
        w2.quit();
    }
     @Test
         public void IEExpoloerBack() throws InterruptedException {
         System.setProperty("webdriver.ie.driver","E:\\ideaProject\\drivers\\IEDriverServer.exe");
         WebDriver w3=new InternetExplorerDriver();
         w3.get("https://www.baidu.com");
         Thread.sleep(6000);
         w3.navigate().back();
         Thread.sleep(6000);
         //浏览器   前进
         w3.navigate().forward();
         Thread.sleep(6000);
         w3.quit();

    }
}
