package com.yaer.remittance.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.RequestMessage;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.utils.EventMessage;
import com.yaer.remittance.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by min on 2017/8/29.
 * 阿里云上传文件配置
 */
public class OssUtile {
    private Context context;
    private OSS oss;
    private String testBucket;
    private String uploadFilePath;
    private OssService.ProgressCallback progressCallback;
    /**
     * 阿里云上传文件初始化
     *
     * @param context        上下文
     * @param uploadFilePath 阿里云配置信息
     * @param uploadFilePath 文件路径
     */
    public OssUtile(Context context, String uploadFilePath) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIUgkh1Pv4ANqb", "VeLY0skMK0qdJ6lkXe5etWPCpaBvIe");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        this.oss = new OSSClient(context.getApplicationContext(), "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
        this.context = context;
        this.testBucket = "paipinhui";
        this.uploadFilePath = uploadFilePath;
    }

    /**
     * 从本地文件上传，使用非阻塞的异步接口
     *
     * @param imgUrL 目录
     */
    public void asyncPutObjectFromLocalFile(String imgUrL, final int USERIMAGE) {
        // 构造上传请求+getImageObjectKey()
        PutObjectRequest put = new PutObjectRequest(testBucket, imgUrL, uploadFilePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                Log.e("test", "onSuccess: " + request);
                double progress = currentSize * 1.0 / totalSize * 100.f;
                if (progressCallback != null) {
                    progressCallback.onProgressCallback(progress);
                }
            }
        });

        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("test", "onSuccess: " + request.getObjectKey());
                Log.e("test", "onSuccess: " + result);
                EventMessage message = new EventMessage();
                Bundle bundle = new Bundle();
                bundle.putString("img", AppApi.ALYOOS + request.getObjectKey());
                message.setBundle(bundle);
                /*正面66*/
                if (USERIMAGE == EventMessage.Type.PERSIONIMAGE) {
                    message.setTag(EventMessage.Type.PERSIONIMAGE);
                    EventBus.getDefault().post(message);
                    /*反面67*/
                } else if (USERIMAGE == EventMessage.Type.PERSIONOTHERIMAGE) {
                    message.setTag(EventMessage.Type.PERSIONOTHERIMAGE);
                    EventBus.getDefault().post(message);
                } else if (USERIMAGE == EventMessage.Type.COMPANYPERSIONJUSTIMAGE) {
                    message.setTag(EventMessage.Type.COMPANYPERSIONJUSTIMAGE);
                    EventBus.getDefault().post(message);
                } else if (USERIMAGE == EventMessage.Type.COMPANYPERSIONBACKIMAGE) {
                    message.setTag(EventMessage.Type.COMPANYPERSIONBACKIMAGE);
                    EventBus.getDefault().post(message);
                } else if (USERIMAGE == EventMessage.Type.COMPANYIMAGE) {
                    message.setTag(EventMessage.Type.COMPANYIMAGE);
                    EventBus.getDefault().post(message);
                }else if(USERIMAGE == EventMessage.Type.DPTOUXIANG){
                    message.setTag(EventMessage.Type.DPTOUXIANG);
                    EventBus.getDefault().post(message);
                }else if(USERIMAGE == EventMessage.Type.DPBEIJING){
                    message.setTag(EventMessage.Type.DPBEIJING);
                    EventBus.getDefault().post(message);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                Log.e("test", "获取数据: " + request);
                Log.e("test", "获取数据: " + clientExcepion);
                Log.e("test", "获取数据: " + serviceException);
                // 请求异常
                if (clientExcepion != null) {
                    ToastUtils.showToast("");
                    ToastUtils.showShort(context, "网络异常,上传图片失败！");
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    ToastUtils.showShort(context, "图片上传失败!");
                }
            }
        });
    }
}
