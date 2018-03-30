package com.qmkj.jydp.module.login.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmkj.jydp.MainActivity;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.GlideApp;
import com.qmkj.jydp.base.MvpBaseFragment;
import com.qmkj.jydp.ui.widget.ClickItemView;
import com.qmkj.jydp.util.BitmapCompressTask;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * author：rongkui.xiao --2018/3/23
 * email：dovexiaoen@163.com
 * description:实名认证界面
 */

public class CertifyNameFragment extends MvpBaseFragment implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.certify_add_front_rl)
    RelativeLayout certifyAddFrontRl;
    @BindView(R.id.certify_add_back_rl)
    RelativeLayout certifyAddBackRl;
    @BindView(R.id.ertify_account_ll)
    View ertify_account_ll;
    @BindView(R.id.ertify_name_ll)
    View ertify_name_ll;
    @BindView(R.id.ertify_type_cv)
    ClickItemView ertify_type_cv;
    @BindView(R.id.ertify_number_ll)
    View ertify_number_ll;
    @BindView(R.id.front_img)
    ImageView frontImg;
    @BindView(R.id.back_img)
    ImageView backImg;
    Unbinder unbinder1;
    @BindView(R.id.front_ll)
    LinearLayout frontLl;
    @BindView(R.id.back_ll)
    LinearLayout backLl;
    private EditText account_et;
    private EditText name_et;
    private EditText certify_num_et;
    private Bitmap front;
    private boolean isFrontCompressing;
    private byte[] frontBytes;
    private Bitmap back;
    private boolean isBackCompressing;
    private byte[] backBytes;

    @Override
    protected void injectPresenter() {

    }

    @Override
    protected void initView() {
        initItemView();
        ertify_type_cv.setOnClickListener(this);
        certifyAddFrontRl.setOnClickListener(this);
        certifyAddBackRl.setOnClickListener(this);
    }

    private void selectCertifyType() {//选择证件类型

    }

    private void initItemView() {
        ((TextView) ertify_account_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.account));
        account_et = (EditText) ertify_account_ll.findViewById(R.id.common_edit_line_content_et);
        account_et.setCursorVisible(false);
        account_et.setOnClickListener(v -> account_et.setCursorVisible(true));

        ((TextView) ertify_name_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.name));
        name_et = (EditText) ertify_name_ll.findViewById(R.id.common_edit_line_content_et);
        name_et.setHint(CommonUtil.getString(R.string.your_real_name));

        ((TextView) ertify_number_ll.findViewById(R.id.common_edit_line_title_tv)).setText(CommonUtil.getString(R
                .string.certificate_num));
        certify_num_et = (EditText) ertify_number_ll.findViewById(R.id.common_edit_line_content_et);
        certify_num_et.setHint(CommonUtil.getString(R.string.input_your_certificate_num));
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
                Intent pickFront = new Intent(Intent.ACTION_PICK);
                pickFront.setType("image/*");//相片类型
                CommonUtil.startActivityForResult(mContext, pickFront, 1);
                break;
            case R.id.certify_add_back_rl://添加反面照
                Intent pickBack = new Intent(Intent.ACTION_PICK);
                pickBack.setType("image/*");//相片类型
                CommonUtil.startActivityForResult(mContext, pickBack, 2);
                break;
            case R.id.ertify_type_cv://选择证件类型
                CommonUtil.gotoActivity(mContext, MainActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//图片压缩工作放在提交任务里面
        LogUtil.i("requestCode"+requestCode+"  ;resultCode="+resultCode);
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
                isFrontCompressing = true;
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (getContext() != null) {
                        frontBytes = bytes;
                        isFrontCompressing = false;
                        io.reactivex.Observable.timer(0, TimeUnit.MICROSECONDS).observeOn(AndroidSchedulers
                                .mainThread()).subscribe(aLong -> {
                            GlideApp.with(mContext).load(bytes).into(frontImg);
                            frontLl.setVisibility(View.INVISIBLE);
                        });

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
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    if (CertifyNameFragment.this.getContext() != null) {
                        backBytes = bytes;
                        isBackCompressing = false;
                        io.reactivex.Observable.timer(0, TimeUnit.MICROSECONDS).observeOn(AndroidSchedulers
                                .mainThread()).subscribe(aLong -> {
                            GlideApp.with(mContext).load(bytes).into(backImg);
                            backLl.setVisibility(View.INVISIBLE);
                        });

                    }
                });
                bitmapCompressTask.execute(back);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
