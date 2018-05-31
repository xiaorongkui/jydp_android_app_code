package com.qmkj.jydp.module.mine.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.request.OutSideDetailReq;
import com.qmkj.jydp.bean.response.OtcDealRecordDetailsRes;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.DateUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:场外交易订单详情记录
 */

public class OutSideExchangeOrderDetailActivity extends BaseMvpActivity<MinePresenter> {
    private static final int GET_MSG = 1;
    private static final int SEND_REQUEST = 2;

    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.order_detail_status_tv) //完成状态
            TextView order_detail_status_tv;
    @BindView(R.id.outside_exchange_recode_order_num_tv) //订单号
            TextView outside_exchange_recode_order_num_tv;
    @BindView(R.id.exchange_recode_detail_name_civ)//名称
            TextView exchange_recode_detail_name_civ;
    @BindView(R.id.exchange_recode_detail_amount_civ)//数量
            ClickItemView exchange_recode_detail_amount_civ;
    @BindView(R.id.exchange_recode_detail_money_civ)//金额
            ClickItemView exchange_recode_detail_money_civ;
    @BindView(R.id.exchange_recode_detail_type_civ)//类型
            ClickItemView exchange_recode_detail_type_civ;
    @BindView(R.id.exchange_recode_detail_area_civ)//地区
            ClickItemView exchange_recode_detail_area_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_name_civ)//经销商名称
            ClickItemView exchange_recode_detail_dealer_alipay_name_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_phone_civ)//手机号码
            ClickItemView exchange_recode_detail_dealer_alipay_phone_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_account_civ)//支付宝账号
            ClickItemView exchange_recode_detail_dealer_alipay_account_civ;
    @BindView(R.id.exchange_recode_detail_dealer_weixin_account_civ)//微信账号
            ClickItemView exchange_recode_detail_dealer_weixin_account_civ;
    @BindView(R.id.exchange_recode_detail_dealer_bank_civ)//银行
            ClickItemView exchange_recode_detail_dealer_bank_civ;
    @BindView(R.id.exchange_recode_detail_dealer_bank_branch_civ)//支行名称
            ClickItemView exchange_recode_detail_dealer_bank_branch_civ;
    @BindView(R.id.exchange_recode_detail_dealer_alipay_img)
    ImageView exchange_recode_detail_dealer_alipay_img;
    @BindView(R.id.exchange_recode_detail_apply_time_civ)  //申请时间
            ClickItemView exchange_recode_detail_apply_time_civ;
    @BindView(R.id.exchange_recode_detail_finish_time_civ)  //完成时间
            ClickItemView exchange_recode_detail_finish_time_civ;
    @BindView(R.id.register_bt)
    Button register_bt;
    @BindView(R.id.exchange_recode_detail_dealer_bank_layout)
    LinearLayout exchange_recode_detail_dealer_bank_layout;
    private OtcDealRecordDetailsRes.OtcTransactionUserDealBean data;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        String number =getIntent().getStringExtra("NUMBER");
        int type = getIntent().getIntExtra(MineRecodeActivity.RECODE_TYPE,1);
        OutSideDetailReq req = new OutSideDetailReq();
        req.setOtcOrderNo(number);
        //1：普通用户 2：经销商
        switch (type){
            case MineRecodeActivity.RECODE_TYPE_NORMAL:
                presenter.getUserSideOrderDetaid(req,1,true);
                break;
            case MineRecodeActivity.RECODE_TYPE_AGENCY:
                presenter.getOutSideOrderDetaid(req,1,true);
                break;
        }




    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.order_detail));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_outside_exchange_order_detail;
    }

    @Override
    protected void initView() {
        int type =getIntent().getIntExtra("TYPE",0);
        if(type==0){
            register_bt.setVisibility(View.GONE);
        }else {
            register_bt.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag){
            case GET_MSG:
                OtcDealRecordDetailsRes res = (OtcDealRecordDetailsRes)response;
                data = res.getOtcTransactionUserDeal();
                if(data!=null){
                    setMessageView(res.getOtcTransactionUserDeal());
                }
                break;
            case SEND_REQUEST:
                finish();
                setResult(200);
        }

    }

    private void setMessageView(OtcDealRecordDetailsRes.OtcTransactionUserDealBean res ) {
        String text = null;
        switch (res.getDealType()){
            case 1:
                text ="待付款";
                break;
            case 2:
                text ="已付款（待确认）";
                break;
            case 3:
                text ="已完成";
                break;
            case 4:
                text ="用户取消";
                break;
            case 5:
                text ="商家取消";
                break;
        }
        order_detail_status_tv.setText(text);
        outside_exchange_recode_order_num_tv.setText(res.getOtcOrderNo());
        exchange_recode_detail_name_civ.setText(res.getCurrencyName());
        exchange_recode_detail_amount_civ.setRightText(res.getCurrencyNumber());
        exchange_recode_detail_money_civ.setRightText("¥"+res.getCurrencyTotalPrice());
        String text_type = null;
        switch (res.getDealType()){
            case 1:
                text_type = "买入";
                exchange_recode_detail_type_civ.setRightTextColor
                        (mContext.getResources().getColor(R.color.color_red_3));
                break;
            case 2:
                text_type = "卖出";
                exchange_recode_detail_type_civ.setRightTextColor
                        (mContext.getResources().getColor(R.color.color_green_3));
                break;
            case 3:
                text_type = "撤销";
                break;
        }

        exchange_recode_detail_type_civ.setRightText(text_type);
        exchange_recode_detail_area_civ.setRightText(res.getArea());
        exchange_recode_detail_dealer_alipay_name_civ.setRightText(res.getDealerName());
        exchange_recode_detail_dealer_alipay_phone_civ.setRightText(res.getPaymentPhone());
        exchange_recode_detail_apply_time_civ.setRightText(DateUtil.longToTimeStr(res.getAddTime(),DateUtil.dateFormat1));
        exchange_recode_detail_finish_time_civ.setRightText(DateUtil.longToTimeStr(res.getUpdateTime(),DateUtil.dateFormat1));

        //收款方式标识：1：银行卡，2：支付宝，3：微信
        switch (res.getPaymentType()){
            case 1:
                exchange_recode_detail_dealer_bank_layout.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_bank_civ.setRightText(res.getBankName());
                exchange_recode_detail_dealer_bank_branch_civ.setRightText(res.getBankBranch());
                break;
            case 2:
                exchange_recode_detail_dealer_alipay_account_civ.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_img.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_account_civ.setRightText(res.getPaymentAccount());
                GlideApp.with(mContext).load(res.getPaymentImage()).placeholder(R.mipmap.ic_launcher).into
                        (exchange_recode_detail_dealer_alipay_img);
                break;
            case 3:
                exchange_recode_detail_dealer_weixin_account_civ.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_img.setVisibility(View.VISIBLE);
                exchange_recode_detail_dealer_alipay_account_civ.setRightText(res.getPaymentAccount());
                GlideApp.with(mContext).load(res.getPaymentImage()).placeholder(R.mipmap.ic_launcher).into
                        (exchange_recode_detail_dealer_alipay_img);
                break;
        }

        if(res.getDealType()==1){  //买入
            register_bt.setText(CommonUtil.getString(R.string.comfirm_receivables_corn));
        }else if(res.getDealType()==2){
            register_bt.setText(CommonUtil.getString(R.string.comfirm_receivables));
        }
        register_bt.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.register_bt:
                if(data == null){
                    return;
                }
                OutSideDetailReq req =new OutSideDetailReq();
                req.setOtcOrderNo(data.getOtcOrderNo());
                if(CommonUtil.getLoginInfo()!=null&&CommonUtil.getLoginInfo().getUser().getIsDealer()==2){   //=2 为经销商
                    if(data.getDealType()==1){  //收货
                        presenter.getOutSideOrderTakeCoin(req,SEND_REQUEST,true);
                    }else if(data.getDealType()==2){//收钱
                        presenter.getOutSideOrderTakeMoney(req,SEND_REQUEST,true);
                    }
                }else {
                    presenter.getOutSideOrderTakeUser(req,SEND_REQUEST,true);
                }

                break;
        }
    }
}
