package com.testng.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Try {
/*
     练习：  --校验一般放在最后，最好可以把多个校验放在不同的case里面，防止case不成功直接抛出异常，不再执行
      1、打开百度首页
      2、点击右上角地图按钮
      3、跳转到百度地图页面
      4、通过页面标题校验，是否到达百度地图页面
      5、校验输入文本框默认属性是否为：搜地点、查公交、找路线
      6、文本框中输入：茶园6号线，点击“搜索”按钮
      7、点击第一个搜索结果“茶园-地铁站”
      8、选择到这去
      9、在第一个文本框中输入“大坪”
      10、退出浏览器
 */
   WebDriver driver;
  @BeforeMethod
    public  void  step(){
      System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
      driver = new ChromeDriver();
  }
  @Test
    public void try1() throws InterruptedException {
      driver.get("https://www.baidu.com/");
      driver.manage().window().maximize();
      //定位 地图 元素，并点击
      driver.findElement(By.xpath("//*[@id=\"u1\"]/a[3]")).click();
      Thread.sleep(3000);
//      获取页面标题
     String t1= driver.getTitle();
     Thread.sleep(3000);
//     校验页面标题是否正确
      Assert.assertEquals(t1,"百度地图");
//     获取文本框默认属性
     WebElement attribute= driver.findElement(By.id("sole-input"));
      String a= attribute.getAttribute("placeholder");
      Thread.sleep(3000);
//    校验默认属性是否为：搜地点、查公交、找路线
      Assert.assertEquals(a,"搜地点、查公交、找路线");
//     文本框中输入：茶园6号线
       attribute.sendKeys("茶园6号线");
      Thread.sleep(3000);
//       找到搜索按钮  元素 ,  点击“搜索”按钮
     driver.findElement(By.id("search-button")).click();
      Thread.sleep(5000);
//    点击第一个搜索结果“茶园-地铁站”
      driver.findElement(By.xpath("//*[@id=\"card-1\"]/div/ul/li[1]/div[1]/div[2]/div[1]/span/a")).click();
      Thread.sleep(5000);
//      点击到这去
      driver.findElement(By.xpath("//*[@id=\"route-go\"]/span[2]")).click();
      Thread.sleep(5000);
//      在输入起点文本框中输入：大坪
      driver.findElement(By.xpath("//*[@id=\"route-searchbox-content\"]/div[2]/div/div[2]/div[1]/input")).
              sendKeys("大坪");
      Thread.sleep(5000);
//      点击搜索
      driver.findElement(By.xpath("//*[@id=\"search-button\"]")).click();
      Thread.sleep(5000);
//      选择骑行
      driver.findElement(By.xpath("//*[@id=\"route-searchbox-content\"]/div[1]/div[1]/div[4]/span")).click();
      Thread.sleep(5000);

    }
    @AfterMethod
    public  void  closed(){
      driver.quit();
    }



}
