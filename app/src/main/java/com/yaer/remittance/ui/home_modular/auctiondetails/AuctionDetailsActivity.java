package com.yaer.remittance.ui.home_modular.auctiondetails;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.util.Util;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.gyf.barlibrary.ImmersionBar;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
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
import com.yaer.remittance.bean.DefaultAddressBean;
import com.yaer.remittance.bean.GetBiddinListBean;
import com.yaer.remittance.bean.NewGoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ShopCommodityNewProductAdapter;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityAddCartDialog;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityImageAdapter;
import com.yaer.remittance.ui.shopping_modular.commodity.ConfirmationOrderActivity;
import com.yaer.remittance.ui.shopping_modular.commodity.OfferRecordAdapter;
import com.yaer.remittance.ui.shopping_modular.shop.ShopActivity;
import com.yaer.remittance.ui.user_modular.setup.AddedReceicingAddress;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoader;
import com.yaer.remittance.utils.GlideLoadUtils;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.GradationScrollView;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.ShowImagesDialog;
import com.yaer.remittance.view.UIAlertView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.QBadgeView;

/**
 * 拍卖详情首页
 * Created by ywl on 2018/11/1.
 */
public class AuctionDetailsActivity extends BaseActivity implements GradationScrollView.ScrollViewListener, OnBannerListener, IUnReadMessageObserver {
    @BindView(R.id.scrollview)
    GradationScrollView scrollView;
    @BindView(R.id.ll_good_detail)
    RelativeLayout llTitle;
    /*商品详情*/
    TextView tvGoodTitle;
    /*拍卖商品banner*/
    @BindView(R.id.auction_banner)
    Banner auction_banner;
    private String gid;
    /*展示banner集合*/
    private List<String> mImages = new ArrayList<>();
    List<String> list = new ArrayList<>();
    private String Images;
    /*进入店铺*/
    @BindView(R.id.tv_go_detail_shop)
    TextView tv_go_detail_shop;
    /*私信*/
    @BindView(R.id.tv_good_detail_message)
    TextView tv_good_detail_message;
    /*拍品名称*/
    TextView tv_auction_details_name;
    /*当前价*/
    TextView tv_auction_details_money;
    /*起拍价*/
    TextView tv_start_price;
    /*加价幅度*/
    TextView tv_add_price;
    /*保证金*/
   /* @BindView(R.id.tv_auction_bond)
    TextView tv_auction_bond;*/
    /*拍品描述*/
    TextView tv_auction_details;
    /*图文列表*/
    @BindView(R.id.nlv_auction_detial_imgs)
    RecyclerView nlv_auction_detial_imgs;
    /*为你推荐*/
    @BindView(R.id.rl_commodity_auction)
    RecyclerView rl_commodity_auction;
    private CommodityImageAdapter commodityImageAdapter;
    /*图文详情*/
    TextView tv_auction_product_brief;
    /*拍品店铺图片*/
    CircleImageView civ_auction_details_iamge;
    /*拍品店铺名称*/
    TextView tv_auction_shangpuname;
    /*返回按钮*/
    @BindView(R.id.iv_good_detai_back)
    ImageView iv_good_detai_back;
    /*结束时间和起始时间*/
    TextView tv_auction_details_time;
    /*拍卖出价按钮*/
    TextView tv_good_detail_buy;
    /*店铺标签*/
    TextView tv_auction_slabel;
    /*原价购价格*/
    TextView tv_good_detail_money;
    /*原价购按钮*/
    LinearLayout ll_purchase_original_price;
    private int height;
    @BindView(R.id.rv_offer_record)
    RecyclerView rv_offer_record;
    /*进入店铺*/
    @BindView(R.id.rl_shop_home)
    RelativeLayout rl_shop_home;
    /*拍卖规则*/
    @BindView(R.id.tv_auction_rule)
    TextView tv_auction_rule;
    private OfferRecordAdapter offerRecordAdapter;
    private List<GetBiddinListBean> getBiddinListBeans = new ArrayList<>();
    /*    @BindView(R.id.sucdtime)
        SnapUpCountDownTimerView sucdTime;*/
    /*查看更多出价记录*/
    TextView tv_view_records;
    /*出价次数*/
    TextView tv_biddingnumber;
    /*拍卖规则*/
    @BindView(R.id.rl_auction_rules)
    RelativeLayout rl_auction_rules;
    private String sid;//店铺id
    private String sname;//店铺名称
    /*拍品出价数量*/
    @BindView(R.id.tv_bin_record_count)
    TextView tv_bin_record_count;
    /*当前显示暂无出价记录*/
    RelativeLayout rl_auction_evaluate;
    /*显示出价记录*/
    RelativeLayout rl_commodity_enaluation;
    /*拍品详情店铺关注*/
  /*  @BindView(R.id.tv_auction_follow)
    TextView tv_auction_follow;*/
    private String aid = "";
    private String Amountmoney;
    private ShopCommodityNewProductAdapter shopCommodityNewProductAdapter;
    private List<NewGoodsBean> Quserylist = new ArrayList<>();
    private int page = 1;
    private int pagesize = 6;
    @BindView(R.id.iv_good_detai_share)
    ImageView iv_good_detai_share;
    UMShareAPI api;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.setTitleBar(this, llTitle);
    }

    private final Handler handler = new Handler();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction_detauls_home;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDefaultaddress();//查询收货地址
    }

    @Override
    public void initView() {
        uid = String.valueOf(AppUtile.getUid(this));
        tv_view_records = findViewById(R.id.tv_view_records);
        rl_commodity_enaluation = findViewById(R.id.rl_commodity_enaluation);
        rl_auction_evaluate = findViewById(R.id.rl_auction_evaluate);
        /*商品详情*/
        tvGoodTitle = findViewById(R.id.tv_good_detail_title_good);
        /*拍品名称*/
        tv_auction_details_name = findViewById(R.id.tv_auction_details_name);
        /*原价购价格*/
        tv_good_detail_money = findViewById(R.id.tv_good_detail_money);
        /*出价次数*/
        tv_biddingnumber = findViewById(R.id.tv_biddingnumber);
        /*原价购按钮*/
        ll_purchase_original_price = findViewById(R.id.ll_purchase_original_price);
        /*当前价*/
        tv_auction_details_money = findViewById(R.id.tv_auction_details_money);
        /*起拍价*/
        tv_start_price = findViewById(R.id.tv_start_price);
        /*加价幅度*/
        tv_add_price = findViewById(R.id.tv_add_price);
        /*结束时间和起始时间*/
        tv_auction_details_time = findViewById(R.id.tv_auction_details_time);
        /*拍卖出价按钮*/
        tv_good_detail_buy = findViewById(R.id.tv_good_detail_buy);
        /*店铺标签*/
        tv_auction_slabel = findViewById(R.id.tv_auction_slabel);
        /*拍品描述*/
        tv_auction_details = findViewById(R.id.tv_auction_details);
        /*图文详情*/
        tv_auction_product_brief = findViewById(R.id.tv_auction_product_brief);
        /*拍品店铺名称*/
        tv_auction_shangpuname = findViewById(R.id.tv_auction_shangpuname);
        /*拍品店铺图片*/
        civ_auction_details_iamge = findViewById(R.id.civ_auction_details_iamge);

        api = UMShareAPI.get(this);
        gid = getIntent().getStringExtra("gidshopping");//拍品id
        selectGoodsInfo();//查询拍品详情
        getBalace();//查询余额
        getDeviceDensity();
        getBiddinList();//查询出价记录
        /*出价列表*/
        rv_offer_record.setLayoutManager(new LinearLayoutManager(this));
        offerRecordAdapter = new OfferRecordAdapter();
        rv_offer_record.setAdapter(offerRecordAdapter);
        rv_offer_record.setNestedScrollingEnabled(false);//禁止滑动
        /*推荐新品*/
        LinearLayoutManager ms = new LinearLayoutManager(this);
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);
        rl_commodity_auction.setLayoutManager(ms);
        shopCommodityNewProductAdapter = new ShopCommodityNewProductAdapter();
        rl_commodity_auction.setAdapter(shopCommodityNewProductAdapter);
        rl_commodity_auction.addOnItemTouchListener(new OnItemChildClickListener() {
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
                        goToActivity(AuctionDetailsActivity.class, bundle);
                        break;
                }
            }
        });
        /*图文详情*/
        nlv_auction_detial_imgs.setLayoutManager(new GridLayoutManager(this, 1));
        commodityImageAdapter = new CommodityImageAdapter();
        nlv_auction_detial_imgs.setNestedScrollingEnabled(false);
        commodityImageAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        nlv_auction_detial_imgs.setAdapter(commodityImageAdapter);
        commodityImageAdapter.setPreLoadNumber(1);
        GetNewGoods(page, pagesize);//为你推荐
        initListeners();
        getJoingroup();
        Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.GROUP
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
    }

    public void getJoingroup() {
        //这个是加入群组的方法，我只有加入群组以后才可以去进入到聊天界面，实例化用户信息没有问题了
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.JOINGROUP)
                .tag(this)
                .params("gid", gid)
                .params("uid", AppUtile.getUid(this))
                .params("gname", gname)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "连接融云成功" + response.body().result);
                        Log.e("test", "连接融云成功" + response.body().code);
                        if (AppUtile.isEmptyNull(response.body().result)) {
                        } else {
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                Log.e("test", "连接融云成功");
                                //  RongIM.getInstance().startGroupChat(AuctionDetailsActivity.this, gid, gname);
                            } else {
                                ToastUtils.showToast(response.body().msg);
                            }
                        }
                    }
                });
    }

    /**
     * 查询默认收货地址*
     */
    public void getDefaultaddress() {
        OkGo.<BaseMode<DefaultAddressBean>>post(AppApi.BASE_URL + AppApi.GETDEFAULTADDRESS)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<DefaultAddressBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<DefaultAddressBean>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            aid = response.body().result.getAid();
                        } else if (response.body().code.equals(Constant.TWOZEROTWO)) {

                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    //当前价
    private String money;
    //输入框加价幅度
    private String jjmoney;
    //起拍价
    private String startmoney;
    // 加价幅度
    private String addmoney;
    // 保证金
    private String bondmoney;
    /*新增订单*/
    private double ototalvalue;//总价
    private double Glatestbid;//当前价
    private String ordergid;//商品id
    private int ognumber;//库存数量
    private String uid;//用户id
    private String simg;//店铺图片
    private String gname;//商品名称
    private String gimg;//商品图片
    private boolean shopfollow;//网络关注状态
    private boolean isfollow;//关注状态
    private String gdesc;//描述
    private String endTime;//拍卖结束时间
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;
    private int isautobidding;//当前委托出价状态

    /**
     * 根据id查询拍品详情
     *
     * @param
     */
    private void selectGoodsInfo() {
        OkGo.<BaseMode<selectGoodsInfoAutionBean>>post(AppApi.BASE_URL + AppApi.SELECTGOODSINFO)
                .tag(this)
                .params("gid", gid)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<selectGoodsInfoAutionBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<selectGoodsInfoAutionBean>> response) {
                        Log.e("text", "根据id查询拍品详情: " + response.body().code);
                        Log.e("text", "根据id查询拍品详情: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            /*商品店铺绑定数据*/
                            String shopnewmoney = response.body().result.getGmoney();
                            /*限制如果为空的话就让他显示0*/
                            if (shopnewmoney.equals("0")) {
                                ll_purchase_original_price.setBackgroundResource(R.color.text_3_color);//setVisibility(View.GONE);
                                ll_purchase_original_price.setClickable(false);
                                shopnewmoney = "0";
                            } else {
                                ll_purchase_original_price.setBackgroundResource(R.color.main_tone);
                                ll_purchase_original_price.setClickable(true);
                                tv_good_detail_money.setText("￥" + AmountUtil.priceNum(Double.parseDouble(shopnewmoney)));//原价购
                            }
                            isautobidding = response.body().result.getIsautobidding();//当前委托出价状态
                            ototalvalue = Double.parseDouble(shopnewmoney);//当前价格
                            sid = String.valueOf(response.body().result.getShopInfoModel().getSid());//店铺id
                            ordergid = response.body().result.getGid();//商品id
                            ognumber = response.body().result.getGnumber();//库存数量
                            simg = response.body().result.getShopInfoModel().getSimg();//店铺头像
                            gname = response.body().result.getGname();//商品名称
                            gimg = response.body().result.getGimg();//商品图片
                            gdesc = response.body().result.getGdesc();//商品描述

                            tvGoodTitle.setText(gname);
                            tv_auction_details_name.setText(response.body().result.getGname());
                            //sid = String.valueOf(response.body().result.getShopInfoModel().getSid());//店铺id
                            sname = response.body().result.getShopInfoModel().getSname();//店铺名称
                            Glatestbid = Double.parseDouble(response.body().result.getGlatestbid());//出价价格
                            money = AmountUtil.priceNum(Double.parseDouble(response.body().result.getGlatestbid()));//带后两位的出价价格
                            if (!AppUtile.isEmptyNull(response.body().result.getGaddprice())) {
                                jjmoney = AmountUtil.priceNum(Double.parseDouble(response.body().result.getGaddprice()));//带后两位的加价价格
                            }
                            startmoney = AmountUtil.priceNum(Double.parseDouble(response.body().result.getGstartingprice()));//带后两位的起拍价格
                            addmoney = AmountUtil.priceNum(Double.parseDouble(response.body().result.getGaddprice()));//带后两位的加价价格
                            tv_biddingnumber.setText(String.valueOf(response.body().result.getBiddingnumber()));//出价次数
                            ///startmoney判断当前
                            if (Glatestbid >= ototalvalue) {
                                ll_purchase_original_price.setBackgroundResource(R.color.text_3_color);//setVisibility(View.GONE);
                                ll_purchase_original_price.setClickable(false);
                            } else {
                                ll_purchase_original_price.setBackgroundResource(R.color.main_tone);
                                ll_purchase_original_price.setClickable(true);
                            }
                           /* shopfollow = response.body().result.isFollowStatus();
                            if (shopfollow == false) {
                                tv_auction_follow.setText("+关注");
                            } else {
                                tv_auction_follow.setText("已关注");
                            }*/
                            // bondmoney = AmountUtil.priceNum(Double.parseDouble(response.body().result.getGcollateral()));//带后两位的加价价格
                            /*doublemoney = response.body().result.getGlatestbid();//double类型的出价价格
                            doublejjmoney =response.body().result.getGaddprice();//double类型的加价价格*/

                            tv_auction_details_money.setText("￥" + money);
                            tv_start_price.setText("￥" + startmoney);
                            tv_add_price.setText("￥" + addmoney);
                            // tv_auction_bond.setText("￥" + bondmoney);
                            String starttime = SystemUtil.stampAuctionDetails(Long.parseLong(response.body().result.getGauctiontime()));//开始时间
                            //endTime = SystemUtil.stampAuctionDetails(Long.parseLong(response.body().result.getGstoptime()));//结束时间
                            String isendTime = SystemUtil.stampAuctionDetails(Long.parseLong(response.body().result.getGstoptime()));//结束时间判断
                            String time = SystemUtil.stampToDatemm(System.currentTimeMillis());//系统时间
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//年-月-日 时-分
                            Date date1 = null;//系统时间
                            Date date2 = null;//结 束时间
                            try {
                                date1 = dateFormat.parse(time.toString());
                                date2 = dateFormat.parse(SystemUtil.stampToDate(response.body().result.getGstoptime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
                            if (date1.getTime() >= date2.getTime()) {
                                tv_auction_details_time.setText("拍卖结束");
                                ll_purchase_original_price.setBackgroundResource(R.color.text_3_color);//setVisibility(View.GONE);
                                ll_purchase_original_price.setClickable(false);
                                tv_good_detail_buy.setBackgroundResource(R.color.text_3_color);
                                tv_good_detail_buy.setClickable(false);
                            } else {
                                ll_purchase_original_price.setBackgroundResource(R.color.main_tone);
                                ll_purchase_original_price.setClickable(true);
                                tv_good_detail_buy.setBackgroundResource(R.color.leak_red);
                                tv_good_detail_buy.setClickable(true);
                                tv_auction_details_time.setText(starttime + "至" + isendTime);
                            }
                            tv_auction_slabel.setText(response.body().result.getShopInfoModel().getSlabel());
                            tv_auction_details.setText(response.body().result.getGdesc());
                            tv_auction_product_brief.setText(response.body().result.getGdesc());
                            //GlideLoadUtils.getInstance().glideLoad(AuctionDetailsActivity.this, response.body().result.getShopInfoModel().getSimg(), civ_auction_details_iamge);
                            Glide.with(AuctionDetailsActivity.this).load(response.body().result.getShopInfoModel().getSimg()).fitCenter().into(civ_auction_details_iamge);//商品图片
                            tv_auction_shangpuname.setText(response.body().result.getShopInfoModel().getSname());
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
                            commodityImageAdapter.setNewData(mImages);
                            auction_banner.setImages(mImages)
                                    .setImageLoader(new GlideImageLoader())
                                    .setIndicatorGravity(BannerConfig.RIGHT)
                                    .setBannerStyle(BannerConfig.NUM_INDICATOR)
                                    .setOnBannerListener(AuctionDetailsActivity.this)
                                    .setDelayTime(5000)
                                    .start();
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        RongIM.getInstance().removeUnReadMessageCountChangedObserver(this);
        super.onDestroy();
    }

    /**
     * 为你推荐
     */
    private void GetNewGoods(final int page, int pagesize) {
        OkGo.<BaseMode<List<NewGoodsBean>>>post(AppApi.BASE_URL + AppApi.SELECTNEWGOODS)
                .tag(this)
                .params("gisauction", "1")
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
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 根据id查询出价 记录
     */
    private void getBiddinList() {
        OkGo.<BaseMode<List<GetBiddinListBean>>>post(AppApi.BASE_URL + AppApi.GETBIDDINLIST)
                .tag(this)
                .params("gid", gid)
                .params("page", "1")
                .params("pagesize", "3")
                .execute(new JsonCallback<BaseMode<List<GetBiddinListBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<GetBiddinListBean>>> response) {
                        getBiddinListBeans.clear();
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            getBiddinListBeans = response.body().result;
                            if (getBiddinListBeans.size() == 0) {
                                tv_view_records.setVisibility(View.GONE);
                                rl_commodity_enaluation.setVisibility(View.GONE);
                                rl_auction_evaluate.setVisibility(View.VISIBLE);
                            } else {
                                tv_view_records.setVisibility(View.VISIBLE);
                                rl_commodity_enaluation.setVisibility(View.VISIBLE);
                                rl_auction_evaluate.setVisibility(View.GONE);
                            }
                            offerRecordAdapter.setNewData(getBiddinListBeans);
                            // ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
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
            tvGoodTitle.setTextColor(this.getResources().getColor(R.color.text_1_color));
            llTitle.setBackgroundColor(this.getResources().getColor(R.color.main_tone));
        } else {    //滑动到banner下面设置普通颜色
            llTitle.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
        }
    }

    private void initListeners() {
        ViewTreeObserver vto = auction_banner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llTitle.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = auction_banner.getHeight();
                scrollView.setScrollViewListener((GradationScrollView.ScrollViewListener) AuctionDetailsActivity.this);
            }
        });
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.iv_good_detai_back, R.id.tv_good_detail_message, R.id.tv_go_detail_shop,
            R.id.tv_good_detail_buy, R.id.tv_auction_rule, R.id.tv_view_records,
            R.id.ll_purchase_original_price, R.id.rl_shop_home, R.id.iv_good_detai_share})
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.iv_good_detai_share:
                String[] arrayStr = new String[]{};// 字符数组
                arrayStr = Images.split(",");// 字符串转字符数组
                String url = arrayStr[0];
                UMImage image = new UMImage(this, url);//分享图标
                final UMWeb web = new UMWeb("http://www.paiphui.com/h5/share/lotDetail.html?id=" + gid);
                web.setTitle(gname);//标题
                web.setThumb(image);//缩略图
                web.setDescription("在拍品汇发现一个宝贝，速来围观~~~");//描述

                //切记切记 这里分享的链接必须是http开头
                new ShareAction(this)
                        .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                if (share_media == SHARE_MEDIA.QQ) {
                                    new ShareAction(AuctionDetailsActivity.this).setPlatform(SHARE_MEDIA.QQ)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN) {
                                    new ShareAction(AuctionDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.QZONE) {
                                    new ShareAction(AuctionDetailsActivity.this).setPlatform(SHARE_MEDIA.QZONE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                                    new ShareAction(AuctionDetailsActivity.this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                            .withMedia(web)
                                            .setCallback(umShareListener)
                                            .share();
                                }
                            }
                        }).open();
                break;
            case R.id.rl_shop_home:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    Intent intent = new Intent(AuctionDetailsActivity.this, ShopActivity.class);
                    intent.putExtra("shopinfosid", sid);
                    startActivity(intent);
                }
                break;
            /*店铺关注*/
            /*case R.id.tv_auction_follow:
                if (AppUtile.isFastDoubleClick()) {
                } else if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "1");
                } else {
                    if (!NetworkUtils.isNetworkConnected(AuctionDetailsActivity.this)) {
                        ToastUtils.showToast("当前无网络请链接网络");
                    } else if (AppUtile.getTicket(AuctionDetailsActivity.this).equals("")) {
                        goToActivity(LoginActivity.class, "type", "1");
                    } else {
                        isfollow = shopfollow;
                        UpdateShopFollow(sid);
                    }
                }
                break;*/
            /*原价购*/
            case R.id.ll_purchase_original_price:
                bundle.putString("simg", simg);//店铺图片
                bundle.putString("sname", sname);//店铺名称
                bundle.putString("gname", gname);//商品名称
                bundle.putString("gimg", gimg);//商品图片
                bundle.putDouble("ototalvalue", ototalvalue);//商品价格
                bundle.putString("sid", sid);//店铺id
                bundle.putString("gid", ordergid);//商品id
                bundle.putInt("ognumber", ognumber);//商品库存
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能购买自己的商品");
                } else {
                    goToActivity(ConfirmationOrderActivity.class, bundle);
                }
                break;
            /*查看更多出价记录*/
            case R.id.tv_view_records:
                bundle.putString("gid", gid);
                goToActivity(AuctionViewRecordsActivity.class, bundle);
                break;
            /*返回*/
            case R.id.iv_good_detai_back:
                finish();
                break;
            /*出价按钮*/
            case R.id.tv_good_detail_buy:
                //CommodityAddCartDialog();
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能对自己的商品出价");
                } else if (aid.equals("")) {
                    showAddressDialog();
                } else {
                    showPopupWindow();
                }
                break;
            /*拍卖规则*/
            case R.id.tv_auction_rule:
                goToActivity(AuctionRuleActivity.class);
                break;
            /*发起私聊*/
            case R.id.tv_good_detail_message:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else if (uid.equals(sid)) {
                    ToastUtils.showToast("不能和自己的店铺沟通");
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
            /*查看店铺信息*/
            case R.id.tv_go_detail_shop:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    Intent intent = new Intent(AuctionDetailsActivity.this, ShopActivity.class);
                    intent.putExtra("shopinfosid", sid);
                    startActivity(intent);
                }
                break;
        }
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
            //Toast.makeText(AuctionDetailsActivity.this, " 分享回调中", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            // Toast.makeText(AuctionDetailsActivity.this, " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            // Toast.makeText(AuctionDetailsActivity.this, " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            // Toast.makeText(AuctionDetailsActivity.this, " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

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
                        photoDiloag = new LoadingDialog2(AuctionDetailsActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "店铺关注: " + response.body().code);
                        Log.e("text", "店铺关注: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            getBiddinList();
                            selectGoodsInfo();
                            getBalace();
                            if (isfollow) {
                                ToastUtils.showToast("取消关注");
                            } else {
                                ToastUtils.showToast("关注成功");
                            }
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
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

    private PopupWindow popupWindow;
    private double tvcjmoney, tvjjmoney;
    private String ototalnum;
    private double cjstartmoney;
    private double tvwtjgmoney;

    private void showPopupWindow() {
        // 获取屏幕的width和height
        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(AuctionDetailsActivity.this).inflate(
                R.layout.commodity_add_crat_dialog, null);
        popupWindow = new PopupWindow(contentView, LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tv_commodity_confirm = (TextView) contentView.findViewById(R.id.tv_commodity_confirm);//确认出价按钮
        TextView auction_close = (TextView) contentView.findViewById(R.id.auction_close);//关闭按钮
        ImageView img_shop = (ImageView) contentView.findViewById(R.id.img_shop);//商品图片
        TextView tv_auction_price = (TextView) contentView.findViewById(R.id.tv_auction_price);//商品当前价格
        TextView tv_jj_price = (TextView) contentView.findViewById(R.id.tv_jj_price);//加价价格
        final TextView tv_num = (TextView) contentView.findViewById(R.id.tv_num);//加价价格框
        TextView tv_reduce = (TextView) contentView.findViewById(R.id.tv_reduce);//价格减少按钮
        TextView tv_add = (TextView) contentView.findViewById(R.id.tv_add);//价格增加按钮
        TextView accoutbalance = (TextView) contentView.findViewById(R.id.tv_account_balance);//当前余额最高可出价
        TextView maxnumbidable = (TextView) contentView.findViewById(R.id.tv_maximum_bidable_balance);//当前余额最高可出价
        TextView tv_account_startmoney = (TextView) contentView.findViewById(R.id.tv_account_startmoney);//起拍价格
        CheckBox proxybid_checkbox = contentView.findViewById(R.id.proxybid_checkbox);//选择出价
        final MyEditText mye_proxybid = contentView.findViewById(R.id.mye_proxybid);//输入委托出价金额
        final TextView tv_submit_proxybid = contentView.findViewById(R.id.tv_submit_proxybid);//提交委托出价按钮
        if (isautobidding == 1) {
            proxybid_checkbox.setChecked(true);
        } else {
            proxybid_checkbox.setChecked(false);
        }
        proxybid_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked) {
                    if (isautobidding == 1) {
                        ToastUtils.showToast("您已经设置过委托出价");
                    } else {
                        mye_proxybid.setVisibility(View.VISIBLE);
                        tv_submit_proxybid.setVisibility(View.VISIBLE);
                    }
                } else {
                    tv_submit_proxybid.setVisibility(View.GONE);
                    mye_proxybid.setVisibility(View.GONE);
                }
            }
        });
        String[] strs = Images.split(",");
        Glide.with(AuctionDetailsActivity.this).load(strs[0]).fitCenter().into(img_shop);//商品图片
        cjstartmoney = Double.parseDouble(startmoney);//起拍价
        // tvcjmoney=cjstartmoney;//把起拍价付给当前出价价格
        tv_account_startmoney.setText("￥" + cjstartmoney);
        tv_auction_price.setText("￥" + money);//商品出价价格
        tv_jj_price.setText("￥" + jjmoney);//商品加价价格
        tvcjmoney = Double.parseDouble(money);//出价
        tvjjmoney = Double.parseDouble(jjmoney);//加价
        if (cjstartmoney > tvcjmoney) {
            tvcjmoney = cjstartmoney;// tvjjmoney + cjstartmoney;
        } else {
            tvcjmoney += tvjjmoney;
        }
        ototalnum = AmountUtil.priceNum(tvcjmoney);
        accoutbalance.setText("￥" + Amountmoney);
        maxnumbidable.setText("￥" + div(Double.parseDouble(Amountmoney), 0.07, 2));//Float.valueOf(Amountmoney) / 0.07
        tv_num.setText(ototalnum);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        /*设置委托出价*/
        tv_submit_proxybid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String proxybid = mye_proxybid.getText().toString().trim();
                if (proxybid.equals("")) {
                } else {
                    tvwtjgmoney = Double.parseDouble(mye_proxybid.getText().toString().trim());//委托出价金额
                }
                if (!AppUtile.isEditText(mye_proxybid)) {
                    ToastUtils.showToast("委托出价金额不能为空，请重新输入");
                } else if (tvwtjgmoney <= tvcjmoney) {
                    ToastUtils.showToast("委托出价金额不能小于" + tvcjmoney + "请重新输入");
                    mye_proxybid.setText("");
                } else {
                    tvwtjgmoney = Double.parseDouble(mye_proxybid.getText().toString().trim());//委托出价金额
                    addentrust(tvwtjgmoney);
                }
            }
        });
        tv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double dangqian = Double.parseDouble(ototalnum.toString());//其当前价
                if (dangqian >= tvcjmoney) {
                    ToastUtils.showToast("不能小于当前出价价格");
                } else {
                    double test = tvcjmoney -= tvjjmoney;
                    String test1 = AmountUtil.priceNum(test);
                    tv_num.setText(test1);
                }
            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double test = tvcjmoney += tvjjmoney;
                String test1 = AmountUtil.priceNum(test);
                tv_num.setText(test1);
            }
        });
        /*关闭按钮点击事件*/
        auction_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvcjmoney = 0;
                tvjjmoney = 0;
                popupWindow.dismiss();
            }
        });
        /*出价按钮点击事件*/
        tv_commodity_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvcjmoney = 0;
                tvjjmoney = 0;
                String banlce = tv_num.getText().toString().trim();
                if (AppUtile.getTicket(AuctionDetailsActivity.this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else if (Float.valueOf(Amountmoney) < Float.valueOf(banlce) * 0.07) {
                    showDelDialog();
                } else {
                    getaddBiddin(tv_num.getText().toString());
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(contentView, Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(AuctionDetailsActivity.this, "温馨提示", "当前余额不足出价,请去充值",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           goToActivity(RechargeActivity.class);
                                       }
                                   }
        );
    }

    private void showAddressDialog() {
        final UIAlertView delDialog = new UIAlertView(AuctionDetailsActivity.this, "温馨提示", "请完善收货地址，再去出价！",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           goToActivity(AddedReceicingAddress.class);
                                       }
                                   }
        );
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 设置委托出价*
     */
    public void addentrust(double proxybidmoneyy) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDENTRUST)
                .tag(this)
                .params("gid", gid)
                .params("money", proxybidmoneyy)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                            popupWindow.dismiss();
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 查询余额*
     */
    public void getBalace() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETBALANCE)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Amountmoney = (String) response.body().result;
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /*新增出价记录*/
    public void getaddBiddin(String bmoney) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDBIDDIN)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .params("gid", gid)
                .params("bmoney", bmoney)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                            //selectGoodsInfo(gid);
                            /*获取出价记录*/
                            getBiddinList();
                            selectGoodsInfo();
                            getBalace();
                        } else {
                            ToastUtils.showShort(AuctionDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    public void CommodityAddCartDialog() {
        final CommodityAddCartDialog commodityAddCartDialog = new CommodityAddCartDialog(this);
        commodityAddCartDialog.show();
    }

    /**
     * 反射方法控制drawerLayout滑动边距
     * 设置drawerLayout滑动边距
     *
     * @param drawerLayout
     * @param activity
     * @param proportion
     */
    public static void setDrawerRightEdgeSize(DrawerLayout drawerLayout, Activity activity, float proportion) {
        if (drawerLayout == null || activity == null) {
            return;
        }
        try {
            Field field = drawerLayout.getClass().getDeclaredField("mRightDragger");
            field.setAccessible(true);
            ViewDragHelper mLeftDragger = (ViewDragHelper) field.get(drawerLayout);
            Field field1 = mLeftDragger.getClass().getDeclaredField("mEdgeSize");
            field1.setAccessible(true);
            DisplayMetrics metrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            field1.setInt(mLeftDragger, (int) (metrics.widthPixels * proportion));
        } catch (Exception e) {
            e.printStackTrace();
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
    }

    @Override
    public void onCountChanged(final int i) {
        //显示到这里了，不就是这里吗？
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //俩条更新Ui逻辑
                getBalace();//查询余额
                selectGoodsInfo();//查询拍品详情
                getBiddinList();//查询出价记录
            }
        });
        Log.d("test", "onCountChanged: " + i);
    }
}