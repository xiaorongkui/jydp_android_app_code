package com.qmkj.jydp.manager;

import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.common.AppNetConfig;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.SPHelper;

/**
 * author：rongkui.xiao --2018/6/8
 * email：dovexiaoen@163.com
 * description:数据管理工具类
 */
public class DataManager {
    /**
     * 保存注册时个人账户
     *
     * @param account the account
     */
    public static void setUserAccount(String account) {
        LogUtil.i("setUserAccount=" + account);
        SPHelper.getInstance().set(Constants.SP_SAVE_ACCOUNT, account);
    }

    /**
     * Gets user account.
     *
     * @return the user account
     */
    public static String getUserAccount() {
        LogUtil.i("getUserAccount=" + SPHelper.getInstance().getString(Constants.SP_SAVE_ACCOUNT));
        return SPHelper.getInstance().getString(Constants.SP_SAVE_ACCOUNT);
    }

    /**
     * Sets login info.
     *
     * @param userInfo the user info
     */
/*保存登录时个人信息返回*/
    public static void setLoginInfo(LoginRes userInfo) {
        SPHelper.getInstance().saveObject(Constants.SP_SAVE_LOGIN_USERINFO, userInfo);
        if (userInfo != null) setToken(userInfo.getToken());
    }

    /**
     * Gets login info.
     *
     * @return the login info
     */
/*保存登录时个人信息返回*/
    public static LoginRes getLoginInfo() {
        return (LoginRes) SPHelper.getInstance().getObject(Constants.SP_SAVE_LOGIN_USERINFO, null);
    }

    /**
     * Clear login info.
     */
/*清除个人信息*/
    public static void clearLoginInfo() {
        SPHelper.getInstance().clearSharedPreferencesByKey(Constants.SP_SAVE_LOGIN_USERINFO);
    }

    /**
     * Gets token.
     *
     * @return the token
     */
//获取tokenId
    public static String getToken() {
        return SPHelper.getInstance().getString(Constants.SP_SAVE_TOKEN);
    }

    /**
     * Sets token.
     *
     * @param token the token
     */
//获取tokenId
    public static void setToken(String token) {
        SPHelper.getInstance().set(Constants.SP_SAVE_TOKEN, token);
    }

    /**
     * Save exchange pwd.
     *
     * @param trim the trim
     */
    public static void saveExchangePwd(String trim) {
        SPHelper.getInstance().set(Constants.SP_SAVE_EXCHANGE_PWD, trim);
    }

    /**
     * Gets exchange pwd.
     *
     * @return the exchange pwd
     */
    public static String getExchangePwd() {
        return SPHelper.getInstance().getString(Constants.SP_SAVE_EXCHANGE_PWD);
    }

    /**
     * Clear login data.
     */
    public static void clearLoginData() {
        setToken("");
        clearLoginInfo();
        saveExchangePwd("");
    }


    /**
     * 获取公私钥加密
     */
    public static String getJYDPSecretKey() {
        return AppNetConfig.JYDP_SECRET_SIGN;
    }
}
