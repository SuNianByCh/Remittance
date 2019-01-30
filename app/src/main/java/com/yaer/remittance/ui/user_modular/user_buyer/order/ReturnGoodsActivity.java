package com.yaer.remittance.ui.user_modular.user_buyer.order;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
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
import com.yaer.remittance.utils.AmountUtil;
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

/*
 * 申请退货*/
public class ReturnGoodsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_retuen_goods_order)
    CustomTitlebar ct_retuen_goods_order;
    @BindView(R.id.iv_commodity_order_image)
    ImageView iv_commodity_order_image;
    @BindView(R.id.tv_allorder_gname)
    TextView tv_allorder_gname;
    @BindView(R.id.tvPriceNewmayer)
    TextView tvPriceNewmayer;
    @BindView(R.id.shop_danwei)
    TextView shop_danwei;
    @BindView(R.id.tv_refund_money)
    TextView tv_refund_money;
    //退货原因
    @BindView(R.id.tv_brief_introduction)
    TextView tv_brief_introduction;
    //请选择
    @BindView(R.id.tv_usignature)
    TextView tv_usignature;
    //退货说明
    @BindView(R.id.et_refund_rdesc)
    TextView et_refund_rdesc;
    //提交
    @BindView(R.id.tv_submit_refun_order)
    TextView tv_submit_refun_order;
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    private OptionsPickerView pvExpress;
    List<String> listname = new ArrayList<>();
    private String gname;
    private String gimg;
    private double gmoney;
    private int ognumber;
    private int gid;
    private int shopid;
    private int oid;
    private String Ototalvalue;
    /*选择快递按钮*/
    @BindView(R.id.tv_express_selection)
    TextView tv_express_selection;
    /*快递名称*/
    @BindView(R.id.tv_express_selection_name)
    TextView tv_express_selection_name;
    /*快递编码*/
    private String code;
    /*快递*/
    List<String> express = new ArrayList<>();
    /*选择快递单号*/
    @BindView(R.id.et_courier_number)
    MyEditText et_courier_number;
    //获取分类集合
    private List<ExpressInformationBean> expressInformationBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_retuen_goods_order).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.retuen_goods_order;
    }

    @Override
    public void initView() {
        ct_retuen_goods_order.setAction(this);
        gid = getIntent().getIntExtra("gid", 0);//退货商品id
        shopid = getIntent().getIntExtra("shopid", 0);//退货商品 所属店铺id
        gimg = getIntent().getStringExtra("gimg");//商品图片
        gname = getIntent().getStringExtra("gname");//商品名称
        gmoney = getIntent().getDoubleExtra("gmoney", 0);//商品金额
        ognumber = getIntent().getIntExtra("ognumber", 0);//商品库存
        Ototalvalue = getIntent().getStringExtra("Ototalvalue");//退款金额
        oid = getIntent().getIntExtra("oid", 0);//订单id
        //用逗号将字符串分开，得到字符串数组
        String[] strs = gimg.split(",");
        //将字符串数组转换成集合list
        Glide.with(this).load(strs[0]).fitCenter().into(iv_commodity_order_image);//订单商品图片*/
        tv_allorder_gname.setText(gname);
        tvPriceNewmayer.setText("￥" + AmountUtil.priceNum(gmoney));
        shop_danwei.setText("x" + ognumber);
        tv_refund_money.setText("￥" + Ototalvalue);
        showresaon();
        getGoodsClassIFicationModels();
    }

    private void showresaon() {
        listname.add("拍错/多拍/不想要");
        listname.add("协商一致退款");
        listname.add("缺货");
        listname.add("未按约定时间发货");
        listname.add("其他");
    }

    @OnClick({R.id.tv_brief_introduction, R.id.tv_submit_refun_order, R.id.tv_express_selection})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_express_selection:
                showExpressView(v, express);
                break;
            case R.id.tv_brief_introduction:
                showPickerView(v, listname);
                break;
            case R.id.tv_submit_refun_order:
                if (!AppUtile.isEmpty(tv_usignature)) {
                    ToastUtils.showToast("请选择退货原因");
                } else {
                    String rdesc = tv_usignature.getText().toString().trim();
                    Addrefindinfo(rdesc);
                }
                break;
        }
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showExpressView(View view, List<String> listname) {
        if (pvExpress == null) {
            pvExpress = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_express_selection_name.setText(expressInformationBeans.get(options1).getName());
                    code = expressInformationBeans.get(options1).getCode();
                }
            })
                    .setTitleText("选择快递")
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                    .setTextColorOut(Color.BLACK)
                    .setTextColorCenter(Color.BLUE)
                    .setSelectOptions(0)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(Color.WHITE)
                    .setTitleColor(Color.LTGRAY)
                    .setCancelColor(Color.parseColor("#C2100D"))
                    .setSubmitColor(Color.parseColor("#C2100D"))
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setBackgroundId(0x00000000) //设置外部遮罩颜色
                    .build();
            pvExpress.setPicker(listname);//一级选择器
            pvExpress.show();
        }else{
            pvExpress.show(view);
        }
        /*//监听选中
        if (pvExpress == null) {
            pvExpress = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
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
            pvExpress.setPicker(listname);
            //展示
            pvExpress.show(view);
        } else {
            pvExpress.show(view);
        }*/
    }

    /**
     * 获取快递分类
     */
    private void getGoodsClassIFicationModels() {
        String JsonData = new GetJsonDataUtil().getJson(context, "express.json");//获取assets目录下的json文件数据
        expressInformationBeans = parseData(JsonData); //用Gson 转成实体
        for (int i = 0; i < expressInformationBeans.size(); i++) {
            express.add(expressInformationBeans.get(i).getName());
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

    /**
     * 发起退货
     */
    public void Addrefindinfo(String rdesc) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDREFUNDINFO)
                .tag(this)
                .params("gid", gid)
                .params("sid", shopid)
                .params("uid", AppUtile.getUid(this))
                .params("rdesc", rdesc)
                .params("gnumber", ognumber)
                .params("oid", oid)
                .params("otrackingnumber", et_courier_number.getText().toString().trim())//"802786255649040589"
                .params("otrackingname", tv_express_selection_name.getText().toString().trim())//"圆通速递"
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "申请退货: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            finish();
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }
                });
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(View view, final List<String> listname) {

        if (pvOptions == null) {
            pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_usignature.setText(listname.get(options1));
                }
            })
                    .setTitleText("退货原因")
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                    .setTextColorOut(Color.BLACK)
                    .setTextColorCenter(Color.BLUE)
                    .setSelectOptions(0)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(Color.WHITE)
                    .setTitleColor(Color.LTGRAY)
                    .setCancelColor(Color.parseColor("#C2100D"))
                    .setSubmitColor(Color.parseColor("#C2100D"))
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setBackgroundId(0x00000000) //设置外部遮罩颜色
                    .build();
            pvOptions.setPicker(listname);//一级选择器
            pvOptions.show();
        }else{
            pvOptions.show(view);
        }
        /*//监听选中
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_usignature.setText(listname.get(options1));
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setTitleText("退货原因")
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
        }*/
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
