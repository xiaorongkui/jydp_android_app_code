package com.qmkj.jydp.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideBuyPayDetailRes extends BaseRes implements Parcelable {

    /**
     * phoneNumber : 10000000001
     * dealerName : 测试经销商1
     * buyNum : 1
     * otcDealerUserDO : {"dealerId":45,"userId":313,"userAccount":"syl001","dealerName":"测试经销商1",
     * "ipAddress":"60.176.183.10","backerId":21,"dealStatus":1,"remark":null,"updateTime":null,
     * "addTime":1525744473000}
     * otcTransactionPendOrder : {"otcPendingOrderNo":"601805213003151508","userId":313,"userAccount":"syl001",
     * "orderType":1,"currencyId":999,"currencyName":"XT","pendingRatio":1,"minNumber":1,"maxNumber":2,
     * "pendingNumber":0,"dealNumber":0,"buyFee":0,"restBalanceLock":0,"area":"中国(CN)","pendingStatus":1,
     * "endTime":null,"remark":null,"updateTime":null,"addTime":1526868614000,"dealerName":"测试经销商1"}
     * userPaymentType : {"typeId":871,"userId":313,"otcPendingOrderNo":"601805213003151508","paymentType":2,
     * "paymentAccount":"412344","bankName":null,"bankCode":null,"bankBranch":null,"paymentName":null,
     * "paymentPhone":null,"paymentImage":"/upload/qeCodeImage/20180521/2018052110101209184645803.jpg",
     * "typeStatus":1,"remark":null,"updateTime":null,"addTime":1526868614000,"paymentImageFormat":"http://test
     * .oksheng.com.cn/fileservice/upload/qeCodeImage/20180521/2018052110101209184645803.jpg"}
     * userName : 五线谱
     * paymentType : 2
     */

    private String phoneNumber;
    private String dealerName;
    private String buyNum;
    private OtcDealerUserDOBean otcDealerUserDO;
    private OtcTransactionPendOrderBean otcTransactionPendOrder;
    private UserPaymentTypeBean userPaymentType;
    private String userName;
    private int paymentType;

    public OutSideBuyPayDetailRes() {
    }

    protected OutSideBuyPayDetailRes(Parcel in) {
        phoneNumber = in.readString();
        dealerName = in.readString();
        buyNum = in.readString();
        userName = in.readString();
        paymentType = in.readInt();
    }

    public static final Creator<OutSideBuyPayDetailRes> CREATOR = new Creator<OutSideBuyPayDetailRes>() {
        @Override
        public OutSideBuyPayDetailRes createFromParcel(Parcel in) {
            return new OutSideBuyPayDetailRes(in);
        }

        @Override
        public OutSideBuyPayDetailRes[] newArray(int size) {
            return new OutSideBuyPayDetailRes[size];
        }
    };

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public OtcDealerUserDOBean getOtcDealerUserDO() {
        return otcDealerUserDO;
    }

    public void setOtcDealerUserDO(OtcDealerUserDOBean otcDealerUserDO) {
        this.otcDealerUserDO = otcDealerUserDO;
    }

    public OtcTransactionPendOrderBean getOtcTransactionPendOrder() {
        return otcTransactionPendOrder;
    }

    public void setOtcTransactionPendOrder(OtcTransactionPendOrderBean otcTransactionPendOrder) {
        this.otcTransactionPendOrder = otcTransactionPendOrder;
    }

    public UserPaymentTypeBean getUserPaymentType() {
        return userPaymentType;
    }

    public void setUserPaymentType(UserPaymentTypeBean userPaymentType) {
        this.userPaymentType = userPaymentType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(dealerName);
        dest.writeString(buyNum);
        dest.writeString(userName);
        dest.writeInt(paymentType);
    }

    public static class OtcDealerUserDOBean {
        /**
         * dealerId : 45
         * userId : 313
         * userAccount : syl001
         * dealerName : 测试经销商1
         * ipAddress : 60.176.183.10
         * backerId : 21
         * dealStatus : 1
         * remark : null
         * updateTime : null
         * addTime : 1525744473000
         */

        private int dealerId;
        private int userId;
        private String userAccount;
        private String dealerName;
        private String ipAddress;
        private int backerId;
        private int dealStatus;
        private Object remark;
        private Object updateTime;
        private long addTime;

        public int getDealerId() {
            return dealerId;
        }

        public void setDealerId(int dealerId) {
            this.dealerId = dealerId;
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

        public String getDealerName() {
            return dealerName;
        }

        public void setDealerName(String dealerName) {
            this.dealerName = dealerName;
        }

        public String getIpAddress() {
            return ipAddress;
        }

        public void setIpAddress(String ipAddress) {
            this.ipAddress = ipAddress;
        }

        public int getBackerId() {
            return backerId;
        }

        public void setBackerId(int backerId) {
            this.backerId = backerId;
        }

        public int getDealStatus() {
            return dealStatus;
        }

        public void setDealStatus(int dealStatus) {
            this.dealStatus = dealStatus;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

    }

    public static class OtcTransactionPendOrderBean {
        /**
         * otcPendingOrderNo : 601805213003151508
         * userId : 313
         * userAccount : syl001
         * orderType : 1
         * currencyId : 999
         * currencyName : XT
         * pendingRatio : 1.0
         * minNumber : 1.0
         * maxNumber : 2.0
         * pendingNumber : 0.0
         * dealNumber : 0.0
         * buyFee : 0.0
         * restBalanceLock : 0.0
         * area : 中国(CN)
         * pendingStatus : 1
         * endTime : null
         * remark : null
         * updateTime : null
         * addTime : 1526868614000
         * dealerName : 测试经销商1
         */

        private String otcPendingOrderNo;
        private int userId;
        private String userAccount;
        private int orderType;
        private int currencyId;
        private String currencyName;
        private double pendingRatio;
        private double minNumber;
        private double maxNumber;
        private double pendingNumber;
        private double dealNumber;
        private double buyFee;
        private double restBalanceLock;
        private String area;
        private int pendingStatus;
        private Object endTime;
        private Object remark;
        private Object updateTime;
        private long addTime;
        private String dealerName;

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

        public double getPendingRatio() {
            return pendingRatio;
        }

        public void setPendingRatio(double pendingRatio) {
            this.pendingRatio = pendingRatio;
        }

        public double getMinNumber() {
            return minNumber;
        }

        public void setMinNumber(double minNumber) {
            this.minNumber = minNumber;
        }

        public double getMaxNumber() {
            return maxNumber;
        }

        public void setMaxNumber(double maxNumber) {
            this.maxNumber = maxNumber;
        }

        public double getPendingNumber() {
            return pendingNumber;
        }

        public void setPendingNumber(double pendingNumber) {
            this.pendingNumber = pendingNumber;
        }

        public double getDealNumber() {
            return dealNumber;
        }

        public void setDealNumber(double dealNumber) {
            this.dealNumber = dealNumber;
        }

        public double getBuyFee() {
            return buyFee;
        }

        public void setBuyFee(double buyFee) {
            this.buyFee = buyFee;
        }

        public double getRestBalanceLock() {
            return restBalanceLock;
        }

        public void setRestBalanceLock(double restBalanceLock) {
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

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getDealerName() {
            return dealerName;
        }

        public void setDealerName(String dealerName) {
            this.dealerName = dealerName;
        }

    }

    public static class UserPaymentTypeBean implements Parcelable {
        public UserPaymentTypeBean() {
        }

        protected UserPaymentTypeBean(Parcel in) {
            typeId = in.readInt();
            userId = in.readInt();
            otcPendingOrderNo = in.readString();
            paymentType = in.readInt();
            paymentAccount = in.readString();
            bankName = in.readString();
            bankCode = in.readString();
            bankBranch = in.readString();
            paymentName = in.readString();
            paymentPhone = in.readString();
            paymentImage = in.readString();
            typeStatus = in.readInt();
            remark = in.readString();
            updateTime = in.readString();
            addTime = in.readLong();
            paymentImageFormat = in.readString();
        }

        public static final Creator<UserPaymentTypeBean> CREATOR = new Creator<UserPaymentTypeBean>() {
            @Override
            public UserPaymentTypeBean createFromParcel(Parcel in) {
                return new UserPaymentTypeBean(in);
            }

            @Override
            public UserPaymentTypeBean[] newArray(int size) {
                return new UserPaymentTypeBean[size];
            }
        };

        @Override
        public String toString() {
            return "UserPaymentTypeBean{" +
                    "typeId=" + typeId +
                    ", userId=" + userId +
                    ", otcPendingOrderNo='" + otcPendingOrderNo + '\'' +
                    ", paymentType=" + paymentType +
                    ", paymentAccount='" + paymentAccount + '\'' +
                    ", bankName='" + bankName + '\'' +
                    ", bankCode='" + bankCode + '\'' +
                    ", bankBranch='" + bankBranch + '\'' +
                    ", paymentName='" + paymentName + '\'' +
                    ", paymentPhone='" + paymentPhone + '\'' +
                    ", paymentImage='" + paymentImage + '\'' +
                    ", typeStatus=" + typeStatus +
                    ", remark='" + remark + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", addTime=" + addTime +
                    ", paymentImageFormat='" + paymentImageFormat + '\'' +
                    '}';
        }

        /**
         * typeId : 871
         * userId : 313
         * otcPendingOrderNo : 601805213003151508
         * paymentType : 2
         * paymentAccount : 412344
         * bankName : null
         * bankCode : null
         * bankBranch : null
         * paymentName : null
         * paymentPhone : null
         * paymentImage : /upload/qeCodeImage/20180521/2018052110101209184645803.jpg
         * typeStatus : 1
         * remark : null
         * updateTime : null
         * addTime : 1526868614000
         * paymentImageFormat : http://test.oksheng.com
         * .cn/fileservice/upload/qeCodeImage/20180521/2018052110101209184645803.jpg
         */

        private int typeId;
        private int userId;
        private String otcPendingOrderNo;
        private int paymentType;
        private String paymentAccount;
        private String bankName;
        private String bankCode;
        private String bankBranch;
        private String paymentName;
        private String paymentPhone;
        private String paymentImage;
        private int typeStatus;
        private String remark;
        private String updateTime;
        private long addTime;
        private String paymentImageFormat;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(typeId);
            dest.writeInt(userId);
            dest.writeString(otcPendingOrderNo);
            dest.writeInt(paymentType);
            dest.writeString(paymentAccount);
            dest.writeString(bankName);
            dest.writeString(bankCode);
            dest.writeString(bankBranch);
            dest.writeString(paymentName);
            dest.writeString(paymentPhone);
            dest.writeString(paymentImage);
            dest.writeInt(typeStatus);
            dest.writeString(remark);
            dest.writeString(updateTime);
            dest.writeLong(addTime);
            dest.writeString(paymentImageFormat);
        }

        public static Creator<UserPaymentTypeBean> getCREATOR() {
            return CREATOR;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getOtcPendingOrderNo() {
            return otcPendingOrderNo;
        }

        public void setOtcPendingOrderNo(String otcPendingOrderNo) {
            this.otcPendingOrderNo = otcPendingOrderNo;
        }

        public int getPaymentType() {
            return paymentType;
        }

        public void setPaymentType(int paymentType) {
            this.paymentType = paymentType;
        }

        public String getPaymentAccount() {
            return paymentAccount;
        }

        public void setPaymentAccount(String paymentAccount) {
            this.paymentAccount = paymentAccount;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankBranch() {
            return bankBranch;
        }

        public void setBankBranch(String bankBranch) {
            this.bankBranch = bankBranch;
        }

        public String getPaymentName() {
            return paymentName;
        }

        public void setPaymentName(String paymentName) {
            this.paymentName = paymentName;
        }

        public String getPaymentPhone() {
            return paymentPhone;
        }

        public void setPaymentPhone(String paymentPhone) {
            this.paymentPhone = paymentPhone;
        }

        public String getPaymentImage() {
            return paymentImage;
        }

        public void setPaymentImage(String paymentImage) {
            this.paymentImage = paymentImage;
        }

        public int getTypeStatus() {
            return typeStatus;
        }

        public void setTypeStatus(int typeStatus) {
            this.typeStatus = typeStatus;
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

        public String getPaymentImageFormat() {
            return paymentImageFormat;
        }

        public void setPaymentImageFormat(String paymentImageFormat) {
            this.paymentImageFormat = paymentImageFormat;
        }
    }
}
