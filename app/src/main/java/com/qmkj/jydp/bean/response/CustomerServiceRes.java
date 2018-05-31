package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 * @author Yi Shan Xiang
 * 文件名称： CustomerServiceRes
 * email: 380948730@qq.com
 */

public class CustomerServiceRes extends BaseRes implements Serializable{


    /**
     * pageNumber : 0
     * totalNumber : 7
     * totalPageNumber : 1
     * userFeedbackList : [{"addTime":1523606744000,"backerAccount":1,"feedbackContent":"啥哈哈哈哈","feedbackTitle":"啥哈哈哈哈","handleContent":1,"handleStatus":1,"handleTime":1,"id":221,"userAccount":"syl001","userId":313}]
     * webAppPath : /jydp
     */

    private int pageNumber;
    private int totalNumber;
    private int totalPageNumber;
    private String webAppPath;
    private List<UserFeedbackListBean> userFeedbackList;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public List<UserFeedbackListBean> getUserFeedbackList() {
        return userFeedbackList;
    }

    public void setUserFeedbackList(List<UserFeedbackListBean> userFeedbackList) {
        this.userFeedbackList = userFeedbackList;
    }

    public static class UserFeedbackListBean {
        /**
         * addTime : 1523606744000
         * backerAccount : 1
         * feedbackContent : 啥哈哈哈哈
         * feedbackTitle : 啥哈哈哈哈
         * handleContent : 1
         * handleStatus : 1
         * handleTime : 1
         * id : 221
         * userAccount : syl001
         * userId : 313
         */

        private long addTime;
        private String backerAccount;
        private String feedbackContent;
        private String feedbackTitle;
        private String handleContent;
        private int handleStatus;
        private long handleTime;
        private int id;
        private String userAccount;
        private int userId;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getBackerAccount() {
            return backerAccount;
        }

        public void setBackerAccount(String backerAccount) {
            this.backerAccount = backerAccount;
        }

        public String getFeedbackContent() {
            return feedbackContent;
        }

        public void setFeedbackContent(String feedbackContent) {
            this.feedbackContent = feedbackContent;
        }

        public String getFeedbackTitle() {
            return feedbackTitle;
        }

        public void setFeedbackTitle(String feedbackTitle) {
            this.feedbackTitle = feedbackTitle;
        }

        public String getHandleContent() {
            return handleContent;
        }

        public void setHandleContent(String handleContent) {
            this.handleContent = handleContent;
        }

        public int getHandleStatus() {
            return handleStatus;
        }

        public void setHandleStatus(int handleStatus) {
            this.handleStatus = handleStatus;
        }

        public long getHandleTime() {
            return handleTime;
        }

        public void setHandleTime(long handleTime) {
            this.handleTime = handleTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
