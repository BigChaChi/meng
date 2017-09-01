package com.wangdh.mengm.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.bean.HealthitemData;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerFragmentComponent;
import com.wangdh.mengm.ui.Presenter.HealthFragmentPresenter;
import com.wangdh.mengm.ui.adapter.HealthFragmentAdapter;
import com.wangdh.mengm.ui.contract.HealthFragmentContract;
import com.wangdh.mengm.utils.RecyclerViewUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;


public class HealthFragment extends BaseFragment implements HealthFragmentContract.View {
    @BindView(R.id.ken)
    KenBurnsView imag;
    @BindView(R.id.recycler_health)
    RecyclerView recycler;
    @Inject
    HealthFragmentPresenter mPresenter;
    private HealthFragmentAdapter adapter;
    private List<HealthitemData.ShowapiResBodyBean.ListBean> itemdata = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_health;
    }

    @Override
    protected void initData() {
        showDialog();
        mPresenter.attachView(this);
        mPresenter.getHealthData();
    }

    @Override
    protected void initView() {
        adapter=new HealthFragmentAdapter(itemdata);
        RecyclerViewUtil.StaggeredGridinit(recycler,adapter);
    }

    @Override
    public void showError(String s) {
        hideDialog();
        toast(s);
    }

    @Override
    public void complete() {
        dismissDialog();
    }

    @Override
    public void showHealthData(HealthitemData data) {
        Log.i("toast",data.getShowapi_res_body().getList().get(2).getName());
        itemdata.addAll(data.getShowapi_res_body().getList());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
