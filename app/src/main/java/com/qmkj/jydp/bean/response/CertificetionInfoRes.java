package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * author：rongkui.xiao --2018/5/17
 * email：dovexiaoen@163.com
 * description:
 */

public class CertificetionInfoRes extends BaseRes {

    /**
     * identification : {"id":551,"userId":555,"userAccount":"15549370360","userName":"肖荣奎",
     * "phoneAreaCode":"+86","userPhone":"15549370360","userCertType":1,"userCertNo":"420322199102264554",
     * "identificationStatus":1,"remark":null,"identiTime":null,"addTime":1526545297000}
     * identificationImageList : [{"id":785,"identificationId":551,
     * "imageUrl":"/upload/identificationImage/20180517/2018051716213897539691783.jpg","addTime":1526545298000,
     * "imageUrlFormat":"http://test.oksheng.com
     * .cn/fileservice/upload/identificationImage/20180517/2018051716213897539691783.jpg"},{"id":786,
     * "identificationId":551,"imageUrl":"/upload/identificationImage/20180517/2018051716213813585116486.jpg",
     * "addTime":1526545298000,"imageUrlFormat":"http://test.oksheng.com
     * .cn/fileservice/upload/identificationImage/20180517/2018051716213813585116486.jpg"}]
     */

    private IdentificationBean identification;
    private List<IdentificationImageListBean> identificationImageList;

    public IdentificationBean getIdentification() {
        return identification;
    }

    public void setIdentification(IdentificationBean identification) {
        this.identification = identification;
    }

    public List<IdentificationImageListBean> getIdentificationImageList() {
        return identificationImageList;
    }

    public void setIdentificationImageList(List<IdentificationImageListBean> identificationImageList) {
        this.identificationImageList = identificationImageList;
    }

    public static class IdentificationBean {
        /**
         * id : 551
         * userId : 555
         * userAccount : 15549370360
         * userName : 肖荣奎
         * phoneAreaCode : +86
         * userPhone : 15549370360
         * userCertType : 1
         * userCertNo : 420322199102264554
         * identificationStatus : 1
         * remark : null
         * identiTime : null
         * addTime : 1526545297000
         */

        private int id;
        private int userId;
        private String userAccount;
        private String userName;
        private String phoneAreaCode;
        private String userPhone;
        private int userCertType;
        private String userCertNo;
        private int identificationStatus;//1待审核。2审核通过，3审核拒绝
        private String remark;
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

    public static class IdentificationImageListBean {
        /**
         * id : 785
         * identificationId : 551
         * imageUrl : /upload/identificationImage/20180517/2018051716213897539691783.jpg
         * addTime : 1526545298000
         * imageUrlFormat : http://test.oksheng.com
         * .cn/fileservice/upload/identificationImage/20180517/2018051716213897539691783.jpg
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
}
