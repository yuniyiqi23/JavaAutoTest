package com.lemon.web.testcase.login;/**
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
public class LoginTester extends BaseTester {

    /**
    * @Description: 登录失败
    * @Param: [mobliePhone, password, expectedTips]
    * @return: void
    * @Author: Adam
    * @Date: 2019/10/31
    */
    @Test(dataProvider = "dp", dataProviderClass = CaseDataProvider.class)
    public void login_failure_test_case(LoginFailureData testData) throws
            InterruptedException {
        toURL("login_url");
//        type(By.id("mobilephone"), testData.getPhone());
//        type(By.id("password"), testData.getPassword());
//        click(By.id("login"));
        // 从XML获取元素定位信息
        type("登录页面", "手机号码输入框", testData.getPhone());
        type("登录页面","密码输入框", testData.getPassword());
        click("登录页面", "登录按钮");
        // 断言
        String actual = getElementText("登录页面", "提示信息元素");
        Assert.assertEquals(actual, testData.getExpectedTips());
    }

    @Test(enabled = false, dataProvider = "dp", dataProviderClass =
            CaseDataProvider.class)
    public void login_success_test_case(LoginSuccessData testData) throws
            InterruptedException {
        toURL("login_url");
        type(By.id("mobilephone"), testData.getPhone());
        type(By.id("password"), testData.getPassword());
        click(By.id("login"));
//        System.out.println(testData);
        // 断言
        Assert.assertTrue(currentUrlContainers(testData.getPartialUrl()));
//        driver.getTitle()
    }

}
