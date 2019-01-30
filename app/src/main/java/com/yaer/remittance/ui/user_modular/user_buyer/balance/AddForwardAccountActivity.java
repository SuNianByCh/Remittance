package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseBaiduMode;
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
public class AddForwardAccountActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_add_forward)
    CustomTitlebar ct_add_forward;
    /*提交银行卡信息按钮下一步*/
    @BindView(R.id.btn_add_forward_confirm)
    Button btn_add_forward_confirm;
    /*持卡人名称*/
    @BindView(R.id.et_forward_name)
    EditText et_forward_name;
    /*持卡人卡号*/
    @BindView(R.id.et_bank_money)
    EditText et_bank_money;
    private int uid;
    private String bankcard;
    private String realName;
    private String bankname;
    private String cardname;
    private String bankimage;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_add_forward).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        ct_add_forward.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_forward_account;
    }

    @OnClick({R.id.btn_add_forward_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_forward_confirm:
                if (!AppUtile.isEditText(et_forward_name)) {
                    ToastUtils.showShort(context, "持卡人姓名不能为空！");
                } else if (!AppUtile.isEditText(et_bank_money)) {
                    ToastUtils.showShort(context, "持卡人银行卡账号不能为空！");
                } else if(!AppUtile.checkBankCard(et_bank_money.getText().toString().trim())){
                    ToastUtils.showShort(context, "持卡人银行卡账号不正确！");
                }else {
                    String forwardname = et_forward_name.getText().toString().trim();
                    String bankmoney = et_bank_money.getText().toString().trim();
                    ValidationBankCard(forwardname, bankmoney);
                }
                break;
        }
    }

    private void ValidationBankCard(String realName, String bankcard) {
        String url = "https://aliyun-bankcard-verify.apistore.cn/bank?";// + "realName=" + realName + "&bankcard=" + bankcard
        String appcode = "653f6f7364b34651ae0c67585fba5a16";
        OkGo.<BaseBaiduMode>get(url)
                .tag(this)
                .headers("Authorization", "APPCODE " + appcode)
                .params("Mobile", "")
                .params("realName", realName)
                .params("cardNo", "")
                .params("bankcard", bankcard)
                .execute(new JsonCallback<BaseBaiduMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseBaiduMode> response) {

                       /* ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getResult().toString());Log.e("text", "添加银行卡信息: " + response.body().getResult().getInformation().getBankname());
                        Log.e("text", "添加银行卡信息: " + response.body().getResult().getInformation().getAbbreviation());
                        Log.e("text", "添加银行卡信息: " + response.body().getReason());
                        Log.e("text", "添加银行卡信息: " + response.body().getError_code());*/
                        if (response.body().getError_code().equals(Constant.BANKCODESUCCESS)) {
                            String bankcard = response.body().getResult().getBankcard();//银行卡号
                            String realName= response.body().getResult().getRealName();//银行卡号卡主名称
                            String bankname= response.body().getResult().getInformation().getBankname();//银行名称
                            String bankimage=response.body().getResult().getInformation().getBankimage();//银行图片
                            AddBankcard(bankname,bankcard,realName,bankimage);
                         /*   ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                            Bundle bundle = new Bundle();
                            bundle.putString("bankcard", response.body().getResult().getBankcard());//银行卡号
                            bundle.putString("realName", response.body().getResult().getRealName());//银行卡号卡主名称
                            bundle.putString("bankname", response.body().getResult().getInformation().getBankname());//银行名称
                            bundle.putString("cardname", response.body().getResult().getInformation().getCardname());//银行卡类型
                            bundle.putString("bankimage", response.body().getResult().getInformation().getBankimage());//银行图片
                            goToActivity(AddIdPhoneActivity.class, bundle);*/
                        } else if (response.body().getError_code().equals(Constant.THISTYPEOFBANKCARDVERIFIVATION)) {//不支持此类银行卡校验
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.INVALIDCARDNUMBER)) {//无效卡号
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.PLEASECONTACTTHEISSUINGPARTY)) {//此卡被没收，请于发卡方联系
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.FAILUREOFCARDHOLDERAUTHENTICATION)) {//持卡人认证失败
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.THECARDISNOTINITIALIZEDORSLEEPINGCARD)) {//该卡未初始化或睡眠卡
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.CHEATINGCARDSWALLOWINGCARD)) {//作弊卡，吞卡
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.THISCARDHASBEENLOST)) {//此卡已经挂失
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.THECARDWASCONFISCATED)) {//此卡被没收
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.THECARDHASEXPIRED)) {//该卡已过期
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.THEISSUINGPARTYDOESNOTALLOWTHISTRANSACTION)) {//发卡方不允许此交易
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.RESTRICTEDCARDS)) {//受限制的卡
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.PASSWORDERRORNUMBEROVERRUN)) {//密码错误次数超限
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.INVALIDIDCARDNUMBER)) {//无效身份证号码
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if (response.body().getError_code().equals(Constant.USERUNOPENEDAUTHENTICATEDPAYMENT)) {//用户未开通认证支付
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }else if(response.body().getError_code().equals(Constant.FAILUREOFCERTIFICATION)) {//认证不通过
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().getReason());
                        }
                    }
                });
    }

    /**
     * 添加银行卡信息
     * http://api.paiphui.com/user/addBankCard?bperplename=陈宇航&bname=中国工商银行&bcardnum=6245644646789456464&uid=5
     */
    private void AddBankcard(String bankname,String bankcard,String realName,String bankimage) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDBANKCARD)
                .tag(this)
                .params("bname", bankname)
                .params("bcardnum", bankcard)
                .params("uid", uid)
                .params("bperplename", realName)
                .params("bimg", bankimage)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加银行卡信息: " + response.body().code);
                        Log.e("text", "添加银行卡信息: " + response.body().result);
                        Log.e("text", "添加银行卡信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(AddForwardAccountActivity.this, response.body().msg);
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

