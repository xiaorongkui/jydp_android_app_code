package com.qmkj.jydp.module.login.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.request.ReCertificetionReq;
import com.qmkj.jydp.bean.response.CertificetionInfoRes;
import com.qmkj.jydp.bean.response.LoginRes;
import com.qmkj.jydp.manager.AppManager;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.SelectorFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证审核中
 */

public class CertifyNameStatusFragment extends BaseMvpFragment<LoginPresenter> {

    private static final int CERTIFICATION_RECHECK_TAG = 1;
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
    @BindView(R.id.check_mark_ll)
    LinearLayout checkMarkLl;
    private CertificetionInfoRes checkInfo;
    private int status;

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        initView();
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            initView();
        }
    }

    @Override
    protected void initView() {
        checkInfo = ((CertificationActivity) getActivity()).getInfoRes();
        if (checkInfo == null) return;
        status = checkInfo.getIdentification().getIdentificationStatus();
        certifyCheckHomeBt.setOnClickListener(this);
        certifyCheckStatusBt.setOnClickListener(this);
        if (checkInfo == null || checkInfo.getIdentification() == null || checkInfo.getIdentificationImageList().size
                () < 2) {
            LogUtil.i("实名认证信息为空");
            return;
        }
        CertificetionInfoRes.IdentificationBean identification = checkInfo.getIdentification();
        ertifyCheckAccountCv.setRightText(identification.getUserAccount());
        ertifyCheckNameCv.setRightText(identification.getUserName());
        int userCertType = identification.getUserCertType();
        switch (userCertType) {
            case 2:
                ertifyCheckTypeCv.setRightText("护照");
                break;
            default:
                ertifyCheckTypeCv.setRightText("身份证");
                break;
        }
        ertifyCheckNumberCv.setRightText(identification.getUserCertNo());
        List<CertificetionInfoRes.IdentificationImageListBean> identificationImageList = checkInfo
                .getIdentificationImageList();
        GlideApp.with(mContext).load(identificationImageList.get(0).getImageUrlFormat())
                .placeholder(R.mipmap.ic_launcher).into(certifyNameFrontIv);
        GlideApp.with(mContext).load(identificationImageList.get(1).getImageUrlFormat())
                .placeholder(R.mipmap.ic_launcher).into(certifyNameBackIv);
        switch (status) {
            case 1://待审核
                setCertifyNameStatus(0);
                break;
            case 2://审核通过
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                AppManager.getInstance().removeCurrent();
                break;
            case 3://审核拒绝
                setCertifyNameStatus(2);
                break;
        }
        certifyCheckHomeBt.setOnClickListener(this);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certify_check_home_bt:
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                break;
            case R.id.certify_check_status_bt://重新认证
                switch (status) {
                    case 2:
                        CommonUtil.gotoActivity(mContext, LoginActivity.class);
                        AppManager.getInstance().removeCurrent();
                        break;
                    case 3:
                        getReCertificationStaus();
                        break;
                }
                break;
        }
    }

    private void getReCertificationStaus() {
        ReCertificetionReq reCertificetionReq = new ReCertificetionReq();
        reCertificetionReq.setUserAccount(checkInfo.getIdentification().getUserAccount());
        presenter.getReCertificationStaus(reCertificetionReq, CERTIFICATION_RECHECK_TAG, true);
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
                checkMarkLl.setVisibility(View.GONE);
                certifyCheckStatusBt.setVisibility(View.GONE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) certifyCheckHomeBt
                        .getLayoutParams();
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                certifyCheckHomeBt.setLayoutParams(layoutParams);
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
                checkMarkLl.setVisibility(View.VISIBLE);
                checkMarkContentTv.setText(checkInfo.getIdentification().getRemark());
                break;
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case CERTIFICATION_RECHECK_TAG:
                if (getActivity() != null) {
                    ((CertificationActivity) getActivity()).setSelect(0);
                }
                break;
        }
    }

}
