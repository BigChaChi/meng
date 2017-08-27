package com.wangdh.mengm.ui.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangdh.mengm.R;
import com.wangdh.mengm.bean.WeChatListData;
import com.wangdh.mengm.utils.MyGlideImageLoader;

import java.util.List;

public class WechatListAdapter extends BaseQuickAdapter<WeChatListData.ShowapiResBodyBean.PagebeanBean.ContentlistBean,BaseViewHolder>{
    public WechatListAdapter(@Nullable List<WeChatListData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data) {
        super(R.layout.item_wechatlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WeChatListData.ShowapiResBodyBean.PagebeanBean.ContentlistBean item) {
        helper.setText(R.id.tv_wechatitem,item.getTitle());
        MyGlideImageLoader.displayImage(item.getContentImg(),helper.getView(R.id.iv_wecharitem));

    }
}
