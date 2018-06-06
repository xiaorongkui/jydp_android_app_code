package com.qmkj.jydp.bean;

/**
 * author：rongkui.xiao --2018/3/21
 * email：dovexiaoen@163.com
 * description:我的页面的条目对象
 */

public class MinelistInfo {
    public int leftIcon;
    public String name;
    public int rightIcon;

    public MinelistInfo(int leftIcon, String name, int rightIcon) {
        this.leftIcon = leftIcon;
        this.name = name;
        this.rightIcon = rightIcon;
    }
}
