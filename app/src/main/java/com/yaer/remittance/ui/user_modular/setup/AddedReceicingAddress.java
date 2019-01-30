package com.yaer.remittance.ui.user_modular.setup;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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
 * 新增收货地址
 */

public class AddedReceicingAddress extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_add_receiving_address)
    CustomTitlebar ct_add_receiving_address;
    @BindView(R.id.mSwitch)
    Switch mSwitch;
    /*获取地址*/
    @BindView(R.id.ed_address)
    TextView ed_address;
    /*详细地址*/
    @BindView(R.id.ed_detailed_address)
    ContainsEmojiEditText ed_detailed_address;
    /*收货人姓名*/
    @BindView(R.id.ed_consignee_name)
    ContainsEmojiEditText ed_consignee_name;
    @BindView(R.id.ed_consignee_phone)
    EditText ed_consignee_phone;
    private GetAddressUtile mAddressUtile;
    private OptionsPickerView pvOptions;
    private int sheng_id = 0;//省
    private int shi_id = 0;//市
    private int qu_id = 0;//区
    private String sheng = "";
    private String shi = "";
    private String qu = "";
    private String name, phone, utoken, ssq, adesc;
    private int uid;
    private String isaddress = "1";
    /*省市区赋值*/
    private String ProvincialUrbanArea;
    private String addresstype = "";

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_add_receiving_address).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_add_receiving_address;
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            addresstype = intent.getStringExtra("addresstype");
        }
        utoken = AppUtile.getTicket(this);
        uid = AppUtile.getUid(this);
        mAddressUtile = new GetAddressUtile(this);
        ct_add_receiving_address.setAction(this);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /*默认地址*/
                    isaddress = "1";
                } else {
                    /*不是默认地址*/
                    isaddress = "0";
                }
            }
        });
        AppUtile.setViewOnTouch(context, ed_address, ed_detailed_address);
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.ed_address})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_address:
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
                    ed_address.setText(ssq);
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
                name = ed_consignee_name.getText().toString().trim();
                phone = ed_consignee_phone.getText().toString().trim();
                adesc = ed_detailed_address.getText().toString().trim();
                ProvincialUrbanArea = ed_address.getText().toString().trim();
                if (!AppUtile.isEditText(ed_consignee_name)) {
                    ToastUtils.showShort(context, "收货人姓名不能为空！");
                } else if (!AppUtile.isEditText(ed_consignee_phone)) {
                    ToastUtils.showShort(context, "收货人联系方式不能为空！");
                } else if (!AppUtile.isEmpty(ed_address)) {
                    ToastUtils.showShort(context, "收货人所在地区不能为空！");
                } else if (!AppUtile.isEditText(ed_detailed_address)) {
                    ToastUtils.showShort(context, "收货人详细地址不能为空！");
                } else {
                    GetAddress(name, phone, adesc, ProvincialUrbanArea);
                }
                break;
        }
    }

    /**
     * 添加收货地址*
     * .params("uid", uid)
     * .params("utoken", utoken)
     * .params("actiy", ssq)
     * .params("asubdistrict", "123")
     * .params("adesc", adesc)
     * .params("aisdefault", isaddress)
     * .params("aphone", phone)
     * .params("aname", name)
     */
    public void GetAddress(String names, String phones, String adescs, String ProvincialUrbanArea) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.RECEIVINGAdDRESS)
                .tag(this)
                .params("uid", uid)
                .params("utoken", utoken)
                .params("acity", ProvincialUrbanArea)
                .params("asubdistrict", "123")
                .params("adesc", adescs)
                .params("aisdefault", isaddress)
                .params("aphone", phones)
                .params("aname", names)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Intent intent = new Intent();
                            intent.putExtra("seccs", "成功");
                            ToastUtils.showShort(AddedReceicingAddress.this, response.body().msg);
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            ToastUtils.showShort(AddedReceicingAddress.this, response.body().msg);
                        }
                    }
                });
    }
}
