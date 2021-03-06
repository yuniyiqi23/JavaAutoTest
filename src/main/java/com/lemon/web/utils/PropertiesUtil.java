package com.lemon.web.utils;/**
 * Created by TW on 2019/10/29 20:32
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *@program: JavaAutoTest
 *@description: 获取配置文件信息
 *@author: liu yan
 *@create: 2019-10-29 20:32
 */
public class PropertiesUtil {

    private static Properties properties = new Properties();

    /**
    * @Description: 从配置文件读取,static代码块，执行一遍
    * @Param:
    * @return:
    * @Author: Adam
    * @Date: 2019/10/29
    */
    static {
        try {
            InputStream inputStream = PropertiesUtil.class.getResourceAsStream("/url" +
                    ".properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertieValue(String urlKey){
        return (String)properties.get(urlKey);
    }

    public static void main(String[] args){
        System.out.println(properties.get("login_url"));
    }

}
