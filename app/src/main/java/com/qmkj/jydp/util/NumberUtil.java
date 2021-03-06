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
        numberFormat.setGroupingUsed(false); //false则不使用分组方式显示数据。
        numberFormat.setMinimumFractionDigits(1);//设置数值的小数部分允许的最小位数。
        numberFormat.setMaximumFractionDigits(accuracy); // 设置数值的小数部分允许的最大位数。
        numberFormat.setMaximumIntegerDigits(10); //设置数值的整数部分允许的最大位数。
        numberFormat.setMinimumIntegerDigits(1); //设置数值的整数部分允许的最小位数.
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

    /**
     * Format 2 point 格式化4位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format5Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#0.00000");
        return myformat.format(str);
    }

    /**
     * Format 6 point 格式化6位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format6Point(double str) {
        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("#0.000000");
        return myformat.format(str);
    }

    /**
     * 加法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double add(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.add(b2).doubleValue();

    }

    /**
     * 减法
     *
     * @param var1
     * @param var2
     * @return
     */

    public static double sub(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double mul(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale 精度，到小数点后几位
     * @return
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

}
