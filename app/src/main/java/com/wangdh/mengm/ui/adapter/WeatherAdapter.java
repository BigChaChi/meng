package com.wangdh.mengm.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangdh.mengm.R;
import com.wangdh.mengm.bean.WeatherAllData;
import com.wangdh.mengm.utils.MyGlideImageLoader;

import java.util.List;

import butterknife.BindView;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private static final int TYPE_THREE = 2;
    private static final int TYPE_FORE = 3;
    private static final int TYPE_FIVE = 4;
    private WeatherAllData data;

    public WeatherAdapter(WeatherAllData weatherData) {
        this.data = weatherData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == WeatherAdapter.TYPE_ONE) {
            return WeatherAdapter.TYPE_ONE;
        }
        if (position == WeatherAdapter.TYPE_TWO) {
            return WeatherAdapter.TYPE_TWO;
        }
        if (position == WeatherAdapter.TYPE_THREE) {
            return WeatherAdapter.TYPE_THREE;
        }
        if (position == WeatherAdapter.TYPE_FORE) {
            return WeatherAdapter.TYPE_FORE;
        }
        if (position == WeatherAdapter.TYPE_FIVE) {
            return WeatherAdapter.TYPE_FIVE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        switch (viewType) {
            case TYPE_ONE:
                return new NowWeatherViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_one, parent, false));
            case TYPE_TWO:
                return new HoursWeatherViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_two, parent, false));
            case TYPE_THREE:
                return new FutureViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_three, parent, false));
            case TYPE_FORE:
                return new TodayViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_fore, parent, false));
            case TYPE_FIVE:
                return new SuggestionViewHolder(
                        LayoutInflater.from(mContext).inflate(R.layout.item_weather_one, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case TYPE_ONE:
                ((NowWeatherViewHolder) holder).bind(data);
                break;
            case TYPE_TWO:
                ((HoursWeatherViewHolder) holder).bind(data);
                break;
            case TYPE_THREE:
                ((FutureViewHolder) holder).bind(data);
                break;
            case TYPE_FORE:
                ((TodayViewHolder) holder).bind(data);
                break;
            case TYPE_FIVE:
                ((SuggestionViewHolder) holder).bind(data);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? 4 : 0;
    }


    class NowWeatherViewHolder extends BaseViewHolder<WeatherAllData> {
        @BindView(R.id.tv_wd)
        TextView mTv;
        @BindView(R.id.tv_q)
        TextView mTq;
        @BindView(R.id.tv_zl)
        TextView mTzl;
        @BindView(R.id.temp_max)
        TextView Tmax;
        @BindView(R.id.temp_min)
        TextView Tmin;
        @BindView(R.id.iv)
        ImageView mIv;

        NowWeatherViewHolder(View view) {
            super(view);
        }

        @Override
        protected void bind(WeatherAllData weather) {
            try {
                mTv.setText(String.format("%s℃", weather.getHeWeather5().get(0).getNow().getTmp()));
                mTq.setText(weather.getHeWeather5().get(0).getNow().getCond().getTxt());
                mTzl.setText(String.format("空气质量:%s", weather.getHeWeather5().get(0).getAqi().getCity().getQlty()));
                Tmax.setText(String.format("↑%s℃", weather.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMax()));
                Tmin.setText(String.format("↓%s℃", weather.getHeWeather5().get(0).getDaily_forecast().get(0).getTmp().getMin()));
                mIv.setImageResource(image());
            } catch (Exception e) {
                Log.i("toast", e.getMessage());
            }
        }
    }

    class FutureViewHolder extends BaseViewHolder<WeatherAllData> {
        @BindView(R.id.t1)
        TextView t1;
        @BindView(R.id.i1)
        ImageView i1;
        @BindView(R.id.wd1)
        TextView wd1;
        @BindView(R.id.t2)
        TextView t2;
        @BindView(R.id.i2)
        ImageView i2;
        @BindView(R.id.wd2)
        TextView wd2;
        @BindView(R.id.t3)
        TextView t3;
        @BindView(R.id.i3)
        ImageView i3;
        @BindView(R.id.wd3)
        TextView wd3;
        @BindView(R.id.tq1)
        TextView tq1;
        @BindView(R.id.tq2)
        TextView tq2;
        @BindView(R.id.tq3)
        TextView tq3;

        public FutureViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(WeatherAllData weatherAllData) {
            List<WeatherAllData.HeWeather5Bean.DailyForecastBean> data = weatherAllData.getHeWeather5().get(0).getDaily_forecast();
            t1.setText(String.format("今天/%s", data.get(0).getDate().substring(data.get(0).getDate().length() - 5)));
            i1.setImageResource(image());
            tq1.setText(data.get(0).getCond().getCode_d() + "/" + data.get(0).getWind().getDir());
            wd1.setText(data.get(0).getTmp().getMax() + "℃～" + data.get(0).getTmp().getMin() + "℃");

            t2.setText(String.format("明天/%s", data.get(1).getDate().substring(data.get(1).getDate().length() - 5)));
            i2.setImageResource(image());
            tq2.setText(data.get(1).getCond().getCode_d() + "/" + data.get(1).getWind().getDir());
            wd2.setText(data.get(1).getTmp().getMax() + "℃～" + data.get(1).getTmp().getMin() + "℃");

            t3.setText(String.format("后天/%s", data.get(2).getDate().substring(data.get(2).getDate().length() - 5)));
            i3.setImageResource(image());
            tq3.setText(data.get(2).getCond().getCode_d() + "/" + data.get(2).getWind().getDir());
            wd3.setText(data.get(2).getTmp().getMax() + "℃～" + data.get(2).getTmp().getMin() + "℃");
        }
    }

    class SuggestionViewHolder extends BaseViewHolder<WeatherAllData> {

        SuggestionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(WeatherAllData weatherAllData) {

        }
    }

    class TodayViewHolder extends BaseViewHolder<WeatherAllData> {

        TodayViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(WeatherAllData weatherAllData) {

        }
    }

    class HoursWeatherViewHolder extends BaseViewHolder<WeatherAllData> {
        @BindView(R.id.tv_time1)
        TextView mT1;
        @BindView(R.id.iv_w1)
        ImageView mIv1;
        @BindView(R.id.tv_tq1)
        TextView mTq1;
        @BindView(R.id.tv_f1)
        TextView mTf1;
        @BindView(R.id.tv_time2)
        TextView mT2;
        @BindView(R.id.iv_w2)
        ImageView mIv2;
        @BindView(R.id.tv_tq2)
        TextView mTq2;
        @BindView(R.id.tv_f2)
        TextView mTf2;
        @BindView(R.id.tv_time3)
        TextView mT3;
        @BindView(R.id.iv_w3)
        ImageView mIv3;
        @BindView(R.id.tv_tq3)
        TextView mTq3;
        @BindView(R.id.tv_f3)
        TextView mTf3;
        @BindView(R.id.tv_time4)
        TextView mT4;
        @BindView(R.id.iv_w4)
        ImageView mIv4;
        @BindView(R.id.tv_tq4)
        TextView mTq4;
        @BindView(R.id.tv_f4)
        TextView mTf4;
        @BindView(R.id.tv_time5)
        TextView mT5;
        @BindView(R.id.iv_w5)
        ImageView mIv5;
        @BindView(R.id.tv_tq5)
        TextView mTq5;
        @BindView(R.id.tv_f5)
        TextView mTf5;
        @BindView(R.id.tv_wd1)
        TextView mTwd1;
        @BindView(R.id.tv_wd2)
        TextView mTwd2;
        @BindView(R.id.tv_wd3)
        TextView mTwd3;
        @BindView(R.id.tv_wd4)
        TextView mTwd4;
        @BindView(R.id.tv_wd5)
        TextView mTwd5;
        @BindView(R.id.ll1)
        LinearLayout mLl1;
        @BindView(R.id.ll2)
        LinearLayout mLl2;
        @BindView(R.id.ll3)
        LinearLayout mLl3;
        @BindView(R.id.ll4)
        LinearLayout mLl4;
        @BindView(R.id.ll5)
        LinearLayout mLl5;
        HoursWeatherViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(WeatherAllData weatherAllData) {   //此处可用RecyclerView
            try {
                List<WeatherAllData.HeWeather5Bean.HourlyForecastBean> data = weatherAllData.getHeWeather5().get(0).getHourly_forecast();
                if(data.size()==0){
                    mLl1.setVisibility(View.GONE);
                    mLl2.setVisibility(View.GONE);
                    mLl3.setVisibility(View.GONE);
                    mLl4.setVisibility(View.GONE);
                    mLl5.setVisibility(View.GONE);
                }else if(data.size()==1){
                    mLl2.setVisibility(View.GONE);
                    mLl3.setVisibility(View.GONE);
                    mLl4.setVisibility(View.GONE);
                    mLl5.setVisibility(View.GONE);
                }else if(data.size()==2){
                    mLl3.setVisibility(View.GONE);
                    mLl4.setVisibility(View.GONE);
                    mLl5.setVisibility(View.GONE);
                }else if(data.size()==3){
                    mLl4.setVisibility(View.GONE);
                    mLl5.setVisibility(View.GONE);
                }else if(data.size()==4){
                    mLl5.setVisibility(View.GONE);
                }
                String s = data.get(0).getDate().substring(data.get(0).getDate().length() - 5);
                mT1.setText(s);
                mIv1.setImageResource(image());
                mTq1.setText(data.get(0).getCond().getTxt());
                mTf1.setText(data.get(0).getWind().getDir());
                mTwd1.setText(String.format("%s℃", data.get(0).getTmp()));

                String s2 = data.get(1).getDate().substring(data.get(1).getDate().length() - 5);
                mT2.setText(s2);
                mIv2.setImageResource(image());
                mTq2.setText(data.get(1).getCond().getTxt());
                mTf2.setText(data.get(1).getWind().getDir());
                mTwd2.setText(String.format("%s℃", data.get(1).getTmp()));

                String s3 = data.get(2).getDate().substring(data.get(1).getDate().length() - 5);
                mT3.setText(s3);
                mIv3.setImageResource(image());
                mTq3.setText(data.get(2).getCond().getTxt());
                mTf3.setText(data.get(2).getWind().getDir());
                mTwd3.setText(String.format("%s℃", data.get(2).getTmp()));

                String s4 = data.get(3).getDate().substring(data.get(3).getDate().length() - 5);
                mT4.setText(s4);
                mIv4.setImageResource(image());
                mTq4.setText(data.get(3).getCond().getTxt());
                mTf4.setText(data.get(3).getWind().getDir());
                mTwd4.setText(String.format("%s℃", data.get(3).getTmp()));

                String s5 = data.get(4).getDate().substring(data.get(4).getDate().length() - 5);
                mT5.setText(s5);
                mIv5.setImageResource(image());
                mTq5.setText(data.get(4).getCond().getTxt());
                mTf5.setText(data.get(4).getWind().getDir());
                mTwd5.setText(String.format("%s℃", data.get(4).getTmp()));
            } catch (Exception e) {
                Log.i("toast", e.getMessage());
            }
        }
    }


    private int image() {
        return R.mipmap.w100;
    }
}

