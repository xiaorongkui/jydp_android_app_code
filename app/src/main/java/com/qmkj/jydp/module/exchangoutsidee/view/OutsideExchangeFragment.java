package com.qmkj.jydp.module.exchangoutsidee.view;

import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangeAdapter;
import com.qmkj.jydp.module.exchangoutsidee.presenter.OutsideExchangePresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author wujiangming
 * @date 2018/4/23
 * @desc
 */

public class OutsideExchangeFragment extends BaseMvpFragment<OutsideExchangePresenter> {
    @BindView(R.id.title_ll)
    LinearLayout title_ll;
    @BindView(R.id.refresh)
    XRefreshLayout refresh;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    OutsideExchangeAdapter mOutsideExchangeAdapter;
    ArrayList<String> mData;

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initStatusBar();
        initRefresh();
        initRecyclerView();
        initClick();
    }

    private void initRefresh() {
        refresh.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return true;
            }
        });
    }

    private void initStatusBar() {
        //状态栏占用的兼容性
        if (Build.VERSION.SDK_INT >= 21) {
            View statusView = new View(getActivity());
            statusView.setBackgroundColor(CommonUtil.getColor(R.color.status_bar_color));
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight());
            title_ll.addView(statusView, 0, lp);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(mContext);
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutmanager);
        mData = new ArrayList<>();
        mData.add("123");
        mData.add("123");
        mOutsideExchangeAdapter = new OutsideExchangeAdapter(R.layout.exchange_outside_item, mData);
        View mEmptyView = View.inflate(getContext(), R.layout.empty, null);
        mOutsideExchangeAdapter.setEmptyView(mEmptyView);
        recyclerView.setAdapter(mOutsideExchangeAdapter);
    }

    public void initClick() {
        mOutsideExchangeAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            LogUtil.i("view=" + view.getId() + "position=" + position);
            switch (view.getId()) {
                case R.id.exchange_outside_go_exchange_tv:
                    toast("去交易");
                    CommonUtil.gotoActivity(mContext, OutSideSoldActivity.class);
                    break;
                case R.id.exchange_outside_buy_tv:
                    CommonUtil.gotoActivity(mContext, OutSideBuyActivity.class);
                    break;

                default:
                    break;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.outside_exchange_fragment;
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
