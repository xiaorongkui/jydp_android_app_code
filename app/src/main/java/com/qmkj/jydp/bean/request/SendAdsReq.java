package com.qmkj.jydp.bean.request;

/**
 * 创建日期：2018/5/23
 * @author Yi Shan Xiang
 * 文件名称： SendAdsReq
 * email: 380948730@qq.com
 */

public class SendAdsReq extends BaseReq{

    private String ara;	//地区	string
    private String currencyId;	//	货币id	string
    private String maxNumber;	//	最大金额	string
    private String minNumbe;    // 	最小金额	string
    private String orderType;	//	挂单类型 1：出售，2：回购	string
    private String pendingRatio;	//	挂单比例	string
    private String  selectList;	//收款方式（多个逗号分隔）

    public String getAra() {
        return ara;
    }

    public void setAra(String ara) {
        this.ara = ara;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(String maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getMinNumbe() {
        return minNumbe;
    }

    public void setMinNumbe(String minNumbe) {
        this.minNumbe = minNumbe;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPendingRatio() {
        return pendingRatio;
    }

    public void setPendingRatio(String pendingRatio) {
        this.pendingRatio = pendingRatio;
    }

    public String getSelectList() {
        return selectList;
    }

    public void setSelectList(String selectList) {
        this.selectList = selectList;
    }
}
