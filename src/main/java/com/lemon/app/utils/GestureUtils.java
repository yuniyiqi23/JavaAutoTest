package com.lemon.app.utils;/**
 * Created by TW on 2019/11/29 19:53
 */

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * @program: JavaAutoTest
 * @description: 工具类
 * @author: liu yan
 * @create: 2019-11-29 19:53
 */
public class GestureUtils {

    /**
    * @Description: 向上滑动
    * @Param: [driver]
    * @return: void
    * @Author: Adam
    * @Date: 2019/11/29
    */
    public static void swipeUp(AndroidDriver driver) {
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        int startX = screenWidth / 2;
        int startY = screenHeight * 3 / 4;
        int endX = screenWidth / 2;
        int endY = screenHeight  / 4;
        // 把原始的坐标值转换成
        PointOption startPoint = PointOption.point(startX, startY);
        PointOption endPoint = PointOption.point(endX, endY);
        // 执行滑动
        excuteSwipe(driver, startPoint, endPoint);
    }

    /**
     * @Description: 手势向下滑动
     * @Param: [driver]
     * @return: void
     * @Author: Adam
     * @Date: 2019/11/29
     */
    public static void swipeDown(AndroidDriver driver) {
        int screenWidth = driver.manage().window().getSize().getWidth();
        int screenHeight = driver.manage().window().getSize().getHeight();
        int startX = screenWidth / 2;
        int startY = screenHeight / 4;
        int endX = screenWidth / 2;
        int endY = screenHeight * 3 / 4;
        // 把原始的坐标值转换成
        PointOption startPoint = PointOption.point(startX, startY);
        PointOption endPoint = PointOption.point(endX, endY);
        // 执行滑动
        excuteSwipe(driver, startPoint, endPoint);
    }

    private static void excuteSwipe(AndroidDriver driver, PointOption startPoint, PointOption endPoint) {
        int duration = 600;
        // 把时间转化为WaitOptions类型
        WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis
                (duration));
        // 初始化触摸动作类
        TouchAction touchAction = new TouchAction(driver);
        // 执行操作
        touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint)
                .release();
        touchAction.perform();
    }

    /** 
    * @Description: 手势解锁
    * @Param: [driver] 
    * @return: void 
    * @Author: Adam
    * @Date: 2019/11/29 
    */
    public static void unLockSquered(AndroidDriver driver) {
        WebElement webElement = driver.findElement(By.id("com.xxzb" +
                ".fenwoo:id/lpv_password"));
        Point point = webElement.getLocation();
        int x = point.getX();
        int y = point.getY();
        // 得到控件的宽、高
        int width = webElement.getSize().getWidth();
        int height = webElement.getSize().getHeight();
        // 解密的手势
        PointOption pointOption1 = PointOption.point(x + width / 6, y + height / 6);
        PointOption pointOption2 = PointOption.point(x + 3 * width / 6, y + height / 6);
        PointOption pointOption3 = PointOption.point(x + 5 * width / 6, y + height / 6);
        PointOption pointOption4 = PointOption.point(x + 3 * width / 6, y + height * 3 / 6);
        PointOption pointOption5 = PointOption.point(x + width / 6, y + height * 5 / 6);
        PointOption pointOption6 = PointOption.point(x + 3 * width / 6, y + height * 5 / 6);
        PointOption pointOption7 = PointOption.point(x + 5 * width / 6, y + height * 5 / 6);
        // 模拟手势操作
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(pointOption1).moveTo(pointOption2).moveTo(pointOption3).moveTo(pointOption4)
                .moveTo(pointOption5).moveTo(pointOption6).moveTo(pointOption7).release();
        touchAction.perform();

    }

    /**
    * @Description: 手势放大操作
    * @Param: []
    * @return: void
    * @Author: Adam
    * @Date: 2019/11/29
    */
    public static void zoomIn(AndroidDriver driver){
        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);
        int width = driver.manage().window().getSize().getWidth();
        int height = driver.manage().window().getSize().getHeight();
        // 坐标点
        int aPointx = width / 5;
        int aPointy = height / 5;

        int bPointx = width * 2 / 5;
        int bPointy = height * 2 / 5;

        int cPointx = width * 3 / 5;
        int cPointy = height * 3 / 5;

        int dPointx = width * 4 / 5;
        int dPointy = height * 4 / 5;
        // 初始化两根手指的动作
        TouchAction action1 = new TouchAction(driver);
        action1.press(PointOption.point(bPointx, bPointy)).
                moveTo(PointOption.point(aPointx, aPointy)).release();
        TouchAction action2 = new TouchAction(driver);
        action2.press(PointOption.point(cPointx, cPointy)).
                moveTo(PointOption.point(dPointx, dPointy)).release();
        // 连贯动作
        multiTouchAction.add(action1).add(action2);
        multiTouchAction.perform();
    }

}
