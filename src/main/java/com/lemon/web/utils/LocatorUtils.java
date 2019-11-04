package com.lemon.web.utils;/**
 * Created by TW on 2019/11/4 18:42
 */

import com.lemon.web.pojo.Locator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaAutoTest
 * @description: 网页定位元素工具类
 * @author: liu yan
 * @create: 2019-11-04 18:42
 */
public class LocatorUtils {
    
    private static Map<String, Map<String, Locator>> getAllLocators;
    
    /** 
    * @Description: 静态代码块，获取全部定位元素
    * @Param:  
    * @return:  
    * @Author: Adam
    * @Date: 2019/11/4 
    */
    static {
        getAllLocators = getPageLocators();
    }

    public static Map<String, Locator> getLocatorsByPageName(String pageName){
        return getAllLocators.get(pageName);
    }

    public static Locator getLocatorByPageNameAndLocatorName(String pageName,
                String locatorName){
        return getAllLocators.get(pageName).get(locatorName);
    }

    /** 
    * @Description: 获取页面定位元素 
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.util.Map<java.lang.String,com.lemon.web.pojo.Locator>> 
    * @Author: Adam
    * @Date: 2019/11/4 
    */
    private static Map<String, Map<String, Locator>> getPageLocators() {
        InputStream inputStream = LocatorUtils.class.getResourceAsStream
                ("/page/page.xml");
        SAXReader saxReader = new SAXReader();
        Map<String, Map<String, Locator>> pagesMap = new HashMap<>();
        try {
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> pageElements = root.elements("page");
            // 查找页面
            for (Element page : pageElements) {
//                System.out.println(page.attributeValue("pageName"));
                List<Element> locatorElements = page.elements("locator");
                // 查找定位元素
                Map<String, Locator> locatorList = new HashMap<>();
                for (Element item : locatorElements){
                    String name = item.attributeValue("name");
                    Locator locator = new Locator(name,
                            item.attributeValue("type"),  item.attributeValue
                            ("value"));
                    locatorList.put(name, locator);
//                    System.out.println(item.attributeValue("name"));
//                    System.out.println(item.attributeValue("type"));
//                    System.out.println(item.attributeValue("value"));
                }
                pagesMap.put(page.attributeValue("pageName"), locatorList);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return pagesMap;
    }

    public static void main(String[] args) {
        Map<String, Map<String, Locator>> pagesMap = getPageLocators();

        Map<String, Locator> page = pagesMap.get("登录页面");
        Locator locator = page.get("手机号码输入框");
        System.out.println(locator.getType());
    }

}
