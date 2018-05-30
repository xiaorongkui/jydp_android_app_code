package com.qmkj.jydp.module.mine.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.SendAdsReq;
import com.qmkj.jydp.bean.response.UserCoinWithdrawInfo;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.MyTextWatcher;
import com.qmkj.jydp.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/10
 * email：dovexiaoen@163.com
 * description:发起广告
 */

public class PublishAdvertisementActivity extends BaseMvpActivity<MinePresenter> {
    private static final int GET_CORN_CODE = 1;
    private static final int SEND_REQUEST_SHELL = 2;
    private static final int SEND_REQUEST_BUY = 3;
    private static final int NEXT_ACTIVITY = 11;
    public  static final String MESSAGE_NEXT = "message";
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.publish_advertise_currency_civ)
    ClickItemView publishAdvertiseCurrencyCiv;
    @BindView(R.id.publish_advertise_type_civ)
    ClickItemView publishAdvertiseTypeCiv;
    @BindView(R.id.publish_advertise_area_civ)
    ClickItemView publishAdvertiseAreaCiv;
    @BindView(R.id.publish_advertise_proportion_eiv)
    EditVItemView publishAdvertiseProportionEiv;
    @BindView(R.id.exchange_limit_min_et)
    EditText exchange_limit_min_et;
    @BindView(R.id.exchange_limit_max_et)
    EditText exchange_limit_max_et;
    @BindView(R.id.publish_payment_type_select_rv)
    RecyclerView publishPaymentTypeSelectRv;
    @BindView(R.id.dealer_publish_advertise_bt)
    Button dealerPublishAdvertiseBt;
    @BindView(R.id.publish_payment_type_layout)
    RelativeLayout publish_payment_type_layout;
    private CommonDialog commonDialog;
    private CommonDialog commonDialog_type;
    private CommonDialog commonDialog_country;

    private List<UserCoinWithdrawInfo.CoinWithdrawInfo> data_corn;
    private List<DialogItemBean> data_type;
    private List<DialogItemBean> data_country;
    private SendAdsReq req;
    private ArrayList<DialogItemBean> paymentSelectDatas;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        presenter.getUserCoinWithdrawalInfo(GET_CORN_CODE,false);
        req = new SendAdsReq();


        data_type = new ArrayList<>();
        data_type.add(new DialogItemBean("出售",1,true));
        data_type.add(new DialogItemBean("回购",2,false));
        req.setOrderType(data_type.get(0).getLeftImageViewId()+"");
        data_country = new ArrayList<>();
        data_country.add(new DialogItemBean("中国(CN)",0,true));
        data_country.add(new DialogItemBean("美国(US)",0,false));
        req.setAra(data_country.get(0).getCertifyName());
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
        publishAdvertiseProportionEiv.setEditTextTextWatch(new MyTextWatcher(publishAdvertiseProportionEiv.getEditTextView(), 2));
        exchange_limit_min_et.addTextChangedListener(new MyTextWatcher(exchange_limit_min_et,2));
        exchange_limit_max_et.addTextChangedListener(new MyTextWatcher(exchange_limit_max_et,2));


        initRecycleView();
        dealerPublishAdvertiseBt.setOnClickListener(this);

        publishAdvertiseCurrencyCiv.setOnClickListener(this);
        publishAdvertiseTypeCiv.setOnClickListener(this);
        publishAdvertiseAreaCiv.setOnClickListener(this);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag){
            case GET_CORN_CODE:
                UserCoinWithdrawInfo res =(UserCoinWithdrawInfo)response;
                if(res.getUserCoinConfigList()!=null){
                    data_corn = res.getUserCoinConfigList();
                    if(data_corn.size()>0){
                        req.setCurrencyId(data_corn.get(0).getCurrencyId()+"");
                        publishAdvertiseCurrencyCiv.setRightText(data_corn.get(0).getCurrencyName());
                    }
                }
                break;
            case SEND_REQUEST_SHELL:  //出售广告
                Intent intent = new Intent(this, ReceivablesActivity.class);
                intent.putExtra(MESSAGE_NEXT,req);
                CommonUtil.startActivityForResult(mContext,intent,NEXT_ACTIVITY);
                break;
            case SEND_REQUEST_BUY: //回购广告
                toast("发布成功");
                setResult(200);
                finish();
                break;
        }
    }

    private void initRecycleView() {
        paymentSelectDatas = new ArrayList<>();
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.bank_card_transfer), R
                .mipmap.bank_card, false));
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.alipay_transfer), R
                .mipmap.alipay, false));
        paymentSelectDatas.add(new DialogItemBean(CommonUtil.getString(R.string.wechat_transfer), R
                .mipmap.wechat_pay, false));
        publishPaymentTypeSelectRv.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecycleAdapter recycleAdapter = new BaseRecycleAdapter<DialogItemBean>(R.layout
                .mine_payment_type_select_item,
                paymentSelectDatas) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.payment_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.payment_type_right_iv);
                TextView payment_type_tv = helper.getView(R.id.payment_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                imageViewRight.setImageResource(item.isSelect() ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                payment_type_tv.setText(item.getCertifyName());
            }
        };
        publishPaymentTypeSelectRv.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(paymentSelectDatas.get(position).isSelect()){
                paymentSelectDatas.get(position).setSelect(false);
            }else {
                paymentSelectDatas.get(position).setSelect(true);
            }

            recycleAdapter.notifyDataSetChanged();
            req.setSelectList(paymentSelectDatas.get(position).getCertifyName());
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dealer_publish_advertise_bt:
                sendRequest();
                break;
            case R.id.publish_advertise_currency_civ: //选择币种
                if(data_corn!=null&&data_corn.size()>0){
                    setCornDialog();
                }
                break;
            case R.id.publish_advertise_type_civ:   //选择类型
                setTypeDialog();
                break;
            case R.id.publish_advertise_area_civ:   //选择地区
                setCountryDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEXT_ACTIVITY&&resultCode== 200){
            setResult(200);
            finish();
        }
    }

    private void sendRequest() {
//        String corn = publishAdvertiseCurrencyCiv.getRightText();
//        String type = publishAdvertiseTypeCiv.getRightText();
//        String area = publishAdvertiseAreaCiv.getRightText();
        String proportion = publishAdvertiseProportionEiv.getEditTextString();
        String min_et = exchange_limit_min_et.getText().toString();
        String max_et = exchange_limit_max_et.getText().toString();
//        publishPaymentTypeSelectRv.get

//        req.setAra(area);
        req.setMinNumber(min_et);
        req.setMaxNumber(max_et);
        req.setPendingRatio(proportion);
        StringBuffer bg = new StringBuffer();
        for (int i=0;i<paymentSelectDatas.size();i++){
            if(paymentSelectDatas.get(i).isSelect()){
                bg.append(i+1+"");
            }
        }
        req.setSelectList(bg.toString());
        if(StringUtil.isNull(proportion)){
            toast("交易比例不能为空");
            return;
        }
        if(StringUtil.isNull(min_et)){
            toast("最低交易限额不能为空");
            return;
        }

        if(Double.parseDouble(min_et)>1000000){
            toast("最低限额不能超过一百万");
            return;
        }

        if(StringUtil.isNull(max_et)){
            toast("最高交易限额不能为空");
            return;
        }

        if(Double.parseDouble(max_et)>1000000){
            toast("最高限额不能超过一百万");
            return;
        }

        if(req.getOrderType().equals("2")){ //回购

            presenter.sendInitiateAdsInfo(req, SEND_REQUEST_BUY,true);
        }else if (req.getOrderType().equals("1")){ //出售
            if(bg.length()<1){
                toast("请至少选择一个收款方式");
                return;
            }
            presenter.sendInitiateAdsInfo(req, SEND_REQUEST_SHELL,true);
        }
    }

    private void setCornDialog(){
        commonDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog.setAlertDialogHight(0);
        commonDialog.setAlertDialogGravity(Gravity.BOTTOM);

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        TextView tittle = commonDialog.getView(R.id.list_item_title_tv,TextView.class);
        ImageView dialog_right_iv = commonDialog.getView(R.id.dialog_right_iv,ImageView.class);
        dialog_right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonDialog.dismiss();
            }
        });
        tittle.setText("选择币种");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecycleAdapter adapter=new BaseRecycleAdapter<UserCoinWithdrawInfo.CoinWithdrawInfo>(R.layout.bottom_select_item,
                data_corn) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, UserCoinWithdrawInfo.CoinWithdrawInfo item, int position) {
//                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
//                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
//                        .bt_unselected);
                certifyType_tv.setText(item.getCurrencyName());

            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                req.setCurrencyId(data_corn.get(position).getCurrencyId()+"");
                publishAdvertiseCurrencyCiv.setRightText(data_corn.get(position).getCurrencyName());
                commonDialog.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        commonDialog.show();
    }


    private void setTypeDialog(){
        commonDialog_type = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog_type.setAlertDialogHight(0);
        commonDialog_type.setAlertDialogGravity(Gravity.BOTTOM);

        RecyclerView recyclerView = commonDialog_type.getView(R.id.certify_type_select_rv, RecyclerView.class);
        TextView tittle = commonDialog_type.getView(R.id.list_item_title_tv,TextView.class);
        ImageView dialog_right_iv = commonDialog_type.getView(R.id.dialog_right_iv,ImageView.class);
        dialog_right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonDialog_type.dismiss();
            }
        });
        tittle.setText("选择类型");
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecycleAdapter<DialogItemBean> adapter = new BaseRecycleAdapter<DialogItemBean>(R.layout.bottom_select_item, data_type
        ) {
            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
//                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
//                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
//                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                req.setOrderType(data_type.get(position).getLeftImageViewId()+"");
                publishAdvertiseTypeCiv.setRightText(data_type.get(position).getCertifyName());
                if(position == 0){
                    publish_payment_type_layout.setVisibility(View.VISIBLE);
                }else if(position == 1){
                    publish_payment_type_layout.setVisibility(View.GONE);
                }

                commonDialog_type.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        commonDialog_type.show();
    }


    private void setCountryDialog(){
        commonDialog_country = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog_country.setAlertDialogHight(0);
        commonDialog_country.setAlertDialogGravity(Gravity.BOTTOM);

        RecyclerView recyclerView = commonDialog_country.getView(R.id.certify_type_select_rv, RecyclerView.class);
        TextView tittle = commonDialog_country.getView(R.id.list_item_title_tv,TextView.class);
        tittle.setText("选择地区");
        ImageView dialog_right_iv = commonDialog_country.getView(R.id.dialog_right_iv,ImageView.class);
        dialog_right_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commonDialog_country.dismiss();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        BaseRecycleAdapter<DialogItemBean> adapter = new BaseRecycleAdapter<DialogItemBean>(R.layout.bottom_select_item, data_country
        ) {
            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                certifyType_tv.setText(item.getCertifyName());
            }
        };
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                req.setAra(data_country.get(position).getCertifyName());
                publishAdvertiseAreaCiv.setRightText(data_country.get(position).getCertifyName());
                commonDialog_country.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        commonDialog_country.show();
    }
}
