package com.qmkj.jydp.module.login.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.DoubleString;
import com.qmkj.jydp.common.Constants;
import com.qmkj.jydp.common.PhoneAreaConfig;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.module.login.presenter.SearchAreaRecyAdapter;
import com.qmkj.jydp.ui.widget.KeyboardChangeListener;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/26
 * email：dovexiaoen@163.com
 * description:国家区号选择页面
 */

public class AreaCodeSecActivity extends BaseMvpActivity<LoginPresenter> {
    @BindView(R.id.area_code_search_et)
    EditText areaCodeSearchEt;
    @BindView(R.id.search_erea_code_cancel_tv)
    TextView searchEreaCodeCancelTv;
    @BindView(R.id.search_area_code_rv)
    RecyclerView searchAreaCodeRv;
    @BindView(R.id.area_code_search_ll)
    LinearLayout areaCodeSearchLl;
    private boolean mBoarddIsShow;
    private KeyboardChangeListener keyboardChangeListener;
    private SearchAreaRecyAdapter areaRecyAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_area_code;
    }

    @Override
    protected void initView() {
        initRecycleView();
        CommonUtil.hideInputWindow(mContext);
        areaCodeSearchLl.setFocusable(true);
        areaCodeSearchLl.setFocusableInTouchMode(true);
        areaCodeSearchLl.requestFocus();
        areaCodeSearchLl.setOnTouchListener((v, event) -> {
            areaCodeSearchLl.setFocusable(true);
            areaCodeSearchLl.setFocusableInTouchMode(true);
            areaCodeSearchLl.requestFocus();
            CommonUtil.hideInputWindow(mContext);
            return false;
        });
        areaCodeSearchEt.setOnTouchListener((v, event) -> {
            areaCodeSearchEt.setCursorVisible(true);
            return false;
        });
        keyboardChangeListener = new KeyboardChangeListener(mContext);
        keyboardChangeListener.setKeyBoardListener((isShow, keyboardHeight) -> mBoarddIsShow = isShow);

        RxTextView.afterTextChangeEvents(areaCodeSearchEt).subscribe(textViewAfterTextChangeEvent -> {
            EditText area_et = (EditText) textViewAfterTextChangeEvent.view();
            searchArea(area_et.getText().toString());
        });
        searchEreaCodeCancelTv.setOnClickListener(v -> finish());
    }

    private void searchArea(String s) {
        datas.clear();
        for (DoubleString data : originalDatas) {
            if (data.getStr2().contains(s)) {
                datas.add(data);
            }
        }
        if (datas.size() == 0) {
            if (TextUtils.isEmpty(s)) {
                datas.addAll(originalDatas);
            } else {
                toast("未搜索到相关结果");
            }
        }
        areaRecyAdapter.notifyDataSetChanged();
        LogUtil.i("s=" + s + "datas.size=" + datas.size());
    }

    List<DoubleString> datas = new ArrayList<>();
    List<DoubleString> originalDatas = new ArrayList<>();

    private void initRecycleView() {
        Map<String, String> phoneAreaMap = PhoneAreaConfig.phoneAreaMap;
        for (Map.Entry<String, String> stringStringEntry : phoneAreaMap.entrySet()) {
            originalDatas.add(new DoubleString(stringStringEntry.getKey(), stringStringEntry.getValue()));
        }
        datas.addAll(originalDatas);
        areaRecyAdapter = new SearchAreaRecyAdapter(mContext, datas, R.layout
                .login_search_area_item);
        searchAreaCodeRv.setLayoutManager(new LinearLayoutManager(mContext));
        searchAreaCodeRv.setAdapter(areaRecyAdapter);
        areaRecyAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (!mBoarddIsShow) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra(Constants.INTENT_PARAMETER_1, datas.get(position));
                setResult(RESULT_OK, intent);
                AppManager.getInstance().removeCurrent();
            } else {//隐藏键盘
                CommonUtil.hideInputWindow(mContext);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (keyboardChangeListener != null) {
            keyboardChangeListener.destroy();
            keyboardChangeListener = null;
        }
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }
}
