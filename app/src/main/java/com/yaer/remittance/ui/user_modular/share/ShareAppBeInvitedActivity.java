package com.yaer.remittance.ui.user_modular.share;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.personal.NickNameActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 邀请来的输入验证码
 */
public class ShareAppBeInvitedActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_belcvited_app)
    CustomTitlebar ct_belcvited_app;
    @BindView(R.id.et_courier_number)
    MyEditText et_courier_number;
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.et_courier_number).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_share_app_belnvited;
    }

    @Override
    public void initView() {
        ct_belcvited_app.setAction(this);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                if (!AppUtile.isEditText(et_courier_number)) {
                    ToastUtils.showToast("邀请码不能为空!");
                } else {
                    AddinviTation(et_courier_number.getText().toString().trim());
                }
                break;
        }
    }

    /**
     * 邀请码*
     */
    public void AddinviTation(String icode) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDINVITATION)
                .tag(this)
                .params("iuid", AppUtile.getUid(this))
                .params("icode", icode)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "邀请码: " + response.body().code);
                        Log.e("text", "邀请码: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(ShareAppBeInvitedActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(ShareAppBeInvitedActivity.this, response.body().msg);
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
        }
    }
}
