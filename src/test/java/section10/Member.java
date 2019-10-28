package section10;

/**
 * @author TW
 *
 */
public class Member {

	private String regName; // 用户名
	private String password; // 密码
	private String mobilePhone; // 手机号码
	private double leaveAmount; // 可用余额

	public Member() {

	}

	public Member(String regName, String mobilePhone, double leaveAmount) {
		this.regName = regName;
		this.mobilePhone = mobilePhone;
		this.leaveAmount = leaveAmount;
	}

	public String getRegName() {
		return regName;
	}

	public void setRegName(String regName) {
		this.regName = regName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public double getLeaveAmount() {
		return leaveAmount;
	}

	public void setLeaveAmount(double leaveAmount) {
		this.leaveAmount = leaveAmount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param mobilephone
	 */
	public void regist(String username, String password, String mobilephone) {
		if (mobilephone.length() == 11 && password.length() == 6
				&& username.length() > 5) {
			System.out.println("注册成功！");
		} else {
			System.out.println("注册失败！");
		}
	}

	/**
	 * 登录
	 * 
	 * @param password
	 * @param mobilephone
	 */
	public void login(String mobilephone, String password) {
		if (mobilephone.length() == 11 && password.length() == 6) {
			System.out.println("登录成功！");
		} else {
			System.out.println("账号或密码错误！");
		}
	}

	
	/**
	 * 充值
	 * @param amount
	 */
	public boolean recharge(double amount) {
		if (amount < 100 || amount > 500000) {
//			System.out.println("充值金额必须大于100，小于500000");
			return false;
		} else {
			this.setLeaveAmount(amount + this.getLeaveAmount());
//			System.out.println("充值成功！余额： " + this.getLeaveAmount());
			return true;
		}
	}
	
	
	/**
	 * 提现
	 * @param amount
	 */
	public void withdraw(double amount) {
		if (amount < 0 || amount > 500000) {
			System.out.println("提现金额必须大于0，小于500000");
		} else {
			this.setLeaveAmount(this.getLeaveAmount() - amount);
			System.out.println("提现成功！余额： " + this.getLeaveAmount());
		}
	}
	
}
