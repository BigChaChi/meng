package com.wangdh.mengm.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
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

    //Glide 动态设置图片宽高
    public static void loadIntoUseFitWidth( final String imageUrl, final ImageView imageView,float f) {
        Glide.with(mContext)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        int vh = (int) ((float)vw/(float) f);//自己设置比例
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    //等比例缩放图片至屏幕宽度
    public static void loadImage(String url,ImageView imageView) {
        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super
                    Bitmap> glideAnimation) {
                int imageWidth = resource.getWidth();
                int imageHeight = resource.getHeight();
                int height = ScreenUtils.getScreenWidth() * imageHeight / imageWidth;
                ViewGroup.LayoutParams para = imageView.getLayoutParams();
                para.height = height;
                para.width = ScreenUtils.getScreenWidth();
                imageView.setImageBitmap(resource);
            }
        });
    }
    /**
     * 需要在子线程执行
     *
     * @param url
     * @return
     */
    public static Bitmap load(String url) {
        try {
            return Glide.with(mContext)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //加载网络图片并设置大小
    public static void displayImage(String url, ImageView imageView, int width, int height) {
        Glide
                .with(mContext)
                .load(url)
                .override(width, height)
                .crossFade()
                .into(imageView);
    }
}
