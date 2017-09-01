package com.wangdh.mengm.ui.Presenter;


import android.util.Log;

import com.wangdh.mengm.api.RetrofitManager;
import com.wangdh.mengm.base.RxPresenter;
import com.wangdh.mengm.bean.HealthitemData;
import com.wangdh.mengm.ui.contract.HealthFragmentContract;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

public class HealthFragmentPresenter extends RxPresenter<HealthFragmentContract.View> implements
        HealthFragmentContract.Presenter<HealthFragmentContract.View> {
    private RetrofitManager api;

    @Inject
    public HealthFragmentPresenter(RetrofitManager api) {
        this.api = api;
    }

    @Override
    public void getHealthData() {
        api.healthitemDataFlowable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<HealthitemData>() {
                    @Override
                    public void onNext(HealthitemData healthitemData) {
                        if(healthitemData.getShowapi_res_code()==0){
                            mView.showHealthData(healthitemData);
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
