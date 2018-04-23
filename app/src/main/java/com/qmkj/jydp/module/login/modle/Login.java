package com.qmkj.jydp.module.login.modle;

import com.qmkj.jydp.net.HttpCallBack;

import java.util.Map;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public interface Login {
    void getCurrentPrice(Map<String, Object> maps, HttpCallBack listener, boolean isShowProgress);
}
