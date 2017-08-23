package com.wangdh.mengm.api;

import com.wangdh.mengm.bean.CelebratedDictum;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * wdh
 */

public interface ApiService {

    @GET("http://api.avatardata.cn/MingRenMingYan/Random?key=7bc718abfae24a7294810102ac7538c3")
    Flowable<CelebratedDictum> splashRxjava();
}
