package com.qmkj.jydp.module.mine.view;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.request.OutSideDetailReq;
import com.qmkj.jydp.bean.response.OtcDealRecordDetailsRes;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.ui.widget.dialog.base.BaseDialog;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.NumberUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:场外交易订单详情记录
 */

public class OutSideExchangeOrderDetailActivity extends BaseMvpActivity<MinePresenter> {
    private static final int GET_MSG = 1;
    private static final int SEND_REQUEST = 2;

    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.order_detail_status_tv) //完成状态
            TextView order_detail_status_tv;
    @BindView(R.id.outside_exchange_recode_order_num_tv) //订单号
            TextView outside_exchange_recode_order_num_tv;
    @BindView(R.id.exchange_recode_detail_name_civ)//名称
            TextView exchange_recode_detail_name_civ;
    @BindView(R.id.exchange_recode_detail_amount_civ)//数量
            ClickItemView exchange_recode_detail_amount_civ;
    @BindView(R.id.exchange_recode_detail_money_civ)//金额
            ClickItemView exchange_recode_detail_money_civ;
    @BindView(R.id.exchange_recode_detail_type_civ)//类型
            ClickItemView exchange_recode_detail_type_civ;
    @BindView(R.id.exchange_recode_detail_area_civ)//地区
            ClickItemView exchange_recode_detail_area_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_name_civ)//经销商名称
            ClickItemView exchange_recode_detail_dealer_alipay_name_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_phone_civ)//手机号码
            ClickItemView exchange_recode_detail_dealer_alipay_phone_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_account_civ)//支付宝账号
            ClickItemView exchange_recode_detail_dealer_alipay_account_civ;
    @BindView(R.id.exchange_recode_detail_dealer_weixin_account_civ)//微信账号
            ClickItemView exchange_recode_detail_dealer_weixin_account_civ;
    @BindView(R.id.exchange_recode_detail_dealer_bank_civ)//银行
            ClickItemView exchange_recode_detail_dealer_bank_civ;
    @BindView(R.id.exchange_recode_detail_dealer_bank_branch_civ)//支行名称
            ClickItemView exchange_recode_detail_dealer_bank_branch_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_img)
    ImageView exchange_recode_detail_dealer_alipay_img;
    @BindView(R.id.exchange_recode_detail_apply_time_civ)  //申请时间
            ClickItemView exchange_recode_detail_apply_time_civ;
    @BindView(R.id.exchange_recode_detail_finish_time_civ)  //完成时间
            ClickItemView exchange_recode_detail_finish_time_civ;
    @BindView(R.id.register_bt)
    Button register_bt;
    @BindView(R.id.exchange_recode_detail_dealer_bank_layout)
    LinearLayout exchange_recode_detail_dealer_bank_layout;
    private OtcDealRecordDetailsRes.OtcTransactionUserDealBean data;
    private int type;  //1.普通用户  2.经销商

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        String number = getIntent().getStringExtra("NUMBER");
        type = getIntent().getIntExtra(MineRecodeActivity.RECODE_TYPE, 1);
        OutSideDetailReq req = new OutSideDetailReq();
        req.setOtcOrderNo(number);
        //1：普通用户 2：经销商
        switch (type) {
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                presenter.getUserSideOrderDetaid(req, 1, true);
                break;
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                presenter.getOutSideOrderDetaid(req, 1, true);
                break;
        }
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.order_detail));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_outside_exchange_order_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case GET_MSG:
                OtcDealRecordDetailsRes res = (OtcDealRecordDetailsRes) response;
                data = res.getOtcTransactionUserDeal();
                if (data != null) {
                    setMessageView(res.getOtcTransactionUserDeal());
                }
                break;
            case SEND_REQUEST:
                setResult(200);
                finish();
        }

    }

    private void setMessageView(OtcDealRecordDetailsRes.OtcTransactionUserDealBean res) {
        String text = null;
        //4：已完成，5:已撤销, 其他：待完成
        if (res.getDealStatus() == 4) {
            text = "已完成";
            register_bt.setVisibility(View.GONE);
        } else if (res.getDealStatus() == 5) {
            text = "已撤销";
            register_bt.setVisibility(View.GONE);
        } else {
            text = "待完成";
            register_bt.setVisibility(View.VISIBLE);
        }
        order_detail_status_tv.setText(text);
        outside_exchange_recode_order_num_tv.setText(res.getOtcOrderNo());
        exchange_recode_detail_name_civ.setText(res.getCurrencyName());
        exchange_recode_detail_amount_civ.setRightText(NumberUtil.doubleFormat(Double.parseDouble(res
                .getCurrencyNumber() + ""), 4));
        exchange_recode_detail_money_civ.setRightText("$" + NumberUtil.doubleFormat(Double.parseDouble(res
                .getCurrencyTotalPrice() + ""), 2));
        String text_type = null;
        switch (res.getDealType()) {
            case 1:
                if (type == MineRecodeActivity.RECODE_TYPE_NORMAL) { //普通用户
                    text_type = "购买";
                    exchange_recode_detail_type_civ.setRightTextColor(mContext.getResources().getColor(R.color
                            .color_green_3));
                } else if (type == MineRecodeActivity.RECODE_TYPE_AGENCY) { //经销商
                    text_type = "出售";
                    exchange_recode_detail_type_civ.setRightTextColor(mContext.getResources().getColor(R.color
                            .color_red_3));
                }
                break;
            case 2:
                if (type == MineRecodeActivity.RECODE_TYPE_NORMAL) { //普通用户
                    text_type = "出售";
                    exchange_recode_detail_type_civ.setRightTextColor(mContext.getResources().getColor(R.color
                            .color_red_3));
                } else if (type == MineRecodeActivity.RECODE_TYPE_AGENCY) { //经销商
                    text_type = "回购";
                    exchange_recode_detail_type_civ.setRightTextColor(mContext.getResources().getColor(R.color
                            .color_green_3));
                }
                break;
            case 3:
                text_type = "撤销";
                exchange_recode_detail_type_civ.setRightTextColor(mContext.getResources().getColor(R.color
                        .color_gray_3));
                break;
        }

        exchange_recode_detail_type_civ.setRightText(text_type);
        exchange_recode_detail_area_civ.setRightText(res.getArea());
        exchange_recode_detail_dealer_alipay_name_civ.setRightText(res.getDealerName());
        exchange_recode_detail_dealer_alipay_phone_civ.setRightText(res.getPhoneNumber());
        exchange_recode_detail_apply_time_civ.setRightText(DateUtil.longToTimeStr(res.getAddTime(), DateUtil
                .dateFormat2));
        exchange_recode_detail_finish_time_civ.setRightText(DateUtil.longToTimeStr(res.getUpdateTime(), DateUtil
                .dateFormat2));

        //收款方式标识：1：银行卡，2：支付宝，3：微信
        switch (res.getPaymentType()) {
            case 1:
                exchange_recode_detail_dealer_bank_layout.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_bank_civ.setRightText(res.getBankName());
                exchange_recode_detail_dealer_bank_branch_civ.setRightText(res.getBankBranch());
                break;
            case 2:
                exchange_recode_detail_dealer_alipay_account_civ.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_img.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_account_civ.setRightText(res.getPaymentAccount());
                GlideApp.with(mContext).load(res.getPaymentImage()).placeholder(R.mipmap.ic_launcher).into
                        (exchange_recode_detail_dealer_alipay_img);
                break;
            case 3:
                exchange_recode_detail_dealer_weixin_account_civ.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_img.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_weixin_account_civ.setRightText(res.getPaymentAccount());
                GlideApp.with(mContext).load(res.getPaymentImage()).placeholder(R.mipmap.ic_launcher).into
                        (exchange_recode_detail_dealer_alipay_img);
                break;
        }

        register_bt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.register_bt:
                CommonDialog commonDialog = new CommonDialog(this);
                commonDialog.setTitleText("确认收款");
                commonDialog.setContentText("确认已收到货款？");
                commonDialog.setOnPositiveButtonClickListener(new BaseDialog.OnPositiveButtonClickListener() {
                    @Override
                    public void onClick(Dialog dialog, View view) {
                        if (data == null) {
                            return;
                        }
                        OutSideDetailReq req = new OutSideDetailReq();
                        req.setOtcOrderNo(data.getOtcOrderNo());
                        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser().getIsDealer()
                                == 2) {   //=2 为经销商
                            presenter.getOutSideOrderTakeMoney(req, SEND_REQUEST, true);
                        } else {
                            presenter.getOutSideOrderTakeUser(req, SEND_REQUEST, true);
                        }
                        commonDialog.dismiss();
                    }
                });
                commonDialog.show();

                break;
        }
    }
}
