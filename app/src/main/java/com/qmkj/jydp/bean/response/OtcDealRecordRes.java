package com.qmkj.jydp.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * 创建日期：2018/5/21
 * @author Yi Shan Xiang
 * 文件名称： OtcDealRecordRes
 * email: 380948730@qq.com
 */
public class OtcDealRecordRes extends BaseRes implements Serializable{

    /**
     * otcTransactionUserDealList : [{"addTime":1525250731000,"area":"中国(CN)","bankBranch":1,"bankCode":1,"bankName":1,"currencyName":"XT","currencyNumber":1,"currencyTotalPrice":1,"dealStatus":1,"dealType":2,"dealerName":"IIIUYTWQ","imageUrl":"1231","otcOrderNo":"611805024496671550","paymentAccount":"123","paymentImage":"http://test.oksheng.com.cn/fileservice1231","paymentName":1,"paymentPhone":"1","paymentType":2,"phoneNumber":"15375455110","remark":1,"typeId":726,"updateTime":1,"userAccount":1,"userPhone":1}]
     * pageNumber : 0
     * totalNumber : 1
     * totalPageNumber : 1
     */

    private int pageNumber;
    private int totalNumber;
    private int totalPageNumber;
    private List<OtcTransactionUserDealListBean> otcTransactionUserDealList;

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

    public List<OtcTransactionUserDealListBean> getOtcTransactionUserDealList() {
        return otcTransactionUserDealList;
    }

    public void setOtcTransactionUserDealList(List<OtcTransactionUserDealListBean> otcTransactionUserDealList) {
        this.otcTransactionUserDealList = otcTransactionUserDealList;
    }

    public static class OtcTransactionUserDealListBean {
        /**
         * addTime : 1525250731000
         * area : 中国(CN)
         * bankBranch : 1
         * bankCode : 1
         * bankName : 1
         * currencyName : XT
         * currencyNumber : 1
         * currencyTotalPrice : 1
         * dealStatus : 1
         * dealType : 2
         * dealerName : IIIUYTWQ
         * imageUrl : 1231
         * otcOrderNo : 611805024496671550
         * paymentAccount : 123
         * paymentImage : http://test.oksheng.com.cn/fileservice1231
         * paymentName : 1
         * paymentPhone : 1
         * paymentType : 2
         * phoneNumber : 15375455110
         * remark : 1
         * typeId : 726
         * updateTime : 1
         * userAccount : 1
         * userPhone : 1
         */

        private long addTime;
        private String area;
        private int bankBranch;
        private int bankCode;
        private int bankName;
        private String currencyName;
        private int currencyNumber;
        private int currencyTotalPrice;
        private int dealStatus;
        private int dealType;
        private String dealerName;
        private String imageUrl;
        private String otcOrderNo;
        private String paymentAccount;
        private String paymentImage;
        private int paymentName;
        private String paymentPhone;
        private int paymentType;
        private String phoneNumber;
        private int remark;
        private int typeId;
        private int updateTime;
        private int userAccount;
        private int userPhone;

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public int getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(int bankBranch) {
            this.bankBranch = bankBranch;
        }

        public int getBankCode() {
            return bankCode;
        }

        public void setBankCode(int bankCode) {
            this.bankCode = bankCode;
        }

        public int getBankName() {
            return bankName;
        }

        public void setBankName(int bankName) {
            this.bankName = bankName;
        }

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public int getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(int currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public int getCurrencyTotalPrice() {
            return currencyTotalPrice;
        }

        public void setCurrencyTotalPrice(int currencyTotalPrice) {
            this.currencyTotalPrice = currencyTotalPrice;
        }

        public int getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(int dealStatus) {
            this.dealStatus = dealStatus;
        }

        public int getDealType() {
            return dealType;
        }

        public void setDealType(int dealType) {
            this.dealType = dealType;
        }

        public String getDealerName() {
            return dealerName;
        }

        public void setDealerName(String dealerName) {
            this.dealerName = dealerName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getOtcOrderNo() {
            return otcOrderNo;
        }

        public void setOtcOrderNo(String otcOrderNo) {
            this.otcOrderNo = otcOrderNo;
        }

        public String getPaymentAccount() {
            return paymentAccount;
        }

        public void setPaymentAccount(String paymentAccount) {
            this.paymentAccount = paymentAccount;
        }

        public String getPaymentImage() {
            return paymentImage;
        }

        public void setPaymentImage(String paymentImage) {
            this.paymentImage = paymentImage;
        }

        public int getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(int paymentName) {
            this.paymentName = paymentName;
        }

        public String getPaymentPhone() {
            return paymentPhone;
        }

        public void setPaymentPhone(String paymentPhone) {
            this.paymentPhone = paymentPhone;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public int getRemark() {
            return remark;
        }

        public void setRemark(int remark) {
            this.remark = remark;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(int userAccount) {
            this.userAccount = userAccount;
        }

        public int getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(int userPhone) {
            this.userPhone = userPhone;
        }
    }
}
