package com.wangdh.mengm.ui.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.bean.CelebratedDictum;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerActivityComponent;
import com.wangdh.mengm.ui.Presenter.SplashPresenter;
import com.wangdh.mengm.ui.contract.SplashContract;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * wdh 启动页
 * Created by Administrator on 2017/8/22.
 */

public class SplashActivity extends BaseActivity implements SplashContract.View {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.textskip)
    TextView textskip;
    @BindView(R.id.tvtime)
    TextView tvtime;
    @BindView(R.id.frame_skip)
    FrameLayout frameSkip;
    private CountDownTimer time;
    @Inject
    SplashPresenter mPresenter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        time = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvtime.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        };
        time.start();
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getSplashDta();
    }

    @OnClick(R.id.frame_skip)
    public void onViewClicked() {
        if (time != null) {
            time.cancel();
        }
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showError(String s) {
        toast(s);
    }

    @Override
    public void complete() {
    }

    @Override
    public void showSplashData(CelebratedDictum dictum) {
        textView.setText(dictum.getResult().getFamous_saying());
        name.setText("─ "+dictum.getResult().getFamous_name());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (time != null) {
            time.cancel();
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
