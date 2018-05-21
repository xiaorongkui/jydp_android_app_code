package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/17.
 */

public class DealerManagementRes extends BaseRes implements Serializable{

    /**
     * totalNumber : 11
     * totalPageNumber : 1
     * pageNumber : 0
     * otcTransactionPendOrderList : [{"otcPendingOrderNo":"601805160153825972","userId":313,"userAccount":"syl001","orderType":1,"currencyId":999,"currencyName":"XT","pendingRatio":1,"minNumber":1,"maxNumber":222,"pendingNumber":0,"dealNumber":0,"buyFee":0,"restBalanceLock":0,"area":"中国(CN)","pendingStatus":1,"endTime":"11","remark":"11","updateTime":"11","addTime":1526461297000},{"otcPendingOrderNo":"601805168541748216","userId":313,"userAccount":"syl001","orderType":1,"currencyId":999,"currencyName":"XT","pendingRatio":12,"minNumber":1,"maxNumber":2,"pendingNumber":0,"dealNumber":0,"buyFee":0,"restBalanceLock":0,"area":"中国(CN)","pendingStatus":1,"endTime":"11","remark":"11","updateTime":"11","addTime":1526460040000}]
     */

    private int totalNumber;
    private int totalPageNumber;
    private int pageNumber;
    private List<OtcTransactionPendOrderListBean> otcTransactionPendOrderList;

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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<OtcTransactionPendOrderListBean> getOtcTransactionPendOrderList() {
        return otcTransactionPendOrderList;
    }

    public void setOtcTransactionPendOrderList(List<OtcTransactionPendOrderListBean> otcTransactionPendOrderList) {
        this.otcTransactionPendOrderList = otcTransactionPendOrderList;
    }

    public static class OtcTransactionPendOrderListBean {
        /**
         * otcPendingOrderNo : 601805160153825972
         * userId : 313
         * userAccount : syl001
         * orderType : 1
         * currencyId : 999
         * currencyName : XT
         * pendingRatio : 1.0
         * minNumber : 1.0
         * maxNumber : 222.0
         * pendingNumber : 0.0
         * dealNumber : 0.0
         * buyFee : 0.0
         * restBalanceLock : 0.0
         * area : 中国(CN)
         * pendingStatus : 1
         * endTime : 11
         * remark : 11
         * updateTime : 11
         * addTime : 1526461297000
         */

        private String otcPendingOrderNo;
        private int userId;
        private String userAccount;
        private int orderType;
        private int currencyId;
        private String currencyName;
        private String pendingRatio;
        private String minNumber;
        private String maxNumber;
        private String pendingNumber;
        private String dealNumber;
        private String buyFee;
        private String restBalanceLock;
        private String area;
        private int pendingStatus;
        private String endTime;
        private String remark;
        private String updateTime;
        private long addTime;

        public String getOtcPendingOrderNo() {
            return otcPendingOrderNo;
        }

        public void setOtcPendingOrderNo(String otcPendingOrderNo) {
            this.otcPendingOrderNo = otcPendingOrderNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
        }

        public int getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(int currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getPendingRatio() {
            return pendingRatio;
        }

        public void setPendingRatio(String pendingRatio) {
            this.pendingRatio = pendingRatio;
        }

        public String getMinNumber() {
            return minNumber;
        }

        public void setMinNumber(String minNumber) {
            this.minNumber = minNumber;
        }

        public String getMaxNumber() {
            return maxNumber;
        }

        public void setMaxNumber(String maxNumber) {
            this.maxNumber = maxNumber;
        }

        public String getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(String pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public String getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(String dealNumber) {
            this.dealNumber = dealNumber;
        }

        public String getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(String buyFee) {
            this.buyFee = buyFee;
        }

        public String getRestBalanceLock() {
            return restBalanceLock;
        }

        public void setRestBalanceLock(String restBalanceLock) {
            this.restBalanceLock = restBalanceLock;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getPendingStatus() {
            return pendingStatus;
        }

        public void setPendingStatus(int pendingStatus) {
            this.pendingStatus = pendingStatus;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }
}
