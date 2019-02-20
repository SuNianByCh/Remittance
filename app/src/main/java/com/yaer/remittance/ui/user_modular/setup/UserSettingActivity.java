package com.yaer.remittance.ui.user_modular.setup;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.feedback.UserFeedBackActivity;
import com.yaer.remittance.utils.GlideCacheUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imkit.RongIM;

/**
 * Created by Administrator on 2018-09-11.
 * 设置首页
 */

public class UserSettingActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_setting)
    CustomTitlebar ct_setting;
    /*  @BindView(R.id.tv_modify_cell_phone)
      TextView tv_modify_cell_phone;*/
    @BindView(R.id.tv_modify_pwd)
    TextView tv_modify_pwd;
    /*  @BindView(R.id.tv_forget_pwd)
      TextView tv_forget_pwd;*/
    @BindView(R.id.tv_receiving_address)
    TextView tv_receiving_address;
    @BindView(R.id.tv_security_payment)
    TextView tv_security_payment;
    @BindView(R.id.tv_clear_caching)
    TextView tv_clear_caching;
    @BindView(R.id.tv_clean)
    TextView tv_clean;
    @BindView(R.id.tv_sign_out)
    TextView tv_sign_out;
    /*设置支付密码
    @BindView(R.id.tv_payment_password)
    TextView tv_payment_password;*/
    private long mExitTime = 0;//点击退出状态初始时间
    //关于我们
    @BindView(R.id.tv_about_us)
    TextView tv_about_us;
    @BindView(R.id.banben)
    TextView banben;
    @BindView(R.id.tv_setting_feedback)
    TextView tv_setting_feedback;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_setting).init();
    }

    @Override
    public void initView() {
        banben.setText("v " + SystemUtil.getVersionName(context));
        ct_setting.setAction(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_setting;
    }

    @Override
    public void initData() {
        tv_clean.setText(GlideCacheUtile.getInstance().getCacheSize(context));
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    //R.id.tv_modify_cell_phone, 修改手机号展示隐藏
    @OnClick({R.id.tv_modify_pwd, R.id.tv_receiving_address, R.id.tv_security_payment, R.id.tv_clear_caching, R.id.tv_sign_out, R.id.tv_about_us, R.id.tv_setting_feedback})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_setting_feedback:
                goToActivity(UserFeedBackActivity.class);
                break;
           /* case R.id.tv_modify_cell_phone:
                //修改手机号
                goToActivity(UserModifyPhoneActivity.class);
                break;*/
            case R.id.tv_modify_pwd:
                //修改密码
                goToActivity(UserModifyPwdActivity.class);
                break;
           /* case R.id.tv_forget_pwd:
                //忘记密码
                goToActivity(UserForgetPwdActivity.class);
                break;*/
            case R.id.tv_receiving_address:
                //收货地址
                goToActivity(UserReceivingAddressActivity.class);
                break;
            case R.id.tv_about_us:
                //关于我们
                goToActivity(UserAboutUsActivity.class);
                break;
            case R.id.tv_security_payment:
                //支付安全
                goToActivity(UserSetPwdYzmActivity.class);
                // goToActivity(UserSecurityPaymentActivity.class);
                break;
            case R.id.tv_clear_caching:
                //清楚缓存
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        m.sendEmptyMessage(1);
                    }
                }).start();
                break;
            case R.id.tv_sign_out:
                //退出程序
                SharedPreferencesUtils.saveData(context, "uToken", "");//用户token
                SharedPreferencesUtils.saveData(context, "uid", 0);//用户id
                SharedPreferencesUtils.saveData(context, "personalauthentication", 0);//个人认证
                SharedPreferencesUtils.saveData(context, "uname", "");//用户名称
                SharedPreferencesUtils.saveData(context, "ulevel", "");//等级
                SharedPreferencesUtils.saveData(context, "uicon", "");//用户头像
                SharedPreferencesUtils.saveData(context, "ishaveLoginpassword", "");//登录密码
                SharedPreferencesUtils.saveData(context, "ishaveUpaypassword", "");//支付密码
                SharedPreferencesUtils.saveData(context, "enterprisecertification", 0);//个人认证
                SharedPreferencesUtils.saveData(context, "ShopInfoid", "");//店铺id标识
                RongIM.getInstance().disconnect();
                BaseApplication.getInstance().cancleTagAndAlias();
                JPushInterface.setAlias(context, 0, "");
                //删除别名
                JPushInterface.deleteAlias(context, 0);
                //极光推送解除绑定别名
              /*  JPushInterface.stopPush(this);//停止推送
                JPushInterface.setAliasAndTags(this, "", tags, mAliasCallback);
                JPushInterface.setAliasAndTags(this, "", new TagAliasCallback() {
                    @Override
                    public void gotResult(int i, String s, Set<String> set) {
                        Log.i("JPush","Logout");
                    }
                });*/
                goToActivity(MainActivity.class, "type", 1);
                break;
        }
    }

    private void exits() {
       // AppManager.getAppManager().AppExit(this);
    }

    Handler m = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                GlideCacheUtile.getInstance().clearImageAllCache(context);
                tv_clean.setText("0.0 M");
                ToastUtils.showShort(context, "缓存清除完成");
            }
        }
    };
}
