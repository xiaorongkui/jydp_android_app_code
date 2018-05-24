package com.qmkj.jydp.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/24
 * email：dovexiaoen@163.com
 * description:
 */

public class OutSideSellDetailRes extends BaseRes implements Parcelable {


    private String paymentPhone;
    private String phoneNumber;
    private String bankBranch;
    private OtcTransactionPendOrderBean otcTransactionPendOrder;
    private String bankName;
    private String userName;
    private String paymentAccount;
    private String paymentName;
    private double sellNum;
    private int paymentType;
    private String imageUrl;
    private String imageUrlFormat;
    private String sellMoney;

    public OutSideSellDetailRes() {
    }

    protected OutSideSellDetailRes(Parcel in) {
        paymentPhone = in.readString();
        phoneNumber = in.readString();
        bankBranch = in.readString();
        otcTransactionPendOrder = in.readParcelable(OtcTransactionPendOrderBean.class.getClassLoader());
        bankName = in.readString();
        userName = in.readString();
        paymentAccount = in.readString();
        paymentName = in.readString();
        sellNum = in.readDouble();
        paymentType = in.readInt();
        imageUrl = in.readString();
        imageUrlFormat = in.readString();
        sellMoney = in.readString();
    }

    public static final Creator<OutSideSellDetailRes> CREATOR = new Creator<OutSideSellDetailRes>() {
        @Override
        public OutSideSellDetailRes createFromParcel(Parcel in) {
            return new OutSideSellDetailRes(in);
        }

        @Override
        public OutSideSellDetailRes[] newArray(int size) {
            return new OutSideSellDetailRes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(paymentPhone);
        dest.writeString(phoneNumber);
        dest.writeString(bankBranch);
        dest.writeParcelable(otcTransactionPendOrder, flags);
        dest.writeString(bankName);
        dest.writeString(userName);
        dest.writeString(paymentAccount);
        dest.writeString(paymentName);
        dest.writeDouble(sellNum);
        dest.writeInt(paymentType);
        dest.writeString(imageUrl);
        dest.writeString(imageUrlFormat);
        dest.writeString(sellMoney);
    }

    public String getPaymentPhone() {
        return paymentPhone;
    }

    public void setPaymentPhone(String paymentPhone) {
        this.paymentPhone = paymentPhone;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public OtcTransactionPendOrderBean getOtcTransactionPendOrder() {
        return otcTransactionPendOrder;
    }

    public void setOtcTransactionPendOrder(OtcTransactionPendOrderBean otcTransactionPendOrder) {
        this.otcTransactionPendOrder = otcTransactionPendOrder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public double getSellNum() {
        return sellNum;
    }

    public void setSellNum(double sellNum) {
        this.sellNum = sellNum;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlFormat() {
        return imageUrlFormat;
    }

    public void setImageUrlFormat(String imageUrlFormat) {
        this.imageUrlFormat = imageUrlFormat;
    }

    public String getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(String sellMoney) {
        this.sellMoney = sellMoney;
    }

    public static class OtcTransactionPendOrderBean implements Parcelable {
        /**
         * otcPendingOrderNo : 601805175750302947
         * userId : 313
         * userAccount : sy001
         * orderType : 2
         * currencyId : 999
         * currencyName : XT
         * pendingRatio : 30.0
         * minNumber : 3.0
         * maxNumber : 30000.0
         * pendingNumber : 0.0
         * dealNumber : 0.0
         * buyFee : 0.0
         * restBalanceLock : 0.0
         * area : 中国(CN)
         * pendingStatus : 1
         * endTime : null
         * remark : null
         * updateTime : null
         * addTime : 1526528769000
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
        private long endTime;
        private String remark;
        private long updateTime;
        private long addTime;
        private String dealerName;

        protected OtcTransactionPendOrderBean(Parcel in) {
            otcPendingOrderNo = in.readString();
            userId = in.readInt();
            userAccount = in.readString();
            orderType = in.readInt();
            currencyId = in.readInt();
            currencyName = in.readString();
            pendingRatio = in.readDouble();
            minNumber = in.readDouble();
            maxNumber = in.readDouble();
            pendingNumber = in.readDouble();
            dealNumber = in.readDouble();
            buyFee = in.readDouble();
            restBalanceLock = in.readDouble();
            area = in.readString();
            pendingStatus = in.readInt();
            endTime = in.readLong();
            remark = in.readString();
            updateTime = in.readLong();
            addTime = in.readLong();
            dealerName = in.readString();
        }

        public static final Creator<OtcTransactionPendOrderBean> CREATOR = new Creator<OtcTransactionPendOrderBean>() {
            @Override
            public OtcTransactionPendOrderBean createFromParcel(Parcel in) {
                return new OtcTransactionPendOrderBean(in);
            }

            @Override
            public OtcTransactionPendOrderBean[] newArray(int size) {
                return new OtcTransactionPendOrderBean[size];
            }
        };

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

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(otcPendingOrderNo);
            dest.writeInt(userId);
            dest.writeString(userAccount);
            dest.writeInt(orderType);
            dest.writeInt(currencyId);
            dest.writeString(currencyName);
            dest.writeDouble(pendingRatio);
            dest.writeDouble(minNumber);
            dest.writeDouble(maxNumber);
            dest.writeDouble(pendingNumber);
            dest.writeDouble(dealNumber);
            dest.writeDouble(buyFee);
            dest.writeDouble(restBalanceLock);
            dest.writeString(area);
            dest.writeInt(pendingStatus);
            dest.writeLong(endTime);
            dest.writeString(remark);
            dest.writeLong(updateTime);
            dest.writeLong(addTime);
            dest.writeString(dealerName);
        }
    }

    @Override
    public String toString() {
        return "OutSideSellDetailRes{" +
                "paymentPhone='" + paymentPhone + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bankBranch='" + bankBranch + '\'' +
                ", otcTransactionPendOrder=" + otcTransactionPendOrder +
                ", bankName='" + bankName + '\'' +
                ", userName='" + userName + '\'' +
                ", paymentAccount='" + paymentAccount + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", sellNum=" + sellNum +
                ", paymentType=" + paymentType +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageUrlFormat='" + imageUrlFormat + '\'' +
                ", sellMoney='" + sellMoney + '\'' +
                '}';
    }
}
