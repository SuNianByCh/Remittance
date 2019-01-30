package com.yaer.remittance.ui.home_modular.expertssay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SpecialistBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ExpertsSayAdapter;
import com.yaer.remittance.ui.shopping_modular.commodity.CommodityDetailsActivity;
import com.yaer.remittance.utils.MxgsaTagHandler;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pl.droidsonroids.gif.GifImageView;

/**
 * 专家说详情
 */
public class ExpertsSayDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_experts_detail_say)
    CustomTitlebar ct_experts_detail_say;
    @BindView(R.id.iv_esimg)
    ImageView iv_esimg;
    @BindView(R.id.tv_esname)
    TextView tv_esname;
    @BindView(R.id.tv_esdesc)
    TextView tv_esdesc;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;
    private int esid;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /***
     * 初始化标题栏
     */
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_experts_detail_say).init();
    }

    /**
     * 设置页面来源-专家说
     *
     * @return
     */
    @Override
    protected int setLayoutId() {
        return R.layout.activity_experts_say_details;
    }

    /**
     * 初始化视图
     */
    @Override
    public void initView() {
        ct_experts_detail_say.setAction(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            esid = getIntent().getIntExtra("esid", 0);
        }
        GetSpecialist();
    }

    @Override
    public void initData() {

    }


    /**
     * 获取专家列表
     */
    private void GetSpecialist() {
        OkGo.<BaseMode<SpecialistBean>>post(AppApi.BASE_URL + AppApi.GETEXPERTSSAYINFO)
                .tag(this)
                .params("esid", esid)
                .execute(new JsonCallback<BaseMode<SpecialistBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<SpecialistBean>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(ExpertsSayDetailsActivity.this, "加载中...");
                        photoDiloag.show();
                    }
                    @Override
                    public void onSuccess(Response<BaseMode<SpecialistBean>> response) {
                        Log.e("text", "专家列表: " + response.body().code);
                        Log.e("text", "专家列表: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Glide.with(ExpertsSayDetailsActivity.this).load(response.body().result.getEsimg()).error(R.drawable.zhuanjia).fitCenter().into(iv_esimg);//专家头像
                            tv_esname.setText(response.body().result.getEsname());
                            tv_esdesc.setText(Html.fromHtml(response.body().result.getEsdesc(), null, new MxgsaTagHandler(ExpertsSayDetailsActivity.this)));
                            tv_esdesc.setClickable(true);
                            tv_esdesc.setMovementMethod(LinkMovementMethod.getInstance());
                            //tv_esdesc.setText(Html.fromHtml(response.body().result.getEsdesc()));
                        } else {
                            stopDialog();
                            ToastUtils.showShort(ExpertsSayDetailsActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode<SpecialistBean>> response) {
                        super.onError(response);
                        stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                    }
                });
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    /***
     * 标题栏点击事件
     * @param view
     */
    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }
}
