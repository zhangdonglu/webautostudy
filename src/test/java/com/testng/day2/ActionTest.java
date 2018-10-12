package com.testng.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionTest {
//    Action类  ：
//      1、  在元素上鼠标右击contextClick(元素)和双击doubeClick（元素）
//      2、  鼠标移动到某个元素上  movetoElement(element);
//      3、  把元素拖动到（x,y） dragAndDropedBy(元素定位，x，y).perform
//      4、  下拉框多选
    WebDriver driver;
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        //添加全局等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
    /*
    case:
        1、打开百度首页
        2、右击 右上角新闻按钮
        3、等待5s
        4、双击百度图标
     */
    @Test
    public void click() throws InterruptedException {
         driver.navigate().to("https://www.baidu.com");
         WebElement w1= driver.findElement(By.linkText("新闻"));
//         实例化Actions类          --将当前driver传入 构造方法中
         Actions action = new Actions(driver);
//         右击  元素 w1   --因此需要将w1传入到参数中
         action.contextClick(w1).perform();//后面一定要加perform  不然不会执行
//         线程等待5s
        Thread.sleep(5000);
//        定位百度图标元素
        WebElement w2=driver.findElement(By.id("su"));
        action.doubleClick(w2).perform();
        Thread.sleep(5000);
    }
    /*
    case2；
       1、打开百度首页
       2、将鼠标移动到右上角更多产品
     */
    @Test
    public void movetoElement(){
        driver.get("https://www.baidu.com/");
        WebElement w1=driver.findElement(By.name("tj_briicon"));
//        实例化Actioms类
        Actions actions=new Actions(driver);
        actions.moveToElement(w1)
                             .perform();//--perform必不可少！！！！！！！
    }
       /*
     case3:
        1、打开dragAndDrop测试主页
        2、将红框移动到（500,500）处
    */
    @Test
    public void dragAndDropedBy() throws InterruptedException {
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/dragAndDrop.html");
        WebElement w1=driver.findElement(By.id("drag"));
        Actions actions=new Actions(driver);
        actions.dragAndDropBy(w1,300,300).perform();
        Thread.sleep(5000);
    }
    /*
  case4:
     1、打开dragAndDrop测试主页
     2、将红框移动到“拖到我这里来"
 */
    @Test
    public void clickAndHold(){
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/dragAndDrop.html");
        WebElement w1=driver.findElement(By.id("drag"));
        WebElement w2=driver.findElement(By.cssSelector("body > h1"));
        Actions actions=new Actions(driver);
//      这是在模拟鼠标的操作：
//        左键按住w1，将它移动到w2，松开左键，即释放 w1，开始运行
        actions.clickAndHold(w1).moveToElement(w2).release(w1).perform();
    }
    /*
     case5:
        1、打开UI自动化测试主页
        2、选择多选下拉框中的前3个
       备注：这里是通过模拟鼠标操作  --点击选择第一个，按住shift键，再选择第三个，则可以选择前三个
     */
    @Test
    public void selectThree(){
        driver.get("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        List<WebElement> w1=driver.findElements(By.xpath
                         ("//*[@id=\"selectWithMultipleEqualsMultiple\"]/option"));
         Actions a1=new Actions(driver);
//        按住SHIFT键，点击多选框的第一个元素，再点击多选框的第二个元素，开始运行
         a1.keyDown(Keys.SHIFT).click(w1.get(0)).click(w1.get(2)).perform();
    }


    @AfterMethod
    public void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
