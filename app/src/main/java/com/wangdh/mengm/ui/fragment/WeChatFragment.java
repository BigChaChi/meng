package com.wangdh.mengm.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.bean.WeChatData;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.ui.adapter.WeChatItemAdapter;
import com.wangdh.mengm.utils.RecyclerViewUtil;
import com.wangdh.mengm.utils.SharedPreferencesMgr;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WeChatFragment extends BaseFragment {
    @BindView(R.id.imag_wechat)
    ImageView imagWechat;
    @BindView(R.id.recycler_wechat)
    RecyclerView recyclerWechat;
    private WeChatItemAdapter adapter;
    private List<WeChatData> Datas = new ArrayList<>();
    private Gson mGson = new Gson();
    private String wechat_item="wechat_item";
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initData() {
        String selectTitle = SharedPreferencesMgr.getString(wechat_item, "");
        if (TextUtils.isEmpty(selectTitle)) {
            String[] titleStr = getResources().getStringArray(R.array.wechat_name);
            String[] titlesCode = getResources().getStringArray(R.array.wechat_type);
            //默认添加了全部频道
            for (int i = 0; i < titlesCode.length; i++) {
                String t = titleStr[i];
                String code = titlesCode[i];
                Datas.add(new WeChatData(t, code));
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
}
