package com.yaer.remittance.ui.user_modular.personal;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 设置性别
 */
public class UserSelectSexActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_select_sex)
    CustomTitlebar ct_select_sex;
    @BindView(R.id.woman)
    CheckBox woman;
    @BindView(R.id.man)
    CheckBox man;
    private String sex;
    /* *//*男*//*
    @BindView(R.id.male_layout)
    RelativeLayout male_layout;
    *//*女*//*
    @BindView(R.id.female_layout)
    RelativeLayout female_layout;*/
    /*获取uid*/
    private int uid;
    /*获取utoken*/
    private String utoken;
    private String PersonalSex;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_select_sex).init();
    }

    @Override
    public void initView() {
        PersonalSex = getIntent().getStringExtra("PersonalSex");
        uid = AppUtile.getUid(this);
        utoken =AppUtile.getTicket(this);
        ct_select_sex.setAction(this);
        if (PersonalSex.equals("2")) {
            sex = "女";
            woman.setChecked(true);
            man.setChecked(false);
        } else {
            sex = "男";
            woman.setChecked(false);
            man.setChecked(true);
        }
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.man, R.id.woman})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.man:
                sex = "1";
                man.setChecked(true);
                woman.setChecked(false);
                updateUserInfoSex(sex);
                break;
            case R.id.woman:
                sex = "2";
                woman.setChecked(true);
                man.setChecked(false);
                updateUserInfoSex(sex);
                break;
        }
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_userselectsex;
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    /**
     * 修改性别*
     */
    public void updateUserInfoSex(String usex) {
        LoadingDialog.showDialogForLoading(UserSelectSexActivity.this, "加载中....");
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUSERINF)
                .tag(this)
                .params("uid", uid)
                .params("usex", usex)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "性别: " + response.body().code);
                        Log.e("text", "性别: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            LoadingDialog.hide();
                            ToastUtils.showShort(UserSelectSexActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserSelectSexActivity.this, response.body().msg);
                        }
                    }
                });
    }
}

