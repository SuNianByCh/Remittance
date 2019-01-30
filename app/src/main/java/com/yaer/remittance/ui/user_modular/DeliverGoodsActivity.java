package com.yaer.remittance.ui.user_modular;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ExpressInformationBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GetJsonDataUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 发货界面
 */

public class DeliverGoodsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_deliver_goods)
    CustomTitlebar ct_deliver_goods;
    @BindView(R.id.tv_express_selection)
    TextView tv_express_selection;
    @BindView(R.id.tv_express_selection_name)
    TextView tv_express_selection_name;
    private String code;
    List<String> listname = new ArrayList<>();
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    //获取分类集合
    private List<ExpressInformationBean> expressInformationBeans = new ArrayList<>();
    @BindView(R.id.button_eeliver_goods)
    TextView button_eeliver_goods;
    @BindView(R.id.et_courier_number)
    MyEditText et_courier_number;
    private String oid;//订单id

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_deliver_goods).init();
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            oid = getIntent().getStringExtra("oid");
        }
        ct_deliver_goods.setAction(this);
        getGoodsClassIFicationModels();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_deliver_goods;
    }

    @OnClick({R.id.tv_express_selection, R.id.button_eeliver_goods})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_eeliver_goods:
                if (!AppUtile.isEditText(et_courier_number)) {
                    ToastUtils.showToast("快递单号不能为空");
                } else if (!AppUtile.isEmpty(tv_express_selection_name)) {
                    ToastUtils.showToast("请选择快递公司");
                } else {
                    DeliverGoods(oid, et_courier_number.getText().toString().trim(), tv_express_selection_name.getText().toString().trim());
                }
                break;
            case R.id.tv_express_selection:
                showPickerView(v, listname);
                break;
        }
    }

    /**
     * 确认发货*
     */
    public void DeliverGoods(String oid, String otrackingnumber, String otrackingname) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELIVERGOODS)
                .tag(this)
                .params("oid", oid)
                .params("otrackingnumber", otrackingnumber)
                .params("otrackingname", otrackingname)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("确认发货成功");
                            finish();
                        } else {
                            ToastUtils.showShort(DeliverGoodsActivity.this, response.body().msg);
                        }
                    }
                });
    }
    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(View view, List<String> listname) {
        //监听选中
        if (pvOptions == null) {
                pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_express_selection_name.setText(expressInformationBeans.get(options1).getName());
                    code = expressInformationBeans.get(options1).getCode();
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setTitleText("选择快递")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(false)//点击背的地方不消失
                    .build();//创建
            //把数据绑定到控件上面
            pvOptions.setPicker(listname);
            //展示
            pvOptions.show(view);
        } else {
            pvOptions.show(view);
        }
    }

    /**
     * 获取快递分类
     */
    private void getGoodsClassIFicationModels() {
        String JsonData = new GetJsonDataUtil().getJson(context, "express.json");//获取assets目录下的json文件数据
        expressInformationBeans = parseData(JsonData); //用Gson 转成实体
        for (int i = 0; i < expressInformationBeans.size(); i++) {
            listname.add(expressInformationBeans.get(i).getName());
        }
    }

    public ArrayList<ExpressInformationBean> parseData(String result) {//Gson 解析
        ArrayList<ExpressInformationBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                ExpressInformationBean entity = gson.fromJson(data.optJSONObject(i).toString(), ExpressInformationBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
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
