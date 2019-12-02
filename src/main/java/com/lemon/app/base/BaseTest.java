package com.lemon.app.base;/**
 * Created by TW on 2019/12/2 9:42
 */

import com.lemon.app.pojo.Locator;
import com.lemon.app.pojo.Page;
import com.lemon.app.utils.XmlUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaAutoTest
 * @description: 测试基类
 * @author: liu yan
 * @create: 2019-12-02 09:42
 */
public class BaseTest {
    protected AndroidDriver driver;
    private static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite
    @Parameters({"deviceName", "platformName", "appPackage", "appActivity", "automationName", "appiumIP",
            "appiumPort"})
    public void setUp(String deviceName, String platformName, String appPackage, String appActivity,
                      String automationName, String appiumIP, String appiumPort) throws MalformedURLException {
        logger.info("========================开始APP测试========================");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformName", platformName);
//        desiredCapabilities.setCapability("appPackage", "com.xxzb.fenwoo");
//        desiredCapabilities.setCapability("appPackage", "com.baidu.BaiduMap");
//        desiredCapabilities.setCapability("appActivity", "com.baidu.baidumaps.WelcomeScreen");
//        desiredCapabilities.setCapability("appActivity", ".activity.addition.WelcomeActivity");
// 3、测试App包名，选择哪一个APP进行测试
        desiredCapabilities.setCapability("appPackage", appPackage);
        // 4、App启动，相当于式大门，启动App
        desiredCapabilities.setCapability("appActivity", appActivity);
        // 自动化测试引擎
        desiredCapabilities.setCapability(MobileCapabilityType
                .AUTOMATION_NAME, automationName);
        //6、noReset参数，不清除掉的应用的数据
//        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://" + appiumIP + ":" + appiumPort +
                "/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // 隐式等待
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        logger.info(driver.getCapabilities());
    }

    /**
     * @Description: 封装click方法
     * @Param: [pageName, locatorName]
     * @return: void
     * @Author: Adam
     * @Date: 2019/12/2
     */
    public void toClick(String pageName, String locatorName) {
        logger.info("在页面【" + pageName + "】找到元素【" + locatorName + "】点击");
        getElement(pageName, locatorName).click();
    }

    /**
     * @Description: 封装click方法
     * @Param: [pageName, locatorName]
     * @return: void
     * @Author: Adam
     * @Date: 2019/12/2
     */
    public void toType(String pageName, String locatorName, String data) {
        logger.info("在页面【" + pageName + "】找到元素【" + locatorName + "】输入数据【" + data + "】");
        getElement(pageName, locatorName).sendKeys(data);
    }

    /**
     * @Description: 根据pageName、locatorName获取webElement
     * @Param: [pageName, locatorName]
     * @return: void
     * @Author: Adam
     * @Date: 2019/12/2
     */
    public WebElement getElement(String pageName, String locatorName) {
        for (Page page : XmlUtil.listPage) {
            if (page.getPageDesc().equalsIgnoreCase(pageName)) {
                List<Locator> locatorList = page.getListLocator();
                for (Locator locator : locatorList) {
                    if (locator.getLocatorDesc().equalsIgnoreCase(locatorName)) {
                        //得到元素的定位方式、定位值
                        String locatorBy = locator.getLocatorBy();
                        String locatorValue = locator.getLocatorValue();
                        WebDriverWait wait = new WebDriverWait(driver, 5);
                        WebElement webElement = wait.until(new
                                                                   ExpectedCondition<WebElement>() {
                                                                       @Override
                                                                       public WebElement apply(WebDriver webDriver) {
                                                                           return getWebElement(locatorBy, locatorValue);
                                                                       }
                                                                   });
                        return webElement;
                    }
                }
            }
        }
        return null;
    }

    private WebElement getWebElement(String locatorBy, String locatorValue) {
        if (locatorBy.equalsIgnoreCase("ID")) {
            return driver.findElement(By.id(locatorValue));
        } else if (locatorBy.equalsIgnoreCase("AccessibilityId")) {
            return driver.findElementByAccessibilityId(locatorValue);
        } else if (locatorBy.equalsIgnoreCase("TEXT")) {
            return driver.findElementByAndroidUIAutomator("new UiSelector().text(" + locatorValue + ")");
        } else if (locatorBy.equalsIgnoreCase("XPATH")) {
            return driver.findElement(By.xpath(locatorValue));
        }
        return null;
    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
        logger.info("========================结束APP测试========================");
        driver.quit();
    }
}
