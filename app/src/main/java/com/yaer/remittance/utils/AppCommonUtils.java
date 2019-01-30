package com.yaer.remittance.utils;

/**
 * Created by min on 2017/7/13.
 */
public class AppCommonUtils {
    private static long lastClickTime;
    //两次点击时间相隔小于900ms
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 900) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
   /* //添加跳转指定的activity false表示不需要登陆，true 需要登录
    public static Map<String,TvMode> getMapInfoDate(){
        Map<String,TvMode> classMap=new HashMap<>();
        classMap.put("patternlist",new TvMode(ShopInfoActivity.class,false));  //兼容
        classMap.put("patterndetail",new TvMode(StyleInfoActivity.class,false)); //无
        classMap.put("smallorder",new TvMode(SmallFastActivity.class,false)); //兼容
        classMap.put("myworksclothes",new TvMode(MyWorkActivity.class,true)); //无
        classMap.put("purposelist",new TvMode(SmallFastShopFormActivity.class,true)); //无
        classMap.put("orderlist",new TvMode(AllOrderActivity.class,true));  //无
        classMap.put("shoppingcar",new TvMode(StyleInfoGoCar.class,true)); //无
        classMap.put("wallet",new TvMode(MyBalanceActivity.class,true));//无
        classMap.put("coupon",new TvMode(CouponActivity.class,true)); //无
        classMap.put("collect",new TvMode(ShouCangActivity.class,true)); //无
        classMap.put("bill",new TvMode(FaPiaoActivity.class,true));//无
        classMap.put("steward",new TvMode(ServiceHomeActivity.class,true));//无
        classMap.put("adderss",new TvMode(UserAdressActivity.class,true));//无
        classMap.put("recharge",new TvMode(RechargeActivity.class,true));//无
        classMap.put("zxing",new TvMode(CaptureActivity.class,false));//无

        return classMap;
    }*/
}
