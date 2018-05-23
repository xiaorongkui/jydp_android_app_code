package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.NoPaddingTextView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:场外交易卖出界面
 */

public class OutSideSoldActivity extends BaseMvpActivity<OutsideExchangePresenter> {
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
    private boolean[] payMethodShow = new boolean[]{true, false, false};
    private String orderNo;
    private String pendingRatio;
    private String dealerName;
    private EditVItemView ouside_sold_bank_card_num_eiv;
    private EditVItemView ouside_sold_bank_card_name_eiv;
    private EditVItemView ouside_sold_bank_branch_name_eiv;
    private EditVItemView ouside_sold_bank_reserve_name_eiv;
    private EditVItemView ouside_sold_bank_reserve_phone_eiv;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        orderNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_1);
        pendingRatio = getIntent().getStringExtra(Constants.INTENT_PARAMETER_2);
        dealerName = getIntent().getStringExtra(Constants.INTENT_PARAMETER_3);
        outsideExchangeSoldRatioTv.setText(CommonUtil.getString(R.string.proportion) + ":  1:" + pendingRatio);
        soldDistributorCiv.setRightText(dealerName);
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

        refreshPaymentMethodView();
        restrictInput();
    }

    private void restrictInput() {
        ousideSoldAmountEiv.getEditTextView().setTransformationMethod(PasswordTransformationMethod.getInstance());
        ousideSoldAmountEiv.getEditTextView().setInputType(InputType.TYPE_CLASS_NUMBER | InputType
                .TYPE_NUMBER_FLAG_DECIMAL);
        ousideSoldAmountEiv.getEditTextView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        ouside_sold_bank_card_num_eiv.getEditTextView().setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        ouside_sold_bank_card_num_eiv.getEditTextView().setInputType(InputType.TYPE_CLASS_NUMBER | InputType
                .TYPE_NUMBER_FLAG_DECIMAL);
        ouside_sold_bank_card_num_eiv.getEditTextView().setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
    }

    private void refreshPaymentMethodView() {
        outside_view_sold_bank.setVisibility(payMethodShow[0] ? View.VISIBLE : View.GONE);
        outside_view_sold_alipay.setVisibility(payMethodShow[1] ? View.VISIBLE : View.GONE);
        outside_view_sold_wechat.setVisibility(payMethodShow[2] ? View.VISIBLE : View.GONE);
    }

    /**
     * 选择支付方式，默认选择银行卡
     */
    private void showPaymentMethodDialog() {
        List<DialogItemBean> certifyTypeData = new ArrayList<>();
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                .mipmap.bank_card, true));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R
                .mipmap.alipay, false));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                .mipmap.wechat_pay, false));

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
                imageViewRight.setImageResource(payMethodShow[position] ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
            }
        });
        ((BaseRecycleAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            payMethodShow[position] = !payMethodShow[position];
            outsidePayMothedTv.setText(certifyTypeData.get(position).getCertifyName());
            recyclerView.getAdapter().notifyDataSetChanged();
            refreshPaymentMethodView();
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
                CommonUtil.gotoActivity(mContext, OutSideSoldDetailActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (commonDialog != null && commonDialog.isShowing()) commonDialog.dismiss();
    }
}
