package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.EditItemView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/10
 * email：dovexiaoen@163.com
 * description:发起广告
 */

public class PublishAdvertisementActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.publish_advertise_currency_civ)
    ClickItemView publishAdvertiseCurrencyCiv;
    @BindView(R.id.publish_advertise_type_civ)
    ClickItemView publishAdvertiseTypeCiv;
    @BindView(R.id.publish_advertise_area_civ)
    ClickItemView publishAdvertiseAreaCiv;
    @BindView(R.id.publish_advertise_proportion_eiv)
    EditItemView publishAdvertiseProportionEiv;
    @BindView(R.id.publish_payment_type_select_rv)
    RecyclerView publishPaymentTypeSelectRv;
    @BindView(R.id.dealer_publish_advertise_bt)
    Button dealerPublishAdvertiseBt;
    private double selectIndex;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.publish_adversite));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_publish_advertisement;
    }

    @Override
    protected void initView() {
        initRecycleView();
        dealerPublishAdvertiseBt.setOnClickListener(this);
    }

    private void initRecycleView() {
        List<DialogItemBean> paymentSelectDatas = new ArrayList<>();
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                .mipmap.bank_card, true));
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R
                .mipmap.alipay, false));
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                .mipmap.wechat_pay, false));
        publishPaymentTypeSelectRv.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecylerAdapter recylerAdapter = new BaseRecylerAdapter<DialogItemBean>(R.layout
                .mine_payment_type_select_item,
                paymentSelectDatas) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.payment_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.payment_type_right_iv);
                TextView payment_type_tv = helper.getView(R.id.payment_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                if (selectIndex == -1) {
                    selectIndex = 0;//默认选择身份证
                }
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                payment_type_tv.setText(item.getCertifyName());
            }
        };
        publishPaymentTypeSelectRv.setAdapter(recylerAdapter);
        recylerAdapter.setOnItemClickListener((adapter, view, position) -> {
            selectIndex = position;
            recylerAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dealer_publish_advertise_bt:
                CommonUtil.gotoActivity(mContext, ReceivablesActivity.class);
                break;
        }
    }
}
