package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 添加提现账号
 */
public class AddIdPhoneActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_add_id_phone)
    CustomTitlebar ct_add_id_phone;
    private int uid;
    private String bankcard;//银行卡号
    private String realName;//银行卡号卡主名称
    private String cardname;//银行卡类型名称
    private String bankimage;//银行图片
    private String bankname;//银行名称

    /*银行图片*/
    @BindView(R.id.iv_bankimage)
    ImageView iv_bankimage;
    /*银行名称*/
    @BindView(R.id.tv_realName)
    TextView tv_realName;
    /*银行卡类型名称*/
    @BindView(R.id.tv_cardname)
    TextView tv_cardname;
    @BindView(R.id.btn_bank_submit)
    Button btn_bank_submit;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_add_id_phone).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        bankcard = getIntent().getStringExtra("bankcard");
        realName = getIntent().getStringExtra("realName");
        cardname = getIntent().getStringExtra("cardname");
        bankname = getIntent().getStringExtra("bankname");
        bankimage = getIntent().getStringExtra("bankimage");
        Glide.with(this).load(bankimage).fitCenter().into(iv_bankimage);//银行logo
        tv_realName.setText(bankname);
        tv_cardname.setText(cardname);
        ct_add_id_phone.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_id_phone;
    }

    @OnClick({R.id.btn_bank_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bank_submit:
                AddBankcard();
                break;
        }
    }


    /**
     * 添加银行卡信息
     * http://api.paiphui.com/user/addBankCard?bperplename=陈宇航&bname=中国工商银行&bcardnum=6245644646789456464&uid=5
     */
    private void AddBankcard() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDBANKCARD)
                .tag(this)
                .params("bname", bankname)
                .params("bcardnum", bankcard)
                .params("uid", uid)
                .params("bperplename", realName)
                .params("bimg",bankimage)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加银行卡信息: " + response.body().code);
                        Log.e("text", "添加银行卡信息: " + response.body().result);
                        Log.e("text", "添加银行卡信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(AddIdPhoneActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(AddIdPhoneActivity.this, response.body().msg);
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

