package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:我的记录
 */

public class MineRecodeActivity extends BaseMvpActivity {
    public static final String RECODE_TYPE = "type";
    public static final int RECODE_TYPE_NORMAL = 1;
    public static final int RECODE_TYPE_AGENCY = 2;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_recode_register_recode_civ) //委托记录
    ClickItemView mineRecodeRegisterRecodeCiv;
    @BindView(R.id.mine_recode_transaction_recode_civ) //成交记录
    ClickItemView mineRecodeTransactionRecodeCiv;
   @BindView(R.id.mine_recode_outside_exchange_recode_civ) //场外交易记录
    ClickItemView mineRecodeOutsideExchangeRecodeCiv;
   @BindView(R.id.mine_recharge_currency_recode_civ)  //充链记录
    ClickItemView mine_recharge_currency_recode_civ;
    @BindView(R.id.mine_recode_outside_exchange_recode_agcy_civ) //场外交易记录(经销商)
    ClickItemView mine_recode_outside_exchange_recode_agcy_civ;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.mine_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_mine_recode;
    }

    @Override
    protected void initView() {
        if(CommonUtil.getLoginInfo()!=null&&CommonUtil.getLoginInfo().getUser().getIsDealer()==2){
            mine_recode_outside_exchange_recode_agcy_civ.setVisibility(View.VISIBLE);
        }
    }


    @OnClick({R.id.mine_recode_register_recode_civ, R.id.mine_recode_transaction_recode_civ, R.id
            .mine_recode_currency_recode_civ, R.id.mine_recode_outside_exchange_recode_civ,
            R.id.mine_recode_outside_exchange_recode_agcy_civ,R.id.mine_recharge_currency_recode_civ})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_recode_register_recode_civ://委托记录
                CommonUtil.gotoActivity(mContext, OrderRecodeActivity.class);
                break;
            case R.id.mine_recode_transaction_recode_civ://成交记录
                CommonUtil.gotoActivity(mContext, TransactionRecodeActivity.class);
                break;
            case R.id.mine_recharge_currency_recode_civ://充币记录
                CommonUtil.gotoActivity(mContext, CurrencyWithDrawRecodeActivity.class);
                break;
            case R.id.mine_recode_currency_recode_civ://提币记录
                CommonUtil.gotoActivity(mContext, CurrencyWithDrawRecodeActivity.class);
                break;
            case R.id.mine_recode_outside_exchange_recode_civ://场外交易记录
                Intent intent = new Intent(this,OutSideExchangeRecodeActivity.class);
                intent.putExtra(RECODE_TYPE,RECODE_TYPE_NORMAL);
                CommonUtil.gotoActivity(mContext, intent);
                break;
            case R.id.mine_recode_outside_exchange_recode_agcy_civ://场外交易记录(经销商)
                Intent intent2 = new Intent(this,OutSideExchangeRecodeActivity.class);
                intent2.putExtra(RECODE_TYPE,RECODE_TYPE_AGENCY);
                CommonUtil.gotoActivity(mContext, intent2);
                break;
        }
    }
}
