package com.qmkj.jydp.module.mine.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dd.ShadowLayout;
import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseMvpActivity;
import com.qmkj.jydp.bean.request.OtcReleaseReq;
import com.qmkj.jydp.bean.request.SendAdsReq;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.EditVItemView;
import com.qmkj.jydp.util.BitmapCompressTask;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.LogUtil;
import com.qmkj.jydp.util.RxPermissionUtils;
import com.qmkj.jydp.util.StringUtil;

import butterknife.BindView;

/**
 * author：rongkui.xiao --2018/5/10
 * email：dovexiaoen@163.com
 * description:收款信息
 */

public class ReceivablesActivity extends BaseMvpActivity<MinePresenter> {
    public static final int ACTIVITY_RESULT_CODE =200;
    @BindView(R.id.title_header_tv)
    TextView titleHeaderTv;

    @BindView(R.id.bank_layout)
    ShadowLayout bank_layout;
    @BindView(R.id.alipay_layout)
    ShadowLayout alipay_layout;
    @BindView(R.id.weixin_layout)
    ShadowLayout weixin_layout;

    @BindView(R.id.receivables_bank_card_num_eiv)
    EditVItemView receivables_bank_card_num_eiv;
    @BindView(R.id.receivables_bank_name_eiv)
    EditVItemView receivables_bank_name_eiv;
    @BindView(R.id.receivables_bank_branch_name_eiv)
    EditVItemView receivables_bank_branch_name_eiv;
    @BindView(R.id.receivables_bank_reserve_name_eiv)
    EditVItemView receivables_bank_reserve_name_eiv;
    @BindView(R.id.receivables_bank_reserve_phone_eiv)
    EditVItemView receivables_bank_reserve_phone_eiv;


    @BindView(R.id.receivables_alipay_account_eiv)
    EditVItemView receivables_alipay_account_eiv;
    @BindView(R.id.receivables_alipay_receipt_code_eiv)
    EditVItemView receivables_alipay_receipt_code_eiv;


    @BindView(R.id.receivables_wechat_account_eiv)
    EditVItemView receivables_wechat_account_eiv;
    @BindView(R.id.receivables_wechat_receipt_code_eiv)
    EditVItemView receivables_wechat_receipt_code_eiv;

    @BindView(R.id.receivables_confirm_bt)
    Button receivables_confirm_bt;
    private boolean isAliCompressing; //正在阿里二维码压缩图片
    private boolean isWeichatCompressing;//正在微信压缩图片
    private OtcReleaseReq releaseReq;
    private String msg; //提交广告时选择的收款方式
    private byte[] aliPicture; //阿里付款码
    private byte[] weiChatPicture;


    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initTitle() {
        titleHeaderTv.setText(CommonUtil.getString(R.string.receiables_info));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_activity_receivables_info;
    }

    @Override
    protected void initView() {
        releaseReq = new OtcReleaseReq();
        receivables_confirm_bt.setOnClickListener(this);
        SendAdsReq sendAdsReq = (SendAdsReq) getIntent().getSerializableExtra(PublishAdvertisementActivity.MESSAGE_NEXT);

        releaseReq.setCurrencyId(sendAdsReq.getCurrencyId());
        releaseReq.setMinNumber(sendAdsReq.getMinNumber());
        releaseReq.setMaxNumber(sendAdsReq.getMaxNumber());
        releaseReq.setOrderType(sendAdsReq.getOrderType());
        releaseReq.setPendingRatio(sendAdsReq.getPendingRatio());


        msg = sendAdsReq.getSelectList();
        if(msg.contains("1")){
            bank_layout.setVisibility(View.VISIBLE);
        }
        if(msg.contains("2")){
            alipay_layout.setVisibility(View.VISIBLE);
        }
        if(msg.contains("3")){
            weixin_layout.setVisibility(View.VISIBLE);
        }

        receivables_bank_card_num_eiv.setEditTextInputType(InputType.TYPE_CLASS_NUMBER);
        receivables_bank_card_num_eiv.setEditTextMaxLength(19);
        receivables_bank_reserve_phone_eiv.setEditTextMaxLength(11);

        receivables_bank_branch_name_eiv.setEditTextNoFu(30);
        receivables_bank_name_eiv.setEditTextNoFu(15);
        receivables_bank_reserve_name_eiv.setEditTextNoFu(20);



        receivables_alipay_receipt_code_eiv.setEditTextViewFocuseAble(false);
        receivables_alipay_receipt_code_eiv.setClickable(true);

        receivables_wechat_receipt_code_eiv.setEditTextViewFocuseAble(false);
        receivables_wechat_receipt_code_eiv.setClickable(true);

        receivables_alipay_receipt_code_eiv.setOnEditClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(1);
            }
        });
        receivables_wechat_receipt_code_eiv.setOnEditClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(2);
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.receivables_confirm_bt:
                sentRequest();
                break;
        }
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);

        toast("发起广告成功");
        setResult(ACTIVITY_RESULT_CODE);
        finish();

    }

    private void sentRequest() {
        String bank_card_num_eiv = receivables_bank_card_num_eiv.getEditTextString(); //卡号
        String bank_name = receivables_bank_name_eiv.getEditTextString();
        String branch_name = receivables_bank_branch_name_eiv.getEditTextString();
        String reserve_name = receivables_bank_reserve_name_eiv.getEditTextString();
        String phone = receivables_bank_reserve_phone_eiv.getEditTextString();

        String alipay_account = receivables_alipay_account_eiv.getEditTextString();
        String alipay_receipt_code = receivables_alipay_receipt_code_eiv.getEditTextString();

        String wechat_account = receivables_wechat_account_eiv.getEditTextString();
        String wechat_receipt_code = receivables_wechat_receipt_code_eiv.getEditTextString();


        if(msg.contains("1")){
            if (bankCheck(bank_card_num_eiv, bank_name, branch_name, reserve_name, phone)) return;
        }
        if(msg.contains("2")){
            if (aliCheck(alipay_account)) return;
        }
        if(msg.contains("3")){
            if (weiChatCheck(wechat_account)) return;
        }

        presenter.sendOtcReleaseInfo(releaseReq,aliPicture,weiChatPicture,1,true);
    }


    private boolean weiChatCheck(String wechat_account) {
        if(isWeichatCompressing){
            toast("图片正在压缩，请稍等");
            return true;
        }
        if(StringUtil.isNull(wechat_account)){
            toast("微信账号不能为空");
            return true;
        }
        if(weiChatPicture==null||weiChatPicture.length<1){
            toast("请选择微信收款二维码");
            return true;
        }
        releaseReq.setWechatAccount(wechat_account);
        return false;
    }

    private boolean aliCheck(String alipay_account) {
        if(isAliCompressing){
            toast("图片正在压缩，请稍等");
            return true;
        }
        if(StringUtil.isNull(alipay_account)){
            toast("支付宝账号不能为空");
            return true;
        }
        if(aliPicture==null||aliPicture.length<1){
            toast("请选择支付宝收款二维码");
            return true;
        }
        releaseReq.setAlipayAccount(alipay_account);
        return false;
    }

    /**
     * 检查银行参数合法性
     * @param bank_card_num_eiv
     * @param bank_name
     * @param branch_name
     * @param reserve_name
     * @param phone
     * @return
     */
    private boolean bankCheck(String bank_card_num_eiv, String bank_name, String branch_name, String reserve_name, String phone) {
        if(StringUtil.isNull(bank_card_num_eiv)){
            toast("银行卡号不能为空");
            return true;
        }
        if(StringUtil.isNull(bank_name)){
            toast("银行名称不能为空");
            return true;
        }
        if(StringUtil.isNull(branch_name)){
            toast("支行名称不能为空");
            return true;
        }
        if(StringUtil.isNull(reserve_name)){
            toast("银行预留姓名不能为空");
            return true;
        }
        if(StringUtil.isNull(phone)){
            toast("银行预留电话不能为空");
            return true;
        }

        releaseReq.setBankAccount(bank_card_num_eiv);
        releaseReq.setBankName(bank_name);
        releaseReq.setBankBranch(branch_name);
        releaseReq.setPaymentName(reserve_name);
        releaseReq.setPaymentPhone(phone);
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//图片压缩工作放在提交任务里面
        LogUtil.i("requestCode" + requestCode + "  ;resultCode=" + resultCode);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {

                final Bitmap alipay_bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (alipay_bitmap == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                isAliCompressing = true;
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    receivables_alipay_receipt_code_eiv.setEditTextView(uri+"");
                    aliPicture = bytes;
                    isAliCompressing = false;

                    if (alipay_bitmap != null && !alipay_bitmap.isRecycled()) {
                        alipay_bitmap.recycle();
                    }
                });
                bitmapCompressTask.execute(alipay_bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            try {
                final Bitmap weichat_bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
                if (weichat_bitmap == null) {
                    toast("选择的图片存在问题，请重新选择图片");
                    return;
                }
                isWeichatCompressing = true;
                //压缩图片
                BitmapCompressTask bitmapCompressTask = new BitmapCompressTask();
                bitmapCompressTask.setOnCompressFinishListener(bytes -> {
                    receivables_wechat_receipt_code_eiv.setEditTextView(uri+"");
                    weiChatPicture = bytes;
                    isWeichatCompressing = false;
                    if (weichat_bitmap != null && !weichat_bitmap.isRecycled()) {
                        weichat_bitmap.recycle();
                    }
                });
                bitmapCompressTask.execute(weichat_bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
}
