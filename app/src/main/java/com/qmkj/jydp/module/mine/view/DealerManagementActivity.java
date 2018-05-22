package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.response.DealerManagementRes;
import com.qmkj.jydp.module.mine.presenter.DealerManagementRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:经销商管理
 */

public class DealerManagementActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private ArrayList<DealerManagementRes.OtcTransactionPendOrderListBean> mData;
    private DealerManagementRecyAdapter dealerManagementRecyAdapter;
    private CommonDialog dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = getBottomButton();
        button.setVisibility(View.VISIBLE);
        button.setText(CommonUtil.getString(R.string.send_advertisement));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonUtil.gotoActivity(mContext, PublishAdvertisementActivity.class);
            }
        });
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        presenter.getDealerManagementInfo(1,true);
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return dealerManagementRecyAdapter;
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        dealerManagementRecyAdapter = new DealerManagementRecyAdapter(mContext, mData, R.layout
                .mine_dealer_managment_item);
        dealerManagementRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> showDeleteDialog());
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
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        DealerManagementRes res = (DealerManagementRes) response;
        if(res.getOtcTransactionPendOrderList()!=null){
            dealerManagementRecyAdapter.addData(res.getOtcTransactionPendOrderList());
            dealerManagementRecyAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.dealer_managment);
    }
}
