package com.yaer.remittance.ui.user_modular.shopinfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.BaseTakePhotoActivity;
import com.yaer.remittance.base.OssUtile;
import com.yaer.remittance.bean.LinkLabelBean;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.eventmessage.TypeMessage;
import com.yaer.remittance.ui.adapter.UserEnterListAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.personal.NickNameActivity;
import com.yaer.remittance.ui.user_modular.personal.PersonalProfileActivity;
import com.yaer.remittance.ui.user_modular.personal.TakePhotoActivity;
import com.yaer.remittance.ui.user_modular.personal.UserHeadPortraitActivity;
import com.yaer.remittance.ui.user_modular.personal.UserSelectSexActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.collect.UserCollectActivity;
import com.yaer.remittance.ui.user_modular.user_seller.enter.UserEnterActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.EventMessage;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.UpLoad;
import com.yaer.remittance.utils.WsbPopUtile;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 店铺设置
 */
public class ShopInformationActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_shop_persona_linformation)
    CustomTitlebar ct_shop_persona_linformation;
    /*头像*/
    @BindView(R.id.tv_shop_head_portrait)
    TextView tv_shop_head_portrait;
    /*头像占位图*/
    @BindView(R.id.civ_shop_personal)
    CircleImageView civ_shop_personal;
    /*昵称*/
    @BindView(R.id.shop_person_nicename)
    TextView shop_person_nicename;
    /*显示昵称*/
    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    /*简介*/
    @BindView(R.id.tv_shop_sdesc)
    TextView tv_shop_sdesc;
    /*店铺简介内容*/
    @BindView(R.id.tv_shop_sdesc_detils)
    TextView tv_shop_sdesc_detils;
    /*店铺标签*/
    @BindView(R.id.tv_shop_slabel)
    TextView tv_shop_slabel;
    /*店铺标签详情*/
    @BindView(R.id.tv_shop_slabel_detils)
    TextView tv_shop_slabel_detils;
    /*店铺背景*/
    @BindView(R.id.iv_shopinfo_image)
    CircleImageView iv_shopinfo_image;
    @BindView(R.id.rl_shop_headportra)
    LinearLayout rl_shop_headportra;

    private String shopname;//店铺名
    private String shopimg;//店铺头像
    private String sdesc;//店铺简介
    private String sbgimg;//店铺背景
    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_shop_persona_linformation).init();
    }

    @Override
    public void onResume() {
        getShopInfoBySid();
        super.onResume();
    }

    @Override
    public void initView() {
        ct_shop_persona_linformation.setAction(this);
        lableshow();
    }

    /*获取店铺信息*/
    public void getShopInfoBySid() {
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<ShopInfoByidBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (response.body().result != null) {
                                shopname = response.body().result.getShopname();//店铺名称
                                shopimg = response.body().result.getShopimg();//店铺头像
                                sbgimg=response.body().result.getSbgimg();//店铺背景
                                sdesc = response.body().result.getSdesc();//店铺简介
                                tv_shop_name.setText(shopname);//店铺名称
                                tv_shop_sdesc_detils.setText(sdesc);//店铺介绍
                                tv_shop_slabel_detils.setText(response.body().result.getShoplabel().toString());
                                Glide.with(ShopInformationActivity.this).load(response.body().result.getShopimg()).fitCenter().into(civ_shop_personal);//店铺头像
                                Glide.with(ShopInformationActivity.this).load(sbgimg).fitCenter().into(iv_shopinfo_image);//店铺背景
                            } else {
                                ToastUtils.showToast("暂无信息");
                            }
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }
                });
    }


    private void lableshow() {
        str.add("珠串雅玩");
        str.add("金石篆刻");
        str.add("民俗工艺");
        str.add("鬼斧神雕");
        str.add("古瓷韵味");
        str.add("当代工艺");
        str.add("珠宝玉石");
        str.add("陈设摆件");
        str.add("古董珍品");
        for (int i = 0; i < str.size(); i++) {
            linkLabelBean = new LinkLabelBean();
            linkLabelBean.setStr(str.get(i));
            list.add(linkLabelBean);
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.shop_persona_linformation;
    }

    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @OnClick({R.id.tv_shop_head_portrait, R.id.tv_shop_name, R.id.tv_shop_sdesc, R.id.tv_shop_slabel, R.id.iv_shopinfo_image})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.tv_shop_head_portrait:
                bundle.putString("shopimg", shopimg);
                goToActivity(ShopHeadPortraitActivity.class, bundle);
                break;
            case R.id.tv_shop_name:
                bundle.putString("shopname", shopname);
                goToActivity(ShopNickNameActivity.class, bundle);
                break;
            case R.id.tv_shop_sdesc:
                bundle.putString("sdesc", sdesc);
                goToActivity(ShoplProfileActivity.class, bundle);
                break;
            case R.id.tv_shop_slabel:
                shodialog();
                break;
            case R.id.iv_shopinfo_image:
                bundle.putString("sbgimg", sbgimg);
                goToActivity(ShopStoreBackgroundActivity.class, bundle);
                break;
        }
    }

    //标签
    private List<LinkLabelBean> list = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    private List<String> str = new ArrayList<>();
    private List<String> hashMap = new ArrayList<>();
    private LinkLabelBean linkLabelBean;
    private String lable;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    private void shodialog() {
        View view = View.inflate(ShopInformationActivity.this, R.layout.user_enter_list_item, null);
        RecyclerView lv = (RecyclerView) view.findViewById(R.id.recyclerview_user_enter_list);
        Button btn_enter_confirm = view.findViewById(R.id.btn_enter_confirm);
        Button btn_enter_cancel = view.findViewById(R.id.btn_enter_cancel);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        lv.setLayoutManager(mLayoutManager);
        UserEnterListAdapter userEnterListAdapter = new UserEnterListAdapter(getApplicationContext(), list);
        lv.setAdapter(userEnterListAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(ShopInformationActivity.this);
        final AlertDialog dialog = builder.setView(view).show();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                list.get(i).setSelect(false);
            }
        }
        btn_enter_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这边其实可以优化 但是时间不够就不再做优化。
                boolean zhishao = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()) {
                        zhishao = true;
                    }
                }
                if (!zhishao) {
                    ToastUtils.showToast("至少选择一个标签。");
                    return;
                }
                hashMap.clear();
                list1.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()) {
                        list1.add(list.get(i).getStr());
                    }
                }
                if (list1.size() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        hashMap.add(list1.get(i));
                    }
                    for (int i = 0; i < hashMap.size(); i++) {
                        hashMap.get(i);
                    }
                }
                StringBuilder images = new StringBuilder();
                for (String s : hashMap) {
                    images.append(s).append(",");
                }
                if (images.length() > 0) {
                    lable = images.toString().substring(0, images.length() - 1);
                } else {
                    lable = "";
                }
                updateUserInfoPersonal(lable);
                dialog.cancel();
            }
        });
        btn_enter_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
    }

    /**
     * 修改店铺标签*
     */
    public void updateUserInfoPersonal(String lable) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPINFO)
                .tag(this)
                .params("sid", AppUtile.getShopid(this))
                .params("slabel", lable)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(ShopInformationActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺简介: " + response.body().code);
                        Log.e("text", "店铺简介: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            getShopInfoBySid();
                            ToastUtils.showShort(ShopInformationActivity.this, response.body().msg);
                            stopDialog();
                        } else {
                            stopDialog();
                            ToastUtils.showShort(ShopInformationActivity.this, response.body().msg);
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

    @Override
    public void initData() {
    }
}
