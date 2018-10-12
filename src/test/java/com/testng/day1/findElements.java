package com.testng.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
//    查找元素优先选用：   id / name / xpath
public class findElements {
//    findElement（）    返回一个WebElement元素
//    findElements()     返回一个List，多个WebElement元素
    WebDriver driver;
    //        八种定位方式：
    //      1、By.id(id)
    //      2、By.name(name);   通过name属性查找
    //      3、By.className(class);   通过className属性查找
    //      4、By.linkText(Link文本);    通过classNameLink文本属性查找
    //      5、By.partialLinkText(Link文本)：        部分link文本查找
    //      6、By.tagName             (dom节点名)
    //      7、By.xpath(xpath路径)：    通过firepath 获取到页面元素的xpath路径
    //      8、By.cssSelector(Css路径)： 跟xpath类似，通过firepath 获取到页面元素的css路径：
    @BeforeMethod
    public void startChrome(){
        System.setProperty("webdriver.chrome.driver","E:\\ideaProject\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }
    @Test
    public void byIDTest() throws InterruptedException {
//        八种定位方式： 有id的，优先选择id进行查找
//            1、By.id(id)    通过ID属性查找         查找时要保证没有其他相同的ID，其他属性查找一样
//                                              要保证属性的唯一性，不然查找到的不是需要的元素
        driver.get("https://www.baidu.com");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        WebElement element= driver.findElement(By.id("kw"));   //通过ID查找到的是WebElemnt类型
    }
    @Test
    public void byNameTest(){
//        2、By.name(name);   通过name属性查找
        driver.get("https://www.baidu.com");
        driver.findElement(By.name("tj_trhao123"));
    }
    @Test
    public void byClassNameTest(){
//         3、By.className(class);   通过className属性查找
        driver.get("https://www.baidu.com");
        driver.findElement(By.className("bri"));
    }
    @Test
    public void byLinkTextTest(){
//         4、By.linkText(Link文本);    通过classNameLink文本属性查找
//        eg：HTML 源码：            注意：该方法只能在 <a> 标签中才能使用
//            <a name="tj_setting" href="http://www.baidu.com/gaoji/preferences.html">搜索设置</a>
//              代码例子：
//	           WebElement  element =  driver.findElement(By.linkText( "搜索设置" ));
        driver.get("https://www.baidu.com");
        driver.findElement(By.linkText("新闻"));
    }
    @Test
    public void byPartialLinkTextTest() {
//         5、By.partialLinkText(Link文本)：        部分link文本查找
//                HTML 源码：
//          <a name="tj_setting" href="http://www.baidu.com/gaoji/preferences.html">搜索设置</a>
//             代码例子：
//	           WebElement  element =  driver.findElement(By. partialLinkText( "搜索" ));
        driver.get("https://www.baidu.com");
        WebElement findElement= driver.findElement(By.partialLinkText("新"));
    }
    @Test
    public void byTagNameTest(){
//         6、By.tagName             (dom节点名)：
//        HTML 源码：
//          <a name="tj_setting" href="http://www.baidu.com/gaoji/preferences.html">搜索设置</a>
//            代码例子：
//	             WebElement element=  driver.findElement(By.tagName( “a" ));
        driver.get("https://www.baidu.com");
        driver.findElement(By.tagName("a"));
    }
    @Test
    public void byXpathTest(){
//         7、By.xpath(xpath路径)：    通过firepath 获取到页面元素的xpath路径
//        WebElement element=  driver.findElement(By. xpath( “.//*[@id='kw']" ));

        driver.get("https://www.baidu.com");
        WebElement element=driver.findElement(By.xpath("//*[@id=\"lg\"]/map/area"));
    }

    @Test
    public void byXpath02Test(){
        driver.get("https://www.baidu.com");
//        当查找到多个元素时，需要用List   。  后面需改为  findElements --加上s
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"u1\"]/a"));
        String text=list.get(0).getText();     //获取数组 List 中的文本内容    --0表示第一个元素
        System.out.println(text);
//         若果想获取所有元素内容，可以结合for循环使用
/*
          for(int i=0;i<list.size;i++){
             String text1=list.get(i).getText();
             System.out.println(text1);
          }
 */
    }




    @Test
    public void byCssSelectorTest(){
//         8、By.cssSelector(Css路径)： 跟xpath类似，通过firepath 获取到页面元素的css路径：
//        代码例子：
//        WebElement element=  driver.findElement(By.cssSelector(“#kw”));
        driver.get("https://www.baidu.com");
        WebElement element=driver.findElement(By.cssSelector("#lg > map > area"));
    }
    @AfterMethod
    public void closedBrowser(){
        driver.quit();
    }
}
