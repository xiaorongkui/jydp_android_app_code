package com.qmkj.jydp.module.mine;

import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.bean.request.UserWithdrawReq;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.bean.response.UserCoinWithdrawInfo;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.ui.widget.dialog.UserWithdrawChooseCurrencyDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.MyTextWatcher;
import com.qmkj.jydp.util.NumberUtil;
import com.qmkj.jydp.util.StringUtil;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 用户提链
 */
public class ChainWithdrawActivity extends BaseMvpActivity<MinePresenter> {
    private static final int splashTotalCountdownTime = 60;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.can_withdraw_num_tv)
    TextView canWithdrawNumTv;
    @BindView(R.id.choose_currency_cv)
    ClickItemView chooseCurrencyCv;
    @BindView(R.id.withdraw_num_ev)
    EditVItemView withdrawNumEv;
    @BindView(R.id.withdraw_num_notice_tv)
    TextView withdrawNumNoticeTv;
    @BindView(R.id.verification_code_ev)
    EditVItemView verificationCodeEv;
    @BindView(R.id.verification_code_notice_tv)
    TextView verificationCodeNoticeTv;
    @BindView(R.id.password_ev)
    EditVItemView passwordEv;
    @BindView(R.id.withdraw_btn)
    Button withdrawBtn;
    private TextView codeTimeDownTv;
    private UserCoinWithdrawInfo userCoinWithdrawInfo;
    private UserCoinWithdrawInfo.CoinWithdrawInfo chooseInfo;
    private Disposable disposable;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.chain_withdraw));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.color_bule_2));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chain_withdraw;
    }

    @Override
    protected void initView() {

        withdrawNumEv.setEditTextTextWatch(new MyTextWatcher(withdrawNumEv.getEditTextView(), 4));

        LoginRes.UserBean userBean = CommonUtil.getLoginInfo().getUser();
        verificationCodeNoticeTv.setText("将向手机" + userBean.getPhoneAreaCode() + " " + StringUtil.formatPhoneNum(userBean.getUserPhone()) + "发送一条短信验证码");
        codeTimeDownTv = verificationCodeEv.getView(R.id.edit_right_tv);
        chooseCurrencyCv.setOnClickListener(v -> {
            UserWithdrawChooseCurrencyDialog userWithdrawChooseCurrencyDialog = new UserWithdrawChooseCurrencyDialog(mContext, userCoinWithdrawInfo.getUserCoinConfigList());
            userWithdrawChooseCurrencyDialog.setOnChooseCurrencyListener(bean -> {
                chooseInfo = bean;
                canWithdrawNumTv.setText(NumberUtil.doubleFormat(Double.parseDouble(bean.getCurrencyNumber()+""),4) + "");
                chooseCurrencyCv.setRightText(bean.getCurrencyName());
                withdrawNumNoticeTv.setText("当前链最低提现" + bean.getMinCurrencyNumber() + "个，超过" + bean.getFreeCurrencyNumber() + "需人工审核");
            });
            userWithdrawChooseCurrencyDialog.show();
        });
        codeTimeDownTv.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode();
        });
        withdrawBtn.setOnClickListener(v -> withdrawNow());
    }

    @Override
    protected void initData() {
        getCurrencyInfoFromNet();
    }

    /**
     * 获取币种信息
     */
    private void getCurrencyInfoFromNet() {
        presenter.getUserCoinWithdrawalInfo(1, true);
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        String phone = CommonUtil.getLoginInfo().getUser().getUserPhone();
        String phoneAreaCode = CommonUtil.getLoginInfo().getUser().getPhoneAreaCode();
        if (phone.isEmpty()) {
            toast("手机号不能为空");
            return;
        }
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode + phone);
        presenter.getRegisterCode(phoneCodeReq, 2, true);
    }

    /**
     * 立即提币
     */
    private void withdrawNow() {
        if (chooseInfo == null) {
            toast("请先选择链");
            return;
        }
        String number =withdrawNumEv.getEditTextString();
        String buyPwd = passwordEv.getEditTextString();
        int currencyId = chooseInfo.getCurrencyId();
        String validateCode = verificationCodeEv.getEditTextString();
        //校验参数
        if (currencyId == 0) {
            toast("请先选择链");
            return;
        }
        if(StringUtil.isNull(number)){
            toast("提链数量不能为空");
            return;
        }

        if (Double.valueOf(number) < chooseInfo.getMinCurrencyNumber()) {
            toast("数量不能小于最低数量");
            return;
        }

        String codeText = CommonUtil.checkCode(validateCode);
        if (!TextUtils.isEmpty(codeText)) {
            toast(codeText);
            return;
        }

        if (buyPwd.isEmpty()) {
            toast("密码不能为空");
            return;
        }


        UserWithdrawReq req = new UserWithdrawReq();
        req.setBuyPwd(buyPwd);
        req.setCurrencyId(String.valueOf(currencyId));
        req.setNumber(String.valueOf(number));
        req.setValidateCode(validateCode);
        presenter.userWithdraw(req, 3, true);
    }

    /**
     * 清除用户输入
     */
    private void clearInput() {
        passwordEv.setEditTextView("");
        withdrawNumEv.setEditTextView("");
        verificationCodeEv.setEditTextView("");
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case 1:
                userCoinWithdrawInfo = (UserCoinWithdrawInfo) response;
                if(userCoinWithdrawInfo.getUserCoinConfigList()!=null){
                    chooseInfo = userCoinWithdrawInfo.getUserCoinConfigList().get(0);
                    canWithdrawNumTv.setText(BigDecimal.valueOf(chooseInfo.getCurrencyNumber()) + "");
                    chooseCurrencyCv.setRightText(chooseInfo.getCurrencyName());
                    withdrawNumNoticeTv.setText("当前链最低提现" + chooseInfo.getMinCurrencyNumber() + "个，超过" + chooseInfo.getFreeCurrencyNumber() + "需人工审核");

                }
                break;
            case 2:
                toast("验证码获取成功");
                codeTimeDown();
                break;
            case 3:
                toast("请求成功");
                clearInput();
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        switch (tag) {
            case 1:
                finish();
                break;
        }
    }

    /**
     * 获取验证码倒计时
     */
    private void codeTimeDown() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime - aLong.intValue()).take
                (splashTotalCountdownTime + 1).subscribe(integer -> {
            if (integer == 0) {
                codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
                codeTimeDownTv.setEnabled(true);
                codeTimeDownTv.setAlpha(1);
            } else {
                codeTimeDownTv.setText(String.format(CommonUtil.getString(R.string.get_rigister_getvertify_code),
                        integer));
                codeTimeDownTv.setEnabled(false);
                codeTimeDownTv.setAlpha(0.5f);
            }
        });
    }
}
