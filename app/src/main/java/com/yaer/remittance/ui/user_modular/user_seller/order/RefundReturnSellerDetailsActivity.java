package com.yaer.remittance.ui.user_modular.user_seller.order;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
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

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 退款/退货详情*/
public class RefundReturnSellerDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
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
    /*同意退款*/
    @BindView(R.id.tv_agree_refund)
    TextView tv_agree_refund;
    /*拒绝退款*/
    @BindView(R.id.tv_refusal_refund)
    TextView tv_refusal_refund;
    /*退款状态*/
    @BindView(R.id.tv_ostatus_type)
    TextView tv_ostatus_type;
    ArrayList<String> list;
    String Images;
    private int rid;
    private RefundInfoListBean refundInfoListBeans;

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
        return R.layout.activity_refund_seller_details_retuen;
    }

    @Override
    public void initView() {
        ct_refimd_retuen_order.setAction(this);
        rid = getIntent().getIntExtra("rid", 0);//退款订单id
        RefundInfo(rid);
    }

    @OnClick({R.id.tv_agree_refund, R.id.tv_refusal_refund})
    public void onClick(View v) {
        switch (v.getId()) {
            /*同意退款*/
            case R.id.tv_agree_refund:
                Adopta();
                break;
            /*拒绝退款*/
            case R.id.tv_refusal_refund:
                refuse();
                break;
        }
    }

    /**
     * 同意退款
     */
    private void Adopta() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADOPT)
                .tag(this)
                .params("rid", rid)
                .execute(new JsonCallback<BaseMode>(this) {

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(RefundReturnSellerDetailsActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(RefundReturnSellerDetailsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 拒绝退款
     **/
    private void refuse() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.REFUSE)
                .tag(this)
                .params("rid", rid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(RefundReturnSellerDetailsActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(RefundReturnSellerDetailsActivity.this, response.body().msg);
                        }
                    }
                });
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
                            tv_refund_details_money.setText("￥" + refundInfoListBeans.getGmoney());
                            tv_refund_details_number.setText("x" + refundInfoListBeans.getGnumber());
                            tv_refund_details_rdesc.setText(refundInfoListBeans.getRdesc());
                            tv_refund_money.setText("退款金额：￥" + refundInfoListBeans.getGmoney());
                            int restatus = refundInfoListBeans.getRstatus();
                            if (restatus == 0) {
                                tv_agree_refund.setVisibility(View.VISIBLE);
                                tv_refusal_refund.setVisibility(View.VISIBLE);
                            } else if (restatus == 1) {
                                tv_ostatus_type.setText("退款成功");
                                tv_agree_refund.setVisibility(View.GONE);
                                tv_refusal_refund.setVisibility(View.GONE);
                            } else if (restatus == 2) {
                                tv_ostatus_type.setText("拒绝退款");
                                tv_agree_refund.setVisibility(View.GONE);
                                tv_refusal_refund.setVisibility(View.GONE);
                            }
                            list = new ArrayList<>();
                            list.add(refundInfoListBeans.getGimg());
                            for (int i = 0; i < list.size(); i++) {
                                Images = list.get(i);
                            }
                            String[] arrayStr = new String[]{};// 字符数组
                            arrayStr = Images.split(",");// 字符串转字符数组
                            Glide.with(RefundReturnSellerDetailsActivity.this).load(arrayStr[0]).fitCenter().into(iv_refund_dtails_image);//商品图片

                        } else {
                            ToastUtils.showShort(RefundReturnSellerDetailsActivity.this, response.body().msg);
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
