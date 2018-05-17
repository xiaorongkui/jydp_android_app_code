package com.qmkj.jydp.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:
 */

public class LoginRes extends BaseRes implements Serializable {

    /**
     * identification : {"id":404,"userId":431,"userAccount":"15549370360","userName":"肖荣奎",
     * "phoneAreaCode":"+86","userPhone":"15549370360","userCertType":1,"userCertNo":"420322199102264554",
     * "identificationStatus":1,"remark":null,"identiTime":null,"addTime":1526522593000}
     * userAccount : 15549370360
     * identificationImageList : [{"id":695,"identificationId":404,
     * "imageUrl":"/upload/identificationImage/20180517/2018051710024160164519571.jpg","addTime":1526522593000,
     * "imageUrlFormat":"http://test.oksheng.com
     * .cn/fileservice/upload/identificationImage/20180517/2018051710024160164519571.jpg"},{"id":696,
     * "identificationId":404,"imageUrl":"/upload/identificationImage/20180517/2018051710024170743895118.jpg",
     * "addTime":1526522593000,"imageUrlFormat":"http://test.oksheng.com
     * .cn/fileservice/upload/identificationImage/20180517/2018051710024170743895118.jpg"}]
     * userId : 431
     */

    private IdentificationBean identification;
    private String userAccount;
    private int userId;
    private List<IdentificationImageListBean> identificationImageList;
    /**
     * user : {"sessionId":null,"userId":313,"userAccount":"syl001","outTime":1526522798406,"isPwd":1,"isDealer":2}
     * token : 9909c76f-4f7e-4442-adff-26902dae86c6f363cb732c894b0188011873a6d2404d
     */

    private UserBean user;
    private String token;

    public IdentificationBean getIdentification() {
        return identification;
    }

    public void setIdentification(IdentificationBean identification) {
        this.identification = identification;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<IdentificationImageListBean> getIdentificationImageList() {
        return identificationImageList;
    }

    public void setIdentificationImageList(List<IdentificationImageListBean> identificationImageList) {
        this.identificationImageList = identificationImageList;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class IdentificationBean implements Serializable {
        /**
         * id : 404
         * userId : 431
         * userAccount : 15549370360
         * userName : 肖荣奎
         * phoneAreaCode : +86
         * userPhone : 15549370360
         * userCertType : 1
         * userCertNo : 420322199102264554
         * identificationStatus : 1
         * remark : null
         * identiTime : null
         * addTime : 1526522593000
         */

        private int id;
        private int userId;
        private String userAccount;
        private String userName;
        private String phoneAreaCode;
        private String userPhone;
        private int userCertType;
        private String userCertNo;
        private int identificationStatus;
        private Object remark;
        private Object identiTime;
        private long addTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhoneAreaCode() {
            return phoneAreaCode;
        }

        public void setPhoneAreaCode(String phoneAreaCode) {
            this.phoneAreaCode = phoneAreaCode;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public int getUserCertType() {
            return userCertType;
        }

        public void setUserCertType(int userCertType) {
            this.userCertType = userCertType;
        }

        public String getUserCertNo() {
            return userCertNo;
        }

        public void setUserCertNo(String userCertNo) {
            this.userCertNo = userCertNo;
        }

        public int getIdentificationStatus() {
            return identificationStatus;
        }

        public void setIdentificationStatus(int identificationStatus) {
            this.identificationStatus = identificationStatus;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getIdentiTime() {
            return identiTime;
        }

        public void setIdentiTime(Object identiTime) {
            this.identiTime = identiTime;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }
    }

    public static class IdentificationImageListBean implements Serializable {
        /**
         * id : 695
         * identificationId : 404
         * imageUrl : /upload/identificationImage/20180517/2018051710024160164519571.jpg
         * addTime : 1526522593000
         * imageUrlFormat : http://test.oksheng.com
         * .cn/fileservice/upload/identificationImage/20180517/2018051710024160164519571.jpg
         */

        private int id;
        private int identificationId;
        private String imageUrl;
        private long addTime;
        private String imageUrlFormat;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIdentificationId() {
            return identificationId;
        }

        public void setIdentificationId(int identificationId) {
            this.identificationId = identificationId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public long getAddTime() {
            return addTime;
        }

        public void setAddTime(long addTime) {
            this.addTime = addTime;
        }

        public String getImageUrlFormat() {
            return imageUrlFormat;
        }

        public void setImageUrlFormat(String imageUrlFormat) {
            this.imageUrlFormat = imageUrlFormat;
        }
    }

    public static class UserBean {
        /**
         * sessionId : null
         * userId : 313
         * userAccount : syl001
         * outTime : 1526522798406
         * isPwd : 1
         * isDealer : 2
         */

        private Object sessionId;
        @SerializedName("userId")
        private int userIdX;
        @SerializedName("userAccount")
        private String userAccountX;
        private long outTime;
        private int isPwd;
        private int isDealer;

        public Object getSessionId() {
            return sessionId;
        }

        public void setSessionId(Object sessionId) {
            this.sessionId = sessionId;
        }

        public int getUserIdX() {
            return userIdX;
        }

        public void setUserIdX(int userIdX) {
            this.userIdX = userIdX;
        }

        public String getUserAccountX() {
            return userAccountX;
        }

        public void setUserAccountX(String userAccountX) {
            this.userAccountX = userAccountX;
        }

        public long getOutTime() {
            return outTime;
        }

        public void setOutTime(long outTime) {
            this.outTime = outTime;
        }

        public int getIsPwd() {
            return isPwd;
        }

        public void setIsPwd(int isPwd) {
            this.isPwd = isPwd;
        }

        public int getIsDealer() {
            return isDealer;
        }

        public void setIsDealer(int isDealer) {
            this.isDealer = isDealer;
        }
    }
}
