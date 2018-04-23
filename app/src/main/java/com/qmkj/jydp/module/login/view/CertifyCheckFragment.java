package com.qmkj.jydp.module.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.ui.widget.ClickItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证审核中
 */

public class CertifyCheckFragment extends BaseMvpFragment implements View.OnClickListener {
    @BindView(R.id.certify_check_front_rl)
    RelativeLayout certifyCheckFrontRl;
    @BindView(R.id.certify_check_back_rl)
    RelativeLayout certifyCheckBackRl;

    Unbinder unbinder;
    @BindView(R.id.ertify_check_status_cv)
    ClickItemView ertifyCheckStatusCv;
    @BindView(R.id.ertify_check_account_cv)
    ClickItemView ertifyCheckAccountCv;
    @BindView(R.id.ertify_check_name_cv)
    ClickItemView ertifyCheckNameCv;
    @BindView(R.id.ertify_check_type_cv)
    ClickItemView ertifyCheckTypeCv;
    @BindView(R.id.ertify_check_number_cv)
    ClickItemView ertifyCheckNumberCv;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        ertifyCheckStatusCv.setOnClickListener(this);
        ertifyCheckStatusCv.setFocusable(true);
    }


    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.login_fragment_certify_check;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

    }
}
