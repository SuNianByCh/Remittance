package com.yaer.remittance.ui.user_modular.user_seller.shopinfo;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.personal.PersonalInformationActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.view.ContainsEmojiEditText;
import com.yaer.remittance.view.CustomTitlebar;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 店铺信息页
 */

public class UserShopInfoActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_shop_linformation)
    CustomTitlebar ct_shop_linformation;
    //店铺头像
    @BindView(R.id.shop_mCircleImageView)
    CircleImageView shop_mCircleImageView;
    //店铺名称
    @BindView(R.id.shop_name)
    TextView shop_name;
    //店铺标签
    @BindView(R.id.tv_shop_label)
    TextView tv_shop_label;
    //店铺等级
    @BindView(R.id.tv_shop_leven)
    TextView tv_shop_leven;
    //店铺介绍
    @BindView(R.id.tv_shopdesc)
    TextView tv_shopdesc;
    //认证类型
    @BindView(R.id.tv_authentication_type)
    TextView tv_authentication_type;
    //所在地区
    @BindView(R.id.tv_shop_where_region)
    TextView tv_shop_where_region;

    private String seller_name,shopicon;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_shop_linformation).init();
    }

    @Override
    public void initView() {
        ct_shop_linformation.setAction(this);
        getShopInfoBySid();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_shop_info;
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
        }
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
    //获取店铺信息
    public void getShopInfoBySid() {
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid",  AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<ShopInfoByidBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            shop_name.setText(response.body().result.getShopname());//卖家名称
                            shopicon = response.body().result.getShopimg();
                            Glide.with(UserShopInfoActivity.this).load(shopicon).fitCenter().into(shop_mCircleImageView);
                            Gson gson=new Gson();
                            tv_shop_label.setText(gson.toJson(response.body().result.getShoplabel()));
                        } else {
                            if (response.body().msg.equals("暂无店铺信息")) {
                            }
                        }
                    }
                });
    }

}

