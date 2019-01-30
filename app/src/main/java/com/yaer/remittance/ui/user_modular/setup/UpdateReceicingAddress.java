package com.yaer.remittance.ui.user_modular.setup;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.yaer.remittance.bean.JsonBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GetAddressUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ContainsEmojiEditText;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 修改收货地址
 */

public class UpdateReceicingAddress extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_up_receiving_address)
    CustomTitlebar ct_up_receiving_address;
    /*获取地址*/
    @BindView(R.id.ed_up_address)
    TextView ed_up_address;
    /*详细地址*/
    @BindView(R.id.ed_detailed_up_address)
    ContainsEmojiEditText ed_detailed_up_address;
    /*收货人姓名*/
    @BindView(R.id.ed_consignee_up_name)
    ContainsEmojiEditText ed_consignee_up_name;
    /*收货人手机号*/
    @BindView(R.id.ed_consignee_up_phone)
    EditText ed_consignee_up_phone;
    private GetAddressUtile mAddressUtile;
    private OptionsPickerView pvOptions;
    private int sheng_id = 0;//省
    private int shi_id = 0;//市
    private int qu_id = 0;//区
    private String sheng = "";
    private String shi = "";
    private String qu = "";
    private String ssq;
    private String ProvincialUrbanArea;   /*省市区赋值*/
    //private String acity;//城市
    private String asubdistrict;//街道如（西三旗）
    private String adesc;//详细地址
    private String aphone;/*手机号*/
    private String aname;/*名称*/
    private String aid;//地址id

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_up_receiving_address).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_update_receiving_address;
    }

    @Override
    public void initView() {
        aid = getIntent().getStringExtra("aid");//地址id
        ProvincialUrbanArea = getIntent().getStringExtra("acity");//城市
        asubdistrict = getIntent().getStringExtra("asubdistrict");//街道如（西三旗）
        adesc = getIntent().getStringExtra("adesc");//详细地址
        aphone = getIntent().getStringExtra("aphone");  /*手机号*/
        aname = getIntent().getStringExtra("aname");/*名称*/
        ed_consignee_up_name.setText(aname);
        ed_consignee_up_phone.setText(aphone);
        ed_up_address.setText(ProvincialUrbanArea);
        ed_detailed_up_address.setText(adesc);
        mAddressUtile = new GetAddressUtile(this);
        ct_up_receiving_address.setAction(this);
        AppUtile.setViewOnTouch(context, ed_up_address, ed_detailed_up_address);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.ed_up_address})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_up_address:
                if (mAddressUtile.isLoaded()) {
                    ShowPickerView(v, mAddressUtile.getOptions1Items(), mAddressUtile.getOptions2Items(),
                            mAddressUtile.getOptions3Items());
                } else {
                    ToastUtils.showShort(context, "信息获取失败");
                }
                break;
        }
    }

    private void ShowPickerView(View view, final ArrayList<JsonBean> options1Items, final ArrayList<ArrayList<JsonBean.City>> options2Items
            , final ArrayList<ArrayList<ArrayList<JsonBean.CityBean>>> options3Items) {// 弹出选择器
        if (pvOptions == null) {
             pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    sheng_id = options1Items.get(options1).getId();
                    shi_id = options2Items.get(options1).get(options2).getId();
                    qu_id = options3Items.get(options1).get(options2).get(options3).getId();
                    sheng = options1Items.get(options1).getPickerViewText();
                    shi = options2Items.get(options1).get(options2).getPickerViewText();
                    qu = options3Items.get(options1).get(options2).get(options3).getPickerViewText();
                    ssq = sheng + shi + qu;
                    ed_up_address.setText(ssq);
                   /* TextView tvAddress = (TextView) v;
                    tvAddress.setText(ssq);*/
                }
            })
                    .setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(false)// default is true
                    .build();
            pvOptions.setPicker(options1Items, options2Items, options3Items);
            pvOptions.show(view);
        } else {
            pvOptions.show(view);
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                aname = ed_consignee_up_name.getText().toString().trim();
                aphone = ed_consignee_up_phone.getText().toString().trim();
                adesc = ed_detailed_up_address.getText().toString().trim();
                ProvincialUrbanArea = ed_up_address.getText().toString().trim();
                if (!AppUtile.isEditText(ed_consignee_up_name)) {
                    ToastUtils.showShort(context, "收货人姓名不能为空！");
                } else if (!AppUtile.isEditText(ed_consignee_up_phone)) {
                    ToastUtils.showShort(context, "收货人联系方式不能为空！");
                } else if (!AppUtile.isEmpty(ed_up_address)) {
                    ToastUtils.showShort(context, "收货人所在地区不能为空！");
                } else if (!AppUtile.isEditText(ed_detailed_up_address)) {
                    ToastUtils.showShort(context, "收货人详细地址不能为空！");
                } else {
                    GetupdateAddress(aname, aphone, adesc, ProvincialUrbanArea);
                }
                break;
        }
    }

    /**
     * 修改收货地址*
     * .params("uid", uid)
     * .params("utoken", utoken)
     * .params("actiy", ssq)
     * .params("asubdistrict", "123")
     * .params("adesc", adesc)
     * .params("aisdefault", isaddress)
     * .params("aphone", phone)
     * .params("aname", name)
     */
    public void GetupdateAddress(String names, String phones, String adescs, String ProvincialUrbanArea) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEUADDRESS)
                .tag(this)
                .params("aid", aid)
                .params("acity", ProvincialUrbanArea)
                .params("asubdistrict", asubdistrict)
                .params("adesc", adescs)
                .params("aphone", phones)
                .params("aname", names)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UpdateReceicingAddress.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(UpdateReceicingAddress.this, response.body().msg);
                        }
                    }
                });
    }
}
