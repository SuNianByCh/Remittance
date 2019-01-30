package com.yaer.remittance.ui.user_modular.user_buyer.order;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/*
 * 选择服务类型*/
public class RefundReturnActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_refimd_retuen_order)
    CustomTitlebar ct_refimd_retuen_order;
    private int gid;
    private int shopid;
    private String gimg;
    private String gname;
    private double gmoney;
    private int ognumber;
    private String Ototalvalue;
    private int oid;
    /*退款商品图片*/
    @BindView(R.id.iv_redund_return_image)
    ImageView iv_redund_return_image;
    /*退款数量*/
    @BindView(R.id.tv_redund_return_number)
    TextView tv_redund_return_number;
    /*退款金额*/
    @BindView(R.id.tv_redund_return_money)
    TextView tv_redund_return_money;
    /*退款名称*/
    @BindView(R.id.tv_redund_return_gname)
    TextView tv_redund_return_gname;
    /*退货*/
    @BindView(R.id.rl_return_goods)
    RelativeLayout rl_return_goods;
    /*退款*/
    @BindView(R.id.rl_refund)
    RelativeLayout rl_refund;

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
        return R.layout.activity_refund_retuen;
    }

    @Override
    public void initView() {
        ct_refimd_retuen_order.setAction(this);
        gid = getIntent().getIntExtra("gid", 0);//退款商品id
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
        Glide.with(this).load(strs[0]).fitCenter().into(iv_redund_return_image);//订单商品图片*/
        tv_redund_return_gname.setText(gname);
        tv_redund_return_money.setText("￥" + AmountUtil.priceNum(gmoney));
        tv_redund_return_number.setText("x" + ognumber);
    }

    @OnClick({R.id.rl_return_goods,R.id.rl_refund})
    public void onClick(View v) {
        switch (v.getId()) {
            /*退货*/
            case R.id.rl_return_goods:
                Intent intent1 = new Intent(RefundReturnActivity.this, ReturnGoodsActivity.class);
                intent1.putExtra("gid", gid);//商品id
                intent1.putExtra("shopid", shopid);//店铺id
                intent1.putExtra("gname", gname);//商品名称
                intent1.putExtra("gimg", gimg);//商品图片
                intent1.putExtra("gmoney", gmoney);//商品价格
                intent1.putExtra("ognumber", ognumber);//商品库存
                intent1.putExtra("Ototalvalue", Ototalvalue);//合计退款金额
                intent1.putExtra("oid", oid);//合计退款金额
                startActivity(intent1);
                finish();
                break;
            /*退款*/
            case R.id.rl_refund:
                Intent intent = new Intent(RefundReturnActivity.this, RefundActivity.class);
                intent.putExtra("gid", gid);//商品id
                intent.putExtra("shopid", shopid);//店铺id
                intent.putExtra("gname", gname);//商品名称
                intent.putExtra("gimg", gimg);//商品图片
                intent.putExtra("gmoney", gmoney);//商品价格
                intent.putExtra("ognumber", ognumber);//商品库存
                intent.putExtra("Ototalvalue", Ototalvalue);//合计退款金额
                intent.putExtra("oid", oid);//合计退款金额
                startActivity(intent);
                finish();
                break;
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
