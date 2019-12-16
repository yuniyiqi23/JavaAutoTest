package com.lemon.app.listener;

import com.google.common.io.Files;
import com.lemon.app.utils.ScreenShotUtil;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

import java.io.File;

/**
 * @Title: AllureReporterListener.java
 * @Description: TODO(描述)
 * @author 歪歪欧巴
 * @date 2019年11月26日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class AllureReporterListener implements IHookable {
	private Logger logger = Logger.getLogger(AllureReporterListener.class);

	@Override
	public void run(IHookCallBack callBack, ITestResult testResult) {
		// TODO Auto-generated method stub
		// 执行test方法
		callBack.runTestMethod(testResult);
		// 如果出现异常
		if (testResult.getThrowable() != null) {
			try {
				// 调用截图方法
				takeScreenShot(testResult.getMethod().getMethodName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Attachment(value = "Failure in method {0}", type = "image/png")
	private byte[] takeScreenShot(String methodName) throws Exception {
		logger.info("用例执行失败，开始截图，测试方法【"+methodName+"】");
		File screenshot = ScreenShotUtil.screenshot("D:\\eclipse-workspace\\app_auto_test_java13\\target\\ScreenShot\\ScreenShot_" + System.currentTimeMillis() + ".png");
		return Files.toByteArray(screenshot);
	}
	
	public static void main(String[] args) {
		//用来得到当前项目的绝对路径
		System.out.println("D:\\eclipse-workspace\\java12_app_autotest\\target\\ScreenShot\\ScreenShot_" + System.currentTimeMillis() + ".png");
		//D:\\eclipse-workspace\\java12_app_autotest\\target\\ScreenShot\\ScreenShot_
	}

}
