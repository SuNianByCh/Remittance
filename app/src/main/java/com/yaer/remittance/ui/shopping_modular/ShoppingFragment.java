package com.yaer.remittance.ui.shopping_modular;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.CategoryBean;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.bean.selectBannerBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ShopCommodityNewProductAdapter;
import com.yaer.remittance.ui.adapter.ShoppingAdapter;
import com.yaer.remittance.ui.adapter.ShoppingclassAdapter;
import com.yaer.remittance.ui.home_modular.auctiondetails.AuctionDetailsActivity;
import com.yaer.remittance.ui.home_modular.search.ActivitySearch;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ClearEditText;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/18.
 * 商城首页
 */
public class ShoppingFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_shopping)
    RecyclerView mRv;
    @BindView(R.id.shopping_refreshLayout)
    SmartRefreshLayout shopping_refreshLayout;
    private ShoppingAdapter shoppingAdapter;
    private ShoppingclassAdapter shoppingclassAdapter;
    private List<SelectGoodsListBean> mItemList = new ArrayList<>();
    private int bannerHeight;
    private View headView;
    private List<String> mImages = new ArrayList<>();
    /*搜索框*/
    @BindView(R.id.ct_shopping)
    ClearEditText ct_shopping;
    private int page = 1;
    private int pagesize = 10;
    private RecyclerView rvclass;
    private RecyclerView rl_sjopping_commodity;
    private List<NewGoodsBean> CommodityNewProduclist = new ArrayList<>();
    private ShopCommodityNewProductAdapter shopCommodityNewProductAdapter;
    List<selectBannerBean> mBanner = new ArrayList<>();

    /***
     * 创建数据
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), mToolbar);
    }
    /***
     * 设置沉浸式标题
     */
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColorTransformEnable(false)
                .navigationBarColor(R.color.colorPrimary)
                .init();
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.shopping_fragment;
    }

    @Override
    protected void initData(Bundle arguments) {
        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        shoppingAdapter = new ShoppingAdapter();
        mRv.setAdapter(shoppingAdapter);
        showtext();
        GetgoodsList(page, pagesize);
        addHeaderView();
        GetBanner();
    }

    /***
     * 初始化视图
     */
    @Override
    protected void initView() {

    }

    /***
     * 上拉下拉
     */
    private void showtext() {
        shopping_refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                shopping_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetBanner();
                        Fetshopclassinfcaiioninfomodels();
                        GetgoodsList(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                shopping_refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetgoodsList(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 500);
            }
        });
    }

    /**
     * 获取商品列表
     *
     * @param page
     * @param pagesize
     */
    private void GetgoodsList(final int page, int pagesize) {
        OkGo.<BaseMode<List<SelectGoodsListBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSLIST)
                .tag(this)
                .params("gissoldout", "0")
                .params("gisauction", "0")
                .params("page", page)
                .params("pagesize", pagesize)
                .execute(new JsonCallback<BaseMode<List<SelectGoodsListBean>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<SelectGoodsListBean>>> response) {
                        Log.e("text", "商品列表: " + response.body().code);
                        Log.e("text", "商品列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            mItemList = response.body().result;
                            if (page == 1) {
                                shoppingAdapter.setNewData(mItemList);
                            } else if (page > 1 && mItemList != null && mItemList.size() > 0) {
                                shoppingAdapter.addData(mItemList);
                            } else {
                                shopping_refreshLayout.finishLoadMoreWithNoMoreData();
                            }
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }

    private Banner banner;

    /***
     * 设置头部视图
     */
    private void addHeaderView() {
        headView = LayoutInflater.from(mActivity).inflate(R.layout.item_banner, (ViewGroup) mRv.getParent(), false);
        banner = (Banner) headView.findViewById(R.id.shopping_banner);
        rvclass = headView.findViewById(R.id.rv_banner_classification);
        rl_sjopping_commodity = headView.findViewById(R.id.rl_sjopping_commodity);
        rvclass.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        rvclass.addItemDecoration(new SpacesItemDecoration(8));
        shoppingclassAdapter = new ShoppingclassAdapter();
        rvclass.setAdapter(shoppingclassAdapter);
        /*为你推荐*/
        LinearLayoutManager ms = new LinearLayoutManager(getActivity());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_sjopping_commodity.setLayoutManager(ms);
        shopCommodityNewProductAdapter = new ShopCommodityNewProductAdapter();
        rl_sjopping_commodity.setAdapter(shopCommodityNewProductAdapter);
        Fetshopclassinfcaiioninfomodels();//获取商品一级分类
        GetNewGoods(page, pagesize);//为你推荐
        rl_sjopping_commodity.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                NewGoodsBean newGoodsBean = shopCommodityNewProductAdapter.getData().get(position);
                switch (itemViewId) {
                    case R.id.ll_newproduct:
                        Bundle bundle=new Bundle();
                        bundle.putString("gidshopping",String.valueOf(newGoodsBean.getGid()));
                        goToActivity(CommodityDetailsActivity.class,bundle);
                        break;
                }
            }
        });

        shoppingAdapter.addHeaderView(headView);
       /* ViewGroup.LayoutParams bannerParams = banner.getLayoutParams();
        ViewGroup.LayoutParams titleBarParams = mToolbar.getLayoutParams();
        bannerHeight = bannerParams.height - titleBarParams.height - ImmersionBar.getStatusBarHeight(getActivity());*/
    }

    /**
     * 为你推荐
     */
    private void GetNewGoods(final int page, int pagesize) {
        OkGo.<BaseMode<List<NewGoodsBean>>>post(AppApi.BASE_URL + AppApi.SELECTNEWGOODS)
                .tag(this)
                .params("gisauction", "0")
                .params("page", page)
                .params("pagesize", pagesize)
                .execute(new JsonCallback<BaseMode<List<NewGoodsBean>>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<NewGoodsBean>>> response) {
                        Log.e("text", "为你推荐: " + response.body().code);
                        Log.e("text", "为你推荐: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            CommodityNewProduclist = response.body().result;
                            shopCommodityNewProductAdapter.setNewData(CommodityNewProduclist);
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }

    /**
     * 获取Banner
     */
    private void GetBanner() {
        OkGo.<BaseMode<List<selectBannerBean>>>post(AppApi.BASE_URL + AppApi.GETBANNER)
                .tag(this)
                .params("bmodel", "2")
                .execute(new JsonCallback<BaseMode<List<selectBannerBean>>>(getActivity()) {
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
                            banner.setImages(mImages)
                                    .setImageLoader(new GlideImageLoader())
                                    .setDelayTime(3000)
                                    .start();
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }

    private List<CategoryBean.ResultBean> foodDatas;

    /**
     * 获取商品分类
     */
    private void Fetshopclassinfcaiioninfomodels() {
        OkGo.<CategoryBean>post(AppApi.BASE_URL + AppApi.FETSHOPCLASSIFICATIONINFOMODELS)
                .tag(this)
                .execute(new JsonCallback<CategoryBean>(getActivity()) {
                    @Override
                    public void onSuccess(Response<CategoryBean> response) {
                        Log.e("text", "获取商品分类: " + response.getRawCall());
                        Log.e("text", "获取商品分类: " + response.body().getResult());
                        if (response.body().getCode().equals(Constant.SUEECECODE)) {
                            foodDatas = response.body().getResult();
                            shoppingclassAdapter.setNewData(foodDatas);
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().getMsg());
                        }
                    }
                });
    }

    /***
     * 监听事件
     */
    @Override
    protected void setListener() {
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalDy = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalDy += dy;
                if (totalDy <= bannerHeight) {
                    float alpha = (float) totalDy / bannerHeight;
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.main_tone), alpha));
                } else {
                    mToolbar.setBackgroundColor(ColorUtils.blendARGB(Color.TRANSPARENT
                            , ContextCompat.getColor(mActivity, R.color.main_tone), 1));
                }
            }
        });
    }

    /***
     * 点击事件
     * @param v
     */
    @OnClick({R.id.ct_shopping})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ct_shopping:
                goToActivity(ActivitySearch.class);
                break;
        }
    }
}
