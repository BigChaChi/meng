package com.wangdh.mengm.component;

import com.wangdh.mengm.ui.activity.SplashActivity;

import dagger.Component;

/**
 * wdh
 */
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    SplashActivity inject(SplashActivity activity);
}
