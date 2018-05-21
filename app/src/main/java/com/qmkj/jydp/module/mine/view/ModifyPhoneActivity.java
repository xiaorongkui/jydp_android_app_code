package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.ChangePhoneReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:修改手机号
 */

public class ModifyPhoneActivity extends BaseMvpActivity<LoginPresenter> {
    private static final int GET_CODE_TAG_1 = 1;
    private static final int GET_CODE_TAG_2 = 2;
    private static final int SEND_REQUEST = 3;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.modify_phone_erea_tv)
    TextView modify_phone_erea_tv;

    @BindView(R.id.modify_phone_erea_et)
    EditText modify_phone_erea_et;

    @BindView(R.id.modify_phone_old_num_civ)
    ClickItemView modify_phone_old_num_civ;
    @BindView(R.id.modify_phone_submit_bt)
    Button modify_phone_submit_bt;
    @BindView(R.id.modify_phone_verification_code_civ)
    EditVItemView modify_phone_verification_code_civ;
    private TextView codeTimeDownTv;
    private TextView codeTimeDownTv_new;
    @BindView(R.id.modify_phone_verification_code_eiv)
    EditVItemView modify_phone_verification_code_eiv;
    @BindView(R.id.modify_phone_password_one_eiv)
    EditVItemView modify_phone_password_one_eiv;
    private int splashTotalCountdownTime;
    private int splashTotalCountdownTime_new;
    private Disposable disposable;
    private Disposable disposable_new;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        if(CommonUtil.getLoginInfo()!=null&&CommonUtil.getLoginInfo().getUser()!=null){
            modify_phone_old_num_civ.setRightText(CommonUtil.getLoginInfo().getUser().getPhoneAreaCode()+CommonUtil.getLoginInfo().getUser().getUserPhone()+"");
        }
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.modify_phone_num));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_phone_num;
    }

    @Override
    protected void initView() {
        codeTimeDownTv = modify_phone_verification_code_civ.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode();
        });

        codeTimeDownTv_new = modify_phone_verification_code_eiv.getView(R.id.edit_right_tv);
        codeTimeDownTv_new.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv_new.setOnClickListener(v -> {
            CommonUtil.hideInputWindow(mContext);
            getVerificationCode2();
        });


    }


    private void codeTimeDown() {
        disposable_new = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
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
    private void codeTimeDown_new() {
        disposable_new = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime_new - aLong.intValue()).take
                (splashTotalCountdownTime_new + 1).subscribe(integer -> {

            if (integer == 0) {
                codeTimeDownTv_new.setText(CommonUtil.getString(R.string.get_rigister_getvertify_code_1));
                codeTimeDownTv_new.setEnabled(true);
                codeTimeDownTv_new.setAlpha(1);
            } else {
                codeTimeDownTv_new.setText(String.format(CommonUtil.getString(R.string.get_rigister_getvertify_code),
                        integer));
                codeTimeDownTv_new.setEnabled(false);
                codeTimeDownTv_new.setAlpha(0.5f);
            }
        });
    }


    @OnClick(R.id.modify_phone_submit_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_phone_submit_bt:
                changePhone();
                break;
        }
    }

    private void changePhone() {
        String code_old = modify_phone_verification_code_civ.getEditTextString();
        String code_new = modify_phone_verification_code_eiv.getEditTextString();
        String phone =modify_phone_erea_et.getText().toString();
        String phoneAreaCode =modify_phone_erea_tv.getText().toString();
        String passWord =modify_phone_password_one_eiv.getEditTextString();

        if (TextUtils.isEmpty(code_old)) {
            toast("验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            toast("新的手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(code_new)) {
            toast("新的手机验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            toast("登录密码不能为空");
            return;
        }

        if (code_old.length() != 6||code_new.length() != 6) {
            toast("验证码必须为六位");
            return;
        }
        ChangePhoneReq changePhoneReq = new ChangePhoneReq();
        changePhoneReq.setOldValidCode(code_old);
        changePhoneReq.setNewValidCode(code_new);
        changePhoneReq.setAreaCode(phoneAreaCode);
        changePhoneReq.setPhone(phone);
        changePhoneReq.setPassword(passWord);

        presenter.changePhone(changePhoneReq,SEND_REQUEST,true);

    }

    private void getVerificationCode() {
        String phone = CommonUtil.getLoginInfo().getUser().getUserPhone();
        String phoneAreaCode = CommonUtil.getLoginInfo().getUser().getPhoneAreaCode();
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode+phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG_1);
    }

    public void getVerificationCode2() {
        String phone =modify_phone_erea_et.getText().toString();
        String phoneAreaCode =modify_phone_erea_tv.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            toast("新的手机号码不能为空");
            return;
        }
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode+phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG_2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag){
            case GET_CODE_TAG_1:
                toast("验证码获取成功");
                codeTimeDown();
                break;
            case GET_CODE_TAG_2:
                toast("验证码获取成功");
                codeTimeDown_new();
                break;
            case SEND_REQUEST:
                toast("修改成功");
                CommonUtil.gotoActivity(mContext,PersonInfoActivity.class);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
        if (disposable_new != null) {
            disposable_new.dispose();
            disposable_new = null;
        }
    }
}
