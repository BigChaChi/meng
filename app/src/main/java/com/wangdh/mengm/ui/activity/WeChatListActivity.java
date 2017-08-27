package com.wangdh.mengm.ui.activity;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.bean.WeChatListData;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerActivityComponent;
import com.wangdh.mengm.ui.Presenter.WechatListPresenter;
import com.wangdh.mengm.ui.adapter.WechatListAdapter;
import com.wangdh.mengm.ui.contract.WechatListContract;
import com.wangdh.mengm.utils.RecyclerViewUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * 微信文章列表
 */

public class WeChatListActivity extends BaseActivity implements WechatListContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private String type;
    @Inject
    WechatListPresenter mPresenter;
    private List<WeChatListData.ShowapiResBodyBean.PagebeanBean.ContentlistBean> itemdata = new ArrayList<>();
    private WechatListAdapter adapter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_wechatlist;
    }

    @Override
    protected void initView() {
        adapter = new WechatListAdapter(itemdata);
        RecyclerViewUtil.StaggeredGridinit(this,recycler,adapter);

    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("wechattype");
        mPresenter.attachView(this);
        mPresenter.getWechatlistDta(type, 1);
    }

    @Override
    public void showError(String s) {
        toast(s);
    }

    @Override
    public void complete() {

    }

    @Override
    public void showWechatlistDta(WeChatListData data) {
        itemdata.addAll(data.getShowapi_res_body().getPagebean().getContentlist());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if ((mPresenter != null)) {
            mPresenter.detachView();
        }
    }
}
