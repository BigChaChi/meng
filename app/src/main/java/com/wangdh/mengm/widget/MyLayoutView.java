package com.wangdh.mengm.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangdh.mengm.R;
/**
 * wdh
 */

public class MyLayoutView extends RelativeLayout {
    private  ImageView iv;
    private  TextView tv;
    private Context context;
    public MyLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);

    }

    public void initViewData(int pic, String s) {
        iv.setImageResource(pic);
        tv.setText(s);
    }


    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_myfragment_layout, this);
        iv= (ImageView) view.findViewById(R.id.iv);
        tv= (TextView) view.findViewById(R.id.tv);
    }
}
