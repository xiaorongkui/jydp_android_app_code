package com.qmkj.jydp.module.login.modle;

import com.qmkj.jydp.base.BaseView;

import java.io.File;

public interface LoginContract {

    interface UpdateView extends BaseView {
        void update(int progrss);

        void downLoadFinish(File file);

        void downLoadFailed();
    }

}
