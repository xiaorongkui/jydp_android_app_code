package com.qmkj.jydp.bean.request;

/**
 * 创建日期：2018/5/21
 *
 * @author Yi Shan Xiang
 *         文件名称： ChangePhoneReq
 *         email: 380948730@qq.com
 */
public class ChangePhoneReq extends BaseReq {
    private String areaCode;    //	区号	string	@mock=+86
    private String newValidCode;    //	新的手机的验证码	string	@mock=123456
    private String oldValidCode;    //	原先手机的验证码	string	@mock=123456
    private String password;    //	登录密码	string	@mock=123123
    private String phone;    //	新电话号码

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNewValidCode() {
        return newValidCode;
    }

    public void setNewValidCode(String newValidCode) {
        this.newValidCode = newValidCode;
    }

    public String getOldValidCode() {
        return oldValidCode;
    }

    public void setOldValidCode(String oldValidCode) {
        this.oldValidCode = oldValidCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
