package com.qmkj.jydp.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Yun on 2018/1/15.
 * 数字工具类
 */
public class NumberUtil {

    /**
     * 格式化double四舍五入
     *
     * @param number 被格式化的数字
     * @param digits 保留的小数位数
     */
    public static String format(double number, int digits) {
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i < digits; i++) {
            pattern.append("0");
        }
        String patternStr = pattern.toString();
        if (patternStr.endsWith(".")) {
            patternStr = patternStr.replace(".", "");
        }
        DecimalFormat decimalFormat = new DecimalFormat(patternStr);
        String formatNumber = decimalFormat.format(number);
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        return numberFormat.format(Double.valueOf(formatNumber));
    }

    /**
     * 格式化double四舍五入
     *
     * @param number 被格式化的String
     * @param digits 保留的小数位数
     */
    public static String format(String number, int digits) {
        if (!StringUtil.isNotNull(number)) {
            number = "0";
        }
        return format(Double.parseDouble(number), digits);
    }

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
     * double精度（直接截取，不舍入）
     *
     * @param doubleValue 要处理的值
     * @param accuracy    保留的小数点位数
     * @return 返回精度的double
     */
    public static String doubleFormat(String doubleValue, int accuracy) {
        if (!StringUtil.isNotNull(doubleValue)) {
            doubleValue = "0";
        }
        try {
            return doubleFormat(Double.parseDouble(doubleValue), accuracy);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return doubleValue;
    }

    /**
     * 提供精确乘法运算的mul方法
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static double mul(double value1, double value2) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.multiply(b2).doubleValue();
    }

    //四舍五入，保留2位小数
    public static double div(double value1, double value2) {
        return div(value1, value2, RoundingMode.HALF_EVEN, 2);
    }

    //四舍五入，保留2位小数
    public static double div(String value1, String value2) {

        double m = 0;
        double n = 0;
        try {
            m = Double.parseDouble(value1);
            n = Double.parseDouble(value2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (n == 0) {
            return 0;
        }
        return div(m, n, RoundingMode.HALF_EVEN, 2);
    }

    /**
     * 提供精确的除法运算方法div
     *
     * @param value1       被除数
     * @param value2       除数
     * @param roundingMode 精确方式RoundingMode.HALF_EVEN
     * @param pointCount   保留到小数位数
     * @return 两个参数的商
     */
    public static double div(double value1, double value2, RoundingMode roundingMode, int
            pointCount) {
        BigDecimal b1 = new BigDecimal(Double.valueOf(value1));
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.divide(b2, pointCount, roundingMode).doubleValue();
    }

    /**
     * 提供精确的除法运算方法div,保留两位小数
     *
     * @param value1 被除数
     * @param value2 除数
     * @return 两个参数的商
     */
    public static double sum(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(Double.valueOf(value2));
        return b1.add(b2).doubleValue();
    }

    /**
     * Format 2 point 格式化两位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format2Point(double str) {
        return format2Point(str + "");
    }

    /**
     * Format 2 point string.
     *
     * @param str the str
     * @return the string
     */
    public static String format2Point(String str) {
        LogUtil.i("format2Point=" + str);
        try {
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##0.00");
            return myformat.format(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * Format 2 point string.
     *
     * @param str the str
     * @return the string
     */
    public static String format4Point(String str) {
        try {
            DecimalFormat myformat = new DecimalFormat();
            myformat.applyPattern("##0.0000");
            return myformat.format(str);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("format4Point exception=" + str + ";e=" + e.getMessage());
        }
        return str;
    }

    /**
     * Format 2 point 格式化4位有效数字.
     *
     * @param str the str
     * @return the string
     */
    public static String format4Point(double str) {
        return format4Point(str + "");
    }
}
