package com.yaer.remittance.ui.shopping_modular.commodity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.bean.SelectGoodsInfoBean;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/*全部评论*/
public class CommentDetailsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_comment_details)
    CustomTitlebar ct_comment_details;
    @BindView(R.id.rv_comment_details)
    RecyclerView rv_comment_details;
    CommodityEvaluationAdapter commodityEvaluationAdapter;
    private List<SelectGoodsInfoBean.GoodsCommentinfoModelsBean> goodsCommentinfoModelsBeans = new ArrayList<>();
    private View LoadingView;
    private View notDataView;
    private View errorView;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_comment_details).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_comment_details;
    }

    @Override
    public void initView() {
        ct_comment_details.setAction(this);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            goodsCommentinfoModelsBeans = (List<SelectGoodsInfoBean.GoodsCommentinfoModelsBean>) bundle.getSerializable("goodsCommentinfoModelsBeans");
        }
        /*评论列表*/
        rv_comment_details.setLayoutManager(new LinearLayoutManager(this));
        commodityEvaluationAdapter = new CommodityEvaluationAdapter();
        rv_comment_details.setAdapter(commodityEvaluationAdapter);
        rv_comment_details.setNestedScrollingEnabled(false);//禁止滑动
        LoadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rv_comment_details.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_comment_details.getParent(), false);
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_comment_details.getParent(), false);
        commodityEvaluationAdapter.setEmptyView(LoadingView);
        if (!NetworkUtils.isNetworkConnected(this)) {
            commodityEvaluationAdapter.setEmptyView(errorView);
        } else {
            if (goodsCommentinfoModelsBeans.size() == 0) {
                commodityEvaluationAdapter.setEmptyView(notDataView);
            }
            commodityEvaluationAdapter.setNewData(goodsCommentinfoModelsBeans);
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
