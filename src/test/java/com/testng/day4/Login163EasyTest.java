package com.testng.day4;

import com.browser.start.ChromeStart;
import com.pageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login163EasyTest {
    WebDriver driver;
    @BeforeMethod
    public void start(){
            System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
//        全局等待
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
//    利用数据驱动来实现case中多种数据登录
    @DataProvider(name = "date1")
    public Object[][] loginData(){
        return new Object[][]{
                {"user1","pwd1"},
                {"user2","pwd2"},
                {"user3","pwd3"}
        };
    }
    @Test(dataProvider = "date1")
    public void loginTest(String user,String pwd){
         driver.get("https://mail.163.com/");
//         定位iFrame   --通过id  直接传入
         driver.switchTo().frame("x-URS-iframe");
         driver.findElement(LoginPage.emailInput).sendKeys(user);
         driver.findElement(LoginPage.pwdInput).sendKeys(pwd);
         driver.findElement(LoginPage.loginButton).click();

    }
    @AfterMethod
    public void closed(){
        driver.quit();
    }
}
