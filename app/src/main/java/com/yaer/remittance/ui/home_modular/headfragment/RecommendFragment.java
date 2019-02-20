package com.yaer.remittance.ui.home_modular.headfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ADEnity;
import com.yaer.remittance.bean.GetMainBean;
import com.yaer.remittance.bean.selectBannerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.BusinessAdapter;
import com.yaer.remittance.ui.adapter.HomeShoppingAdapter;
import com.yaer.remittance.ui.adapter.NewCommendProductAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.home_modular.auctionspecial.AuctionSpecialActivity;
import com.yaer.remittance.ui.home_modular.elementbeat.ElementBeatActivity;
import com.yaer.remittance.ui.home_modular.expertssay.ExpertsSayActivity;
import com.yaer.remittance.ui.home_modular.leakhunting.LeakHuntingActivity;
import com.yaer.remittance.ui.login_modular.AgreementActivity;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 推荐页面
 * Created by ywl on 2016/6/27.
 */
public class RecommendFragment extends BaseLazyFragment {
    private Context context;
    /*banner*/
    @BindView(R.id.recommend_banner)
    Banner recommend_banner;
    List<selectBannerBean> mBanner = new ArrayList<>();
    List<String> mImages = new ArrayList<>();
    /*为你优选*/
    @BindView(R.id.rv_optimization)
    RecyclerView rv_home;
    /*新品推荐*/
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    /*推荐商家*/
    @BindView(R.id.rv_business)
    RecyclerView rv_business;
    /*0元拍*/
    @BindView(R.id.ll_element)
    LinearLayout ll_element;
    /*专家说*/
    @BindView(R.id.ll_experts_say)
    LinearLayout ll_experts_say;
    /*拍卖专场*/
    @BindView(R.id.ll_auction_special)
    LinearLayout ll_auction_special;
    /*捡漏*/
    @BindView(R.id.ll_leak_hunting)
    LinearLayout ll_leak_hunting;
    /*广告滚动文字*/
  /*  @BindView(R.id.textad)
    TextViewAd textad;*/
    @BindView(R.id.refreshLayout_recommend)
    SmartRefreshLayout refreshLayout_recommend;
    private List<ADEnity> mList = new ArrayList<>();
    private HomeShoppingAdapter homeShoppingAdapter;//为你优选
    private NewCommendProductAdapter newCommendProductAdapter;//拍品推荐
    private BusinessAdapter businessAdapter;//推荐商家
    /*为你优选*/
    private List<GetMainBean.ChoicegoodsBean> choicegoodsBeans = new ArrayList<>();
    /*拍品推荐*/
    private List<GetMainBean.RecommendgoodsBean> recommendgoodsBeans = new ArrayList<>();
    /*商家*/
    private List<GetMainBean.ShopInfoModelBean> shopInfoModelBeans = new ArrayList<>();

    /**
     * dialog
     */
    /* private LoadingDialog2 photoDiloag;*/

    /* private int uid;
     private String token;*/
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout_recommend.autoRefresh(500);
    }

    @Override
    protected void initView() {
        /*为你优选*/
        rv_home.setLayoutManager(new GridLayoutManager(mActivity, 2));
        homeShoppingAdapter = new HomeShoppingAdapter();
        rv_home.setAdapter(homeShoppingAdapter);
        /*推荐新品*/
        LinearLayoutManager ms = new LinearLayoutManager(mActivity);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(ms);
        newCommendProductAdapter = new NewCommendProductAdapter();
        recycler_view.setAdapter(newCommendProductAdapter);
        /*推荐商家*/
        rv_business.setLayoutManager(new LinearLayoutManager(mActivity));
        businessAdapter = new BusinessAdapter();
        rv_business.setAdapter(businessAdapter);
        final Bundle bundle = new Bundle();
        /*推荐商家*/
        rv_business.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                GetMainBean.ShopInfoModelBean shopInfoModelBean = businessAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入店铺首页*/
                    case R.id.iv_business_image:
                       if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("shopinfosid", String.valueOf(shopInfoModelBean.getSid()));
                            goToActivity(ShopActivity.class, bundle);
                        }
                        bundle.putString("shopinfosid", String.valueOf(shopInfoModelBean.getSid()));
                        goToActivity(ShopActivity.class, bundle);
                        break;
                }
            }
        });
        /*为你优选*/
        rv_home.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                GetMainBean.ChoicegoodsBean choicegoodsBean = homeShoppingAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_new_comment_product:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(choicegoodsBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        /*推荐新品*/
        recycler_view.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                GetMainBean.RecommendgoodsBean recommendgoodsBean = newCommendProductAdapter.getData().get(position);
                switch (itemViewId) {
                    /*进入拍品详情*/
                    case R.id.ll_new_comment_product:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            bundle.putString("gidshopping", String.valueOf(recommendgoodsBean.getGid()));
                            goToActivity(AuctionDetailsActivity.class, bundle);
                        }
                        break;
                }
            }
        });
        //获取banner
        GetBanner();
        getMainData();//首页聚合
        showtext();
        showRefresh();
    }

    private void showRefresh() {
        refreshLayout_recommend.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshLayout_recommend.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        GetBanner();
                        getMainData();
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 500);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshLayout_recommend.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }


    private boolean isfollow;

    private void showtext() {//下去你用一个android 7.0以上的模拟器，百度啊
      /*  for (int i = 0; i < 4; i++) {
            ADEnity adEnity = new ADEnity("鉴赏：" + i, "古代工艺中的葫芦" + i, "http://www.baidu.com" + i);
            mList.add(adEnity);
        }
        textad.setmTexts(mList);
        textad.setFrontColor(Color.BLACK);嗯呐
        textad.setBackColor(Color.BLACK);
        textad.setmDuration(500);
        textad.setmInterval(500);
        textad.setOnClickLitener(new TextViewAd.onClickLitener() {
            @Override
            public void onClick(String mUrl) {
                ToastUtils.showToast("点击了" + mUrl);
                goToActivity(ActivityAreaActivity.class);
            }
        });*/
        rv_business.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                int itemViewId = view.getId();
                switch (itemViewId) {
                    case R.id.tv_sfans:
                        if (!NetworkUtils.isNetworkConnected(mActivity)) {
                            ToastUtils.showToast("当前无网络请链接网络");
                        } else if (AppUtile.getTicket(mActivity).equals("")) {
                            goToActivity(LoginActivity.class, "type", "1");
                        } else {
                            isfollow = businessAdapter.getData().get(position).isFollowStatus();
                            String sid = String.valueOf(businessAdapter.getData().get(position).getSid());
                            UpdateShopFollow(position, sid);
                        }
                        break;
                }
            }
        });
    }

    /**
     * 店铺关注 已关注/未关注
     */
    private void UpdateShopFollow(final int postion, String sid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPFOLLOW)
                .tag(this)
                .params("uid", AppUtile.getUid(mActivity))
                .params("sid", sid)
                .params("follow", isfollow ? "0" : "1")
                .params("token", AppUtile.getTicket(mActivity))
                .execute(new JsonCallback<BaseMode>(mActivity) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                       /* photoDiloag = new LoadingDialog2(getActivity(), "加载中...");
                        photoDiloag.show();*/
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        GetMainBean.ShopInfoModelBean item = businessAdapter.getItem(postion);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (item != null) {
                                item.setFollowStatus(!isfollow);
                                businessAdapter.notifyItemChanged(postion);
                            }
                            if (isfollow) {
                                ToastUtils.showToast("取消关注");
                            } else {
                                ToastUtils.showToast("关注成功");
                            }
                            //stopDialog();
                            // getMainData();
                        } else {
                            ToastUtils.showShort(mActivity, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        //stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        // stopDialog();
                    }
                });
    }

    /**
     * 获取Banner
     */
    private void GetBanner() {
        OkGo.<BaseMode<List<selectBannerBean>>>post(AppApi.BASE_URL + AppApi.GETBANNER)
                .tag(this)
                .params("bmodel", "1")
                .execute(new JsonCallback<BaseMode<List<selectBannerBean>>>(mActivity) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<selectBannerBean>>> response) {
                        Log.e("text", "获取Banner: " + response.body().code);
                        Log.e("text", "获取Banner: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            mBanner = response.body().result;
                            mImages.clear();
                            for (int i = 0; i < mBanner.size(); i++) {
                                String image = mBanner.get(i).getBimg();
                                mImages.add(image);
                            }
                            recommend_banner.setImages(mImages)
                                    .setImageLoader(new GlideImageLoader())
                                    .setDelayTime(3000)
                                    .start();
                            recommend_banner.setOnBannerClickListener(new OnBannerClickListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    //ToastUtils.showToast("点击了"+position);
                                    /*Bundle bundle = new Bundle();
                                    bundle.putString("agreementtitle", mBanner.get(position).getBname());
                                    bundle.putString("agreementurl", mBanner.get(position).getBurl());
                                    goToActivity(AgreementActivity.class, bundle);*/
                                }
                            });
                        } else {
                            ToastUtils.showShort(mActivity, response.body().msg);
                        }
                    }
                });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_element, R.id.ll_experts_say, R.id.ll_auction_special, R.id.ll_leak_hunting})
    public void onClick(View v) {
        switch (v.getId()) {
            /*0元拍*/
            case R.id.ll_element:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(mActivity).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    goToActivity(ElementBeatActivity.class);
                }
                break;
            /*专家说*/
            case R.id.ll_experts_say:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(mActivity).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    goToActivity(ExpertsSayActivity.class);
                }
                break;
            /*拍卖专场*/
            case R.id.ll_auction_special:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(mActivity).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    goToActivity(AuctionSpecialActivity.class);
                }
                break;
            /*捡漏*/
            case R.id.ll_leak_hunting:
                if (!NetworkUtils.isNetworkConnected(mActivity)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(mActivity).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    goToActivity(LeakHuntingActivity.class);
                }
                break;
        }
    }

    /**
     * dialog 隐藏
     */
   /* private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }else{
            photoDiloag.dismiss();
        }
    }*/
    public void getMainData() {
        if (!NetworkUtils.isNetworkConnected(mActivity)) {
            return;
        } else {
            OkGo.<BaseMode<GetMainBean>>post(AppApi.BASE_URL + AppApi.GETMAINDATA)
                    .tag(this)
                    .params("uid", AppUtile.getUid(mActivity))
                    .execute(new JsonCallback<BaseMode<GetMainBean>>(mActivity) {
                        @Override
                        public void onStart(Request<BaseMode<GetMainBean>, ? extends Request> request) {
                            super.onStart(request);
                           /* photoDiloag = new LoadingDialog2(mActivity, "加载中");
                            photoDiloag.show();*/
                        }

                        @Override
                        public void onSuccess(Response<BaseMode<GetMainBean>> response) {
                            Log.e("text", "获取首页聚合: " + response.body().code);
                            Log.e("text", "获取首页聚合: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                choicegoodsBeans = response.body().result.getChoicegoods();//为你优选
                                recommendgoodsBeans = response.body().result.getRecommendgoods();//拍品
                                shopInfoModelBeans = response.body().result.getShopInfoModel();//店铺
                                businessAdapter.setNewData(shopInfoModelBeans);
                                newCommendProductAdapter.setNewData(recommendgoodsBeans);
                                homeShoppingAdapter.setNewData(choicegoodsBeans);
                            } else {
                            }
                        }

                        @Override
                        public void onError(Response<BaseMode<GetMainBean>> response) {
                            super.onError(response);
                            // stopDialog();
                            // ToastUtils.showShort(getActivity(), "网络连接错误");
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            //stopDialog();
                            //LoadingDialog.hide();
                        }
                    });
        }
    }
}
