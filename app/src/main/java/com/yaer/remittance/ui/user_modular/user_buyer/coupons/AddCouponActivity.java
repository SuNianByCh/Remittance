package com.yaer.remittance.ui.user_modular.user_buyer.coupons;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppPickerView;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.MyEditText;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加优惠券
 */
public class AddCouponActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_add_coupon)
    CustomTitlebar ct_add_coupon;
    /*提交按钮*/
    @BindView(R.id.btn_submitcoupon)
    Button btn_submitcoupon;
    /*请输入优惠券名称*/
    @BindView(R.id.et_add_coupon_name)
    MyEditText et_add_coupon_name;
    /*优惠金额*/
    @BindView(R.id.myedit_addcoupon_money)
    MyEditText myedit_addcoupon_money;
    /*时间选择*/
    @BindView(R.id.tj_moey)
    MyEditText tj_moey;
    /*起始时间*/
    @BindView(R.id.tv_coupon_startTime)
    TextView tv_coupon_startTime;
    /*请输入有效天数*/
    @BindView(R.id.myet_effective_days)
    MyEditText myet_effective_days;
    /*请输入优惠劵数量*/
    @BindView(R.id.myet_coupon_number)
    MyEditText myet_coupon_number;
    String cbstartime = null;//开始时间戳
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_add_coupon).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_coupon;
    }

    @Override
    public void initView() {
        ct_add_coupon.setAction(this);
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_coupon_startTime, R.id.btn_submitcoupon})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitcoupon:
                submitcoupon();
                break;
            case R.id.tv_coupon_startTime:
                initStartTimePicker();
                break;
        }
    }

    private void submitcoupon() {
        if (!AppUtile.isEditText(et_add_coupon_name)) {
            ToastUtils.showToast("优惠券名称不能为空");
        } else if (!AppUtile.isEditText(myedit_addcoupon_money)) {
            ToastUtils.showToast("优惠券金额不能为空");
        } else if (!AppUtile.isEditText(tj_moey)) {
            ToastUtils.showToast("条件金额不能为空");
        } else if (!AppUtile.isEmpty(tv_coupon_startTime)) {
            ToastUtils.showToast("起始时间不能为空");
        } else if (!AppUtile.isEditText(myet_effective_days)) {
            ToastUtils.showToast("有效天数不能为空");
        } else if (!AppUtile.isEditText(myet_coupon_number)) {
            ToastUtils.showToast("优惠劵数量");
        } else {
            String cbname = et_add_coupon_name.getText().toString().trim();
            String cbmoney = myedit_addcoupon_money.getText().toString().trim();
            String cbmaxmoney = tj_moey.getText().toString().trim();
            String day = myet_effective_days.getText().toString().trim();
            String cbnumber = myet_coupon_number.getText().toString().trim();
            try {
                cbstartime = SystemUtil.dateToStamp1(tv_coupon_startTime.getText().toString().trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            GetgoodsList(cbname, cbmoney, cbmaxmoney, cbstartime, day, cbnumber);
        }
    }

    /**
     * 发布优惠券
     *
     * @param cbname
     * @param cbmoney
     * @param cbmaxmoney
     * @param cbstartime
     * @param day
     * @param cbnumber
     */
    private void GetgoodsList(String cbname, String cbmoney, String cbmaxmoney, String cbstartime, String day, String cbnumber) {
        if (!NetworkUtils.isNetworkConnected(this)) {
            ToastUtils.showToast("网络异常");
        } else {
            OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDCOUPONBYER)
                    .tag(this)
                    .params("sid", AppUtile.getUid(this))
                    .params("cbname", cbname)
                    .params("cbmoney", cbmoney)
                    .params("cbmaxmoney", cbmaxmoney)
                    .params("cbstartime", cbstartime)
                    .params("day", day)
                    .params("cbnumber", cbnumber)
                    .execute(new JsonCallback<BaseMode>(this) {
                        @Override
                        public void onStart(Request<BaseMode, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(AddCouponActivity.this, "加载中...");
                            photoDiloag.show();
                        }
                        @Override
                        public void onSuccess(Response<BaseMode> response) {
                            Log.e("text", "发布优惠券: " + response.body().code);
                            Log.e("text", "发布优惠券: " + response.body().result);
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                ToastUtils.showShort(AddCouponActivity.this, response.body().msg);
                                finish();
                            } else {
                                ToastUtils.showShort(AddCouponActivity.this, response.body().msg);
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            stopDialog();
                        }

                        @Override
                        public void onError(Response<BaseMode> response) {
                            super.onError(response);
                            stopDialog();
                        }
                    });
        }
    }

    private TimePickerView pvTime;//时间选择器

    /*拍卖起始时间选择器*/
    private void initStartTimePicker() {
        AppPickerView.showWorkingPickerView(this, tv_coupon_startTime);

        /**
         * @description
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        /*Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //这里是选中回调的
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
                String format = simpleDateFormat.format(date);
                tv_coupon_startTime.setText(format);
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .isCyclic(true)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        pvTime.show();*/
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
