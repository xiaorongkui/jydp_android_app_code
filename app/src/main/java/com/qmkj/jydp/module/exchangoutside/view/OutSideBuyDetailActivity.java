package com.qmkj.jydp.module.exchangoutside.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.dd.ShadowLayout;
import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.event.OutSideExchangeEvent;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.bean.response.OutSideBuyPayDetailRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.exchangoutside.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxBus;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/7
 * email：dovexiaoen@163.com
 * description:确认购买
 */

public class OutSideBuyDetailActivity extends BaseMvpActivity<OutsideExchangePresenter> {
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
    private OutSideBuyPayDetailRes outSideBuyPayDetailRes;
    OutSideBuyPayDetailRes.UserPaymentTypeBean bean;
    private CommonDialog qrCodeDialog;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        outSideBuyPayDetailRes = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_1);
        String paymentMoney = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        bean = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_3);
        if (outSideBuyPayDetailRes == null) return;
        outsideBuyDistributorNameCiv.setRightText(outSideBuyPayDetailRes.getDealerName());
        businessPhoneCiv.setRightText(outSideBuyPayDetailRes.getPhoneNumber());
        businessNameCiv.setRightText(outSideBuyPayDetailRes.getUserName());

        LogUtil.i("bean=" + (bean == null ? "" : bean.toString()));
        if (bean == null) return;
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
        obtainedMoneyCiv.setRightText("¥" + paymentMoney);//todo

        alipayIv.setOnClickListener(this);
        weixinIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.alipay_iv:
                showQRCodeDialog();
                break;
            case R.id.weixin_iv:
                showQRCodeDialog();
                break;
        }
    }

    private void showQRCodeDialog() {
        qrCodeDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout.dialog_qr_code);
        ImageView qr_code_iv = qrCodeDialog.getView(R.id.qr_code_iv, ImageView.class);
        if (bean == null || TextUtils.isEmpty(bean.getPaymentImageFormat())) return;
        GlideApp.with(mContext).asBitmap().signature(new MediaStoreSignature("image/jpeg", System.currentTimeMillis()
                , 0)).load(bean.getPaymentImageFormat()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (resource != null) {
                    qr_code_iv.setImageBitmap(resource);
                    ViewGroup.LayoutParams layoutParams = qr_code_iv.getLayoutParams();
                    layoutParams.width = (int) CommonUtil.getDimen(R.dimen.x200);
                    layoutParams.height = (int) CommonUtil.getDimen(R.dimen.x400);
                    qr_code_iv.setLayoutParams(layoutParams);
                }
            }
        });

        qrCodeDialog.setCanceledOnTouchOutside(true);
        qrCodeDialog.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x250));
        qrCodeDialog.show();
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
        comfirmSoldComfirmBt.setOnClickListener(v -> startPayment());
//        RxView.clicks(comfirmSoldComfirmBt).throttleFirst(2, TimeUnit.SECONDS).compose(bindToLifecycle()).observeOn
//                (AndroidSchedulers.mainThread()).subscribe(o -> startPayment());
    }

    private void startPayment() {
        OutSideBuyPayReq outSideBuyPayReq = new OutSideBuyPayReq();
        OutSideBuyPayDetailRes.UserPaymentTypeBean userPaymentType = outSideBuyPayDetailRes.getUserPaymentType();
        if (userPaymentType == null) return;
        outSideBuyPayReq.setPaymentType(userPaymentType.getPaymentType() + "");
        outSideBuyPayReq.setOtcPendingOrderNo(userPaymentType.getOtcPendingOrderNo());
        outSideBuyPayReq.setBuyNum(outSideBuyPayDetailRes.getBuyNum());
        presenter.payOutsideExchange(outSideBuyPayReq, BUY_PAY_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_PAY_TAG:
                toast("购买成功");
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (qrCodeDialog != null && qrCodeDialog.isShowing()) {
            qrCodeDialog.dismiss();
        }
    }
}
