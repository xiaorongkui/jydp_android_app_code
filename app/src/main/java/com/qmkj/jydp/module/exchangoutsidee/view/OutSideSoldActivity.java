package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
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
    private CommonDialog commonDialog;
    private int selectIndex;

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

        commonDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog.setAlertDialogHight(0);
        commonDialog.setAlertDialogGravity(Gravity.BOTTOM);
        TextView list_item_title_tv = commonDialog.getView(R.id.list_item_title_tv, TextView.class);
        list_item_title_tv.setText(CommonUtil.getString(R.string.select_pay_type));

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseRecylerAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                if (selectIndex == -1) {
                    selectIndex = 0;//默认选择身份证
                }
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        });
        commonDialog.show();
        ((BaseRecylerAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            commonDialog.dismiss();
            selectIndex = position;
            outsidePayMothedTv.setText(certifyTypeData.get(position).getCertifyName());
        });
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

}
