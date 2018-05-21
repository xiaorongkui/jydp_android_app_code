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
    private CommonDialog dialogUtils;

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
        if (dialogUtils != null && dialogUtils.isShowing()) {
            dialogUtils.dismiss();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exchange_passowrd_iv:
                showSettingExchangePwdDialog();
                break;
            case R.id.exchange_center_buy_bt:
                submitBuyXt();
                break;
        }
    }

    private void submitBuyXt() {
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

        dialogUtils = new CommonDialog(mContext, R.style.common_dialog, R.layout.common_dialog_settting_pwd);
        TextView message = dialogUtils.getView(R.id.message, TextView.class);
        message.setVisibility(View.GONE);
        FrameLayout common_dialog_content_container_fl = dialogUtils.getView(R.id.common_dialog_content_container_fl,
                FrameLayout.class);
        View view = View.inflate(mContext, R.layout.exchange_dialog_setting_pwd_content, null);
        common_dialog_content_container_fl.addView(view);
        common_dialog_content_container_fl.setVisibility(View.VISIBLE);
        ImageView exchange_setting_pwd_login_iv = view.findViewById(R.id.exchange_setting_pwd_login_iv);
        ImageView exchange_setting_pwd_exchange_iv = view.findViewById(R.id.exchange_setting_pwd_exchange_iv);
        EditText exchange_passowrd_et = view.findViewById(R.id.exchange_passowrd_et);
        exchange_passowrd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        exchange_passowrd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType
                .TYPE_TEXT_VARIATION_PASSWORD);
        exchange_passowrd_et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        exchange_passowrd_et.setKeyListener(DigitsKeyListener.getInstance(digits));

        exchange_setting_pwd_login_iv.setOnClickListener(v -> {
            isRememberLoginPwd = !isRememberLoginPwd;
            exchange_setting_pwd_login_iv.setImageResource(isRememberLoginPwd ? R.mipmap.bt_selected : R.mipmap
                    .bt_unselected);
        });
        exchange_setting_pwd_exchange_iv.setOnClickListener(v -> {
            isRememberExchangePwd = !isRememberExchangePwd;
            exchange_setting_pwd_exchange_iv.setImageResource(isRememberExchangePwd ? R.mipmap.bt_selected : R
                    .mipmap.bt_unselected);
        });
        dialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        dialogUtils.setOneOrTwoBtn(false);
        dialogUtils.setTitle(CommonUtil.getString(R.string.remember_pwd_notice));
        dialogUtils.setTwoCancelBtn("取消", v -> {
            LogUtil.i("取消");
            //todo
        });
        dialogUtils.setTwoConfirmBtn("确认", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exchangePwd = exchange_passowrd_et.getText().toString().trim();
                if (TextUtils.isEmpty(exchangePwd)) {
                    toast("请输入交易密码");
                    return;
                }
                ExchangePwdReq exchangePwdReq = new ExchangePwdReq();
                exchangePwdReq.setPayPasswordStatus("");
                exchangePwdReq.setRememberPwd(exchangePwd);
                presenter.rememberExchangePwd(exchangePwdReq, EXCHANGE_PWD_TAG, true);
            }
        });
        dialogUtils.show();
    }

    public void refreshData(ExchangeCenterRes.UserDealCapitalMessageBean data) {
        if (data == null) return;
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
                RxBus.getDefault().post(new ExchangeEvent());
                break;
            case EXCHANGE_PWD_TAG:
                toast("记住密码设置成功");
                if (dialogUtils != null) dialogUtils.dismiss();
                break;
        }
    }
}
