package com.qmkj.jydp.module.mine.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.qmkj.jydp.R;
import com.qmkj.jydp.base.BaseRecycleAdapter;
import com.qmkj.jydp.base.BaseRefreshRecycleMvpActivity;
import com.qmkj.jydp.bean.request.PageNumberReq;
import com.qmkj.jydp.bean.request.SendContactServiceReq;
import com.qmkj.jydp.bean.response.CustomerServiceRes;
import com.qmkj.jydp.module.mine.presenter.ContactServiceRecyAdapter;
import com.qmkj.jydp.module.mine.presenter.MinePresenter;
import com.qmkj.jydp.ui.widget.CommonDialog;
import com.qmkj.jydp.ui.widget.ContactServiceDialog;
import com.qmkj.jydp.util.CommonUtil;
import com.qmkj.jydp.util.StringUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * author：rongkui.xiao --2018/5/4
 * email：dovexiaoen@163.com
 * description:联系客服
 */

public class ContactServiceActivity extends BaseRefreshRecycleMvpActivity<MinePresenter> {
    private static final int CONTACTS_GET_MSG=1;
    private static final int CONTACTS_SNED_MSG=2;
    private ContactServiceRecyAdapter contactServiceRecyAdapter;
    private ArrayList datas;
    private ContactServiceDialog dialogUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = getBottomButton();
        button.setVisibility(View.VISIBLE);
        button.setText(CommonUtil.getString(R.string.commit_problem));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConformDialog();
            }
        });
    }

    @Override
    protected void injectPresenter() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        PageNumberReq req = new PageNumberReq();
        req.setPageNumber(0);
        presenter.getCustomerServiceInfo(req,CONTACTS_GET_MSG,true);
    }

    @Override
    public BaseRecycleAdapter getRecycleAdapter() {
        initRecycleView();
        return contactServiceRecyAdapter;
    }

    private void initRecycleView() {
        datas = new ArrayList();
        contactServiceRecyAdapter = new ContactServiceRecyAdapter(mContext, datas, R.layout
                .mine_contact_service_item);
    }

    @Override
    public void onSuccess(Object response, int tag) {
        super.onSuccess(response, tag);
        switch (tag){
            case CONTACTS_GET_MSG:
                CustomerServiceRes customerServiceRes = (CustomerServiceRes)response;
                if(customerServiceRes.getUserFeedbackList()!=null){
                    contactServiceRecyAdapter.addData(customerServiceRes.getUserFeedbackList());
                    contactServiceRecyAdapter.notifyDataSetChanged();
                }
                break;
            case CONTACTS_SNED_MSG:
                toast("提交成功");
                dialogUtils.dismiss();
                break;
        }

    }

    @Override
    public List getData() {
        return datas;
    }

    @Override
    public String getTittle() {
        return CommonUtil.getString(R.string.connect_service);
    }


    private void showConformDialog() {
        dialogUtils = new ContactServiceDialog(mContext, R.style.common_dialog);
        dialogUtils.setAlertDialogWidth((int) CommonUtil.getDimen(R.dimen.x330));
        dialogUtils.setConfirmBtnListener( v -> {
           String tittle = dialogUtils.getTittleText();
           String content = dialogUtils.getContentText();
           if(StringUtil.isNull(tittle)){
               toast("标题不能为空");
           }
           if(StringUtil.isNull(content)){
               toast("内容不能为空");
           }
            SendContactServiceReq req = new SendContactServiceReq();
            req.setFeedbackTitle(tittle);
            req.setFeedbackContent(content);
            presenter.sendCustomerServiceInfo(req,CONTACTS_SNED_MSG,true);
        });


        dialogUtils.setCancelBtnListener( v -> dialogUtils.dismiss());
        dialogUtils.show();
    }
}
