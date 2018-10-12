package com.testng.day3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GridandData {
//    Grid和数据驱动
//       1、Grid   --通过hub分配，到不同的node运行
    @Test   // test后也可传入参数
    public void testChrome() throws MalformedURLException, InterruptedException {
//        创建一个DesiredCapabilities 类型
        DesiredCapabilities dc=DesiredCapabilities.chrome();
//        实例化一个driver      new和以前不一样，是new  RemoteWebDriver
//                                      需传入grid中hub的地址  和  创建的DesiredCapabilities 类型
        WebDriver driver= new RemoteWebDriver(new URL("http://192.168.0.101:8008/wd/hub"),dc);
//     后续操作和原来的一样
        driver.get("https://www.baidu.com/");
        Thread.sleep(10000);
        driver.quit();
    }
//    2、数据驱动（重要）
    @DataProvider(name = "date1")
    public Object[][] test(){   //有返回值的方法，返回值类型为obj
            return new Object[][]{
//                 每条数据后面还可以跟多条数据，有哪个test需要数据驱动，每条里面
//              有几条我们需要的内容就需要在test参数里面传入几条
                    {" http://192.168.0.101:5555","chrome"},
                    {"http://192.168.0.101:5588","firefox"},
            };
    }
//   3、利用数据驱动来分配任务
    @Test(dataProvider = "date1")//把数据驱动data1传进来
//    在参数列表中传入数据驱动中的数据内容，属性名称根据数据内容自定义
//          数据驱动里面有几组数据，case就跑几次，每跑一次，取一组数据
    public void testGrid2(String url,String browser) throws MalformedURLException, InterruptedException {
//        定义一个DesiredCapabilities类型（先定义，不创建，利用if判断后再创建）
        DesiredCapabilities dc=null;
        if (browser.equals("chrome")) {
            dc = DesiredCapabilities.chrome();
        } else if (browser.equals("firefox")) {
            dc = DesiredCapabilities.firefox();
        } else {
            System.out.println("Error");
        }
//     实例化一个WebDriver
        WebDriver driver = new RemoteWebDriver(new URL(url + "/wd/hub"), dc);
        driver.get("https://www.baidu.com/");
        Thread.sleep(10000);
        driver.quit();
    }

}
