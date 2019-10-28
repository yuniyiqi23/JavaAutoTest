package com.lemon.web.testcase.register;/**
 * Created by TW on 2019/10/11 20:10
 */

import com.lemon.web.base.BaseTester;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @program: java_web_auto
 * @description: web自动化测试
 * @author: liu yan
 * @create: 2019-10-11 20:10
 */
public class RegisterTester extends BaseTester {

    @Test(dataProvider = "dp1", dataProviderClass = RegisterDataProvider.class)
    public void registerTestCase(String mobliePhone, String password, String
            pwdConfirm, String verifyCode,
                     String expectedTips) throws InterruptedException {
        driver.get("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
        type(By.id("mobilephone"), mobliePhone);
        type(By.id("password"), password);
        type(By.id("pwdconfirm"), pwdConfirm);
        type(By.id("verifycode"), verifyCode);
        click(By.id("signup-button"));
        // 断言
        String actual = getText(By.className("tips"));
        Assert.assertEquals(actual, expectedTips);
    }

}
