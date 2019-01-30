package com.yaer.remittance.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.bean.AppMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.update.UpdateManager;
import com.yaer.remittance.utils.ActivityManager;
import com.yaer.remittance.utils.AppManager;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;

import java.text.ParseException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 * Created by geyifeng on 2017/5/9.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    public static Context context;
    private Long time;
    private int version;
    private String Url = "http://www.paiphui.com/app-release.apk";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        time = System.currentTimeMillis();
        setContentView(setLayoutId());
        context = this;
        //绑定控件
        unbinder = ButterKnife.bind(this);
        ActivityManager.getActivityManager().add(this);
        AppManager.getAppManager().addActivity(this);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        Bundle bundle = getIntent().getExtras();
        initParms(bundle);
        //初始化数据
        initData();
        //view与数据绑定
        initView();

        //设置监听
        setListener();
        Log.d("loading activity time->", "加载activity" + this.getClass().getName() +
                " 用的时间 :" + (System.currentTimeMillis() - time) + "(ms)");
    }


    /**
     * [初始化Bundle参数]
     *
     * @param parms
     */
    protected void initParms(Bundle parms) {

    }

    // BaseActivity中统一调用MobclickAgent 类的 onResume/onPause 接口
    // 子类中无需再调用
    @Override
    protected void onResume() {
        super.onResume();
        /*   MobclickAgent.onResume(this); // 基础指标统计，不能遗漏*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        // MobclickAgent.onPause(this); // 基础指标统计，不能遗漏
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //每个页面从后台被唤醒\
        version = SystemUtil.packageCode(context);
        //getApp();
    }

    @Override
    protected void onDestroy() {
        LoadingDialog.hide();
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(), this);
        super.onDestroy();
        unbinder.unbind();
        ActivityManager.getActivityManager().remove(this);
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }

    protected abstract int setLayoutId();

    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    /**
     * 组件初始化
     */
    public abstract void initView();

    /**
     * 赋值
     */
    public abstract void initData();


    protected void setListener() {
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

    public static void fishActivity(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppManager.getAppManager().finishActivity(activity);
            }
        }, 300);
    }

    public static void goToActivity(Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }

    public static void goToActivity(Class<?> clazz, final Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    /**
     * 启动一个activity
     *
     * @param cls 目标类
     */
    public static void goToActivity(Class<?> cls, String key, Object data) {
        Intent intent = new Intent();
        String type = data.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            intent.putExtra(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            intent.putExtra(key, (Boolean) data);
        } else if ("String".equals(type)) {
            intent.putExtra(key, (String) data);
        } else if ("Float".equals(type)) {
            intent.putExtra(key, (Float) data);
        } else if ("Long".equals(type)) {
            intent.putExtra(key, (Long) data);
        }
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    private void getApp() {
        OkGo.<BaseMode<AppMode>>post(AppApi.BASE_URL + AppApi.GETAPPVERSION)
                .tag(this)
                .execute(new JsonCallback<BaseMode<AppMode>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<AppMode>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (response.body().result != null) {
                                if (response.body().result.getAndroidv() != version) {
                                    //判断第一次缓存的版本 是否为空 如果为空则更新 否则
                                    if (SharedPreferencesUtils.getData(context, "version", "").equals("")) {
                                        if (isFinishing()) {
                                            UpdateManager.showNoticeDialog(BaseActivity.this, response.body().result.getVdesc(), Url,
                                                    response.body().result.getAndroidvname());
                                        }
                                    } else {
                                        //判断拒绝更新的版本是否与服务器的版本相等 如果相等则不更新，如果不等则更新
                                        if (SharedPreferencesUtils.getData(BaseActivity.this, "version", "").equals(response.body().result.getAndroidvname())) {
                                            return;
                                        } else {
                                            if (isFinishing()) {
                                                UpdateManager.showNoticeDialog(BaseActivity.this, response.body().result.getVdesc(), Url,
                                                        response.body().result.getAndroidvname());
                                            }
                                        }
                                    }
                                    ToastUtils.showToast("请求更新");
                                } else {
                                    UpdateManager.showNoticeDialog(BaseActivity.this, response.body().result.getVdesc(), Url,
                                            response.body().result.getAndroidvname());
                                    ToastUtils.showToast("不用更新");
                                }
                            }
                        }
                    }
                });
    }
}
