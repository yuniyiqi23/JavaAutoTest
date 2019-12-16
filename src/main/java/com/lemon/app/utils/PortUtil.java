package com.lemon.app.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 歪歪欧巴
 * @Title: PortUtil.java
 * @Description: TODO(端口管理的工具类)
 * @date 2019年11月26日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class PortUtil {

    /**
     * 根据设备的数量来去创建端口号的集合
     *
     * @param startPort 起始端口号
     * @param deviceNum 设备的数量
     * @return
     */
    public static List<Integer> getPortList(int startPort, int deviceNum) {
        //1、Appium的工作端口 4723 4725 4727 4729 ...
        //2、uiautomator2的工作端口8200 8202 8204 8206 ...
        List<Integer> portList = new ArrayList<Integer>();
        for (int i = 0; i < deviceNum; i++) {
            portList.add(startPort);
            startPort = startPort + 2;
        }
        return portList;
    }

    public static void main(String[] args) {
        System.out.println(getPortList(8200, 4));
    }
}
