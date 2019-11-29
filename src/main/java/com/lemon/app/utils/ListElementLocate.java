package com.lemon.app.utils;/**
 * Created by TW on 2019/11/29 20:50
 */

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * @program: JavaAutoTest
 * @description: 定位列表元素
 * @author: liu yan
 * @create: 2019-11-29 20:50
 */
public class ListElementLocate {

    public static void findElement(AndroidDriver driver, String elementText) {
        String pageSource = null;
        // 不断向上滑动页面，直到最底端
        while (true){
            pageSource = driver.getPageSource();
            // 根据名称查找元素
            if(pageSource.contains(elementText)){
                driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + elementText + "\")").click();
                break;
            }
            // 向上滑动页面
            GestureUtils.swipeUp(driver);
            String newPageSource = driver.getPageSource();
            // 滑到最底端
            if(pageSource.equalsIgnoreCase(newPageSource)){
                break;
            }
        }
    }
}
