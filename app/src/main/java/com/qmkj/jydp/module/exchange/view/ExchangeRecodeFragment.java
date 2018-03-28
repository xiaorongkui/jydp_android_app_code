package com.qmkj.jydp.module.exchange.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.module.exchange.presenter.ExchangeRecodeRecAdapter;
import com.qmkj.jydp.ui.widget.FullyLinearLayoutManager;
import com.qmkj.jydp.ui.widget.MyRecycleView;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/27
 * email：dovexiaoen@163.com
 * description:五条资金记录小页面
 */

public class ExchangeRecodeFragment extends MvpBaseFragment {

    @BindView(R.id.entrust_recode_rv)
    RecyclerView entrustRecodeRv;

    public static ExchangeRecodeFragment newInstance(int index) {
        Bundle args = new Bundle();
        ExchangeRecodeFragment fragment = new ExchangeRecodeFragment();
        args.putInt(Constants.INTENT_PARAMETER_1, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        initRecycleView();
    }

    private void initRecycleView() {
        List datas = new ArrayList();
        for (int i = 0; i < 5; i++) {
            datas.add("");

        }
        ExchangeRecodeRecAdapter recodeRecAdapter = new ExchangeRecodeRecAdapter(mContext, datas, R.layout
                .exchange_entrust_recode_item);
        entrustRecodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        entrustRecodeRv.setAdapter(recodeRecAdapter);
        recodeRecAdapter.setOnItemClickLitener(new BaseRecylerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                CommonUtil.gotoActivity(mContext, EntrustRecodeActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_exchange_recode;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

}
