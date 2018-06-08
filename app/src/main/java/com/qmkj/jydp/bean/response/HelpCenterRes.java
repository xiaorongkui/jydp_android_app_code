package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * 创建日期：2018/5/23
 *
 * @author Yi Shan Xiang
 *         文件名称： HelpCenterRes
 *         email: 380948730@qq.com
 */

public class HelpCenterRes extends BaseRes implements Serializable {
    /**
     * helpId : 101014
     * helpTitle : 公司简介
     * systemHelpDO : {"addTime":1523607467000,"content":"<p>公司简介<\/p>","helpTitle":"公司简介","helpType":"用户帮助",
     * "id":101014}
     */

    private int helpId;
    private String helpTitle;
    private SystemHelpDOBean systemHelpDO;

    public int getHelpId() {
        return helpId;
    }

    public void setHelpId(int helpId) {
        this.helpId = helpId;
    }

    public String getHelpTitle() {
        return helpTitle;
    }

    public void setHelpTitle(String helpTitle) {
        this.helpTitle = helpTitle;
    }

    public SystemHelpDOBean getSystemHelpDO() {
        return systemHelpDO;
    }

    public void setSystemHelpDO(SystemHelpDOBean systemHelpDO) {
        this.systemHelpDO = systemHelpDO;
    }

    public static class SystemHelpDOBean {
        /**
         * addTime : 1523607467000
         * content : <p>公司简介</p>
         * helpTitle : 公司简介
         * helpType : 用户帮助
         * id : 101014
         */

        private long addTime;
        private String content;
        private String helpTitle;
        private String helpType;
        private int id;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHelpTitle() {
            return helpTitle;
        }

        public void setHelpTitle(String helpTitle) {
            this.helpTitle = helpTitle;
        }

        public String getHelpType() {
            return helpType;
        }

        public void setHelpType(String helpType) {
            this.helpType = helpType;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
