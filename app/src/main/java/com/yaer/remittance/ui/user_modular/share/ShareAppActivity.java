package com.yaer.remittance.ui.user_modular.share;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 邀请好友
 */
public class ShareAppActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.tv_was_invited_here)
    TextView tv_was_invited_here;
    @BindView(R.id.ct_sharre_app)
    CustomTitlebar ct_sharre_app;
    private String uinvitationcode;
    @BindView(R.id.tv_uinvitationcode)
    TextView tv_uinvitationcode;
    @BindView(R.id.tv_to_invite)
    TextView tv_to_invite;
  /*  @BindView(R.id.tv_invitation_number)
    TextView tv_invitation_number;*/
    UMShareAPI api;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_sharre_app).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_share_app;
    }

    @Override
    public void initView() {
        ct_sharre_app.setAction(this);
        api = UMShareAPI.get(this);
        uinvitationcode = (String) SharedPreferencesUtils.getData(this, "uinvitationcode", "");
        tv_uinvitationcode.setText(uinvitationcode);
        getinvitatlonlist();
    }

    /**
     * 获取邀请码列表
     */
    public void getinvitatlonlist() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETINVITATIONLIST)
                .tag(this)
                .params("icode", uinvitationcode)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取邀请码列表: " + response.body().code);
                        Log.e("text", "获取邀请码列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            //ToastUtils.showShort(ShareAppActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(ShareAppActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_was_invited_here, R.id.tv_to_invite})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_was_invited_here:
                goToActivity(ShareAppBeInvitedActivity.class);
                break;
            case R.id.tv_to_invite:
                UMImage image = new UMImage(this, R.drawable.logo);//分享图标
                final UMWeb web = new UMWeb("http://www.paiphui.com/h5/share/share.html?code=" + uinvitationcode);
                web.setTitle("朋友，诚邀你跟我一起，玩转拍卖，展翅飞翔");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("拍品汇app,让拍卖成为潮流");//描述
                //切记切记 这里分享的链接必须是http开头
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                if (share_media == SHARE_MEDIA.QQ) {
                                    new ShareAction(ShareAppActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN) {
                                    new ShareAction(ShareAppActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.QZONE) {
                                    new ShareAction(ShareAppActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                                    new ShareAction(ShareAppActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                }
                            }
                        }).open();
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            //Toast.makeText(ShareAppActivity.this, " 分享回调中", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
           // Toast.makeText(ShareAppActivity.this, " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            //Toast.makeText(ShareAppActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
           // Toast.makeText(ShareAppActivity.this, " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result", "onActivityResult");
    }
    /* */

    /**
     * 友盟分享的回调
     *
     * @param
     *//*

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<MainActivity> mActivity;

        private CustomShareListener(MainActivity activity) {
            mActivity = new WeakReference(activity);
        }
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast("分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享取消了");
        }
    }*/
    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
