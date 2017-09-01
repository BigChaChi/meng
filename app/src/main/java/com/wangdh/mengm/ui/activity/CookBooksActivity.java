package com.wangdh.mengm.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.bean.CookBookslistData;
import com.wangdh.mengm.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CookBooksActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private List<CookBookslistData.ResultBeanX.ResultBean.ListBean> listdata=new ArrayList<>();
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
