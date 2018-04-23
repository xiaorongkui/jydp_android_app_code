package com.qmkj.jydp.module.home.modle;

import com.qmkj.jydp.net.HttpCallBack;

import java.util.Map;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:首页的接口
 */

public interface Home {
    void getHomeData(Map<String, Object> maps, HttpCallBack listener, boolean isShowProgress);
}
