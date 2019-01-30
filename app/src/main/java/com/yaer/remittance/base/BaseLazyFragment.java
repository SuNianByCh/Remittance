package com.yaer.remittance.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.yaer.remittance.utils.LoadingDialog;

import org.litepal.util.LogUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 当使用viewpager加载Fragment，沉浸式的使用，原理懒加载
 * Created by geyifeng on 2017/4/7.
 */
public abstract class BaseLazyFragment extends Fragment {
    protected Activity mActivity;
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;

    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;

    protected ImmersionBar mImmersionBar;
    private Unbinder unbinder;
    private Long time;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        time = System.currentTimeMillis();
        mRootView = inflater.inflate(setLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/

        Log.d("loading fragmet time-->","加载fragment" + this.getClass().getName()+
                " 用的时间: " + (System.currentTimeMillis() - time) + "(ms)");
        unbinder = ButterKnife.bind(this, mRootView);
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            initData();
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
        initView();
        setListener();
        Log.d("loading fragmet time-->","加载fragment" + this.getClass().getName()+
                " 用的时间 2:" + (System.currentTimeMillis() - time) + "(ms)");
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {//你的项目好久上线？？？27号，哦，后面的时间还多哈？到时候还需要你的帮助，你都帮了我
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
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
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }

    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            initData();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * Sets layout id.
     *
     * @return the layout id
     */
    protected abstract int setLayoutId();

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(getActivity());
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * view与数据绑定
     */
    protected void initView() {

    }

    /**
     * 设置监听
     */
    protected void setListener() {

    }

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }

    /**
     * 找到activity的控件
     *
     * @param <T> the type parameter
     * @param id  the id
     * @return the t
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T findActivityViewById(@IdRes int id) {
        return (T) mActivity.findViewById(id);
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

}