package com.qmkj.jydp.common;

public class NetResponseCode {
    public final static String HMC_SUCCESS = "1";//成功
    public final static String HMC_SUCCESS_NULL = "-1";//请求成功，但是data为空。自定义
    public static final String HMC_NETWORK_ERROR = "-00000";//没有网络
    public static final String HMC_HAS_CHECKING = "5";//已在认证中
    public static final String HMC_NO_LOGIN = "4";//已在认证中
    public static final String HMC_EXCHANGE_PWD_ERROR = "101";//交易中心买卖支付密码错误
}