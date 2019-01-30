package com.yaer.remittance.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by Administrator on 2017/6/12.
 */
public class AppManager {
    public static Activity context = null;

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager(){}
    /**
     * 单一实例
     */
    public static AppManager getAppManager(){
        if(instance==null){
            instance=new AppManager();
        }
        return instance;
    }
    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }
    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity(){
        Activity activity=activityStack.lastElement();
        return activity;
    }
    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivityName(Class<?> cls){
        for (Activity activity : activityStack) {
            if(activity.getClass().equals(cls) ){
                activity.finish();
                activity=null;
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for (int i = 0, size = activityStack.size(); i < size; i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) { }
    }

    /**
     * Path：SDK/sources/android-25/android/app/ActivityManager#RunningAppProcessInfo.java
     *
     * 这个进程正在运行前台UI，也就是说，它是当前在屏幕顶部的东西，用户正在进行交互的而进程
     */
    public static final int IMPORTANCE_FOREGROUND = 100;

    /**
     * 此进程正在运行前台服务，即使用户不是在应用中时也执行音乐播放，这一般表示该进程正在做用户积极关心的事情
     */
    public static final int IMPORTANCE_FOREGROUND_SERVICE = 125;
    /**
     * 这个过程不是用户的直接意识到，但在某种程度上是他们可以察觉的。
     */
    public static final int IMPORTANCE_PERCEPTIBLE = 130;

    /**
     * 此进程正在运行前台UI，但设备处于睡眠状态，因此用户不可见，意思是用户意识不到的进程，因为他们看不到或与它交互，
     * 但它是相当重要，因为用户解锁设备时期望的返回到这个进程
     */
    public static final int IMPORTANCE_TOP_SLEEPING = 150;

    /**
     * 进程在后台，但我们不能恢复它的状态，所以我们想尽量避免杀死它，不然这个而进程就丢了
     */
    public static final int IMPORTANCE_CANT_SAVE_STATE = 170;

    /**
     * 此进程正在运行某些对用户主动可见的内容，但不是直接显示在UI，
     * 这可能运行在当前前台之后的窗口（因此暂停并且其状态被保存，不与用户交互，但在某种程度上对他们可见）;
     * 也可能在系统的控制下运行其他服务，
     */
    public static final int IMPORTANCE_VISIBLE = 200;

    /**
     * 服务进程，此进程包含在后台保持运行的服务，这些后台服务用户察觉不到，是无感知的，所以它们可以由系统相对自由地杀死
     */
    public static final int IMPORTANCE_SERVICE = 300;

    /**
     * 后台进程
     */
    public static final int IMPORTANCE_BACKGROUND = 400;

    /**
     * 空进程，此进程没有任何正在运行的代码
     */
    public static final int IMPORTANCE_EMPTY = 500;

    // 此过程不存在。
    public static final int IMPORTANCE_GONE = 1000;
}


