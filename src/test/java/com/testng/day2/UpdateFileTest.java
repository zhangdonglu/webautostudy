package com.testng.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateFileTest {
    /*上传文件的处理  sendKeys()
         思路：
	        1. 定位上传控件
	        2. 使用sendkeys(文件地址)方法，并传入文件路径
     */
    WebDriver driver;
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    /*
      case:
         1、打开UI自动测试主页
         2、定位上传文件元素
         3、上传文件
     */
    @Test
    public void sendKeys(){
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        //选择上传文件的路径
        driver.findElement(By.id("load")).sendKeys("C:\\Users\\Administrator\\Desktop\\1.jpg");

    }
}
