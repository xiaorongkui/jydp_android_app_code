package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;


/**
 * 创建日期：2018/5/18
 *
 * @author Yi Shan Xiang
 *         文件名称： 挂单委托记录
 *         email: 380948730@qq.com
 */

public class OrderRecodeRes extends BaseRes implements Serializable {


    /**
     * totalPageNumber : 2
     * transactionPendOrderRecordList : [{"addTime":1525336486000,"buyFee":0,"currencyId":202,"currencyName":"量子链",
     * "dealNumber":2038,"endTime":1525396002000,"feeRemark":1,"paymentType":1,"pendingNumber":2038,
     * "pendingOrderNo":"301805039939696925","pendingPrice":5,"pendingStatus":3,"remainNum":0,"remark":"",
     * "restBalanceLock":"0","totalPrice":"10190","userAccount":"syl002","userId":314}]
     */

    private int totalPageNumber; //总页数
    private List<TransactionPendOrderRecordListBean> transactionPendOrderRecordList; //挂单记录

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<TransactionPendOrderRecordListBean> getTransactionPendOrderRecordList() {
        return transactionPendOrderRecordList;
    }

    public void setTransactionPendOrderRecordList(List<TransactionPendOrderRecordListBean>
                                                          transactionPendOrderRecordList) {
        this.transactionPendOrderRecordList = transactionPendOrderRecordList;
    }

    public static class TransactionPendOrderRecordListBean {
        /**
         * addTime : 1525336486000
         * buyFee : 0
         * currencyId : 202
         * currencyName : 量子链
         * dealNumber : 2038
         * endTime : 1525396002000
         * feeRemark : 1
         * paymentType : 1
         * pendingNumber : 2038
         * pendingOrderNo : 301805039939696925
         * pendingPrice : 5
         * pendingStatus : 3
         * remainNum : 0
         * remark :
         * restBalanceLock : 0
         * totalPrice : 10190
         * userAccount : syl002
         * userId : 314
         */

        private long addTime; //添加时间
        private String buyFee; //买入手续费
        private int currencyId; //币种Id
        private String currencyName; //货币名称
        private String dealNumber; //成交数量
        private long endTime;  //完成时间
        private String feeRemark; //费率备注，手续费
        private int paymentType; //收支类型,1：买入，2：卖出
        private String pendingNumber; //挂单数量
        private String pendingOrderNo; //记录号
        private String pendingPrice; //挂单单价
        private int pendingStatus; //挂单状态，1：未成交，2：部分成交，3：全部成交，4：部分撤销，5：全部撤销
        private String remainNum; //剩余数量
        private String remark; //备注
        private String restBalanceLock; //剩余冻结XT
        private String totalPrice; //总价
        private String userAccount; //用户账号
        private int userId; //用户Id

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
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


        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }


        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }


        public String getPendingOrderNo() {
            return pendingOrderNo;
        }

        public void setPendingOrderNo(String pendingOrderNo) {
            this.pendingOrderNo = pendingOrderNo;
        }

        public int getPendingStatus() {
            return pendingStatus;
        }

        public void setPendingStatus(int pendingStatus) {
            this.pendingStatus = pendingStatus;
        }


        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRestBalanceLock() {
            return restBalanceLock;
        }

        public void setRestBalanceLock(String restBalanceLock) {
            this.restBalanceLock = restBalanceLock;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
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

        public String getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(String buyFee) {
            this.buyFee = buyFee;
        }

        public String getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(String dealNumber) {
            this.dealNumber = dealNumber;
        }

        public String getFeeRemark() {
            return feeRemark;
        }

        public void setFeeRemark(String feeRemark) {
            this.feeRemark = feeRemark;
        }

        public String getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(String pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public String getPendingPrice() {
            return pendingPrice;
        }

        public void setPendingPrice(String pendingPrice) {
            this.pendingPrice = pendingPrice;
        }

        public String getRemainNum() {
            return remainNum;
        }

        public void setRemainNum(String remainNum) {
            this.remainNum = remainNum;
        }
    }
}
