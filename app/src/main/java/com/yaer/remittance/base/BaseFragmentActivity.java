package com.yaer.remittance.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.yaer.remittance.utils.ActivityManager;
import com.yaer.remittance.utils.AppManager;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SystemUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    private InputMethodManager imm;
    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(setLayoutId());
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
        context = this;
        //设置监听
        setListener();
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
        //每个页面从后台被唤醒
        SystemUtil.getVersionName(context);
    }

    @Override
    protected void onDestroy() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
        LoadingDialog.hide();
        super.onDestroy();
        unbinder.unbind();
        ActivityManager.getActivityManager().remove(this);
        //finish();
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
}