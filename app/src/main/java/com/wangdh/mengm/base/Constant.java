package com.wangdh.mengm.base;

import android.graphics.Color;
import android.os.Environment;

public class Constant {
    public static final String BaseUrl="http://route.showapi.com/";
    public static final String jcloudKey="6d119cf4202fec65d699ebb68d1d6e5f";
    public static final String showapi_sign="f255043723fe40839e61f6a40a6b0741";
    public static final String showapi_appid="44640";
    public static final String weatherKey="65f888e8c8ef49539f89a249a5e296ed";
    public static final String SAVED_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/meng";

    public static final int[] tagColors = new int[]{
            Color.parseColor("#90C5F0"),
            Color.parseColor("#91CED5"),
            Color.parseColor("#F88F55"),
            Color.parseColor("#C0AFD0"),
            Color.parseColor("#E78F8F"),
            Color.parseColor("#67CCB7"),
            Color.parseColor("#F6BC7E")
    };
}
