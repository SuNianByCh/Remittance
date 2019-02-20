package com.yaer.remittance.utils;

import android.app.Activity;

import com.yaer.remittance.base.BaseActivity;

import java.util.Stack;

/**
 * Created by Administrator on 2018/1/15.
 * Activity管理类
 */

public class ActivityManager {
    private static ActivityManager mAManager;
    private Stack<Activity> activities;

    public static ActivityManager getActivityManager() {
        if (mAManager == null) {
            synchronized (ActivityManager.class) {
                if (mAManager == null) {
                    mAManager = new ActivityManager();
                }
            }
        }
        return mAManager;
    }

    /**
     * activity集合
     *
     * @return
     */
    private Stack<Activity> activityStack() {
        if (activities == null) {
            activities = new Stack<Activity>();
        }
        return activities;
    }

    /**
     * 获取下一个activity
     *
     * @return
     */
    private Activity activityStackLastElement() {
        try {
            return activityStack().lastElement();
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 添加
     *
     * @param activity
     */
    public void add(Activity activity) {
        activityStack().add(activity);
    }

    /**
     * 获取当前activity
     *
     * @return
     */
    public Activity get(){
        Activity activity = activityStack().lastElement();
        return activity;
    }
    /**
     * 获取指定activity
     *
     * @return
     */
    public Activity get(Class cls) {
        for (Activity activity : activityStack()) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 移除当前Activity
     */
    public void remove() {
        Activity activity = activityStackLastElement();
        remove(activity);
    }

    /**
     * 移除Activity
     */
    public void remove(Activity activity) {
        if (activity != null) {
            activityStack().remove(activity);
        }
    }

    /**
     * 关闭当前界面
     */
    public void finish() {
        Activity activity = activityStackLastElement();
        finish(activity);
    }

    /**
     * 关闭指定界面
     *
     * @param activity 要关闭的Activity
     */
    public void finish(Activity activity) {
        if (activity != null) {
            activityStack().remove(activity);
            activity.finish();
        }
    }

    /**
     * 关闭指定界面
     *
     * @param cls 要关闭的cls
     */
    public void finish(Class cls) {
        for (Activity activity : activityStack()) {
            if (activity.getClass().equals(cls)) {
                finish(activity);
                break;
            }
        }
    }
    
    /**
     * 逐层关闭到指定界面
     *
     * @param cls
     */
    public void finishOtherGoClass(Class cls) {
        while (true) {
            Activity activity = activityStackLastElement();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            finish(activity);
        }
    }

    /**
     * 关闭所有页面
     */
    public void exitApp() {
        while (true) {
            Activity activity = activityStackLastElement();
            if (activity == null) {
                break;
            }
            finish(activity);
        }
    }
    public void exit(){
        ((BaseActivity)get()).exit();
    }
}
