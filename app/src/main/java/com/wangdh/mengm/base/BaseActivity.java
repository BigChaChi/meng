package com.wangdh.mengm.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wangdh.mengm.MyApplication;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.utils.StateBarTranslucentUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);//用于初始化view之前做一些事情
        StateBarTranslucentUtils.setStateBarTranslucent(this);
        setContentView(setLayoutResourceID());
        unbinder=ButterKnife.bind(this);
        mContext = this;
        transparent19and20();
        setupActivityComponent(MyApplication.getsInstance().getAppComponent());
        initData();
        initView();
    }

    protected void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected void init(Bundle savedInstanceState) {
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

    protected abstract int setLayoutResourceID();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        finish();
    }

    public Context getContext() {
        return this;
    }

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


}
