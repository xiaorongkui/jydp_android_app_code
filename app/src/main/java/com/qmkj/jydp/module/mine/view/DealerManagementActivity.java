package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.module.mine.presenter.DealerManagmentRecyAdapter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:经销商管理
 */

public class DealerManagementActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.dealer_managment_rv)
    RecyclerView dealerManagmentRv;
    @BindView(R.id.dealer_publish_advertise_bt)
    Button dealerPublishAdvertiseBt;
    private ArrayList<Object> mData;
    private DealerManagmentRecyAdapter dealerManagmentRecyAdapter;
    private CommonDialog dialogUtils;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.dealer_managment));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_dealer_managment;
    }

    @Override
    protected void initView() {
        dealerPublishAdvertiseBt.setOnClickListener(this);
        initRecycleView();
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        mData.add("123");
        dealerManagmentRecyAdapter = new DealerManagmentRecyAdapter(mContext, mData, R.layout
                .mine_dealer_managment_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dealerManagmentRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        dealerManagmentRecyAdapter.setEmptyView(mEmptyView);
        dealerManagmentRv.setAdapter(dealerManagmentRecyAdapter);
        dealerManagmentRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> showDeleteDialog());
    }

    private void showDeleteDialog() {
        dialogUtils = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .common_dialog_1);
        dialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        dialogUtils.setOneOrTwoBtn(false);
        dialogUtils.setTitle(CommonUtil.getString(R.string.delete_operation));
        dialogUtils.setMessage(getString(R.string.deletions));
        dialogUtils.setTwoCancelBtn("取消", v -> {
            LogUtil.i("取消");
            //todo
        });
        dialogUtils.setTwoConfirmBtn("确认", v -> dialogUtils.dismiss());
        dialogUtils.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dialogUtils != null && dialogUtils.isShowing()) {
            dialogUtils.dismiss();
            dialogUtils = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dealer_publish_advertise_bt:
                CommonUtil.gotoActivity(mContext, PublishAdvertisementActivity.class);
                break;
        }
    }
}
