package com.qmkj.jydp.module.mine.view;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.ShadowLayout;
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
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author：rongkui.xiao --2018/5/8
 * email：dovexiaoen@163.com
 * description:修改支付密码
 */

public class ModifyPaymentActivity extends BaseMvpActivity<LoginPresenter> {
    private static final int GET_CODE_TAG = 1;
    private static final int REQUEST_BY_PWD = 2;
    private static final int REQUEST_BY_PHONE = 3;
    private static final int splashTotalCountdownTime = 60;

    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.modify_pwd_old_pwd_title_tv) //原密码修改
            TextView modifyPwdOldPwdTitleTv;
    @BindView(R.id.modify_pwd_old_pwd_title_line)
    View modifyPwdOldPwdTitleLine;
    @BindView(R.id.modify_pwd_old_pwd_title_ll)
    LinearLayout modifyPwdOldPwdTitleLl;
    @BindView(R.id.modify_pwd_phone_title_tv) //通过手机号修改
            TextView modifyPwdPhoneTitleTv;
    @BindView(R.id.modify_pwd_phone_title_line)
    View modifyPwdPhoneTitleLine;
    @BindView(R.id.modify_pwd_phone_title_ll)
    LinearLayout modifyPwdPhoneTitleLl;
    @BindView(R.id.modify_original_pwd_ll)
    LinearLayout modifyOriginalPwdLl;
    @BindView(R.id.modify_phone_pwd_ll)
    LinearLayout modifyPhonePwdLl;
    @BindView(R.id.modify_code_layout)
    ShadowLayout modify_code_layout;

    @BindView(R.id.user_phone_num_tv)  //手机号码
            TextView userPhoneNumTv;
    @BindView(R.id.login_forget_pwd_vertification_code_eiv) //获取验证码
            EditVItemView login_forget_pwd_vertification_code_eiv;

    @BindView(R.id.modify_pwd_old_pwd_one_eiv) //原密码
            EditVItemView modify_pwd_old_pwd_one_eiv;
    @BindView(R.id.modify_pwd_new_pwd_one_eiv) //新密码
            EditVItemView modify_pwd_new_pwd_one_eiv;
    @BindView(R.id.modify_pwd_login_pwd_one_eiv) //重复密码
            EditVItemView modify_pwd_login_pwd_one_eiv;

    @BindView(R.id.modify_phone_pwd_one_eiv) //新密码(手机修改)
            EditVItemView modify_phone_pwd_one_eiv;
    @BindView(R.id.modify_phone_pwd_again_eiv) //重复密码(手机修改)
            EditVItemView modify_phone_pwd_again_eiv;

    @BindView(R.id.modify_pwd_bt) //提 交
            Button modify_pwd_bt;
    private TextView codeTimeDownTv;
    private Disposable disposable;

    private int SELECT_TYPE = 0;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
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
        titleHeaderTv.setText(ResourcesManager.getString(R.string.modify_payment_pwd));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_modify_payment_password;
    }

    @Override
    protected void initView() {
        login_forget_pwd_vertification_code_eiv.setEditTextInputType(InputType.TYPE_CLASS_NUMBER);
        login_forget_pwd_vertification_code_eiv.setEditTextMaxLength(6);

        modifyPwdOldPwdTitleLl.setOnClickListener(this);
        modifyPwdPhoneTitleLl.setOnClickListener(this);
        modify_pwd_bt.setOnClickListener(this);
        setSelect(0);

        codeTimeDownTv = login_forget_pwd_vertification_code_eiv.getView(R.id.edit_right_tv);
        codeTimeDownTv.setText(ResourcesManager.getString(R.string.get_rigister_getvertify_code_1));
        codeTimeDownTv.setOnClickListener(v -> {
            SystemManager.hideInputWindow(mContext);
            getVerificationCode();
        });
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        String phone = DataManager.getLoginInfo().getUser().getUserPhone();
        String phoneAreaCode = DataManager.getLoginInfo().getUser().getPhoneAreaCode();
        if (phone.isEmpty()) {
            toast("手机号不能为空");
            return;
        }
        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhoneNumber(phoneAreaCode + phone);
        presenter.getRegisterCode(phoneCodeReq, GET_CODE_TAG);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.modify_pwd_old_pwd_title_ll:
                setSelect(0);  //通过原密码修改
                break;
            case R.id.modify_pwd_phone_title_ll:
                setSelect(1); //通过手机修改
                break;
            case R.id.modify_pwd_bt:
                sendRequest(); //发送修改请求
                break;
        }
    }

    private void sendRequest() {
        //通过原密码修改
        if (SELECT_TYPE == 0) {
            String oldPass = modify_pwd_old_pwd_one_eiv.getEditTextString();
            String newPass = modify_pwd_new_pwd_one_eiv.getEditTextString();
            String newPassAgain = modify_pwd_login_pwd_one_eiv.getEditTextString();
            if (TextUtils.isEmpty(oldPass)) {
                toast("原支付密码不能为空");
                return;
            }
            if (!CheckTextUtil.checkPassword(oldPass)) {
                toast("支付密码必须是字母、数字，6～16个字符");
                return;
            }

            if (checkNewPass(newPass, newPassAgain)) return;
            ChangePassWordReq req = new ChangePassWordReq();
            req.setOldPassword(oldPass);
            req.setNewPassword(newPass);
            req.setConfirmPassword(newPassAgain);
            presenter.changePassWordByPwd(req, REQUEST_BY_PWD, true);
        } else if (SELECT_TYPE == 1) {
            //通过手机号修改
            String newPass = modify_phone_pwd_one_eiv.getEditTextString();
            String newPassAgain = modify_phone_pwd_again_eiv.getEditTextString();
            String code = login_forget_pwd_vertification_code_eiv.getEditTextString();
            if (checkNewPass(newPass, newPassAgain)) return;

            String codeText = CheckTextUtil.checkCode(code);
            if (codeText != null) {
                toast(codeText);
                return;
            }
            ChangePassWordReq req = new ChangePassWordReq();
            req.setNewPassword(newPass);
            req.setConfirmPassword(newPassAgain);
            req.setValidCode(code);
            presenter.changePassWordByPhone(req, REQUEST_BY_PHONE, true);
        }

    }


    /**
     * 参数验证
     *
     * @param newPass      新支付密码
     * @param newPassAgain 重复新支付密码
     */
    private boolean checkNewPass(String newPass, String newPassAgain) {
        if (TextUtils.isEmpty(newPass)) {
            toast("新密码不能为空");
            return true;
        }
        if (TextUtils.isEmpty(newPassAgain)) {
            toast("重复密码不能为空");
            return true;
        }
        if (!CheckTextUtil.checkPassword(newPass) ||
                !CheckTextUtil.checkPassword(newPassAgain)
                ) {
            toast("支付密码必须是字母、数字，6～16个字符");
            return true;
        }
        if (!newPass.equals(newPassAgain)) {
            toast("两次支付密码输入不同");
            return true;
        }
        return false;
    }

    /**
     * 选择修改方式
     *
     * @param index
     */
    private void setSelect(int index) {
        switch (index) {
            case 0:
                modifyPwdOldPwdTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_bule_5));
                modifyPwdPhoneTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_black_10));
                modifyPwdPhoneTitleLine.setVisibility(View.INVISIBLE);
                modifyPwdOldPwdTitleLine.setVisibility(View.VISIBLE);
                modifyOriginalPwdLl.setVisibility(View.VISIBLE);
                modifyPhonePwdLl.setVisibility(View.GONE);
                modify_code_layout.setVisibility(View.GONE);
                SELECT_TYPE = 0;
                break;
            case 1:
                modifyPwdOldPwdTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_black_10));
                modifyPwdPhoneTitleTv.setTextColor(ResourcesManager.getColor(R.color.color_bule_5));
                modifyPwdPhoneTitleLine.setVisibility(View.VISIBLE);
                modifyPwdOldPwdTitleLine.setVisibility(View.INVISIBLE);
                modifyOriginalPwdLl.setVisibility(View.GONE);
                modifyPhonePwdLl.setVisibility(View.VISIBLE);
                modify_code_layout.setVisibility(View.VISIBLE);
                SELECT_TYPE = 1;
                break;
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case GET_CODE_TAG:
                toast("验证码获取成功");
                codeTimeDown();
                break;
            case REQUEST_BY_PWD:
            case REQUEST_BY_PHONE:
                toast("修改成功");
                ActivityManager.gotoActivity(mContext, PersonInfoActivity.class);
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

}
