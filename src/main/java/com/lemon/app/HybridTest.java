package com.lemon.app;/**
 * Created by TW on 2019/11/30 17:00
 */

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *@program: JavaAutoTest
 *@description: 混合场景测试
 *@author: liu yan
 *@create: 2019-11-30 17:00
 */
public class HybridTest {
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
        //noReset参数，不清除掉的应用的数据
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(enabled = true)
    public void webTest() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElement(By.xpath
                ("//android.widget.TextView[@text='全程班']"));
        el1.click();
        // 获取去context
        Set<String> contextSet = driver.getContextHandles();
//        System.out.println(contextSet);
        // 切换至WEBVIEW_com.lemon.lemonban
        driver.context("WEBVIEW_com.lemon.lemonban");
        //点击立即购买
        driver.findElement(By.xpath("//button[text()=\"立即购买\"]")).click();
        //在登录页面的QQ号码输入框输入QQ号
        driver.findElement(By.xpath("//*[@id=\"u\"]")).sendKeys("1425301899");
        //在登录页面的密码输入框输入密码
        driver.findElement(By.id("p")).sendKeys("123456");
        //点击登录按钮
        driver.findElement(By.xpath("//*[@id=\"go\"]")).click();
    }
}
