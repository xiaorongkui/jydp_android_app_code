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
        return doubleFormat(Double.parseDouble(doubleValue), accuracy);
    }

}
