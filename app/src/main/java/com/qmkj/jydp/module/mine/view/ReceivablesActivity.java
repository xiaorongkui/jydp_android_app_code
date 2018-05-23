package com.qmkj.jydp.module.mine.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.OtcReleaseReq;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/10
 * email：dovexiaoen@163.com
 * description:收款信息
 */

public class ReceivablesActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.receivables_bank_card_num_eiv)
    EditVItemView receivables_bank_card_num_eiv;
    @BindView(R.id.receivables_bank_name_eiv)
    EditVItemView receivables_bank_name_eiv;
    @BindView(R.id.receivables_bank_branch_name_eiv)
    EditVItemView receivables_bank_branch_name_eiv;
    @BindView(R.id.receivables_bank_reserve_name_eiv)
    EditVItemView receivables_bank_reserve_name_eiv;
    @BindView(R.id.receivables_bank_reserve_phone_eiv)
    EditVItemView receivables_bank_reserve_phone_eiv;


    @BindView(R.id.receivables_alipay_account_eiv)
    EditVItemView receivables_alipay_account_eiv;
    @BindView(R.id.receivables_alipay_receipt_code_eiv)
    EditVItemView receivables_alipay_receipt_code_eiv;


    @BindView(R.id.receivables_wechat_account_eiv)
    EditVItemView receivables_wechat_account_eiv;
    @BindView(R.id.receivables_wechat_receipt_code_eiv)
    EditVItemView receivables_wechat_receipt_code_eiv;

    @BindView(R.id.receivables_confirm_bt)
    Button receivables_confirm_bt;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.receiables_info));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_receivables_info;
    }

    @Override
    protected void initView() {
        receivables_confirm_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.receivables_confirm_bt:
                String bank_card_num_eiv = receivables_bank_card_num_eiv.getEditTextString(); //卡号
                String bank_name = receivables_bank_name_eiv.getEditTextString();
                String branch_name = receivables_bank_branch_name_eiv.getEditTextString();
                String reserve_name = receivables_bank_reserve_name_eiv.getEditTextString();
                String phone = receivables_bank_reserve_phone_eiv.getEditTextString();

                String alipay_account = receivables_alipay_account_eiv.getEditTextString();
                String alipay_receipt_code = receivables_alipay_receipt_code_eiv.getEditTextString();

                String wechat_account = receivables_wechat_account_eiv.getEditTextString();
                String wechat_receipt_code = receivables_wechat_receipt_code_eiv.getEditTextString();


                OtcReleaseReq releaseReq = new OtcReleaseReq();
                releaseReq.setBankAccount(bank_card_num_eiv);
                releaseReq.setBankName(bank_name);
                releaseReq.setBankBranch(branch_name);
                releaseReq.setPaymentName(reserve_name);
                releaseReq.setPaymentPhone(phone);

                releaseReq.setAlipayAccount(alipay_account);
                releaseReq.setAlipayImageUrl(alipay_receipt_code);

                releaseReq.setWechatAccount(wechat_account);
                releaseReq.setWechatImageUrl(wechat_receipt_code);

                presenter.sendOtcReleaseInfo(releaseReq,1,true);

                break;
        }
    }
}
