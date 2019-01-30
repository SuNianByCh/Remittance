package com.yaer.remittance.update;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.format.Formatter;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.utils.ToastUtils;

import java.io.File;

/**
 * Created by min on 2017/7/31.
 */
public class DowlondService extends Service {
    /**
     * 目标文件存储的文件名
     */
    private String destFileName = "paipinhui.apk";

    private Context mContext;
    private int NOTIFY_ID = 1000;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private String url;//文件下载路径

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mContext = this;
        url = intent.getStringExtra("url");
        loadFile();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 下载文件
     */
    private void loadFile() {
        Log.e("--开始下载文件--", "开始下载文件");
        initNotification();
        OkGo.<File>get(url)
                .tag(mContext)
                .execute(new FileCallback(destFileName) {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        ToastUtils.showShort(mContext,"开始下载");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        ToastUtils.showShort(mContext,"下载成功");
                        installApk(response.body().getAbsoluteFile());
                        cancelNotification();
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        ToastUtils.showShort(mContext,"下载失败");
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        Log.e("progress",progress.fileName);
                        updateNotification(progress);
                    }
                });

    }
    /**
     * 安装软件
     *
     * @param file
     */
    private void installApk(File file) {
        Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        mContext.startActivity(install);
        stopSelf();//关闭service

    }



    /**
     * 初始化Notification通知
     */
    public void initNotification() {
        builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.drawable.logo)
                .setContentText("0%")
                .setContentTitle("拍品汇更新")
                .setProgress(100, 0, false);
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 更新通知
     */
    public void updateNotification(Progress progress) {
           String speed = Formatter.formatFileSize(getApplicationContext(), progress.speed);
            builder.setContentText(speed + "%");
            builder.setProgress(10000, (int) (progress.fraction * 10000), false);
            notificationManager.notify(NOTIFY_ID, builder.build());
    }

    /**
     * 取消通知
     */
    public void cancelNotification() {
        notificationManager.cancel(NOTIFY_ID);
    }
}
