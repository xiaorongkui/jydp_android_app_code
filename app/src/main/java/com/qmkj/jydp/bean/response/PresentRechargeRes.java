package com.qmkj.jydp.bean.response;

import java.util.List;

/**
 * 创建日期：2018/5/30
 *
 * @author Yi Shan Xiang
 *         文件名称： 充币记录
 *         email: 380948730@qq.com
 */
public class PresentRechargeRes extends BaseRes {


    /**
     * pageNumber : 0
     * totalPageNumber : 6
     * userRechargeCoinRecordList : [{"currencyName":"盛源链","currencyNumber":"1000","orderTime":1524748991000,
     * "remark":"盛源链app充值","walletOrderNo":"10152474899108043408"}]
     * webAppPath : /jydp
     */

    private int pageNumber; //页码
    private int totalPageNumber; //总页数
    private String webAppPath;
    private List<UserRechargeCoinRecordListBean> userRechargeCoinRecordList;//用户充币记录

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public List<UserRechargeCoinRecordListBean> getUserRechargeCoinRecordList() {
        return userRechargeCoinRecordList;
    }

    public void setUserRechargeCoinRecordList(List<UserRechargeCoinRecordListBean> userRechargeCoinRecordList) {
        this.userRechargeCoinRecordList = userRechargeCoinRecordList;
    }

    public static class UserRechargeCoinRecordListBean {
        /**
         * currencyName : 盛源链
         * currencyNumber : 1000
         * orderTime : 1524748991000
         * remark : 盛源链app充值
         * walletOrderNo : 10152474899108043408
         */

        private String currencyName; //货币名称
        private String currencyNumber; //币种数量
        private long orderTime; //订单时间
        private String remark; //备注
        private String walletOrderNo; //钱包订单号

        public String getCurrencyName() {
            return currencyName;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public String getCurrencyNumber() {
            return currencyNumber;
        }

        public void setCurrencyNumber(String currencyNumber) {
            this.currencyNumber = currencyNumber;
        }

        public long getOrderTime() {
            return orderTime;
        }

        public void setOrderTime(long orderTime) {
            this.orderTime = orderTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWalletOrderNo() {
            return walletOrderNo;
        }

        public void setWalletOrderNo(String walletOrderNo) {
            this.walletOrderNo = walletOrderNo;
        }
    }
}
