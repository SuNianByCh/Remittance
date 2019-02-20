package com.yaer.remittance.ui.user_modular.user_buyer.integral;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ExtendListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 购买服务
 */

public class UserSellerExtensionDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_extension_details)
    CustomTitlebar ct_extension_details;
    private String edesc, ename;
    @BindView(R.id.tv_extension_name)
    TextView tv_extension_name;
    @BindView(R.id.tv_extension_edesc)
    TextView tv_extension_edesc;
    @BindView(R.id.rl_length_purchase)
    RelativeLayout rl_length_purchase;
    /*支付方式*/
    @BindView(R.id.rl_payment_method)
    RelativeLayout rl_payment_method;
    @BindView(R.id.tv_cost)
    TextView tv_cost;
    private List<ExtendListBean.ExtendMoneyInfoModelListBean> extendMoneyInfoModelListBeans;
    List<String> listname = new ArrayList<>();
    List<String> listdetails = new ArrayList<>();
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    @BindView(R.id.tv_purchase)
    TextView tv_purchase;
    @BindView(R.id.term_validity)
    TextView term_validity;
    @BindView(R.id.tv_extension_paoy)
    TextView tv_extension_paoy;
    private String emid, emoney;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_extension_details).init();
    }

    private String gmsj;
    private String zffs;
    SimpleDateFormat sf;
    Date date;
    Calendar c;

    @Override
    public void initView() {
        ct_extension_details.setAction(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            extendMoneyInfoModelListBeans = (List<ExtendListBean.ExtendMoneyInfoModelListBean>) getIntent().getSerializableExtra("extendmoneyinfomode");
            edesc = getIntent().getStringExtra("edesc");
            ename = getIntent().getStringExtra("ename");
        }
        for (int i = 0; i < extendMoneyInfoModelListBeans.size(); i++) {
            listname.add(extendMoneyInfoModelListBeans.get(i).getEmday() + "天" + "-" + extendMoneyInfoModelListBeans.get(i).getEmoney() + "元");
            gmsj = extendMoneyInfoModelListBeans.get(0).getEmday();
            zffs = extendMoneyInfoModelListBeans.get(0).getEintegral();
            sf = new SimpleDateFormat("MM-dd");
            date = new Date(System.currentTimeMillis());
            c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(extendMoneyInfoModelListBeans.get(0).getEmday()));
        }
        tv_purchase.setText(gmsj + "天");
        term_validity.setText(sf.format(date) + "至" + sf.format(c.getTime()));
        tv_cost.setText("积分兑换：￥" + zffs + "积分");
        tv_extension_edesc.setText(edesc.toString());
        tv_extension_name.setText(ename.toString());
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_extension_details;
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

    @OnClick({R.id.rl_length_purchase, R.id.tv_extension_paoy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_length_purchase:
                showPickerView(v, listname);
                break;
            case R.id.tv_extension_paoy:
                if (!AppUtile.isEmpty(tv_purchase)) {
                    ToastUtils.showToast("请选择购买时长");
                } else {
                    Buyextendservice();
                }
                break;
        }
    }

    /**
     * 购买服务接口
     */
    private void Buyextendservice() {
        int sid = AppUtile.getUid(this);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.BUYEXTENDSERVICE)
                .tag(this)
                .params("emid", emid)
                .params("sid", sid)
                .params("state",2)
                .params("emoney", emoney)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "购买服务接口: " + response.body().code);
                        Log.e("text", "购买服务接口: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserSellerExtensionDetailsActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UserSellerExtensionDetailsActivity.this, response.body().msg);
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
                    tv_purchase.setText(extendMoneyInfoModelListBeans.get(options1).getEmday() + "天");
                    tv_cost.setText("积分兑换：￥" + extendMoneyInfoModelListBeans.get(options1).getEintegral()+"积分");
                    SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    Calendar c = Calendar.getInstance();
                    emid = String.valueOf(extendMoneyInfoModelListBeans.get(options1).getEmid());
                    emoney = String.valueOf(extendMoneyInfoModelListBeans.get(options1).getEintegral());
                    c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(extendMoneyInfoModelListBeans.get(options1).getEmday()));
                    term_validity.setText(sf.format(date) + "至" + sf.format(c.getTime()));
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
}
