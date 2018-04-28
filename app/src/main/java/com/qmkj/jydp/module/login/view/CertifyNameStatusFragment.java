package com.qmkj.jydp.module.login.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.SelectorFactory;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证审核中
 */

public class CertifyNameStatusFragment extends BaseMvpFragment {

    @BindView(R.id.certify_name_status_iv)
    ImageView certifyNameStatusIv;
    @BindView(R.id.ertify_check_account_cv)
    ClickItemView ertifyCheckAccountCv;
    @BindView(R.id.ertify_check_name_cv)
    ClickItemView ertifyCheckNameCv;
    @BindView(R.id.ertify_check_type_cv)
    ClickItemView ertifyCheckTypeCv;
    @BindView(R.id.ertify_check_number_cv)
    ClickItemView ertifyCheckNumberCv;
    @BindView(R.id.ertify_check_picture_tv)
    TextView ertifyCheckPictureTv;
    @BindView(R.id.certify_name_front_iv)
    ImageView certifyNameFrontIv;
    @BindView(R.id.certify_check_front_rl)
    RelativeLayout certifyCheckFrontRl;
    @BindView(R.id.certify_name_back_iv)
    ImageView certifyNameBackIv;
    @BindView(R.id.certify_check_back_rl)
    RelativeLayout certifyCheckBackRl;
    @BindView(R.id.check_mark_tv)
    TextView checkMarkTv;
    @BindView(R.id.check_mark_content_tv)
    TextView checkMarkContentTv;
    @BindView(R.id.certify_check_home_bt)
    Button certifyCheckHomeBt;
    @BindView(R.id.certify_check_status_bt)
    Button certifyCheckStatusBt;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        certifyCheckHomeBt.setOnClickListener(this);
        certifyCheckStatusBt.setOnClickListener(this);
        setCertifyNameStatus(0);
    }


    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.login_fragment_certify_check;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }

    int i = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certify_check_home_bt:
                setCertifyNameStatus(i);
                i++;
                if (i > 2 || i < 0) {
                    i = 0;
                }
                break;
            case R.id.certify_check_status_bt:
                if (getActivity() != null) {
                    ((CertificationActivity) getActivity()).setSelect(0);
                }
                break;
        }
    }

    public void setCertifyNameStatus(int index) {
        SelectorFactory.ShapeSelector shapeSelector = SelectorFactory.newShapeSelector()
                .setCornerRadius((int) CommonUtil.getDimen(R.dimen.x3))
                .setStrokeWidth((int) CommonUtil.getDimen(R.dimen.x1))
                .setDefaultBgColor(CommonUtil.getColor(R.color.color_white_1));
        switch (index) {
            case 0://审核中
                certifyCheckHomeBt.setBackground(CommonUtil.getDrawable(R.drawable.shape_btn_bule));
                shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_bule_1));
                certifyCheckStatusBt.setBackground(shapeSelector.create());
                certifyCheckStatusBt.setTextColor(CommonUtil.getColor(R.color.color_bule_1));
                certifyCheckStatusBt.setText(CommonUtil.getString(R.string.re_certification));
                certifyNameStatusIv.setImageResource(R.mipmap.certify_name_check);
                break;
            case 1://审核通过
                certifyCheckHomeBt.setBackground(CommonUtil.getDrawable(R.drawable.shape_btn_green));
                shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_green_2));
                certifyCheckStatusBt.setBackground(shapeSelector.create());
                certifyCheckStatusBt.setTextColor(CommonUtil.getColor(R.color.color_green_3));
                certifyCheckStatusBt.setText(CommonUtil.getString(R.string.login));
                certifyNameStatusIv.setImageResource(R.mipmap.certify_name_check_pass);
                break;
            case 2://审核拒绝
                certifyCheckHomeBt.setBackground(CommonUtil.getDrawable(R.drawable.shape_btn_red));
                shapeSelector.setDefaultStrokeColor(CommonUtil.getColor(R.color.color_red_2));
                certifyCheckStatusBt.setBackground(shapeSelector.create());
                certifyCheckStatusBt.setTextColor(CommonUtil.getColor(R.color.color_red_2));
                certifyCheckStatusBt.setText(CommonUtil.getString(R.string.re_certification));
                certifyNameStatusIv.setImageResource(R.mipmap.certify_name_check_refuse);
                break;
        }

    }
}
