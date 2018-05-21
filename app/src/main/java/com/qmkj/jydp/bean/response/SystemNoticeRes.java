package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/21.
 */

public class SystemNoticeRes extends BaseRes implements Serializable {


    /**
     * pageNumber : 0
     * systemNoticeList : [{"addTime":1525314643000,"content":"<p>53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告v<img src=\"http://test.oksheng.com.cn/fileservice/upload/ueditor/20180503/1525314635977062214.jpg\" title=\"1525314635977062214.jpg\" alt=\"test_301.jpg\" style=\"float: right;\"/><\/p>","id":253,"noticeTitle":"53新增公告53新增公告53新增公告53新增公告53新增公告53","noticeType":"53新增公告53新增公告53新增","noticeUrl":"1","noticeUrlFormat":"http://test.oksheng.com.cn/fileservice1","rankNumber":1}]
     * totalNumber : 23
     * totalPageNumber : 3
     */

    private int pageNumber;
    private int totalNumber;
    private int totalPageNumber;
    private List<SystemNoticeListBean> systemNoticeList;

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

    public List<SystemNoticeListBean> getSystemNoticeList() {
        return systemNoticeList;
    }

    public void setSystemNoticeList(List<SystemNoticeListBean> systemNoticeList) {
        this.systemNoticeList = systemNoticeList;
    }

    public static class SystemNoticeListBean {
        /**
         * addTime : 1525314643000
         * content : <p>53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告53新增公告v<img src="http://test.oksheng.com.cn/fileservice/upload/ueditor/20180503/1525314635977062214.jpg" title="1525314635977062214.jpg" alt="test_301.jpg" style="float: right;"/></p>
         * id : 253
         * noticeTitle : 53新增公告53新增公告53新增公告53新增公告53新增公告53
         * noticeType : 53新增公告53新增公告53新增
         * noticeUrl : 1
         * noticeUrlFormat : http://test.oksheng.com.cn/fileservice1
         * rankNumber : 1
         */

        private long addTime;
        private String content;
        private int id;
        private String noticeTitle;
        private String noticeType;
        private String noticeUrl;
        private String noticeUrlFormat;
        private int rankNumber;

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNoticeTitle() {
            return noticeTitle;
        }

        public void setNoticeTitle(String noticeTitle) {
            this.noticeTitle = noticeTitle;
        }

        public String getNoticeType() {
            return noticeType;
        }

        public void setNoticeType(String noticeType) {
            this.noticeType = noticeType;
        }

        public String getNoticeUrl() {
            return noticeUrl;
        }

        public void setNoticeUrl(String noticeUrl) {
            this.noticeUrl = noticeUrl;
        }

        public String getNoticeUrlFormat() {
            return noticeUrlFormat;
        }

        public void setNoticeUrlFormat(String noticeUrlFormat) {
            this.noticeUrlFormat = noticeUrlFormat;
        }

        public int getRankNumber() {
            return rankNumber;
        }

        public void setRankNumber(int rankNumber) {
            this.rankNumber = rankNumber;
        }
    }
}
