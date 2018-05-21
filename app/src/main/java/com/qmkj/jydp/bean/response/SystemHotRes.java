package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 * @author Yi Shan Xiang
 * 文件名称： SystemHotRes
 * email: 380948730@qq.com
 */

public class SystemHotRes extends BaseRes implements Serializable{


    /**
     * pageNumber : 0
     * systemHotList : [{"addTime":1525314697000,"content":"<p>53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题<img src=\"http://test.oksheng.com.cn/fileservice/upload/ueditor/20180503/1525314687167011661.jpg\" title=\"1525314687167011661.jpg\" alt=\"test_300.jpg\" style=\"float: right;\"/><\/p>","id":131,"noticeTitle":"53新增话题53新增话题53新增话题53新增话题53新增话题53","noticeType":"53新增话题53新增话题53新增","noticeUrl":"1","noticeUrlFormat":"http://test.oksheng.com.cn/fileservice1","rankNumber":1}]
     * totalNumber : 11
     * totalPageNumber : 2
     */

    private String pageNumber;
    private int totalNumber;
    private int totalPageNumber;
    private List<SystemHotListBean> systemHotList;

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
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

    public List<SystemHotListBean> getSystemHotList() {
        return systemHotList;
    }

    public void setSystemHotList(List<SystemHotListBean> systemHotList) {
        this.systemHotList = systemHotList;
    }

    public static class SystemHotListBean {
        /**
         * addTime : 1525314697000
         * content : <p>53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题53新增话题<img src="http://test.oksheng.com.cn/fileservice/upload/ueditor/20180503/1525314687167011661.jpg" title="1525314687167011661.jpg" alt="test_300.jpg" style="float: right;"/></p>
         * id : 131
         * noticeTitle : 53新增话题53新增话题53新增话题53新增话题53新增话题53
         * noticeType : 53新增话题53新增话题53新增
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
