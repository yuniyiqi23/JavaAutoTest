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
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @program: JavaAutoTest
 * @description: App测试
 * @author: liu yan
 * @create: 2019-11-22 16:00
 */
public class LoginTest extends BaseTest {

    @BeforeTest
    public void beforeTest() {
        toClick("主页页面", "我的柠檬");
        toClick("主页页面", "点击头像登录");
    }

    @Test(priority = 3)
    public void testLoginSuccess() throws InterruptedException {
        toType("登录页面", "手机号码输入框", "13323234545");
        toType("登录页面", "密码输入框", "234545");
        toClick("登录页面", "登录按钮");
    }

    @Test(priority = 2)
    public void testLoginFailure() throws InterruptedException {
        toType("登录页面", "手机号码输入框", "13323234545");
        toType("登录页面", "密码输入框", "123456");
        toClick("登录页面", "登录按钮");
    }

    @Test(enabled = false, priority = 3)
    public void listTestCase() {
        driver.findElementByAndroidUIAutomator("new UiSelector().text" +
                "(\"题库\")").click();
//        driver.findElementByAndroidUIAutomator("new UiSelector().text" +
//                "(\"逻辑思维\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()."
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
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"题库\")").click();
        ListElementLocate.findElement(driver, "接口测试");
    }

}

