package com.testng.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
/*时间戳： 时间戳记录的是1970.1.1到现在的经过的秒数，每秒都在加1，因此不会重复
                 在注册邮箱时，为保证注册的邮箱不会重复，可以利用时间戳来注册
*/

public class RegTest {
//    练习：1、完成163邮箱的注册
    WebDriver driver;
    @BeforeMethod
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
//        全局等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void regTest() throws InterruptedException {

        //打开163邮箱首页
        driver.navigate().to("https://mail.163.com/");
        //1、获得当前页面的句柄     --返回字符串类型
     String handle1=   driver.getWindowHandle();
//        把控制权交还给iFrame --即登录/注册框
        driver.switchTo().frame("x-URS-iframe");  //有ID直接用id插入iFrame位置
//        点击注册
        driver.findElement(By.id("changepage")).click();
//    由于跳转到了新的页面，因此需要获得新页面的句柄，把控制权交给新的句柄
       //利用for循环
        for (String handles:driver.getWindowHandles()){
            if (handles.equals(handle1)){
                continue;
            }else{
                driver.switchTo().window(handles);
            }
        }
//        利用时间戳来注册邮箱，保证跑脚本时，不会重复
       String time= String.valueOf( System.currentTimeMillis());//强制转换为String类型

        Thread.sleep(3000);
//        向邮件地址中输入内容
        driver.findElement(By.id("nameIpt")).sendKeys("z"+time);//直接把time（时间戳）传入
        Thread.sleep(3000);
//        在密码框中输入秘密
        driver.findElement(By.id("mainPwdIpt")).sendKeys("z9879980");
        Thread.sleep(3000);
//        确认秘密框中确认密码
        driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("z9879980");
        Thread.sleep(3000);
//        在手机号码框中输入手机密码
        driver.findElement(By.id("mainMobileIpt")).sendKeys("13388776655");
        Thread.sleep(3000);
//        图片验证码获取方式：1、找开发要万能验证码   2、去掉验证功能   3、图形解析（难）
//        这里输入错误的验证码：
        driver.findElement(By.id("vcodeIpt")).sendKeys("2456");
//        免费获取验证码需要手机号，目前直接向框中输入内容
        driver.findElement(By.id("mainAcodeIpt")).sendKeys("8899");
//        点击立即注册
        driver.findElement(By.id("mainRegA")).click();

    }
    @AfterMethod
    public void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
