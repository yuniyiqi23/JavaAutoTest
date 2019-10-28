package section10;

/**
 * @author TW
 *
 */
public class Investor extends Member {
	
	/**
	 * 投资
	 * @param amount
	 */
	public void invest(double amount) {
		System.out.println("投资");
	}
	
	
	/* 
	 * 
	 * (non-Javadoc)
	 * @see com.lemon.base.day05.section02.Member#recharge(double)
	 */
	public boolean recharge(double amount) {
		boolean flag = super.recharge(amount);
		if (!flag) {
			System.out.println("充值金额必须大于100，小于500000");
		} else {
//			this.setLeaveAmount(amount + this.getLeaveAmount());
			System.out.println("充值成功！您可以去投资了！");
		}
		return flag;
	}
}
