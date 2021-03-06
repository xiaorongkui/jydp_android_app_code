package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 *
 * @author Yi Shan Xiang
 *         文件名称： 联系客户返回实体类
 *         email: 380948730@qq.com
 */

public class CustomerServiceRes extends BaseRes implements Serializable {


    /**
     * pageNumber : 0
     * totalNumber : 7
     * totalPageNumber : 1
     * userFeedbackList : [{"addTime":1523606744000,"backerAccount":1,"feedbackContent":"啥哈哈哈哈",
     * "feedbackTitle":"啥哈哈哈哈","handleContent":1,"handleStatus":1,"handleTime":1,"id":221,"userAccount":"syl001",
     * "userId":313}]
     * webAppPath : /jydp
     */

    private int pageNumber; //页码
    private int totalNumber; //总数量
    private int totalPageNumber;//总页数
    private String webAppPath; //项目名
    private List<UserFeedbackListBean> userFeedbackList; //意见反馈

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

        private long addTime; //反馈时间
        private String backerAccount;//处理的后台管理员帐号
        private String feedbackContent; //反馈内容
        private String feedbackTitle; //反馈标题
        private String handleContent; //处理说明
        private int handleStatus; //处理状态，1：待处理，2：处理中，3：已处理
        private long handleTime; //处理时间
        private int id; //记录Id
        private String userAccount; //用户账号
        private int userId; //用户Id

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
