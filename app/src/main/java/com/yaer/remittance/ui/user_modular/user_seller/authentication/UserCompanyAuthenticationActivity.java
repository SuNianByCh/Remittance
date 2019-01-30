package com.yaer.remittance.ui.user_modular.user_seller.authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.OssUtile;
import com.yaer.remittance.bean.SelectCompanyAuthenticationInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.personal.TakePhotoActivity;
import com.yaer.remittance.ui.user_modular.user_seller.enter.UserEnterActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.EventMessage;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.UpLoad;
import com.yaer.remittance.view.LoadingDialog2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 认证中心_企业认证
 */

public class UserCompanyAuthenticationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_company_authentication)
    RelativeLayout rl_company_authentication;
    /*机构代码证*/
    @BindView(R.id.et_dealer_idCard)
    EditText et_dealer_idCard;
    /*企业名称*/
    @BindView(R.id.tv_dealer_storename)
    EditText tv_dealer_storename;
    /*企业法人名称*/
    @BindView(R.id.et_dealer_name)
    EditText et_dealer_name;
    /*企业认证身份证*/
    @BindView(R.id.et_idCard)
    EditText et_idCard;
    /*企业营业执照*/
    @BindView(R.id.iv_company_photo)
    ImageView iv_company_photo;
    /*手持身份证正面照*/
    @BindView(R.id.iv_company_idcard_just)
    ImageView iv_company_idcard_just;
    /*手持身份证反面照*/
    @BindView(R.id.iv_company_idcard_back)
    ImageView iv_company_idcard_back;
    /*企业认证提交*/
    @BindView(R.id.btn_dealer_RZsubmit)
    Button btn_dealer_RZsubmit;
    private String companyname, companyid, com_persionalname, com_persion_num;
    private int uid;
    private int enterprisecertification;//企业认证状态
    private BottomSheetDialog sheetDialog;
    /*企业认证状态描述*/
    @BindView(R.id.tv_company_state)
    TextView tv_company_state;
    /*布局外填充*/
    @BindView(R.id.ll_company)
    LinearLayout ll_company;
    @BindView(R.id.btn_settled_next_company)
    Button btn_settled_next_company;
    /*设置图片路径*/
    private String eacardimg, eacardimgback, eacompanyimg;
    /*上传图片路径*/
    private String companyimg = "", com_persion_img = "", com_persion_back = "";
    @BindView(R.id.iv_left_rz)
    ImageView iv_left_rz;
    @BindView(R.id.tv_right_rz)
    TextView tv_right_rz;
    @BindView(R.id.v_company)
    View v_company;
    @BindView(R.id.btn_update_company)
    Button btn_update_company;
    private String ecid;
    private String zmdata = "";
    private String fmdata = "";
    private String cmdata = "";
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.v_company).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        enterprisecertification = (int) SharedPreferencesUtils.getData(UserCompanyAuthenticationActivity.this, "enterprisecertification", 0);
        btn_dealer_RZsubmit.setOnClickListener(this);
        iv_company_idcard_just.setOnClickListener(this);
        iv_company_idcard_back.setOnClickListener(this);
        iv_company_photo.setOnClickListener(this);
        btn_update_company.setOnClickListener(this);
        btn_settled_next_company.setOnClickListener(this);
        iv_left_rz.setOnClickListener(this);
        tv_right_rz.setOnClickListener(this);
        eacardimg = "auction/user/" + getImageObjectKey();
        eacardimgback = "auction/user/" + getImageObjectKey();
        eacompanyimg = "auction/user/" + getImageObjectKey();
        if (enterprisecertification == 0) {
            tv_company_state.setText("认证申请的真实姓名将是成为提现的姓名，一经认证，无法修改");
            btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
            iv_company_idcard_just.setClickable(true);
            iv_company_photo.setClickable(true);
            iv_company_idcard_back.setClickable(true);
            tv_dealer_storename.setFocusable(true);
            et_dealer_idCard.setFocusable(true);
            btn_update_company.setVisibility(View.GONE);
            btn_settled_next_company.setVisibility(View.GONE);
        } else if (enterprisecertification == 3) {
            tv_company_state.setText("审核被拒");
            SelectCompanyAuthenticationInfo();
            et_dealer_name.setFocusable(false);
            et_idCard.setFocusable(false);
            tv_dealer_storename.setFocusable(false);
            et_dealer_idCard.setFocusable(false);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_company.setVisibility(View.GONE);
            tv_right_rz.setVisibility(View.VISIBLE);
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            iv_company_photo.setClickable(false);
            btn_settled_next_company.setVisibility(View.GONE);
        } else if (enterprisecertification == 2) {
            tv_company_state.setText("正在认证中");
            SelectCompanyAuthenticationInfo();
            et_dealer_name.setFocusable(false);
            et_idCard.setFocusable(false);
            tv_dealer_storename.setFocusable(false);
            et_dealer_idCard.setFocusable(false);
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            iv_company_photo.setClickable(false);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_company.setVisibility(View.GONE);
            btn_settled_next_company.setVisibility(View.GONE);
        } else if (enterprisecertification == 1) {
            SelectCompanyAuthenticationInfo();
            tv_company_state.setText("审核通过");
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            iv_company_photo.setClickable(false);
            et_dealer_name.setFocusable(false);
            tv_dealer_storename.setFocusable(false);
            et_dealer_idCard.setFocusable(false);
            et_idCard.setFocusable(false);
            btn_settled_next_company.setVisibility(View.VISIBLE);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_company.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
    }

    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_company_rz;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_rz:
                finish();
                break;
            case R.id.btn_settled_next_company:
                goToActivity(UserEnterActivity.class);
                finish();
                break;
            case R.id.tv_right_rz:
                tv_company_state.setText("请填写相关资料及上传证照,企业认证后，申请账户的名称将是该企业的名称，一经认证，无法修改");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
                btn_update_company.setVisibility(View.VISIBLE);
                tv_dealer_storename.setFocusable(true);
                tv_dealer_storename.setFocusableInTouchMode(true);
                tv_dealer_storename.requestFocus();
                et_dealer_idCard.setFocusable(true);
                et_dealer_idCard.setFocusableInTouchMode(true);
                et_dealer_idCard.requestFocus();
                et_dealer_name.setFocusable(true);
                et_dealer_name.setFocusableInTouchMode(true);
                et_dealer_name.requestFocus();
                et_idCard.setFocusable(true);
                et_idCard.setFocusableInTouchMode(true);
                et_idCard.requestFocus();
                iv_company_idcard_just.setClickable(true);
                iv_company_idcard_back.setClickable(true);
                iv_company_photo.setClickable(true);
                tv_dealer_storename.setText("");
                et_dealer_idCard.setText("");
                et_dealer_name.setText("");
                et_idCard.setText("");
                Glide.with(UserCompanyAuthenticationActivity.this).load(R.drawable.add_photo).fitCenter().into(iv_company_idcard_just);
                Glide.with(UserCompanyAuthenticationActivity.this).load(R.drawable.add_photo).fitCenter().into(iv_company_idcard_back);
                Glide.with(UserCompanyAuthenticationActivity.this).load(R.drawable.add_photo).fitCenter().into(iv_company_photo);
                break;
            /*修改企业认证*/
            case R.id.btn_update_company:
                companyname = tv_dealer_storename.getText().toString().trim();
                companyid = et_dealer_idCard.getText().toString().trim();
                com_persionalname = et_dealer_name.getText().toString().trim();
                com_persion_num = et_idCard.getText().toString().trim();
                btn_update_company.setTag("2");
                if (!AppUtile.isEditText(et_dealer_idCard)) {
                    ToastUtils.showShort(context, "机构代码不能为空！");
                } else if (!AppUtile.isEditText(tv_dealer_storename)) {
                    ToastUtils.showShort(context, "企业名称不能为空！");
                } else if (cmdata.equals("") && cmdata == "") {
                    ToastUtils.showShort(context, "营业执照不能为空！");
                } else if (!AppUtile.isEditText(et_dealer_name)) {
                    ToastUtils.showShort(context, "法人姓名不能为空！");
                } else if (!AppUtile.isEditText(et_idCard)) {
                    ToastUtils.showShort(context, "身份证号不能为空！");
                } else if (zmdata.equals("") && zmdata == "") {
                    ToastUtils.showShort(context, "身份证正面照片不能为空！");
                } else if (fmdata.equals("") && fmdata == "") {
                    ToastUtils.showShort(context, "身份证反面照片不能为空！");
                } else {
                    zhengmian();
                    fanmian();
                    companyanmian();
                }
                break;
            /*正面照片*/
            case R.id.iv_company_idcard_just:
                //   showDialog();
                UpLoad upLoad = new UpLoad(new UpLoad.ActionCallback() {
                    @Override
                    public void onClickCamera() {
                        startActForResult(1, 101);
                    }

                    @Override
                    public void onClickPhoto() {
                        startActForResult(2, 102);
                    }
                });
                upLoad.uploadImage(this);
                break;
            /*反面照片*/
            case R.id.iv_company_idcard_back:
                UpLoad upLoad1 = new UpLoad(new UpLoad.ActionCallback() {
                    @Override
                    public void onClickCamera() {
                        startActForResult(1, 104);
                    }

                    @Override
                    public void onClickPhoto() {
                        startActForResult(2, 105);
                    }
                });
                upLoad1.uploadImage(this);
                break;
            /*营业执照照片*/
            case R.id.iv_company_photo:
                UpLoad upLoad2 = new UpLoad(new UpLoad.ActionCallback() {
                    @Override
                    public void onClickCamera() {
                        startActForResult(1, 106);
                    }

                    @Override
                    public void onClickPhoto() {
                        startActForResult(2, 107);
                    }
                });
                upLoad2.uploadImage(this);
                break;
            case R.id.btn_dealer_RZsubmit:
                companyname = tv_dealer_storename.getText().toString().trim();
                companyid = et_dealer_idCard.getText().toString().trim();
                com_persionalname = et_dealer_name.getText().toString().trim();
                com_persion_num = et_idCard.getText().toString().trim();
                btn_dealer_RZsubmit.setTag("1");
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else if (!AppUtile.isEditText(et_dealer_idCard)) {
                    ToastUtils.showShort(context, "机构代码不能为空！");
                } else if (!AppUtile.isEditText(tv_dealer_storename)) {
                    ToastUtils.showShort(context, "企业名称不能为空！");
                } else if (cmdata.equals("") && cmdata == "") {
                    ToastUtils.showShort(context, "营业执照不能为空！");
                } else if (!AppUtile.isEditText(et_dealer_name)) {
                    ToastUtils.showShort(context, "法人姓名不能为空！");
                } else if (!AppUtile.isEditText(et_idCard)) {
                    ToastUtils.showShort(context, "身份证号不能为空！");
                } else if (zmdata.equals("") && zmdata == "") {
                    ToastUtils.showShort(context, "身份证正面照片不能为空！");
                } else if (fmdata.equals("") && fmdata == "") {
                    ToastUtils.showShort(context, "身份证反面照片不能为空！");
                } else {
                    zhengmian();
                    fanmian();
                    companyanmian();
                }
                break;
        }
    }

    private void zhengmian() {
        OssUtile ossUtile = new OssUtile(this, zmdata);
        ossUtile.asyncPutObjectFromLocalFile(eacardimg, EventMessage.Type.COMPANYPERSIONJUSTIMAGE);
    }

    private void fanmian() {
        OssUtile ossUtile = new OssUtile(this, fmdata);
        ossUtile.asyncPutObjectFromLocalFile(eacardimgback, EventMessage.Type.COMPANYPERSIONBACKIMAGE);
    }

    private void companyanmian() {
        OssUtile ossUtile = new OssUtile(this, cmdata);
        ossUtile.asyncPutObjectFromLocalFile(eacompanyimg, EventMessage.Type.COMPANYIMAGE);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 添加企业认证信息
     */
    private void AddCompanyauthenticatInfo(String companyname, String companyid, String companyimg, String com_persionalname, String com_persion_num, String com_persion_img, String com_persion_back) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDENTERPRISEAUTHENTICATIONINFO)
                .tag(this)
                .params("uid", uid)
                .params("ecname", companyname)
                .params("ecnumber", companyid)
                .params("ecimg", companyimg)
                .params("eclegalpersonnamecardnum", com_persion_num)
                .params("eclegalpersonname", com_persionalname)
                .params("eclegalpersoncardimg", com_persion_img)
                .params("eclegalpersoncardimgback", com_persion_back)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserCompanyAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加企业认证信息: " + response.body().code);
                        Log.e("text", "添加企业认证信息: " + response.body().result);
                        Log.e("text", "添加企业认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserCompanyAuthenticationActivity.this, response.body().msg);
                            SharedPreferencesUtils.saveData(context, "fragmentShow", 1);
                            finish();
                        } else {
                            ToastUtils.showShort(UserCompanyAuthenticationActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                    }
                });
    }

    /**
     * 修改企业认证信息
     */
    private void UpdateCompanyauthenticatInfo(String companyname, String companyid, String companyimg, String com_persionalname, String com_persion_num, String com_persion_img, String com_persion_back) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEENTERPRISEAUTHENTICATIONINFO)
                .tag(this)
                .params("uid", uid)
                .params("ecname", companyname)
                .params("ecnumber", companyid)
                .params("ecimg", companyimg)
                .params("eclegalpersonnamecardnum", com_persion_num)
                .params("eclegalpersonname", com_persionalname)
                .params("eclegalpersoncardimg", com_persion_img)
                .params("eclegalpersoncardimgback", com_persion_back)
                .params("eclegalpersoncardimgback", ecid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserCompanyAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "修改企业认证信息: " + response.body().code);
                        Log.e("text", "修改企业认证信息: " + response.body().result);
                        Log.e("text", "修改企业认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserCompanyAuthenticationActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserCompanyAuthenticationActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                    }
                });
    }

    /**
     * 查询企业认证信息
     */
    public void SelectCompanyAuthenticationInfo() {
        OkGo.<BaseMode<SelectCompanyAuthenticationInfoBean>>post(AppApi.BASE_URL + AppApi.SELECTENTERPRISEAUTHENTICATIONINFO)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<SelectCompanyAuthenticationInfoBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<SelectCompanyAuthenticationInfoBean>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserCompanyAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<SelectCompanyAuthenticationInfoBean>> response) {
                        Log.e("text", "查询企业认证信息: " + response.body().code);
                        Log.e("text", "查询企业认证信息: " + response.body().result);
                        Log.e("text", "查询企业认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ecid = response.body().result.getEcid();//认证id,修改备用
                            tv_dealer_storename.setText(response.body().result.getEcname());//认证企业名称
                            et_dealer_idCard.setText(response.body().result.getEcnumber());//认证企业机构代码证
                            et_idCard.setText(response.body().result.getEclegalpersonnamecardnum());//认证身份证号
                            et_dealer_name.setText(response.body().result.getEclegalpersonname());//认证企业法人名称
                            String company = response.body().result.getEcimg();//认证企业营业执照
                            String just = response.body().result.getEclegalpersoncardimg();//正面身份证图片
                            String back = response.body().result.getEclegalpersoncardimgback();//反面身份证图片
                            Glide.with(UserCompanyAuthenticationActivity.this).load(just).fitCenter().into(iv_company_idcard_just);
                            Glide.with(UserCompanyAuthenticationActivity.this).load(back).fitCenter().into(iv_company_idcard_back);
                            Glide.with(UserCompanyAuthenticationActivity.this).load(company).fitCenter().into(iv_company_photo);
                        } else {
                            ToastUtils.showShort(UserCompanyAuthenticationActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<SelectCompanyAuthenticationInfoBean>> response) {
                        super.onError(response);
                        stopDialog();
                        ToastUtils.showToast("网络连接异常");
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                    }
                });
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    private void startActForResult(int flag, int requestCode) {
        startActivityForResult(new Intent(this, TakePhotoActivity.class).putExtra("flag", flag), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("test", "onActivityResult: " + requestCode);
        Log.e("test", "onActivityResult: " + resultCode);
        Log.e("test", "onActivityResult: " + data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == 101) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                //  ToastUtils.showToast("您没有拍照,请拍照");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_just);
                zmdata = data1;
                //showoos(data1, eacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 102) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_just);
                zmdata = data1;
                //showoos(data1, eacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 104) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_back);
                fmdata = data1;
                //showoos1(data1, eacardimgback);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 105) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                //ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_back);
                fmdata = data1;
            }
            Log.v("WZ", data1);
        } else if (requestCode == 106) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_photo);
                cmdata = data1;
                //showoos1(data1, eacardimgback);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 107) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                //ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_photo);
                cmdata = data1;
            }
            Log.v("WZ", data1);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage eventMessage) {
        //用户退出操作
        if (eventMessage != null) {
            String xinzheng = (String) btn_dealer_RZsubmit.getTag();
            String xiugai = (String) btn_update_company.getTag();
            int tag = eventMessage.getTag();
            if (tag == EventMessage.Type.COMPANYPERSIONJUSTIMAGE) {
                Bundle bundle = eventMessage.getBundle();
                com_persion_img = bundle.getString("img");
            } else if (tag == EventMessage.Type.COMPANYPERSIONBACKIMAGE) {
                Bundle bundle = eventMessage.getBundle();
                com_persion_back = bundle.getString("img");
            } else if (tag == EventMessage.Type.COMPANYIMAGE) {
                Bundle bundle = eventMessage.getBundle();
                companyimg = bundle.getString("img");
            }
            if (com_persion_img.equals("") || com_persion_back.equals("") || companyimg.equals("")) {
                // ToastUtils.showToast("图片不能为空");
            } else if (xinzheng != null && xinzheng.equals("1") && !"".equals(com_persion_img) && !"".equals(com_persion_back) && !"".equals(companyimg)) {
                AddCompanyauthenticatInfo(companyname, companyid, companyimg, com_persionalname, com_persion_num, com_persion_img, com_persion_back);
            } else if (xiugai != null && xiugai.equals("2") && !"".equals(com_persion_img) && !"".equals(com_persion_back) && !"".equals(companyimg)) {
                UpdateCompanyauthenticatInfo(companyname, companyid, companyimg, com_persionalname, com_persion_num, com_persion_img, com_persion_back);
            }
        }
    }
}
