package com.lemon.web.testcase.login;/**
 * Created by TW on 2019/10/11 20:10
 */

import com.lemon.web.base.BaseTester;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @program: java_web_auto
 * @description: web自动化测试
 * @author: liu yan
 * @create: 2019-10-11 20:10
 */
public class LoginTester extends BaseTester {

    /**
    * @Description: 登录失败
    * @Param: [mobliePhone, password, expectedTips]
    * @return: void
    * @Author: Adam
    * @Date: 2019/10/31
    */
    @Test(dataProvider = "failData", dataProviderClass = LoginDataProvider.class)
    public void loginFail(LoginFailData testData) throws InterruptedException {
        toURL("login_url");
        type(By.id("mobilephone"), testData.getPhone());
        type(By.id("password"), testData.getPassword());
        click(By.id("login"));
        // 强制等待
//        Thread.sleep(1000);
        // 断言
        String actual = getElementText(By.className("tips"));
        Assert.assertEquals(actual, testData.getExpectedTips());
    }

    @Test(dataProvider = "successData", dataProviderClass = LoginDataProvider.class)
    public void loginSuccess(LoginSuccessData testData) throws
            InterruptedException {
        toURL("login_url");
        type(By.id("mobilephone"), testData.getPhone());
        type(By.id("password"), testData.getPassword());
        click(By.id("login"));
//        System.out.println(testData);
        // 强制等待
        Thread.sleep(2000);
        // 断言
        Assert.assertTrue(getCurrentUrl().contains(testData.getPartialUrl()));
//        driver.getTitle()
    }

}
