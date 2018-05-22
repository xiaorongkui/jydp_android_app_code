package com.qmkj.jydp.module.mine.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.module.mine.presenter.HelpCenterRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:帮助中心
 */

public class HelpCenterActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private ArrayList<Object> mData;
    private HelpCenterRecyAdapter helpCenterRecyAdapter;

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
//        presenter.getHelpCenterInfo();
    }

    @Override
    protected void initView() {
        initRecycleView();

    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return helpCenterRecyAdapter;
    }

    @Override
    public List getData() {
        return mData;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.help_center);
    }

    private void initRecycleView() {
        mData = new ArrayList<>();
        helpCenterRecyAdapter = new HelpCenterRecyAdapter(mContext, mData, R.layout.single_click_item);
        helpCenterRecyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
        });
    }
}
