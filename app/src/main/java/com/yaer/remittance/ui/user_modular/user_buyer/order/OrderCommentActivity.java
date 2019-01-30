package com.yaer.remittance.ui.user_modular.user_buyer.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SelectOrderInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.payment.PayDialogFragment;
import com.yaer.remittance.ui.shopping_modular.commodity.ConfirmationOrderActivity;
import com.yaer.remittance.utils.AmountUtil;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.StarBar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/3.
 * 评论界面
 */
public class OrderCommentActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_order_comment_title)
    CustomTitlebar ct_order_comment_title;
    /*商品描述*/
    @BindView(R.id.commodity_score)
    StarBar commodity_score;
    /*获取评分状态*/
    @BindView(R.id.scoring_type)
    TextView scoring_type;
    /*编辑框*/
    @BindView(R.id.et_confirm_message)
    MyEditText et_confirm_message;
    /*编辑字数*/
    @BindView(R.id.user_tv_count)
    TextView user_tv_count;
    /*获取编辑内容*/
    private String PersonalProfile;
    private int num = 140;
    private float start;
    private String gname;
    private String gimg;
    private int gid;
    private int oid;
    @BindView(R.id.iv_giamge)
    ImageView iv_giamge;
    @BindView(R.id.tv_gname)
    TextView tv_gname;


    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_order_comment_title).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.order_comment_layout;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initView() {
        ct_order_comment_title.setAction(this);
        gname = getIntent().getStringExtra("gname");//商品名称
        gimg = getIntent().getStringExtra("gimg");//商品图片
        gid = getIntent().getIntExtra("gid", 0);//商品id
        oid = getIntent().getIntExtra("oid", 0);//订单id
        tv_gname.setText(gname);
        String[] strs = gimg.split(",");
        Glide.with(this).load(strs[0]).fitCenter().into(iv_giamge);//买家头像
        commodity_score.setStarMark(Float.parseFloat("5.0"));
        commodity_score.setIntegerMark(true);
        commodity_score.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
            @Override
            public void onStarChange(float mark) {
                if (mark == 1.0) {
                    scoring_type.setText("非常差");
                } else if (mark == 2.0) {
                    scoring_type.setText("差");
                } else if (mark == 3.0) {
                    scoring_type.setText("一般");
                } else if (mark == 4.0) {
                    scoring_type.setText("优秀");
                } else if (mark == 5.0) {
                    scoring_type.setText("完美");
                }
            }
        });
        showeditText();
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

    @Override
    public void initData() {

    }


    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                String count = et_confirm_message.getText().toString().trim();
                start = commodity_score.getStarMark();
                if (start == 0.0) {
                    ToastUtils.showToast("请选择商品评分");
                } else if (count.equals("")) {
                    ToastUtils.showToast("请输入对商品评论内容");
                } else {
                    AddGoodsComment(start, count);
                }
                break;
        }
    }

    /**
     * 添加订单评论*
     */
    public void AddGoodsComment(float start, String ucdesc) {
        int uid = AppUtile.getUid(this);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDGOODSCOMMENT)
                .tag(this)
                .params("ucdesc", ucdesc)
                .params("uid", uid)
                .params("ucstars", start)
                .params("gid", gid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("评论成功");
                            UpdateOrderStatus(oid);
                        } else {
                            ToastUtils.showShort(OrderCommentActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 确认评论*
     */
    public void UpdateOrderStatus(int oid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEORDERSTATUS)
                .tag(this)
                .params("oid", oid)
                .params("ostatus", "7")
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showToast("评论成功");
                            finish();
                        } else {
                            ToastUtils.showShort(OrderCommentActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
