package com.lemon.web.testcase.register;/**
 * Created by TW on 2019/10/11 20:10
 */

import com.lemon.web.base.BaseTester;
import com.lemon.web.base.CaseDataProvider;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @program: java_web_auto
 * @description: web自动化测试
 * @author: liu yan
 * @create: 2019-10-11 20:10
 */
public class RegisterTester extends BaseTester {

    @Test(dataProvider = "dp", dataProviderClass = CaseDataProvider.class)
    public void register_failure_test_case(RegisterFailureData testData) throws
            InterruptedException {
        toURL("register_url");
        type(By.id("mobilephone"), testData.getMobilephone());
        type(By.id("password"), testData.getPassword());
        type(By.id("pwdconfirm"), testData.getPwdConfirm());
        type(By.id("verifycode"), testData.getVerifyCode());
        click(By.id("signup-button"));
        // 断言
        String actual = getText(By.className("tips"));
        Assert.assertEquals(actual, testData.getExpectedTips());
    }

}
