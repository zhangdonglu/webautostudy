package com.testng.day1;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//    浏览器常用操作：
//           1、浏览器刷新：driver.navigate().refresh();
//           2、浏览器最大化： driver.navigate().window().maximize();
//           3、浏览器设置大小： Dimension dimension = new Dimension(900,800);
//                              driver.manage().window().setSize(dimension);
//           4、获取当前页面URL:
//                          driver.getCurrentUrl();    有返回值
//           5、获取当前页面Title:
//                          driver.getTitle();
public class Demo2 {
    @BeforeMethod
    public void in(){
        System.setProperty("webdriver.gecko.driver","E:\\ideaProject\\drivers\\geckodriver.exe");
        }
//        把路径设置放在BeforeMethod中，每次执行@Test前都会执行
    @Test
    public void FirefoxWebrowserRefresh() throws InterruptedException {
        WebDriver w1= new FirefoxDriver();
        w1.navigate().to("https://www.baidu.com");
        Thread.sleep(5000);
        w1.get("http://news.baidu.com/");
        Thread.sleep(5000);
        w1.navigate().refresh();
        Thread.sleep(5000);
        w1.navigate().back();
        Thread.sleep(5000);
        w1.navigate().forward();
        Thread.sleep(5000);
        w1.quit();
    }
    @Test
    public void FirefoxSetSize() throws InterruptedException {
        WebDriver w2= new FirefoxDriver();
//       设置窗口大小
//           创建Dimension对象   ，将该对象设置到Size里面
        Dimension dimension = new Dimension(800,900);
        w2.manage().window().setSize(dimension);
        Thread.sleep(5000);
        w2.manage().window().maximize();
    }
    @Test     //窗口最大化
    public void FirefoxMaxSize(){
        WebDriver w3 = new FirefoxDriver();
        w3.manage().window().maximize();   //最大化
        w3.quit();
    }
    @Test   //获取当前页面的URL
    public void FirefoxGetCurrentUrl() throws InterruptedException {
        WebDriver w4=new FirefoxDriver();
        w4.navigate().to("https://www.baidu.com");
        Thread.sleep(5000);
        String currentUrl= w4.getCurrentUrl();
//        做校验
        Assert.assertEquals(currentUrl,"https://www.baidu.com/");
        Thread.sleep(5000);
        //        获取当前页面的title
        String title=w4.getTitle();
        w4.quit();
        System.out.println("当前的网址是："+currentUrl);
        System.out.println("当前的title是："+title);
    }


}

