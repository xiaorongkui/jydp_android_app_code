package com.qmkj.jydp.module.exchangoutside.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.OutSideSellDetailReq;
import com.qmkj.jydp.bean.response.OutSideSellDetailRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangoutside.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.util.BitmapCompressTask;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxPermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:场外交易卖出界面
 */

public class OutSideSoldActivity extends BaseMvpActivity<OutsideExchangePresenter> {
    private static final int SELL_DETAIL_TAG = 1;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.outside_slod_pay_mothed_iv)
    ImageView outsidePayMothedIv;
    @BindView(R.id.outside_slod_pay_mothed_tv)
    TextView outsidePayMothedTv;
    @BindView(R.id.outside_sold_comfirm_bt)
    Button outsideSoldComfirmBt;
    @BindView(R.id.payment_method_ll)
    LinearLayout paymentMethodLl;
    @BindView(R.id.sold_distributor_civ)
    ClickItemView soldDistributorCiv;
    @BindView(R.id.ouside_sold_amount_eiv)
    EditVItemView ousideSoldAmountEiv;
    @BindView(R.id.total_price_tv)
    TextView totalPriceTv;
    @BindView(R.id.outside_exchange_sold_ratio_tv)
    NoPaddingTextView outsideExchangeSoldRatioTv;
    private CommonDialog commonDialog;
    private View outside_view_sold_bank;
    private View outside_view_sold_alipay;
    private View outside_view_sold_wechat;
    int selectIndex = 0;//默认选择银行卡
    private String orderNo;
    private String pendingRatio;
    private String dealerName;
    private EditVItemView ouside_sold_bank_card_num_eiv;
    private EditVItemView ouside_sold_bank_card_name_eiv;
    private EditVItemView ouside_sold_bank_branch_name_eiv;
    private EditVItemView ouside_sold_bank_reserve_name_eiv;
    private EditVItemView ouside_sold_bank_reserve_phone_eiv;
    private EditVItemView ouside_sold_alipay_account_eiv;
    private EditVItemView ouside_sold_alipay_qr_code_eiv;
    private EditVItemView ouside_sold_wechat_account_eiv;
    private EditVItemView ouside_sold_wechat_qr_code_eiv;
    private static final int DECIMAL_DIGITS = 4;//最多输入4位数值
    private Bitmap apilpay;
    private boolean isApilpayCompressing;
    private byte[] apilpayBytes;
    private Bitmap weixinPay;
    private boolean isWeixinCompressing;
    private byte[] weixinBytes;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    List<DialogItemBean> certifyTypeData = new ArrayList<>();

    @Override
    protected void initData() {
        orderNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        pendingRatio = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        dealerName = getIntent().getStringExtra(Constants.INTENT_PARAMETER_3);
        outsideExchangeSoldRatioTv.setText(CommonUtil.getString(R.string.proportion) + ":  1:" + pendingRatio);
        soldDistributorCiv.setRightText(dealerName);


        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                .mipmap.bank_card, true));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R
                .mipmap.alipay, false));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                .mipmap.wechat_pay, false));
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.sell));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.outside_activity_sold;
    }

    @Override
    protected void initView() {
        outsidePayMothedIv.setOnClickListener(this);
        outsidePayMothedTv.setOnClickListener(this);
        outsideSoldComfirmBt.setOnClickListener(this);

        outside_view_sold_bank = View.inflate(mContext, R.layout.outside_view_sold_bank, null);
        outside_view_sold_alipay = View.inflate(mContext, R.layout.outside_view_sold_alipay, null);
        outside_view_sold_wechat = View.inflate(mContext, R.layout.outside_view_sold_wechat, null);
        paymentMethodLl.addView(outside_view_sold_bank);
        paymentMethodLl.addView(outside_view_sold_alipay);
        paymentMethodLl.addView(outside_view_sold_wechat);
        ouside_sold_bank_card_num_eiv = outside_view_sold_bank.findViewById(R.id.ouside_sold_bank_card_num_eiv);
        ouside_sold_bank_card_name_eiv = outside_view_sold_bank.findViewById(R.id.ouside_sold_bank_card_name_eiv);
        ouside_sold_bank_branch_name_eiv = outside_view_sold_bank.findViewById(R.id.ouside_sold_bank_branch_name_eiv);
        ouside_sold_bank_reserve_name_eiv = outside_view_sold_bank.findViewById(R.id.ouside_sold_bank_reserve_name_eiv);
        ouside_sold_bank_reserve_phone_eiv = outside_view_sold_bank.findViewById(R.id
                .ouside_sold_bank_reserve_phone_eiv);

        ouside_sold_alipay_account_eiv = outside_view_sold_alipay.findViewById(R.id.ouside_sold_alipay_account_eiv);
        ouside_sold_alipay_qr_code_eiv = outside_view_sold_alipay.findViewById(R.id.ouside_sold_alipay_qr_code_eiv);

        ouside_sold_wechat_account_eiv = outside_view_sold_wechat.findViewById(R.id.ouside_sold_wechat_account_eiv);
        ouside_sold_wechat_qr_code_eiv = outside_view_sold_wechat.findViewById(R.id.ouside_sold_wechat_qr_code_eiv);
        refreshPaymentMethodView();
        restrictInput();

        ouside_sold_alipay_qr_code_eiv.getEditTextView().setFocusable(false);
        ouside_sold_alipay_qr_code_eiv.getEditTextView().setCursorVisible(false);
        ouside_sold_alipay_qr_code_eiv.getEditTextView().setFocusableInTouchMode(false);
        ouside_sold_alipay_qr_code_eiv.setClickable(true);

        ouside_sold_wechat_qr_code_eiv.getEditTextView().setFocusable(false);
        ouside_sold_wechat_qr_code_eiv.getEditTextView().setCursorVisible(false);
        ouside_sold_wechat_qr_code_eiv.getEditTextView().setFocusableInTouchMode(false);
        ouside_sold_wechat_qr_code_eiv.setClickable(true);

        ouside_sold_alipay_qr_code_eiv.setOnClickListener(this);
        ouside_sold_wechat_qr_code_eiv.setOnClickListener(this);
    }

    private void restrictInput() {
        ousideSoldAmountEiv.getEditTextView().setInputType(InputType.TYPE_CLASS_NUMBER | InputType
                .TYPE_NUMBER_FLAG_DECIMAL);
        ousideSoldAmountEiv.getEditTextView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        InitialValueObservable<CharSequence> amountTextChanges = RxTextView.textChanges(ousideSoldAmountEiv
                .getEditTextView());

        amountTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> {
            restrictedInput(sequence);
            String s = ousideSoldAmountEiv.getEditTextView().getText().toString();
            double buAmount = 0;
            double ratio = 0;
            try {
                buAmount = Double.parseDouble(s);
                ratio = Double.parseDouble(pendingRatio);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            totalPriceTv.setText(NumberUtil.format4Point(NumberUtil.mul(buAmount, ratio)));
        });

    }

    private void restrictedInput(CharSequence s) {
        if (TextUtils.isEmpty(s)) return;
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                ousideSoldAmountEiv.getEditTextView().setText(s);
                ousideSoldAmountEiv.getEditTextView().setSelection(s.length());
            }
        }
        if (s.toString().trim().equals(".")) {
            s = "0" + s;
            ousideSoldAmountEiv.getEditTextView().setText(s);
            ousideSoldAmountEiv.getEditTextView().setSelection(2);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                ousideSoldAmountEiv.getEditTextView().setText(s.subSequence(0, 1));
                ousideSoldAmountEiv.getEditTextView().setSelection(1);
                return;
            }
        }
    }

    private void refreshPaymentMethodView() {
        outside_view_sold_bank.setVisibility(selectIndex == 0 ? View.VISIBLE : View.GONE);
        outside_view_sold_alipay.setVisibility(selectIndex == 1 ? View.VISIBLE : View.GONE);
        outside_view_sold_wechat.setVisibility(selectIndex == 2 ? View.VISIBLE : View.GONE);
    }

    /**
     * 选择支付方式，默认选择银行卡
     */
    private void showPaymentMethodDialog() {

        commonDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog.setAlertDialogHight(0);
        commonDialog.setAlertDialogGravity(Gravity.BOTTOM);
        TextView list_item_title_tv = commonDialog.getView(R.id.list_item_title_tv, TextView.class);
        list_item_title_tv.setText(CommonUtil.getString(R.string.select_pay_type));

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        ImageView dialog_right_iv = commonDialog.getView(R.id.dialog_right_iv, ImageView.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseRecycleAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                certifyType_tv.setText(item.getCertifyName());
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
            }
        });
        ((BaseRecycleAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            selectIndex = position;
            outsidePayMothedTv.setText(certifyTypeData.get(position).getCertifyName());
            refreshPaymentMethodView();
            recyclerView.getAdapter().notifyDataSetChanged();
            commonDialog.dismiss();
        });
        dialog_right_iv.setOnClickListener(v -> commonDialog.dismiss());
        commonDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.outside_slod_pay_mothed_iv:
            case R.id.outside_slod_pay_mothed_tv:
                showPaymentMethodDialog();
                break;
            case R.id.outside_sold_comfirm_bt:
                OutSideSellDetailReq outSideSellDetailReq = new OutSideSellDetailReq();
                String amount = ousideSoldAmountEiv.getEditTextString().trim();

                double amountDouble = 0;
                try {
                    amountDouble = Double.parseDouble(amount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                if (amountDouble <= 0 || TextUtils.isEmpty(amount)) {
                    toast("出售数量必须大于0");
                    return;
                }

                outSideSellDetailReq.setOtcPendingOrderNo(orderNo);
                outSideSellDetailReq.setPaymentType((selectIndex + 1) + "");
                outSideSellDetailReq.setSellNumStr(amount);

                switch (selectIndex) {
                    case 0://银行卡
                        String bankNum = ouside_sold_bank_card_num_eiv.getEditTextString().trim();
                        String bankName = ouside_sold_bank_card_name_eiv.getEditTextString().trim();
                        String bankBranchName = ouside_sold_bank_branch_name_eiv.getEditTextString().trim();
                        String bankReserveName = ouside_sold_bank_reserve_name_eiv.getEditTextString().trim();
                        String bankReservePhone = ouside_sold_bank_reserve_phone_eiv.getEditTextString().trim();
                        if (TextUtils.isEmpty(bankNum)) {
                            toast("银行卡号不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(bankName)) {
                            toast("银行名称不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(bankBranchName)) {
                            toast("银行支行名称不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(bankReserveName)) {
                            toast("银行预留姓名不能为空");
                            return;
                        }
                        if (TextUtils.isEmpty(bankReservePhone)) {
                            toast("银行预留手机号不能为空");
                            return;
                        }
                        outSideSellDetailReq.setBankCardPaymentAccount(bankNum);
                        outSideSellDetailReq.setBankName(bankNum);
                        outSideSellDetailReq.setBankBranch(bankBranchName);
                        outSideSellDetailReq.setPaymentName(bankReserveName);
                        outSideSellDetailReq.setPaymentPhone(bankReservePhone);
                        break;
                    case 1://支付宝
                        String alipayAccount = ouside_sold_alipay_account_eiv.getEditTextString();
                        if (TextUtils.isEmpty(alipayAccount)) {
                            toast("请输入支付宝账号");
                            return;
                        }
                        if (isApilpayCompressing) {
                            toast("请等待压缩");
                            return;
                        }
                        if (apilpayBytes == null || apilpayBytes.length <= 0) {
                            toast("请先上传支付宝二维码图片");
                            return;
                        }
                        outSideSellDetailReq.setAlipayPaymentAccount(alipayAccount);
                        break;
                    case 2://微信
                        String weixinAccount = ouside_sold_wechat_account_eiv.getEditTextString();
                        if (TextUtils.isEmpty(weixinAccount)) {
                            toast("请输入微信账号");
                            return;
                        }
                        if (isWeixinCompressing) {
                            toast("请等待压缩");
                            return;
                        }
                        if (weixinBytes == null || weixinBytes.length <= 0) {
                            toast("请先上传微信二维码图片");
                            return;
                        }
                        outSideSellDetailReq.setWechatPaymentAccount(weixinAccount);
                        break;
                }
                presenter.sellOutsideDetailConfirm(outSideSellDetailReq, apilpayBytes, weixinBytes, SELL_DETAIL_TAG,
                        true);
                break;
            case R.id.ouside_sold_alipay_qr_code_eiv://支付宝二维码
                checkPermission(0);
                break;
            case R.id.ouside_sold_wechat_qr_code_eiv://微信二维码
                checkPermission(1);
                break;
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case SELL_DETAIL_TAG:
                OutSideSellDetailRes outSideSellDetailRes = (OutSideSellDetailRes) response;
                if (outSideSellDetailRes == null) return;
                String money = totalPriceTv.getText().toString().trim();
                outSideSellDetailRes.setSellMoney(money);
                LogUtil.i("卖出数据==" + outSideSellDetailRes.toString());
                Intent intent = new Intent(mContext, OutSideSoldDetailActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, outSideSellDetailRes);
                CommonUtil.gotoActivity(mContext, intent);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (commonDialog != null && commonDialog.isShowing()) commonDialog.dismiss();
        RxPermissionUtils.destory();
    }

    private void checkPermission(int index) {
        RxPermissionUtils.getInstance(mContext).setPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest
                .permission.WRITE_EXTERNAL_STORAGE)
                .setOnPermissionCallBack(new RxPermissionUtils
                        .OnPermissionListener() {

                    @Override
                    public void onPermissionGranted(String name) {
                        LogUtil.i("onPermissionGranted name=" + name);
                    }

                    @Override
                    protected void onAllPermissionGranted() {
                        switch (index) {
                            case 0:
                                Intent pickFront = new Intent(Intent.ACTION_PICK);
                                pickFront.setType("image/*");//相片类型
                                startActivityForResult(pickFront, 1);
                                break;
                            case 1:
                                Intent pickBack = new Intent(Intent.ACTION_PICK);
                                pickBack.setType("image/*");//相片类型
                                startActivityForResult(pickBack, 2);
                                break;
                        }
                    }
                }).start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//图片压缩工作放在提交任务里面
        LogUtil.i("requestCode" + requestCode + "  ;resultCode=" + resultCode);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            String uriPath = uri != null ? uri.getPath() : null;
            LogUtil.i("uriPath1=" + uriPath);
            try {
                if (apilpay != null && !apilpay.isRecycled()) {
                    apilpay.recycle();
                    apilpay = null;
                }
                apilpay = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (apilpay == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                isApilpayCompressing = true;

                ouside_sold_alipay_qr_code_eiv.setContentEditHintText("支付宝图片已选择");
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (mContext != null) {
                        apilpayBytes = bytes;
                        isApilpayCompressing = false;
                    }
                });
                bitmapCompressTask.execute(apilpay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            LogUtil.i("uriPath2=" + (uri != null ? uri.getPath() : null));
            try {
                if (weixinPay != null && !weixinPay.isRecycled()) {
                    weixinPay.recycle();
                    weixinPay = null;
                }
                weixinPay = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (weixinPay == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                isWeixinCompressing = true;
                ouside_sold_wechat_qr_code_eiv.setContentEditHintText("微信图片已选择");
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (mContext != null) {
                        weixinBytes = bytes;
                        isWeixinCompressing = false;
                    }
                });
                bitmapCompressTask.execute(weixinPay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
