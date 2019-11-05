package com.lemon.web.testcase.register;/**
 * Created by TW on 2019/10/31 18:18
 */

import com.lemon.base.pojo.ExcelObject;

/**
 *@program: JavaAutoTest
 *@description: 登录失败数据对象
 *@author: liu yan
 *@create: 2019-10-31 18:18
 */
public class RegisterFailureData extends ExcelObject {
//    Mobilephone	Password	Pwdconfirm	Verifycode ExpectedTips
    private String mobilephone;
    private String password;
    private String pwdconfirm;
    private String verifycode;
    private String expectedTips;

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwdconfirm() {
        return pwdconfirm;
    }

    public void setPwdconfirm(String pwdconfirm) {
        this.pwdconfirm = pwdconfirm;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public String getExpectedTips() {
        return expectedTips;
    }

    public void setExpectedTips(String expectedTips) {
        this.expectedTips = expectedTips;
    }

    @Override
    public String toString() {
        return "RegisterFailData{" +
                "mobilephone='" + mobilephone + '\'' +
                ", password='" + password + '\'' +
                ", pwdconfirm='" + pwdconfirm + '\'' +
                ", verifycode='" + verifycode + '\'' +
                ", expectedTips='" + expectedTips + '\'' +
                "} " + super.toString();
    }
}
