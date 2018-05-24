package com.qmkj.jydp.module.exchangoutsidee.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.ShadowLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.event.OutSideExchangeEvent;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.response.OutSideBuyPayDetailRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.RxBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * author：rongkui.xiao --2018/5/7
 * email：dovexiaoen@163.com
 * description:确认购买
 */

public class OutSideBuyDetailActivity extends BaseMvpActivity<OutsideExchangePresenter> {
    private static final int BUY_DETAIL_INFO_TAG = 1;
    private static final int BUY_PAY_TAG = 2;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.outside_buy_distributor_name_civ)
    ClickItemView outsideBuyDistributorNameCiv;
    @BindView(R.id.business_phone_civ)
    ClickItemView businessPhoneCiv;
    @BindView(R.id.business_name_civ)
    ClickItemView businessNameCiv;
    @BindView(R.id.alipay_civ)
    ClickItemView alipayCiv;
    @BindView(R.id.alipay_iv)
    ImageView alipayIv;
    @BindView(R.id.sold_amount_civ)
    ClickItemView soldAmountCiv;
    @BindView(R.id.obtained_money_civ)
    ClickItemView obtainedMoneyCiv;
    @BindView(R.id.comfirm_sold_comfirm_bt)
    Button comfirmSoldComfirmBt;
    @BindView(R.id.exchange_outside_detail_ll)
    LinearLayout exchangeOutsideDetailLl;
    @BindView(R.id.bank_pyament_method_card_num_civ)
    ClickItemView bankPyamentMethodCardNumCiv;
    @BindView(R.id.bank_pyament_method_bank_name_civ)
    ClickItemView bankPyamentMethodBankNameCiv;
    @BindView(R.id.bank_pyament_method_bank_branch_name_civ)
    ClickItemView bankPyamentMethodBankBranchNameCiv;
    @BindView(R.id.bank_pyament_method_bank_payee_name_civ)
    ClickItemView bankPyamentMethodBankPayeeNameCiv;
    @BindView(R.id.bank_pyament_method_bank_payee_phone_civ)
    ClickItemView bankPyamentMethodBankPayeePhoneCiv;
    @BindView(R.id.bank_pyament_method_sl)
    ShadowLayout bankPyamentMethodSl;
    @BindView(R.id.alipay_pyament_method_sl)
    ShadowLayout alipayPyamentMethodSl;
    @BindView(R.id.weixin_civ)
    ClickItemView weixinCiv;
    @BindView(R.id.weixin_iv)
    ImageView weixinIv;
    @BindView(R.id.weixin_pyament_method_sl)
    ShadowLayout weixinPyamentMethodSl;
    private OutSideBuyPayDetailReq outSideBuyPayDetailReq;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        outSideBuyPayDetailReq = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_1);
        getBuyDetailData();
    }

    private void getBuyDetailData() {
        if (outSideBuyPayDetailReq == null) return;
        presenter.payOutsideDetailConfirm(outSideBuyPayDetailReq, BUY_DETAIL_INFO_TAG, true);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.confirm_buy));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.outside_activity_buy_detail;
    }

    @Override
    protected void initView() {
        showSuccessView(exchangeOutsideDetailLl, false);
        RxView.clicks(comfirmSoldComfirmBt).throttleFirst(2, TimeUnit.SECONDS).compose(bindToLifecycle()).observeOn
                (AndroidSchedulers.mainThread()).subscribe(o -> startPayment());
    }

    private void startPayment() {
        OutSideBuyPayReq outSideBuyPayReq = new OutSideBuyPayReq();
        outSideBuyPayReq.setPaymentType(outSideBuyPayDetailReq.getPaymentType());
        outSideBuyPayReq.setOtcPendingOrderNo(outSideBuyPayDetailReq.getOtcPendingOrderNo());
        outSideBuyPayReq.setBuyNum(outSideBuyPayDetailReq.getBuyNum());
        presenter.payOutsideExchange(outSideBuyPayReq, BUY_PAY_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_DETAIL_INFO_TAG:
                OutSideBuyPayDetailRes outSideBuyPayDetailRes = (OutSideBuyPayDetailRes) response;
                if (outSideBuyPayDetailRes == null || outSideBuyPayDetailRes.getOtcDealerUserDO() == null ||
                        outSideBuyPayDetailRes
                                .getUserPaymentType() == null) {
                    toast("数去获取为空");
                    return;
                }
                OutSideBuyPayDetailRes.OtcDealerUserDOBean otcDealerUserDO = outSideBuyPayDetailRes
                        .getOtcDealerUserDO();
                showSuccessView(exchangeOutsideDetailLl, true);
                outsideBuyDistributorNameCiv.setRightText(outSideBuyPayDetailRes.getDealerName());
                businessPhoneCiv.setRightText(outSideBuyPayDetailRes.getPhoneNumber());
                businessNameCiv.setRightText(outSideBuyPayDetailRes.getUserName());

                OutSideBuyPayDetailRes.UserPaymentTypeBean bean = outSideBuyPayDetailRes
                        .getUserPaymentType();
                switch (bean.getPaymentType()) {
                    case 1://银行卡
                        bankPyamentMethodSl.setVisibility(View.VISIBLE);
                        bankPyamentMethodCardNumCiv.setRightText(bean.getPaymentAccount());
                        bankPyamentMethodBankNameCiv.setRightText(bean.getBankName());
                        bankPyamentMethodBankBranchNameCiv.setRightText(bean.getBankBranch());
                        bankPyamentMethodBankPayeeNameCiv.setRightText(bean.getPaymentName());
                        bankPyamentMethodBankPayeePhoneCiv.setRightText(bean.getPaymentPhone());
                        break;
                    case 2://支付宝
                        alipayPyamentMethodSl.setVisibility(View.VISIBLE);

                        alipayCiv.setRightText(bean.getPaymentAccount());
                        GlideApp.with(mContext).load(bean.getPaymentImageFormat()).into(alipayIv);
                        break;
                    case 3://微信
                        weixinPyamentMethodSl.setVisibility(View.VISIBLE);
                        weixinCiv.setRightText(bean.getPaymentAccount());
                        GlideApp.with(mContext).load(bean.getPaymentImageFormat()).into(weixinIv);
                        break;
                }
                soldAmountCiv.setRightText(outSideBuyPayDetailRes.getBuyNum());
                obtainedMoneyCiv.setRightText(outSideBuyPayDetailReq.getPayMentMoney());//todo
                break;
            case BUY_PAY_TAG:
                toast("购买发起成功");
                RxBus.getDefault().post(new OutSideExchangeEvent());
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, 2);//去场外交易页面
                CommonUtil.gotoActivity(mContext, intent);
                AppManager.getInstance().removeCurrent();
                AppManager.getInstance().removeActivity(OutSideBuyActivity.class);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        switch (tag) {
            case BUY_DETAIL_INFO_TAG:
                showNetErrorView(exchangeOutsideDetailLl, true);
                break;
        }
    }

    @Override
    protected void tryData(int id) {
        super.tryData(id);
        switch (id) {
            case R.id.exchange_outside_detail_ll:
                getBuyDetailData();
                break;
        }
    }

}
