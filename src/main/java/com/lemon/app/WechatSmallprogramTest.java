package com.lemon.app;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WechatSmallprogramTest {

  private AndroidDriver driver;

  @BeforeTest
  public void setUp() throws MalformedURLException {
	 //前置准备工作
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //这四个配置必不可少
    //1、deviceName：选择哪一台设备进行测试
    desiredCapabilities.setCapability("deviceName", "emulator-5554");
    //2、因为Appium同时支持android&ios，需要指明测试哪一个
    desiredCapabilities.setCapability("platformName", "Android");
    //3、测试App包名，选择哪一个APP进行测试
    desiredCapabilities.setCapability("appPackage", "com.tencent.mm");
    //4、App启动，相当于式大门，启动App
    desiredCapabilities.setCapability("appActivity", "com.tencent.mm.ui.LauncherUI");
    //5、一定要加这个配置,不清除掉微信的数据，一定要切记！！！！！
    desiredCapabilities.setCapability("noReset", true);
    
    // 支持X5内核应用自动化配置
    desiredCapabilities.setCapability("recreateChromeDriverSessions", true);
    // ChromeOptions使用来定制启动选项，因为在appium中切换context识别webview的时候, 
    // 把com.tencent.mm:toolsmp的webview识别成com.tencent.mm的webview.
    // 所以为了避免这个问题，加上androidProcess: com.tencent.mm:toolsmp
    ChromeOptions options = new ChromeOptions();
    options.setExperimentalOption("androidProcess", "com.tencent.mm:appbrand0");
    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
    // 初始化会默认将chrome浏览器打开，需要将Browser置为空
    desiredCapabilities.setBrowserName("");
    
    URL remoteUrl = new URL("http://localhost:4723/wd/hub");
    driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void testSmallprogram() throws InterruptedException{
	  //1、等待微信的主页面加载完成
	  Thread.sleep(8000);
	  //2、下拉找到小程序
	  swipeDown();
	  //3、找到柠檬班软件测试并且点击
	  driver.findElement(By.id("com.tencent.mm:id/dd")).click();
	  //4、等待小程序加载结束
	  Thread.sleep(8000);
	  //5、获取到所有的contexts
	  Set<String> contexts = driver.getContextHandles();
	  System.out.println(contexts);
	  //6、切换到小程序对应的context中
	  driver.context("WEBVIEW_com.tencent.mm:appbrand0");
	  Thread.sleep(2000);
	  //7、找到小程序页面的课程，并且点击-- 找不到这个元素时因为句柄的问题，遍历所有的句柄，来确认我们当前页面在哪个句柄中
	  Set<String> handles = driver.getWindowHandles();
	  for (String handle : handles) {
		driver.switchTo().window(handle);
		//判断当前页面有没有包含某个关键字
		if(driver.getPageSource().contains("柠檬班")){
			break;
		}
	  }
	  driver.findElement(By.xpath("//a[contains(text(),\'课程\')]")).click();
	  Thread.sleep(3000);
	  
  }
  
  /**
	 * 通用的向下滑动方法
	 */
	public void swipeDown() {
		// 怎么获取滑动的起始点、终止点、滑动时间
		// 起始点--》屏幕宽度的1/2位置 高度 1/4位置
		int screenX = driver.manage().window().getSize().getWidth();
		int screenY = driver.manage().window().getSize().getHeight();
		int startx = screenX / 2;
		int starty = screenY / 4;
		int endx = screenX / 2;
		int endy = screenY * 3 / 4;
		int duration = 100;

		// 把原始的坐标转换成PointOption类型
		PointOption startPoint = PointOption.point(startx, starty);
		PointOption endPoint = PointOption.point(endx, endy);

		// 把时间转换成WaitOptions类型(二次转换)
		WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(500));
		// 1、初始化触摸动作对象
		TouchAction touchAction = new TouchAction(driver);
		// appium converts press-wait-moveto-release to a swipe action
		touchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release();
		touchAction.perform();
	}

  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}
