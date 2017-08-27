package com.wangdh.mengm.component;

import com.wangdh.mengm.ui.fragment.WeChatFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    WeChatFragment inject(WeChatFragment fragment);
}
