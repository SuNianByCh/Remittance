package com.yaer.remittance.ui.shopping_modular.commodity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.Config;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.bean.SelectGoodsInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ShopCommodityNewProductAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.ShoppingCartActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBeanDaoUtil;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopInfoModelBean;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.GradationScrollView;
import com.yaer.remittance.view.ShowImagesDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2017/6/18.
 * 商品详情
 */
public class CommodityDetailsActivity extends BaseActivity implements GradationScrollView.ScrollViewListener, OnBannerListener {
    @BindView(R.id.scrollview)
    GradationScrollView scrollView;
    @BindView(R.id.ll_good_detail)
    RelativeLayout llTitle;
    @BindView(R.id.tv_good_detail_title_good)
    TextView tvGoodTitle;
    private int height;
    private int width;
    @BindView(R.id.rl_commodity)
    RecyclerView rl_commodity;
    private List<String> commodityItemList = new ArrayList<>();
    private ShopCommodityNewProductAdapter shopCommodityNewProductAdapter;
    @BindView(R.id.nlv_good_detial_imgs)
    RecyclerView nlvImgs;
    private CommodityImageAdapter commodityImageAdapter;
    @BindView(R.id.commodity_banner)
    Banner commodity_banner;
    private List<String> mImages = new ArrayList<>();
    @BindView(R.id.tv_commodity_secured)
    TextView secured;
    @BindView(R.id.tv_commodity_quick_refund)
    TextView quick_refund;
    /*@BindView(R.id.tv_commodity_quality_assurance)
    TextView quality_assurance;*/
    /*平台入驻*/
    @BindView(R.id.tv_commodity_platform_authentication)
    TextView platform_authentication;
    /*加入购物车*/
    @BindView(R.id.tv_good_detail_shop_cart)
    TextView add_cart;
    /*私信*/
    @BindView(R.id.tv_good_detail_shop)
    TextView tv_good_detail_shop;
    /*立即购买*/
    @BindView(R.id.tv_good_detail_buy)
    TextView detail_buy;
    /*商品名称*/
    @BindView(R.id.tv_commodity_details_name)
    TextView tv_commodity_details_name;
    /*商品价格*/
    @BindView(R.id.tv_commodity_details_money)
    TextView tv_commodity_details_money;
    /*商品店铺头像*/
    @BindView(R.id.civ_commodity_shop_portrait)
    CircleImageView civ_commodity_shop_portrait;
    /*店铺名称*/
    @BindView(R.id.tv_commodity_shop_name)
    TextView tv_commodity_shop_name;
    /*店铺标签*/
    @BindView(R.id.tv_commodity_shop_slabel)
    TextView tv_commodity_shop_slabel;
    /*店铺点赞数*/
  /*  @BindView(R.id.tv_commodity_shop_follow)
    TextView tv_commodity_shop_follow;*/
    @BindView(R.id.tv_commodity_details_number)
    TextView tv_commodity_details_number;
    @BindView(R.id.tv_product_brief)
    TextView tv_product_brief;
    private String gid;//商品id
    /*新增订单*/
    private double ototalvalue;//总价
    private String sid;//店铺id
    private String ordergid;//商品id
    private int ognumber;//库存数量
    private String uid;//用户id
    private String simg;//店铺图片
    private String sname;//店铺名称
    private String gname;//商品名称
    private String gimg;//商品图片
    private int gpostage;//	邮费
    List<String> list = new ArrayList<>();
    private String Images;
    /*查看更多评论*/
    @BindView(R.id.tv_talent_detail_open)
    TextView tv_talent_detail_open;
    /*评论总数*/
    @BindView(R.id.tv_commodity_score)
    TextView tv_commodity_score;
    /*评论列表*/
    /*@BindView(R.id.rv_commodity_evaluation)
    RecyclerView rv_commodity_evaluation;*/
    /*暂无评价*/
    @BindView(R.id.rl_notime_evaluate)
    RelativeLayout rl_notime_evaluate;
    /*显示数据*/
    @BindView(R.id.rl_commodity_enaluation)
    RelativeLayout rl_commodity_enaluation;
    /*购物车按钮*/
    @BindView(R.id.tv_go_shop_cart)
    TextView tv_go_shop_cart;
    private CommodityEvaluationAdapter commodityEvaluationAdapter;
    private List<SelectGoodsInfoBean.GoodsCommentinfoModelsBean> goodsCommentinfoModelsBeans = new ArrayList<>();
    // List<ShopCartBean> shopCartList = UserDao.getInstance().selectShop();
    private List<ShopCartBean> persons;
    @BindView(R.id.rl_details)
    RelativeLayout rl_details;
    /*评论信息*/
    @BindView(R.id.iv_comment_shop_image)
    CircleImageView iv_comment_shop_image;
    @BindView(R.id.tv_comment_shop_name)
    TextView tv_comment_shop_name;
    @BindView(R.id.sb_commodity_score)
    RatingBar sb_commodity_score;
    @BindView(R.id.tv_commodity_gtime)
    TextView tv_commodity_gtime;
    @BindView(R.id.tv_comment_shop_describe)
    TextView tv_comment_shop_describe;
    private List<NewGoodsBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 6;
    /*收藏商品*/
    @BindView(R.id.ll_good_detail_collect)
    LinearLayout ll_good_detail_collect;
    @BindView(R.id.iv_good_detai_collect_unselect)
    ImageView iv_good_detai_collect_unselect;
    /*分享商品*/
    @BindView(R.id.iv_good_detai_share)
    ImageView iv_good_detai_share;
    UMShareAPI api;

    public CommodityDetailsActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.setTitleBar(this, llTitle);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initView() {
        api = UMShareAPI.get(this);
        uid = String.valueOf(AppUtile.getUid(this));
        gid = getIntent().getStringExtra("gidshopping");//商品id
        /*根据id查询商品详情*/
        selectGoodsInfo();
        getDeviceDensity();
        /*为你推荐*/
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_commodity.setLayoutManager(ms);
        shopCommodityNewProductAdapter = new ShopCommodityNewProductAdapter();
        rl_commodity.setAdapter(shopCommodityNewProductAdapter);
        rl_commodity.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                NewGoodsBean newGoodsBean = shopCommodityNewProductAdapter.getData().get(position);
                switch (itemViewId) {
                    case R.id.ll_newproduct:
                        Bundle bundle = new Bundle();
                        bundle.putString("gidshopping", String.valueOf(newGoodsBean.getGid()));
                        goToActivity(CommodityDetailsActivity.class, bundle);
                        break;
                }
            }
        });

        /*图文详情*/
        nlvImgs.setLayoutManager(new LinearLayoutManager(this));
        commodityImageAdapter = new CommodityImageAdapter();
        nlvImgs.setAdapter(commodityImageAdapter);
        nlvImgs.setNestedScrollingEnabled(false);//禁止滑动
        initListeners();
        GetNewGoods(page, pagesize);//为你推荐
        /*查询购物车信息*/
        //persons = ShopCartBeanDaoUtil.getShopCartBeanList();
        shojiaobiao();
    }

    private ShopCartBean shopCartBean;
    private boolean shopinfofollowstatus;//获取网络关注状态
    private boolean commodityfollowstatus;//商品收藏状态
    private String followtype;//状态值
    private boolean isfollow = false;//获取本地关注状态
    private String gdesc;//详情

    /**
     * 根据id查询商品详情
     */
    private void selectGoodsInfo() {
        OkGo.<BaseMode<SelectGoodsInfoBean>>post(AppApi.BASE_URL + AppApi.SELECTGOODSINFO)
                .tag(this)
                .params("gid", gid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<SelectGoodsInfoBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<SelectGoodsInfoBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            /*商品店铺绑定数据*/
                            ototalvalue = response.body().result.getGmoney();//总价
                            sid = response.body().result.getShopInfoModel().getSid();//店铺id
                            ordergid = response.body().result.getGid();//商品id
                            ognumber = response.body().result.getGnumber();//库存数量
                            simg = response.body().result.getShopInfoModel().getSimg();//店铺头像
                            sname = response.body().result.getShopInfoModel().getSname();//店铺名称
                            gname = response.body().result.getGname();//商品名称
                            gdesc = response.body().result.getGdesc();
                            gimg = response.body().result.getGimg();//商品图片
                            gpostage = response.body().result.getGpostage();//商品邮费
                            if (!AppUtile.isNull(gname)) {
                                tvGoodTitle.setText(gname);
                            } else {
                                tvGoodTitle.setText("----");
                            }
                            /*商品详情name图片价格*/
                            tv_commodity_details_name.setText(response.body().result.getGname().toString());
                            String money = AmountUtil.priceNum(response.body().result.getGmoney());
                            tv_commodity_details_money.setText("￥" + money);
                            tv_commodity_details_number.setText("库存" + response.body().result.getGnumber() + "件");
                            tv_product_brief.setText(response.body().result.getGdesc());//商品简介
                            mImages.clear();
                            /*绑定图片*/
                            Images = response.body().result.getGimg();
                            //用逗号将字符串分开，得到字符串数组
                            String[] strs = Images.split(",");
                            //将字符串数组转换成集合list
                            list = java.util.Arrays.asList(strs);// 字符数组转list
                            //查看集合
                            for (int i = 0; i < list.size(); i++) {
                                mImages.add(list.get(i));
                            }
                            //图文详情图片
                            commodityImageAdapter.setNewData(mImages);
                            //浏览图片banner
                            commodity_banner.setImages(mImages)
                                    .setImageLoader(new GlideImageLoader())
                                    .setIndicatorGravity(BannerConfig.RIGHT)
                                    .setBannerStyle(BannerConfig.NUM_INDICATOR)
                                    .setOnBannerListener(CommodityDetailsActivity.this)
                                    .setDelayTime(5000)
                                    .start();
                            tv_commodity_shop_name.setText(response.body().result.getShopInfoModel().getSname());
                            tv_commodity_shop_slabel.setText(response.body().result.getShopInfoModel().getSlabel());
                            Glide.with(CommodityDetailsActivity.this).load(response.body().result.getShopInfoModel().getSimg()).fitCenter().into(civ_commodity_shop_portrait);//买家头像
                            /*关注店铺状态*/
                           /* shopinfofollowstatus = response.body().result.getShopInfoModel().isFollowStatus();
                            if (shopinfofollowstatus == true) {
                                tv_commodity_shop_follow.setText("已关注");
                            } else {
                                tv_commodity_shop_follow.setText("+关注");
                            }*/
                            commodityfollowstatus = response.body().result.isFollowStatus();
                            if (commodityfollowstatus == true) {
                                iv_good_detai_collect_unselect.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_collect_true));
                            } else {
                                iv_good_detai_collect_unselect.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_collect));
                            }
                            /*评论信息*/
                            goodsCommentinfoModelsBeans = response.body().result.getGoodsCommentinfoModels();
                            if (goodsCommentinfoModelsBeans.size() == 0) {
                                rl_notime_evaluate.setVisibility(View.VISIBLE);
                                rl_commodity_enaluation.setVisibility(View.GONE);
                            } else {
                                rl_notime_evaluate.setVisibility(View.GONE);
                                rl_commodity_enaluation.setVisibility(View.VISIBLE);
                                tv_comment_shop_describe.setText(goodsCommentinfoModelsBeans.get(0).getUcdesc());//评论内容
                                tv_comment_shop_name.setText(goodsCommentinfoModelsBeans.get(0).getUname());//评论名称
                                tv_commodity_gtime.setText(SystemUtil.stampToDate(goodsCommentinfoModelsBeans.get(0).getGtime()));//评论时间
                                sb_commodity_score.setRating(Float.parseFloat(goodsCommentinfoModelsBeans.get(0).getUcstars()));//评论五角星分数
                                Glide.with(CommodityDetailsActivity.this).load(goodsCommentinfoModelsBeans.get(0).getUicon()).fitCenter().into(iv_comment_shop_image);
                                tv_commodity_score.setText("(" + goodsCommentinfoModelsBeans.size() + ")");
                            }
                           /* ShopCartBeanDaoUtil.deleteShopCarAll();
                            List<ShopCartBean> shopCartBeanList = ShopCartBeanDaoUtil.getShopCartBeanList();*/
                            /*商品信息*/
                            shopCartBean = new ShopCartBean();
                            shopCartBean.setUid(String.valueOf(uid));//用户id
                            shopCartBean.setGid(response.body().result.getGid());//商品id
                            shopCartBean.setGname(response.body().result.getGname());//商品名称
                            shopCartBean.setGmoney(response.body().result.getGmoney());//商品金额
                            shopCartBean.setGdesc(response.body().result.getGdesc());//商品描述
                            shopCartBean.setGcid(response.body().result.getGcid());
                            shopCartBean.setGimg(response.body().result.getGimg());//商品图片
                            shopCartBean.setGnumber(response.body().result.getGnumber());//商品库存
                            shopCartBean.setSid(response.body().result.getSid());//店铺id
                            shopCartBean.setGpostage(String.valueOf(response.body().result.getGpostage()));//商品邮费
                            /*店铺信息*/
                            ShopInfoModelBean shopInfoModelBean = new ShopInfoModelBean();
                            shopInfoModelBean.setSid(response.body().result.getShopInfoModel().getSid());//店铺id
                            shopInfoModelBean.setSname(response.body().result.getShopInfoModel().getSname());//店铺名称
                            shopInfoModelBean.setStime(response.body().result.getShopInfoModel().getStime());//店铺时间
                            shopInfoModelBean.setSimg(response.body().result.getShopInfoModel().getSimg());//店铺头像
                            shopCartBean.setShopInfoModelBean(shopInfoModelBean);
                            /*添加足迹*/
                            addBrowsingHistory();
                        } else {
                            ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        }
                    }
                });
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
                .execute(new JsonCallback<BaseMode<List<NewGoodsBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<NewGoodsBean>>> response) {
                        Log.e("text", "为你推荐: " + response.body().code);
                        Log.e("text", "为你推荐: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Quserylist = response.body().result;
                            shopCommodityNewProductAdapter.setNewData(Quserylist);
                        } else {
                            ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /*添加足迹*/
    private void addBrowsingHistory() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDBROWSINGHISTORY)
                .tag(this)
                .params("gid", gid)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加足迹: " + response.body().code);
                        Log.e("text", "添加足迹: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            //ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        } else {
                            //ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        }
                    }
                });

    }

    @Override
    public void initData() {
    }

    @Override
    protected void setListener() {

    }

    private String goodsfollow = "";//商品关注参数
    private boolean goodsfollowtype = false;//商品关注状态

    @OnClick({R.id.iv_good_detai_back, R.id.tv_commodity_platform_authentication, R.id.tv_commodity_quick_refund, R.id.tv_commodity_secured,
            R.id.tv_good_detail_shop_cart, R.id.tv_good_detail_shop, R.id.tv_good_detail_buy, R.id.tv_talent_detail_open, R.id.tv_go_shop_cart, R.id.rl_details, R.id.ll_good_detail_collect, R.id.iv_good_detai_share})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        String uid = String.valueOf(AppUtile.getUid(this));
        switch (v.getId()) {
            case R.id.iv_good_detai_share:
                String[] arrayStr = new String[]{};// 字符数组
                arrayStr = Images.split(",");// 字符串转字符数组
                String url = arrayStr[0];
                UMImage image = new UMImage(this, url);//分享图标
                final UMWeb web = new UMWeb("http://www.paiphui.com/h5/share/goodsDetail.html?id=" + gid);
                web.setTitle(gname);//标题
                web.setThumb(image);  //缩略图
                web.setDescription("在拍品汇发现一个宝贝，速来围观~~~");//描述
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                if (share_media == SHARE_MEDIA.QQ) {
                                    new ShareAction(CommodityDetailsActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN) {
                                    new ShareAction(CommodityDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.QZONE) {
                                    new ShareAction(CommodityDetailsActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                                    new ShareAction(CommodityDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                }
                            }
                        }).open();
                break;
            /*收藏商品*/
            case R.id.ll_good_detail_collect:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                }  else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能关注自己的商品");
                } else {
                    if (commodityfollowstatus == false) {
                        //已收藏
                        goodsfollow = "1";
                    } else {
                        //取消收藏
                        goodsfollow = "0";
                    }
                    updateGoodsFollow(goodsfollow);
                }
                break;
            /*关注商家*/
         /*   case R.id.tv_commodity_shop_follow:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                   *//* if (followstatus == true) {
                        followtype = "0";
                    } else {
                        followtype = "1";
                    }*//*
                    // UpdateShopFollow(followtype, sid);
                }
                break;*/
            case R.id.tv_talent_detail_open:
                bundle.putSerializable("goodsCommentinfoModelsBeans", (Serializable) goodsCommentinfoModelsBeans);
                goToActivity(CommentDetailsActivity.class, bundle);
                break;
            case R.id.iv_good_detai_back:
                finish();
                break;
            case R.id.tv_commodity_platform_authentication:
                CommodityDialog();
                break;
         /*   case R.id.tv_commodity_quality_assurance:
                CommodityDialog();
                break;*/
            case R.id.tv_commodity_quick_refund:
                CommodityDialog();
                break;
            case R.id.tv_commodity_secured:
                CommodityDialog();
                break;
            /*进入确认订单*/
            case R.id.tv_good_detail_buy:
                bundle.putString("simg", simg);//店铺图片
                bundle.putString("sname", sname);//店铺名称
                bundle.putString("gname", gname);//商品名称
                bundle.putString("gimg", gimg);//商品图片
                bundle.putDouble("ototalvalue", ototalvalue);//商品价格
                bundle.putInt("gpostage", gpostage);//商品邮费
                bundle.putString("sid", sid);//店铺id
                bundle.putString("gid", ordergid);//商品id
                bundle.putInt("ognumber", ognumber);//商品库存
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else if (ognumber == 0) {
                    ToastUtils.showToast("库存不足");
                } else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能购买自己的商品");
                } else {
                    goToActivity(ConfirmationOrderActivity.class, bundle);
                }
                break;
            /*添加购物车*/
            case R.id.tv_good_detail_shop_cart:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    if (ognumber == 0) {
                        ToastUtils.showToast("库存不足");
                    } else if (uid.equals(sid)) {
                        ToastUtils.showToast("自己的商品不能加入购物车");
                    } else {
                        AddShopCart();
                    }
                }
                break;
            /*跳转购物车*/
            case R.id.tv_go_shop_cart:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    goToActivity(ShoppingCartActivity.class);
                }

                break;
            /*跳转到店铺*/
            case R.id.rl_details:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    Intent intent = new Intent(CommodityDetailsActivity.this, ShopActivity.class);
                    intent.putExtra("shopinfosid", sid);
                    startActivity(intent);
                }
                break;
            /*联系商家*/
            case R.id.tv_good_detail_shop:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能和自己的聊天");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    /**
                     * 这个是启动单聊界面
                     * 启动单聊界面。
                     *
                     * @param context      应用上下文。
                     * @param targetUserId 要与之聊天的用户 Id。
                     * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
                     *                     获取该值, 再手动设置为聊天界面的标题。
                     */
                    RongIM.getInstance().startPrivateChat(this, sid, sname);
                }
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            //   Toast.makeText(CommodityDetailsActivity.this, " 分享回调中", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            //  Toast.makeText(CommodityDetailsActivity.this, " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            //   Toast.makeText(CommodityDetailsActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            //Toast.makeText(CommodityDetailsActivity.this, " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        Log.d("result", "onActivityResult");
    }

    /**
     * 商品关注 已关注/未关注
     */
    private void updateGoodsFollow(String goodsfollow) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDSTEGOODSFOLLOW)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .params("gid", gid)
                .params("follow", goodsfollow)
                .params("token", AppUtile.getTicket(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "商品收藏: " + response.body().code);
                        Log.e("text", "商品收藏: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                         /*   if (commodityfollowstatus == true) {
                                iv_good_detai_collect_unselect.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_collect_true));
                            } else {
                                iv_good_detai_collect_unselect.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_collect));
                            }*/
                            selectGoodsInfo();
                        } else {
                            ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 店铺关注 已关注/未关注
     */
   /* private void UpdateShopFollow(String follow, String sid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATESHOPFOLLOW)
                .tag(this)
                .params("uid", uid)
                .params("sid", sid)
                .params("follow", follow)
                .params("token", AppUtile.getTicket(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (isfollow == false) {
                                isfollow = true;
                                tv_commodity_shop_follow.setText("+关注");
                                ToastUtils.showToast("取消关注");
                            } else {
                                isfollow = false;
                                tv_commodity_shop_follow.setText("已关注");
                                ToastUtils.showToast("关注成功");
                            }
                            selectGoodsInfo();
                        } else {
                            ToastUtils.showShort(CommodityDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }*/

    /**
     * 添加购物车
     */
    private void AddShopCart() {
        if (shopCartBean == null) {
            ToastUtils.showToast("添加购物车失败");
            return;
        } else {
            ShopCartBeanDaoUtil.saveShopCardBean(shopCartBean);
            //  ShopCartBeanDaoUtil.updateShopCardBean(shopCartBean.getGid(),1);
           /* String shopCartBeanListGosn = ShopCartBeanDaoUtil.getShopCartBeanListGosn();
            List<ShopInfoModelBean> shopInfoModelBeanList = ShopCartBeanDaoUtil.getShopInfoModelBeanList();
            String shopInfoModelBeanListJson = ShopCartBeanDaoUtil.getShopInfoModelBeanListJson();
            String shopAndGoodsAllGson = ShopCartBeanDaoUtil.getShopAndGoodsAllGson();*/
            ToastUtils.showToast("添加购物车成功");
        }
        shojiaobiao();
    }

    public void shojiaobiao() {
        final Badge badge = new QBadgeView(CommodityDetailsActivity.this).bindTarget(tv_go_shop_cart);
        // List<ShopInfoModelBean> shopInfoModelBeanList = ShopCartBeanDaoUtil.getShopInfoModelBeanList();
        List<ShopCartBean> shopCartBeanList = ShopCartBeanDaoUtil.getShopnList();
        if (shopCartBeanList.size() <= 0) {
            badge.hide(true);
        } else if (shopCartBeanList != null) {
            badge.setBadgeText("" + shopCartBeanList.size());
        } else {
            badge.hide(true);
        }

        //shopCartBeans = ShopCartDao.selectAll();//查询全部商品
       /* if (shopCartBeans.size() <= 0) {
            badge.hide(true);
        } else {
            //显示到这里了
            badge.setBadgeText("" + shopCartBeans.size());
        }*/
    }

    public void CommodityDialog() {
        final CommodityDetailsDialog commodityDetailsDialog = new CommodityDetailsDialog(this);
        commodityDetailsDialog.show();
        commodityDetailsDialog.setClicklistener(new CommodityDetailsDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {
                commodityDetailsDialog.dismiss();
            }

            @Override
            public void doCancel() {
                commodityDetailsDialog.dismiss();
            }
        });
    }

    public void CommodityAddCartDialog() {
        final CommodityAddCartDialog commodityAddCartDialog = new CommodityAddCartDialog(this);
        commodityAddCartDialog.show();
        commodityAddCartDialog.setClicklistener(new CommodityAddCartDialog.ClickListenerInterface() {
            @Override
            public void doConfirm() {

            }

            @Override
            public void doCancel() {

            }
        });
    }


    private void initListeners() {
        ViewTreeObserver vto = commodity_banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = commodity_banner.getHeight();
                scrollView.setScrollViewListener((GradationScrollView.ScrollViewListener) CommodityDetailsActivity.this);
            }
        });
    }

    /**
     * 滑动监听
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y,
                                int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
            llTitle.setBackgroundColor(Color.argb((int) 0, 255, 255, 255));
            tvGoodTitle.setTextColor(Color.argb((int) 0, 225, 225, 225));
        } else if (y > 0) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            /* float scale = (float) y / height * 2;
             *//* && y <= height*//*
            float alpha = (225 * y);*/
            /* tvGoodTitle.setTextColor(Color.argb((int) 225, 1, 24, 28));*/
            tvGoodTitle.setTextColor(this.getResources().getColor(R.color.text_1_color));
            llTitle.setBackgroundColor(this.getResources().getColor(R.color.main_tone));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    /**
     * 获取当前设备的屏幕密度等基本参数
     */
    protected void getDeviceDensity() {
       /* DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);*/
        /* WindowManager.LayoutParams layoutParams = getWindow().getAttributes();*/
        /*layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);*/
        Config.EXACT_SCREEN_HEIGHT = ViewGroup.LayoutParams.MATCH_PARENT;
        Config.EXACT_SCREEN_WIDTH = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public void OnBannerClick(int position) {
        new ShowImagesDialog(this, mImages).show();
        //Toast.makeText(getApplicationContext(), "你点击了：" + position, Toast.LENGTH_SHORT).show();
    }
}
