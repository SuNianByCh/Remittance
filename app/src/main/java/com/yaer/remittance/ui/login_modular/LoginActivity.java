package com.yaer.remittance.ui.login_modular;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.base.RongYongSingleton;
import com.yaer.remittance.bean.ThirdPartyBean;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.setup.SetPhoneActivity;
import com.yaer.remittance.ui.user_modular.setup.UserForgetPwdActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.UmengShare;

import java.util.Map;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_fopwd)
    TextView tv_fopwd;
    @BindView(R.id.Login_Verflcation)
    LinearLayout Login_Verflcation;
    @BindView(R.id.iv_login_close)
    ImageView iv_login_close;
    @BindView(R.id.ll_qq)
    LinearLayout ll_qq;
    @BindView(R.id.ll_weixin)
    LinearLayout ll_weixin;
    @BindView(R.id.ll_weibo)
    LinearLayout ll_weibo;
    private SHARE_MEDIA[] list = {SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.SINA};
    @BindView(R.id.tv_login_title)
    TextView tv_login_title;
    @BindView(R.id.quick_login)
    TextView quick_login;
    //手机号
    @BindView(R.id.user_tel)
    EditText user_tel;
    //密码
    @BindView(R.id.et_password)
    EditText et_password;
    /*立即注册*/
    @BindView(R.id.Tv_Register)
    TextView Tv_Register;
    UMShareAPI api;
    /*获取时间戳*/
    private long timeStamp;
    /*手机号*/
    private String phone;
    /*密码*/
    private String password;
    private String type = "";//传过来的状态描述
    private int isPhone;
    /*微信第三方登录*/
    public String uname = "";//微信用户名
    public String uicon = ""; //微信头像
    public String usex = "";//微信性别
    public String uthirdpartyid = "";//用户唯一标识
    public String uthirdpartytype = "";
    public int sex;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    public void initData() {
        type = getIntent().getStringExtra("type");
        Tv_Register.setOnClickListener(this);
        tv_fopwd.setOnClickListener(this);
        Login_Verflcation.setOnClickListener(this);
        iv_login_close.setOnClickListener(this);
        ll_weixin.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_weibo.setOnClickListener(this);
        quick_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_fopwd:
                goToActivity(UserForgetPwdActivity.class);
                break;
            case R.id.Login_Verflcation:
                if (type.equals("4")) {//我的状态
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "4");
                    startActivity(intent);
                } else if (type.equals("5")) {//商品详情状态
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "5");
                    startActivity(intent);
                } else if (type.equals("1")) {//首页状态
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                } else if (type.equals("3")) {//消息状态
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "3");
                    startActivity(intent);
                }
                break;
            case R.id.iv_login_close:
                if (type.equals("4")) {
                    goToActivity(MainActivity.class, "type", 1);
                } else if (type.equals("5")) {
                    finish();
                } else if (type.equals("1")) {
                    finish();
                } else if (type.equals("3")) {
                    goToActivity(MainActivity.class, "type", 1);
                }
                break;
            case R.id.Tv_Register:
                if (type.equals("4")) {
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "4");
                    startActivity(intent);
                } else if (type.equals("5")) {
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "5");
                    startActivity(intent);
                } else if (type.equals("1")) {
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "1");
                    startActivity(intent);
                } else if (type.equals("3")) {
                    Intent intent = new Intent(this, Login_Verification_Activity.class);
                    intent.putExtra("type", "3");
                    startActivity(intent);
                }
                break;
            case R.id.ll_qq:
                UmengShare.UmengLogin(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
                break;
            case R.id.ll_weixin:
                boolean isauth = UMShareAPI.get(this).isAuthorize(this, SHARE_MEDIA.WEIXIN);
                if (isauth) {//删除授权
                    UMShareAPI.get(this).deleteOauth(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                } else {
                    UMShareAPI.get(this).doOauthVerify(this, SHARE_MEDIA.WEIXIN, umAuthListener);
                }
                break;
            case R.id.ll_weibo:
                UmengShare.UmengLogin(LoginActivity.this, SHARE_MEDIA.SINA, umAuthListener);
                break;
            case R.id.quick_login:
                isInfo();
                break;
        }
    }

    @Override
    public void initView() {
        api = UMShareAPI.get(this);
        timeStamp = System.currentTimeMillis();
    }

    /*快速注册和登陆*/
    private void isInfo() {
        phone = user_tel.getText().toString().trim();
        password = et_password.getText().toString().trim();
        if (!AppUtile.isEditText(user_tel)) {
            ToastUtils.showShort(context, "手机号不能为空");
        } else if (!AppUtile.isPhone(phone)) {
            ToastUtils.showShort(context, "手机号格式错误");
        } else if (!AppUtile.isEditText(et_password)) {
            ToastUtils.showShort(context, "密码不能为空");
        } else {
            goLogin(phone, password);
        }
    }

    /*账号密码登录*/
    private void goLogin(String phone, String password) {
        LoadingDialog.showDialogForLoading(LoginActivity.this, "登录中....");
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.LOGINBYPWD)
                .tag(this)
                .params("uphone", phone)
                .params("uloginpassword", password)
                .params("timestamp", timeStamp)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "登录: " + response.body().code);
                        Log.e("text", "登录: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(LoginActivity.this, "uToken", response.body().result);
                            if (type.equals("4")) {
                                goToActivity(MainActivity.class, "type", 4);
                                SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                            } else if (type.equals("5")) {
                                finish();
                            } else if (type.equals("1")) {
                                finish();
                            } else if (type.equals("3")) {
                                goToActivity(MainActivity.class, "type", 3);
                                SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                            }
                            GetUserInfo();
                            LoadingDialog.hide();
                        } else {
                            LoadingDialog.hide();
                            ToastUtils.showShort(context, response.body().msg);
                        }
                    }
                });
    }

    /*第三方登录*/
    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            ToastUtils.showToast("授权开始");
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //友盟API获得平台信息
            api.getPlatformInfo(LoginActivity.this, platform,
                    new UMAuthListener() {
                        @Override
                        public void onError(SHARE_MEDIA platform, int arg1, Throwable arg2) {
                            ToastUtils.showToast("授权回调失败");
                        }

                        @Override
                        public void onStart(SHARE_MEDIA share_media) {
                            ToastUtils.showToast("授权回调成功");
                        }

                        @Override
                        public void onComplete(SHARE_MEDIA platform, int arg1, Map<String, String> map) {
                            Log.e("text", "第三方回调: " + platform);
                            Log.e("text", "第三方回调: " + arg1);
                            Log.e("text", "第三方回调: " + map);
                            if (platform.equals(SHARE_MEDIA.QQ) && platform == SHARE_MEDIA.QQ) {
                                uthirdpartytype = "1";
                            } else if (platform.equals(SHARE_MEDIA.WEIXIN) && platform == SHARE_MEDIA.WEIXIN) {
                                uthirdpartytype = "2";
                            } else if (platform.equals(SHARE_MEDIA.SINA) && platform == SHARE_MEDIA.SINA) {
                                uthirdpartytype = "3";
                            }
                            /*微信*/
                            uname = map.get("screen_name");//微信用户名
                            uicon = map.get("iconurl");//微信头像
                            usex = map.get("gender");//微信性别
                            uthirdpartyid = map.get("uid");//用户唯一标识
                            if (usex.equals("1")) {
                                sex = 1;
                            } else {
                                sex = 2;
                            }
                            LoginAddress(uname, uicon, sex, uthirdpartyid, uthirdpartytype);
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA platform, int arg1) {
                            ToastUtils.showToast("授权回调取消");
                        }
                    });
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            ToastUtils.showToast("授权错误");
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ToastUtils.showToast("授权取消");
        }
    };

    /**
     * 友盟QQ登录需要的回调   在有些低端手机上登录之后不走回调，需要这个方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 第三方登录*
     *
     * @param uname
     * @param uicon
     * @param sex
     * @param uthirdpartyid
     * @param uthirdpartytype
     */
    public void LoginAddress(String uname, String uicon, int sex, final String uthirdpartyid, final String uthirdpartytype) {
        OkGo.<BaseMode<ThirdPartyBean>>post(AppApi.BASE_URL + AppApi.LOGINTHIRPARTY)
                .tag(this)
                .params("uname", uname)
                .params("uicon", uicon)
                .params("usex", sex)
                .params("uthirdpartyid", uthirdpartyid)
                .params("uthirdpartytype", uthirdpartytype)
                .params("timestamp", timeStamp)
                .execute(new JsonCallback<BaseMode<ThirdPartyBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<ThirdPartyBean>, ? extends Request> request) {
                        super.onStart(request);
                        LoadingDialog.showDialogForLoading(LoginActivity.this, "登录中....");
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<ThirdPartyBean>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        Bundle bundle = new Bundle();
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            isPhone = response.body().result.getIsPhone();
                            if (isPhone == 1) {
                                if (type.equals("4")) {
                                    //登录成功返回我的界面
                                    SharedPreferencesUtils.saveData(LoginActivity.this, "uToken", response.body().result.getUtoken());
                                    SharedPreferencesUtils.saveData(context, "uid", response.body().result.getUid());
                                    goToActivity(MainActivity.class, "type", 4);
                                    SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                                } else if (type.equals("5")) {
                                    finish();
                                } else if (type.equals("1")) {
                                    finish();
                                } else if (type.equals("3")) {
                                    //登录成功返回我的界面
                                    SharedPreferencesUtils.saveData(LoginActivity.this, "uToken", response.body().result.getUtoken());
                                    SharedPreferencesUtils.saveData(context, "uid", response.body().result.getUid());
                                    goToActivity(MainActivity.class, "type", 3);
                                    SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                                }
                                GetUserInfo();
                                LoadingDialog.hide();
                            } else {
                                if (type.equals("4")) {
                                    // SharedPreferencesUtils.saveData(LoginActivity.this, "uToken", response.body().result.getUtoken());
                                    SharedPreferencesUtils.saveData(context, "dengluuid", response.body().result.getUid());
                                    bundle.putString("type", "4");
                                    bundle.putString("uthirdpartyid", uthirdpartyid);
                                    bundle.putString("uthirdpartytype", uthirdpartytype);
                                    goToActivity(SetPhoneActivity.class, bundle);
                                } else if (type.equals("5")) {
                                    finish();
                                } else if (type.equals("1")) {
                                    finish();
                                } else if (type.equals("3")) {
                                    //SharedPreferencesUtils.saveData(LoginActivity.this, "uToken", response.body().result.getUtoken());
                                    SharedPreferencesUtils.saveData(context, "dengluuid", response.body().result.getUid());
                                    bundle.putString("type", "3");
                                    bundle.putString("uthirdpartyid", uthirdpartyid);
                                    bundle.putString("uthirdpartytype", uthirdpartytype);
                                    goToActivity(SetPhoneActivity.class, bundle);
                                }
                                GetUserInfo();
                                LoadingDialog.hide();
                            }
                        } else {
                            LoadingDialog.hide();
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<ThirdPartyBean>> response) {
                        super.onError(response);
                        LoadingDialog.hide();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        LoadingDialog.hide();
                    }
                });
    }

    private String utoken;

    /**
     * 获取用户信息
     */
    public void GetUserInfo() {
        utoken = AppUtile.getTicket(this);
        if (utoken.equals("")) {
            return;
        } else {
            OkGo.<BaseMode<UserBean>>post(AppApi.BASE_URL + AppApi.GETUSERINFO)
                    .tag(this)
                    .params("utoken", utoken)
                    .params("type", "1")
                    .execute(new JsonCallback<BaseMode<UserBean>>(this) {
                        @Override
                        public void onSuccess(Response<BaseMode<UserBean>> response) {
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                SharedPreferencesUtils.saveData(LoginActivity.this, "uid", response.body().result.getUid());
                                SharedPreferencesUtils.saveData(LoginActivity.this, "personalauthentication", response.body().result.getPersonalauthentication());//个人认证
                                SharedPreferencesUtils.saveData(LoginActivity.this, "enterprisecertification", response.body().result.getEnterprisecertification());//企业认证
                                SharedPreferencesUtils.saveData(LoginActivity.this, "uname", response.body().result.getUname());//用户名称
                                SharedPreferencesUtils.saveData(LoginActivity.this, "uphone", response.body().result.getUphone());//用户手机号
                                SharedPreferencesUtils.saveData(LoginActivity.this, "ulevel", response.body().result.getUlevel());//等级
                                SharedPreferencesUtils.saveData(LoginActivity.this, "uicon", response.body().result.getUicon());//用户头像
                                SharedPreferencesUtils.saveData(LoginActivity.this, "ishaveLoginpassword", response.body().result.getIshaveLoginpassword());//登录密码
                                SharedPreferencesUtils.saveData(LoginActivity.this, "ishaveUpaypassword", response.body().result.getIshaveUpaypassword());//支付密码

                                getRongcloudtoken();
                            } else {
                                // ToastUtils.showToast(response.body().msg.toString());
                            }
                        }
                    });
        }
    }

    public void getRongcloudtoken() {
        int uid = AppUtile.getUid(this);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETRONGCLOUDTOKEN)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取融云token: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            SharedPreferencesUtils.saveData(LoginActivity.this, "rongcloudtoken", response.body().result);//融云token
                            //connectRongServer(response.body().result.toString());
                            RongYongSingleton.getInstance();
                        } else {
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (type.equals("4")) {
                goToActivity(MainActivity.class, "type", 1);
            } else if (type.equals("5")) {
                finish();
            } else if (type.equals("1")) {
                finish();
            } else if (type.equals("3")) {
                goToActivity(MainActivity.class, "type", 1);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
