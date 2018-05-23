package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.response.PresentRecordRes;
import com.qmkj.jydp.module.mine.presenter.CurrencyWithDrawRecodeRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.utrlrefresh.XRefreshLayout;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/11
 * email：dovexiaoen@163.com
 * description:币种提现记录
 */
public class CurrencyWithDrawRecodeActivity extends BaseMvpActivity<MinePresenter> {
    @BindView(R.id.mine_currency_withdraw_recode_rl)
    XRefreshLayout mRefreshLayout;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.mine_currency_withdraw_recode_rv)
    RecyclerView mineCurrencyWithdrawRecodeRv;

    private ArrayList<PresentRecordRes.CoinOutRecordListBean> mData = new ArrayList<>();
    private CurrencyWithDrawRecodeRecyAdapter currencyWithDrawRecodeRecyAdapter;
    private int mPage;
    boolean mIsCanRefresh = true;
    boolean mIsLoadMore;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        mRefreshLayout.callRefresh();
    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.currency_withdraw_recode));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_currency_withdraw_recode;
    }

    @Override
    protected void initView() {
        initRecycleView();
        initListener();
    }

    private void initRecycleView() {
        currencyWithDrawRecodeRecyAdapter = new CurrencyWithDrawRecodeRecyAdapter(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mineCurrencyWithdrawRecodeRv.setLayoutManager(layoutManager);

        View mEmptyView = View.inflate(mContext, R.layout.empty, null);
        currencyWithDrawRecodeRecyAdapter.setEmptyView(mEmptyView);
        mineCurrencyWithdrawRecodeRv.setAdapter(currencyWithDrawRecodeRecyAdapter);
    }

    private void initListener() {
        mRefreshLayout.setOnRefreshListener(new XRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mIsLoadMore = false;
                mPage = 0;
                getDateFromNet();
            }

            @Override
            public boolean checkCanDoRefresh(View content, View header) {
                return mIsCanRefresh;
            }
        });

        mineCurrencyWithdrawRecodeRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int topRowVerticalPosition =
                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                mIsCanRefresh = topRowVerticalPosition >= 0;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        currencyWithDrawRecodeRecyAdapter.setOnLoadMoreListener(() -> {
            mIsLoadMore = true;
            getDateFromNet();
        }, mineCurrencyWithdrawRecodeRv);
    }

    /**
     * 获取网络数据
     */
    private void getDateFromNet() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(mPage);
        presenter.getPresentRecordInfo(req, 1, false);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        mRefreshLayout.refreshComplete();
        PresentRecordRes recordRes = (PresentRecordRes)response;
        if (mIsLoadMore) {
            currencyWithDrawRecodeRecyAdapter.addData(recordRes.getCoinOutRecordList());
        } else {
            currencyWithDrawRecodeRecyAdapter.update(recordRes.getCoinOutRecordList());
        }
        if (mPage < recordRes.getTotalPageNumber() - 1) {
            currencyWithDrawRecodeRecyAdapter.loadMoreComplete();
            mPage++;
        } else {
            currencyWithDrawRecodeRecyAdapter.loadMoreEnd();
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object response) {
        super.onError(errorMsg, code, tag, response);
        mRefreshLayout.refreshComplete();
    }
}
