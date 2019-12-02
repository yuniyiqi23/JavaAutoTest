package com.lemon.app.pojo;
/**  
 * @Title: Locator.java
 * @Description: TODO(页面元素对象)
 * @author 歪歪欧巴
 * @date 2019年11月21日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved. 
 */
public class Locator {
	
	private String locatorBy;
	private String locatorValue;
	private String locatorDesc;

	public String getLocatorBy() {
		return locatorBy;
	}
	public void setLocatorBy(String locatorBy) {
		this.locatorBy = locatorBy;
	}
	public String getLocatorValue() {
		return locatorValue;
	}
	public void setLocatorValue(String locatorValue) {
		this.locatorValue = locatorValue;
	}
	public String getLocatorDesc() {
		return locatorDesc;
	}
	public void setLocatorDesc(String locatorDesc) {
		this.locatorDesc = locatorDesc;
	}
	
	public Locator(String locatorBy, String locatorValue, String locatorDesc) {
		super();
		this.locatorBy = locatorBy;
		this.locatorValue = locatorValue;
		this.locatorDesc = locatorDesc;
	}
	
	@Override
	public String toString() {
		return "Locator [locatorBy=" + locatorBy + ", locatorValue=" + locatorValue + ", locatorDesc=" + locatorDesc
				+ "]";
	}

	
	
	
}
