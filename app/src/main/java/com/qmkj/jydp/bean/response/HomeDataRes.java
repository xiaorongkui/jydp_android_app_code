package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/15
 * email：dovexiaoen@163.com
 * description:
 */

public class HomeDataRes extends BaseRes {

    private List<SystemAdsHomepagesListBean> systemAdsHomepagesList;
    private List<SystemBusinessesPartnerListBean> systemBusinessesPartnerList;
    private List<SystemNoticeListBean> systemNoticeList;
    private List<TransactionUserDealListBean> transactionUserDealList;

    public List<SystemAdsHomepagesListBean> getSystemAdsHomepagesList() {
        return systemAdsHomepagesList;
    }

    public void setSystemAdsHomepagesList(List<SystemAdsHomepagesListBean> systemAdsHomepagesList) {
        this.systemAdsHomepagesList = systemAdsHomepagesList;
    }

    public List<SystemBusinessesPartnerListBean> getSystemBusinessesPartnerList() {
        return systemBusinessesPartnerList;
    }

    public void setSystemBusinessesPartnerList(List<SystemBusinessesPartnerListBean> systemBusinessesPartnerList) {
        this.systemBusinessesPartnerList = systemBusinessesPartnerList;
    }

    public List<SystemNoticeListBean> getSystemNoticeList() {
        return systemNoticeList;
    }

    public void setSystemNoticeList(List<SystemNoticeListBean> systemNoticeList) {
        this.systemNoticeList = systemNoticeList;
    }

    public List<TransactionUserDealListBean> getTransactionUserDealList() {
        return transactionUserDealList;
    }

    public void setTransactionUserDealList(List<TransactionUserDealListBean> transactionUserDealList) {
        this.transactionUserDealList = transactionUserDealList;
    }

    public static class SystemAdsHomepagesListBean {
        /**
         * addTime : 1523257344000
         * adsImageUrl : /upload/adImage/20180409/2018040915022388960979894.jpg
         * adsImageUrlFormat : http://test.oksheng.com
         * .cn/fileservice/upload/adImage/20180409/2018040915022388960979894.jpg
         * adsTitle : new12
         * id : 210
         * rankNumber : 1
         * wapLinkUrl :
         * webLinkUrl :
         */

        private long addTime;
        private String adsImageUrl;
        private String adsImageUrlFormat;
        private String adsTitle;
        private int id;
        private int rankNumber;
        private String wapLinkUrl;
        private String webLinkUrl;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getAdsImageUrl() {
            return adsImageUrl;
        }

        public void setAdsImageUrl(String adsImageUrl) {
            this.adsImageUrl = adsImageUrl;
        }

        public String getAdsImageUrlFormat() {
            return adsImageUrlFormat;
        }

        public void setAdsImageUrlFormat(String adsImageUrlFormat) {
            this.adsImageUrlFormat = adsImageUrlFormat;
        }

        public String getAdsTitle() {
            return adsTitle;
        }

        public void setAdsTitle(String adsTitle) {
            this.adsTitle = adsTitle;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRankNumber() {
            return rankNumber;
        }

        public void setRankNumber(int rankNumber) {
            this.rankNumber = rankNumber;
        }

        public String getWapLinkUrl() {
            return wapLinkUrl;
        }

        public void setWapLinkUrl(String wapLinkUrl) {
            this.wapLinkUrl = wapLinkUrl;
        }

        public String getWebLinkUrl() {
            return webLinkUrl;
        }

        public void setWebLinkUrl(String webLinkUrl) {
            this.webLinkUrl = webLinkUrl;
        }
    }

    public static class SystemBusinessesPartnerListBean {
        /**
         * addTime : 1523327040000
         * businessesImageUrl : /upload/systemBusinessesPartner/20180410/2018041010235923894083650.png
         * businessesImageUrlFormat : http://test.oksheng.com
         * .cn/fileservice/upload/systemBusinessesPartner/20180410/2018041010235923894083650.png
         * businessesName : 阿里巴巴吧
         * id : 189
         * rankNumber : 1
         * wapLinkUrl : 范德萨
         * webLinkUrl : 阿斯蒂芬
         */

        private long addTime;
        private String businessesImageUrl;
        private String businessesImageUrlFormat;
        private String businessesName;
        private int id;
        private int rankNumber;
        private String wapLinkUrl;
        private String webLinkUrl;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getBusinessesImageUrl() {
            return businessesImageUrl;
        }

        public void setBusinessesImageUrl(String businessesImageUrl) {
            this.businessesImageUrl = businessesImageUrl;
        }

        public String getBusinessesImageUrlFormat() {
            return businessesImageUrlFormat;
        }

        public void setBusinessesImageUrlFormat(String businessesImageUrlFormat) {
            this.businessesImageUrlFormat = businessesImageUrlFormat;
        }

        public String getBusinessesName() {
            return businessesName;
        }

        public void setBusinessesName(String businessesName) {
            this.businessesName = businessesName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRankNumber() {
            return rankNumber;
        }

        public void setRankNumber(int rankNumber) {
            this.rankNumber = rankNumber;
        }

        public String getWapLinkUrl() {
            return wapLinkUrl;
        }

        public void setWapLinkUrl(String wapLinkUrl) {
            this.wapLinkUrl = wapLinkUrl;
        }

        public String getWebLinkUrl() {
            return webLinkUrl;
        }

        public void setWebLinkUrl(String webLinkUrl) {
            this.webLinkUrl = webLinkUrl;
        }
    }

    public static class SystemNoticeListBean {
        /**
         * addTime : 1523264526000
         * content : <p>11</p>
         * id : 223
         * noticeTitle : new
         * noticeType : new11
         * noticeUrl : /upload/noticeImage/20180409/2018040917020500784275059.jpg
         * noticeUrlFormat : http://test.oksheng.com
         * .cn/fileservice/upload/noticeImage/20180409/2018040917020500784275059.jpg
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

    public static class TransactionUserDealListBean {
        /**
         * buyOnePrice : 0
         * change : 0
         * currencyId : 198
         * currencyImg : /upload/transactionCyrrency/20180409/2018040917305092264844579.jpg
         * currencyImgUrl : http://test.oksheng.com
         * .cn/fileservice/upload/transactionCyrrency/20180409/2018040917305092264844579.jpg
         * currencyName : 工匠币
         * currencyShortName : GJ
         * latestPrice : 0
         * sellOnePrice : 0
         * volume : 0
         * yesterdayLastPrice : 0
         */

        private double buyOnePrice;
        private double change;
        private int currencyId;
        private String currencyImg;
        private String currencyImgUrl;
        private String currencyName;
        private String currencyShortName;
        private double latestPrice;
        private double sellOnePrice;
        private double volume;
        private double yesterdayLastPrice;

        public double getBuyOnePrice() {
            return buyOnePrice;
        }

        public void setBuyOnePrice(int buyOnePrice) {
            this.buyOnePrice = buyOnePrice;
        }

        public double getChange() {
            return change;
        }

        public void setChange(int change) {
            this.change = change;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyImg() {
            return currencyImg;
        }

        public void setCurrencyImg(String currencyImg) {
            this.currencyImg = currencyImg;
        }

        public String getCurrencyImgUrl() {
            return currencyImgUrl;
        }

        public void setCurrencyImgUrl(String currencyImgUrl) {
            this.currencyImgUrl = currencyImgUrl;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getCurrencyShortName() {
            return currencyShortName;
        }

        public void setCurrencyShortName(String currencyShortName) {
            this.currencyShortName = currencyShortName;
        }

        public double getLatestPrice() {
            return latestPrice;
        }

        public void setLatestPrice(int latestPrice) {
            this.latestPrice = latestPrice;
        }

        public double getSellOnePrice() {
            return sellOnePrice;
        }

        public void setSellOnePrice(int sellOnePrice) {
            this.sellOnePrice = sellOnePrice;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public double getYesterdayLastPrice() {
            return yesterdayLastPrice;
        }

        public void setYesterdayLastPrice(int yesterdayLastPrice) {
            this.yesterdayLastPrice = yesterdayLastPrice;
        }
    }
}
