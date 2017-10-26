package com.wangdh.mengm.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.bean.WeatherAllData;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerActivityComponent;
import com.wangdh.mengm.manager.EventManager;
import com.wangdh.mengm.ui.Presenter.WeatherActivityPresenter;
import com.wangdh.mengm.ui.adapter.WeatherAdapter;
import com.wangdh.mengm.ui.contract.WeatherActivityContract;
import com.wangdh.mengm.utils.RecyclerViewUtil;
import com.wangdh.mengm.utils.StateBarTranslucentUtils;
import com.wangdh.mengm.utils.ToolbarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

public class WeatherActivity extends BaseActivity implements WeatherActivityContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mSwipe)
    SwipeRefreshLayout mSwipe;
    @BindView(R.id.coord)
    CoordinatorLayout mCoord;
    @Inject
    WeatherActivityPresenter mPresenter;
    private WeatherAdapter adapter;
    private WeatherAllData mData = new WeatherAllData();
    private String city = "杭州";

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
        mSwipe.setColorSchemeResources(R.color.colorPrimaryDark2, R.color.btn_blue, R.color.ywlogin_colorPrimaryDark);//设置进度动画的颜色
        mSwipe.setProgressViewOffset(true, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        mSwipe.setOnRefreshListener(() -> {
            setDataRefresh(true);
            mPresenter.getWeatherData(city);
        });
        setDataRefresh(true);
    }

    @Override
    protected void initData() {
        StateBarTranslucentUtils.setStateBarColor(this);
        ToolbarUtils.initTitle(toolbar, R.mipmap.ab_back, city, this);
        EventBus.getDefault().register(this);
        mPresenter.attachView(this);
        mPresenter.getWeatherData(city);
    }

    @Override
    public void showError(String s) {
        setDataRefresh(false);
        toast(s);
    }

    private void setDataRefresh(boolean refresh) {
        if (refresh) {
            mSwipe.setRefreshing(refresh);
        } else {
            new Handler().postDelayed(() -> mSwipe.setRefreshing(refresh), 800);//延时消失加载的loading
        }
    }

    @Override
    public void complete() {
        setDataRefresh(false);
    }

    @Override
    public void showWeatherData(WeatherAllData data) {
        mData = data;
        try {
            ToolbarUtils.initTitle(toolbar, R.mipmap.ab_back, mData.getHeWeather5().get(0).getBasic().getCity(), this);
        }catch (NullPointerException e){
            e.getMessage();
        }

        adapter = new WeatherAdapter(mData);
        RecyclerViewUtil.initNoDecoration(this, mRecyclerView, adapter);
        int code=mData.getHeWeather5().get(0).getNow().getCond().getCode();

        if (code==100){
            mCoord.setBackgroundColor(ContextCompat.getColor(this,R.color.colorSendName6));
        }else if(code==104){
            mCoord.setBackgroundColor(ContextCompat.getColor(this,R.color.color_54698a));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_search) {
            startActivity(new Intent(this, SearchWeatherActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void RefreshCollectionList(EventManager event) {
        mData.setHeWeather5(null);
        mSwipe.setRefreshing(true);
        city = event.message;
        mPresenter.getWeatherData(event.message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
