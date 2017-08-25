package com.wangdh.mengm.ui.fragment;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.component.AppComponent;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.imageView)
    CircleImageView imageView;
    @BindView(R.id.my_tv)
    TextView myTv;
    @BindView(R.id.coll_layout)
    CollapsingToolbarLayout collLayout;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        intToolbar();

    }

    private void intToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
       // toolbar.setNavigationIcon(R.drawable.ximalogo);
        toolbar.setTitle("喜马拉雅");
    }
}
