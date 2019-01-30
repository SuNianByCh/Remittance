package com.yaer.remittance.ui.home_modular.auctiondetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.GetBiddinListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.shopping_modular.commodity.OfferRecordAdapter;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 更多出价记录
 * Created by ywl on 2016/6/27.
 */
public class AuctionViewRecordsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_auction_view_records)
    CustomTitlebar ct_auction_view_records;
    @BindView(R.id.rv_auction_view_record)
    RecyclerView rv_auction_view_record;
    OfferRecordAdapter offerRecordAdapter;
    private String gid;
    private List<GetBiddinListBean> getBiddinListBeans = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gid = getIntent().getStringExtra("gid");
        }
        ct_auction_view_records.setAction(this);
        getBiddinList();
        /*出价列表*/
        rv_auction_view_record.setLayoutManager(new LinearLayoutManager(this));
        offerRecordAdapter = new OfferRecordAdapter();
        rv_auction_view_record.setAdapter(offerRecordAdapter);
        rv_auction_view_record.setNestedScrollingEnabled(false);//禁止滑动

    }

    @Override
    public void initData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_auction_view_records).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_auction_view_records;
    }

    /**
     * 根据id查询出价 记录
     */
    private void getBiddinList() {
        OkGo.<BaseMode<List<GetBiddinListBean>>>post(AppApi.BASE_URL + AppApi.GETBIDDINLIST)
                .tag(this)
                .params("gid", gid)
                .execute(new JsonCallback<BaseMode<List<GetBiddinListBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<GetBiddinListBean>>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            getBiddinListBeans = response.body().result;
                            offerRecordAdapter.setNewData(getBiddinListBeans);
                            ToastUtils.showShort(AuctionViewRecordsActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(AuctionViewRecordsActivity.this, response.body().msg);
                        }
                    }
                });
    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
            case 0:
                break;
        }
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
