package com.qmkj.jydp.module.exchangoutside.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.response.DistributorPayMethodRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangoutside.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:场外交易购买界面
 */

public class OutSideBuyActivity extends BaseMvpActivity<OutsideExchangePresenter> {

    private static final int DECIMAL_DIGITS = 4;//最多输入4位数值
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
    private int selectIndex = 0;//1：银行卡，2：支付宝，3：微信
    private String orderNo;
    private String pendingRatio;
    private DistributorPayMethodRes payMethodRes;
    private boolean[] payMethodShow = new boolean[]{false, false, false};
    private String userId;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        orderNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        pendingRatio = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        payMethodRes = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_3);
        userId = getIntent().getStringExtra(Constants.INTENT_PARAMETER_4);
        outsideExchangeBuyRatioTv.setText(CommonUtil.getString(R.string.proportion) + ":  1:" + pendingRatio);

        if (payMethodRes != null) {
            if (payMethodRes.getHasBank() == 1) {
                certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                        .mipmap.bank_card, false));
                payMethodShow[0] = true;
            }

            if (payMethodRes.getHasAliPay() == 1) {
                certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R.mipmap.alipay,
                        false));
                payMethodShow[1] = true;
            }

            if (payMethodRes.getHasWeiXin() == 1) {
                certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                        .mipmap.wechat_pay, false));
                payMethodShow[2] = true;
            }
        }
        if (!payMethodShow[0] && !payMethodShow[1] && !payMethodShow[2]) {
            selectIndex = -1;
            outsidePayMothedTv.setText("");
        } else {
            outsidePayMothedTv.setText(certifyTypeData.get(0).getCertifyName());
        }
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

        InitialValueObservable<CharSequence> amountTextChanges = RxTextView.textChanges(ousideBuyAmountEiv
                .getEditTextView());

        amountTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> {
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

            totalPriceTv.setText(NumberUtil.mul(buAmount, ratio, 4) + "");
        });
    }

    List<DialogItemBean> certifyTypeData = new ArrayList<>();

    /**
     * 选择支付方式
     */
    private void showPaymentMethodDialog() {
        commonDialog = new CommonDialog(mContext, R.style.common_dialog_animation, R.layout
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
                if (selectIndex == -1) {
                    selectIndex = 0;//默认选择第一个
                }
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        });

        commonDialog.setAnimation(R.style.common_dialog_animation);
        ((BaseRecycleAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            commonDialog.dismiss();
            selectIndex = position;
            outsidePayMothedTv.setText(certifyTypeData.get(position).getCertifyName());
        });
        dialog_right_iv.setOnClickListener(v -> commonDialog.dismiss());
        commonDialog.show();
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
        String paymentMoney = totalPriceTv.getText().toString().trim();
        double v = 0;
        try {
            v = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (v <= 0 || TextUtils.isEmpty(paymentMoney)) {
            toast("购买数量必须大于0");
            return;
        }
        OutSideBuyPayDetailReq outSideBuyPayDetailReq = new OutSideBuyPayDetailReq();
        switch (selectIndex) {
            case 0://银行卡
                for (int i = 0; i < payMethodShow.length; i++) {
                    if (payMethodShow[i]) {
                        outSideBuyPayDetailReq.setPaymentType((i + 1) + "");
                        break;
                    }
                }
                break;
            case 1://支付宝
                if (payMethodShow[0]) {
                    if (payMethodShow[1]) {
                        outSideBuyPayDetailReq.setPaymentType("2");
                    } else {
                        outSideBuyPayDetailReq.setPaymentType("3");
                    }

                } else {
                    outSideBuyPayDetailReq.setPaymentType("3");
                }
                break;
            case 2:
                outSideBuyPayDetailReq.setPaymentType(3 + "");//微信
                break;
            default:
                toast("付款方式不支持");
                return;
        }

        outSideBuyPayDetailReq.setBuyNum(v + "");
        outSideBuyPayDetailReq.setOtcPendingOrderNo(orderNo);
        outSideBuyPayDetailReq.setPageNumber("0");
        outSideBuyPayDetailReq.setUserId(userId);
        outSideBuyPayDetailReq.setPayMentMoney(paymentMoney);


        Intent intent = new Intent(mContext, OutSideBuyDetailActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_1, outSideBuyPayDetailReq);
        CommonUtil.gotoActivity(mContext, intent);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);

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
