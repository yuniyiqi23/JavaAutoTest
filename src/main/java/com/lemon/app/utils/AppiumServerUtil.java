package com.lemon.app.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.openqa.selenium.json.StaticInitializerCoercer;

/**
 * @author 歪歪欧巴
 * @Title: AppiumServerUtil.java
 * @Description: TODO(管理Appium服务)
 * @date 2019年11月28日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class AppiumServerUtil {
    private static Logger logger = Logger.getLogger(AppiumServerUtil.class);

    public static final String APPIUM_JS_PATH =
            "C:\\Users\\TW\\AppData\\Local\\Programs\\Appium\\resources\\app" +
                    "\\node_modules\\appium\\build\\lib\\main.js";
    public static final String APPIUM_IP = "127.0.0.1";

    public static List<Integer> appiumPortLists;
    public static List<Integer> uiautomato2PortList;

    public static void startAppiumServer() {
        //1、执行adb devices命令，获取到所有的设备名
        List<String> deviceLists = CmdUtil.getDeviceLists();
        //2、创建Appium工作的端口4723 4725 4727.。。
        appiumPortLists = PortUtil.getPortList(4723, deviceLists.size());
        //3、创建uiautomator2引擎工作的端口号8200 8202 8204...
        uiautomato2PortList = PortUtil.getPortList(8200, deviceLists.size());
        //4、端口的检测与释放(如果端口有被占用，那么Appium是启动不了)
        for (Integer appiumPort : appiumPortLists) {
            PortUtil.checkPortAndRelease(appiumPort);
        }
        for (Integer uiautomato2Port : uiautomato2PortList) {
            PortUtil.checkPortAndRelease(uiautomato2Port);
        }
        //5、收集到现有的信息，来去启动Appium Server
        //node C:\Users\Administrator\AppData\Local\Programs\Appium\resources\app\node_modules\appium\build\lib\main.js
        // --address 127.0.0.1 --port 4723 --session-override
        // --log D:\eclipse-workspace\app_auto_test_java13\target\device1.log
        //循环创建启动Appium服务的命令参数
        for (int i = 0; i < deviceLists.size(); i++) {
            int num = i + 1;
            String cmdStr = "node " + APPIUM_JS_PATH + " --address " + APPIUM_IP +
                    " --port " + appiumPortLists.get(i) + " --session-override" +
                    " --log " + System.getProperty("user.dir") + "\\target\\device" + num + ".log";
            logger.info("=======================启动Appium Server==========================");
            logger.info("Appium的启动配置:设备名【" + deviceLists.get(i) + "】");
            //启动Appium Server 第二个Appium没有起来？？？
            //程序被阻塞了？？进程阻塞 多线程？？？--》有多个同时 一个Appium对应着一个线程
            new Thread(new Runnable() {

                @Override
                public void run() {
                    // 执行的操作
                    CmdUtil.execCmd(cmdStr);
                }
            }).start();
        }
    }

    /**
     * 停止所有的Appium进程
     */
    public static void stopAppiumServer() {
        //杀掉所有相关的进程即可
        for (Integer appiumPort : appiumPortLists) {
            PortUtil.checkPortAndRelease(appiumPort);
        }
        for (Integer uiautomato2Port : uiautomato2PortList) {
            PortUtil.checkPortAndRelease(uiautomato2Port);
        }
    }

    public static void main(String[] args) {
        startAppiumServer();
    }
}
