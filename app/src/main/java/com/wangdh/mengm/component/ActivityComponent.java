package com.wangdh.mengm.component;

import com.wangdh.mengm.ui.activity.JokeActivity;
import com.wangdh.mengm.ui.activity.MeizhiPictureActivity;
import com.wangdh.mengm.ui.activity.SplashActivity;
import com.wangdh.mengm.ui.activity.VideoListActivity;
import com.wangdh.mengm.ui.activity.WeChatListActivity;

import dagger.Component;

/**
 * wdh
 */
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    SplashActivity inject(SplashActivity activity);
    WeChatListActivity inject(WeChatListActivity activity);
    JokeActivity inject(JokeActivity activity);
    MeizhiPictureActivity inject(MeizhiPictureActivity activity);
    VideoListActivity inject(VideoListActivity activity);
}
