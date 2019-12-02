package com.lemon.app.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.lemon.app.pojo.Locator;
import com.lemon.app.pojo.Page;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * @author 歪歪欧巴
 * @Title: XmlUtil.java
 * @Description: TODO(描述)
 * @date 2019年11月21日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class XmlUtil {

    //所有的UI库的信息保存到这里
    public static List<Page> listPage = new ArrayList<Page>();

    //静态代码块
    static {
        readUILibrary();
    }

    public static void readUILibrary() {
        //1、实例化saxReader对象
        SAXReader saxReader = new SAXReader();
        try {
            //2、读取xml文件
            Document document = saxReader.read(new File
                    ("src/main/resources/UILibrary.xml"));
            //3、对document对象一层一层的解析
            Element rootElement = document.getRootElement();
            List<Element> pagesElement = rootElement.elements("page");
            for (Element pageElement : pagesElement) {
                //获取到activityName和pageDesc属性
                String activityName = pageElement.attributeValue("activityName");
                String pageDesc = pageElement.attributeValue("pageDesc");
                //获取到locator子元素
                List<Element> locatorsElement = pageElement.elements("locator");

                List<Locator> listLocator = new ArrayList<Locator>();
                for (Element locatorElement : locatorsElement) {
                    //获取到locatorBy、locatorValue、locatorDesc
                    String locatorBy = locatorElement.attributeValue("locatorBy");
                    String locatorValue = locatorElement.attributeValue("locatorValue");
                    String locatorDesc = locatorElement.attributeValue("locatorDesc");
                    Locator locator = new Locator(locatorBy, locatorValue, locatorDesc);
                    listLocator.add(locator);
                }
                Page page = new Page(activityName, pageDesc, listLocator);
                listPage.add(page);
            }

        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readUILibrary();
        for (Page page : listPage) {
            System.out.println("页面类名：" + page.getActivityName());
            System.out.println("页面关键字：" + page.getPageDesc());
            List<Locator> listLocator = page.getListLocator();
            for (Locator locator : listLocator) {
                System.out.println("元素的定位方式：" + locator.getLocatorBy());
                System.out.println("元素定位的值:" + locator.getLocatorValue());
                System.out.println("元素的关键字:" + locator.getLocatorDesc());
            }
        }
    }

}
