package com.lemon.app;/**
 * Created by TW on 2019/11/22 16:00
 */
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
import org.testng.annotations.Test;

/**
 *@program: JavaAutoTest
 *@description: App测试
 *@author: liu yan
 *@create: 2019-11-22 16:00
 */
public class AppTest {

    private AndroidDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:62025");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
        desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");

        desiredCapabilities.setCapability(MobileCapabilityType
                .AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        //6、noReset参数，不清除掉的应用的数据
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void login() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"我的柠檬\"]/android.widget.ImageView");
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/fragment_my_lemon_avatar_title");
        el2.click();
        MobileElement el3 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_mobile");
        el3.sendKeys("13323234545");
        MobileElement el4 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/et_password");
        el4.sendKeys("123456");
        MobileElement el5 = (MobileElement) driver.findElementById("com.lemon.lemonban:id/btn_login");
        el5.click();
//        driver.findElement(By.xpath("//android.widget.TextView[@text='柠檬社区']")).click();
        WebElement webElement = driver.findElement(By.xpath("//*[contains" +
                "(@text,'账号信息')" + "]"));
        System.out.println(webElement.getText());
    }

    @Test
    public void listTestCase(){
        driver.findElementByAndroidUIAutomator("new UiSelector().text" +
                "(\"题库\")").click();
//        driver.findElementByAndroidUIAutomator("new UiSelector().text" +
//                "(\"逻辑思维\")").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()."
                + "scrollable(true).instance(0))."
                + "scrollIntoView(new UiSelector().textMatches(\"逻辑思维题\").instance(0))").click();
        driver.currentActivity();
        driver.getPageSource();
        driver.getDeviceTime();
        driver.getDisplayDensity();

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}

