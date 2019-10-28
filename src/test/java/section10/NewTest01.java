package section10;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class NewTest01 {
  @Test//(invocationCount=8,threadPoolSize=2)
  public void f2() {
    boolean result = true;
    Assert.assertTrue(result);
    System.out.println("f2 : " + System.currentTimeMillis());

  }
  @BeforeMethod
  public void beforeMethod() {
    System.out.println("1beforeMethod");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("1afterMethod");

  }

  @BeforeClass
  public void beforeClass() {
    System.out.println("1beforeClass");

  }

  @AfterClass
  public void afterClass() {
    System.out.println("1afterClass");

  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("1beforeTest");

  }

  @AfterTest
  public void afterTest() {
    System.out.println("1afterTest");

  }

  @BeforeSuite
  public void beforeSuite() {
    System.out.println("1beforeSuite");

  }

  @AfterSuite
  public void afterSuite() {
    System.out.println("1afterSuite");

  }

}
