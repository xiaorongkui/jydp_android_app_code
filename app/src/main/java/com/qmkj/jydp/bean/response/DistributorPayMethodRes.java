package com.qmkj.jydp.bean.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/5/23
 * email：dovexiaoen@163.com
 * description:
 */

public class DistributorPayMethodRes extends BaseRes implements Parcelable {


    /**
     * hasBank : 0
     * hasWeiXin : 0
     * hasAliPay : 1
     */

    private int hasBank;
    private int hasWeiXin;
    private int hasAliPay;

    protected DistributorPayMethodRes(Parcel in) {
        hasBank = in.readInt();
        hasWeiXin = in.readInt();
        hasAliPay = in.readInt();
    }

    public static final Creator<DistributorPayMethodRes> CREATOR = new Creator<DistributorPayMethodRes>() {
        @Override
        public DistributorPayMethodRes createFromParcel(Parcel in) {
            return new DistributorPayMethodRes(in);
        }

        @Override
        public DistributorPayMethodRes[] newArray(int size) {
            return new DistributorPayMethodRes[size];
        }
    };

    public int getHasBank() {
        return hasBank;
    }

    public void setHasBank(int hasBank) {
        this.hasBank = hasBank;
    }

    public int getHasWeiXin() {
        return hasWeiXin;
    }

    public void setHasWeiXin(int hasWeiXin) {
        this.hasWeiXin = hasWeiXin;
    }

    public int getHasAliPay() {
        return hasAliPay;
    }

    public void setHasAliPay(int hasAliPay) {
        this.hasAliPay = hasAliPay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hasBank);
        dest.writeInt(hasWeiXin);
        dest.writeInt(hasAliPay);
    }
}
