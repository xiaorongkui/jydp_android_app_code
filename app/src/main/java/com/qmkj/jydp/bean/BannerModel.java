package com.qmkj.jydp.bean;

/**
 * @author neo.duan
 * @date 2018/1/11 0:28
 * @desc 首页banner条
 */
public class BannerModel {
    String adsImageUrl;
    String adsUrl;
    String adsTitle;

    public BannerModel(String adsImageUrl, String adsUrl, String adsTitle) {
        this.adsImageUrl = adsImageUrl;
        this.adsUrl = adsUrl;
        this.adsTitle = adsTitle;
    }

    public String getAdsImageUrl() {
        return adsImageUrl == null ? "" : adsImageUrl;
    }

    public void setAdsImageUrl(String adsImageUrl) {
        this.adsImageUrl = adsImageUrl == null ? "" : adsImageUrl;
    }

    public String getAdsUrl() {
        return adsUrl == null ? "" : adsUrl;
    }

    public void setAdsUrl(String adsUrl) {
        this.adsUrl = adsUrl == null ? "" : adsUrl;
    }

    public String getAdsTitle() {
        return adsTitle == null ? "" : adsTitle;
    }

    public void setAdsTitle(String adsTitle) {
        this.adsTitle = adsTitle == null ? "" : adsTitle;
    }
}
