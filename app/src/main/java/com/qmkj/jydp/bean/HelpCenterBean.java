package com.qmkj.jydp.bean;

import java.io.Serializable;

/**
 * 创建日期：2018/5/23
 *
 * @author Yi Shan Xiang
 *         文件名称： HelpCenterBean
 *         email: 380948730@qq.com
 */

public class HelpCenterBean implements Serializable {
    private String name;
    private String id;

    public HelpCenterBean(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}