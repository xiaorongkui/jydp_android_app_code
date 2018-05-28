package com.qmkj.jydp.util;

/**
 * 字符串操作
 *
 * @author gql
 */
public class StringUtil {

    /**
     * 字符串空判断
     * 属于空的：（NULL，‘’， ‘null’,'NULL'）
     *
     * @param paramStr 待判断的字符串
     * @return true：空，false：非空
     */
    public static boolean isNull(String paramStr) {
        return !isNotNull(paramStr);
    }

    /**
     * 字符串非空判断
     * 属于空的：（NULL，‘’， ‘null’,'NULL'）
     *
     * @param paramStr 待判断的字符串
     * @return true：非空，false：空
     */
    public static boolean isNotNull(String paramStr) {
        if (paramStr == null) {
            return false;
        }
        if (paramStr.isEmpty()) {
            return false;
        }
        paramStr = paramStr.trim();
        if (paramStr.equals("")) {
            return false;
        }
        if (paramStr.equals("null")) {
            return false;
        }
        if (paramStr.equals("NULL")) {
            return false;
        }

        return true;
    }

    /**
     * 对字符串进行处理，把'null'、'NULL'处理成''空字符串，非空的字符串会执行trim
     *
     * @param paramStr 待处理的字符串
     * @return 处理后的字符串
     */
    public static String stringNullHandle(String paramStr) {
        if (isNotNull(paramStr)) {
            return paramStr.trim();
        } else {
            return "";
        }
    }

    /**
     * 链地址，链地址中间4位星号替换
     */
    public static String formatChainAddress(String chainAddress) {
        return chainAddress.substring(0, 6) + "****" + chainAddress.substring(chainAddress.length() - 4, chainAddress.length());
//        return chainAddress.replaceAll("(\\d{6})(\\d{4})", "$1****$2");
    }

    /**
     * 链地址，链地址中间4位星号替换
     */
    public static String formatPhoneNum(String phoneNum) {
        return phoneNum.substring(0, 3) + "***" + phoneNum.substring(phoneNum.length() - 3, phoneNum.length());
//        return chainAddress.replaceAll("(\\d{6})(\\d{4})", "$1****$2");
    }

}
