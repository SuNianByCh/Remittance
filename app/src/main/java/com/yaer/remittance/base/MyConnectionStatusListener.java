package com.yaer.remittance.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.ActivityManager;
import com.yaer.remittance.utils.SharedPreferencesUtils;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MyConnectionStatusListener implements RongIMClient.ConnectionStatusListener {
    @Override
    public void onChanged(ConnectionStatus connectionStatus) {
        switch (connectionStatus) {
            case CONNECTED://连接成功。
                Log.e("test", "CONNECTED: "+" 连接成功");
                break;
            case DISCONNECTED://断开连接。
                Log.e("test", "DISCONNECTED: "+" 断开连接");
                break;
            case CONNECTING://连接中。
                Log.e("test", "CONNECTING: "+" 连接中");
                break;
            case NETWORK_UNAVAILABLE://网络不可用。
                Log.e("test", "NETWORK_UNAVAILABLE: "+" 网络不可用");
                break;
            case KICKED_OFFLINE_BY_OTHER_CLIENT://todo 用户账户在其他设备登录，本机会被踢掉线
                Log.e("test", "KICKED_OFFLINE_BY_OTHER_CLIENT: "+" 用户账户在其他设备登录，本机会被踢掉线");
              new Handler(Looper.getMainLooper()).post(new Runnable() {
                  @Override
                  public void run() {
                      ActivityManager.getActivityManager().finishOtherGoClass(MainActivity.class);
                      if (RongIM.getInstance() != null) {
                          RongIM.getInstance().disconnect();
                      }
                      AlertDialog dialog1 = new AlertDialog.Builder(ActivityManager.getActivityManager().get())
                              .setTitle("账号退出")
                              .setMessage("账号在别处登录")
                              .setCancelable(false)
                            /*  .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                                          @Override
                                          public void onClick(DialogInterface dialog, int which) {
                                              ActivityManager.getActivityManager().get().startActivity(new Intent(BaseApplication.getInstance(),MainActivity.class));
                                          }
                                      }
                              )*/
                              .setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "uToken", "");//用户token
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "uid", 0);//用户id
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "personalauthentication", 0);//个人认证
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "uname", "");//用户名称
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "ulevel", "");//等级
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "uicon", "");//用户头像
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "ishaveLoginpassword", "");//登录密码
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "ishaveUpaypassword", "");//支付密码
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "enterprisecertification", 0);//个人认证
                                      SharedPreferencesUtils.saveData(BaseApplication.getInstance(), "ShopInfoid", "");//店铺id标识
                                      RongIM.getInstance().disconnect();
                                      JPushInterface.setAlias(BaseApplication.getInstance(), 0, "");
                                      //删除别名
                                      JPushInterface.deleteAlias(BaseApplication.getInstance(), 0);
                                      Intent intent=new Intent(BaseApplication.getInstance(),LoginActivity.class);
                                      intent.putExtra("type", "4");
                                      ActivityManager.getActivityManager().get().startActivity(intent);
                                  }
                              }).create();
                      dialog1.show();
                  }
              });
               // ActivityManger.INSTANCE.loginActivity();
                break;
        }
    }
}
