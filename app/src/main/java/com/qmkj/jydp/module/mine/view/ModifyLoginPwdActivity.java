package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.ChangePassWordReq;
import com.qmkj.jydp.bean.request.PhoneCodeReq;
import com.qmkj.jydp.manager.ActivityManager;
import com.qmkj.jydp.manager.DataManager;
import com.qmkj.jydp.manager.ResourcesManager;
import com.qmkj.jydp.manager.SystemManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.CheckTextUtil;

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
 * description:修改登录密码
 */

public class ModifyLoginPwdActivity extends BaseMvpActivity<LoginPresenter> {

    private static final int GET_CODE_TAG = 1;
    private static final int SEND_REQUEST_TAG = 2;
    private static final int splashTotalCountdownTime = 60;

    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.user_phone_num_tv)
    TextView userPhoneNumTv;
    @BindView(R.id.modify_old_login_pwd_eiv) //原密码
            EditVItemView modify_old_login_pwd_eiv;
    @BindView(R.id.modify_new_login_pwd_eiv) //新密码
            EditVItemView modify_new_login_pwd_eiv;
    @BindView(R.id.modify_new_login_pwd_again_eiv) //重复密码
            EditVItemView modify_new_login_pwd_again_eiv;
    @BindView(R.id.modify_login_pwd_verification_code_civ) //获取验证码
            EditVItemView modify_login_pwd_verification_code_civ;
    @BindView(R.id.modify_login_pwd_submit_bt)  //提 交
            Button modify_login_pwd_submit_bt;

    private TextView codeTimeDownTv;
    private Disposable disposable;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        if (DataManager.getLoginInfo() != null && DataManager.getLoginInfo().getUser() != null) {
            userPhoneNumTv.setText(String.format("%s %s", DataManager.getLoginInfo().getUser().getPhoneAreaCode(),
                    DataManager.getLoginInfo().getUser().getUserPhone()));
        }
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(ResourcesManager.getString(R.string.modify_password));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_login_password;
    }

    @Override
    protected void initView() {
        modify_login_pwd_verification_code_civ.setEditTextInputType(InputType.TYPE_CLASS_NUMBER);
        modify_login_pwd_verification_code_civ.setEditTextMaxLength(6);
        codeTimeDownTv = modify_login_pwd_verification_code_civ.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(ResourcesManager.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            SystemManager.hideInputWindow(mContext);
            getVerificationCode();
        });
    }

    /**
     * 发送获取验证码请求
     */
    private void getVerificationCode() {
        String phone = DataManager.getLoginInfo().getUser().getUserPhone();
        String phoneAreaCode = DataManager.getLoginInfo().getUser().getPhoneAreaCode();
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode + phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG);
    }


    @OnClick(R.id.modify_login_pwd_submit_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_login_pwd_submit_bt:
                changeRequest();
                break;
        }
    }

    /**
     * 发送更改密码请求
     */
    private void changeRequest() {
        String oldPass = modify_old_login_pwd_eiv.getEditTextString();
        String newPass = modify_new_login_pwd_eiv.getEditTextString();
        String newPassAgain = modify_new_login_pwd_again_eiv.getEditTextString();
        String code = modify_login_pwd_verification_code_civ.getEditTextString();

        if (TextUtils.isEmpty(oldPass)) {
            toast("原密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(newPass)) {
            toast("新密码不能为空");
            return;
        }
        if (TextUtils.isEmpty(newPassAgain)) {
            toast("重复密码不能为空");
            return;
        }

        if (!CheckTextUtil.checkPassword(oldPass) ||
                !CheckTextUtil.checkPassword(newPass) ||
                !CheckTextUtil.checkPassword(newPassAgain)
                ) {
            toast("登录密码必须是字母、数字，6～16个字符");
            return;
        }
        if (!newPass.equals(newPassAgain)) {
            toast("两次登录密码输入不同");
            return;
        }

        String codeText = CheckTextUtil.checkCode(code);
        if (codeText != null) {
            toast(codeText);
            return;
        }
        ChangePassWordReq req = new ChangePassWordReq();
        req.setOldPassword(oldPass);
        req.setNewPassword(newPass);
        req.setConfirmPassword(newPassAgain);
        req.setValidCode(code);
        presenter.changePassWord(req, SEND_REQUEST_TAG, true);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case GET_CODE_TAG:
                toast("验证码获取成功");
                codeTimeDown();
                break;
            case SEND_REQUEST_TAG:
                toast("修改成功");
                ActivityManager.gotoActivity(mContext, PersonInfoActivity.class);
                break;
        }
    }

    /**
     * 点击了获取验证码
     */
    private void codeTimeDown() {
        disposable = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.io()).observeOn
                (AndroidSchedulers.mainThread()).map(aLong -> splashTotalCountdownTime - aLong.intValue()).take
                (splashTotalCountdownTime + 1).subscribe(integer -> {
            if (integer == 0) {
                codeTimeDownTv.setText(ResourcesManager.getString(R.string.get_rigister_getvertify_code_1));
                codeTimeDownTv.setEnabled(true);
                codeTimeDownTv.setAlpha(1);
            } else {
                codeTimeDownTv.setText(String.format(ResourcesManager.getString(R.string.get_rigister_getvertify_code),
                        integer));
                codeTimeDownTv.setEnabled(false);
                codeTimeDownTv.setAlpha(0.5f);
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
