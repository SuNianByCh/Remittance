package com.yaer.remittance.ui.user_modular.user_buyer.integral;

import android.graphics.PixelFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_seller.shopinfo.UserShopInfoActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.X5WebView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 积分
 * Created by ywl on 2016/6/27.
 */
public class UserIntegralActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_integral)
    CustomTitlebar ct_integral;
    @BindView(R.id.civ_integral)
    CircleImageView civ_integral;
    /*商家*/
    @BindView(R.id.tv_integral_name)
    TextView tv_integral_name;
   /* @BindView(R.id.tv_integral_lable)
    TextView tv_integral_lable;*/
    @BindView(R.id.tv_user_uintegral)
    TextView tv_user_uintegral;
    @BindView(R.id.userintegral)
    WebView userintegral;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    private String uname;

    @Override
    public void initView() {
        ct_integral.setAction(this);
        String uintegral = (String) SharedPreferencesUtils.getData(this, "uintegral", "");
        uname = (String) SharedPreferencesUtils.getData(this, "uname", "");
        shopicon = (String) SharedPreferencesUtils.getData(this, "uicon", "");
        String uphone = (String) SharedPreferencesUtils.getData(this, "uphone", "");
        tv_user_uintegral.setText(uintegral);
        if (uname.equals("")) {
            tv_integral_name.setText(uphone);//买家手机号
        } else {
            tv_integral_name.setText(uname);//买家名称
        }
        if (shopicon.equals("")) {
            Glide.with(UserIntegralActivity.this).load(R.drawable.user_settings).fitCenter().into(civ_integral);//商品图片
        } else {
            Glide.with(UserIntegralActivity.this).load(shopicon).fitCenter().into(civ_integral);
        }
        // getShopInfoBySid();
     /*   userintegral.loadUrl("http://www.paiphui.com/turntable/turntable.html?uid=" + AppUtile.getUid(this));
        userintegral.getX5WebViewExtension().setScrollBarFadingEnabled(false);
        userintegral.getSettings().setDomStorageEnabled(true);
        userintegral.setHorizontalScrollBarEnabled(false);
        IX5WebViewExtension ix5 = userintegral.getX5WebViewExtension();
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(false);
        }*/
        getWindow().setFormat(PixelFormat.TRANSLUCENT);//（这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        loadUrl("http://www.paiphui.com/turntable/turntable.html?uid=" + AppUtile.getUid(this));
    }

    private void loadUrl(String url) {
        WebSettings webSettings = userintegral.getSettings();
        webSettings.setJavaScriptEnabled(true);
        userintegral.loadUrl(url);
        userintegral.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                Log.i("打印日志", "网页加载失败");
            }
        });
        //进度条
        userintegral.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    Log.i("打印日志", "加载完成");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (userintegral != null) userintegral.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (userintegral != null && userintegral.canGoBack()) {
                userintegral.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_integral).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_integral;
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        if (userintegral != null)
            userintegral.destroy();
    }*/

    /* @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
         if (keyCode == KeyEvent.KEYCODE_BACK) {
             if (userintegral != null && userintegral.canGoBack()) {
                 userintegral.goBack();
                 return true;
             } else {
                 return super.onKeyDown(keyCode, event);
             }
         }
         return super.onKeyDown(keyCode, event);
     }
 */
    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                goToActivity(UserIntegralRuleActivity.class);
                break;
        }
    }

    private String shopicon;

    //获取店铺信息
    public void getShopInfoBySid() {
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<ShopInfoByidBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            tv_integral_name.setText(response.body().result.getShopname());//卖家名称
                            shopicon = response.body().result.getShopimg();
                            Glide.with(UserIntegralActivity.this).load(shopicon).fitCenter().into(civ_integral);
                            Gson gson = new Gson();
                            //tv_integral_lable.setText(gson.toJson(response.body().result.getShoplabel()));
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }
                });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
