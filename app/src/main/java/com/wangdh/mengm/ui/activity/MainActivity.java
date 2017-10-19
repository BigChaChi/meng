package com.wangdh.mengm.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wangdh.mengm.R;
import com.wangdh.mengm.base.BaseActivity;
import com.wangdh.mengm.base.TabEntity;
import com.wangdh.mengm.component.AppComponent;
import com.wangdh.mengm.ui.fragment.LiveFragment;
import com.wangdh.mengm.ui.fragment.MyFragment;
import com.wangdh.mengm.ui.fragment.NewFragment;
import com.wangdh.mengm.ui.fragment.WeChatFragment;
import com.wangdh.mengm.utils.StorageData;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.RationaleListener;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {
    private static final int REQUEST_CODE_PERMISSION = 100;
    private static final int REQUEST_CODE_SETTING = 300;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.commontab)
    CommonTabLayout mainTab;
    private LinearLayout headerLayout;
    private CircleImageView mIv;
    private TextView mTv1,mTv2,mTv3;
    private ArrayList<Fragment> mFragments ;
    private int[] mIconUnselectIds = {
            R.mipmap.icon_news_un, R.mipmap.icon_wechat_un,
            R.mipmap.icon_find_un, R.mipmap.icon_my_un};
    private int[] mIconSelectIds = {
            R.mipmap.icon_news, R.mipmap.icon_wechat,
            R.mipmap.icon_find, R.mipmap.icon_my};
    private String[] mTitles = {"新闻头条", "微信精选", "生活健康", "我的"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolbar();
        NavigationItemSelectedListener();
        initTab();
    }

    private void initToolbar() {
        toolbar.setTitle("MyApp");
        setSupportActionBar(toolbar);
    }

    private void initTab() {
        mFragments=new ArrayList<>();
        mFragments.add(new NewFragment());
        mFragments.add(new WeChatFragment());
        mFragments.add(new LiveFragment());
        mFragments.add(new MyFragment());
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mainTab.setTabData(mTabEntities, this, R.id.framelayout, mFragments);
        mainTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mainTab.setCurrentTab(position);
                if(position>0){
                    toolbar.setVisibility(View.GONE);
                }else {
                    toolbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mainTab.setCurrentTab(0);
    }

    private void NavigationItemSelectedListener() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close){};//抽屉开关的效果
        mDrawerToggle.syncState();//初始化状态
        drawer.addDrawerListener(mDrawerToggle);
        navView.setNavigationItemSelectedListener((item) -> {
            switch (item.getItemId()) {
                case R.id.weatheritem:
                    startActivity(new Intent(this,WeatherActivity.class));
                    break;
                case R.id.pictureitem:
                    startActivity(new Intent(this,MeizhiPictureActivity.class));
                    break;
                case R.id.videoiitem:
                    startActivity(new Intent(this,VideoListActivity.class));
                    break;
            }
            item.setChecked(true);//点击了把它设为选中状态
            drawer.closeDrawers();//关闭抽屉
            return true;
        });
    }

    @Override
    protected void initData() {
        initPermission();
        String n=getIntent().getStringExtra("n");
        String tit=getIntent().getStringExtra("tit");
        String txt=getIntent().getStringExtra("txt");
        headerLayout = (LinearLayout) navView.inflateHeaderView(R.layout.nav_header_main);
        mIv= (CircleImageView) headerLayout.findViewById(R.id.c_iv);
        mTv1= (TextView) headerLayout.findViewById(R.id.tv_title);
        mTv2= (TextView) headerLayout.findViewById(R.id.tv_name);
        mTv3= (TextView) headerLayout.findViewById(R.id.tv_txt);
        StorageData.setHeadImage(mIv,"", getContext());  //头
        if (n!=null){
            mTv1.setText(tit);
            mTv2.setText(n);
            mTv3.setText(txt);
        }
    }

    private void initPermission() {
        // 申请权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE)
                .callback(permissionListener)
                .rationale(rationaleListener)
                .start();
    }

    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION: {
                    // Toast.makeText(LoginActivity.this, "获取权限成功", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION: {
                    Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(MainActivity.this, REQUEST_CODE_SETTING).show();
            }
        }
    };

    /**
     * Rationale支持，这里自定义对话框。
     */
    private RationaleListener rationaleListener = (requestCode, rationale) -> {

        AlertDialog.newBuilder(this)
                .setTitle("友好提醒")
                .setMessage("你已经拒绝了权限")
                .setPositiveButton("好，给你", (dialog, which) -> {
                    dialog.cancel();
                    rationale.resume();
                })
                .setNegativeButton("我拒绝", (dialog, which) -> {
                    dialog.cancel();
                    rationale.cancel();
                }).show();
    };

    @Override   //权限---用户在系统Setting中操作完成后
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Fragment fragment=getSupportFragmentManager().findFragmentByTag("");
//        fragment.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case REQUEST_CODE_SETTING: {
                //    Toast.makeText(LoginActivity.this, "系统设置中操作完成后", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis();
                toast(getString(R.string.exit_tips));
                return true;
            } else {
                finish(); // 退出
            }
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
