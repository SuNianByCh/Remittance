package com.yaer.remittance.ui.user_modular.shopinfo;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.BaseTakePhotoActivity;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.eventmessage.TypeMessage;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.EventMessage;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.WsbPopUtile;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 设置店铺背景
 */

public class ShopStoreBackgroundActivity extends BaseTakePhotoActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_head_portrait)
    CustomTitlebar ct_head_portrait;
    @BindView(R.id.iv_userhead)
    ImageView iv_userhead;
    @BindView(R.id.rl_user_headportra)
    RelativeLayout rl_user_headportra;
    private String userpath, oosuserpath;
    private int uid;
    private String utoken;
    private String sbgimg;
    private File image_file;
    private ArrayList<TImage> imagesTalk;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_head_portrait).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getShopid(this);
        ct_head_portrait.setTilte("店铺背景");
        sbgimg = getIntent().getStringExtra("sbgimg");
        Glide.with(this).load(sbgimg).fitCenter().into(iv_userhead);
        userpath = "auction/user/" + getImageObjectKey();
        ct_head_portrait.setAction(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_head_portrait;
    }

    @Override
    public void initData() {
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                WsbPopUtile.getInstence(this).showTalkPhoto(rl_user_headportra, getTakePhoto(), TypeMessage.oppen);
                break;
        }
    }

    @Override
    public void takeImageSuccess(TResult result) {
        imagesTalk = result.getImages();
        String strName = imagesTalk.get(imagesTalk.size() - 1).getCompressPath();
        image_file = new File(imagesTalk.get(imagesTalk.size() - 1).getCompressPath());
        showoos(strName, userpath);
    }

    /*修改店铺信息头像*/
    private void GetupheadPortrait(String oosuserpath) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPINFO)
                .tag(this)
                .params("sid", AppUtile.getShopid(this))
                .params("sbgimg", oosuserpath)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(ShopStoreBackgroundActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            stopDialog();
                            ToastUtils.showShort(ShopStoreBackgroundActivity.this, response.body().msg);
                            finish();
                        } else {
                            stopDialog();
                            ToastUtils.showShort(ShopStoreBackgroundActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                    }
                });
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    private String testBucket;
    private OSS oss;
    private String uploadFilePath;

    /**
     * 阿里云上传文件初始化正面身份证
     *
     * @param 、、        上下文
     * @param 、、阿里云配置信息
     * @param 、、        文件路径
     */
    private void showoos(String filePath, String path) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIUgkh1Pv4ANqb", "VeLY0skMK0qdJ6lkXe5etWPCpaBvIe");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(context.getApplicationContext(), "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
        testBucket = "paipinhui";
        this.uploadFilePath = filePath;
        asyncPutObjectFromLocalFile(path, EventMessage.Type.PERSIONIMAGE);
        Log.e("test", "showoos: " + path);
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
                // LoadingDialog.showDialogForLoading(UserHeadPortraitActivity.this, "上传中....");
            }
        });

        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("test", "onSuccess: " + request.getObjectKey());
                Log.e("test", "onSuccess: " + result);
                oosuserpath = AppApi.ALYOOS + request.getObjectKey();
                if (oosuserpath.equals("")) {
                    ToastUtils.showToast("图片正在上传中....");
                } else {
                    GetupheadPortrait(oosuserpath);
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

    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }
}
