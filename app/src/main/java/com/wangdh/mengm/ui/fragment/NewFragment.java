package com.wangdh.mengm.ui.fragment;

import android.util.Log;

import com.wangdh.mengm.MyApplication;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.base.Constant;
import com.wangdh.mengm.bean.NewListData;
import com.wangdh.mengm.component.AppComponent;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //channel={channel}&num=20&start={start}&appkey=
        Map<String,String> map=new HashMap<>();
        map.put("channel","头条");
        map.put("num","10");
        map.put("start","0");
        map.put("appkey", Constant.jcloudKey);
        MyApplication.getsInstance().getAppComponent().getAppApi().newListDataFlowable(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<NewListData>() {
                    @Override
                    public void onNext(NewListData newListDatas) {
                     Log.i("toast", newListDatas.getResult().getResult().getList().get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable t) {
                        toast(t.getMessage());
                        Log.i("toast", t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initView() {

    }
}
