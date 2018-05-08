package com.qmkj.jydp.bean;

import java.io.Serializable;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginBean implements Serializable {
    public String sessionId;

    public String getSessionid() {
        return sessionId;
    }

    public void setSessionid(String sessionid) {
        this.sessionId = sessionid;
    }
}
