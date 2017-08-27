package com.wangdh.mengm.api;

import com.wangdh.mengm.base.Constant;
import com.wangdh.mengm.bean.CelebratedDictum;
import com.wangdh.mengm.bean.NewListData;
import com.wangdh.mengm.bean.WeChatListData;
import com.wangdh.mengm.bean.WechatImage;

import java.util.List;
import java.util.Map;
import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * wdh.
 */

public class RetrofitManager {
    public static RetrofitManager instance;
    private ApiService service;

    public RetrofitManager(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static RetrofitManager getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            instance = new RetrofitManager(okHttpClient);
        return instance;
    }


    public Flowable<CelebratedDictum> splashRxjava() {
        return service.splashRxjava();
    }

    public Flowable<NewListData> newListDataFlowable(Map<String,String> params) {
        return service.newListDataRxjava(params);
    }

    public Flowable<WeChatListData> weChatListDataFlowable(Map<String,String> params){
        return service.weCharListDataRxjava(params);
    }

    public Flowable<WechatImage> wechatImageFlowable(){
        return service.wecharImageRxjava();
    }
}
