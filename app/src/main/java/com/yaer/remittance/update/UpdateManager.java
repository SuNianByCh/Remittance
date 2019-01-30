package com.yaer.remittance.update;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.base.GuideActivity;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;

import java.io.File;

/**
 * Created by gcy on 2017/3/31.
 */
public class UpdateManager {
    /**
     * 目标文件存储的文件名
     */
    private static String destFileName = "paipinhui.apk";
    public static ProgressDialog pd;

    /**
     * 不强制显示更新对话框
     *
     * @param context
     * @param info    更新内容
     * @param url     版本下载路径
     * @param version 版本号
     */
    public static void showNoticeDialog(final Context context, String info, final String url, final String version) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("更新提示");
        builder.setIcon(R.mipmap.logo);
        builder.setMessage(info);
        // 更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getDowlondInfo(context, url, dialog);
            }
        });
        // 稍后更新
        builder.setNegativeButton("以后更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferencesUtils.saveData(context, "version", String.valueOf(SystemUtil.packageCode(context)));
                dialog.dismiss();
                onNext(context);
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }

    /**
     * 强制更新显示更新对话框
     *
     * @param context
     * @param info    更新内容
     * @param url     版本下载路径
     */
    public static void showNotiForceDialog(final Context context, final String info, final String url) {
        // 构造对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("更新提示");
        builder.setIcon(R.drawable.logo);
        builder.setMessage(info);
        // 更新
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getDowlondInfo(context, url, dialog);
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }

    private static void getDowlondInfo(final Context context, String url, final DialogInterface dialog) {
        pd = new ProgressDialog(context);
        pd.setTitle("更新进度");
        pd.setIcon(R.drawable.logo);
        pd.setMax(10000);
        pd.setIndeterminate(false);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.incrementProgressBy(0);
        pd.setCancelable(false);
        final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/download";
        final File file = new File(filePath + "/" + destFileName);
        if (file.exists())
            file.delete();

        OkGo.<File>get(url)
                .tag(context)
                .execute(new FileCallback(filePath, destFileName) {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        pd.show();
                        ToastUtils.showShort(context, "开始下载");
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        dialog.dismiss();
                        pd.dismiss();
                        ToastUtils.showShort(context, "下载成功");
                        installApk(context, response.body().getAbsoluteFile());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        super.onError(response);
                        if (response.getException().toString().equals("java.net.SocketException: Software caused connection abort")) {
                            pd.dismiss();
                            ToastUtils.showShort(context, "网络错误，请重新下载");
                        } else {
                            pd.dismiss();
                            ToastUtils.showShort(context, "下载失败");
                        }
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        super.downloadProgress(progress);
                        updateNotification(progress);
                    }
                });
    }

    /**
     * 更新通知
     */
    public static void updateNotification(Progress progress) {
        pd.setMax(10000);
        pd.setProgress((int) (progress.fraction * 10000));
    }

    /**
     * 安装软件
     *
     * @param file
     */
    private static void installApk(Context context, File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(context, "com.yaer.remittance.MyFileProvider", file);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
       context.startActivity(intent);
      /*   Uri uri = Uri.fromFile(file);
        Intent install = new Intent(Intent.ACTION_VIEW);
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        install.setDataAndType(uri, "application/vnd.android.package-archive");
        // 执行意图进行安装
        context.startActivity(install);*/
    }

    /**
     * 下一步操作
     */
    private static void onNext(final Context context) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ((int) SharedPreferencesUtils.getData(context, "yidaoye", 0) == 0) {
                    Intent intent = new Intent(context, GuideActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                   /* if ((int) SharedPreferencesUtils.getData(context, "usertype", 0) == 0) {
                        Intent intent = new Intent(context, UserCharacterActivity.class);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }*/
                }
            }
        }, 2000);
    }

    /**
     * 如果跳转协议没有配置则弹出提示
     *
     * @param context
     * @param text
     */
    public static void showUpdateDialog(Context context, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("温馨提示");
        builder.setIcon(R.drawable.logo);
        builder.setMessage(text);
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }
}
