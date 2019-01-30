package com.yaer.remittance.api;

/**
 * Created by Administrator on 2017/6/13.
 * -------  api 路径 ----
 */
public class AppApi {
    //*****************App 静态页主路径******************//
    //public static String BASE_URL = "http://api.paiphui.com";
    public static String BASE_URL = "http://47.95.196.248:8081";

    //获取验证码
    public static String GET_YANZHENGMA = "/user/sendSMS";
    //验证码是否正确
    public static String VERIFICATION_YANZHENGMA = "/user/validateYZM";
    //验证码登录
    public static String YZMLOGIN = "/user/loginbyyzm";
    //设置密码
    public static String UPDATELOGPWD = "/user/updateLogPWD";
    //登录
    public static String LOGINBYPWD = "/user/loginbypwd";
    //获取Banner
    public static String GETBANNER = "/goods/selectBanner";
    //查询最新商品
    public static String SELECTNEWGOODS = "/goods/selectNewGoods";
    //获取用户信息
    public static String GETUSERINFO = "/user/selectUserInfo";
    //修改用户信息
    public static String UPDATEUSERINF = "/user/updateUserInfo";
    //根据用户id查询用户所有收货地址
    public static String ENQUERYRECEIPTADDRESS = "/address/selectAddress";
    //添加收货地址
    public static String RECEIVINGAdDRESS = "/address/addAddressInfo";
    //删除用户收货地址
    public static String DELETEADDRESS = "/address/deleteAddress";
    //修改用户收货地址
    public static String UPDATEUADDRESS = "/address/updateAddressInfo";
    //根据诋地址id修改默认收货地址
    public static String UPDATEDEFAULT = "/address/updateAddressDefault";
    //获取优店数据
    public static String GETSHOPINFOMAXFANS = "/shopInfo/getShopInfoMaxFans";
    //阿里云OOS连接
    public static String ALYOOS = "https://paipinhui.oss-cn-qingdao.aliyuncs.com/";
    //获取专家列表详情
    public static String GETEXPERTSSAYINFO = "/user/getExpertsSayInfo";
    //获取专家列表
    public static String GETALLEXPERTSSAY = "/user/getAllExpertsSay";
    //获取活动专区列表
    public static String ALLSPECIALEVENT = "/specialevent/selectAllSpecialEvent";
    //获取商品列表
    public static String SELECTGOODSLIST = "/goods/selectGoodsList";
    //根据id查询商品详情
    public static String SELECTGOODSINFO = "/goods/selectGoodsInfo";
    //发布商品
    public static String SELECTINSERTGOODS = "/goods/insertGoods";
    //查询精选商品
    public static String SELECTGOODSCHOICE = "/goods/selectGoodsChoice";
    //获取详情分类列表
    public static String GETGOODSCLASSMODELS = "/goodClassIFicationInfo/getGoodsClassIFicationModels";
    //获取我的浏览足迹列表
    public static String GETBROWSINGHISTORY = "/goods/selectBrowsingHistory";
    //删除我的浏览足迹列表
    public static String DELETEBROWSINGHISTORY = "/goods/deleteBrowsingHistory";
    //添加反馈举报
    public static String ADDREPORTINFO = "/report/addReportInfo";
    //查询个人认证信息
    public static String SELECTPERSONALAUTHENTICATIONINFO = "/user/selectPersonalAuthenticationInfo";
    //添加个人认证信息
    public static String ADDPERSONALAUTHENTICATIONINFO = "/user/addPersonalAuthenticationInfo";
    //修改个人认证信息
    public static String UPDATEPERSONALAUTHENTICATIONINFO = "/user/updatePersonalAuthenticationInfo";
    //查询企业认证信息
    public static String SELECTENTERPRISEAUTHENTICATIONINFO = "/user/selectEnterprisecertification";
    //添加企业认证信息
    public static String ADDENTERPRISEAUTHENTICATIONINFO = "/user/addEnterprisecertification";
    //修改企业认证信息
    public static String UPDATEENTERPRISEAUTHENTICATIONINFO = "/user/updateEnterprisecertification";
    //添加专家认证信息
    //  public static String ADDEXPERTCERTIFICATIONINFO = "/goods/deleteBrowsingHistory";
    //查询该用户绑定的所有银行卡
    public static String SELECTBANKCRDINFOLIST = "/user/selectBankCardInfoList";
    //添加银行卡
    public static String ADDBANKCARD = "/user/addBankCard";
    //删除银行卡
    public static String DELETEBANKCARD = "/user/deleteBankCard";
    //第三方登录
    public static String LOGINTHIRPARTY = "/user/loginThirdParty";
    //第三方登录设置手机号
    public static String LOGINBINDPHONE = "/user/bindPhone";
    //获取0元拍
    public static String SELECTGOODSPAI = "/goods/selectGoods0Pai";
    //获取首页聚合信息
    public static String GETMAINDATA = "/goods/getMainData";
    //获取首页商品分类
    public static String FETSHOPCLASSIFICATIONINFOMODELS = "/shopClassIFicationInfo/getShopClassIFicationInfoModels";
    //添加店铺信息
    public static String INSERTSHOPINFOMODEL = "/shopInfo/insertShopInfoModel";
    //新增订单
    public static String ADDORDER = "/order/addOrder";
    //查询订单默认地址
    public static String GETDEFAULTADDRESS = "/address/getDefaultAddress";
    //根据用户id获取订单列表
    public static String GETORDERLIST = "/order/getOrderList";
    //取消订单
    public static String CANCELORDER = "/order/cancelOrder";
    //删除订单
    public static String DELETEORDER = "/order/deleteOrder";
    //修改订单
    public static String UPDATEORDERSTATUS = "/order/updateOrderStatus";
    //发货接口
    public static String DELIVERGOODS = "/order/deliverGoods";
    //发布订单评论
    public static String ADDGOODSCOMMENT = "/goods/addGoodsComment";
    //查询余额
    public static String GETBALANCE = "/user/getBalance";
    //账户余额支付
    public static String ORDERPAY = "/pay/orderPay";
    //支付宝支付
    public static String ORDERPAYALI = "/pay/orderPayAli";
    //微信支付
    public static String ORDERPAYWX = "/pay/orderPayWX";
    //获取余额明细接口
    public static String GETWALLETLIST = "/user/getWalletList";
    //查询店铺信息
    public static String FETSHOPINFOBYSID = "/shopInfo/getShopInfoBySid";
    //添加商品浏览足迹
    public static String ADDBROWSINGHISTORY = "/goods/addBrowsingHistory";
    //订单详情
    public static String SELECTORDERINFO = "/order/selectOrderInfo";
    //店铺关注 已关注/未关注
    public static String UPDATESHOPFOLLOW = "/shopUserFollowInfo/updateShopFollow";
    //商品关注 已关注/未关注
    public static String UPDSTEGOODSFOLLOW = "/goods/updateGoodsFollow";
    //获取融云token
    public static String GETRONGCLOUDTOKEN = "/user/getRongcloudtoken";
    //搜索店铺
    public static String GETSHOPINFOLISTBYKEY = "/shopInfo/getShopInfoListByKey";
    //加入融云群组
    public static String JOINGROUP = "/rongcloud/joinGroup";
    //根据商品名字模糊查询商品
    public static String SELECTGOODSBYNAME = "/goods/selectGoodsByName";
    //根据店铺id获取该店铺下商品
    public static String SELECTGOODSBYSID = "/goods/selectGoodsBySid";
    //查询关注商品
    public static String GETFOLLOWGOODS = "/goods/getFollowGoods";
    //查询关注店铺
    public static String GETFOLLOWSHOP = "/shopUserFollowInfo/getFollowShop";
    //获取出价列表
    public static String GETBIDDINLIST = "/goods/getBiddinList";
    //新增出价记录
    public static String ADDBIDDIN = "/goods/addBiddin";
    //签到
    public static String DOSIFN = "/user/doSign";
    //获取签到详情
    public static String GETSIFNINFO = "/user/getSignInfo";
    //获取我发布的商品或拍品
    public static String GETMYAUCTION = "/goods/getMyAuction";
    //获取专区列表
    public static String GETSPECISLGOODS = "/specialevent/getSpecialGoods";
    //查询我参加的拍品
    public static String GETPARYAKEAUCTION = "/goods/getPartakeAuction";
    //修改商品上架或下架
    public static String UPDATEISSOLDOYT = "/goods/updateIssoldout";
    //发起退款
    public static String ADDREFUNDINFO = "/refund/addRefundInfo";
    //提现
    public static String ADDDWITHDRAWCASHINFO = "/user/addWithdrawCashInfo";
    //获取退货申请列表
    public static String GETREFUNDINFOLIST = "/refund/getRefundInfoList";
    //获取退款详情
    public static String GETREFUNDINFO = "/refund/getRefundInfo";
    //捡漏
    public static String GET_LEAK_COLLECTION = BASE_URL + "/goods/getLeakCollection";
    //拒绝退货
    public static String REFUSE = "/refund/reFuse";
    //退款申请通过
    public static String ADOPT = "/refund/aDopt";
    //获取保证金详情
    public static String GETBONDINFO = "/user/getBondInfo";
    //支付保证金
    public static String PAYBOND = "/pay/payBond";
    //帮助中心
    public static String GETHELPCENTERTYPE = "/user/getHelpCenterType";
    //获取推广服务接口
    public static String GETEXTENDLIST = "/extend/getExtendList";
    //获取店铺的推广服务
    public static String GETEXTENDSHOP = "/extend/getExtendShop";
    //购买服务接口
    public static String BUYEXTENDSERVICE = "/extend/buyExtendService";
    //获取所有优惠劵
    public static String GETALLCOUPONBUYER = "/coupon/getAllCouponBuyer";
    //获取卖家的优惠劵
    public static String GETALLCOUPONSELLERSHOP = "/coupon/getAllCouponSellerShop";
    //发布优惠劵
    public static String ADDCOUPONBYER = "/coupon/addCouponBuyer";
    //领取优惠劵
    public static String ADDCOUPONBUYERUSER = "/coupon/addCouponBuyerUser";
    //用户查询领取的优惠劵
    public static String GETALLCOUPONBUYERUSER = "/coupon/getAllCouponBuyerUser";
    //填写邀请码
    public static String ADDINVITATION = "/user/addInvitation";
    //我邀请得列表
    public static String GETINVITATIONLIST = "/user/getInvitationList";
    //获取版本号
    public static String GETAPPVERSION = "/user/getAppVersion";
    //获取后台通知的消息
    public static String GETALLMESSAGE = "/message/getAllMessage";
    //设置委托出价
    public static String ADDENTRUST = "/goods/addEntrust";
    //修改商品信息
    public static String UPDATEGOODS = "/goods/updateGoods";
    //店铺报表
    public static String GETREPORTFORM = "/shopInfo/getReportForm";
    //修改店铺信息
    public static String UPDATESHOPINFO = "/shopInfo/updateShopInfo";

}
