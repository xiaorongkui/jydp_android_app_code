package com.qmkj.jydp.bean.request;

/**
 * 用户提币请求
 */
public class UserWithdrawReq {
    private String buyPwd;  //支付密码
    private String currencyId;  //币种id
    private String number;  //数量
    private String validateCode;  //短信验证码

    public String getBuyPwd() {
        return buyPwd;
    }

    public void setBuyPwd(String buyPwd) {
        this.buyPwd = buyPwd;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
