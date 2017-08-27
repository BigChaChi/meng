package com.wangdh.mengm.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.bean.WeChatData;
import com.wangdh.mengm.bean.WechatImage;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.component.DaggerFragmentComponent;
import com.wangdh.mengm.ui.Presenter.WeChatFragmentPresenter;
import com.wangdh.mengm.ui.activity.WeChatListActivity;
import com.wangdh.mengm.ui.adapter.WeChatItemAdapter;
import com.wangdh.mengm.ui.contract.WeChatFragmentContract;
import com.wangdh.mengm.utils.MyGlideImageLoader;
import com.wangdh.mengm.utils.RecyclerViewUtil;
import com.wangdh.mengm.utils.SharedPreferencesMgr;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class WeChatFragment extends BaseFragment implements WeChatFragmentContract.View {
    @BindView(R.id.tv_bt)
    TextView mTv;
    @BindView(R.id.imag_wechat)
    KenBurnsView imagWechat;
    @BindView(R.id.recycler_wechat)
    RecyclerView recyclerWechat;
    private WeChatItemAdapter adapter;
    private List<WeChatData> Datas = new ArrayList<>();
    private Gson mGson = new Gson();
    private String wechat_item = "wechat_item";
    @Inject
    WeChatFragmentPresenter mPresenter;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getImageData();
        String selectTitle = SharedPreferencesMgr.getString(wechat_item, "");
        if (TextUtils.isEmpty(selectTitle)) {
            String[] titleStr = getResources().getStringArray(R.array.wechat_name);
            String[] titlesCode = getResources().getStringArray(R.array.wechat_type);
            String[] titlesTypeId = getResources().getStringArray(R.array.wechat_typeid);
            //默认添加了全部频道
            for (int i = 0; i < titlesCode.length; i++) {
                String t = titleStr[i];
                String code = titlesCode[i];
                String type = titlesTypeId[i];
                Datas.add(new WeChatData(t, code, type));
            }
            String selectedStr = mGson.toJson(Datas);
            SharedPreferencesMgr.setString(wechat_item, selectedStr);
        } else {
            //之前添加过
            List<WeChatData> selecteData = mGson.fromJson(selectTitle, new TypeToken<List<WeChatData>>() {
            }.getType());
            Datas.addAll(selecteData);
        }
    }

    @Override
    protected void initView() {
        adapter = new WeChatItemAdapter(Datas);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerWechat);
        // 开启拖拽
        adapter.enableDragItem(itemTouchHelper);
        adapter.setOnItemDragListener(onItemDragListener);
        RecyclerViewUtil.Gridinit(getActivity(), recyclerWechat, adapter);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            Intent intent = new Intent(getActivity(), WeChatListActivity.class);
            intent.putExtra("wechattype", Datas.get(position).getType());
            startActivity(intent);
        });
    }

    private OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            WeChatData data = Datas.get(pos);
            Datas.remove(pos);
            Datas.add(pos, data);
            //保存
            SharedPreferencesMgr.setString(wechat_item, mGson.toJson(Datas));
        }
    };

    @Override
    public void showError(String s) {
        toast(s);
    }

    @Override
    public void complete() {

    }

    @Override
    public void showImageData(WechatImage data) {
        mTv.setText(data.getShowapi_res_body().getData().getCopyright());
        Log.i("toast",data.getShowapi_res_body().getData().getImg_1366());
        MyGlideImageLoader.displayImage(data.getShowapi_res_body().getData().getImg_1366(), imagWechat);
        imagWechat.setTransitionListener(new KenBurnsView.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
