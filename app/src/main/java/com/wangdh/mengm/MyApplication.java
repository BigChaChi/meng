package com.wangdh.mengm;

import android.app.Application;

import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerAppComponent;
import com.wangdh.mengm.module.ApiModule;
import com.wangdh.mengm.module.AppModule;
import com.wangdh.mengm.utils.AppUtils;
import com.wangdh.mengm.utils.SharedPreferencesMgr;

public class MyApplication extends Application {
    private static MyApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initCompoent();
        sInstance = this;
        AppUtils.init(this);
        SharedPreferencesMgr.init(this,"mengm");
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
    
}
