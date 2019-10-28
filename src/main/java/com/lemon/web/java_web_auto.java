package com.lemon.web;/**
 * Created by TW on 2019/10/11 20:10
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: java_web_auto
 * @description: web自动化测试
 * @author: liu yan
 * @create: 2019-10-11 20:10
 */
public class java_web_auto {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/web_driver/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
//        driver.get("http://www.baidu.com");
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        <input type="submit" id="su" value="百度一下" class="bg s_btn">
        driver.get("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
        driver.findElement(By.id("mobilephone")).sendKeys("13888888888");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.cssSelector("div[data-type='5']"));
        driver.executeScript("");
        // 点击排课管理
        driver.findElement(By.id("class-manage")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
//        ExpectedConditions.titleContains("");
        WebElement element = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return driver.findElement(By.linkText("一周排课"));
            }
        });
        element.click();

        List<WebElement> elementList = driver.findElements(By.name(""));
        elementList.get(0);
        WebElement frameElement = driver.findElement(By.cssSelector
                ("iframe[src='class-plan-list.html']"));
        driver.switchTo().frame(frameElement);

        Select select = new Select(driver.findElement(By.id("m-select")));
        List<WebElement> webElementList = select.getAllSelectedOptions();
        for (WebElement item: webElementList){
            item.getText();
        }
        select.deselectAll();

        Set<String> handleSet = driver.getWindowHandles();
        Map<String, String> map = new HashMap<>();


        Thread.sleep(3000);
        driver.close();

    }

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/web_driver/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
        driver.findElement(By.partialLinkText("京公网安备")).click();

        Set<String> handleSet = driver.getWindowHandles();
        for(String item: handleSet){
            System.out.println(item);
        }
        List<String> handleList = new ArrayList<>(handleSet);
        driver.switchTo().window(handleList.get(1));

//        <a href="/portal/recordQuery?token=518a8fa8-7c77-47fa-b53d-f6172b386b7f">公共查询</a>
        driver.findElement(By.partialLinkText("公共查询")).click();

        driver.switchTo().window(handleList.get(0));

        Thread.sleep(3000);
        driver.quit();
    }

}
