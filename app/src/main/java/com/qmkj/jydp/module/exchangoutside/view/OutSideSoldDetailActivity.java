package com.qmkj.jydp.module.exchangoutside.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.ShadowLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.event.OutSideExchangeEvent;
import com.qmkj.jydp.bean.request.OutSideSellReq;
import com.qmkj.jydp.bean.response.OutSideSellDetailRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.exchangoutside.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.RxBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author：rongkui.xiao --2018/5/7
 * email：dovexiaoen@163.com
 * description:确认出售
 */

public class OutSideSoldDetailActivity extends BaseMvpActivity<OutsideExchangePresenter> {
    private static final int OUTSIDE_SELL_TAG = 1;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.distributor_name_civ)
    ClickItemView distributorNameCiv;
    @BindView(R.id.business_phone_civ)
    ClickItemView businessPhoneCiv;
    @BindView(R.id.business_name_civ)
    ClickItemView businessNameCiv;
    @BindView(R.id.bank_payment_method_card_num_civ)
    ClickItemView bankPaymentMethodCardNumCiv;
    @BindView(R.id.bank_payment_method_bank_name_civ)
    ClickItemView bankPaymentMethodBankNameCiv;
    @BindView(R.id.bank_payment_method_bank_branch_name_civ)
    ClickItemView bankPaymentMethodBankBranchNameCiv;
    @BindView(R.id.bank_payment_method_bank_payee_name_civ)
    ClickItemView bankPaymentMethodBankPayeeNameCiv;
    @BindView(R.id.bank_payment_method_bank_payee_phone_civ)
    ClickItemView bankPaymentMethodBankPayeePhoneCiv;
    @BindView(R.id.bank_pyament_method_sl)
    ShadowLayout bankPyamentMethodSl;
    @BindView(R.id.alipay_civ)
    ClickItemView alipayCiv;
    @BindView(R.id.alipay_iv)
    ImageView alipayIv;
    @BindView(R.id.alipay_payment_method_sl)
    ShadowLayout alipayPaymentMethodSl;
    @BindView(R.id.weixin_civ)
    ClickItemView weixinCiv;
    @BindView(R.id.weixin_iv)
    ImageView weixinIv;
    @BindView(R.id.weixin_pyament_method_sl)
    ShadowLayout weixinPyamentMethodSl;
    @BindView(R.id.sold_amount_civ)
    ClickItemView soldAmountCiv;
    @BindView(R.id.obtained_money_civ)
    ClickItemView obtainedMoneyCiv;
    @BindView(R.id.comfirm_sold_comfirm_bt)
    Button comfirmSoldComfirmBt;
    private OutSideSellDetailRes outSideSellDetailRes;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        RxView.clicks(comfirmSoldComfirmBt).throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> submitSellOrder());
    }


    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.confirm_sold));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.outside_activity_sold_detail;
    }

    @Override
    protected void initView() {
        outSideSellDetailRes = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_1);
        if (outSideSellDetailRes == null || outSideSellDetailRes.getOtcTransactionPendOrder() == null) return;

        distributorNameCiv.setRightText(outSideSellDetailRes.getOtcTransactionPendOrder().getDealerName());
        businessPhoneCiv.setRightText(outSideSellDetailRes.getPhoneNumber());
        businessNameCiv.setRightText(outSideSellDetailRes.getUserName());
        int paymentType = outSideSellDetailRes.getPaymentType();
        switch (paymentType) {
            case 1://银行卡
                bankPyamentMethodSl.setVisibility(View.VISIBLE);
                bankPaymentMethodCardNumCiv.setRightText(outSideSellDetailRes.getPaymentAccount());
                bankPaymentMethodBankNameCiv.setRightText(outSideSellDetailRes.getBankName());
                bankPaymentMethodBankBranchNameCiv.setRightText(outSideSellDetailRes.getBankBranch());
                bankPaymentMethodBankPayeeNameCiv.setRightText(outSideSellDetailRes.getPaymentName());
                bankPaymentMethodBankPayeePhoneCiv.setRightText(outSideSellDetailRes.getPaymentPhone());
                break;
            case 2://支付宝
                alipayPaymentMethodSl.setVisibility(View.VISIBLE);

                alipayCiv.setRightText(outSideSellDetailRes.getPaymentAccount());
                GlideApp.with(mContext).load(outSideSellDetailRes.getImageUrlFormat()).into(alipayIv);
                break;
            case 3://微信
                weixinPyamentMethodSl.setVisibility(View.VISIBLE);
                weixinCiv.setRightText(outSideSellDetailRes.getPaymentAccount());
                GlideApp.with(mContext).load(outSideSellDetailRes.getImageUrlFormat()).into(weixinIv);
                break;
        }
        soldAmountCiv.setRightText(outSideSellDetailRes.getSellNum() + "");
        obtainedMoneyCiv.setRightText("¥" + outSideSellDetailRes.getSellMoney());//todo
    }

    private void submitSellOrder() {
        if (outSideSellDetailRes == null || outSideSellDetailRes.getOtcTransactionPendOrder() == null) return;
        OutSideSellReq outSideSellReq = new OutSideSellReq();
        outSideSellReq.setAlipayPaymentAccount(outSideSellDetailRes.getPaymentAccount());
        outSideSellReq.setBankBranch(outSideSellDetailRes.getBankBranch());
        outSideSellReq.setBankCardPaymentAccount(outSideSellDetailRes.getPaymentAccount());
        outSideSellReq.setBankName(outSideSellDetailRes.getBankName());
        outSideSellReq.setImageUrl(outSideSellDetailRes.getImageUrlFormat());
        outSideSellReq.setOtcPendingOrderNo(outSideSellDetailRes.getOtcTransactionPendOrder().getOtcPendingOrderNo());
        outSideSellReq.setPaymentName(outSideSellDetailRes.getPaymentName());
        outSideSellReq.setPaymentPhone(outSideSellDetailRes.getPaymentPhone());
        outSideSellReq.setPaymentType(outSideSellDetailRes.getPaymentType() + "");
        outSideSellReq.setSellNum(outSideSellDetailRes.getSellNum() + "");
        outSideSellReq.setWechatPaymentAccount(outSideSellDetailRes.getPaymentAccount());
        presenter.sellOutsideExchange(outSideSellReq, OUTSIDE_SELL_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case OUTSIDE_SELL_TAG:
                toast("卖出发起成功");
                RxBus.getDefault().post(new OutSideExchangeEvent());
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, 2);//去场外交易页面
                CommonUtil.gotoActivity(mContext, intent);
                AppManager.getInstance().removeCurrent();
                AppManager.getInstance().removeActivity(OutSideSoldActivity.class);
                break;
        }
    }
}
