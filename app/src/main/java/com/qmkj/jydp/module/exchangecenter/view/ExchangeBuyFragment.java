package com.qmkj.jydp.module.exchangecenter.view;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
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
import com.qmkj.jydp.bean.response.BuyExchangeRes;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxBus;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:买入界面
 */

public class ExchangeBuyFragment extends BaseMvpFragment<ExchangeCenterPresenter> {
    private static final int BUY_EXCAHNGE_TAG = 1;
    private static final int EXCHANGE_PWD_TAG = 2;
    public String digits = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
    @BindView(R.id.exchange_passowrd_iv)
    ImageView exchangePassowrdIv;
    Unbinder unbinder;
    @BindView(R.id.exchange_buy_total_assets_tv)
    TextView exchangeBuyTotalAssetsTv;
    @BindView(R.id.exchange_forzen_assets_tv)
    TextView exchangeForzenAssetsTv;
    @BindView(R.id.exchange_available_assets_tv)
    TextView exchangeAvailableAssetsTv;
    @BindView(R.id.exchange_unit_price_et)
    EditText exchangeUnitPriceEt;
    @BindView(R.id.exchange_amount_et)
    EditText exchangeAmountEt;
    @BindView(R.id.exchange_passowrd_et)
    EditText exchangePassowrdEt;
    @BindView(R.id.exchange_center_buy_bt)
    Button exchangeCenterBuyBt;
    @BindView(R.id.exchange_buy_pwd_notice_tv)
    Button exchange_buy_pwd_notice_tv;
    private CommonDialog pwdDialogUtils;
    private CommonDialog buyDialogUtils;
    private ExchangeCenterRes centerRes;
    private String buyPrice;
    private String buyAmount;
    private String buyPassword;
    private String currencyId;

    public static ExchangeBuyFragment newInstance(int index) {
        Bundle args = new Bundle();
        ExchangeBuyFragment fragment = new ExchangeBuyFragment();
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
        exchangePassowrdIv.setOnClickListener(this);
        exchangeCenterBuyBt.setOnClickListener(this);
        initInput();
    }

    private void initInput() {
        exchangeUnitPriceEt.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchangeUnitPriceEt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
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
        return R.layout.exchange_fragment_exchange_buy;
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
        if (buyDialogUtils != null && buyDialogUtils.isShowing()) {
            buyDialogUtils.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_passowrd_iv:
                showSettingExchangePwdDialog();
                break;
            case R.id.exchange_center_buy_bt:
                buyPrice = exchangeUnitPriceEt.getText().toString().trim();
                buyAmount = exchangeAmountEt.getText().toString().trim();
                buyPassword = exchangePassowrdEt.getText().toString().trim();
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
                showBuyDialog();
                break;
        }
    }

    private void submitBuyXt() {
        BuyExchangeReq buyExchangeReq = new BuyExchangeReq();
        buyExchangeReq.setCurrencyId(currencyId);
        buyExchangeReq.setBuyPrice(buyPrice);
        buyExchangeReq.setBuyNum(buyAmount);
        buyExchangeReq.setBuyPwd(buyPassword);
        presenter.buyXtExchange(buyExchangeReq, BUY_EXCAHNGE_TAG, true);

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
        pwdDialogUtils.setTwoConfirmBtn("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exchangePwd = exchange_passowrd_et.getText().toString().trim();
                if (TextUtils.isEmpty(exchangePwd)) {
                    toast("请输入交易密码");
                    return;
                }
                ExchangePwdReq exchangePwdReq = new ExchangePwdReq();
                if (isRememberLoginPwd) {
                    exchangePwdReq.setPayPasswordStatus("2");
                }
                if (isRememberExchangePwd) {
                    exchangePwdReq.setPayPasswordStatus("1");
                }
                exchangePwdReq.setRememberPwd(exchangePwd);
                presenter.rememberExchangePwd(exchangePwdReq, EXCHANGE_PWD_TAG, true);
            }
        });
        pwdDialogUtils.show();
    }

    private void showBuyDialog() {

        buyDialogUtils = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_settting_pwd);
        TextView message = buyDialogUtils.getView(R.id.message, TextView.class);
        message.setVisibility(View.GONE);
        FrameLayout common_dialog_content_container_fl = buyDialogUtils.getView(R.id.common_dialog_content_container_fl,
                FrameLayout.class);
        View view = View.inflate(mContext, R.layout.exchange_dialog_buy_content, null);
        common_dialog_content_container_fl.addView(view);
        common_dialog_content_container_fl.setVisibility(View.VISIBLE);
        TextView exchange_buy_price_tv = view.findViewById(R.id.exchange_buy_price_tv);
        TextView exchange_buy_amount_tv = view.findViewById(R.id.exchange_buy_amount_tv);
        TextView exchange_buy_total_tv = view.findViewById(R.id.exchange_buy_total_tv);
        TextView exchange_buy_fee_tv = view.findViewById(R.id.exchange_buy_fee_tv);
        ExchangeCenterRes.TransactionCurrencyBean transactionCurrency = centerRes.getTransactionCurrency();
        if (transactionCurrency == null) {
            toast("购买数据异常，请刷新重试");
            return;
        }
        exchange_buy_price_tv.setText(buyPrice + " XT");
        exchange_buy_amount_tv.setText(buyAmount);
        double totalPrice = 0;
        try {
            totalPrice = Double.parseDouble(buyPrice) * Double.parseDouble(buyAmount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        exchange_buy_total_tv.setText(NumberUtil.format4Point(totalPrice) + " XT");
        exchange_buy_fee_tv.setText(transactionCurrency.getBuyFee() + " XT");
        buyDialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        buyDialogUtils.setOneOrTwoBtn(false);
        buyDialogUtils.setTitle(CommonUtil.getString(R.string.remember_pwd_notice));
        buyDialogUtils.setTwoCancelBtn("取消", v -> {
            LogUtil.i("取消");
            buyDialogUtils.dismiss();
        });
        buyDialogUtils.setTwoConfirmBtn("确认", v -> {
            submitBuyXt();
        });
        buyDialogUtils.show();
    }

    public void refreshData(ExchangeCenterRes centerRes) {
        if (centerRes == null || centerRes.getUserDealCapitalMessage() == null) return;
        this.centerRes = centerRes;
        ExchangeCenterRes.UserDealCapitalMessageBean data = centerRes.getUserDealCapitalMessage();

        double total = 0;
        try {
            total = Double.parseDouble(data.getUserBalanceLock()) + Double.parseDouble(data.getUserBalance());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("资产总额格式化异常" + e.getMessage());
        }
        exchangeBuyTotalAssetsTv.setText(NumberUtil.format4Point(total));
        exchangeForzenAssetsTv.setText(NumberUtil.format4Point(data.getUserBalanceLock()));
        exchangeAvailableAssetsTv.setText(NumberUtil.format4Point(data.getUserBalance()));

    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_EXCAHNGE_TAG:
                toast("购买成功");
                if (buyDialogUtils != null && buyDialogUtils.isShowing()) buyDialogUtils.dismiss();
                RxBus.getDefault().post(new ExchangeEvent());
                break;
            case EXCHANGE_PWD_TAG:
                toast("记住密码设置成功");
                if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) pwdDialogUtils.dismiss();
                if (isRememberLoginPwd)
                    exchange_buy_pwd_notice_tv.setText(CommonUtil.getString(R.string.exchange_password_login_notice));

                if (isRememberExchangePwd)
                    exchange_buy_pwd_notice_tv.setText(CommonUtil.getString(R.string
                            .exchange_password_exchange_notice));
                break;
        }
    }
}
