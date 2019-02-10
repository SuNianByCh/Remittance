package com.yaer.remittance.ui.shopping_modular.shop;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.makeramen.roundedimageview.RoundedImageView;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.CouponShopAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.shopinfo.UserShopInfoActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;

/**
 * Created by deng on 2018/5/8
 * 店铺首页
 */

public class ShopActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    /*   @BindView(R.id.tabLayout)
       TabLayout tabLayout;*/
    @BindView(R.id.nal_bar1)
    NavitationLayout nal_bar1;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private String[] titles1 = new String[]{"商品", "拍品"};
    private ViewPagerAdapter viewPagerAdapter1;
    private ArrayList<Fragment> fragments1 = new ArrayList<Fragment>();
    private boolean isPrepared = false;
    private String sid;//店铺id
    /*店铺头像*/
    @BindView(R.id.shop_mCircleImageView)
    CircleImageView shop_mCircleImageView;
    /*店铺名称*/
    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;
    /*店铺标签*/
    @BindView(R.id.tv_shop_lable)
    TextView tv_shop_lable;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.iv_shop_imaege)
    RoundedImageView iv_shop_imaege;
/*
    @BindView(R.id.ll_shop)
    LinearLayout ll_shop;
*/
    /*店铺关注状态*/
    @BindView(R.id.tv_shopinfo_followstatus)
    TextView tv_shopinfo_followstatus;
    @BindView(R.id.tv_privateletter)
    TextView tv_privateletter;
    private String shopname;//店铺名称
    private boolean followstatus;//网络关注状态
    private boolean isfollow;//关注状态
    @BindView(R.id.rl_activity_shop)
    RecyclerView rl_activity_shop;
    private CouponShopAdapter couponShopAdapter;
    private List<AllCouponBuyerBean> allCouponBuyerBeanArrayList = new ArrayList<>(); // 这个是右边

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
        mImmersionBar.titleBar(R.id.toolbar).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shop;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        sid = getIntent().getStringExtra("shopinfosid");//店铺id
        fragments1.add(ShopGoodsFragment.newInstance(sid));//商品
        fragments1.add(ShopPattingFragment.newInstance(sid));//拍品
        viewPagerAdapter1 = new ViewPagerAdapter(this.getSupportFragmentManager(), fragments1);
        viewPager.setAdapter(viewPagerAdapter1);
        nal_bar1.setViewPager(this, titles1, viewPager, R.color.text_danhui_color, R.color.main_tone, 14, 14, 0, 20, true);
        /* navitationLayout.setBgLine(getActivity(), 1, R.color.colorAccent);*/
        nal_bar1.setNavLine(this, 2, R.color.main_tone, 0);
        Fetshopinfobusid();
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_activity_shop.setLayoutManager(ms);
        couponShopAdapter = new CouponShopAdapter();
        rl_activity_shop.setAdapter(couponShopAdapter);
        rl_activity_shop.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                AllCouponBuyerBean allCouponBuyerBean = couponShopAdapter.getData().get(position);
                switch (itemViewId) {
                    /*立即购买*/
                    case R.id.tv_immediately_receive:
                        getAddcouponuyeruser(allCouponBuyerBean.getCbid());
                        break;
                }
            }
        });
        //getAllcouponeuyer();
    }

    /**
     * 获取优惠券列表
     */
    private void getAddcouponuyeruser(int cbid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDCOUPONBUYERUSER)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .params("cbid", cbid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "商品列表: " + response.body().code);
                        Log.e("text", "商品列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(ShopActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(ShopActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 获取优惠券列表
     */
    private void getAllcouponeuyer() {
        OkGo.<BaseMode<List<AllCouponBuyerBean>>>post(AppApi.BASE_URL + AppApi.GETALLCOUPONBUYER)
                .tag(this)
                .params("sid", sid)
                .execute(new JsonCallback<BaseMode<List<AllCouponBuyerBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<AllCouponBuyerBean>>> response) {
                        Log.e("text", "商品列表: " + response.body().code);
                        Log.e("text", "商品列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            allCouponBuyerBeanArrayList = response.body().result;
                            couponShopAdapter.setNewData(allCouponBuyerBeanArrayList);
                        } else {
                            ToastUtils.showShort(ShopActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 获取店铺信息
     */
    private void Fetshopinfobusid() {
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid", sid)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<ShopInfoByidBean>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(ShopActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(final Response<BaseMode<ShopInfoByidBean>> response) {
                        //模拟弱
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        Log.e("text", "获取店铺信息: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            tv_shop_lable.setText(response.body().result.getShoplabel().toString());
                            followstatus = response.body().result.isFollowstatus();
                            toolbar_title.setText(response.body().result.getShopname());
                            if (followstatus == false) {
                                tv_shopinfo_followstatus.setText("+关注");
                            } else {
                                tv_shopinfo_followstatus.setText("已关注");
                            }
                            shopname = response.body().result.getShopname();
                            tv_shop_name.setText(shopname);
                            Glide.with(ShopActivity.this).load(response.body().result.getSbgimg()).fitCenter().into(iv_shop_imaege);//店铺背景
                            Glide.with(ShopActivity.this).load(response.body().result.getShopimg()).fitCenter().into(shop_mCircleImageView);//卖家头像
                        } else {
                            stopDialog();
                            ToastUtils.showShort(ShopActivity.this, response.body().msg);
                        }
                    }


                    @Override
                    public void onError(Response<BaseMode<ShopInfoByidBean>> response) {
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

    @OnClick({R.id.toolbar, R.id.tv_shopinfo_followstatus, R.id.tv_privateletter})
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.ll_shop:
                Intent intent = new Intent(this, UserShopInfoActivity.class);
                startActivity(intent);
                break;*/
            case R.id.toolbar:
                finish();
                break;
            /*关注按钮*/
            case R.id.tv_shopinfo_followstatus:
                if (AppUtile.isFastDoubleClick()) {
                } else if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    if (!NetworkUtils.isNetworkConnected(ShopActivity.this)) {
                        ToastUtils.showToast("当前无网络请链接网络");
                    } else if (AppUtile.getTicket(ShopActivity.this).equals("")) {
                        goToActivity(LoginActivity.class, "type", "1");
                    } else {
                        isfollow = followstatus;
                        UpdateShopFollow(sid);
                    }
                }
                break;
            case R.id.tv_privateletter:
                /**
                 * 这个是启动单聊界面
                 * 启动单聊界面。
                 *
                 * @param context      应用上下文。
                 * @param targetUserId 要与之聊天的用户 Id。
                 * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
                 *                     获取该值, 再手动设置为聊天界面的标题。
                 */
                RongIM.getInstance().startPrivateChat(this, sid, shopname);
                break;

        }
    }

    /**
     * 店铺关注 已关注/未关注
     */
    private void UpdateShopFollow(String sid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPFOLLOW)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .params("sid", sid)
                .params("follow", isfollow ? "0" : "1")
                .params("token", AppUtile.getTicket(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Fetshopinfobusid();
                            if (isfollow) {
                                ToastUtils.showToast("取消关注");
                            } else {
                                ToastUtils.showToast("关注成功");
                            }
                        } else {
                            ToastUtils.showShort(ShopActivity.this, response.body().msg);
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

}