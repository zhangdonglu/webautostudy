package com.testng.day2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertConfirm {
  /*
   1、 Alert、Confirm、prompt 的处理
   2、 iFrame 的处理
   3、 下拉框的选取
   4、 多窗口的处理
   5、 元素等待的处理
*/
   WebDriver driver;
   @BeforeMethod
    public void startBrowser(){
       System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
       driver=new ChromeDriver();
       driver.manage().window().maximize();
   }
   /*
     case1:
        1、打开UI 自动化测试主页
        2、点击主页中的 Alert按钮
        3、在弹出的警告框中，点击确定
    */
    @Test
    public void alert() throws InterruptedException {
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        driver.findElement(By.className("alert")).click();
//        工作中处理弹窗等 ，需要停留1秒左右,用来保证js浮层（弹窗）顺利显示出来之后，再进行操作
        Thread.sleep(3000);
//        当前页面driver的控制权交换给alert  ---交换控制权：switchTo()
//        alert() 返回的是Alert类型，需要定义属性，来接收返回值
        Alert alert=driver.switchTo().alert();
//        获取警告框的文本
       String text= alert.getText();
//        点击确定
         alert.accept();
         Thread.sleep(3000);
//         校验警告框文本
        Assert.assertEquals(text,"请点击确定");
    }
    /*
     case2:
        1、打开UI 自动化测试主页
        2、点击主页中的 confirm按钮
        3、在弹出的警告框中，点击取消
        4、在弹窗的警告框中，点击确定
    */
    @Test
    public void confirm() throws InterruptedException {
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        driver.findElement(By.className("confirm")).click();
        Thread.sleep(3000);
//        控制权交换
        Alert alert = driver.switchTo().alert();
//        弹出的警告框，点击取消
        alert.dismiss();
        Thread.sleep(3000);
//        点击取消后，弹出的警告框，点击确定
        alert.accept();
        Thread.sleep(3000);
    }
    /*
    测试用例：
	1. 打开“UI自动化测试”主页
	2. 点击Prompt按钮
	3. 在Prompt 弹窗中，输入“这个是Prompt”
	4.  点击确定
	5.  点击确定
     */
    @Test
    public void prompt() throws InterruptedException {
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        driver.findElement(By.className("prompt")).click();
        Thread.sleep(3000);
//        交换控制权
      Alert alert=  driver.switchTo().alert();
        Thread.sleep(3000);
//      在Prompt 弹窗中，输入“这个是Prompt”
        alert.sendKeys("zheshi");    //当前版本Chrome浏览器有BUG，不能向弹窗中输入值
   //      点击确定
        alert.accept();
        Thread.sleep(3000);
        alert.accept();
       Thread.sleep(3000);
    }
    /*           --iFrame 的处理
    1. 定位iFrame
	2. driver控制权交给iFrame
	3. 操作iFrame里面的元素
	4. driver控制权交回原页面
     */
    @Test
    public void iFrame() throws InterruptedException {
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
       /*       iFrame 的定位需在  括号中传入以下任意参数：
                            1、String 类型 ：  iFrame 的 id  或  name
                            2、WebElement类型   --找到这个元素，再传入参数中
*/
//      控制权转交给iFrame    ,交换权限之后再去定位需要的元素，否则不成功
        driver.switchTo().frame("aa");  //通过iFrame 的name来定位的
        Thread.sleep(3000);
        WebElement w1=driver.findElement(By.linkText("baidu"));
        w1.click();
        Thread.sleep(3000);
//        控制权交换给原页面
        driver.switchTo().defaultContent();
    }
    /*           --iFrame 的处理
    1. 定位iFrame
	2. driver控制权交给iFrame
	3. 操作iFrame里面的元素
	4. driver控制权交回原页面
	5. 点击原页面的“登录界面”
     */
    @Test
    public void iFrame2() throws InterruptedException {
        driver.navigate().to("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
//       同过WebElement元素来定位iFrame
        WebElement w1=driver.findElement(By.xpath("/html/body/div/table/tbody/tr[15]/td[2]/iframe"));
//        将找到的iFrame的位置传入frame，并得到控制权
        driver.switchTo().frame(w1);
//        找到iFrame的  baidu 这个文本链接 并点击
        driver.findElement(By.linkText("baidu")).click();
        Thread.sleep(3000);
//        控制权交换给原页面
        driver.switchTo().defaultContent();
        Thread.sleep(5000);
//        找到当前页面中的 登录界面  并 点击
        driver.findElement(By.linkText("登陆界面")).click();
        Thread.sleep(3000);
    }

    @AfterMethod
    public void closed(){

        driver.quit();
    }



}
