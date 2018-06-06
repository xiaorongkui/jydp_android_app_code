package com.qmkj.jydp.module.login.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpFragment;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRecyclerViewHolder;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.bean.DialogItemBean;
import com.qmkj.jydp.bean.request.CertifyNameReq;
import com.qmkj.jydp.common.NetResponseCode;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

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
        if (getActivity() != null) {
            userAccount = ((CertificationActivity) getActivity()).getAccount();
        }
        certifyNameAccountCv.setRightText(userAccount);
        initInput();
    }

    private void initInput() {
        String regEx = "[^a-zA-Z\u4E00-\u9FA5 ]";
        certifyNameNameCv.setEditTextInputFilter((source, start, end, dest, dstart, dend) -> {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(source.toString());
            return matcher.matches() ? "" : null;
        }, new InputFilter.LengthFilter(16));
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

    /**
     * 提交认证请求
     */
    private void submitCertifyNameData() {
        String userName = certifyNameNameCv.getEditTextString();
        String userCertNo = ertifyTypeNumEt.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            toast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(userCertNo)) {
            toast("请输入证件号");
            return;
        }

        if (userCertNo.length() < 6 || userCertNo.length() > 18) {
            toast("证件号输入错误");
            return;
        }
        if (isBackCompressing || isFrontCompressing) {
            toast("请等待压缩");
            return;
        }
        if (frontBytes == null || frontBytes.length <= 0) {
            toast("请先上传正面图片");
            return;
        }
        if (backBytes == null || backBytes.length <= 0) {
            toast("请先上传反面图片");
            return;
        }

        CertifyNameReq certifyNameReq = new CertifyNameReq();
        certifyNameReq.setUserAccount(userAccount);
        certifyNameReq.setUserName(userName);
        certifyNameReq.setUserCertNo(userCertNo);
        certifyNameReq.setUserCertTypeStr((selectIndex + 1) + "");

        presenter.submitCertify(certifyNameReq, frontBytes, backBytes, CERTIFY_NAME_TAG);
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
        recyclerView.setAdapter(new BaseRecycleAdapter<DialogItemBean>(R.layout.certify_type_select_item,
                certifyTypeData) {

            @Override
            protected void convert(BaseRecyclerViewHolder helper, DialogItemBean item, int position) {
                ImageView imageViewLeft = helper.getView(R.id.certify_type_left_iv);
                ImageView imageViewRight = helper.getView(R.id.certify_type_right_iv);
                TextView certifyType_tv = helper.getView(R.id.certify_type_tv);
                imageViewLeft.setImageResource(item.getLeftImageViewId());
                imageViewRight.setImageResource(selectIndex == position ? R.mipmap.bt_selected : R.mipmap
                        .bt_unselected);
                certifyType_tv.setText(item.getCertifyName());
            }
        });
        commonDialog.show();
        ((BaseRecycleAdapter) recyclerView.getAdapter()).setOnItemClickListener((adapter, view, position) -> {
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
                LogUtil.i("图片正面照出错=" + e.getMessage());
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
                LogUtil.i("图片反面照出错=" + e.getMessage());
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

    /**
     * 先检测权限，再打开系统相册选择图片
     *
     * @param index 1 选择正面照片 2 选择背面照片
     */
    private void checkPermission(int index) {
        //判断是否处于压缩状态，如果处于压缩状态，则提示用户稍候再操作
        switch (index) {
            case 1:
                if (isFrontCompressing) {
                    toast("正在压缩中，请稍后");
                    return;
                }
                break;
            case 2:
                if (isBackCompressing) {
                    toast("正在压缩中，请稍后");
                    return;
                }
                break;
        }
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
                                frontBytes = null;
                                break;
                            case 2:
                                Intent pickBack = new Intent(Intent.ACTION_PICK);
                                pickBack.setType("image/*");//相片类型
                                startActivityForResult(pickBack, 2);
                                backBytes = null;
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
                if (getActivity() != null) {
                    ((CertificationActivity) getActivity()).getCertifyStatus();
                }
                break;
        }
    }

    @Override
    public void onError(String errorMsg, String code, int tag, Object o) {
        super.onError(errorMsg, code, tag, o);
        switch (tag) {
            case CERTIFY_NAME_TAG:
                switch (code) {
                    case NetResponseCode.HMC_HAS_CHECKING://审核中
                        if (getActivity() != null) {
                            ((CertificationActivity) getActivity()).setSelect(0);
                        }
                        break;
                }
                break;
        }
    }
}
