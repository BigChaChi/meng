
package com.wangdh.mengm.base;

import org.reactivestreams.Subscription;

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;
    private Subscription subscription;

    protected void unSubscribe() {
        if(subscription!=null){
            subscription.cancel();
        }
    }

    protected void addSubscrebe(Subscription subscription){
        if(subscription==null) {
            this.subscription = subscription;
        }
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}
