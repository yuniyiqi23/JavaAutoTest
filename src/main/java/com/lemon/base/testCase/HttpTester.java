package com.lemon.base.testCase;

import com.lemon.base.pojo.ApiCaseDetail;
import com.lemon.base.utils.HttpUtil;
import org.testng.annotations.Test;

public class HttpTester {

    @Test(dataProvider = "apiCaseData", dataProviderClass = com.lemon.base.testCase.TestCase.class)
    public void testCase1(ApiCaseDetail apiCaseDetail) {
        System.out.println(apiCaseDetail.getCaseId());
    }

    @Test
    public void testCase2() {
        String url = "http://120.78.128.25:8766/futureloan/member/59/info";
        String jsonString = "{\n" +
                "	\"mobile_phone\":\"15158011239\",\n" +
                "	\"pwd\":\"111111_a\",\n" +
                "	\"reg_name\":\"Adam\"\n" +
                "}";
        System.out.println(HttpUtil.get(url, jsonString));
    }

}
