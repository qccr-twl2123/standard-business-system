/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.arcsoft.facego.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三方接口调用工具
 * @author xierongli
 * @version $$Id: uber-system, v 0.1 2018/4/10 上午10:04 mark1xie Exp $$
 */
public class ThridPartyInterface {

    public final static String carBrand = "/car/brand";
    public final static String CAR_LIST = "/car/carlist";
    public final static String CAR_DETAIL ="/car/detail";


    public static  String invokeAliCloudApi(String path, Map<String, String> querys){
        String host = "http://jisucxdq.market.alicloudapi.com";
        String method = "GET";
        String appcode = "279d07726e68440ea1b820dd1132ee66";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String result =EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
