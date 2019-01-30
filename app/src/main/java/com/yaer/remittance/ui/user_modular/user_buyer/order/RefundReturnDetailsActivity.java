package com.yaer.remittance.ui.user_modular.user_buyer.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.RefundInfoListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 * 退款/退货详情*/
public class RefundReturnDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_refimd_retuen_order)
    CustomTitlebar ct_refimd_retuen_order;
    /*商品图片*/
    @BindView(R.id.iv_refund_dtails_image)
    ImageView iv_refund_dtails_image;
    /*商品名称*/
    @BindView(R.id.tv_refund_details_name)
    TextView tv_refund_details_name;
    /*商品金额*/
    @BindView(R.id.tv_refund_details_money)
    TextView tv_refund_details_money;
    /*商品库存*/
    @BindView(R.id.tv_refund_details_number)
    TextView tv_refund_details_number;
    /*退款原因*/
    @BindView(R.id.tv_refund_details_rdesc)
    TextView tv_refund_details_rdesc;
    /*退款金额*/
    @BindView(R.id.tv_refund_money)
    TextView tv_refund_money;
    ArrayList<String> list;
    String Images;
    private int rid;
    private RefundInfoListBean refundInfoListBeans;
    /*查看快递*/
    @BindView(R.id.tv_refund_viewexpress)
    TextView tv_refund_viewexpress;
    /*退款退货状态*/
    @BindView(R.id.tv_ostatus_type)
    TextView tv_ostatus_type;
    private String Otrackingname;
    private String Otrackingnumber;
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_refimd_retuen_order).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_refund_details_retuen;
    }

    @Override
    public void initView() {
        ct_refimd_retuen_order.setAction(this);
        rid = getIntent().getIntExtra("rid", 0);//退款订单id
        RefundInfo(rid);
    }

    /**
     * 根据用户id获取退款列表*
     */
    public void RefundInfo(int rid) {
        OkGo.<BaseMode<RefundInfoListBean>>post(AppApi.BASE_URL + AppApi.GETREFUNDINFO)
                .tag(this)
                .params("rid", rid)
                .execute(new JsonCallback<BaseMode<RefundInfoListBean>>(this) {

                    @Override
                    public void onSuccess(Response<BaseMode<RefundInfoListBean>> response) {
                        refundInfoListBeans = response.body().result;
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            tv_refund_details_name.setText(refundInfoListBeans.getGname());
                            tv_refund_details_money.setText("￥"+refundInfoListBeans.getGmoney());
                            tv_refund_details_number.setText("x"+refundInfoListBeans.getGnumber());
                            tv_refund_details_rdesc.setText(refundInfoListBeans.getRdesc());
                            tv_refund_money.setText("退款金额：￥"+refundInfoListBeans.getGmoney());
                            Otrackingname=refundInfoListBeans.getOtrackingname();
                            Otrackingnumber=refundInfoListBeans.getOtrackingnumber();
                            if (Otrackingname.equals("")){
                                tv_ostatus_type.setText("退款信息");
                            }else{
                                tv_ostatus_type.setText("退货信息");
                            }
                            list = new ArrayList<>();
                            list.add(refundInfoListBeans.getGimg());
                            for (int i = 0; i < list.size(); i++) {
                                Images = list.get(i);
                            }
                            String[] arrayStr = new String[]{};// 字符数组
                            arrayStr = Images.split(",");// 字符串转字符数组
                            Glide.with(RefundReturnDetailsActivity.this).load(arrayStr[0]).fitCenter().into(iv_refund_dtails_image);//商品图片

                        } else {
                            ToastUtils.showShort(RefundReturnDetailsActivity.this, response.body().msg);
                        }
                    }
                });
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
