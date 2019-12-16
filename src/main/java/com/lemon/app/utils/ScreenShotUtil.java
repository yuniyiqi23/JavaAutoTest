package com.lemon.app.utils;

import com.google.common.io.Files;
import com.lemon.app.base.BaseTest;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;

/**  
 * @Title: ScreenShotUtil.java
 * @Description: TODO(截图的工具类)
 * @author adam
 * @date 2019年11月26日
 */
public class ScreenShotUtil {
	public static File screenshot(String filePath){
		//driver截图API
		File sourceFile = BaseTest.getDriver().getScreenshotAs(OutputType.FILE);
		File targetFile = new File(filePath);
		try {
			Files.copy(sourceFile, targetFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return targetFile;
	}
}
