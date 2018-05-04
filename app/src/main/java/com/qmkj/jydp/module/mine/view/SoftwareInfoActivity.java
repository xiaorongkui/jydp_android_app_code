package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:软件信息
 */

public class SoftwareInfoActivity extends BaseMvpActivity {
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.software_info_version_num_tv)
    TextView softwareInfoVersionNumTv;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.software_info));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_software_info;
    }

    @Override
    protected void initView() {
        final SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x11))
                .setDefaultStrokeColor(CommonUtil.getColor(R.color.color_gray_2))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));

        softwareInfoVersionNumTv.setBackground(shapeSelector.create());
        softwareInfoVersionNumTv.setText(CommonUtil.getAppVersionName(mContext));
    }


}
