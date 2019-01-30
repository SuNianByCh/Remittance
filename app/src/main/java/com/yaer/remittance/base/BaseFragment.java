package com.yaer.remittance.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.yaer.remittance.utils.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    /**
     * 贴附的activity
     */
    protected FragmentActivity mActivity;
    /**
     * 根view
     */
    protected View mRootView;
    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResId = setLayoutResouceId();
        if (layoutResId > 0) {
            mRootView = inflater.inflate(setLayoutResouceId(), container, false);
            //解决点击穿透问题
            mRootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });
        }
        unbinder = ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(getArguments());
        initView();
        mIsPrepare = true;
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
        setListener();
    }

    @Override
    public void onDestroy() {
        OkGo.cancelTag(OkGo.getInstance().getOkHttpClient(),this);
        LoadingDialog.hide();
        super.onDestroy();
        unbinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    /**
     * 初始化数据
     *
     * @param arguments 接收到的从其他地方传递过来的参数
     */
    protected abstract void initData(Bundle arguments);

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置监听事件
     *
     * @author 漆可
     * @date 2016-5-26 下午3:59:36
     */
    protected void setListener() {

    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T getViewID(int id) {
        if (mRootView == null) {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad() {
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 设置根布局资源id
     *
     * @return
     */
    protected abstract int setLayoutResouceId();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     *
     * @author 漆可
     * @date 2016-5-26 下午4:09:39
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    public void goToActivity(Class<?> clazz) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clazz);
        mActivity.startActivity(intent);
    }

    public void goToActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clazz);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    /**
     * 启动一个activity
     *
     * @param cls 目标类
     */
    public void goToActivity(Class<?> cls, String key, Object data) {
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
        intent.setClass(mActivity, cls);
        mActivity.startActivity(intent);
    }

    @Override
    public void onClick(View view) {

    }
}