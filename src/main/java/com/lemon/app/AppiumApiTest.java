package com.lemon.app;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppiumApiTest {

	private AndroidDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		// 前置准备工作
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		// 这四个配置必不可少
		// 1、deviceName：选择哪一台设备进行测试
		desiredCapabilities.setCapability("deviceName", "emulator-5554");
		// 2、因为Appium同时支持android&ios，需要指明测试哪一个
		desiredCapabilities.setCapability("platformName", "Android");
		// 3、测试App包名，选择哪一个APP进行测试
		desiredCapabilities.setCapability("appPackage", "com.lemon.lemonban");
		// 4、App启动，相当于式大门，启动App
		desiredCapabilities.setCapability("appActivity", "com.lemon.lemonban.activity.WelcomeActivity");

		// URL地址
		// localhost:Appium的工作地址
		// 4723：Appium的端口号
		// "/wd/hub" Appium通讯节点 接口地址
		URL remoteUrl = new URL("http://localhost:4723/wd/hub");

		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	}

	@Test
	public void testApi() throws InterruptedException{
		/**
		Thread.sleep(5000);
		//1、currentActivity 用来获取当前页面的activityName
		System.out.println("当前页面名:"+driver.currentActivity());
		//2、获取当前页面的全部结构信息
		System.out.println("当前页面的结构:"+driver.getPageSource());
		//3、获取所有配置信息
		System.out.println("所有的配置信息:"+driver.getCapabilities());
		System.out.println("deviceName的配置"+driver.getCapabilities().getCapability("deviceName"));
		*/
		
		/*
		//获取设备时间信息
		System.out.println("设备时间:"+driver.getDeviceTime());
		//获取设备DPI，注意不是分辨率
		System.out.println("屏幕的DIP："+driver.getDisplayDensity());
		//获取automation name，默认为null，如果有指定automation name为uiautomator2就为对应的值
		System.out.println("当前的自动化引擎:"+driver.getAutomationName());
		//获取设备横竖屏状态，有PORTRAIT(竖屏)与LANDSCAPE(横屏)
		System.out.println("屏幕的横竖屏状态:"+driver.getOrientation());
		*/
		
		Thread.sleep(5000);
		//（1）App内部页面切换
		//切换页面-从主页直接切换到了登录页面 
		//1、初始化Activity对象  appPackage:包名  appActivity:你想要切换对应的页面的activityName
		//Activity activity = new Activity("com.lemon.lemonban", ".activity.LoginActActivity");
		//2、切换页面
		//driver.startActivity(activity);
		
		//（2）App相互之间切换 
		//1、初始化Activity对象-从主页切换到浏览器(必须要是另外App的入口)
		//Activity activity = new Activity("com.android.browser", ".BrowserActivity");
		//driver.startActivity(activity);
		
		//System.out.println(driver.isAppInstalled("com.lemon.lemonban"));
		//driver.installApp("D:\\apk\\58tongcheng.apk");
		WebElement webElement = driver.findElementByAndroidUIAutomator("new UiSelector().text(\"柠檬社区\")");
		//System.out.println(webElement.getAttribute(name));
		
		//截图
		File file = driver.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("D:\\test_111.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
