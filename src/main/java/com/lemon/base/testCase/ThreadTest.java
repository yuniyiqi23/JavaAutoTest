package com.lemon.base.testCase;/**
 * Created by TW on 2019/9/25 14:20
 */

import com.lemon.base.utils.ParamUtils;
import org.apache.log4j.Logger;

/**
 * @program: JavaAutoTest
 * @description: 多线程测试类
 * @author: liu yan
 * @create: 2019-09-25 14:20
 */
public class ThreadTest{

    private static Logger logger = Logger.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        logger.info("Hello Log");
        logger.warn("Hello Log");
        logger.error("Hello Log");
    }

}
