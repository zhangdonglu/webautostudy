package com.testng.day2;

import org.apache.commons.io.FileUtils;      //Apache的jar包，自己重新下载导入的，之前会报错
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ActionClick {
//   元素的操作：  步骤：1、找到元素   2、操作元素
//    点击：click()
//    文本框输入文本：sendkeys()      --只能对文本框进行操作
//    清空文本框：clear()
//    获取文本：getText()            --只能获取标签中间的值  eg：<a>新闻</a>  只能获取新闻
//    获取title：getTitle()
//    判断元素是否展示：isDisplayed()      --（常用）是Boolean类型  ，输入只有true或false
//    getAttribute(属性)            --获取属性值
//    判断选择框是否选取：isSelected()
//   判断输入框是否为可输入：isEnabled()
//    截图
    WebDriver driver;
    @BeforeMethod
    public void stepChrome(){
    System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
    driver=new ChromeDriver();
}
    /*case：点击百度首页的新闻按钮      --click();
    */
    @Test
    public void Click(){
        driver.get("https://www.baidu.com/");
//        找到新闻这个元素，赋值给news
        WebElement news= driver.findElement(By.name("tj_trnews"));
//        对news元素进行click操作
        news.click();
    }
    /*
    case2:
             1、打开百度首页
             2、在百度搜索文本框中输入selenium
             3、点击百度一下
             4、通过网页title 校验获得的页面是否正确   --getTitle();
     */
    @Test
      public  void sendKeys() throws InterruptedException {//sendkeys:输入框中输入文本
        driver.navigate().to("https://www.baidu.com");
        WebElement text=driver.findElement(By.id("kw"));
//        百度搜索文本框中输入 selenium
        text.sendKeys("selenium");
//        点击百度一下
        WebElement baiduButton=driver.findElement(By.id("su"));
//        点击百度一下按钮
        baiduButton.click();
//      线程等待5秒    -- （防止速度快，校验跟不上会出差）以防速度太快，还没到目的页面，已经获取了前一页面的title
        Thread.sleep(5000);
//        获取网页的title
        String t1=driver.getTitle();
//        开始校验
        Assert.assertEquals(t1,"selenium_百度搜索");
    }
//    清除文本框内容
    @Test
    public  void clear() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        WebElement text=driver.findElement(By.id("kw"));
        text.sendKeys("selenium");
        Thread.sleep(3000);
        text.clear();
        Thread.sleep(3000);
    }
//    获取文本：  getText()；
    /*
     case3:
        1、打开百度首页
        2、获取百度首页右上角的所有文本并输出
     */
    @Test
    public void getText() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        List<WebElement> text=driver.findElements(By.xpath("//*[@id=\"u1\"]/a"));
        for(int i=0;i<text.size();i++){
            String m1=text.get(i).getText();
            System.out.println(m1);
        }

    }
    /*
          1. 打开百度首页
	      2. 获取百度一下的value值      --getAttribute();
          3. 校验value值是否为百度一下

     */
    @Test
    public  void  getAttribute(){
            driver.navigate().to("https://www.baidu.com/");
         String v=  driver.findElement(By.id("su")).getAttribute("value");
           Assert.assertEquals(v,"百度一下");
    }
    //打开百度首页，判断百度图标是否展示    --isDisplayed();
    @Test
    public void isDisplayed(){
        driver.navigate().to("https://www.baidu.com/");
        WebElement w1=driver.findElement(By.cssSelector("#lg > map > area"));
        //把w1这个元素是否展示，赋值给布尔类型b
        Boolean b=w1.isDisplayed();
       //  校验是否展示，我们需求是：展示   即true
        Assert.assertTrue(b);
    }
    /*1、打开自动化测试练习网页
      2、选中RadioBox单选框中的Volvo
      3、判断RadioBox单选框中的Volvo 是否被选中   --isSelected();
     */
    @Test
    public void isSelected() throws InterruptedException {
        driver.navigate().to("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        WebElement w1=driver.findElement(By.cssSelector("#radio > input.Volvo"));
        w1.click();
        Thread.sleep(5000);
        Boolean b=w1.isSelected();
        Assert.assertTrue(b);
    }
    /*
      判断元素是否激活：  isEnabled();
      1、打开自动化测试练习网页
      2、判断Button按钮右边的   submit 按钮是否激活  （需求：没激活）
      */
    @Test
    public void isEnabled(){
        driver.navigate().to("file:///C:/Users/Administrator/Desktop/selenium_html/index.html");
        WebElement w3=driver.findElement(By.name("buttonhtml"));
        Boolean b=w3.isEnabled();
        Assert.assertFalse(b);
    }
    /*
       需求：打开百度首页，截图    --截图直接当前窗口页面
       1、打开百度首页
       2、截图
     */
    @Test
    public void shotScreen(){
        driver.get("https://www.baidu.com");
        File shot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(shot,new File("D:/test.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @AfterMethod
    public void closed(){

        driver.quit();
    }

}