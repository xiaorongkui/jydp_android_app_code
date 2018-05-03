package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/3
 * email：dovexiaoen@163.com
 * description:个人信息界面
 */

public class PersonInfoActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;
    @BindView(R.id.person_info_login_out_bt)
    Button personInfoLoginOutBt;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.person_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_person_info;
    }

    @Override
    protected void initView() {
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x3))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_black_10))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        personInfoLoginOutBt.setBackground(shapeSelector.create());
    }
}
