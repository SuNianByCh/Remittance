package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.graphics.Color;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.BankCardInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.CashierInputFilter;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 提现
 */
public class PutForwardActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_put_forward)
    CustomTitlebar ct_put_forward;
    /*提现按钮*/
    @BindView(R.id.btn_forward_confirm)
    Button btn_forward_confirm;
    /*点击选择银行卡*/
    @BindView(R.id.ll_yhk)
    LinearLayout ll_yhk;
    /*选择提现银行卡*/
    @BindView(R.id.tv_put_firward_bname)
    TextView tv_put_firward_bname;
    /*可用余额*/
    @BindView(R.id.tv_available_balance)
    TextView tv_available_balance;
    /*全部提现按钮*/
    @BindView(R.id.tv_whole_cash_withdrawal)
    TextView tv_whole_cash_withdrawal;
    /*提现输入框*/
    @BindView(R.id.et_put_forward_money)
    EditText et_put_forward_money;
    @BindView(R.id.iv_put_forward_bimg)
    ImageView iv_put_forward_bimg;
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    private List<BankCardInfoBean> ManageForwardItemList = new ArrayList<>();
    private List<String> listname = new ArrayList<>();
    private String Amountmoney;//可用提现金额
    private String bid;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_put_forward).init();
    }

    @Override
    public void initView() {
        ct_put_forward.setAction(this);
        getBalace();//查询余额
        GetBankCardInfo();//查询银行卡
        InputFilter[] filters = {new CashierInputFilter()};
        et_put_forward_money.setFilters(filters); //设置金额输入的过滤器，保证只能输入金额类型
    }

    @Override
    public void initData() {

    }

    /**
     * 查询余额*
     */
    public void getBalace() {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.GETBALANCE)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Amountmoney = (String) response.body().result;
                            if (Amountmoney != null) {
                                tv_available_balance.setText("￥" + Amountmoney);
                            } else {
                                ToastUtils.showToast("余额不足请求充值");
                            }
                        } else {
                            ToastUtils.showShort(PutForwardActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_put_forward;
    }

    private Double money;

    @OnClick({R.id.btn_forward_confirm, R.id.ll_yhk, R.id.tv_whole_cash_withdrawal})
    public void onClick(View v) {
        switch (v.getId()) {
            /*全部提现按钮*/
            case R.id.tv_whole_cash_withdrawal:
                et_put_forward_money.setText(Amountmoney);
                break;
            /*选择银行卡*/
            case R.id.ll_yhk:
                showPickerView(v, listname);
                break;
            case R.id.btn_forward_confirm:
                String et_forward_money = et_put_forward_money.getText().toString();
                Double dqmoney = Double.parseDouble(Amountmoney);
                if (et_forward_money.equals("")) {
                } else {
                    money = Double.valueOf(et_forward_money);
                }
                if (!AppUtile.isEmpty(et_put_forward_money)) {
                    ToastUtils.showToast("金额不能为空");
                } else if (money > dqmoney) {
                    ToastUtils.showToast("您输入的金额大于提现金额，请重新输入");
                } else if (et_put_forward_money.getText().toString().trim().equals("0") || et_put_forward_money.getText().toString().trim().equals("0.0") || et_put_forward_money.getText().toString().trim().equals("0.00")) {
                    ToastUtils.showToast("请输入合法金额");
                } else {
                    AddWithdrawchshinfo(et_put_forward_money.getText().toString().trim());
                }
                break;
        }
    }

    /**
     * 提现方法
     */
    private void AddWithdrawchshinfo(String wcmoney) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDDWITHDRAWCASHINFO)
                .tag(this)
                .params("wcmoney", wcmoney)
                .params("uid", AppUtile.getUid(this))
                .params("bid", bid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(final Response<BaseMode> response) {
                        Log.e("text", "提现: " + response.body().code);
                        Log.e("text", "提现: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            //ManageForwardItemList = response.body().result;
                            ToastUtils.showShort(PutForwardActivity.this, response.body().msg);
                            finish();
                        } else {
                            ToastUtils.showShort(PutForwardActivity.this, response.body().msg);
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
                    tv_put_firward_bname.setText(ManageForwardItemList.get(options1).getBname() + "(" + getCardTailNum(ManageForwardItemList.get(options1).getBcardnum() + ")"));
                    bid = ManageForwardItemList.get(options1).getBid();
                    Glide.with(PutForwardActivity.this).load(ManageForwardItemList.get(options1).getBimg()).fitCenter().into(iv_put_forward_bimg);//银行卡图片
                }
            })
                    .setTitleText("选择银行卡")
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
        //监听选中
        /*if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_put_firward_bname.setText(ManageForwardItemList.get(options1).getBname() + "(" + getCardTailNum(ManageForwardItemList.get(options1).getBcardnum() + ")"));
                    bid = ManageForwardItemList.get(options1).getBid();
                    Glide.with(PutForwardActivity.this).load(ManageForwardItemList.get(options1).getBimg()).fitCenter().into(iv_put_forward_bimg);//银行卡图片
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setTitleText("选择银行卡")
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

    /**
     * 获取提现账号
     */
    private void GetBankCardInfo() {
        OkGo.<BaseMode<List<BankCardInfoBean>>>post(AppApi.BASE_URL + AppApi.SELECTBANKCRDINFOLIST)
                .tag(this)
                .params("uid", AppUtile.getUid(this))
                .execute(new JsonCallback<BaseMode<List<BankCardInfoBean>>>(this) {
                    @Override
                    public void onSuccess(final Response<BaseMode<List<BankCardInfoBean>>> response) {
                        Log.e("text", "获取提现账号: " + response.body().code);
                        Log.e("text", "获取提现账号: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ManageForwardItemList = response.body().result;
                            if (!ManageForwardItemList.equals("") && ManageForwardItemList.size() > 0) {
                                for (int i = 0; i < ManageForwardItemList.size(); i++) {
                                    listname.add(ManageForwardItemList.get(i).getBname() + "(" + getCardTailNum(ManageForwardItemList.get(i).getBcardnum() + ")"));
                                    tv_put_firward_bname.setText(ManageForwardItemList.get(0).getBname() + "(" + getCardTailNum(ManageForwardItemList.get(0).getBcardnum() + ")"));
                                    bid=ManageForwardItemList.get(0).getBid();
                                    Glide.with(PutForwardActivity.this).load(ManageForwardItemList.get(0).getBimg()).fitCenter().into(iv_put_forward_bimg);//卖家头像
                                }
                            }
                        } else {
                            ToastUtils.showShort(PutForwardActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 获取银行卡后四位
     *
     * @param cardNum
     * @return
     */
    public static String getCardTailNum(String cardNum) {
        StringBuffer tailNum = new StringBuffer();
        if (cardNum != null) {
            int len = cardNum.length();
            for (int i = len - 1; i >= len - 5; i--) {
                tailNum.append(cardNum.charAt(i));
            }
            tailNum.reverse();
        }
        return tailNum.toString();
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

