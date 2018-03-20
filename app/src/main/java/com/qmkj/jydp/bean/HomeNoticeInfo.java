package com.qmkj.jydp.bean;

/**
 * @author neo.duan
 * @date 2018/1/8 19:26
 * @desc 公告实体
 */
public class HomeNoticeInfo {
    String id;
    /**
     * 公告类型
     */
    String noticeType;
    /**
     * 公告title
     */
    String noticeTitle;


    /**
     * 图片url
     */
    String noticeUrl;

    /**
     * 置顶时间
     */
    long topTime;

    public HomeNoticeInfo(String id, String noticeType, String noticeTitle, String noticeUrl, long topTime) {
        this.id = id;
        this.noticeType = noticeType;
        this.noticeTitle = noticeTitle;
        this.noticeUrl = noticeUrl;
        this.topTime = topTime;
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id;
    }

    public String getNoticeType() {
        return noticeType == null ? "" : noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType == null ? "" : noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle == null ? "" : noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle == null ? "" : noticeTitle;
    }

    public long getTopTime() {
        return topTime;
    }

    public void setTopTime(long topTime) {
        this.topTime = topTime;
    }

    public String getNoticeUrl() {
        return noticeUrl == null ? "" : noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl == null ? "" : noticeUrl;
    }
}
