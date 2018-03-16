package com.qmkj.jydp.module.login.modle;

import com.qmkj.jydp.net.HttpOnNextListener;

import java.util.Map;

/**
 * author：rongkui.xiao --2018/3/16
 * email：dovexiaoen@163.com
 * description:
 */

public interface LoginInterface {
    void getCurrentPrice(Map<String, Object> maps, HttpOnNextListener listener, boolean isShowProgress);
}
