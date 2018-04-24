package com.qmkj.jydp.module.exchangecenter.view;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.module.exchangecenter.presenter.ExchangeCenterPresenter;

/**
 * @author wujiangming
 * @date 2018/4/24
 * @desc
 */

public class ExchangeKlineFrament extends BaseMvpFragment<ExchangeCenterPresenter> {
    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_exchange_center;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
    }

    @Override
    public void onError(String errorMsg, String code, int tag) {
        super.onError(errorMsg, code, tag);
    }
}
