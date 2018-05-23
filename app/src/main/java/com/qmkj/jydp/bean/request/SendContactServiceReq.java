package com.qmkj.jydp.bean.request;

/**
 * 创建日期：2018/5/23
 * @author Yi Shan Xiang
 * 文件名称： 意见反馈内容
 * email: 380948730@qq.com
 */
public class SendContactServiceReq {
   private String feedbackContent;
   private String feedbackTitle;

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
}
