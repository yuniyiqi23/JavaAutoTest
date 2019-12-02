package com.lemon.app.pojo;

import java.util.List;

/**
 * @author 歪歪欧巴
 * @Title: Page.java
 * @Description: TODO(页面对象)
 * @date 2019年11月21日
 * @Copyright:湖南省零檬信息技术有限公司. All rights reserved.
 */
public class Page {

    private String activityName;
    private String pageDesc;
    //一个页面会有多个元素
    private List<Locator> listLocator;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getPageDesc() {
        return pageDesc;
    }

    public void setPageDesc(String pageDesc) {
        this.pageDesc = pageDesc;
    }

    public List<Locator> getListLocator() {
        return listLocator;
    }

    public void setListLocator(List<Locator> listLocator) {
        this.listLocator = listLocator;
    }

    public Page(String activityName, String pageDesc, List<Locator> listLocator) {
        super();
        this.activityName = activityName;
        this.pageDesc = pageDesc;
        this.listLocator = listLocator;
    }

    @Override
    public String toString() {
        return "Page [activityName=" + activityName + ", pageDesc=" + pageDesc + ", listLocator=" + listLocator + "]";
    }


}
