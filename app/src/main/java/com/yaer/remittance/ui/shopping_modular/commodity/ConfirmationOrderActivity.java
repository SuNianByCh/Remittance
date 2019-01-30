package com.yaer.remittance.ui.shopping_modular.commodity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.OptionsPickerView;
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
import com.yaer.remittance.bean.AllCouponBuyerBean;
import com.yaer.remittance.bean.DefaultAddressBean;
import com.yaer.remittance.bean.OrdergoodsBean;
import com.yaer.remittance.bean.QueryCollectAddressBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.user_modular.setup.AddedReceicingAddress;
import com.yaer.remittance.ui.user_modular.setup.UserReceivingAddressActivity;
import com.yaer.remittance.ui.user_modular.setup.UserSetPwdYzmActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/18.
 * 填写订单
 */
public class ConfirmationOrderActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    /*标题*/
    @BindView(R.id.ct_confirm_title)
    CustomTitlebar ct_confirm_title;
    /*修改收货地址*/
    @BindView(R.id.ll_receiving_address)
    LinearLayout ll_receiving_address;
    /*添加收货地址*/
    @BindView(R.id.rl_address)
    RelativeLayout rl_address;
    /*商品列表*/
    /*@BindView(R.id.recycler_confirm_shop)
    RecyclerView recycler_confirm_shop;*/
    /*合计金额*/
    @BindView(R.id.tv_total_confirm_price)
    TextView tv_total_confirm_price;
    @BindView(R.id.btn_go_confirm_pay)
    Button btn_go_confirm_pay;
    /*默认地址*/
    private List<QueryCollectAddressBean> Addresslist = new ArrayList<>();
    //收货名称
    @BindView(R.id.tv_order_name)
    TextView tv_order_name;
    /*收货手机号*/
    @BindView(R.id.tv_order_phone)
    TextView tv_order_phone;
    @BindView(R.id.tv_order_address)
    TextView tv_order_address;
    private int uid;//用户id
    private String aid = "";//地址id
    private double ototalvalue;//总价
    private String sid;//店铺id
    private String gid;//商品id
    private int ognumber;//库存数量
    private String osleaveword;//店铺留言
    private Gson gson = new Gson();
    private String ordergoods;
    private String simg;//店铺图片
    private String sname;//店铺名称
    private String gname;//商品名称
    private String gimg;//商品图片
    private int gpostage;//邮费
    private int mOrginPostMoney;//
    private int mCurretPostMoney;//
    private int initBuyNum;
    /*店铺图片*/
    @BindView(R.id.iv_order_simg)
    CircleImageView iv_order_simg;
    /*店铺名称*/
    @BindView(R.id.tv_order_sname)
    TextView tv_order_sname;
    /*商品图片*/
    @BindView(R.id.iv_commodiy_order_image)
    ImageView iv_commodiy_order_image;
    /*商品名称*/
    @BindView(R.id.tv_commodity_order_name)
    TextView tv_commodity_order_name;
    @BindView(R.id.et_confirm_message)
    MyEditText et_confirm_message;
    /*获取编辑内容*/
    private String PersonalProfile;
    private int num = 140;
    /*编辑字数统计*/
    @BindView(R.id.user_tv_count)
    TextView user_tv_count;
    //商品价格
    @BindView(R.id.tv_price)
    TextView tv_price;
    /*减号*/
    @BindView(R.id.tv_reduce)
    TextView tv_reduce;
    /*数量*/
    @BindView(R.id.tv_num)
    TextView tv_num;
    /*加号*/
    @BindView(R.id.tv_add)
    TextView tv_add;
    @BindView(R.id.tv_commodity_order_ognumber)
    TextView tv_commodity_order_ognumber;
    /*邮费*/
    @BindView(R.id.tv_gpostage)
    TextView tv_gpostage;
    /*选择优惠券*/
  /*  @BindView(R.id.tv_choice_coupon)
    TextView tv_choice_coupon;*/
    //商品价格
    private double ototalnum;
    private String upaypws;//密码是否为空
    List<String> listname = new ArrayList<>();
    public static ConfirmationOrderActivity confirmationOrderActivity;
    private List<AllCouponBuyerBean> allCouponBuyerBeanArrayList = new ArrayList<>(); // 这个是右边
    /*分类选择标签*/
    private OptionsPickerView pvOptions;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override

    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_confirm_title).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_commodity_order;
    }

    @Override
    public void initView() {
        confirmationOrderActivity = this;
        ct_confirm_title.setAction(this);
        et_confirm_message.setText(PersonalProfile);
        showeditText();
        uid = AppUtile.getUid(this);
        // getDefaultaddress();
        Intent intent = getIntent();
        if (intent != null) {
            // 获取Bundle对象中的参数
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                simg = bundle.getString("simg", "");//店铺图片
                sname = bundle.getString("sname", "");//店铺名称
                gname = bundle.getString("gname", "");//商品名称
                gimg = bundle.getString("gimg", "");//商品图片
                gpostage = bundle.getInt("gpostage", 0);//商品邮费
                mOrginPostMoney = gpostage;
                ototalvalue = bundle.getDouble("ototalvalue");
                sid = bundle.getString("sid", "");
                gid = bundle.getString("gid", "");
                ognumber = bundle.getInt("ognumber", 0);
            }
        }
        ototalnum = ototalvalue;
        tv_gpostage.setText("" + gpostage);//邮费
        tv_price.setText("￥" + ototalnum);//商品价格
        tv_total_confirm_price.setText("￥" + AmountUtil.priceNum(ototalnum += gpostage));//商品价格加邮费
        tv_commodity_order_name.setText(gname);//商品名称
        tv_commodity_order_ognumber.setText("x" + ognumber);//商品库存
        //用逗号将字符串分开，得到字符串数组
        String[] strs = gimg.split(",");
        //将字符串数组转换成集合list
        Glide.with(ConfirmationOrderActivity.this).load(strs[0]).fitCenter().into(iv_commodiy_order_image);//商品图片
        Glide.with(ConfirmationOrderActivity.this).load(simg).fitCenter().into(iv_order_simg);//买家头像
        tv_order_sname.setText(sname);
        getDefaultaddress();
        // getallcouponbuyer();
        //getBalace();
    }

    private void showeditText() {
        et_confirm_message.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            public void afterTextChanged(Editable s) {
                int number = num - s.length();
                user_tv_count.setText(s.length() + "/50");
                selectionStart = et_confirm_message.getSelectionStart();
                selectionEnd = et_confirm_message.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    et_confirm_message.setText(s);
                    et_confirm_message.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    private String moneys;

    @OnClick({R.id.ll_receiving_address, R.id.btn_go_confirm_pay, R.id.tv_add, R.id.tv_reduce, R.id.rl_address})
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.tv_choice_coupon:
                showPickerView(v, listname);
                break;*/
            case R.id.tv_add:
                int intValue2 = Integer.valueOf(tv_num.getText().toString()).intValue();
                if (intValue2 >= ognumber) {
                    ToastUtils.showToast("库存不足");
                } else {
                    intValue2++;
                    ototalnum += ototalvalue;
                    moneys = AmountUtil.priceNum(ototalnum);
                    //tv_price.setText("￥" + money);
                    tv_num.setText(String.valueOf(intValue2));
                    tv_total_confirm_price.setText("￥" + moneys);
                }
                calcPostMoney();
                break;
            case R.id.tv_reduce:
                int intValue = Integer.valueOf(tv_num.getText().toString()).intValue();
                if (intValue > 1) {
                    intValue--;
                    ototalnum -= ototalvalue;
                    moneys = AmountUtil.priceNum(ototalnum);
                    // tv_price.setText("￥" + money);
                    tv_num.setText(String.valueOf(intValue));
                    tv_total_confirm_price.setText("￥" + moneys);
                }
                calcPostMoney();
                break;
            /*添加收货地址*/
            case R.id.rl_address:
                Intent intent1 = new Intent(ConfirmationOrderActivity.this, AddedReceicingAddress.class);
                intent1.putExtra("addresstype", "1");
                // 第二个参数是请求码，只要是一个唯一值
                startActivityForResult(intent1, 21);
                break;
            /*修改收货地址*/
            case R.id.ll_receiving_address:
                Intent intent = new Intent(ConfirmationOrderActivity.this, UserReceivingAddressActivity.class);
                intent.putExtra("order", "1");
                // 第二个参数是请求码，只要是一个唯一值
                startActivityForResult(intent, 20);
                break;
            case R.id.btn_go_confirm_pay:
                osleaveword = et_confirm_message.getText().toString().trim();
                String ogbumber = tv_num.getText().toString().trim();
                OrdergoodsBean ordergoodsBean = new OrdergoodsBean();
                ordergoodsBean.setAid(aid);//地址id
                ordergoodsBean.setUid(uid);//用户id
                ordergoodsBean.setOtotalvalue(ototalnum);//ototalnum += gpostage);//金额
                List<OrdergoodsBean.ShoplistBean> shoplistBeans = new ArrayList<>();
                OrdergoodsBean.ShoplistBean shoplistBean = new OrdergoodsBean.ShoplistBean();
                shoplistBean.setOsleaveword(osleaveword);//对商家说的内容
                shoplistBean.setOpostage(String.valueOf(gpostage));//邮费
                shoplistBean.setSid(sid);//店铺id
                List<OrdergoodsBean.ShoplistBean.GoodslistBean> goodslistBeans = new ArrayList<>();
                OrdergoodsBean.ShoplistBean.GoodslistBean goodslistBean = new OrdergoodsBean.ShoplistBean.GoodslistBean();
                goodslistBean.setGid(gid);//商品id
                goodslistBean.setOgnumber(Integer.parseInt(ogbumber));//商品库存
                goodslistBean.setGmoney(ototalvalue);//商品价格
                shoplistBeans.add(shoplistBean);
                goodslistBeans.add(goodslistBean);
                ordergoodsBean.setShoplist(shoplistBeans);
                shoplistBean.setGoodslist(goodslistBeans);
                //返回结果转json
                ordergoods = gson.toJson(ordergoodsBean);
                if (aid.equals("")) {
                    ToastUtils.showToast("请设置收货地址");
                } else if (Amountmoney == 0) {
                    showDelDialog2();
                } else if (Amountmoney <= ototalnum) {
                    showDelDialog2();
                } else if (upaypws.equals("0")) {
                    showDelDialog1();
                } else {
                    Addorder(ordergoods);
                }
                break;
        }
    }


    private void calcPostMoney() {
        try {
            String s = tv_num.getText().toString();
            int num = Integer.parseInt(s.toString());
            if (num == 0) {
                mCurretPostMoney = mOrginPostMoney;
                tv_gpostage.setText("0");
            } else {
                /*这个地方是描述怎么的*/
            //    mCurretPostMoney = num >= 2 ? mOrginPostMoney *  : mOrginPostMoney;
                mCurretPostMoney = mOrginPostMoney;
                tv_gpostage.setText(("" + mCurretPostMoney));
            }
        } catch (Throwable throwable) {
        }

    }


    /**
     * 获取商品列表
     */
    private void getallcouponbuyer() {
        OkGo.<BaseMode<List<AllCouponBuyerBean>>>post(AppApi.BASE_URL + AppApi.GETALLCOUPONBUYER)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<List<AllCouponBuyerBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<AllCouponBuyerBean>>> response) {
                        Log.e("text", "商品列表: " + response.body().code);
                        Log.e("text", "商品列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            allCouponBuyerBeanArrayList = response.body().result;
                        } else {
                            ToastUtils.showShort(ConfirmationOrderActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onError(Response<BaseMode<List<AllCouponBuyerBean>>> response) {
                        super.onError(response);
                    }
                });
    }

    /*提示跳转到设置支付密码界面设置支付密码*/
    private void showDelDialog2() {
        final UIAlertView delDialog = new UIAlertView(ConfirmationOrderActivity.this, "温馨提示", "您的余额不足，请充值",
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
        final UIAlertView delDialog = new UIAlertView(ConfirmationOrderActivity.this, "温馨提示", "您还没有设置支付密码",
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
                                           goToActivity(UserSetPwdYzmActivity.class);
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
                    tv_order_name.setText(name);
                    tv_order_phone.setText(aphone);
                    tv_order_address.setText(acity + " " + adesc);
                }
                break;
            case 21:
                if (resultCode == RESULT_OK) {
                    String seccs = data.getStringExtra("seccs");
                    getDefaultaddress();
                }
                break;
            /*   case 22:
                if (resultCode == RESULT_OK) {
                    String seccs = data.getStringExtra("seccs");
                    getBalace();
                }
                break;
            case 23:
                if (resultCode == RESULT_OK) {
                    String seccs = data.getStringExtra("seccs");
                    upaypws = (String) SharedPreferencesUtils.getData(this, "upaypassword", "");
                }
                break;*/
            default:
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBalace();//查询余额
        upaypws = (String) SharedPreferencesUtils.getData(this, "ishaveUpaypassword", "");//支付密码
    }

    @Override
    public void initData() {

    }

    /**
     * 查询默认收货地址*
     */
    public void getDefaultaddress() {
        uid = AppUtile.getUid(this);
        OkGo.<BaseMode<DefaultAddressBean>>post(AppApi.BASE_URL + AppApi.GETDEFAULTADDRESS)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<DefaultAddressBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<DefaultAddressBean>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            rl_address.setVisibility(View.GONE);
                            ll_receiving_address.setVisibility(View.VISIBLE);
                            aid = response.body().result.getAid();
                            tv_order_name.setText(response.body().result.getAname());
                            tv_order_phone.setText(response.body().result.getAphone());
                            tv_order_address.setText(response.body().result.getAcity() + "" + response.body().result.getAdesc());
                        } else if (response.body().code.equals(Constant.TWOZEROTWO)) {
                            rl_address.setVisibility(View.VISIBLE);
                            ll_receiving_address.setVisibility(View.GONE);
                        } else {
                            ToastUtils.showShort(ConfirmationOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    private int Amountmoney;

    /**
     * 查询余额*
     */
    public void getBalace() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETBALANCE)
                .tag(this)
                .params("uid", uid)
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
                            ToastUtils.showShort(ConfirmationOrderActivity.this, response.body().msg);
                        }
                    }
                });
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
                            showpayydialog(jsonString);
                        } else if (response.body().code.equals("303")) {
                            ToastUtils.showShort(ConfirmationOrderActivity.this, "库存不足");
                        } else {
                            ToastUtils.showShort(ConfirmationOrderActivity.this, response.body().msg);
                        }
                    }
                });
    }

    private void showpayydialog(String onumber) {
        PayDialogFragment payDialogFragment = new PayDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("onumber", onumber);//订单编号
        bundle.putDouble("ototalvalue", ototalvalue);//订单金额
        bundle.putString("payftype", "1");//提交订单传的id
        payDialogFragment.setArguments(bundle);
        payDialogFragment.show(getSupportFragmentManager(), "payFragment");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
