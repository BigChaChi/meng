package com.wangdh.mengm.ui.contract;


import com.wangdh.mengm.base.BaseContract;
import com.wangdh.mengm.bean.CelebratedDictum;

public interface SplashContract{
    interface View extends BaseContract.BaseView{
        void showSplashData(CelebratedDictum dictum);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T>{
        void getSplashDta();
    }
}
