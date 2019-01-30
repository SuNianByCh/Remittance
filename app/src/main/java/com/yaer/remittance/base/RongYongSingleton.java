package com.yaer.remittance.base;

import android.net.Uri;
import android.util.Log;

import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.StringUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

public class RongYongSingleton {
    private String rongyuntoken;
    private int uid;
    private String rongname;
    private String ronguiconimg;
    private static RongYongSingleton instance = null;
    private String ronguserid;

    private RongYongSingleton() {

    }
    private void connect(){
        //这里面可以进行初始化操作
        //这个是拿到融云的token
        rongyuntoken = (String) SharedPreferencesUtils.getData(BaseApplication.getInstance().getApplicationContext(), "rongcloudtoken", "");
        //融云的用户名称
        rongname = (String) SharedPreferencesUtils.getData(BaseApplication.getInstance().getApplicationContext(), "uname", "");
        //融云的用户头像
        ronguiconimg = (String) SharedPreferencesUtils.getData(BaseApplication.getInstance().getApplicationContext(), "uicon", "");
        //用户id
        // uid = AppUtile.getUid(BaseApplication.getInstance().getApplicationContext());
        if(StringUtils.isEmpty(rongyuntoken)){
            return;
        }
        connectRongServer(rongyuntoken);
        //这个方法是初始化用户信息

        //  RongIM.getInstance().setCurrentUserInfo();
        RongIM.getInstance().setMessageAttachedUserInfo(true);
          /*   RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String s) {
                FriendBean friendBean = new FriendBean();
                friendBean.setUserId(ronguserid);
                friendBean.setUserName(rongname);
                friendBean.setPortraitUri(ronguiconimg);
                return new UserInfo(friendBean.getUserId(), friendBean.getUserName(), Uri.parse(friendBean.getPortraitUri()));
            }
        }, true);*/
    }
    //这样可以，同步放在整个方法，确定就是同一时刻只能相应一个方法，其他方法要等，不过可以，满足你的要求了
    //剩下的就是把关于融云连接相关的放到这个里面就可以了。恩
    public static synchronized RongYongSingleton getInstance() {
        if (instance == null) {
            instance = new RongYongSingleton();
        }
        //这个是什么意思了、、
        instance.connect();
        //可以测试了
        return instance;
    }

    /**
     * 连接融云服务器
     *
     * @param token
     */
    private void connectRongServer(String token) {
        //这能返回200么 可以
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onSuccess(String userId) {
                Log.d("LoginActivity", "--onSuccess" + userId);
                //这是同步 方法 你在哪些的 同步监听，没有写,是不是要咱们自己在写一个实体类，把用户的信息set进去,那个私聊绘画列表页，我可以单独写一个，
                // 那个聊天的你都在这边初始化了，我直接进去，就不用更新我的用户信息了
                //发送消息有两种一种是有融云提供  一种是从自己服务器获取 我现在写这个就是携带式设置当前用户的 id 头像 昵称
                ronguserid = userId;
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userId,rongname,Uri.parse(ronguiconimg)));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("test", "connect failure errorCode is :" + errorCode.getValue());
            }

            @Override
            public void onTokenIncorrect() {
                Log.e("test", "token is error , please check token and appkey ");
            }
        });
    }
}
