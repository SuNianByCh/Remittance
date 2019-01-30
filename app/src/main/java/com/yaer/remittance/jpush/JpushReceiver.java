package com.yaer.remittance.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.orhanobut.logger.Logger;
import com.yaer.remittance.base.SpashActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class JpushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private static String type;
    private static String title;
    private static String content;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        try {
            String pushJson = printBundle(bundle);
            Log.e("pushjson", "" + pushJson);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle
                    .getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
			/*intent = new Intent(context, SpashActivity.class);
			context.startActivity(intent);*/
            // send the Registration Id to your server...
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            Log.d(TAG,
                    "[MyReceiver] 接收到推送下来的自定义消息: "
                            + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle
                    .getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            Log.e(TAG, "用户点击打开了通知" + type);
            Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
            Intent i = new Intent(context, SpashActivity.class);
            i.putExtras(bundle);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            context.startActivity(i);
            //EventBus.getDefault().post(new JpushBean(type,content,title));
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
                .getAction())) {
            Log.d(TAG,
                    "[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
                            + bundle.getString(JPushInterface.EXTRA_EXTRA));
            // 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
            // 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
                .getAction())) {
            boolean connected = intent.getBooleanExtra(
                    JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction()
                    + " connected state change to " + connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey1:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey2:" + key + ", value:" + bundle.getBoolean(key));
            } else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                    Log.i(TAG, "This message has no Extra data");
                    continue;
                }

                try {
                    JSONObject json = new JSONObject(
                            bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        if ("type".equals(myKey)) {
                            type = json.optString(myKey);
                        }
                    }
                    Log.e(TAG, "type==" + type);
                } catch (JSONException e) {
                    Log.e(TAG, "得到额外JSON消息错误");
                }

            } else {
                sb.append("\nkey3:" + key + ", value:" + bundle.getString(key));
                if (key.equals("cn.jpush.android.ALERT")) {//内容
                    content = bundle.getString(key);
                }
                if (key.equals("cn.jpush.android.NOTIFICATION_CONTENT_TITLE")) {//标题
                    title = bundle.getString(key);
                }
            }
        }
        return sb.toString();
    }

    // send msg to MainActivity
    private void processCustomMessage(Context context, Bundle bundle) {

    }

    public class PushEntity {
        private int type;
        private int dataid;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDataid() {
            return dataid;
        }

        public void setDataid(int dataid) {
            this.dataid = dataid;
        }

    }

}
