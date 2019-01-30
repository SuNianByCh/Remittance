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

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
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
import com.yaer.remittance.bean.SelectPersonalAuthenticationInfoBean;
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
 * 认证中心_个人认证
 */

public class UserPersonalAuthenticationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_personal_authentication)
    RelativeLayout rl_personal_authentication;
    /*个人认证名称*/
    @BindView(R.id.et_personal_name)
    EditText et_personal_name;
    /*个人认证身份证*/
    @BindView(R.id.et_personal_idCard)
    EditText et_personal_idCard;
    /*手持身份证正面照*/
    @BindView(R.id.iv_company_idcard_just)
    ImageView iv_company_idcard_just;
    /*手持身份证反面照*/
    @BindView(R.id.iv_company_idcard_back)
    ImageView iv_company_idcard_back;
    /*个人认证提交*/
    @BindView(R.id.btn_dealer_RZsubmit)
    Button btn_dealer_RZsubmit;
    private String persionname, persionidCard;
    private int uid;
    private int personalauthentication;//个人认证状态
    private BottomSheetDialog sheetDialog;
    /*个人认证状态描述*/
    @BindView(R.id.tv_personal_state)
    TextView tv_personal_state;
    /*布局外填充*/
    @BindView(R.id.ll_personal)
    LinearLayout ll_personal;
    @BindView(R.id.btn_settled_next_persion)
    Button btn_settled_next_persion;
    /*设置图片路径*/
    private String pacardimg, pacardimgback;
    /*上传图片路径*/
    private String pacardimgs = "", pacardimgbacks = "";
    @BindView(R.id.iv_left_rz)
    ImageView iv_left_rz;
    @BindView(R.id.tv_right_rz)
    TextView tv_right_rz;
    @BindView(R.id.v_persion)
    View v_persion;
    @BindView(R.id.btn_update_persion)
    Button btn_update_persion;
    private String paid;
    private String zmdata = "";
    private String fmdata = "";
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
        mImmersionBar.titleBar(R.id.v_persion).init();
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(this);
        personalauthentication = (int) SharedPreferencesUtils.getData(UserPersonalAuthenticationActivity.this, "personalauthentication", 0);
        btn_dealer_RZsubmit.setOnClickListener(this);
        iv_company_idcard_just.setOnClickListener(this);
        iv_company_idcard_back.setOnClickListener(this);
        btn_update_persion.setOnClickListener(this);
        btn_settled_next_persion.setOnClickListener(this);
        iv_left_rz.setOnClickListener(this);
        tv_right_rz.setOnClickListener(this);
        pacardimg = "auction/user/" + getImageObjectKey();
        pacardimgback = "auction/user/" + getImageObjectKey();
        if (personalauthentication == 0) {
            tv_personal_state.setText("认证申请的真实姓名将是成为提现的姓名，一经认证，无法修改");
            btn_dealer_RZsubmit.setVisibility(View.VISIBLE);
            iv_company_idcard_just.setClickable(true);
            iv_company_idcard_back.setClickable(true);
            btn_update_persion.setVisibility(View.GONE);
            btn_settled_next_persion.setVisibility(View.GONE);
        } else if (personalauthentication == 3) {
            tv_personal_state.setText("审核被拒");
            SelectPersonalAuthenticationInfo();
            et_personal_name.setFocusable(false);
            et_personal_idCard.setFocusable(false);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_persion.setVisibility(View.GONE);
            tv_right_rz.setVisibility(View.VISIBLE);
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            btn_settled_next_persion.setVisibility(View.GONE);
        } else if (personalauthentication == 2) {
            tv_personal_state.setText("正在认证中");
            SelectPersonalAuthenticationInfo();
            et_personal_name.setFocusable(false);
            et_personal_idCard.setFocusable(false);
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_persion.setVisibility(View.GONE);
            btn_settled_next_persion.setVisibility(View.GONE);
        } else if (personalauthentication == 1) {
            SelectPersonalAuthenticationInfo();
            tv_personal_state.setText("审核通过");
            iv_company_idcard_just.setClickable(false);
            iv_company_idcard_back.setClickable(false);
            et_personal_name.setFocusable(false);
            et_personal_idCard.setFocusable(false);
            btn_settled_next_persion.setVisibility(View.VISIBLE);
            btn_dealer_RZsubmit.setVisibility(View.GONE);
            btn_update_persion.setVisibility(View.GONE);

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
        return R.layout.activity_personal_rz;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_rz:
                finish();
                break;
            case R.id.btn_settled_next_persion:
                goToActivity(UserEnterActivity.class);
                finish();
                break;
            case R.id.tv_right_rz:
                tv_personal_state.setText("认证申请的真实姓名将是成为提现的姓名，一经认证，无法修改");
                btn_dealer_RZsubmit.setVisibility(View.GONE);
                btn_update_persion.setVisibility(View.VISIBLE);
                et_personal_name.setFocusable(true);
                et_personal_name.setFocusableInTouchMode(true);
                et_personal_name.requestFocus();
                et_personal_idCard.setFocusable(true);
                et_personal_idCard.setFocusableInTouchMode(true);
                et_personal_idCard.requestFocus();
                iv_company_idcard_just.setClickable(true);
                iv_company_idcard_back.setClickable(true);
                et_personal_name.setText("");
                et_personal_idCard.setText("");
                Glide.with(UserPersonalAuthenticationActivity.this).load(R.drawable.add_photo).fitCenter().into(iv_company_idcard_just);
                Glide.with(UserPersonalAuthenticationActivity.this).load(R.drawable.add_photo).fitCenter().into(iv_company_idcard_back);
                break;
            /*修改个人认证*/
            case R.id.btn_update_persion:
                persionname = et_personal_name.getText().toString().trim();
                persionidCard = et_personal_idCard.getText().toString().trim();
                btn_update_persion.setTag("2");
                if (!AppUtile.isEditText(et_personal_name)) {
                    ToastUtils.showShort(context, "真实姓名不能为空！");
                } else if (!AppUtile.isEditText(et_personal_idCard)) {
                    ToastUtils.showShort(context, "身份证号不能为空！");
                } else if (zmdata.equals("") && zmdata == "") {
                    ToastUtils.showShort(context, "身份证正面照片不能为空！");
                } else if (fmdata.equals("") && fmdata == "") {
                    ToastUtils.showShort(context, "身份证反面照片不能为空！");
                } else {
                    zhengmian();
                    fanmian();
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
            case R.id.btn_dealer_RZsubmit:
                persionname = et_personal_name.getText().toString().trim();
                persionidCard = et_personal_idCard.getText().toString().trim();
                btn_dealer_RZsubmit.setTag("1");
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else if (!AppUtile.isEditText(et_personal_name)) {
                    ToastUtils.showShort(context, "真实姓名不能为空！");
                } else if (!AppUtile.isEditText(et_personal_idCard)) {
                    ToastUtils.showShort(context, "身份证号不能为空！");
                } else if (zmdata.equals("") && zmdata == "") {
                    ToastUtils.showShort(context, "身份证正面照片不能为空！");
                } else if (fmdata.equals("") && fmdata == "") {
                    ToastUtils.showShort(context, "身份证反面照片不能为空！");
                } else {
                    zhengmian();
                    fanmian();
                }
                break;
        }
    }

    private void zhengmian() {
        OssUtile ossUtile = new OssUtile(this, zmdata);
        ossUtile.asyncPutObjectFromLocalFile(pacardimg, EventMessage.Type.PERSIONIMAGE);
    }

    private void fanmian() {
        OssUtile ossUtile = new OssUtile(this, fmdata);
        ossUtile.asyncPutObjectFromLocalFile(pacardimgback, EventMessage.Type.PERSIONOTHERIMAGE);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 添加个人认证信息
     */
    private void AddPersonAlauthenticatInfo(String persionname, String persionid, String img, String imgback) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDPERSONALAUTHENTICATIONINFO)
                .tag(this)
                .params("uid", uid)
                .params("paname", persionname)
                .params("pacard", persionid)
                .params("pacardimg", img)
                .params("pacardimgback", imgback)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserPersonalAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加个人认证信息: " + response.body().code);
                        Log.e("text", "添加个人认证信息: " + response.body().result);
                        Log.e("text", "添加个人认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserPersonalAuthenticationActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserPersonalAuthenticationActivity.this, response.body().msg);
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
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    /**
     * 修改个人认证信息
     */
    private void UpdatePersonAlauthenticatInfo(String persionname, String persionid, String img, String imgback) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEPERSONALAUTHENTICATIONINFO)
                .tag(this)
                .params("paname", persionname)
                .params("pacard", persionid)
                .params("pacardimg", img)
                .params("pacardimgback", imgback)
                .params("paid", paid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserPersonalAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加个人认证信息: " + response.body().code);
                        Log.e("text", "添加个人认证信息: " + response.body().result);
                        Log.e("text", "添加个人认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserPersonalAuthenticationActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserPersonalAuthenticationActivity.this, response.body().msg);
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
     * 查询个人认证信息
     */
    public void SelectPersonalAuthenticationInfo() {
        OkGo.<BaseMode<SelectPersonalAuthenticationInfoBean>>post(AppApi.BASE_URL + AppApi.SELECTPERSONALAUTHENTICATIONINFO)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<SelectPersonalAuthenticationInfoBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<SelectPersonalAuthenticationInfoBean>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserPersonalAuthenticationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<SelectPersonalAuthenticationInfoBean>> response) {
                        Log.e("text", "查询个人认证信息: " + response.body().code);
                        Log.e("text", "查询个人认证信息: " + response.body().result);
                        Log.e("text", "查询个人认证信息: " + response.body().msg);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            paid = response.body().result.getPaid();//认证id,修改备用
                            et_personal_name.setText(response.body().result.getPaname());//认证名称
                            et_personal_idCard.setText(response.body().result.getPacard());//认证身份证
                            String just = response.body().result.getPacardimg();//正面身份证图片
                            String back = response.body().result.getPacardimgback();//反面身份证图片
                            Glide.with(UserPersonalAuthenticationActivity.this).load(just).fitCenter().into(iv_company_idcard_just);
                            Glide.with(UserPersonalAuthenticationActivity.this).load(back).fitCenter().into(iv_company_idcard_back);
                        } else {
                            ToastUtils.showShort(UserPersonalAuthenticationActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<SelectPersonalAuthenticationInfoBean>> response) {
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
                //showoos(data1, pacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 102) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_just);
                zmdata = data1;
                //showoos(data1, pacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 104) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_company_idcard_back);
                fmdata = data1;
                //showoos1(data1, pacardimgback);
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
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage eventMessage) {
        //用户退出操作
        if (eventMessage != null) {
            String xinzheng = (String) btn_dealer_RZsubmit.getTag();
            String xiugai = (String) btn_update_persion.getTag();
            int tag = eventMessage.getTag();
            if (tag == EventMessage.Type.PERSIONIMAGE) {
                Bundle bundle = eventMessage.getBundle();
                pacardimgs = bundle.getString("img");
            } else if (tag == EventMessage.Type.PERSIONOTHERIMAGE) {
                Bundle bundle = eventMessage.getBundle();
                pacardimgbacks = bundle.getString("img");
            }
            if (pacardimgs.equals("") || pacardimgbacks.equals("")) {
                // ToastUtils.showToast("图片不能为空");
            } else if (xinzheng != null && xinzheng.equals("1") && !"".equals(pacardimgs) && !"".equals(pacardimgbacks)) {
                AddPersonAlauthenticatInfo(persionname, persionidCard, pacardimgs, pacardimgbacks);
            } else if (xiugai != null && xiugai.equals("2") && !"".equals(pacardimgs) && !"".equals(pacardimgbacks)) {
                UpdatePersonAlauthenticatInfo(persionname, persionidCard, pacardimgs, pacardimgbacks);
            }
        }
    }
}
