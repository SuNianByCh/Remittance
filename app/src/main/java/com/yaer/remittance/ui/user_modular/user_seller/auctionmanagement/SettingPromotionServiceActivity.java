package com.yaer.remittance.ui.user_modular.user_seller.auctionmanagement;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ViewPagerAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.extensioncenter.ExtendShopBean;
import com.yaer.remittance.ui.user_modular.user_seller.extensioncenter.UserExtensionCenterActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.WsbPopUtile;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.bar.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置推广服务
 */
public class SettingPromotionServiceActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_setting_promotion_service)
    CustomTitlebar ct_setting_promotion_service;
    /*商品图片*/
    @BindView(R.id.iv_setting_service)
    ImageView iv_setting_service;
    /*商品名称*/
    @BindView(R.id.tv_setting_servicetv_gname)
    TextView tv_setting_servicetv_gname;
    /*商品价格*/
    @BindView(R.id.tv_setting_servicetv_pricenewmayer)
    TextView tv_setting_servicetv_pricenewmayer;
    /*商品库存*/
    @BindView(R.id.tv_setting_servicetv_shop_danwei)
    TextView tv_setting_servicetv_shop_danwei;
    /*商品服务*/
    @BindView(R.id.rl_setting_promotion_purchase)
    RelativeLayout rl_setting_promotion_purchase;
    /*区分拍品商品*/
    @BindView(R.id.tv_glatestbid)
    TextView tv_glatestbid;
    /*推广确定按钮*/
    @BindView(R.id.tv_setting_promotion_submit)
    TextView tv_setting_promotion_submit;
    /*选择服务*/
    @BindView(R.id.tv_purchase)
    TextView tv_purchase;
    private List<ExtendShopBean> extendListBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_setting_promotion_service).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_setting_promotion_service;
    }

    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    private String gid;//商品id
    private String glatestbid;//起拍价格
    private int gnumber;//库存
    private String gmoney;//金额
    private int gisauction;//是否是商品还是拍品
    private String gname;//商品名称
    private String selImageList; //当前选择的所有图片
    private String sid;//店铺id
    ArrayList<String> list;
    String Images;
    List<String> listname = new ArrayList<>();

    @Override
    public void initView() {
        ct_setting_promotion_service.setAction(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gid = getIntent().getStringExtra("gid");//商品id
            glatestbid = getIntent().getStringExtra("glatestbid");//起拍价格
            selImageList = getIntent().getStringExtra("imageitem");//商品图片
            gnumber = getIntent().getIntExtra("gnumber", 0);//库存
            gmoney = getIntent().getStringExtra("gmoney");//金额
            gisauction = getIntent().getIntExtra("gisauction", 0);//是否是商品还是拍品
            gname = getIntent().getStringExtra("gname");//商品名称
            sid = getIntent().getStringExtra("sid");//店铺id
            list = new ArrayList<>();
            list.add(selImageList);
            for (int i = 0; i < list.size(); i++) {
                Images = list.get(i);
            }
            String[] arrayStr = new String[]{};// 字符数组
            arrayStr = Images.split(",");// 字符串转字符数组
            Glide.with(this).load(arrayStr[0]).fitCenter().into(iv_setting_service);//商品或拍品图片
            tv_setting_servicetv_gname.setText(gname);
            tv_setting_servicetv_shop_danwei.setText(gnumber + "件");
            if (gisauction == 1) {
                tv_glatestbid.setText("拍品");
                tv_setting_servicetv_pricenewmayer.setText("￥" + glatestbid);
            } else {
                tv_glatestbid.setText("商品");
                tv_setting_servicetv_pricenewmayer.setText("￥" + gmoney);
            }
        }
        getExtendList();
    }

    /**
     * 获取推广服务
     */
    private void getExtendList() {
        OkGo.<BaseMode<List<ExtendShopBean>>>post(AppApi.BASE_URL + AppApi.GETEXTENDSHOP)
                .tag(this)
                .params("sid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<List<ExtendShopBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<ExtendShopBean>>> response) {
                        Log.e("text", "获取推广服务: " + response.body().code);
                        Log.e("text", "获取推广服务: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            extendListBeans = response.body().result;
                            for (int i = 0; i < extendListBeans.size(); i++) {
                                listname.add(extendListBeans.get(i).getExtendMoneyInfoModels().getEname() + "-" + SystemUtil.getDate2String(Long.parseLong(extendListBeans.get(i).getEstime()), "yyyy-MM-dd"));
                            }
                        } else {
                            ToastUtils.showShort(SettingPromotionServiceActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @OnClick({R.id.tv_setting_promotion_submit, R.id.rl_setting_promotion_purchase})
    public void onClick(View v) {
        switch (v.getId()) {
            /*提交服务*/
            case R.id.tv_setting_promotion_submit:
                if (!AppUtile.isEmpty(tv_purchase)) {
                    ToastUtils.showToast("请选择服务");
                } else {
                    getUpdataGoodsSextend();
                }
                break;
            /*选择服务*/
            case R.id.rl_setting_promotion_purchase:
                showPickerView(v, listname);
                break;
        }
    }

    private int esid;

    /**
     * 修改推广服务
     */
    private void getUpdataGoodsSextend() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEGOODSEXTEND)
                .tag(this)
                .params("esid", esid)
                .params("gid", gid)//商品id
                .params("sid", sid)//店铺id
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "修改推广服务: " + response.body().code);
                        Log.e("text", "修改推广服务: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            //ToastUtils.showShort(SettingPromotionServiceActivity.this, response.body().msg);
                            ToastUtils.showShort(SettingPromotionServiceActivity.this, "设置服务成功");
                            finish();
                        } else {
                            ToastUtils.showShort(SettingPromotionServiceActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(View view, List<String> listname) {
        if (pvOptions == null) {
            pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_purchase.setText(extendListBeans.get(options1).getExtendMoneyInfoModels().getEname());
                    esid = extendListBeans.get(options1).getEsid();
                }
            })
                    .setTitleText("购买时长")
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                    .setTextColorOut(Color.BLACK)
                    .setTextColorCenter(Color.BLUE)
                    .setSelectOptions(0)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(Color.WHITE)
                    .setTitleColor(Color.LTGRAY)
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setBackgroundId(0x00000000) //设置外部遮罩颜色
                    .build();
            pvOptions.setPicker(listname);//一级选择器
            pvOptions.show();
        } else {
            pvOptions.show();
        }
    }

    @Override
    public void initData() {
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
