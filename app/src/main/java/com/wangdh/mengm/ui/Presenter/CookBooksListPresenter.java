package com.wangdh.mengm.ui.Presenter;

import com.wangdh.mengm.api.RetrofitManager;
import com.wangdh.mengm.base.Constant;
import com.wangdh.mengm.base.RxPresenter;
import com.wangdh.mengm.bean.CookBookslistData;
import com.wangdh.mengm.ui.contract.CookBooksListContract;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;


public class CookBooksListPresenter extends RxPresenter<CookBooksListContract.View>
                        implements CookBooksListContract.Presenter<CookBooksListContract.View>{
    private RetrofitManager api;
    @Inject
    public CookBooksListPresenter(RetrofitManager api) {
        this.api = api;
    }

    @Override
    public void getCookBooksListData(String classid, String num, String start) {
        Map<String, String> map = new HashMap<>();
        map.put("classid", classid);
        map.put("num", num);
        map.put("start", start);
        map.put("appkey", Constant.jcloudKey);
        api.cookBookslistDataFlowable(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<CookBookslistData>() {
                    @Override
                    public void onNext(CookBookslistData cookBookslistData) {
                      if(cookBookslistData.getCode().equals("10000")&&cookBookslistData!=null){
                          try {
                              mView.showCookBooksListData(cookBookslistData);
                          }catch (Exception e){
                              mView.showError(e.getMessage());
                          }
                      }else {
                          mView.showError("数据加载失败");
                      }
                    }
                    @Override
                    public void onError(Throwable t) {
                        mView.showError(t.getMessage());
                    }
                    @Override
                    public void onComplete() {
                        mView.complete();
                    }
                });
    }
}
