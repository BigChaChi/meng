package com.wangdh.mengm.ui.activity;


import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.widget.FilterImageView;
import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity {
    @BindView(R.id.videoplayer)
    JCVideoPlayerStandard videoplayer;
    @BindView(R.id.filter_iv)
    FilterImageView back;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        back.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.fade_exit, R.anim.hold);
        });

    }

    @Override
    protected void initData() {
        String url = getIntent().getStringExtra("videourl");
        videoplayer.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
       // videoplayer.thumbImageView.setImageResource(R.mipmap.ic_play);
    }

    @Override
    public void onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
