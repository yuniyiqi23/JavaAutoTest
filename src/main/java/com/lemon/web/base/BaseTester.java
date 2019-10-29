package com.lemon.web.base;/**
 * Created by TW on 2019/10/28 16:59
 */

import com.lemon.web.utils.WebAutoUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 *@program: JavaAutoTest
 *@description: 测试基类
 *@author: liu yan
 *@create: 2019-10-28 16:59
 */
public class BaseTester {
    protected static WebDriver driver = null;

    @BeforeSuite
    public void beforeSuite(){
        driver = WebAutoUtils.getDriver("chrome", "2.x");
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void afterSuite() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    /** 
    * @Description: 打开网页
    * @Param: [url] 
    * @return: void 
    * @Author: Adam
    * @Date: 2019/10/29 
    */
    protected void toURL(String url){
        driver.get(url);
    }
    
    /** 
    * @Description: 点击元素 
    * @Param: [] 
    * @return: void 
    * @Author: Adam
    * @Date: 2019/10/28 
    */
    protected void click(By by){
        driver.findElement(by).click();
    }

    /**
     * @Description: 输入元素
     * @Param: []
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/28
     */
    protected void type(By by, String content){
        driver.findElement(by).sendKeys(content);
    }

    /**
     * @Description: 获取元素文本
     * @Param: []
     * @return: void
     * @Author: Adam
     * @Date: 2019/10/28
     */
    protected String getText(By by){
        return driver.findElement(by).getText();
    }

}
