package com.wangdh.mengm.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.wangdh.mengm.R;
import java.io.IOException;
import java.io.InputStream;


public class MyGlideImageLoader  {
    private static Context mContext;
    public static void displayImage(String url, ImageView img){
        mContext = img.getContext();
       // if (NetworkUtil.isWifiConnected(mContext)) {
            if (NetworkUtil.isAvailable(mContext)) {
            loadNormal(url, img);
        } else {
            loadCache(url, img);
        }
    }

    private static void loadNormal(String url, ImageView img) {  //placeholder占位符。错误占位符：.error()
        Glide.with(mContext).load(url).placeholder(R.mipmap.ic_launcher).dontAnimate().//去掉动画
                diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
    }
    private static void loadCache(String url, ImageView img) {
        Glide.with(mContext).using(new StreamModelLoader<String>() {

            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(url).placeholder(R.mipmap.ic_launcher).dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);

    }

}
