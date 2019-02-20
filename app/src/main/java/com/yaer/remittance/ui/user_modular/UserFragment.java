package com.yaer.remittance.ui.user_modular;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.MainActivity;
import com.yaer.remittance.bean.ShopInfoByidBean;
import com.yaer.remittance.bean.UserBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.personal.PersonalInformationActivity;
import com.yaer.remittance.ui.user_modular.setup.UserSettingActivity;
import com.yaer.remittance.ui.user_modular.share.ShareAppActivity;
import com.yaer.remittance.ui.user_modular.shopinfo.ShopInformationActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.attention.UserAttentionActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.UserBalanceActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.collect.UserCollectActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.coupons.CouponSellerActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.coupons.MyAddcouponActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.footprint.UserFootPrintActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.integral.UserIntegralActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.integral.UserSellerIntegralActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.joinlot.UserJoinLotActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.RefundInfoListActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.signin.UserSignInActivity;
import com.yaer.remittance.ui.user_modular.user_seller.auctionmanagement.AuctionActivity;
import com.yaer.remittance.ui.user_modular.user_seller.authentication.UserAuthenticationStatusActivity;
import com.yaer.remittance.ui.user_modular.user_seller.drafts.DraftsActivity;
import com.yaer.remittance.ui.user_modular.user_seller.extensioncenter.UserMyAuctionActivity;
import com.yaer.remittance.ui.user_modular.user_seller.help.UserHelpActivity;
import com.yaer.remittance.ui.user_modular.user_seller.order.SellerAllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_seller.order.SellerRefundInfoListActivity;
import com.yaer.remittance.ui.user_modular.user_seller.storetable.StoreTableActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.CSCustomServiceInfo;

/**
 * Created by Administrator on 2017/6/18.
 * 我的首页
 */
public class UserFragment extends BaseFragment {
    //返回
    @BindView(R.id.mFlCardBack)
    FrameLayout mFlCardBack;
    //整个页面layout
    @BindView(R.id.mFlCardFront)
    FrameLayout mFlCardFront;
    //买家text
    @BindView(R.id.tv_buyer)
    TextView tv_buyer;
    //卖家text
    @BindView(R.id.tv_seller)
    TextView tv_seller;
    //个人信息头部layout
    @BindView(R.id.rl_user)
    RelativeLayout rl_user;
    //买家layout
    @BindView(R.id.rl_userbuyer)
    RelativeLayout rl_userbuyer;
    //卖家帮助
    @BindView(R.id.ll_seller_help)
    LinearLayout ll_seller_help;
    //买家帮助
    @BindView(R.id.ll_buyer_help)
    LinearLayout ll_buyer_help;
    //买家意见反馈
  /*  @BindView(R.id.tv_feedback)
    TextView tv_feedback;*/
    //买家参加拍品
    @BindView(R.id.tv_joinlot)
    TextView tv_joinlot;
    //卖家头像
    @BindView(R.id.seller_mCircleImageView)
    CircleImageView seller_mCircleImageView;
    //买家头像
    @BindView(R.id.buyer_mCircleImageView)
    CircleImageView buyer_mCircleImageView;
    //买家名称
    @BindView(R.id.buyer_name)
    TextView buyer_name;
    //卖家名称
    @BindView(R.id.seller_name)
    TextView seller_name;
    //我的关注
    @BindView(R.id.tv_my_attention)
    TextView tv_my_attention;
    /* //认证中心
     @BindView(R.id.tv_authentication_center)
     TextView tv_authentication_center;*/
    //买家设置
    @BindView(R.id.ll_buyer_setting)
    LinearLayout ll_buyer_setting;
    //卖家设置
    @BindView(R.id.ll_seller_setting)
    LinearLayout ll_seller_setting;
    //推广中心
    @BindView(R.id.ll_extension_center)
    LinearLayout ll_extension_center;
    //余额
    @BindView(R.id.ll_balance)
    LinearLayout ll_balance;
    //签到
    @BindView(R.id.ll_sign_in)
    LinearLayout ll_sign_in;
    /*买家等级*/
    @BindView(R.id.tv_buyer_leven)
    TextView tv_buyer_leven;
    /*买家简介*/
    @BindView(R.id.tv_buyer_desc)
    TextView tv_buyer_desc;
    /*卖家等级*/
    @BindView(R.id.tv_seller_leven)
    TextView tv_seller_leven;
    //积分
    @BindView(R.id.ll_integral)
    LinearLayout ll_integral;
    //分享
    @BindView(R.id.ll_share)
    LinearLayout ll_share;
    //我的足迹
    @BindView(R.id.ll_my_tracks)
    LinearLayout ll_my_tracks;
    //我的收藏
    @BindView(R.id.ll_collect)
    LinearLayout ll_collect;
    /*待付款*/
    @BindView(R.id.ll_pending_payment)
    LinearLayout ll_pending_payment;
    /*待发货*/
    @BindView(R.id.ll_pending_delivery)
    LinearLayout ll_pending_delivery;
    /*待收货*/
    @BindView(R.id.ll_goods_received)
    LinearLayout ll_goods_received;
    /*待评价*/
    @BindView(R.id.ll_to_evaluated)
    LinearLayout ll_to_evaluated;
    /*退款售后*/
    @BindView(R.id.ll_refund_after_sale)
    LinearLayout ll_refund_after_sale;
    /*查看全部订单*/
    @BindView(R.id.full_order)
    LinearLayout full_order;
    /*卖家余额*/
    @BindView(R.id.seller_title_balance)
    LinearLayout seller_title_balance;
    /*卖家签到*/
    @BindView(R.id.seller_signin)
    LinearLayout seller_signin;
    /*卖家积分*/
    @BindView(R.id.seller_integral)
    LinearLayout seller_integral;
    /*卖家店铺报表*/
    @BindView(R.id.ll_seller_store_report)
    LinearLayout ll_seller_store_report;
    /*卖家客服*/
    @BindView(R.id.ll_customer_service_seller)
    LinearLayout ll_customer_service_seller;
    /*卖家商家入驻*/
   /* @BindView(R.id.ll_seller_enter)
    LinearLayout ll_seller_enter;*/
    /*卖家分享*/
    @BindView(R.id.ll_seller_share)
    LinearLayout ll_seller_share;
    /*藏品管理*/
    @BindView(R.id.ll_auctionmanagement)
    LinearLayout ll_auctionmanagement;
    /* @BindView(R.id.ll_AddCollection)
     LinearLayout ll_AddCollection;*/
    /*草稿箱*/
    @BindView(R.id.ll_drafts)
    LinearLayout ll_drafts;
    /*卖家待付款*/
    @BindView(R.id.wait_pay)
    LinearLayout wait_pay;
    /*卖家待发货*/
    @BindView(R.id.wait_delivery)
    LinearLayout wait_delivery;
    /*卖家待收货*/
    @BindView(R.id.wait_received)
    LinearLayout wait_received;
    /*卖家待评价*/
    @BindView(R.id.evaluate)
    LinearLayout evaluate;
    /*卖家退款售后*/
    @BindView(R.id.refund_after_sale)
    LinearLayout refund_after_sale;
    /*卖家查看全部订单*/
    @BindView(R.id.seller_all)
    LinearLayout seller_all;
    /*客服*/
    @BindView(R.id.ll_customer_service)
    LinearLayout ll_customer_service;
    /*卖家退货比例*/
    @BindView(R.id.tv_seller_ureturnscale)
    TextView tv_seller_ureturnscale;
    /*卖家违约比例*/
    @BindView(R.id.tv_seller_uinfringementscale)
    TextView tv_seller_uinfringementscale;
    /*买家优惠券*/
 /*   @BindView(R.id.tv_coupon)
    TextView tv_coupon;*/
    /*卖家优惠券*/
    @BindView(R.id.tv_seller_coupon)
    TextView tv_seller_coupon;
    /*用户标识utoken*/
    private String utoken = "";
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;
    /*   @BindView(R.id.ll_issue_coupons)
       LinearLayout ll_issue_coupons;*/
    private int sid;

    /**
     * 初始化titleBar
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), rl_user);
        ImmersionBar.setTitleBar(getActivity(), rl_userbuyer);
    }


    /***
     * 设置沉浸式
     */
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColorTransformEnable(false)
                .navigationBarColor(R.color.main_tone)
                .init();
    }

    /***
     *
     * 设置布局文件
     * */
    @Override
    protected int setLayoutResouceId() {
        return R.layout.user_fragment;
    }

    //先走他
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//false
            GetUsertype();
        }
    }

    //第二个走
    @Override
    public void onResume() {
        super.onResume();
        utoken = AppUtile.getTicket(mActivity);
//         String icon= (String) SharedPreferencesUtils.getData(getActivity(),"PersonalIcon","");
//         //在这里刷新头像
//        Glide.with(getActivity()).load(icon).fitCenter().into(buyer_mCircleImageView);//买家头像asdasd
        if (utoken.equals("")) {

        } else {
            GetUserInfo();
        }
    }

    //第三个走
    @Override
    protected void initData(Bundle arguments) {
        GetUsertype();
    }

    private void GetUsertype() {
        utoken = AppUtile.getTicket(mActivity);
        if (utoken.equals("")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            intent.putExtra("type", "4");
            startActivity(intent);
        } else if (!NetworkUtils.isNetworkConnected(getActivity())) {
            ToastUtils.showToast("当前没有连接网络");
        }
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initView() {

    }

    /**
     * 获取用户信息
     */
    public void GetUserInfo() {
        OkGo.<BaseMode<UserBean>>post(AppApi.BASE_URL + AppApi.GETUSERINFO)
                .tag(this)
                .params("utoken", utoken)
                .params("type", "1")
                .execute(new JsonCallback<BaseMode<UserBean>>(getActivity()) {
                    @Override
                    public void onStart(Request<BaseMode<UserBean>, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<UserBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (response.body().result == null) {
                                return;
                            } else {
                                String level = response.body().result.getUlevel();
                                if (!level.equals("") || !"".equals(level)) {
                                    tv_buyer_leven.setText("Lv" + level);
                                } else {
                                    tv_buyer_leven.setText("Lv0");
                                }
                                if (response.body().result.getUname().equals("")) {
                                    buyer_name.setText("" + AppUtile.hideCardNo(response.body().result.getUphone()));//买家名称手机号
                                } else {
                                    buyer_name.setText(response.body().result.getUname());//买家名称
                                }
                                if (response.body().result.getUicon().equals("")) {
                                    Glide.with(mActivity).load(R.drawable.user_settings).fitCenter().into(buyer_mCircleImageView);//商品图片
                                } else {
                                    Glide.with(mActivity).load(response.body().result.getUicon()).fitCenter().into(buyer_mCircleImageView);//买家头像
                                }
                                if (response.body().result.getUsignature().equals("")) {
                                    tv_buyer_desc.setText("暂无数据！");
                                } else {
                                    tv_buyer_desc.setText(response.body().result.getUsignature());
                                }
                                tv_seller_ureturnscale.setText("退货比例:" + response.body().result.getUreturnscale());//卖家退货比例
                                tv_seller_uinfringementscale.setText("违约比例:" + response.body().result.getUinfringementscale());//卖家违约比例
                                SharedPreferencesUtils.saveData(getActivity(), "uinvitationcode", response.body().result.getUinvitationcode());//邀请码
                                SharedPreferencesUtils.saveData(getActivity(), "uintegral", response.body().result.getUintegral());//积分
                                SharedPreferencesUtils.saveData(getActivity(), "uname", response.body().result.getUname());//用户名称
                                SharedPreferencesUtils.saveData(getActivity(), "uphone", response.body().result.getUphone());//用户手机号
                                SharedPreferencesUtils.saveData(getActivity(), "ulevel", response.body().result.getUlevel());//用户等级
                                SharedPreferencesUtils.saveData(getActivity(), "uicon", response.body().result.getUicon());//用户头像
                                SharedPreferencesUtils.saveData(getActivity(), "enterprisecertification", response.body().result.getEnterprisecertification());//企业认证
                                SharedPreferencesUtils.saveData(getActivity(), "personalauthentication", response.body().result.getPersonalauthentication());//个人认证
                                SharedPreferencesUtils.saveData(getActivity(), "ishaveLoginpassword", response.body().result.getIshaveLoginpassword());//登录密码
                                SharedPreferencesUtils.saveData(getActivity(), "ishaveUpaypassword", response.body().result.getIshaveUpaypassword());//支付密码
                                getShopInfoBySid();
                            }
                        }/* else if (response.body().code.equals(Constant.YZM_INVALID)) {
                            goToActivity(LoginActivity.class, "type", "4");
                            SharedPreferencesUtils.saveData(getActivity(), "uToken", "");
                        }*/ else {
                            goToActivity(LoginActivity.class, "type", "4");
                            SharedPreferencesUtils.saveData(getActivity(), "uToken", "");//用户token
                            SharedPreferencesUtils.saveData(getActivity(), "uid", 0);//用户id
                            SharedPreferencesUtils.saveData(getActivity(), "personalauthentication", 0);//个人认证
                            SharedPreferencesUtils.saveData(getActivity(), "uname", "");//用户名称
                            SharedPreferencesUtils.saveData(getActivity(), "ulevel", "");//等级
                            SharedPreferencesUtils.saveData(getActivity(), "uicon", "");//用户头像
                            SharedPreferencesUtils.saveData(getActivity(), "ishaveLoginpassword", "");//登录密码
                            SharedPreferencesUtils.saveData(getActivity(), "ishaveUpaypassword", "");//支付密码
                            SharedPreferencesUtils.saveData(getActivity(), "enterprisecertification", 0);//个人认证
                            SharedPreferencesUtils.saveData(getActivity(), "ShopInfoid", "");//店铺id标识
                            //showgoods();
                            tv_buyer_leven.setText("Lv0");//等级
                            buyer_name.setText("拍品汇");//买家名称
                            tv_buyer_desc.setText("暂无数据！");
                            Glide.with(mActivity).load(R.drawable.user_settings).fitCenter().into(buyer_mCircleImageView);//商品图片
                            RongIM.getInstance().disconnect();
                            JPushInterface.setAlias(mActivity, 0, "");
                            //删除别名
                            JPushInterface.deleteAlias(mActivity, 0);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<UserBean>> response) {
                        super.onError(response);
//                        ToastUtils.showShort(mActivity, response.body().msg);
                        // stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //LoadingDialog.hide();
                        // stopDialog();
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

    /*获取店铺信息*/
    public void getShopInfoBySid() {
        int uid = AppUtile.getUid(getActivity());
        OkGo.<BaseMode<ShopInfoByidBean>>post(AppApi.BASE_URL + AppApi.FETSHOPINFOBYSID)
                .tag(this)
                .params("sid", uid)
                .execute(new JsonCallback<BaseMode<ShopInfoByidBean>>(getActivity()) {
                    @Override
                    public void onSuccess(Response<BaseMode<ShopInfoByidBean>> response) {
                        Log.e("text", "获取店铺信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            seller_name.setText(response.body().result.getShopname());//卖家名称
                            sid = response.body().result.getShopid();
                            SharedPreferencesUtils.saveData(getActivity(), "Scid", response.body().result.getScid());
                            SharedPreferencesUtils.saveData(getActivity(), "shopid", response.body().result.getShopid());//判断店铺状态
                            SharedPreferencesUtils.saveData(getActivity(), "Shopinfo", 1);//判断店铺状态
                            Glide.with(mActivity).load(response.body().result.getShopimg()).fitCenter().into(seller_mCircleImageView);//卖家头像
                        } else {
                            if (response.body().code.equals("207")) {
                                SharedPreferencesUtils.saveData(getActivity(), "Shopinfo", 0);
                            }
                        }
                    }
                });
    }

    /**
     * 全部点击事件
     */
    @OnClick({R.id.wait_pay, R.id.seller_title_balance, R.id.seller_signin, R.id.seller_integral, R.id.ll_seller_store_report, R.id.ll_seller_share, R.id.wait_delivery, R.id.wait_received, R.id.evaluate, R.id.refund_after_sale, R.id.seller_all, R.id.tv_buyer, R.id.ll_drafts, R.id.ll_share, R.id.ll_auctionmanagement, R.id.tv_joinlot, R.id.tv_my_attention, R.id.tv_seller, R.id.ll_seller_help, R.id.ll_buyer_help,
            R.id.buyer_mCircleImageView, R.id.seller_mCircleImageView, R.id.ll_buyer_setting, R.id.ll_seller_setting,
            R.id.ll_extension_center, R.id.ll_balance, R.id.ll_collect, R.id.ll_my_tracks,
            R.id.ll_pending_payment, R.id.ll_customer_service, R.id.ll_customer_service_seller, R.id.ll_pending_delivery, R.id.ll_goods_received, R.id.ll_to_evaluated, R.id.ll_refund_after_sale,
            R.id.full_order, R.id.ll_sign_in, R.id.ll_integral, R.id.tv_seller_coupon})
    //R.id.tv_authentication_center
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            /*卖家优惠券*/
            case R.id.tv_seller_coupon:
                //goToActivity(CouponSellerActivity.class);
                goToActivity(MyAddcouponActivity.class);
                break;
        /*    case R.id.ll_issue_coupons:
                goToActivity(AddCouponActivity.class);
                break;*/
            /*买家优惠券*/
          /*  case R.id.tv_coupon:
                goToActivity(CouponActivity.class);
                break;*/
            /**
             * 发布*
             * */
         /*   case R.id.ll_AddCollection:
                goToActivity(AddCollectionActivity.class);
                break;*/
            /**
             * 卖家帮助
             * */
            case R.id.ll_seller_help:
                bundle.putString("hctype", "2");
                goToActivity(UserHelpActivity.class, bundle);
                break;
            /**
             * 买家帮助
             * */
            case R.id.ll_buyer_help:
                bundle.putString("hctype", "1");
                goToActivity(UserHelpActivity.class, bundle);
                break;
            /**
             * 买家头像登录
             * */
            case R.id.buyer_mCircleImageView:
                if (!NetworkUtils.isNetworkConnected(getActivity())) {
                    ToastUtils.showToast("当前无网络状态，请连接网络");
                } else {
                    goToActivity(PersonalInformationActivity.class);
                }
                break;
            /**
             * 卖家头像登录
             * */
            case R.id.seller_mCircleImageView:
                if (!NetworkUtils.isNetworkConnected(getActivity())) {
                    ToastUtils.showToast("当前无网络状态，请连接网络");
                } else {
                    goToActivity(ShopInformationActivity.class);
                }
                break;
            /*买家设置*/
            case R.id.ll_buyer_setting:
                goToActivity(UserSettingActivity.class);
                break;
            /**
             * 认证中心
             * */
           /* case R.id.tv_authentication_center:
                goToActivity(UserAuthenticationCenterActivity.class);
                break;*/
            /**
             * 买家设置
             ** /
             /* case R.id.ll_buyer_setting:
             goToActivity(UserSettingActivity.class);
             break;*/
            /**
             * 卖家设置
             * */
            case R.id.ll_seller_setting:
                goToActivity(UserSettingActivity.class);
                break;
            /**
             * 推广中心
             * */
            case R.id.ll_extension_center:
                //goToActivity(UserExtensionCenterActivity.class);
                goToActivity(UserMyAuctionActivity.class);
                break;
            /**
             * 买家意见反馈
             * */
           /* case R.id.tv_feedback:
                goToActivity(UserFeedBackActivity.class);
                break;*/
            /**
             * 买家分享
             * */
            case R.id.ll_share:
                goToActivity(ShareAppActivity.class);
                break;
            /**
             * 买家参加拍品
             * */
            case R.id.tv_joinlot:
                goToActivity(UserJoinLotActivity.class);
                break;
            /**
             * 买家关注
             * */
            case R.id.tv_my_attention:
                goToActivity(UserAttentionActivity.class);
                break;
            /**
             * 买家客服
             * */
            case R.id.ll_customer_service:
                CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                CSCustomServiceInfo csInfo = csBuilder.nickName("融云客服").build();
                //首先需要构造使用客服者的用户信息
                /**
                 * 启动客户服聊天界面。
                 *
                 * @param context           应用上下文。
                 * @param customerServiceId 要与之聊天的客服 Id。
                 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
                 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
                 */
                RongIM.getInstance().startCustomerServiceChat(getActivity(), "KEFU154348202121131", "在线客服", csInfo);
                // goToActivity(ReportActivity.class);
                break;
            /**
             * 卖家客服
             * */
            case R.id.ll_customer_service_seller:
                CSCustomServiceInfo.Builder csBuilderseller = new CSCustomServiceInfo.Builder();
                CSCustomServiceInfo csInfoseller = csBuilderseller.nickName("融云客服").build();
                //首先需要构造使用客服者的用户信息
                /**
                 * 启动客户服聊天界面。
                 *
                 * @param context           应用上下文。
                 * @param customerServiceId 要与之聊天的客服 Id。
                 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
                 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
                 */
                RongIM.getInstance().startCustomerServiceChat(getActivity(), "KEFU154348202121131", "在线客服", csInfoseller);
                // goToActivity(ReportActivity.class);
                break;
            /**
             * 买家
             * */
            case R.id.tv_buyer:
                int enterprisecertification = (int) SharedPreferencesUtils.getData(mActivity, "enterprisecertification", 0);
                int personalauthentication = (int) SharedPreferencesUtils.getData(mActivity, "personalauthentication", 0);
                int shopinfo = (int) SharedPreferencesUtils.getData(mActivity, "Shopinfo", 0);
                if (!NetworkUtils.isNetworkConnected(getActivity())) {
                    ToastUtils.showToast("当前没有连接网络");
                } else if (shopinfo == 1) {
                    showgoods();//切换店铺
                } else if (personalauthentication == 0) {
                    if (enterprisecertification == 0) {
                        showDelDialog();
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 2) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    }
                } else if (personalauthentication == 1) {
                    if (enterprisecertification == 0) {
                        showDelDialog();
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 2) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    }
                } else if (personalauthentication == 2) {
                    if (enterprisecertification == 0) {
                        showDelDialog();
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 2) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    }
                } else if (personalauthentication == 3) {
                    if (enterprisecertification == 0) {
                        showDelDialog();
                    } else if (enterprisecertification == 1) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 2) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    } else if (enterprisecertification == 3) {
                        goToActivity(UserAuthenticationStatusActivity.class);
                    }
                }
                break;
            /**
             * 卖家
             * */
            case R.id.tv_seller:
                mFlCardBack.setVisibility(View.GONE);
                mFlCardFront.setVisibility(View.VISIBLE);
                // 向左边移入
                mFlCardFront.setAnimation(AnimationUtils.makeInAnimation(getActivity(), false));
                // 向左边移出
                mFlCardBack.setAnimation(AnimationUtils.makeOutAnimation(getActivity(), false));
                break;
            /**
             * 买家余额
             * */
            case R.id.ll_balance:
                bundle.putString("buyerbalance", "1");
                goToActivity(UserBalanceActivity.class,bundle);
                break;
            /**
             * 买家签到
             * */
            case R.id.ll_sign_in:
                goToActivity(UserSignInActivity.class);
                break;
            /**
             * 买家积分
             * */
            case R.id.ll_integral:
                goToActivity(UserIntegralActivity.class);
                break;
            /**
             * 买家收藏
             * */
            case R.id.ll_collect:
                goToActivity(UserCollectActivity.class);
                break;
            /**Rz
             * 买家我的足迹
             * */
            case R.id.ll_my_tracks:
                goToActivity(UserFootPrintActivity.class);
                break;
            /**
             * 买家待付款
             * */
            case R.id.ll_pending_payment:
                bundle.putString("type", "1");
                goToActivity(AllOrderActivity.class, bundle);
                break;
            /**
             * 买家待发货
             * */
            case R.id.ll_pending_delivery:
                bundle.putString("type", "2");
                goToActivity(AllOrderActivity.class, bundle);
                break;
            /**
             * 买家待收货
             * */
            case R.id.ll_goods_received:
                bundle.putString("type", "3");
                goToActivity(AllOrderActivity.class, bundle);
                break;
            /**
             * 买家待评价
             * */
            case R.id.ll_to_evaluated:
                bundle.putString("type", "4");
                goToActivity(AllOrderActivity.class, bundle);
                break;
            /**
             * 买家退款售后
             * */
            case R.id.ll_refund_after_sale:
                bundle.putString("type", String.valueOf(sid));
                goToActivity(RefundInfoListActivity.class, bundle);
                break;
            /**
             * 买家全部订单*/
            case R.id.full_order:
                bundle.putString("type", "0");
                goToActivity(AllOrderActivity.class, bundle);
                break;
            /**
             * 卖家草稿箱管理
             * */
            case R.id.ll_drafts:
                goToActivity(DraftsActivity.class);
                break;
            /**
             * 卖家藏品管理
             * */
            case R.id.ll_auctionmanagement:
                goToActivity(AuctionActivity.class);
                break;
            /**
             * 卖家余额
             * */
            case R.id.seller_title_balance:
                bundle.putString("buyerbalance", "2");
                goToActivity(UserBalanceActivity.class,bundle);
                break;
            /**
             * 卖家签到
             * */
            case R.id.seller_signin:
                goToActivity(UserSignInActivity.class);
                break;
            /**
             * 卖家积分
             * */
            case R.id.seller_integral:
                goToActivity(UserSellerIntegralActivity.class);
                break;
            /**
             * 卖家店铺报表
             * */
            case R.id.ll_seller_store_report:
                goToActivity(StoreTableActivity.class);
                break;
            /**
             * 卖家认证中心
             * */
           /* case R.id.ll_seller_authentication_center:
                goToActivity(UserAuthenticationCenterActivity.class);
                break;*/
            /**
             * 商家入驻
             * */
           /* case R.id.ll_seller_enter:
                goToActivity(UserEnterActivity.class);
                break;*/
            /**
             * 卖家邀请好友
             * */
            case R.id.ll_seller_share:
                goToActivity(ShareAppActivity.class);
                break;
            /**
             * 卖家待付款
             * */
            case R.id.wait_pay:
                bundle.putString("type", "1");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);
                break;
            /**
             * 卖家待发货
             * */
            case R.id.wait_delivery:
                bundle.putString("type", "2");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);
                break;
            /**
             * 卖家待收货
             * */
            case R.id.wait_received:
                bundle.putString("type", "3");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);
                break;
            /**
             * 卖家待评价
             * */
            case R.id.evaluate:
                bundle.putString("type", "4");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);
                break;
            /**
             * 卖家退款售后
             * */
            case R.id.refund_after_sale:
               /* bundle.putString("type", "5");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);*/
                goToActivity(SellerRefundInfoListActivity.class);
                break;
            /**
             * 卖家全部订单
             * */
            case R.id.seller_all:
                bundle.putString("type", "0");
                bundle.putString("Sour", "user");
                goToActivity(SellerAllOrderActivity.class, bundle);
                break;
        }
    }

    private void showgoods() {
        mFlCardFront.setVisibility(View.GONE);//买家
        mFlCardBack.setVisibility(View.VISIBLE);//卖家
        // 向右边移出
        mFlCardBack.setAnimation(AnimationUtils.makeOutAnimation(getActivity(), true));
        // 向右边移入
        mFlCardFront.setAnimation(AnimationUtils.makeInAnimation(getActivity(), true));
    }

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "您还未认证，是否现在认证？",
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
                                           goToActivity(UserAuthenticationStatusActivity.class);
                                       }
                                   }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 友盟分享的回调
     *
     * @param
     */

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<MainActivity> mActivity;

        private CustomShareListener(MainActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享成功啦");
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            ToastUtils.showToast("分享失败啦");
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showToast("分享取消了");
        }
    }
}
