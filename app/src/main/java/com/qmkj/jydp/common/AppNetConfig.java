package com.qmkj.jydp.common;

/**
 * APP参数配置
 */
public final class AppNetConfig {
    //请求URL前缀
    public static final String urlPath = "/";
    //正式服地址 http://www.extez.com/
    public static final String BASE_URL = "http://www.extez.com/";
    //kline里的H5页面
    public static final String kline_url = BASE_URL + "/userWap/wapDealRecord/toChartPage/";
    //系统公告URL
    public static final String SYSTEM_NOTICE_URL = BASE_URL + "/userWap/wapSystemNotice/showNoticeDetailApp/";
    //热门话题URL
    public static final String HOT_TOPIC_URL = BASE_URL + "/userWap/wapSystemHot/showHotDetailApp/";
    //帮助中心URL
    public static final String HELP_CENTER_URL = BASE_URL + "/userWap/wapHelpCenter/showApp/";
}
