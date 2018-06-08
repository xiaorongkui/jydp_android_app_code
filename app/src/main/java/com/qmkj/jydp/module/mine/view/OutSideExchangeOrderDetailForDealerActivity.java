package com.qmkj.jydp.module.mine.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.MediaStoreSignature;
import com.dd.ShadowLayout;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.request.OutSideDetailReq;
import com.qmkj.jydp.bean.response.OtcDealRecordDetailsRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.dialog.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;
import com.qmkj.jydp.util.NumberUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 场外交易订单详情（经销商）
 *
 * @author Yun
 */
public class OutSideExchangeOrderDetailForDealerActivity extends BaseMvpActivity<MinePresenter> {
    private static final int REQUEST_TAG_GET_ORDER_DETAIL = 1;
    private static final int REQUEST_TAG_CONFIRM_RECEIPET = 2;
    //title
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    //订单状态图标
    @BindView(R.id.order_detail_status_iv)
    ImageView orderDetailStatusIv;
    //订单状态文字
    @BindView(R.id.order_detail_status_tv)
    TextView orderDetailStatusTv;
    ////交易流水号
    @BindView(R.id.order_detail_order_num_tv)
    TextView orderDetailOrderNoTv;
    //币种名称
    @BindView(R.id.order_detail_currency_name_tv)
    TextView orderDetailCurrencyNameTv;
    //购买数量
    @BindView(R.id.order_detail_currency_num_cv)
    ClickItemView orderDetailCurrencyNumCv;
    //购买金额
    @BindView(R.id.order_detail_currency_total_price_cv)
    ClickItemView orderDetailCurrencyTotalPriceCv;
    //交易类型 购买/出售
    @BindView(R.id.order_detail_deal_type_cv)
    ClickItemView orderDetailDealTypeCv;
    //地区
    @BindView(R.id.order_detail_area_cv)
    ClickItemView orderDetailAreaCv;
    //用户账号
    @BindView(R.id.order_detail_user_account_cv)
    ClickItemView orderDetailUserAccountCv;
    //用户手机号
    @BindView(R.id.order_detail_user_phone_num_cv)
    ClickItemView orderDetailUserPhoneNumCv;
    //用户支付方式
    @BindView(R.id.order_detail_user_payment_type_cv)
    ClickItemView orderDetailUserPaymentTypeCv;
    //用户信息布局
    @BindView(R.id.order_detail_user_info_content_fl)
    FrameLayout orderDetailUserInfoContentFl;
    //我的信息支付信息布局
    @BindView(R.id.order_detail_my_info_content_fl)
    FrameLayout orderDetailMyInfoContentFl;
    //我的信息布局
    @BindView(R.id.order_detail_my_info_sl)
    ShadowLayout orderDetailMyInfoSl;
    //申请时间
    @BindView(R.id.order_detail_add_time_cv)
    ClickItemView orderDetailAddTimeCv;
    //完成时间
    @BindView(R.id.order_detail_update_time_cv)
    ClickItemView orderDetailUpdateTimeCv;
    //确认收款
    @BindView(R.id.confirm_receipt_btn)
    Button confirmReceiptBtn;
    //订单详情信息
    private OtcDealRecordDetailsRes.OtcTransactionUserDealBean orderDetailInfo;
    //确认收款二次确认Dialog
    private CommonDialog commonDialog;
    private com.qmkj.jydp.ui.widget.CommonDialog qrCodeDialog;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.order_detail_dealer));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_out_side_exchange_order_detail_for_dealer;
    }

    @Override
    protected void initView() {
        if (orderDetailInfo == null) {
            return;
        }
        if (orderDetailInfo.getDealStatus() == 4) {
            orderDetailStatusTv.setText("已完成");
        } else if(orderDetailInfo.getDealStatus() == 5){
            orderDetailStatusTv.setText("已撤销");
        }else {
            orderDetailStatusTv.setText("待完成");
        }
        orderDetailOrderNoTv.setText(orderDetailInfo.getOtcOrderNo());
        orderDetailCurrencyNameTv.setText(orderDetailInfo.getCurrencyName());
        orderDetailCurrencyNumCv.setRightText(NumberUtil.doubleFormat(Double.parseDouble(orderDetailInfo.getCurrencyNumber()+""),4));
        orderDetailCurrencyTotalPriceCv.setRightText("$" + NumberUtil.doubleFormat(Double.parseDouble(orderDetailInfo.getCurrencyTotalPrice() + ""), 2));
        //1：出售 2：购买 3：撤销
        switch (orderDetailInfo.getDealType()) {
            case 1:
                orderDetailDealTypeCv.setRightText("出售");
                orderDetailDealTypeCv.setRightTextColor(mContext.getResources().getColor(R.color.color_green_3));
                orderDetailUserPhoneNumCv.setVisibility(View.GONE);
                break;
            case 2:
                orderDetailDealTypeCv.setRightText("回购");
                orderDetailDealTypeCv.setRightTextColor(mContext.getResources().getColor(R.color.color_red_3));

                break;
            case 3:
                orderDetailDealTypeCv.setRightText("撤销");
                orderDetailDealTypeCv.setRightTextColor(mContext.getResources().getColor(R.color.color_gray_3));
                break;
        }
        orderDetailAreaCv.setRightText(orderDetailInfo.getArea());
        orderDetailUserAccountCv.setRightText(orderDetailInfo.getUserAccount());
        orderDetailUserPhoneNumCv.setRightText(orderDetailInfo.getUserPhone());
        //收款方式标识：1：银行卡，2：支付宝，3：微信
        switch (orderDetailInfo.getPaymentType()) {
            case 1:
                orderDetailUserPaymentTypeCv.setRightText("银行卡转账");
                break;
            case 2:
                orderDetailUserPaymentTypeCv.setRightText("支付宝转账");
                break;
            case 3:
                orderDetailUserPaymentTypeCv.setRightText("微信转账");
                break;
        }
        orderDetailAddTimeCv.setRightText(DateUtil.longToTimeStr(orderDetailInfo.getAddTime(), DateUtil.dateFormat2));
        orderDetailUpdateTimeCv.setRightText(DateUtil.longToTimeStr(orderDetailInfo.getUpdateTime(), DateUtil.dateFormat2));
        //只有当交易状态为未确认且交易类型为出售时 才显示确认收款按钮
        if (orderDetailInfo.getDealStatus() != 4 &&orderDetailInfo.getDealStatus() != 5&& orderDetailInfo.getDealType() == 1) {
            confirmReceiptBtn.setVisibility(View.VISIBLE);
        } else {
            confirmReceiptBtn.setVisibility(View.GONE);
        }
        //初始化经销商/我的支付信息布局
        initDealerAndMyPayInfoLayout();
    }

    /**
     * 初始化经销商支付信息布局
     */
    private void initDealerAndMyPayInfoLayout() {
        //经销商支付信息布局/我的支付信息布局 两个布局相同此处复用同一个
        View dealerPayLayout = LayoutInflater.from(mContext).inflate(R.layout.layout_out_side_exchange_order_detail_for_user_dealer_info, null);
        //银行卡信息布局
        View bankPayInfoLayout = dealerPayLayout.findViewById(R.id.my_bank_pay_info_ll);
        //微信支付宝信息布局
        View aliWeiXinPayInfoLayout = dealerPayLayout.findViewById(R.id.my_ali_weixin_pay_info_ll);
        //银行卡号
        ClickItemView bankPayBankNoCv = dealerPayLayout.findViewById(R.id.bank_pay_bank_card_no_cv);
        //银行信息
        ClickItemView bankPayBankInfoCv = dealerPayLayout.findViewById(R.id.bank_pay_bank_info_cv);
        //银行预留姓名
        ClickItemView bankPayBankUserNameCv = dealerPayLayout.findViewById(R.id.bank_pay_user_name_cv);
        //银行预留手机
        ClickItemView bankPayBankUserPhoneNumCv = dealerPayLayout.findViewById(R.id.bank_pay_user_phone_num_cv);
        //支付宝/微信 账号
        ClickItemView aliWeiXinPayAccountCv = dealerPayLayout.findViewById(R.id.ali_weixin_pay_account_cv);
        //支付宝/微信 二维码
        ImageView aliWeiXinPayQrcodeImg = dealerPayLayout.findViewById(R.id.ali_weixin_pay_qrcode_img);
        //收款方式标识：1：银行卡，2：支付宝，3：微信
        if (orderDetailInfo.getPaymentType() == 1) {
            //银行卡
            bankPayInfoLayout.setVisibility(View.VISIBLE);
            aliWeiXinPayInfoLayout.setVisibility(View.GONE);
            bankPayBankNoCv.setRightText(orderDetailInfo.getPaymentAccount());
            bankPayBankInfoCv.setRightText(orderDetailInfo.getBankName() + "" + orderDetailInfo.getBankBranch());
            bankPayBankUserNameCv.setRightText(orderDetailInfo.getPaymentName());
            bankPayBankUserPhoneNumCv.setRightText(orderDetailInfo.getPaymentPhone());
        } else {
            //支付宝/微信
            bankPayInfoLayout.setVisibility(View.GONE);
            aliWeiXinPayInfoLayout.setVisibility(View.VISIBLE);
            aliWeiXinPayAccountCv.setLeftText(orderDetailInfo.getPaymentType() == 2 ? "支付宝账号" : "微信账号");
            aliWeiXinPayAccountCv.setRightText(orderDetailInfo.getPaymentAccount());
            GlideApp.with(mContext).load(orderDetailInfo.getPaymentImage()).placeholder(R.mipmap.ic_launcher).into(aliWeiXinPayQrcodeImg);
        }
        //1：出售 2：购买 3：撤销
        switch (orderDetailInfo.getDealType()) {
            case 1:
                //出售不显示经销商支付信息 显示我的信息
                orderDetailMyInfoSl.setVisibility(View.VISIBLE);
                orderDetailMyInfoContentFl.addView(dealerPayLayout);
                break;
            case 2:
                //出售需要更具支付方式显示经销商支付信息
                //不显示我的信息
                orderDetailMyInfoSl.setVisibility(View.GONE);
                //显示经销商支付信息
                orderDetailUserInfoContentFl.addView(dealerPayLayout);
                //显示支付二维码
                aliWeiXinPayQrcodeImg.setVisibility(View.VISIBLE);
                break;
            case 3:
                //撤销暂时不做处理
                break;
        }
        aliWeiXinPayQrcodeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQRCodeDialog();
            }
        });
    }

    @Override
    protected void initData() {
        //获取订单号 发起请求获取订单详情
        String orderNo = getIntent().getStringExtra(OutSideExchangeRecodeActivity.OtcOrderNo_KEY);
        OutSideDetailReq req = new OutSideDetailReq();
        req.setOtcOrderNo(orderNo);
        presenter.getOutSideOrderDetaid(req, REQUEST_TAG_GET_ORDER_DETAIL, true);
    }

    /**
     * 显示二维码Dialog
     */
    private void showQRCodeDialog() {
        if (qrCodeDialog != null && qrCodeDialog.isShowing()) {
            return;
        }
        qrCodeDialog = new com.qmkj.jydp.ui.widget.CommonDialog(mContext, R.style.common_dialog, R.layout.dialog_qr_code);
        ImageView qr_code_iv = qrCodeDialog.getView(R.id.qr_code_iv, ImageView.class);
        if (orderDetailInfo == null || TextUtils.isEmpty(orderDetailInfo.getPaymentImage())) return;
        GlideApp.with(mContext).asBitmap().signature(new MediaStoreSignature("image/jpeg", System.currentTimeMillis()
                , 0)).load(orderDetailInfo.getPaymentImage()).into(new SimpleTarget<Bitmap>() {
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

    /**
     * 确认收款
     */
    @OnClick(R.id.confirm_receipt_btn)
    public void onViewClicked() {
        if (commonDialog != null && commonDialog.isShowing()) {
            return;
        }
        commonDialog = new CommonDialog(this);
        commonDialog.setTitleText("确认收款");
        commonDialog.setContentText("确认已收到货款？");
        commonDialog.setOnPositiveButtonClickListener((dialog, view) -> {
            OutSideDetailReq req = new OutSideDetailReq();
            req.setOtcOrderNo(orderDetailInfo.getOtcOrderNo());
            presenter.getOutSideOrderTakeMoney(req, REQUEST_TAG_CONFIRM_RECEIPET, true);
            commonDialog.dismiss();
        });
        commonDialog.show();
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            //获取订单详情
            case REQUEST_TAG_GET_ORDER_DETAIL:
                OtcDealRecordDetailsRes res = (OtcDealRecordDetailsRes) response;
                orderDetailInfo = res.getOtcTransactionUserDeal();
                initView();
                break;
            //确认收款
            case REQUEST_TAG_CONFIRM_RECEIPET:
                setResult(Activity.RESULT_OK);
                finish();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        switch (tag) {
            //获取订单详情
            case REQUEST_TAG_GET_ORDER_DETAIL:
                finish();
                break;
            //确认收款
            case REQUEST_TAG_CONFIRM_RECEIPET:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (qrCodeDialog != null && qrCodeDialog.isShowing()) {
            qrCodeDialog.dismiss();
        }
    }
}
