package com.lemon.app;/**
 * Created by TW on 2019/11/22 16:00
 */

import com.lemon.app.base.BaseTest;
import com.lemon.app.utils.GestureUtils;
import com.lemon.app.utils.ListElementLocate;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * @program: JavaAutoTest
 * @description: App测试
 * @author: liu yan
 * @create: 2019-11-22 16:00
 */
public class LoginTest extends BaseTest {

    private static Logger logger = Logger.getLogger(LoginTest.class);


    @BeforeClass
    public void beforeTest() {
        toClick("主页页面", "我的柠檬");
        toClick("主页页面", "点击头像登录");
    }

    @Test(priority = 3, enabled = true)
    public void testLoginSuccess() throws InterruptedException {
        logger.info("-------------------StartTestLoginSuccess---------------");
        toType("登录页面", "手机号码输入框", "13323234545");
        toType("登录页面", "密码输入框", "234545");
        toClick("登录页面", "登录按钮");
        Thread.sleep(3000);
        String expected = getActivtyNameByPageDesc("主页页面");
        String actual = getCurrentActivtyName();
        Assert.assertEquals(actual, expected);
    }

    @Test(priority = 2, dataProvider = "getLoginFailureDatas")
    public void testLoginFailure(String mobilephone, String password, String tips) throws InterruptedException {
        logger.info("-------------------StarTestLoginFailure---------------");
        toType("登录页面", "手机号码输入框", mobilephone);
        toType("登录页面", "密码输入框", password);
        toClick("登录页面", "登录按钮");
        // 断言
        String expected = tips;
        String actual = getToastTips(tips);
        Assert.assertEquals(actual, expected);
    }

    @Test(enabled = false, priority = 3)
    public void listTestCase() {
        getDriver().findElementByAndroidUIAutomator("new UiSelector().text" +
                "(\"题库\")").click();
//        driver.findElementByAndroidUIAutomator("new UiSelector().text" +
//                "(\"逻辑思维\")").click();
        getDriver().findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()."
                + "scrollable(true).instance(0))."
                + "scrollIntoView(new UiSelector().textMatches(\"逻辑思维题\").instance(0))").click();
//        driver.currentActivity();
//        driver.getPageSource();
//        driver.getDeviceTime();
//        driver.getDisplayDensity();
    }

    @Test(enabled = false)
    public void testCase() throws InterruptedException {
//        Thread.sleep(3000);
//        GestureUtils.swipeDown(driver);
//        GestureUtils.unLockSquered(driver);
//        GestureUtils.zoomIn(driver);
        getDriver().findElementByAndroidUIAutomator("new UiSelector().text(\"题库\")").click();
        ListElementLocate.findElement(getDriver(), "接口测试");
    }

    @DataProvider
    public Object[][] getLoginFailureDatas() {
        Object[][] objects = {{"13323234545", "123456", "错误的账号信息"},
                {"", "123456", "手机号码或密码不能为空"}, {"1332323454", "123456", "手机号码格式不正确"},
                {"", "", "手机号码或密码不能为空"}};
        return objects;
    }

}

