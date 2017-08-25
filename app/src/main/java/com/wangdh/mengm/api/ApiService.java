package com.wangdh.mengm.api;

import com.wangdh.mengm.bean.CelebratedDictum;
import com.wangdh.mengm.bean.NewListData;
import com.wangdh.mengm.bean.WeChatListData;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * wdh
 */

public interface ApiService {
    //http://route.showapi.com/582-2?page=1&showapi_appid=44640&showapi_sign=f255043723fe40839e61f6a40a6b0741//微信精选
    // https://way.jd.com/jisuapi/get?channel=%E5%A4%B4%E6%9D%A1&num=10&start=0&appkey=6d119cf4202fec65d699ebb68d1d6e5f//新闻
    @GET("http://api.avatardata.cn/MingRenMingYan/Random?key=7bc718abfae24a7294810102ac7538c3")
    Flowable<CelebratedDictum> splashRxjava();

    @GET("https://way.jd.com/jisuapi/get")
    Flowable<NewListData> newListDataRxjava(@QueryMap Map<String, String> params);

    @GET("http://route.showapi.com/582-2")
    Flowable<WeChatListData> weCharListDataRxjava(@QueryMap Map<String, String> params);
}
