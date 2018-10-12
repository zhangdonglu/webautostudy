package com.testng.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectByIndex {
    /*下拉框的处理：
          三种处理方式：
	1. selectByIndex() 根据索引来选取，从0开始     --下标值
	2. selectByValue() 根据属性value的属性值来选取
	3. selectByVisibleText()根据标签之间的Text值，也就是页面显示的
     */
    WebDriver driver;
    @BeforeMethod
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void selectByIndex() throws InterruptedException {
//        打开UI 自动化测试主页
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
//        定位下拉框元素
        WebElement w1= driver.findElement(By.name("select"));
//        创建select对象
        Select s1=new Select(w1);    //传入要处理的下拉框元素的位置
        s1.selectByIndex(2);
//        为了看效果等待5s
        Thread.sleep(5000);
//        根据value的属性值来选择
        s1.selectByValue("oppe");
        //        为了看效果等待5s
        Thread.sleep(5000);
//        根据标签之间的Text值，也就是页面显示的文本
        s1.selectByVisibleText("huawei");
        //        为了看效果等待5s
        Thread.sleep(5000);
    }
    @AfterMethod
    public void closedBrowser(){
        driver.quit();
    }
}
