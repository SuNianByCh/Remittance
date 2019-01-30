package com.yaer.remittance.ui.user_modular.personal;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 个人设置
 */
public class PersonalInformationActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_persona_linformation)
    CustomTitlebar ct_persona_linformation;
    /*头像*/
    @BindView(R.id.tv_head_portrait)
    TextView tv_head_portrait;
    @BindView(R.id.civ_personal)
    CircleImageView civ_personal;
    /*昵称*/
    @BindView(R.id.person_nicename)
    TextView person_nicename;
    /*昵称*/
    @BindView(R.id.tv_name)
    TextView tv_name;
    /*简介*/
    @BindView(R.id.tv_brief_introduction)
    TextView tv_brief_introduction;
    @BindView(R.id.tv_usignature)
    TextView tv_usignature;
    /*性别*/
    @BindView(R.id.tv_personal_sex)
    TextView tv_personal_sex;
    @BindView(R.id.tv_usex)
    TextView tv_usex;
    /*身份标签*/
   /* @BindView(R.id.tv_identity_interest)
    TextView tv_identity_interest;*/
    /*utoken*/
    private String utoken;
    private String PersonalName, PersonalIcon, PersonalUsignature, PersonalSex, Personphone;
    private Handler mHandler = new Handler();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_persona_linformation).init();
    }

    @Override
    public void onResume() {
        GetUserInfo();
        super.onResume();
    }

    @Override
    public void initView() {
        ct_persona_linformation.setAction(this);
        utoken = AppUtile.getTicket(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_persona_linformation;
    }

    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                finish();
                break;
        }
    }

    @OnClick({R.id.tv_head_portrait, R.id.person_nicename, R.id.tv_brief_introduction, R.id.tv_personal_sex})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            /*头像*/
            case R.id.tv_head_portrait:
                bundle.putString("icon", PersonalIcon);
                bundle.putString("name", PersonalName);
                bundle.putString("phone", Personphone);
                goToActivity(UserHeadPortraitActivity.class, bundle);
                break;
            /*昵称*/
            case R.id.person_nicename:
                if (PersonalIcon.equals("")){
                    ToastUtils.showToast("请设置头像，再来修改昵称");
                }else{
                    bundle.putString("icon", PersonalIcon);
                    bundle.putString("name", PersonalName);
                    bundle.putString("phone", Personphone);
                    goToActivity(NickNameActivity.class, bundle);
                }
                break;
            /*简介*/
            case R.id.tv_brief_introduction:
                bundle.putString("Usignature", PersonalUsignature);
                goToActivity(PersonalProfileActivity.class, bundle);
                break;
            /*性别*/
            case R.id.tv_personal_sex:
                bundle.putString("PersonalSex", PersonalSex);
                goToActivity(UserSelectSexActivity.class, bundle);
                break;
            /*身份标签*/
            /*case R.id.tv_identity_interest:
                goToActivity(UserAddTagActivity.class);
                break;*/
        }
    }

    /**
     * 获取用户信息*
     */
    public void GetUserInfo() {
        OkGo.<BaseMode<UserBean>>post(AppApi.BASE_URL + AppApi.GETUSERINFO)
                .tag(this)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode<UserBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<UserBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            PersonalName = response.body().result.getUname();
                            PersonalIcon = response.body().result.getUicon();
                            PersonalUsignature = response.body().result.getUsignature();
                            Personphone = response.body().result.getUphone();
                            //SharedPreferencesUtils.saveData(PersonalInformationActivity.this,PersonalIcon,"");
                            if (response.body().result.getUname().equals("")) {
                                tv_name.setText(AppUtile.hideCardNo(Personphone));//买家手机号
                            } else {
                                tv_name.setText(PersonalName);
                            }
                            if (response.body().result.getUicon().equals("")) {
                                Glide.with(PersonalInformationActivity.this).load(R.drawable.user_settings).fitCenter().into(civ_personal);//买家头像
                            } else {
                                Glide.with(PersonalInformationActivity.this).load(PersonalIcon).fitCenter().into(civ_personal);
                            }
                            if (response.body().result.getUsignature().equals("")) {
                            } else {
                                tv_usignature.setText(PersonalUsignature);
                            }
                            PersonalSex = response.body().result.getUsex();
                            if (PersonalSex.equals("2")) {
                                tv_usex.setText("女");
                            } else {
                                tv_usex.setText("男");
                            }
                        } else {
                            goToActivity(LoginActivity.class, "type", "4");
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
                        }
                    }
                });
    }

    @Override
    public void initData() {
    }
}
