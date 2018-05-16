package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.BaseRecylerAdapter;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.CertifyNameReq;
import com.qmkj.jydp.module.login.presenter.LoginPresenter;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.BitmapCompressTask;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertifyNameFragment extends BaseMvpFragment<LoginPresenter> implements View.OnClickListener {
    private static final int CERTIFY_NAME_TAG = 1;
    @BindView(R.id.certify_add_front_rl)
    RelativeLayout certifyAddFrontRl;
    @BindView(R.id.certify_add_back_rl)
    RelativeLayout certifyAddBackRl;
    @BindView(R.id.front_img)
    ImageView frontImg;
    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.front_ll)
    LinearLayout frontLl;
    @BindView(R.id.back_ll)
    LinearLayout backLl;
    @BindView(R.id.ertify_type_select_tv)
    TextView ertifyTypeSelectTv;
    @BindView(R.id.ertify_type_select_iv)
    ImageView ertifyTypeSelectIv;
    @BindView(R.id.certify_name_submit_bt)
    Button certifyNameSubmitBt;
    @BindView(R.id.certify_name_account_cv)
    ClickItemView certifyNameAccountCv;
    @BindView(R.id.certify_name_name_cv)
    EditVItemView certifyNameNameCv;
    @BindView(R.id.ertify_type_num_et)
    EditText ertifyTypeNumEt;
    private Bitmap front;
    private boolean isFrontCompressing;
    private byte[] frontBytes;
    private Bitmap back;
    private boolean isBackCompressing;
    private byte[] backBytes;
    private int selectIndex = 0;
    private CommonDialog commonDialog;
    private String userAccount;

    @Override
    protected void injectPresenter() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initView() {
        initItemView();
        certifyAddFrontRl.setOnClickListener(this);
        certifyAddBackRl.setOnClickListener(this);
        ertifyTypeSelectTv.setOnClickListener(this);
        ertifyTypeSelectIv.setOnClickListener(this);
        certifyNameSubmitBt.setOnClickListener(this);
    }

    private void initItemView() {
        userAccount = CommonUtil.getUserAccount();
        certifyNameAccountCv.setRightText(userAccount);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.login_fragment_certify_name;
    }

    @Override
    protected String getSimpleNme() {
        return getClass().getSimpleName();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.certify_add_front_rl://添加正面照
                checkPermission(1);
                break;
            case R.id.certify_add_back_rl://添加反面照
                checkPermission(2);
                break;
            case R.id.ertify_type_select_tv://选择证件类型
            case R.id.ertify_type_select_iv://选择证件类型
                showCertifyTypeSelectDialog();
                break;
            case R.id.certify_name_submit_bt://提交
                submitCertifyNameData();
                break;
        }
    }

    private void submitCertifyNameData() {
        String userName = certifyNameNameCv.getEditTextString();
        String userCertNo = ertifyTypeNumEt.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            toast("姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(userCertNo)) {
            toast("证件不能为空");
            return;
        }
        if (backBytes == null || backBytes.length <= 0 || frontBytes == null || frontBytes.length <= 0) {
            toast("请先上传图片");
            return;
        }
        if (isBackCompressing || isFrontCompressing) {
            toast("请等待压缩");
            return;
        }

        CertifyNameReq certifyNameReq = new CertifyNameReq();
        certifyNameReq.setUserAccount(userAccount);
        certifyNameReq.setUserName(userName);
        certifyNameReq.setUserCertNo(userCertNo);
        certifyNameReq.setUserCertTypeStr((selectIndex + 1) + "");

        presenter.submitCertify(certifyNameReq, backBytes, frontBytes, CERTIFY_NAME_TAG);
    }

    /**
     * 选择证件照类型对话框
     */
    private void showCertifyTypeSelectDialog() {
        List<DialogItemBean> certifyTypeData = new ArrayList<>();
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.certificate_identification), R
                .mipmap.certificate_identification, true));
        certifyTypeData.add(new DialogItemBean(CommonUtil.getString(R.string.passport), R
                .mipmap.passport, false));

        commonDialog = new CommonDialog(mContext, R.style.common_dialog, R.layout
                .certify_type_select_dialog);
        commonDialog.setAlertDialogHight(0);
        commonDialog.setAlertDialogGravity(Gravity.BOTTOM);

        RecyclerView recyclerView = commonDialog.getView(R.id.certify_type_select_rv, RecyclerView.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(new BaseRecylerAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = (ImageView) helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = (ImageView) helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        });
        commonDialog.show();
        ((BaseRecylerAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
            commonDialog.dismiss();
            selectIndex = position;
            ertifyTypeSelectTv.setText(certifyTypeData.get(position).getCertifyName());
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//图片压缩工作放在提交任务里面
        LogUtil.i("requestCode" + requestCode + "  ;resultCode=" + resultCode);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                if (front != null && !front.isRecycled()) {
                    front.recycle();
                    front = null;
                }
                front = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (front == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                GlideApp.with(mContext).load(front).into(frontImg);
                frontLl.setVisibility(View.INVISIBLE);
                isFrontCompressing = true;
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (getContext() != null) {
                        frontBytes = bytes;
                        isFrontCompressing = false;
                    }
                });
                bitmapCompressTask.execute(front);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                if (back != null && !back.isRecycled()) {
                    back.recycle();
                    back = null;
                }
                back = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (back == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                isBackCompressing = true;
                GlideApp.with(mContext).load(back).into(backImg);
                backLl.setVisibility(View.INVISIBLE);
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (CertifyNameFragment.this.getContext() != null) {
                        backBytes = bytes;
                        isBackCompressing = false;
                    }
                });
                bitmapCompressTask.execute(back);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (commonDialog != null) {
            commonDialog.dismiss();
        }
    }

    private void checkPermission(int index) {
        RxPermissionUtils.getInstance(mContext).setPermission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest
                .permission.WRITE_EXTERNAL_STORAGE)
                .setOnPermissionCallBack(new RxPermissionUtils
                        .OnPermissionListener() {

                    @Override
                    public void onPermissionGranted(String name) {
                        LogUtil.i("onPermissionGranted name=" + name);
                    }

                    @Override
                    protected void onAllPermissionGranted() {
                        switch (index) {
                            case 1:
                                Intent pickFront = new Intent(Intent.ACTION_PICK);
                                pickFront.setType("image/*");//相片类型
                                startActivityForResult(pickFront, 1);
                                break;
                            case 2:
                                Intent pickBack = new Intent(Intent.ACTION_PICK);
                                pickBack.setType("image/*");//相片类型
                                startActivityForResult(pickBack, 2);
                                break;
                        }
                    }
                }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxPermissionUtils.destory();
    }


    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag) {
            case CERTIFY_NAME_TAG:
                toast("提交成功");
                ((CertificationActivity) getActivity()).setSelect(1);
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
    }
}
