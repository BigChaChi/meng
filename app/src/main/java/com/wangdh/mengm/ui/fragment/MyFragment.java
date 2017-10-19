package com.wangdh.mengm.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseFragment;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.utils.StorageData;
import com.wangdh.mengm.widget.MyLayoutView;
import com.wangdh.mengm.widget.PinchImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.imageView)
    CircleImageView imageView;
    @BindView(R.id.my_tv)
    TextView myTv;
    @BindView(R.id.coll_layout)
    CollapsingToolbarLayout collLayout;
    @BindView(R.id.my_toolbar)
    Toolbar toolbar;
    @BindView(R.id.myview1)
    MyLayoutView v1;
    private Dialog dialog;
    private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    // 创建一个以当前时间为名称的文件
    File tempFile = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initData() {
        v1.initViewData(R.mipmap.ic_launcher, "打呼哈");
        StorageData.setHeadImage(imageView,StorageData.getString(), getContext());  //头像
    }

    @Override
    protected void initView() {
        intToolbar();
        imageView.setOnClickListener(v -> Dialog());
    }

    private void Dialog() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.photo_choose_dialog, null);
        initViews(view);
        dialog = new Dialog(getContext(), R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        getActivity().setFinishOnTouchOutside(true);
        dialog.show();
    }

    private void initViews(final View view) {
        Button mBt1, mBt2, mBt3, mBt4;
        mBt1 = (Button) view.findViewById(R.id.bt);
        mBt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 调用系统的拍照功能
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 指定调用相机拍照后照片的储存路径
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOTO);
            }
        });
        mBt2 = (Button) view.findViewById(R.id.bt2);
        mBt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });
        mBt3 = (Button) view.findViewById(R.id.bt3);
        mBt3.setOnClickListener(new View.OnClickListener() {        //放大图片
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
                dialog.show();
                Window window = dialog.getWindow();
                window.setContentView(R.layout.imageshower);
                PinchImageView dialogIv = (PinchImageView) dialog.findViewById(R.id.dialogImage);
                dialogIv.setImageDrawable(imageView.getDrawable());

                dialogIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        mBt4 = (Button) view.findViewById(R.id.qx);
        mBt4.setOnClickListener(new View.OnClickListener() {    //取消dialog
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_REQUEST_TAKEPHOTO:
                startPhotoZoom(Uri.fromFile(tempFile), 150);
                break;
            case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData(), 150);
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null)
                    setPicToView(data);
                break;
        }

    }

    private void startPhotoZoom(Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    private String urlpath;

    //将进行剪裁后的图片显示到UI界面上
    private void setPicToView(Intent picdata) {
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
            Bitmap photo = bundle.getParcelable("data");
            imageView.setImageBitmap(photo);
            urlpath = StorageData.saveFile(getContext(), "temphead.jpg", photo);
            StorageData.storePath(urlpath);
        }
    }

    // 使用系统当前日期加以调整作为照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    private void intToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        // toolbar.setNavigationIcon(R.drawable.ximalogo);
        toolbar.setTitle("喜马拉雅");
    }


}
