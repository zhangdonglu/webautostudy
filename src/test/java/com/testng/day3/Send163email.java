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

public class Send163email {
    WebDriver driver;
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test   //登录并 发送带附件的邮件
    public void sendEmail() throws InterruptedException {
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
//        Thread.sleep(10000);
//        //        再次点击登录   --这里因为重复多次出现了验证
//
//        driver.findElement(By.id("dologin")).click();
        WebDriverWait wait1 = new WebDriverWait(driver,10);
        wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"_mail_component_63_63\"]/span[2]")));
//        点击写信按钮
        driver.findElement(By.xpath("//*[@id=\"_mail_component_63_63\"]/span[2]")).click();
        Thread.sleep(5000);
//        填写收件人
        driver.findElement(By.className("nui-editableAddr-ipt"))
                .sendKeys("1160017266@qq.com");
//        点击添加附件
        driver.findElement(By.className("O0"))
                .sendKeys("C:\\Users\\Administrator\\Desktop\\LR需求.txt");
//        在正文框里输入内容
//              由于正文框是iFrame 因此需要先定位，然后控制权转交
        WebElement w1 = driver.findElement(By.className("APP-editor-iframe"));
        driver.switchTo().frame(w1);
//        定位正文框 ，发送内容
        driver.findElement(By.xpath("/html/body")).sendKeys("这是正文！");
//        内容写完，点击发送，发送在iFrame外面。因此需要控制权转换
         driver.switchTo().defaultContent(); //转换到原来的控制
//        点击发送

        //定位发送按钮花费了很多时间，可以通过匹配文本来  用xpath定位
        driver.findElement(By.xpath(".//*[text()='发送']")).click();
//         如果 发送成功  校验  “发送成功”  这几个字被显示出来
        Boolean b = driver.findElement(By.xpath(".//*[text()='发送成功']")).isDisplayed();
        Assert.assertTrue(b);



    }
    @AfterMethod
    public void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
