package com.testng.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login163 {
//    登录163邮箱练习：  1、正确登录   2、错误账号登录
    WebDriver driver;
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test   //1、正确登录
    public void rightLogin(){
        driver.get("https://mail.163.com/");
//        定位元素时，可以加  显示等待
//                            new的wait方法需要传入参数（driver，超时时间）
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("x-URS-iframe")));
//        把控制全交换给iFrame   --通过定位元素，来定位iFrame
        WebElement iFrame=driver.findElement(By.id("x-URS-iframe"));
        driver.switchTo().frame(iFrame);//传入定位的元素iFrame
//        输入账号
        driver.findElement(By.name("email")).sendKeys("13368343199");
//        输入密码
        driver.findElement(By.name("password")).sendKeys("zdl20151515");
//        点击登录
        driver.findElement(By.id("dologin")).click();
    }
    @Test
    public void errorLogin(){
//        时间戳
        String time=String.valueOf(System.currentTimeMillis());
        driver.get("https://mail.163.com/");
//        控制权转换
        driver.switchTo().frame("x-URS-iframe"); //通过iframe  ID定位
//        输入正确账号
        driver.findElement(By.name("email")).sendKeys("13368343199");
//        输入错误密码
        driver.findElement(By.name("password")).sendKeys("zdl"+time);
//        获取 登录错误的提示 的文本
//        点击登录
        driver.findElement(By.id("dologin")).click();
        String a= driver.findElement(By.className("ferrorhead")).getText();
//        获取的文本做校验
        Assert.assertEquals(a,"帐号或密码错误","错误登录时，提示有误");

    }
    @AfterMethod
    public void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
