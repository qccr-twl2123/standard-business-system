package com.business.system.facetech.config;

import com.business.system.util.ConfigUtil;

public class FreeSdkConfig {
    /**
     * fd appid  请到官网申请  http://www.arcsoft.com.cn/ai/
     */
    public static String fdAppID = ConfigUtil.getProperty("fdappid");
    /**
     * fd key  请到官网申请  http://www.arcsoft.com.cn/ai/
     */
    public static String fdSDKKey = ConfigUtil.getProperty("fdsdkkey");
    /**
     * fd  用于数值表示的最小人脸尺寸 有效值范围[2,50] 推荐值 16
     */
    public static int fdNscanle = Integer.parseInt(ConfigUtil.getProperty("fdappid"));
    /**
     * fd  分配给引擎使用的内存地址  单位：M    建议值：32M以上
     */
    public static int fdMemSize = Integer.parseInt(ConfigUtil.getProperty("fdMemSize"));
    /**
     * 期望的脸部检测角度的优先级  推荐值为0，0：为正脸
     */
    public static int fdOrientPriority = Integer.parseInt(ConfigUtil.getProperty("fdOrientPriority"));
    /**
     * fd  用户期望引擎最多能检测出的人脸数 有效值范围[1,100]，如不做特殊处理请用1
     */
    public static int fdMaxFaceNum = Integer.parseInt(ConfigUtil.getProperty("fdMaxFaceNum"));
    /**
     * fd  引擎池大小，建议值：4
     */
    public static int fdPoolSize = Integer.parseInt(ConfigUtil.getProperty("fdPoolSize"));

    /**
     * fr appid  请到官网申请  http:/**www.arcsoft.com.cn/ai/
     */
    public static String frAppID = ConfigUtil.getProperty("frAppID");
    /**
     * fr key  请到官网申请  http:/**www.arcsoft.com.cn/ai/
     */
    public static String frSDKKey = ConfigUtil.getProperty("frSDKKey");
    /**
     * fr  分配给引擎使用的内存地址  单位：M    建议值：32M以上
     */
    public static int frMemSize = Integer.parseInt(ConfigUtil.getProperty("frMemSize"));
    /**
     * fr  线程池数量，建议值：20
     */
    public static int frPoolSize = Integer.parseInt(ConfigUtil.getProperty("frPoolSize"));
    /**
     * FR 搜索feature线程数，和内核有关，数值<=内核数
     */
    public static int maxThreadNum = Integer.parseInt(ConfigUtil.getProperty("maxThreadNum"));
    /**
     * 图片存放的目录，后面跟/image
     */
    public static String imagedir = ConfigUtil.getProperty("imagedir");
    /**
     * 图片删除后要移动到的地址,当前默认为image同级目录
     */
    public static String imageCanDeleteDir = "imagesCanDelete";
    /**
     * 存放图片目录的名称  如：/image/  放到的是root下
     */
    public static String imgurlprefix = ConfigUtil.getProperty("imgurlprefix");
}
