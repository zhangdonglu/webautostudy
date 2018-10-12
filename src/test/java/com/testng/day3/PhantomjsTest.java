package com.testng.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;

public class PhantomjsTest
{
    //Phantomjs  ：  无头浏览器，跑脚本时不打开任何浏览器，但是脚本依然在运行
//    1、导入phantomjs驱动到drivers
//    2、在mvn仓库中添加phantomjs
    @Test
    public void pjsTest(){
//        设置phantomjs 的路径
        System.setProperty("phantomjs.binary.path","E:\\ideaProject\\drivers\\phantomjs.exe");
//        实例化phantomjs
        PhantomJSDriver driver=new PhantomJSDriver();
//        运行失败了，以后查找原因
        driver.get("Https://www.baidu.com/");
        String a=   driver.findElement(By.linkText("视频")).getText();
        System.out.println(a);
        driver.quit();

    }

}
