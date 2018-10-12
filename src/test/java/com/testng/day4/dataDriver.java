package com.testng.day4;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataDriver {
//    数据驱动（重要）
    @DataProvider(name = "data1")
    public Object[][] dataTest(){
       return new Object[][] {
            {"user1","pwd1"},
            {"user2","pwd2"},
        };
    }
    @Test(dataProvider = "data1")
//    传入数据驱动中，每列的类型
    public void loginTest(String user,String pwd){
        System.out.println(user+pwd);
    }

}
