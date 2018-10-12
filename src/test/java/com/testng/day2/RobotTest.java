package com.testng.day2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotTest {
    /*
   Robot类  --Robot类，提供所有按键元素，Actions类，只提供外围按键，没有a-z字母键
    按住某个按键  :keyPress()
    松开某个按键  :keyRelease()
 案例：@Test
    public void robotDemo() throws AWTException {
        Robot robot = new Robot();
//        按住Ctrl按键
        robot.keyPress(KeyEvent.VK_CONTROL);
//        按住S按键
        robot.keyPress(KeyEvent.VK_S);
//        得到S的ASCII值
        int keyS = (int)new Character('S');
//        松开s键
        robot.keyRelease(keyS);
//        松开Ctrl键
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
     */
    WebDriver driver;
    @BeforeMethod
    public void start(){
        System.setProperty("webdriver.chrome.driver", "E:\\ideaProject\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    /*
    case1:
       1、打开百度首页
       2、键盘输入 Ctrl + s
       3、弹出界面，键盘输入 Enter 键
     */
    @Test
    public void robotTest() throws AWTException, InterruptedException {
       driver.get("https://www.baidu.com/");
        Robot robot=new Robot();
//        按住control键
        robot.keyPress(KeyEvent.VK_CONTROL);
//        按住s键
        robot.keyPress(KeyEvent.VK_S);
        Thread.sleep(3000);
//        点击Enter键
        robot.keyPress(KeyEvent.VK_ENTER);
//        依次释放control、s、Enter键
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }
    @AfterMethod
    public void closed(){
        driver.quit();
    }
}
