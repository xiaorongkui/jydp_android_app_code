package com.qmkj.jydp.module.exchangoutside.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.OutSideBuyPayDetailReq;
import com.qmkj.jydp.bean.response.DistributorPayMethodRes;
import com.qmkj.jydp.bean.response.OutSideBuyPayDetailRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.exchangoutside.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;

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
    private static final int BUY_DETAIL_INFO_TAG = 1;
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
    private int selectIndex = -1;//1：银行卡，2：支付宝，3：微信
    private String orderNo;
    private String pendingRatio;
    private boolean[] payMethodShow = new boolean[]{false, false, false};
    private String userId;
    private String minAccount;
    private String maxAccount;
    private String paymentMoney;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        orderNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        pendingRatio = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        DistributorPayMethodRes payMethodRes = getIntent().getParcelableExtra(Constants.INTENT_PARAMETER_3);
        userId = getIntent().getStringExtra(Constants.INTENT_PARAMETER_4);
        minAccount = getIntent().getStringExtra(Constants.INTENT_PARAMETER_5);
        maxAccount = getIntent().getStringExtra(Constants.INTENT_PARAMETER_6);
        outsideExchangeBuyRatioTv.setText(ResourcesManager.getString(R.string.proportion) + ":  1:" + pendingRatio);

        if (payMethodRes != null) {
            if (payMethodRes.getHasBank() == 1) {
                certifyTypeData.add(new DialogItemBean(ResourcesManager.getString(R.string.bank_card_transfer), R
                        .mipmap.bank_card, false));
                payMethodShow[0] = true;
            }

            if (payMethodRes.getHasAliPay() == 1) {
                certifyTypeData.add(new DialogItemBean(ResourcesManager.getString(R.string.alipay_transfer), R.mipmap
                        .alipay,
                        false));
                payMethodShow[1] = true;
            }

            if (payMethodRes.getHasWeiXin() == 1) {
                certifyTypeData.add(new DialogItemBean(ResourcesManager.getString(R.string.wechat_transfer), R
                        .mipmap.wechat_pay, false));
                payMethodShow[2] = true;
            }
        }
        if (!payMethodShow[0] && !payMethodShow[1] && !payMethodShow[2]) {
            selectIndex = -1;
            outsidePayMothedTv.setText(ResourcesManager.getString(R.string.no_payment_method));
        }
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.buy_1));
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

            totalPriceTv.setText(NumberUtil.format6Point(NumberUtil.mul(buAmount, ratio)));
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
        list_item_title_tv.setText(ResourcesManager.getString(R.string.select_pay_type));

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        ImageView dialog_right_iv = commonDialog.getView(R.id.dialog_right_iv, ImageView.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseRecycleAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
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
        paymentMoney = totalPriceTv.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            toast("请输入购买数量");
            return;
        }
        double min = 0;
        double max = 0;
        double amu = 0;
        try {
            min = Double.parseDouble(minAccount);
            max = Double.parseDouble(maxAccount);
            amu = Double.parseDouble(amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (min > amu) {
            toast("购买数量必须大于" + min);
            return;
        }
        if (max < amu) {
            toast("购买数量不能大于" + max);
            return;
        }

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
        if (selectIndex < 0) {
            toast("请先选择收款方式");
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
        presenter.payOutsideDetailConfirm(outSideBuyPayDetailReq, BUY_DETAIL_INFO_TAG, true);

    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_DETAIL_INFO_TAG:
                OutSideBuyPayDetailRes outSideBuyPayDetailRes = (OutSideBuyPayDetailRes) response;
                if (outSideBuyPayDetailRes == null) {
                    toast("获取数据为空");
                    return;
                }
                Intent intent = new Intent(mContext, OutSideBuyDetailActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, outSideBuyPayDetailRes);
                intent.putExtra(Constants.INTENT_PARAMETER_2, paymentMoney);
                intent.putExtra(Constants.INTENT_PARAMETER_3, outSideBuyPayDetailRes.getUserPaymentType());
                ActivityManager.gotoActivity(mContext, intent);
                OutSideBuyPayDetailRes.UserPaymentTypeBean bean = outSideBuyPayDetailRes.getUserPaymentType();
                LogUtil.i("bean2=" + (bean == null ? "" : bean.toString()));
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
        if (s.toString().trim().equals(".")) {
            s = "0" + s;
            ousideBuyAmountEiv.getEditTextView().setText(s);
            ousideBuyAmountEiv.getEditTextView().setSelection(2);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                ousideBuyAmountEiv.getEditTextView().setText(s.subSequence(0, 1));
                ousideBuyAmountEiv.getEditTextView().setSelection(1);
            }
        }
    }
}
