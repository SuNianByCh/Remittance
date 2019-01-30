package com.yaer.remittance.ui.user_modular.personal;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 设置个人简介
 */

public class PersonalProfileActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_personal_profile)
    CustomTitlebar ct_personal_profile;
    /*个人简介编辑框*/
    @BindView(R.id.et_personal_profile)
    MyEditText et_personal_profile;
    @BindView(R.id.user_tv_count)
    TextView user_tv_count;
    private int num = 50;
    /*获取uid*/
    private int uid;
    /*获取编辑内容*/
    private String PersonalProfile;
    /*获取utoken*/
    private String utoken;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_personal_profile).init();
    }

    @Override
    public void initView() {
        PersonalProfile = getIntent().getStringExtra("Usignature");
        et_personal_profile.setText(PersonalProfile);
        uid=AppUtile.getUid(this);
        utoken =AppUtile.getTicket(this);
        ct_personal_profile.setAction(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_personal_profile;
    }

    @Override
    public void initData() {
        et_personal_profile.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                user_tv_count.setText(s.length() + "/50");
                selectionStart = et_personal_profile.getSelectionStart();
                selectionEnd = et_personal_profile.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    et_personal_profile.setText(s);
                    et_personal_profile.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                isInfo();
                break;
        }
    }

    /*昵称非空判断*/
    private void isInfo() {
        if (AppUtile.isEditText(et_personal_profile)) {
            updateUserInfoPersonal();
        } else {
            ToastUtils.showShort(context, "个人简介不能为空！");
        }

    }

    /**
     * 修改个人简介*
     */
    public void updateUserInfoPersonal() {
        LoadingDialog.showDialogForLoading(PersonalProfileActivity.this, "加载中....");
        PersonalProfile = et_personal_profile.getText().toString().trim();
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUSERINF)
                .tag(this)
                .params("uid", uid)
                .params("usignature", PersonalProfile)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "个人简介: " + response.body().code);
                        Log.e("text", "个人简介: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            LoadingDialog.hide();
                            ToastUtils.showShort(PersonalProfileActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(PersonalProfileActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
