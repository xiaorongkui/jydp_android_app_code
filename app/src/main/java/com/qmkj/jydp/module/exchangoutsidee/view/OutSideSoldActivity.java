package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.ui.widget.CommonDialog;
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

public class OutSideSoldActivity extends BaseMvpActivity {
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
    private CommonDialog commonDialog;
    private View outside_view_sold_bank;
    private View outside_view_sold_alipay;
    private View outside_view_sold_wechat;
    private boolean[] payMethodShow = new boolean[]{true, false, false};

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

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
        refreshPaymentMethodView();
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
