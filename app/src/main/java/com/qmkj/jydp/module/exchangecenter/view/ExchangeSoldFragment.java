package com.qmkj.jydp.module.exchangecenter.view;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.bean.event.ExchangeEvent;
import com.qmkj.jydp.bean.event.ExchangePwdEvent;
import com.qmkj.jydp.bean.request.ExchangePwdReq;
import com.qmkj.jydp.bean.request.SellExchangeReq;
import com.qmkj.jydp.bean.response.ExchangeCenterRes;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.common.NetResponseCode;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;
import com.qmkj.jydp.module.login.view.LoginActivity;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.RxBus;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:m
 * 卖出界面
 */

public class ExchangeSoldFragment extends BaseMvpFragment<ExchangeCenterPresenter> {
    private static final int EXCHANGE_PWD_TAG = 1;
    private static final int SELL_EXCAHNGE_TAG = 2;
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
    @BindView(R.id.exchange_max_sold_tv)
    TextView exchange_max_sold_tv;
    @BindView(R.id.exchange_sold_notice_pwd_tv)
    TextView exchange_sold_notice_pwd_tv;
    private CommonDialog pwdDialogUtils;
    private static final int DECIMAL_DIGITS_AMMOUNT = 4;//最多输入4位数值
    private static final int DECIMAL_DIGITS_PRICE = 2;//最多输入2位数值
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_1;
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_2;
    private ExchangeCenterRes.UserDealCapitalMessageBean data;
    private String payPasswordStatus;
    private boolean isInputExchangePwd;
    private com.qmkj.jydp.ui.widget.dialog.CommonDialog loginCommonDialog_3;
    private String sellFee;
    private CommonDialog sellDialogUtils;
    private ExchangeCenterRes centerRes;
    private String sellPrice;
    private String sellAmount;
    private String sellPassword;
    private String currencyId;
    boolean isRefreshPwd = true;
    private EditText exchange_passowrd_et;
    private Disposable subscribe_1;

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
        exchangePassowrdIv.setOnClickListener(this);
        InitialValueObservable<CharSequence> amountTextChanges = RxTextView.textChanges(exchangeAmountEt);
        amountTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> restrictedInput(sequence, exchangeAmountEt,
                DECIMAL_DIGITS_AMMOUNT));
        InitialValueObservable<CharSequence> priceTextChanges = RxTextView.textChanges(exchangeUnitPriceEt);
        priceTextChanges.compose(bindToLifecycle()).observeOn(AndroidSchedulers
                .mainThread()).subscribe(sequence -> {
            restrictedInput(sequence, exchangeUnitPriceEt, DECIMAL_DIGITS_PRICE);
            calculateSellAccount();
        });
        RxView.clicks(exchangeCenterSoldOutBt).throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> sellStart());//防重复点击

        subscribe_1 = RxBus.getDefault().toObservable(ExchangePwdEvent.class).subscribe(exchangeEvent -> isRefreshPwd
                = true);
    }

    private void calculateSellAccount() {
        String price = exchangeUnitPriceEt.getText().toString().trim();
        double parseDoublePrice = 0;
        double parseDoubleAvailable = 0;
        double parseDoubleAvailableSellFee = 0;
        try {
            parseDoublePrice = Double.parseDouble(price);
            parseDoubleAvailable = Double.parseDouble(data.getCurrencyNumber());
            parseDoubleAvailableSellFee = NumberUtil.div(Double.parseDouble(sellFee), 100, 4);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.i("卖出输入数据格式化错误" + e.getMessage());
        }
        if (parseDoublePrice > 0 && parseDoubleAvailable >= 0) {
            double totalPrice = NumberUtil.mul((1 + parseDoubleAvailableSellFee), parseDoublePrice);
            double div = NumberUtil.div(parseDoubleAvailable, totalPrice, 6);
            exchange_max_sold_tv.setText(String.format("最大可获得：%sXT", NumberUtil.format2Point(div)));
        } else exchange_max_sold_tv.setText(R.string.max_sold_get_xt);
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
        if (loginCommonDialog_1 != null && loginCommonDialog_1.isShowing()) {
            loginCommonDialog_1.dismiss();
        }
        if (loginCommonDialog_2 != null && loginCommonDialog_2.isShowing()) {
            loginCommonDialog_2.dismiss();
        }
        if (loginCommonDialog_3 != null && loginCommonDialog_3.isShowing()) {
            loginCommonDialog_3.dismiss();
        }
        if (subscribe_1 != null && subscribe_1.isDisposed()) {
            subscribe_1.dispose();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.exchange_passowrd_iv:
                if (TextUtils.isEmpty(DataManager.getToken())) {
                    loginCommonDialog_1 = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
                    loginCommonDialog_1.setContentText("请先登录");
                    loginCommonDialog_1.setOnPositiveButtonClickListener((Dialog dialog, View view) -> {
                        ActivityManager.gotoActivity(mContext, LoginActivity.class);
                        loginCommonDialog_1.dismiss();
                    });
                    loginCommonDialog_1.show();
                    return;
                }
                showSettingExchangePwdDialog();
                break;
        }
    }

    private void sellStart() {
        if (TextUtils.isEmpty(DataManager.getToken())) {
            loginCommonDialog_2 = new com.qmkj.jydp.ui.widget.dialog.CommonDialog(mContext);
            loginCommonDialog_2.setContentText("请先登录");
            loginCommonDialog_2.setOnPositiveButtonClickListener((Dialog dialog, View view) -> {
                ActivityManager.gotoActivity(mContext, LoginActivity.class);
                loginCommonDialog_2.dismiss();
            });
            loginCommonDialog_2.show();
            return;
        }
        sellPrice = exchangeUnitPriceEt.getText().toString().trim();
        sellAmount = exchangeAmountEt.getText().toString().trim();
        sellPassword = exchangePassowrdEt.getText().toString().trim();
        ExchangeFragment parentFragment = (ExchangeFragment) getParentFragment();
        if (parentFragment != null) {
            currencyId = parentFragment.getCurrencyId();
        }

        if (TextUtils.isEmpty(sellPrice)) {
            toast("请输入卖出单价");
            return;
        }
        if (TextUtils.isEmpty(sellAmount)) {
            toast("请输入卖出数量");
            return;
        }
        double parseSellPrice = 0;
        double parseSellAmount = 0;
        try {
            parseSellPrice = Double.parseDouble(sellPrice);
            parseSellAmount = Double.parseDouble(sellAmount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (parseSellPrice <= 0) {
            toast("卖出单价不能小于等于0");
            return;
        }
        if (parseSellAmount <= 0) {
            toast("卖出数量不能小于等于0");
            return;
        }

        if (TextUtils.isEmpty(currencyId)) {
            toast("购买的产品currencyId为空");
            return;
        }
        if (TextUtils.isEmpty(sellPassword)) {
            toast("请输入交易密码");
            return;
        }
        if (sellPassword.length() < 6) {
            toast("交易密码长度不能小于6位");
            return;
        }
        showBuyDialog();
    }

    boolean isRememberLoginPwd = false;
    boolean isRememberExchangePwd = false;

    private void showSettingExchangePwdDialog() {
        isRememberLoginPwd = false;
        isRememberExchangePwd = false;

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
        exchange_passowrd_et = view.findViewById(R.id.exchange_passowrd_et);

        exchange_passowrd_et.setOnTouchListener((View v, MotionEvent event) -> {
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
                ActivityManager.gotoActivity(mContext, LoginActivity.class);
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
        pwdDialogUtils.setAlertDialogWidth((int) ResourcesManager.getDimen(R.dimen.x330));
        pwdDialogUtils.setOneOrTwoBtn(false);
        pwdDialogUtils.setTitle(ResourcesManager.getString(R.string.remember_pwd_notice));
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
            if (isRememberLoginPwd) {
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


    private void submitSellXt() {
        SellExchangeReq sellExchangeReq = new SellExchangeReq();
        sellExchangeReq.setCurrencyId(currencyId);
        sellExchangeReq.setSellPrice(sellPrice);
        sellExchangeReq.setSellNum(sellAmount);
        sellExchangeReq.setSellPwd(sellPassword);
        presenter.sellXtExchange(sellExchangeReq, SELL_EXCAHNGE_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case SELL_EXCAHNGE_TAG:
                toast("挂单成功");
                RxBus.getDefault().post(new ExchangeEvent());
                RxBus.getDefault().post(new ExchangePwdEvent());//去更新密码状态
                String exchanegPwd = exchangePassowrdEt.getText().toString().trim();
                if (!TextUtils.isEmpty(exchanegPwd)) {
                    DataManager.saveExchangePwd(exchanegPwd);
                }
                if (sellDialogUtils != null && sellDialogUtils.isShowing()) sellDialogUtils.dismiss();
                clearSoldInput();
                break;
            case EXCHANGE_PWD_TAG:
                toast("密码设置成功");
                DataManager.saveExchangePwd(exchange_passowrd_et.getText().toString().trim());
                if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) pwdDialogUtils.dismiss();
                RxBus.getDefault().post(new ExchangePwdEvent());//去更新密码状态
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            case SELL_EXCAHNGE_TAG:
                if (sellDialogUtils != null && sellDialogUtils.isShowing()) sellDialogUtils.dismiss();
                if (TextUtils.isEmpty(code)) return;
                switch (code) {
                    case NetResponseCode.HMC_EXCHANGE_PWD_ERROR:
                        RxBus.getDefault().post(new ExchangePwdEvent());//去更新密码状态
                        break;
                }
                break;
            case EXCHANGE_PWD_TAG://密码错误
                if (pwdDialogUtils != null && pwdDialogUtils.isShowing()) pwdDialogUtils.dismiss();
                if (TextUtils.isEmpty(code)) return;
                switch (code) {
                    case NetResponseCode.HMC_EXCHANGE_PWD_ERROR:
                        RxBus.getDefault().post(new ExchangePwdEvent());//去更新密码状态
                        break;
                }
                break;
        }
    }

    private void clearSoldInput() {
        exchangeUnitPriceEt.setText("");
        exchangeAmountEt.setText("");
    }

    @Override
    protected void onViewResume() {
        super.onViewResume();
//        clearSoldInput();
    }

    @Override
    protected void onViewPause() {
        super.onViewPause();
        clearSoldInput();

    }

    public void cleanAssetes() {
        exchangeSoldForzenTv.setText("--");
        exchangeSoldAvailableTv.setText("--");
    }

    public void refreshData(ExchangeCenterRes centerRes) {
        if (centerRes == null || centerRes.getUserDealCapitalMessage() == null) return;
        this.centerRes = centerRes;
        data = centerRes.getUserDealCapitalMessage();
        exchangeSoldForzenTv.setText(NumberUtil.format4Point(data.getCurrencyNumberLock()));
        exchangeSoldAvailableTv.setText(NumberUtil.format4Point(data.getCurrencyNumber()));
        String price = exchangeUnitPriceEt.getText().toString().trim();
        String amount = exchangeAmountEt.getText().toString().trim();
        if (TextUtils.isEmpty(price) && TextUtils.isEmpty(amount)) {
            exchange_max_sold_tv.setText(R.string.max_sold_get_xt);
        }
        ExchangeCenterRes.TransactionCurrencyBean transactionCurrency = centerRes.getTransactionCurrency();
        if (transactionCurrency != null) {
            sellFee = transactionCurrency.getSellFee();
        }
        payPasswordStatus = centerRes.getPayPasswordStatus();
        if (!isRefreshPwd) return;
        if (TextUtils.isEmpty(payPasswordStatus)) {
            payPasswordStatus = "1";
        }
        switch (payPasswordStatus) {
            case "1"://1：每笔交易都输入交易密码
                exchange_sold_notice_pwd_tv.setText(ResourcesManager.getString(R.string
                        .exchange_password_exchange_notice));
                exchangePassowrdEt.setText("");//初始化一次
                break;
            case "2"://2：每次登录只输入一次交易密码
                exchange_sold_notice_pwd_tv.setText(ResourcesManager.getString(R.string
                        .exchange_password_login_notice));
                exchangePassowrdEt.setText(DataManager.getExchangePwd());
                break;
        }
        isRefreshPwd = false;

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
        if (s.toString().trim().equals(".")) {
            s = "0" + s;
            exchangeAmountEt.setText(s);
            exchangeAmountEt.setSelection(2);
        }
        if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                exchangeAmountEt.setText(s.subSequence(0, 1));
                exchangeAmountEt.setSelection(1);
            }
        }
    }

    private void showBuyDialog() {
        sellDialogUtils = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_settting_pwd);
        TextView message = sellDialogUtils.getView(R.id.message, TextView.class);
        message.setVisibility(View.GONE);
        FrameLayout common_dialog_content_container_fl = sellDialogUtils.getView(R.id
                        .common_dialog_content_container_fl,
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
        exchange_buy_price_tv.setText((NumberUtil.format4Point(sellPrice) + " XT"));
        exchange_buy_amount_tv.setText((NumberUtil.format2Point(sellAmount)));

        double parseDoublePrice = 0;
        double parseDoublebuyAmount = 0;
        double parseDoubleSellFee = 0;
        try {
            parseDoublePrice = Double.parseDouble(sellPrice);
            parseDoublebuyAmount = Double.parseDouble(sellAmount);
            parseDoubleSellFee = Double.parseDouble(sellFee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        double money = NumberUtil.mul(parseDoublePrice, parseDoublebuyAmount);
        exchange_buy_total_tv.setText(String.format("%s XT", NumberUtil.format4Point(money)));
        exchange_buy_fee_tv.setText(String.format("%s%%", parseDoubleSellFee));

        sellDialogUtils.setAlertDialogWidth((int) ResourcesManager.getDimen(R.dimen.x330));
        sellDialogUtils.setOneOrTwoBtn(false);
        sellDialogUtils.setTitle(ResourcesManager.getString(R.string.sold_notice));
        sellDialogUtils.setTwoCancelBtn("取消", v -> {
            LogUtil.i("取消");
            sellDialogUtils.dismiss();
        });

        RxView.clicks(sellDialogUtils.getView(R.id.yes, Button.class)).throttleFirst(2, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(o -> submitSellXt());//防重复点击
        sellDialogUtils.show();
    }
}
