package com.qmkj.jydp.module.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertifyNameFragment extends MvpBaseFragment implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.certify_add_front_rl)
    RelativeLayout certifyAddFrontRl;
    @BindView(R.id.certify_add_back_rl)
    RelativeLayout certifyAddBackRl;
    @BindView(R.id.ertify_account_ll)
    View ertify_account_ll;
    @BindView(R.id.ertify_name_ll)
    View ertify_name_ll;
    @BindView(R.id.ertify_type_cv)
    ClickItemView ertify_type_cv;
    @BindView(R.id.ertify_number_ll)
    View ertify_number_ll;
    private EditText account_et;
    private EditText name_et;
    private EditText certify_num_et;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        initItemView();
        ertify_type_cv.setOnClickListener(this);
        certifyAddFrontRl.setOnClickListener(this);
        certifyAddBackRl.setOnClickListener(this);
    }

    private void selectCertifyType() {//选择证件类型

    }

    private void initItemView() {
        ((TextView) ertify_account_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.account));
        account_et = (EditText) ertify_account_ll.findViewById(R.id.common_edit_line_content_et);
        account_et.setCursorVisible(false);
        account_et.setOnClickListener(v -> account_et.setCursorVisible(true));

        ((TextView) ertify_name_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.name));
        name_et = (EditText) ertify_name_ll.findViewById(R.id.common_edit_line_content_et);
        name_et.setHint(CommonUtil.getString(R.string.your_real_name));

        ((TextView) ertify_number_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.certificate_num));
        certify_num_et = (EditText) ertify_number_ll.findViewById(R.id.common_edit_line_content_et);
        certify_num_et.setHint(CommonUtil.getString(R.string.input_your_certificate_num));
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_certify_name;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certify_add_front_rl://添加正面照

                break;
            case R.id.certify_add_back_rl://添加反面照

                break;
            case R.id.ertify_type_cv://选择证件类型
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                break;
        }
    }
}
