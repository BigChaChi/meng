package com.wangdh.mengm.ui.Presenter;

import android.util.Log;

import com.wangdh.mengm.base.RxPresenter;
import com.wangdh.mengm.bean.bmob.AccountBean;
import com.wangdh.mengm.ui.contract.RegisterContract;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterPresenter extends RxPresenter<RegisterContract.View>implements RegisterContract.Presenter<RegisterContract.View>{


    @Override
    public void getRegister(String name,String password,String phone) {
        AccountBean bean=new AccountBean();
        bean.setName(name);
        bean.setPassword(password);
        bean.setPhone(phone);
        bean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    mView.complete();
                }else{
                    if (e.getMessage().equals("unique index cannot has duplicate value: "+name)) {
                        mView.showError("您输入的账户已被注册");
                    }else {
                        mView.showError("注册失败");
                    }
                    Log.i("toast","失败"+e.getMessage()+"+code+:"+e.getErrorCode());
                }
            }
        });
    }
}
