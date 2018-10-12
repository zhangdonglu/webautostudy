package com.testng.day2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class IEalert {
    WebDriver driver;

    @BeforeMethod
    public void alert() {
        System.setProperty("webdriver.ie.driver", "E:\\ideaProject\\drivers\\IEDriverServer.exe");
        driver= new InternetExplorerDriver();
    }
        @Test
        public void prompt() throws InterruptedException {
            driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
            driver.findElement(By.className("prompt")).click();
            Thread.sleep(3000);
//        交换控制权
            Alert alert=  driver.switchTo().alert();
            Thread.sleep(3000);
//      在Prompt 弹窗中，输入“这个是Prompt”
            alert.sendKeys("zheshi");
            //      点击确定
            alert.accept();
            Thread.sleep(3000);
            alert.accept();
            Thread.sleep(3000);

        }
        @AfterMethod
    public void closed(){
        driver.quit();
        }

}
