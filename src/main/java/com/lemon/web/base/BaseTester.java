package com.lemon.web.base;/**
 * Created by TW on 2019/10/28 16:59
 */

import com.lemon.web.utils.PropertiesUtil;
import com.lemon.web.utils.WebAutoUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: JavaAutoTest
 * @description: 测试基类
 * @author: liu yan
 * @create: 2019-10-28 16:59
 */
public class BaseTester {
    protected static WebDriver driver = null;

    @BeforeSuite
    public void beforeSuite() {
        driver = WebAutoUtils.getDriver("chrome", "2.x");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void afterSuite() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /**
     * @Description: 显示等待元素
     * @Param: [by]
     * @return: org.openqa.selenium.WebElement
     * @Author: Adam
     * @Date: 2019/10/29
     */
    protected WebElement getElement(By by) {
        return getElement(by, 5);
    }

    /**
     * @Description: 显示等待元素
     * @Param: [by, time]
     * @return: org.openqa.selenium.WebElement
     * @Author: Adam
     * @Date: 2019/10/29
     */
    protected WebElement getElement(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        return wait.until(new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(by);
            }
        });
    }

    /**
     * @Description: 智能等待获取元素文本
     * @Param: []
     * @return: java.lang.String
     * @Author: Adam
     * @Date: 2019/10/31
     */
    protected String getElementText(By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver webDriver) {
                WebElement element = driver.findElement(by);
                // 判断元素是否存在
                if(element != null && element.getText() != null && element
                        .getText().length() > 0){
                    return element.getText();
                }
                return null;
            }
        });
    }

    /**
     * @Description: 打开网页
     * @Param: [url]
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/29
     */
    protected void toURL(String urlKey) {
        driver.get(PropertiesUtil.getPropertieValue(urlKey));
    }

    /**
     * @Description: 点击元素
     * @Param: []
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/28
     */
    protected void click(By by) {
        getElement(by).click();
    }

    /**
     * @Description: 输入元素
     * @Param: []
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/28
     */
    protected void type(By by, String content) {
        getElement(by).sendKeys(content);
    }

    /**
     * @Description: 获取元素文本
     * @Param: []
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/28
     */
    protected String getText(By by) {
        return getElement(by).getText();
    }

    /**
    * @Description: 获取URL
    * @Param: []
    * @return: java.lang.String
    * @Author: Adam
    * @Date: 2019/10/31
    */
    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
