package com.qmkj.jydp.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Yun on 2018/1/15.
 * 数字工具类
 */
public class NumberUtil {

    /**
     * double精度（直接截取，不舍入）
     *
     * @param doubleValue 要处理的值
     * @param accuracy    保留的小数点位数
     * @return 返回精度的double
     */
    public static String doubleFormat(double doubleValue, int accuracy) {
        BigDecimal bigDecimal = new BigDecimal(doubleValue + "");
        BigDecimal resultValue = bigDecimal.setScale(accuracy, BigDecimal.ROUND_DOWN);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(Double.valueOf(resultValue.toString()));
    }

    /**
     * Format 2 point 格式化两位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format2Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#0.00");
        return myformat.format(str);
    }

    /**
     * Format 2 point string.
     *
     * @param str the str
     * @return the string
     */
    public static String format2Point(String str) {
        LogUtil.i("format2Point=" + str);
        double doubleMoney = 0;
        try {
            doubleMoney = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("format2Point exception! e=" + e.getMessage());
            return str;
        }
        return format2Point(doubleMoney);
    }

    /**
     * Format 2 point string.
     *
     * @param str the str
     * @return the string
     */
    public static String format4Point(String str) {
        LogUtil.i("format4Point=" + str);
        double doubleMoney = 0;
        try {
            doubleMoney = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("format4Point exception! e=" + e.getMessage());
            return str;
        }
        return format4Point(doubleMoney);
    }

    /**
     * Format 2 point 格式化4位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format4Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#0.0000");
        return myformat.format(str);
    }
}
