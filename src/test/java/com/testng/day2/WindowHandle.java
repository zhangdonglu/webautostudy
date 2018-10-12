package com.testng.day2;

import jdk.internal.org.objectweb.asm.Handle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WindowHandle {
//    多窗口处理    handle --句柄
WebDriver driver;

    @BeforeMethod
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        //        添加全局等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void closed(){
        driver.quit();
    }
    /*
    case1:
        1、打开UI自动化测试主页
        2、点击 open new window 文本链接
        3、点击新窗口的  baidu 文本链接
     */
    @Test
    public void windowHandle(){
        //适用于2个窗口之间，多个窗口可以选择先关闭第一个之后再操作
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        driver.findElement(By.linkText("Open new window")).click();
//        获取当前页面的handle 句柄
        String handle1=driver.getWindowHandle();
//        通过for 循环 遍历 Handles 与第一个handle进行比较获得第二个窗口的handle
        for(String handls:driver.getWindowHandles()){
            if(handle1.equals(handls)){
                continue;   //如果和handle1 相等 则中断本次操作，继续下一次循环
            }else{
                driver.switchTo().window(handls);
            }
        }

        driver.findElement(By.linkText("baidu")).click();

    }
}
