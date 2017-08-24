package com.wangdh.mengm.ui.fragment;

import android.util.Log;

import com.wangdh.mengm.MyApplication;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.bean.NewListData;
import com.wangdh.mengm.component.AppComponent;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;

/**
 * 新闻头条
 */

public class NewFragment extends BaseFragment {
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recyclerview;
    }

    @Override
    protected void initData() {
//        MyApplication.getsInstance().getAppComponent().getAppApi().newListDataFlowable("头条",0)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultSubscriber<List<NewListData>>() {
//                    @Override
//                    public void onNext(List<NewListData> newListDatas) {
//                     Log.i("toast", newListDatas.get(0).getResult().getResult().getList().get(0).getTitle());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        toast(t.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

    }

    @Override
    protected void initView() {

    }
}
