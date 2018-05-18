package com.qmkj.jydp.bean.request;

/**
 * 创建日期：2018/5/18
 * @author Yi Shan Xiang
 * 文件名称： ChangePassWordReq
 * email: 380948730@qq.com
 */
public class ChangePassWordReq extends BaseReq {
    private String confirmPassword;	//新密码确认	string
    private String newPassword;	//新密码
    private String oldPassword;	//旧密码
    private String validCode; //手机验证码


    public ChangePassWordReq() {
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
