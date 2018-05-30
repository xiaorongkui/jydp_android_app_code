package com.qmkj.jydp.module.exchangecenter.view;

import android.app.Dialog;
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

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.event.ExchangeEvent;
import com.qmkj.jydp.bean.request.BuyExchangeReq;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxBus;

import butterknife.BindView;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:买入界面
 */

public class ExchangeBuyFragment extends BaseMvpFragment<ExchangeCenterPresenter> {
    private static final int BUY_EXCAHNGE_TAG = 1;
    private static final int EXCHANGE_PWD_TAG = 2;
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
    TextView exchange_buy_pwd_notice_tv;
    @BindView(R.id.buy_max_tv)
    TextView buy_max_tv;
    private CommonDialog pwdDialogUtils;
    private CommonDialog buyDialogUtils;
    private ExchangeCenterRes centerRes;
    private String buyPrice;
    private String buyAmount;
    private String buyPassword;
    private String currencyId;

    private static final int DECIMAL_DIGITS_AMMOUNT = 4;//最多输入4位数值
    private static final int DECIMAL_DIGITS_PRICE = 2;//最多输入2位数值
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_1;
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_2;
    private ExchangeCenterRes.UserDealCapitalMessageBean userDealCapitalMessage;
    private String payPasswordStatus;
    private EditText exchange_passowrd_et;
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_3;
    private String buyFee;

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

        InitialValueObservable<CharSequence> amountTextChanges = RxTextView.textChanges(exchangeAmountEt);
        amountTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> {
            restrictedInput(sequence, exchangeAmountEt, DECIMAL_DIGITS_AMMOUNT);
        });
        InitialValueObservable<CharSequence> priceTextChanges = RxTextView.textChanges(exchangeUnitPriceEt);
        priceTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> {
            restrictedInput(sequence, exchangeUnitPriceEt, DECIMAL_DIGITS_PRICE);
            calculateBuyAccount();
        });
    }

    private void calculateBuyAccount() {
        String price = exchangeUnitPriceEt.getText().toString().trim();
        double parseDoublePrice = 0;
        double parseDoubleAvailable = 0;
        double parseDoubleAvailableBuyFee = 0;
        try {
            parseDoublePrice = Double.parseDouble(price);
            parseDoubleAvailable = Double.parseDouble(userDealCapitalMessage.getUserBalance());
            parseDoubleAvailableBuyFee = NumberUtil.div(Double.parseDouble(buyFee), 100, 4);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (parseDoublePrice > 0 && parseDoubleAvailable >= 0) {
            double totalPrice = NumberUtil.mul((1 + parseDoubleAvailableBuyFee), parseDoublePrice);
            double div = NumberUtil.div(parseDoubleAvailable, totalPrice, 4);
            buy_max_tv.setText("最大可买：" + NumberUtil.format4Point(div));
        } else {
            buy_max_tv.setText("最大可买：0");
        }
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
        if (loginCommonDialog_1 != null && loginCommonDialog_1.isShowing()) {
            loginCommonDialog_1.dismiss();
        }
        if (loginCommonDialog_2 != null && loginCommonDialog_2.isShowing()) {
            loginCommonDialog_2.dismiss();
        }
        if (loginCommonDialog_3 != null && loginCommonDialog_3.isShowing()) {
            loginCommonDialog_3.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_passowrd_iv:
                if (TextUtils.isEmpty(CommonUtil.getToken())) {
                    loginCommonDialog_1 = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
                    loginCommonDialog_1.setContentText("请先登录");
                    loginCommonDialog_1.setOnPositiveButtonClickListener((Dialog dialog, View view) -> {
                        CommonUtil.gotoActivity(mContext, LoginActivity.class);
                        loginCommonDialog_1.dismiss();
                    });
                    loginCommonDialog_1.show();
                    return;
                }
                showSettingExchangePwdDialog();
                break;
            case R.id.exchange_center_buy_bt:
                if (TextUtils.isEmpty(CommonUtil.getToken())) {
                    loginCommonDialog_2 = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
                    loginCommonDialog_2.setContentText("请先登录");
                    loginCommonDialog_2.setOnPositiveButtonClickListener((Dialog dialog, View view) -> {
                        CommonUtil.gotoActivity(mContext, LoginActivity.class);
                        loginCommonDialog_2.dismiss();
                    });
                    loginCommonDialog_2.show();
                    return;
                }

                buyPrice = exchangeUnitPriceEt.getText().toString().trim();
                buyAmount = exchangeAmountEt.getText().toString().trim();
                buyPassword = exchangePassowrdEt.getText().toString().trim();
                ExchangeFragment parentFragment = (ExchangeFragment) getParentFragment();
                if (parentFragment != null) {
                    currencyId = parentFragment.getCurrencyId();
                }
                if (TextUtils.isEmpty(buyPrice)) {
                    toast("请输入购买单价");
                    return;
                }

                if (TextUtils.isEmpty(buyAmount)) {
                    toast("请输入购买数量");
                    return;
                }
                double parseBuyPrice = 0;
                double parseBuyAmount = 0;
                try {
                    parseBuyPrice = Double.parseDouble(buyPrice);
                    parseBuyAmount = Double.parseDouble(buyAmount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (parseBuyPrice <= 0) {
                    toast("购买单价不能小于等于0");
                    return;
                }
                if (parseBuyAmount <= 0) {
                    toast("购买单价不能小于等于0");
                    return;
                }

                if (TextUtils.isEmpty(currencyId)) {
                    toast("购买的产品currencyId为空");
                    return;
                }
                if (TextUtils.isEmpty(buyPassword)) {
                    toast("请输入交易密码");
                    return;
                }

                if (buyPassword.length() < 6) {
                    toast("密码长度不能小于6位");
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

    boolean isRememberExchangePwd = false;
    boolean isInputExchangePwd = false;

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
        exchange_passowrd_et = view.findViewById(R.id.exchange_passowrd_et);

        exchange_passowrd_et.setOnTouchListener((v, event) -> {
            exchange_passowrd_et.setCursorVisible(true);
            exchange_passowrd_et.setFocusable(true);
            exchange_passowrd_et.setFocusableInTouchMode(true);
            exchange_passowrd_et.requestFocus();
            return false;
        });
        if (TextUtils.isEmpty(payPasswordStatus)) {
            loginCommonDialog_3 = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
            loginCommonDialog_3.setContentText("请先登录");
            loginCommonDialog_3.setOnPositiveButtonClickListener((Dialog dialog, View v) -> {
                CommonUtil.gotoActivity(mContext, LoginActivity.class);
                loginCommonDialog_3.dismiss();
            });
            loginCommonDialog_3.show();
            return;
        }
        switch (payPasswordStatus) {
            case "1"://1：每笔交易都输入交易密码
                isInputExchangePwd = true;
                exchange_setting_pwd_exchange_iv.setImageResource(R.mipmap.bt_selected);
                break;
            case "2"://2：每次登录只输入一次交易密码
                isRememberExchangePwd = true;
                exchange_setting_pwd_login_iv.setImageResource(R.mipmap.bt_selected);
                break;
        }

        exchange_setting_pwd_login_ll.setOnClickListener(v -> {
            if (!isRememberExchangePwd) {
                isRememberExchangePwd = true;
                isInputExchangePwd = false;
                exchange_setting_pwd_login_iv.setImageResource(R.mipmap.bt_selected);
                exchange_setting_pwd_exchange_iv.setImageResource(R.mipmap.bt_unselected);
            }
        });
        exchange_setting_pwd_exchange_ll.setOnClickListener(v -> {
            if (!isInputExchangePwd) {
                isRememberExchangePwd = false;
                isInputExchangePwd = true;
                exchange_setting_pwd_login_iv.setImageResource(R.mipmap.bt_unselected);
                exchange_setting_pwd_exchange_iv.setImageResource(R.mipmap.bt_selected);
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
            if (exchangePwd.length() < 6) {
                toast("交易密码长度不能小于6位");
                return;
            }
            ExchangePwdReq exchangePwdReq = new ExchangePwdReq();
            if (isInputExchangePwd) {
                exchangePwdReq.setPayPasswordStatus("1");
            }
            if (isRememberExchangePwd) {
                exchangePwdReq.setPayPasswordStatus("2");
            }
            exchangePwdReq.setRememberPwd(exchangePwd);
            presenter.rememberExchangePwd(exchangePwdReq, EXCHANGE_PWD_TAG, true);
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
        exchange_buy_price_tv.setText((NumberUtil.format4Point(buyPrice) + " XT"));
        exchange_buy_amount_tv.setText((NumberUtil.format2Point(buyAmount)));

        double parseDoublePrice = 0;
        double parseDoublebuyAmount = 0;
        double parseDoubleBuyFee = 0;
        try {
            parseDoublePrice = Double.parseDouble(buyPrice);
            parseDoublebuyAmount = Double.parseDouble(buyAmount);
            parseDoubleBuyFee = Double.parseDouble(buyFee);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        double money = NumberUtil.mul(parseDoublePrice, parseDoublebuyAmount);
        double totalFee = NumberUtil.mul(money, parseDoubleBuyFee);
        double totalMoney = NumberUtil.add(money, totalFee);

        exchange_buy_total_tv.setText(NumberUtil.format4Point(money) + " XT");
        exchange_buy_fee_tv.setText(parseDoubleBuyFee + "%");

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
        userDealCapitalMessage = centerRes.getUserDealCapitalMessage();

        double total = 0;
        try {
            total = Double.parseDouble(userDealCapitalMessage.getUserBalanceLock()) + Double.parseDouble
                    (userDealCapitalMessage.getUserBalance());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            LogUtil.i("资产总额格式化异常" + e.getMessage());
        }
        exchangeBuyTotalAssetsTv.setText(NumberUtil.format4Point(userDealCapitalMessage.getCurrencyNumberSum()));
        exchangeForzenAssetsTv.setText(NumberUtil.format4Point(userDealCapitalMessage.getUserBalanceLock()));
        exchangeAvailableAssetsTv.setText(NumberUtil.format4Point(userDealCapitalMessage.getUserBalance()));
        String price = exchangeUnitPriceEt.getText().toString().trim();
        String amount = exchangeAmountEt.getText().toString().trim();
        if (TextUtils.isEmpty(price) && TextUtils.isEmpty(amount)) {
            buy_max_tv.setText("最大可买：0");
        }

        payPasswordStatus = centerRes.getPayPasswordStatus();
        if (TextUtils.isEmpty(payPasswordStatus)) return;
        switch (payPasswordStatus) {
            case "1"://1：每笔交易都输入交易密码
                exchange_buy_pwd_notice_tv.setText(CommonUtil.getString(R.string.exchange_password_exchange_notice));
                exchangePassowrdEt.setText("");
                break;
            case "2"://2：每次登录只输入一次交易密码
                exchange_buy_pwd_notice_tv.setText(CommonUtil.getString(R.string.exchange_password_login_notice));
                exchangePassowrdEt.setText(CommonUtil.getExchangePwd());
                break;
        }

        ExchangeCenterRes.TransactionCurrencyBean transactionCurrency = centerRes.getTransactionCurrency();
        if (transactionCurrency != null) {
            buyFee = transactionCurrency.getBuyFee();
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case BUY_EXCAHNGE_TAG:
                toast("挂单成功");
                if (buyDialogUtils != null && buyDialogUtils.isShowing()) buyDialogUtils.dismiss();
                RxBus.getDefault().post(new ExchangeEvent());
                exchangeUnitPriceEt.setText("");
                exchangeAmountEt.setText("");
                exchangePassowrdEt.setText("");
                break;
            case EXCHANGE_PWD_TAG:
                toast("记住密码设置成功");
                CommonUtil.saveExchangePwd(exchange_passowrd_et.getText().toString().trim());
                if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) pwdDialogUtils.dismiss();
                RxBus.getDefault().post(new ExchangeEvent());//去更新密码状态
                exchangePassowrdEt.setText(CommonUtil.getExchangePwd());
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            case BUY_EXCAHNGE_TAG:
                if (buyDialogUtils != null && buyDialogUtils.isShowing()) buyDialogUtils.dismiss();
                break;
        }
    }

    private void restrictedInput(CharSequence s, EditText exchangeAmountEt, int i) {
        if (TextUtils.isEmpty(s)) return;
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > i) {
                s = s.toString().subSequence(0, s.toString().indexOf(".") + i + 1);
                exchangeAmountEt.setText(s);
                exchangeAmountEt.setSelection(s.length());
            }
        }
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            exchangeAmountEt.setText(s);
            exchangeAmountEt.setSelection(2);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                exchangeAmountEt.setText(s.subSequence(0, 1));
                exchangeAmountEt.setSelection(1);
                return;
            }
        }
    }
}
