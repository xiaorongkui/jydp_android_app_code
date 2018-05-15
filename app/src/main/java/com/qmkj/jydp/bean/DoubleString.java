package com.qmkj.jydp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author：rongkui.xiao --2018/3/26
 * email：dovexiaoen@163.com
 * description:
 */

public class DoubleString implements Parcelable {
    public String str1;
    public String str2;

    public DoubleString(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.str1);
        dest.writeString(this.str2);
    }

    protected DoubleString(Parcel in) {
        this.str1 = in.readString();
        this.str2 = in.readString();
    }

    public static final Parcelable.Creator<DoubleString> CREATOR = new Parcelable.Creator<DoubleString>() {
        @Override
        public DoubleString createFromParcel(Parcel source) {
            return new DoubleString(source);
        }

        @Override
        public DoubleString[] newArray(int size) {
            return new DoubleString[size];
        }
    };
}
