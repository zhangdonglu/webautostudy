package com.pageObject;

import org.openqa.selenium.By;

public class LoginPage {
  /* page-object （把一个页面的元素定位，写在一个page里面，写test时，定位相应
    的元素，只需要调用，所在页面的page管理的类里面的对应方法即可）
          为什么要使用page-object  -- 1、集中管理元素对象
                                     2、集中管理一个page内的公共方法
                                     3、后期维护方便

*/
//  定位 输入邮箱文本框 元素
  public static By emailInput = By.name("email") ;
//  定位  密码框  元素
  public static By pwdInput = By.name("password");
//  定位   登录按钮
  public static By loginButton = By.id("dologin");
//    定位  注册按钮
    public static By registerButton = By.id("changepage");


}
