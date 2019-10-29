package com.lemon.web.testcase.login;/**
 * Created by TW on 2019/10/11 20:10
 */

import com.lemon.web.base.BaseTester;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @program: java_web_auto
 * @description: web自动化测试
 * @author: liu yan
 * @create: 2019-10-11 20:10
 */
public class LoginTester extends BaseTester {

    @Test(dataProvider = "dp1", dataProviderClass = LoginDataProvider.class)
    public void loginTestCase(String mobliePhone, String password, String expectedTips) throws InterruptedException {
        toURL("login_url");
        type(By.id("mobilephone"), mobliePhone);
        type(By.id("password"), password);
        click(By.id("login"));
        // 强制等待
        Thread.sleep(1000);
        // 断言
        String actual = getText(By.className("tips"));
        Assert.assertEquals(actual, expectedTips);
    }

}
