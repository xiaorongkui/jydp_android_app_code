package com.qmkj.jydp.common;

import com.qmkj.jydp.util.MD5Util;

/**
 * APP网络请求基本参数配置
 */
public final class AppNetConfig {
    //正式服地址 http://www.extez.com/
    //测试服地址 http://192.168.4.216:8080/jydp/
    public static final String BASE_URL = "http://192.168.4.216:8080/jydp/";
    //请求公钥
    public static final String JYDP_PUBLIC_KEY = "524bee776fe6267bbcf3b3d3ac2ecbf8";
    //请求私钥 测试服:2c4e87caae0de93675387f3fcea369f8
    private static final String JYDP_SECRET_KEY = "2c4e87caae0de93675387f3fcea369f8";
    //请求签名
    public static final String JYDP_SECRET_SIGN = MD5Util.toMd5(JYDP_PUBLIC_KEY + JYDP_SECRET_KEY);
    //连接超时时间
    public static final int connectionTime = 20;
    //kline里的H5页面
    public static final String kline_url = BASE_URL + "/userWap/wapDealRecord/toChartPage/";
    //系统公告URL
    public static final String SYSTEM_NOTICE_URL = BASE_URL + "/userWap/wapSystemNotice/showNoticeDetailApp/";
    //热门话题URL
    public static final String HOT_TOPIC_URL = BASE_URL + "/userWap/wapSystemHot/showHotDetailApp/";
    //帮助中心URL
    public static final String HELP_CENTER_URL = BASE_URL + "/userWap/wapHelpCenter/showApp/";
}
