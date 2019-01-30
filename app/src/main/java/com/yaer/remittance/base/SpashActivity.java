package com.yaer.remittance.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.bean.AppMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.update.UpdateManager;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ImageManager;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;

public class SpashActivity extends BaseActivity {
    private int version;
    private String Url = "http://www.paiphui.com/app-release.apk";

    @Override
    public void initView() {
        if (!isTaskRoot()) {
            finish();
            return;
        }
        onNext();
        //获取当前版本code
        version = SystemUtil.packageCode(context);
        //getApp();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.spash_act;
    }

    @Override
    public void initData() {

    }

    private void getApp() {
        OkGo.<BaseMode<AppMode>>post(AppApi.BASE_URL + AppApi.GETAPPVERSION)
                .tag(this)
                .execute(new JsonCallback<BaseMode<AppMode>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<AppMode>> response) {
                        if (AppUtile.isEmptyNull(response.body().result)) {
                            onNext();
                        } else {
                            //200
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                if (response.body().result != null) {
                                    if (response.body().result.getAndroidv() != version) {
                                        ToastUtils.showToast("当前版本不一样去更新！");
                                    } else if (response.body().result.getState() == 1) {
                                        ToastUtils.showToast("强制更新！");
                                    } else {
                                        onNext();
                                    }
                                } else {
                                    onNext();
                                }
                            } else {
                                //!200
                                onNext();
                                ToastUtils.showToast(response.body().msg);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<AppMode>> response) {
                        super.onError(response);
                        onNext();
                    }
                });
    }

    /**
     * 下一步操作
     */
    private void onNext() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((int) SharedPreferencesUtils.getData(context, "yidaoye", 0) == 0) {
                    Intent intent = new Intent(context, GuideActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                }
            }
        }, 100);
    }
}
