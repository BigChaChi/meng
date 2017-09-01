package com.wangdh.mengm.ui.Presenter;

import com.wangdh.mengm.api.RetrofitManager;
import com.wangdh.mengm.base.Constant;
import com.wangdh.mengm.base.RxPresenter;
import com.wangdh.mengm.bean.CookBooksData;
import com.wangdh.mengm.ui.contract.CookBooksContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

public class CookBooksPresenter extends RxPresenter<CookBooksContract.View> implements CookBooksContract.Presenter<CookBooksContract.View> {
    private RetrofitManager api;
    @Inject
    public CookBooksPresenter(RetrofitManager api) {
        this.api = api;
    }

    @Override
    public void getCookBooksData() {
        api.cookBooksDataFlowable(Constant.jcloudKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<CookBooksData>() {
                    @Override
                    public void onNext(CookBooksData data) {
                        if(data.getCode().equals("10000")){
                            mView.showCookBooksData(data);
                        }else {
                            mView.showError("数据加载失败");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mView.complete();
                    }
                });

    }
}
