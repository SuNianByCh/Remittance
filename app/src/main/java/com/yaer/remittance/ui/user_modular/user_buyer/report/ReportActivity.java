package com.yaer.remittance.ui.user_modular.user_buyer.report;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;

/**
 * 举报页面.
 *
 * @author hj
 */
public class ReportActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener, View.OnClickListener {
    //标题栏
    @BindView(R.id.ct_report)
    CustomTitlebar ct_report;
    //假冒伪劣
    @BindView(R.id.fack_commodities_layout)
    RelativeLayout fack_commodities_layout;
    //假冒伪劣文字
    @BindView(R.id.fack_commodities_tv)
    TextView fack_commodities_tv;
    //假冒伪劣选项
    @BindView(R.id.fack_commodities_cb)
    CheckBox fack_commodities_cb;
    //违背法律法规
    @BindView(R.id.vary_layout)
    RelativeLayout vary_layout;
    //违背法律法规
    @BindView(R.id.vary_tv)
    TextView vary_tv;
    //违背法律法规
    @BindView(R.id.vary_cb)
    CheckBox vary_cb;
    //暴力色情
    @BindView(R.id.rough_stuff_layout)
    RelativeLayout rough_stuff_layout;
    //暴力色情文字
    @BindView(R.id.rough_stuff_tv)
    TextView rough_stuff_tv;
    //暴力色情选项
    @BindView(R.id.rough_stuff_cb)
    CheckBox rough_stuff_cb;
    //诈骗虚假消息
    @BindView(R.id.defraud_layout)
    RelativeLayout defraud_layout;
    //诈骗虚假消息
    @BindView(R.id.defraud_tv)
    TextView defraud_tv;
    //诈骗虚假消息
    @BindView(R.id.defraud_cb)
    CheckBox defraud_cb;
    //非法传销
    @BindView(R.id.pyramid_layout)
    RelativeLayout pyramid_layout;
    //非法传销
    @BindView(R.id.pyramid_tv)
    TextView pyramid_tv;
    //非法传销
    @BindView(R.id.pyramid_cb)
    CheckBox pyramid_cb;
    //涉嫌虚假交易
    @BindView(R.id.trumpery_layout)
    RelativeLayout trumpery_layout;
    //涉嫌虚假交易
    @BindView(R.id.trumpery_tv)
    TextView trumpery_tv;
    //涉嫌虚假交易
    @BindView(R.id.trumpery_cb)
    CheckBox trumpery_cb;
    //其他
    @BindView(R.id.other_layout)
    RelativeLayout other_layout;
    //其他
    @BindView(R.id.other_tv)
    TextView other_tv;
    //其他
    @BindView(R.id.other_cb)
    CheckBox other_cb;

    private int uid;
    private int coverid;
    private String reportdesc;
    private int reportType;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_report;
    }

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_report).init();
    }

    @Override
    public void initView() {
        ct_report.setAction(this);
        uid = AppUtile.getUid(this);
        coverid = 86;
        fack_commodities_layout.setOnClickListener(this);
        vary_layout.setOnClickListener(this);
        rough_stuff_layout.setOnClickListener(this);
        defraud_layout.setOnClickListener(this);
        pyramid_layout.setOnClickListener(this);
        trumpery_layout.setOnClickListener(this);
        other_layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fack_commodities_layout:
                setSelectItem(fack_commodities_cb, fack_commodities_tv);
                break;
            case R.id.vary_layout:
                setSelectItem(vary_cb, vary_tv);
                break;
            case R.id.rough_stuff_layout:
                setSelectItem(rough_stuff_cb, rough_stuff_tv);
                break;
            case R.id.defraud_layout:
                setSelectItem(defraud_cb, defraud_tv);
                break;
            case R.id.pyramid_layout:
                setSelectItem(pyramid_cb, pyramid_tv);
                break;
            case R.id.trumpery_layout:
                setSelectItem(trumpery_cb, trumpery_tv);
                break;
            case R.id.other_layout:
                setSelectItem(other_cb, other_tv);
                break;
        }
    }

    private int checkReortType() {
        if (fack_commodities_cb.isChecked()) {
            return 1;
        }
        if (vary_cb.isChecked()) {
            return 2;
        }
        if (rough_stuff_cb.isChecked()) {
            return 3;
        }
        if (defraud_cb.isChecked()) {
            return 4;
        }
        if (pyramid_cb.isChecked()) {
            return 5;
        }
        if (trumpery_cb.isChecked()) {
            return 6;
        }
        if (other_cb.isChecked()) {
            return 7;
        }
        return -1;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    private void setSelectItem(CheckBox cb, TextView tv) {
        if (cb.isChecked()) {
            cb.setChecked(false);
        } else {
            fack_commodities_cb.setChecked(false);
            vary_cb.setChecked(false);
            rough_stuff_cb.setChecked(false);
            defraud_cb.setChecked(false);
            pyramid_cb.setChecked(false);
            trumpery_cb.setChecked(false);
            other_cb.setChecked(false);
            cb.setChecked(true);
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
            case R.id.tv_right:
                reportType = checkReortType();
                if (reportType != -1) {
                    addReport();
                } else {
                    ToastUtils.showToast("请选择举报类型");
                    return;
                }
                break;
        }
    }

    /*添加举报*/
    private void addReport() {
        if (reportType == 1) {
            reportdesc = "假冒伪劣商品";
        } else if (reportType == 2) {
            reportdesc = "违背法律法规";
        } else if (reportType == 3) {
            reportdesc = "暴力色情";
        } else if (reportType == 4) {
            reportdesc = "诈骗和虚假消息";
        } else if (reportType == 5) {
            reportdesc = "非法传销";
        } else if (reportType == 6) {
            reportdesc = "该商品/店铺涉嫌虚假交易";
        } else if (reportType == 7) {
            reportdesc = "其他";
        }
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDREPORTINFO)
                .tag(this)
                .params("rdesc", reportdesc)
                .params("rtype", 1)
                .params("ruid", uid)
                .params("reportedallegedid", coverid)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加举报: " + response.body().code);
                        Log.e("text", "添加举报: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(ReportActivity.this, "举报成功");
                            finish();
                        } else {
                            ToastUtils.showShort(ReportActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
