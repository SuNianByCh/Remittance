package com.yaer.remittance.ui.shopping_modular.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.DefaultAddressBean;
import com.yaer.remittance.bean.OrdergoodsBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ComFirmOrderBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsAndShopInfo;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.GoodsBean;
import com.yaer.remittance.ui.shopping_modular.shoppingcart.bean.ShopCartBeanDaoUtil;
import com.yaer.remittance.ui.user_modular.setup.AddedReceicingAddress;
import com.yaer.remittance.ui.user_modular.setup.UserReceivingAddressActivity;
import com.yaer.remittance.ui.user_modular.setup.UserSetPwdActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/18.
 * 购物车确认订单
 */
public class ConfirmOrderActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_confirm_order)
    CustomTitlebar ct_confirm_order;
    /*修改收货地址*/
    @BindView(R.id.ll_receiving_address)
    LinearLayout ll_receiving_address;
    /*添加收货地址*/
    @BindView(R.id.rl_address)
    RelativeLayout rl_address;
    /*合计金额*/
    @BindView(R.id.tv_total_confirm_price)
    TextView tv_total_confirm_price;
    /*提交订单*/
    @BindView(R.id.btn_go_confirm_pay)
    Button btn_go_confirm_pay;
    /*确认订单列表*/
    @BindView(R.id.elv_shopping_car)
    RecyclerView recycler_confirm;
    private String aid;
    @BindView(R.id.tv_confirm_order_address)
    TextView tv_confirm_order_address;
    @BindView(R.id.tv_confirm_order_address_name)
    TextView tv_confirm_order_address_name;
    @BindView(R.id.tv_confirm_order_address_phone)
    TextView tv_confirm_order_address_phone;
    private double ototalnum;//商品价格
    private String upaypws;//密码是否为空
    public static ConfirmOrderActivity confirmOrderActivity;//购物车确认订单界面
    private OrdergoodsBean ordergoodsBean;


    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_confirm_order).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initView() {
        confirmOrderActivity = this;
        ct_confirm_order.setAction(this);
        getDefaultaddress();
        getBalace();
        btn_go_confirm_pay.setText(getString(R.string.go_submission_order, 0));
        tv_total_confirm_price.setText(getString(R.string.rmb_X, 0.00));
        upaypws = (String) SharedPreferencesUtils.getData(this, "ishaveUpaypassword", "");//用户支付密码
        recycler_confirm.setLayoutManager(new LinearLayoutManager(this));
        if (goodsAndShopInfo != null) {
            ConfirmOrderAdapter mAdapter = new ConfirmOrderAdapter(goodsAndShopInfo.getComFirmOrderBeanList());
            recycler_confirm.setAdapter(mAdapter);
            tv_total_confirm_price.setText(String.format(getResources().getString(R.string.rmb_X), goodsAndShopInfo.getTotalPrice()));
            btn_go_confirm_pay.setText(String.format(getResources().getString(R.string.go_submission_order), goodsAndShopInfo.getTotalCount()));
        }
        //生成订单实体类
        ordergoodsBean = new OrdergoodsBean();
        ototalnum = goodsAndShopInfo.getTotalPrice();
        //把你的地址设置上去呗，页面上
    }

    private GoodsAndShopInfo goodsAndShopInfo = null;

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            goodsAndShopInfo = (GoodsAndShopInfo) bundle.getSerializable("GoodsAndShopInfo");
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    private int Amountmoney;

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
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            String string = (String) response.body().result;
                            String str = string.substring(0, string.indexOf("."));
                            Amountmoney = Integer.parseInt(str);//int.parse(STRING);//Convert.ToInt32(Convert.ToDecimal(string));
                        } else {
                            ToastUtils.showShort(ConfirmOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @OnClick({R.id.rl_address, R.id.btn_go_confirm_pay, R.id.ll_receiving_address})
    public void onClick(View v) {
        switch (v.getId()) {
            /*添加收货地址*/
            case R.id.rl_address:
                Intent intent1 = new Intent(ConfirmOrderActivity.this, AddedReceicingAddress.class);
                intent1.putExtra("addresstype", "1");
                // 第二个参数是请求码，只要是一个唯一值
                startActivityForResult(intent1, 21);
                break;
            /*修改收货地址*/
            case R.id.ll_receiving_address:
                Intent intent = new Intent(ConfirmOrderActivity.this, UserReceivingAddressActivity.class);
                intent.putExtra("order", "1");
                // 第二个参数是请求码，只要是一个唯一值
                startActivityForResult(intent, 20);
                break;
            case R.id.btn_go_confirm_pay: {
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前无网络请链接网络");
                } else if (AppUtile.getTicket(this).equals("")) {
                    goToActivity(LoginActivity.class, "type", "5");
                } else {
                    //设置用户id
                    ordergoodsBean.setUid(AppUtile.getUid(this));
                    //设置订单金额
                    ordergoodsBean.setOtotalvalue(goodsAndShopInfo.getTotalPrice());
                    //设置地址
                    ordergoodsBean.setAid(aid);
                    //设置商品信息，信息不一样，需要自己构建
                    List<OrdergoodsBean.ShoplistBean> shoplist = new ArrayList<>();
                    ordergoodsBean.setShoplist(shoplist);
                    for (ComFirmOrderBean comFirmOrderBean : goodsAndShopInfo.getComFirmOrderBeanList()) {
                        OrdergoodsBean.ShoplistBean shoplistBean = new OrdergoodsBean.ShoplistBean();
                        //设置对商家说的话
                        shoplistBean.setOsleaveword(comFirmOrderBean.getBuyerBean().getLeaving());
                        //设置店铺id,是这个吗？对
                        shoplistBean.setSid(comFirmOrderBean.getShopBean().getShop_sid());
                        //设置商品
                        List<OrdergoodsBean.ShoplistBean.GoodslistBean> goodslist = new ArrayList<>();
                        shoplistBean.setGoodslist(goodslist);
                        for (GoodsBean goodsBean : comFirmOrderBean.getGoodsBeanList()) {
                            OrdergoodsBean.ShoplistBean.GoodslistBean goodslistBean = new OrdergoodsBean.ShoplistBean.GoodslistBean();
                            //设置商品id
                            goodslistBean.setGid(goodsBean.getGid());
                            //设置金额，总的，还是啥？？这个不是总的，就是这个商品的价格，外面有一个总价了
                            goodslistBean.setGmoney(goodsBean.getGoods_price());
                            //设置购买数量，这个吗,应该是这个，这个，是传过来的库存数量，那个是全部库存0.0
                            goodslistBean.setOgnumber(goodsBean.getGoods_amount());
                            goodslist.add(goodslistBean);
                        }
                        shoplist.add(shoplistBean);
                    }
                    if (aid.equals("") && aid.equals(null)) {
                        ToastUtils.showToast("请设置收货地址");
                    } else if (Amountmoney == 0) {
                        showDelDialog2();//您的余额不足
                    } else if (Amountmoney <= ototalnum) {
                        showDelDialog2();//你的余额少于购买金额请去充值
                    } else if (upaypws.equals("0")) {
                        showDelDialog1();
                    } else {
                        Addorder(new Gson().toJson(ordergoodsBean));
                        Log.d("打印出来看看", new Gson().toJson(ordergoodsBean));
                    }
                }
                break;
            }
        }
    }

    /*提示跳转到设置支付密码界面设置支付密码*/
    private void showDelDialog2() {
        final UIAlertView delDialog = new UIAlertView(ConfirmOrderActivity.this, "温馨提示", "您的余额不足，请充值",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           goToActivity(RechargeActivity.class);
                                           delDialog.dismiss();
                                       }
                                   }
        );
    }

    /*提示跳转到设置支付密码界面设置支付密码*/
    private void showDelDialog1() {
        final UIAlertView delDialog = new UIAlertView(ConfirmOrderActivity.this, "温馨提示", "您还没有设置支付密码",
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
                                           /*Intent intent1 = new Intent(ConfirmationOrderActivity.this, UserSetPwdActivity.class);
                                           intent1.putExtra("pwd", "1");
                                           // 第二个参数是请求码，只要是一个唯一值
                                           startActivityForResult(intent1, 23);*/
                                           goToActivity(UserSetPwdActivity.class);
                                       }
                                   }
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 判断请求码和返回码是不是正确的，这两个码都是我们自己设置的
        switch (requestCode) {
            case 20:
                if (resultCode == RESULT_OK) {
                    //接收对象
                    aid = data.getStringExtra("aid");
                    String name = data.getStringExtra("aname");
                    String aphone = data.getStringExtra("aphone");
                    String acity = data.getStringExtra("acity");
                    String adesc = data.getStringExtra("adesc");
                    tv_confirm_order_address_name.setText(name);
                    tv_confirm_order_address_phone.setText(aphone);
                    tv_confirm_order_address.setText(acity + " " + adesc);
                }
                break;
            case 21:
                if (resultCode == RESULT_OK) {
                    String seccs = data.getStringExtra("seccs");
                    getDefaultaddress();
                }
                break;
            default:
        }
    }

    private String jsonString;

    /**
     * 新增订单*
     *
     * @param ordergoods
     */
    public void Addorder(String ordergoods) {
        OkGo.<BaseMode<List<String>>>post(AppApi.BASE_URL + AppApi.ADDORDER)
                .tag(this)
                .params("orderdata", ordergoods)
                .execute(new JsonCallback<BaseMode<List<String>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<String>>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            List<String> re = response.body().result;
                            for (int i = 0; i < re.size(); i++) {
                                jsonString = re.get(i);
                            }
                            /*删除数据库里面的数据*/
                            for (ComFirmOrderBean comFirmOrderBean : goodsAndShopInfo.getComFirmOrderBeanList()) {
                                for (GoodsBean goodsBean : comFirmOrderBean.getGoodsBeanList()) {
                                    ShopCartBeanDaoUtil.deleteGoodsById(goodsBean.getGid(), comFirmOrderBean.getShopBean().getShop_sid());
                                }
                            }
                            showpayydialog(jsonString);
                        } else if (response.body().code.equals("303")) {
                            ToastUtils.showShort(ConfirmOrderActivity.this, "库存不足");
                        } else {
                            ToastUtils.showShort(ConfirmOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /*跳转支付界面*/
    private void showpayydialog(String onumber) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("onumber", onumber);//订单编号
        bundle.putDouble("ototalvalue", ototalnum);//订单金额
        bundle.putString("payftype", "4");//提交订单传的购物车状态
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getSupportFragmentManager(), "payFragment");
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
                            tv_confirm_order_address.setText(response.body().result.getAcity() + "-" + response.body().result.getAdesc());
                            tv_confirm_order_address_name.setText(response.body().result.getAname());
                            tv_confirm_order_address_phone.setText(response.body().result.getAphone());
                        } else if (response.body().code.equals(Constant.TWOZEROTWO)) {
                        } else {
                            ToastUtils.showShort(ConfirmOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
