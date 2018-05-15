package com.qmkj.jydp.bean.request;

import com.qmkj.jydp.util.CommonUtil;

/**
 * author：rongkui.xiao --2018/5/14
 * email：dovexiaoen@163.com
 * description:请求参数基类
 */

public class BaseReq {
    public String token = CommonUtil.getToken();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
