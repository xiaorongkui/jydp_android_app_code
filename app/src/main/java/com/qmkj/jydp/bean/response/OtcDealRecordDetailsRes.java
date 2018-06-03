package com.qmkj.jydp.bean.response;

import java.io.Serializable;

/**
 * 创建日期：2018/5/21
 * @author Yi Shan Xiang
 * 文件名称： OtcDealRecordDetailsRes
 * email: 380948730@qq.com
 */
public class OtcDealRecordDetailsRes extends BaseRes implements Serializable{


    /**
     * otcTransactionUserDeal : {"addTime":1525250731000,"area":"中国(CN)","bankBranch":1,"bankCode":1,"bankName":1,"currencyName":"XT","currencyNumber":1,"currencyTotalPrice":1,"dealStatus":2,"dealType":2,"dealerName":"IIIUYTWQ","imageUrl":"1231","otcOrderNo":"611805024496671550","paymentAccount":"123","paymentImage":"http://test.oksheng.com.cn/fileservice1231","paymentName":"1","paymentPhone":1,"paymentType":2,"phoneNumber":"15375455110","remark":1,"typeId":"726","updateTime":1525405710000,"userAccount":1,"userPhone":1}
     */

    private OtcTransactionUserDealBean otcTransactionUserDeal;

    public OtcTransactionUserDealBean getOtcTransactionUserDeal() {
        return otcTransactionUserDeal;
    }

    public void setOtcTransactionUserDeal(OtcTransactionUserDealBean otcTransactionUserDeal) {
        this.otcTransactionUserDeal = otcTransactionUserDeal;
    }

    public static class OtcTransactionUserDealBean {
        /**
         * addTime : 1525250731000
         * area : 中国(CN)
         * bankBranch : 1
         * bankCode : 1
         * bankName : 1
         * currencyName : XT
         * currencyNumber : 1
         * currencyTotalPrice : 1
         * dealStatus : 2
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
         * updateTime : 1525405710000
         * userAccount : 1
         * userPhone : 1
         */
        private String otcOrderNo;  //记录号
        private int dealStatus;  //状态：1：待付款，2：已付款（待确认），3：已完成，4：用户取消，5：商家取消

        private String currencyName;  //货币名称
        private String currencyNumber;  //成交数量
        private String currencyTotalPrice;  //成交总价
        private String area;  //地区
        private int dealType;  //（普通用户）收支类型：1：买入，2：卖出，3：撤销（经销商）收支类型：1：卖出，2：买入，3：撤销

        private String dealerName;  //经销商名称
        private String phoneNumber;  //经销商电话

        private String typeId;  //收款方式Id
        private int paymentType;  //收款方式标识：1：银行卡，2：支付宝，3：微信

        private String bankBranch;  //收款支行
        private String bankName;  //收款银行
        private String bankCode;  //收款银行标识

        private String imageUrl;  //二维码地址
        private String paymentImage;  //二维码地址（绝对路径

        private String paymentAccount;  //收款账号
        private String paymentName;  //收款人姓名
        private String paymentPhone;  //收款人手机号

        private String userAccount;  //用户账号
        private String userPhone;  //用户手机号
        private String remark;  //备注

        private long addTime;  //添加时间
        private long updateTime;  //修改时间

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

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
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

        public String getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(String paymentName) {
            this.paymentName = paymentName;
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

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(String currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public String getCurrencyTotalPrice() {
            return currencyTotalPrice;
        }

        public void setCurrencyTotalPrice(String currencyTotalPrice) {
            this.currencyTotalPrice = currencyTotalPrice;
        }

        public String getPaymentPhone() {
            return paymentPhone;
        }

        public void setPaymentPhone(String paymentPhone) {
            this.paymentPhone = paymentPhone;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }
    }
}
