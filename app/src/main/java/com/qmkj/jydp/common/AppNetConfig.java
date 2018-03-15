package com.qmkj.jydp.common;

import com.qmkj.jydp.util.CommonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产接口地址：https://api.zifae.com/zifae/api/
 * 新测试环境地址：http://140.207.53.166:8080/zifae_pre/
 */

public final class AppNetConfig {
    public static final String urlPath = "jydp/sljz/";//测试环境
    public static final String BASE_URL = "http://192.168.59.104:8081/";//测试环境


    //.....所有的项目当中接口的请求url全部配置在这里.....//
    private static Map<String, Object> baseFiel = new HashMap<>();

    public static Map<String, Object> getBaseMaps() {
        if (!baseFiel.isEmpty()) baseFiel.clear();
        baseFiel.put("token", CommonUtil.getTokenId());
        return baseFiel;
    }
}
