package com.qmkj.jydp.module.exchange.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.MvpBaseActivity;
import com.qmkj.jydp.ui.widget.DialogUtils;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.SelectorFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:
 */

public class EntrustRecodeActivity extends MvpBaseActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.entrust_recode_sold_detail_tv)
    TextView entrustRecodeSoldDetailTv;
    @BindView(R.id.entrust_recode_buy_cancel_tv)
    TextView entrustRecodeBuyCancelTv;
    @BindView(R.id.entrust_recode_buy_detail_tv)
    TextView entrustRecodeBuyDetailTv;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.exchange_entrust_recod));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.exchange_activity_entrust_recode;
    }

    @Override
    protected void initView() {
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x20))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x2))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.colorBlack_7));
        entrustRecodeSoldDetailTv.setBackground(shapeSelector.create());
        entrustRecodeBuyDetailTv.setBackground(shapeSelector.create());
        entrustRecodeBuyCancelTv.setBackground(shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color
                .colorGreen_3)).create());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.entrust_recode_sold_detail_tv, R.id.entrust_recode_buy_cancel_tv, R.id.entrust_recode_buy_detail_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.entrust_recode_sold_detail_tv:
                break;
            case R.id.entrust_recode_buy_cancel_tv:
                showCancelDialog();
                break;
            case R.id.entrust_recode_buy_detail_tv:
                break;
        }
    }

    private void showCancelDialog() {
        DialogUtils dialogUtils = new DialogUtils(mContext, R.style.common_dialog, R.layout
                .exchange_revocation_entrust_dialog);
        dialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x500));
        dialogUtils.setOneOrTwoBtn(false);
        dialogUtils.setTwoConfirmBtn("确认撤销", v -> {
            LogUtil.i("确认撤销");
            //todo
        });
        dialogUtils.setTwoConfirmBtn("取消撤销", v -> dialogUtils.dismiss());
        dialogUtils.setMessage("将撤销此次挂单委托？");
        dialogUtils.show();
    }
}
