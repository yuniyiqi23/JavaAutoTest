package com.lemon.app.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @Title: PortUtil.java
 * @Description: TODO(端口管理的工具类)
 * @author 歪歪欧巴
 * @date 2019年11月26日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class PortUtil {
	
	private static Logger logger = Logger.getLogger(PortUtil.class);
	
	/**
	 * 端口的检测与释放
	 */
	public static void checkPortAndRelease(int port){
		if(isPortUsed(port)){
			logger.info("端口【"+port+"】被占据");
			List<Integer> pidLists = getPidsByPort(port);
			for (Integer pid : pidLists) {
				killProcessByPid(pid);
			}
		}
	}

	/**
	 * 根据设备的数量来去创建端口号的集合
	 * 
	 * @param startPort
	 *            起始端口号
	 * @param deviceNum
	 *            设备的数量
	 * @return
	 */
	public static List<Integer> getPortList(int startPort, int deviceNum) {
		// 1、Appium的工作端口 4723 4725 4727 4729 ...
		// 2、uiautomator2的工作端口8200 8202 8204 8206 ...
		List<Integer> portList = new ArrayList<Integer>();
		for (int i = 0; i < deviceNum; i++) {
			portList.add(startPort);
			startPort = startPort + 2;
		}
		return portList;
	}

	/**
	 * 判断端口是否有被占用
	 * 
	 * @param port
	 *            对应端口号
	 */
	public static Boolean isPortUsed(int port) {
		// 1、执行netstat -ano | find “端口号”
		List<String> result = CmdUtil.execCmd("netstat -ano | findstr " + port);
		// System.out.println(result);
		// 通过空白字符\s进行分割
		for (String content : result) {
			//输出结果内容中必须要包含“LISTENING”我们才认为是对应的进程
			if(content.contains("LISTENING")){
				return true;
				//String[] splitInfos = content.split("\\s");
				//通过空白字符分割的最后一个元素就是为进程号
				//System.out.println(splitInfos[splitInfos.length - 1]);
			}
		}
		return false;
	}
	
	/**
	 * 由端口号得到所有对应的进程号
	 * @param port
	 */
	public static List<Integer> getPidsByPort(int port){
		//1、执行netstat -ano | find “端口号”
		List<String> result = CmdUtil.execCmd("netstat -ano | findstr " + port);
		//2、对结果解析得到所有的进程号
		List<Integer> pidList = new ArrayList<Integer>();
		for (String content : result) {
			//输出结果内容中必须要包含“LISTENING”我们才认为是对应的进程
			if(content.contains("LISTENING")){
				String[] splitInfos = content.split("\\s");
				//通过空白字符分割的最后一个元素就是为进程号
				//splitInfos[splitInfos.length - 1] 转换成整数类型？？
				int pid = Integer.valueOf(splitInfos[splitInfos.length - 1]);
				pidList.add(pid);
				//System.out.println(splitInfos[splitInfos.length - 1]);
			}
		}
		return pidList;
	}
	
	/**
	 * 由进程号pid去杀死对应的进程
	 * @param pid
	 */
	public static void killProcessByPid(int pid){
		//1、执行taskkill -f -pid “进程号 ”
		List<String> result = CmdUtil.execCmd("taskkill -f -pid "+pid);
		//2、遍历集合，判断集合内容有没有包含“成功”
		for (String content : result) {
			if(content.contains("成功")){
				logger.info("杀死进程【"+pid+"】成功");
			}else if (content.contains("错误")) {
				logger.info("杀死进程【"+pid+"】失败-进程号错误，请检查！！");
			}
		}
	}

	public static void main(String[] args) {
		//List<Integer> result = getPidsByPort(4725);
		//System.out.println(result);
		killProcessByPid(279292);
	}
}
