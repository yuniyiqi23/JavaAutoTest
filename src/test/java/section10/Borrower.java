package section10;


/**
 * @author TW
 *
 */
public class Borrower extends Member {

	/* (non-Javadoc)
	 * @see com.lemon.base.day05.section02.Member#recharge(double)
	 */
	public boolean recharge(double amount) {
		boolean flag = super.recharge(amount);
		if (!flag) {
			System.out.println("充值金额必须大于100，小于500000");
		} else {
			System.out.println("充值成功！您可以去还款了！ ");
		}
		return flag;
	}
}
