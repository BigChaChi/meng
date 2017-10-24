package com.wangdh.mengm;

import com.mob.MobApplication;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerAppComponent;
import com.wangdh.mengm.module.ApiModule;
import com.wangdh.mengm.module.AppModule;
import com.wangdh.mengm.utils.AppUtils;
import com.wangdh.mengm.utils.SharedPreferencesMgr;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.Bmob;

public class MyApplication extends MobApplication {
    private static MyApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initCompoent();
        sInstance = this;
        AppUtils.init(this);
        Bmob.initialize(this, "60394b1cd31efaf89ab9b0f586241788");
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

    public static boolean isPhoneNum(String phone) {  //手机号
        //   Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        //"'|^1[34578]{1}\\d{9}$|'"
        Pattern p = Pattern.compile("^0{0,1}(13[0-9]|15[0-9]|18[0-9])[0-9]{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    public static boolean isPasswordCorrect(String pwd) { //密码由数字或字母组成
        Pattern p = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher m = p.matcher(pwd);
        return m.matches();
    }
}
