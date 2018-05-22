package com.qmkj.jydp.module.exchangecenter.view;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.event.ExchangeEvent;
import com.qmkj.jydp.bean.request.BuyExchangeReq;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.request.SellExchangeReq;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:m
 * 卖出界面
 */

public class ExchangeSoldFragment extends BaseMvpFragment<ExchangeCenterPresenter> {
    private static final int EXCHANGE_PWD_TAG = 1;
    private static final int SELL_EXCAHNGE_TAG = 2;
    public String digits = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
    @BindView(R.id.exchange_sold_forzen_tv)
    TextView exchangeSoldForzenTv;
    @BindView(R.id.exchange_sold_available_tv)
    TextView exchangeSoldAvailableTv;
    @BindView(R.id.exchange_unit_price_et)
    EditText exchangeUnitPriceEt;
    @BindView(R.id.exchange_amount_et)
    EditText exchangeAmountEt;
    @BindView(R.id.exchange_passowrd_et)
    EditText exchangePassowrdEt;
    @BindView(R.id.exchange_passowrd_iv)
    ImageView exchangePassowrdIv;
    @BindView(R.id.exchange_center_sold_out_bt)
    Button exchangeCenterSoldOutBt;
    private CommonDialog pwdDialogUtils;

    public static ExchangeSoldFragment newInstance(int index) {
        Bundle args = new Bundle();
        ExchangeSoldFragment fragment = new ExchangeSoldFragment();
        args.putInt(Constants.INTENT_PARAMETER_1, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initInputView();
        exchangePassowrdIv.setOnClickListener(this);
        exchangeCenterSoldOutBt.setOnClickListener(this);
    }

    private void initInputView() {
        exchangeUnitPriceEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchangeUnitPriceEt.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        exchangeUnitPriceEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});

        exchangeAmountEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchangeAmountEt.setInputType(InputType.TYPE_CLASS_NUMBER);

        exchangePassowrdEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchangePassowrdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                .TYPE_TEXT_VARIATION_PASSWORD);
        exchangePassowrdEt.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        exchangePassowrdEt.setKeyListener(DigitsKeyListener.getInstance(digits));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.exchange_fragment_exchange_sold;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) {
            pwdDialogUtils.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.exchange_passowrd_iv:
                showSettingExchangePwdDialog();
                break;
            case R.id.exchange_center_sold_out_bt:

                submitSellXt();
                break;
        }
    }


    boolean isRememberLoginPwd = false;
    boolean isRememberExchangePwd = false;

    private void showSettingExchangePwdDialog() {

        pwdDialogUtils = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_settting_pwd);
        TextView message = pwdDialogUtils.getView(R.id.message, TextView.class);
        message.setVisibility(View.GONE);
        FrameLayout common_dialog_content_container_fl = pwdDialogUtils.getView(R.id.common_dialog_content_container_fl,
                FrameLayout.class);
        View view = View.inflate(mContext, R.layout.exchange_dialog_setting_pwd_content, null);
        common_dialog_content_container_fl.addView(view);
        common_dialog_content_container_fl.setVisibility(View.VISIBLE);
        ImageView exchange_setting_pwd_login_iv = view.findViewById(R.id.exchange_setting_pwd_login_iv);
        ImageView exchange_setting_pwd_exchange_iv = view.findViewById(R.id.exchange_setting_pwd_exchange_iv);
        LinearLayout exchange_setting_pwd_login_ll = view.findViewById(R.id.exchange_setting_pwd_login_ll);
        LinearLayout exchange_setting_pwd_exchange_ll = view.findViewById(R.id.exchange_setting_pwd_exchange_ll);
        LinearLayout setting_pwd_ll = view.findViewById(R.id.setting_pwd_ll);
        EditText exchange_passowrd_et = view.findViewById(R.id.exchange_passowrd_et);
        setting_pwd_ll.setFocusable(true);
        setting_pwd_ll.setFocusableInTouchMode(true);
        setting_pwd_ll.requestFocus();

        exchange_passowrd_et.setOnTouchListener((v, event) -> {
            exchange_passowrd_et.setCursorVisible(true);
            exchange_passowrd_et.setFocusable(true);
            exchange_passowrd_et.setFocusableInTouchMode(true);
            exchange_passowrd_et.requestFocus();
            return false;
        });

        exchange_passowrd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchange_passowrd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                .TYPE_TEXT_VARIATION_PASSWORD);
        exchange_passowrd_et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        exchange_passowrd_et.setKeyListener(DigitsKeyListener.getInstance(digits));

        exchange_setting_pwd_login_ll.setOnClickListener(v -> {
            isRememberLoginPwd = !isRememberLoginPwd;
            exchange_setting_pwd_login_iv.setImageResource(isRememberLoginPwd ? R.mipmap.bt_selected : R.mipmap
                    .bt_unselected);
            if (isRememberExchangePwd) {
                exchange_setting_pwd_exchange_iv.setImageResource(R.mipmap.bt_unselected);
            }
        });
        exchange_setting_pwd_exchange_ll.setOnClickListener(v -> {
            isRememberExchangePwd = !isRememberExchangePwd;
            exchange_setting_pwd_exchange_iv.setImageResource(isRememberExchangePwd ? R.mipmap.bt_selected : R
                    .mipmap.bt_unselected);
            if (isRememberLoginPwd) {
                exchange_setting_pwd_login_iv.setImageResource(R.mipmap.bt_unselected);
            }
        });
        pwdDialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        pwdDialogUtils.setOneOrTwoBtn(false);
        pwdDialogUtils.setTitle(CommonUtil.getString(R.string.remember_pwd_notice));
        pwdDialogUtils.setTwoCancelBtn("取消", v -> {
            LogUtil.i("取消");
            pwdDialogUtils.dismiss();
        });
        pwdDialogUtils.setTwoConfirmBtn("确认", v -> {
            String exchangePwd = exchange_passowrd_et.getText().toString().trim();
            if (TextUtils.isEmpty(exchangePwd)) {
                toast("请输入交易密码");
                return;
            }
            ExchangePwdReq exchangePwdReq = new ExchangePwdReq();
            if (isRememberLoginPwd) {
                exchangePwdReq.setPayPasswordStatus(2 + "");
            }
            if (isRememberExchangePwd) {
                exchangePwdReq.setPayPasswordStatus(1 + "");
            }
            exchangePwdReq.setRememberPwd(exchangePwd);
            presenter.rememberExchangePwd(exchangePwdReq, EXCHANGE_PWD_TAG, true);
        });
        pwdDialogUtils.show();
    }


    private void submitSellXt() {
        String buyPrice = exchangeUnitPriceEt.getText().toString().trim();
        String buyAmount = exchangeAmountEt.getText().toString().trim();
        String buyPassword = exchangePassowrdEt.getText().toString().trim();
        String currencyId = null;
        ExchangeFragment parentFragment = (ExchangeFragment) getParentFragment();
        if (parentFragment != null) {
            currencyId = parentFragment.getCurrencyId();
        }

        if (TextUtils.isEmpty(buyPrice)) {
            toast("购买单价不能为空");
            return;
        }
        if (TextUtils.isEmpty(buyAmount)) {
            toast("购买数量不能为空");
            return;
        }
        if (TextUtils.isEmpty(currencyId)) {
            LogUtil.i("购买的产品currencyId为空");
            return;
        }
        if (TextUtils.isEmpty(buyPassword)) {
            LogUtil.i("密码输入不能为空为空");
            return;
        }
        SellExchangeReq sellExchangeReq = new SellExchangeReq();
        sellExchangeReq.setCurrencyId(currencyId);
        sellExchangeReq.setSellPrice(buyPrice);
        sellExchangeReq.setSellNum(buyAmount);
        sellExchangeReq.setSellPwd(buyPassword);
        presenter.sellXtExchange(sellExchangeReq, SELL_EXCAHNGE_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case SELL_EXCAHNGE_TAG:
                toast("交易成功");
                RxBus.getDefault().post(new ExchangeEvent());
                break;
            case EXCHANGE_PWD_TAG:
                toast("密码设置成功");
                if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) pwdDialogUtils.dismiss();

                break;
        }
    }

    public void refreshData(ExchangeCenterRes centerRes) {
        if (centerRes == null || centerRes.getUserDealCapitalMessage() == null) return;
        ExchangeCenterRes.UserDealCapitalMessageBean data = centerRes.getUserDealCapitalMessage();
        exchangeSoldForzenTv.setText(NumberUtil.format4Point(data.getUserBalanceLock()));
        exchangeSoldAvailableTv.setText(NumberUtil.format4Point(data.getUserBalance()));

    }
}
