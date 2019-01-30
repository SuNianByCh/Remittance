package com.yaer.remittance.ui.user_modular.personal;

import android.util.Log;
import android.view.View;

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
import com.yaer.remittance.view.ClearEditText;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 设置昵称
 */

public class NickNameActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_nickname)
    CustomTitlebar ct_nickname;
    @BindView(R.id.et_nickname)
    ClearEditText et_nickname;
    private int uid;
    private String name;
    private String utoken;
    private String PersonalName, Personphone,PersonalIcon;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_nickname).init();
    }

    @Override
    public void initView() {
        ct_nickname.setAction(this);
        PersonalIcon = getIntent().getStringExtra("icon");
        PersonalName = getIntent().getStringExtra("name");
        Personphone = getIntent().getStringExtra("phone");
        uid = AppUtile.getUid(this);
        utoken = AppUtile.getTicket(this);
        if (!PersonalName.equals("")) {
            et_nickname.setText(PersonalName);
        } else {
            et_nickname.setText(AppUtile.hideCardNo(Personphone));
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_nickname;
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
                isInfo();
        }
    }

    /**
     * 修改昵称*
     */
    public void updateUserInfo() {
        LoadingDialog.showDialogForLoading(NickNameActivity.this, "加载中....");
        name = et_nickname.getText().toString().trim();
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUSERINF)
                .tag(this)
                .params("uid", uid)
                .params("uicon",PersonalIcon)
                .params("uname", name)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "修改昵称: " + response.body().code);
                        Log.e("text", "修改昵称: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            LoadingDialog.hide();
                            ToastUtils.showShort(NickNameActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(NickNameActivity.this, response.body().msg);
                            LoadingDialog.hide();
                        }
                    }
                });
    }

    /*昵称非空判断*/
    private void isInfo() {
        if (AppUtile.isEditText(et_nickname)) {
            updateUserInfo();
        } else {
            ToastUtils.showShort(context, "昵称不能为空！");
        }

    }

}
