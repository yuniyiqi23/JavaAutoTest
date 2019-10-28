package section10;

import org.testng.Assert;
import org.testng.annotations.*;


public class NewTest {
	@Test//(invocationCount=8,threadPoolSize=2)
	public void f() {
		Borrower borrower = new Borrower();
		double before = borrower.getLeaveAmount();
		borrower.recharge(100);
		double expected = before + 100;
		double after = borrower.getLeaveAmount();
		Assert.assertEquals(after, expected);
		System.out.println("f : " + System.currentTimeMillis());
	}

	@Test//(invocationCount=8,threadPoolSize=2)
	public void f1() {
		Borrower borrower = new Borrower();
		double before = borrower.getLeaveAmount();
		borrower.recharge(100);
		double expected = before + 100;
		double after = borrower.getLeaveAmount();
		Assert.assertEquals(after, expected);
		System.out.println("f1 : " + System.currentTimeMillis());

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("afterTest");

	}
}
