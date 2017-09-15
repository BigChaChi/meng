package com.wangdh.mengm.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.bean.WeatherAllData;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerActivityComponent;
import com.wangdh.mengm.ui.Presenter.WeatherActivityPresenter;
import com.wangdh.mengm.ui.adapter.WeatherAdapter;
import com.wangdh.mengm.ui.contract.WeatherActivityContract;
import com.wangdh.mengm.utils.RecyclerViewUtil;
import com.wangdh.mengm.utils.ToolbarUtils;

import javax.inject.Inject;
import butterknife.BindView;

public class WeatherActivity extends BaseActivity implements WeatherActivityContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipe)
    SwipeRefreshLayout mSwipe;
    @Inject
    WeatherActivityPresenter mPresenter;
    private WeatherAdapter adapter;
    private WeatherAllData mData = new WeatherAllData();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getWeatherData("杭州");
    }

    @Override
    public void showError(String s) {
        toast(s);
    }

    @Override
    public void complete() {

    }

    @Override
    public void showWeatherData(WeatherAllData data) {
        mData=data;
        ToolbarUtils.initTitle(toolbar,R.mipmap.ab_back,mData.getHeWeather5().get(0).getBasic().getCity(), this);
        adapter=new WeatherAdapter(mData);
        RecyclerViewUtil.initNoDecoration(this,mRecyclerView,adapter);
    }
}
