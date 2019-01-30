package com.yaer.remittance.base;

/**
 * Created by Administrator on 2018-09-11.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.yaer.remittance.bean.NetEvent;
import com.yaer.remittance.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * @Description:网络状态的Receive
 */
public class NetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            boolean isConnected = NetworkUtils.isNetworkConnected(context);
            if (isConnected) {
            /*    Toast.makeText(context, "已经连接网络", Toast.LENGTH_LONG).show();*/
                EventBus.getDefault().post(new NetEvent(true));
            } else {
                EventBus.getDefault().post(new NetEvent(false));
                Toast.makeText(context, "已经断开网络", Toast.LENGTH_LONG).show();
            }
        }
    }

}
