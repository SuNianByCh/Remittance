package com.yaer.remittance.ui.user_modular.shopinfo;

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
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ClearEditText;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 设置店铺名称
 */

public class ShopNickNameActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_shop_nickname)
    CustomTitlebar ct_shop_nickname;
    @BindView(R.id.et_shop_nickname)
    ClearEditText et_shop_nickname;
    private String Shopname;
    private String name;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_shop_nickname).init();
    }

    @Override
    public void initView() {
        ct_shop_nickname.setAction(this);
        Shopname = getIntent().getStringExtra("shopname");
        et_shop_nickname.setText(Shopname);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.shop_nickname;
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
        LoadingDialog.showDialogForLoading(ShopNickNameActivity.this, "加载中....");
        name = et_shop_nickname.getText().toString().trim();
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPINFO)
                .tag(this)
                .params("sid", AppUtile.getShopid(this))
                .params("sname", name)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "修改昵称: " + response.body().code);
                        Log.e("text", "修改昵称: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            LoadingDialog.hide();
                            ToastUtils.showShort(ShopNickNameActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(ShopNickNameActivity.this, response.body().msg);
                            LoadingDialog.hide();
                        }
                    }
                });
    }

    /*昵称非空判断*/
    private void isInfo() {
        if (AppUtile.isEditText(et_shop_nickname)) {
            updateUserInfo();
        } else {
            ToastUtils.showShort(context, "店铺名称不能为空！");
        }

    }

}
