package com.testng.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WaitTest {
    /*
    等待方式1：
	线程等待：Thread.sleep(xxxx)

等待方式2：
	全局等待：driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

等待方式3：
	显示等待：
	new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By))
	*/
    WebDriver driver;

    @BeforeMethod
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
//        添加全局等待
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void selectByIndex() throws InterruptedException {
//        打开UI 自动化测试主页
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
//        找到下拉框元素
        WebElement w1=driver.findElement(By.name("select"));
//        实例化Select 对象   并传入下拉框元素位置
        Select s1= new Select(w1);
//        通过标签中间的文本选择元素
        s1.selectByVisibleText("vivo");
//        通过元素下标值 选择
        s1.selectByIndex(2);
//        通过value属性值 选择下拉框元素
        s1.selectByValue("meizu");

    }
//    显示等待
    /*
    case1:
       1、打开UI 自动化测试主页
       2、点击wait按钮
       3、校验点击按钮后，是否出现“wait for display”
    */
    @Test
    public void waitTest(){
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        driver.findElement(By.xpath("//*[@id=\"wait\"]/input")).click();
//        显示等待，加在查找元素之前，查找该元素直到超时为止，如果提前查到，则不需等待直接继续后面代码
//     利用WebDriverWait提供的Wait方法，来实现显示等待
//                          new的wait方法需要传入参数（driver，超时时间）
        WebDriverWait wait=new WebDriverWait(driver,10);
//        设置显示等待                                       --传入定位元素所用的方法
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"wait\"]/input")));
//        获取点击  wait  后 显示的文本
        String text=driver.findElement(By.xpath("//*[@id=\"display\"]/div")).getText();
//        做校验
        Assert.assertEquals(text,"wait for display");
    }
    @AfterMethod
    public void closed(){
        driver.quit();
    }


}
