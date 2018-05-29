package com.qmkj.jydp.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author：rongkui.xiao --2018/5/14
 * email：dovexiaoen@163.com
 * description:检查文字正确性
 */

public class CheckTextUtil {
    /**
     * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        String regex = "(\\+\\d+)?1[34578]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }

    /**
     * 验证固定电话号码
     *
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkLandlinePhone(String phone) {
        String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
        return Pattern.matches(regex, phone);
    }


    /**
     * 检查密码。必须包含数字和字母
     */
    public static boolean checkPassword(CharSequence str) {
        return checkPassword(str.toString());
    }

    /**
     * 检查密码。必须包含数字或者字母(6-16数字字母)，其他不容许输入
     */
    public static boolean checkPassword(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        boolean isLength = str.length() >= 6 && str.length() <= 16;//定义一个boolean值，用来表示是否8-16位长度

        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        return (isDigit || isLetter) && str.matches(regex) && isLength;
    }


    /**
     * 检查姓名
     */
    public static boolean checkName(String str) {
        if (str.length() == 0) {
            return false;
        }
        try {
            Pattern p = Pattern.compile("^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$");
            return p.matcher(str).matches();
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * 检查是否是整数
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 检查是小数或者整数
     */
    public static boolean isNumericorFloat(String str) {
        Pattern pattern = Pattern.compile("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {

            return false;
        }
        return true;
    }

    //处理电话号码,返回加密的号
    public static String dealPhoneNum(String userName, int startLength, int endlength) {
        if (TextUtils.isEmpty(userName) || userName.length() < (startLength + endlength)) return "";
        String startStr = userName.substring(0, startLength);
        String endStr = userName.substring(userName.length() - endlength, userName.length());
        StringBuilder sb = new StringBuilder(startStr);
        for (int i = 0; i < (userName.length() - startLength - endlength); i++) {
            sb.append("*");
        }
        return sb.append(endStr).toString();
    }

    /**
     * 验证输入的名字是否为“中文”或者是否包含“·”
     *
     * @param str w为用户输入的姓名
     * @return
     */
    public static boolean verifyName(String str) {
        if (str.contains("·") || str.contains("•")) {
            if (str.matches("^[\\u4e00-\\u9fa5]+[·•][\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        } else {
            if (str.matches("^[\\u4e00-\\u9fa5]+$")) {
                return true;
            } else {
                return false;
            }
        }
    }

}
