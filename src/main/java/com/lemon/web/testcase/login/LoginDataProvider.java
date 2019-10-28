package com.lemon.web.testcase.login;/**
 * Created by TW on 2019/10/28 16:51
 */

import org.testng.annotations.DataProvider;

/**
 *@program: JavaAutoTest
 *@description: 登录数据提供者
 *@author: liu yan
 *@create: 2019-10-28 16:51
 */
public class LoginDataProvider {
    //反向测试用例的数据提供者
    @DataProvider
    public static Object[][] dp1() {
        return new Object[][] {
                new Object[] { "", "", "用户名不能为空" },
                new Object[] { "lemon", "", "非法的手机号" },
                new Object[] { "13888886666", "", "密码不能为空" },
                new Object[] { "13888886666", "12345", "账号信息错误" }
        };
    }

}
