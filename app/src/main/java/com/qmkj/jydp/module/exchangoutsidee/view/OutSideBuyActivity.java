package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.OutSideBuyPayReq;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:场外交易购买界面
 */

public class OutSideBuyActivity extends BaseMvpActivity<OutsideExchangePresenter> {
    private static final int BUY_PAY_TAG = 1;
    private static final int DECIMAL_DIGITS = 2;//最多输入两位数值
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.outside_buy_pay_mothed_iv)
    ImageView outsidePayMothedIv;
    @BindView(R.id.outside_buy_pay_mothed_tv)
    TextView outsidePayMothedTv;
    @BindView(R.id.ouside_buy_amount_eiv)
    EditVItemView ousideBuyAmountEiv;
    @BindView(R.id.outside_exchange_buy_ratio_tv)
    NoPaddingTextView outsideExchangeBuyRatioTv;
    @BindView(R.id.total_price_tv)
    TextView totalPriceTv;
    @BindView(R.id.outside_buy_bt)
    Button outsideBuyBt;
    private CommonDialog commonDialog;
    private double selectIndex = 0;//1：银行卡，2：支付宝，3：微信
    private String orderNo;
    private String pendingRatio;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        orderNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        pendingRatio = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        outsideExchangeBuyRatioTv.setText(CommonUtil.getString(R.string.proportion) + ":" + pendingRatio);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.buy_1));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.outside_activity_buy;
    }

    @Override
    protected void initView() {
        outsidePayMothedIv.setOnClickListener(this);
        outsidePayMothedTv.setOnClickListener(this);
        outsideBuyBt.setOnClickListener(this);

        ousideBuyAmountEiv.getEditTextView().setTransformationMethod(PasswordTransformationMethod.getInstance());
        ousideBuyAmountEiv.getEditTextView().setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        ousideBuyAmountEiv.getEditTextView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        InitialValueObservable<CharSequence> amountTextChanges = RxTextView.textChanges(ousideBuyAmountEiv
                .getEditTextView());

        amountTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(new Consumer<CharSequence>() {
            @Override
            public void accept(CharSequence sequence) throws Exception {
                restrictedInput(sequence);
                String s = ousideBuyAmountEiv.getEditTextView().getText().toString();
                double buAmount = 0;
                double ratio = 0;
                try {
                    buAmount = Double.parseDouble(s);
                    ratio = Double.parseDouble(pendingRatio);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                totalPriceTv.setText(buAmount * ratio + "");
            }
        });
    }

    /**
     * 选择支付方式
     */
    private void showPaymentMethodDialog() {
        List<DialogItemBean> certifyTypeData = new ArrayList<>();
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                .mipmap.bank_card, true));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R
                .mipmap.alipay, false));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                .mipmap.wechat_pay, false));

        commonDialog = new CommonDialog(mContext, R.style.common_dialog_animation, R.layout
                .certify_type_select_dialog);
        commonDialog.setAlertDialogHight(0);
        commonDialog.setAlertDialogGravity(Gravity.BOTTOM);
        TextView list_item_title_tv = commonDialog.getView(R.id.list_item_title_tv, TextView.class);
        list_item_title_tv.setText(CommonUtil.getString(R.string.select_pay_type));

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseRecycleAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                if (selectIndex == -1) {
                    selectIndex = 0;//默认选择银行卡
                }
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        });
        commonDialog.show();
        commonDialog.setAnimation(R.style.common_dialog_animation);
        ((BaseRecycleAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            commonDialog.dismiss();
            selectIndex = position;
            outsidePayMothedTv.setText(certifyTypeData.get(position).getCertifyName());
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.outside_buy_pay_mothed_iv:
            case R.id.outside_buy_pay_mothed_tv:
                showPaymentMethodDialog();
                break;
            case R.id.outside_buy_bt:
                goPay();
                break;
        }
    }

    private void goPay() {
        String amount = ousideBuyAmountEiv.getEditTextString();
        OutSideBuyPayReq outSideBuyPayReq = new OutSideBuyPayReq();
        outSideBuyPayReq.setBuyNum(amount);
        outSideBuyPayReq.setOtcPendingOrderNo(orderNo);
        outSideBuyPayReq.setPaymentType((selectIndex + 1) + "");
        presenter.payOutsideExchange(outSideBuyPayReq, BUY_PAY_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_PAY_TAG://支付接口调用成功
                break;
        }
    }

    private void restrictedInput(CharSequence s) {
        if (TextUtils.isEmpty(s)) return;
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > DECIMAL_DIGITS) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + DECIMAL_DIGITS + 1);
                ousideBuyAmountEiv.getEditTextView().setText(s);
                ousideBuyAmountEiv.getEditTextView().setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            ousideBuyAmountEiv.getEditTextView().setText(s);
            ousideBuyAmountEiv.getEditTextView().setSelection(2);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                ousideBuyAmountEiv.getEditTextView().setText(s.subSequence(0, 1));
                ousideBuyAmountEiv.getEditTextView().setSelection(1);
                return;
            }
        }
    }
}
