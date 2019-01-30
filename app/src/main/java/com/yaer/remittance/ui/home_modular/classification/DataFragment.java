package com.yaer.remittance.ui.home_modular.classification;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnRefreshLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.SelectGoodsListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.DataAdapter;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DataFragment extends BaseLazyFragment implements View.OnClickListener {
    public static final String ARGS_PAGE = "args_page";
    public static final String ARGS_PSCID = "args_scid";
    private int mPage;
    @BindView(R.id.data_class__refreshLayout)
    SmartRefreshLayout data_class__refreshLayout;
    @BindView(R.id.rv_data_fragment)
    RecyclerView rv_data_fragment;
    private List<SelectGoodsListBean> DataItemList = new ArrayList<>();
    private DataAdapter dataAdapter;
    private int page = 1;
    private int pagesize = 10;
    private View notDataView;
    private View errorView;


    public static DataFragment newInstance(int gcid, int scid) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, gcid);
        args.putInt(ARGS_PSCID, scid);
        DataFragment fragment = new DataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.layput_data_fragment;
    }

    @Override
    protected void initView() {
        // mPage = getArguments().getInt(ARGS_PAGE);
        rv_data_fragment.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        dataAdapter = new DataAdapter();
        rv_data_fragment.setAdapter(dataAdapter);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_data_fragment.getParent(), false);
      /*  notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataList(page, pagesize);
            }
        });*/
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_data_fragment.getParent(), false);
       /* errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDataList(page, pagesize);
            }
        });*/
        GetDataList(page, pagesize);
        showtext();
    }

    /***
     * 显示内容
     */
    private void showtext() {
        data_class__refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                data_class__refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        GetDataList(page, pagesize);
                        refreshlayout.finishRefresh();
                        refreshlayout.resetNoMoreData();//恢复上拉状态
                    }
                }, 2000);
            }

            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                data_class__refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        GetDataList(page, pagesize);
                        refreshlayout.finishLoadMore();
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onClick(View v) {
       /* mError = true;
        mNoData = true;
        GetDataList(page, pagesize);*/
    }

    private boolean mError = true;
    private boolean mNoData = true;
    private int gcid, scid;

    /**
     * 获取商品列表
     *
     * @param page
     * @param pagesize
     */
    private void GetDataList(final int page, int pagesize) {
        dataAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) rv_data_fragment.getParent());
        gcid = getArguments().getInt(ARGS_PAGE);
        scid = getArguments().getInt(ARGS_PSCID);
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            dataAdapter.setEmptyView(errorView);
        } else {
            HttpParams params = new HttpParams();
            if (gcid != 0)
                params.put("gcid", gcid);
            params.put("scid", scid);
            params.put("gissoldout", "0");
            params.put("gisauction", "0");
            params.put("page", page);
            params.put("pagesize", pagesize);
            OkGo.<BaseMode<List<SelectGoodsListBean>>>post(AppApi.BASE_URL + AppApi.SELECTGOODSLIST)
                    .tag(this)
                    .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                    .params(params)
                    .execute(new JsonCallback<BaseMode<List<SelectGoodsListBean>>>(getActivity()) {
                        @Override
                        public void onSuccess(Response<BaseMode<List<SelectGoodsListBean>>> response) {
                            Log.e("text", "商品列表: " + response.body().code);
                            Log.e("text", "商品列表: " + response.body().result);
                            DataItemList = response.body().result;
                            if (DataItemList.size() == 0) {
                                dataAdapter.setEmptyView(notDataView);
                                //mNoData = false;
                            }
                            if (response.body().code.equals(Constant.SUEECECODE)) {
                                if (page == 1) {
                                    dataAdapter.setNewData(DataItemList);
                                } else if (page > 1 && DataItemList != null && DataItemList.size() > 0) {
                                    dataAdapter.addData(DataItemList);
                                } else {
                                    data_class__refreshLayout.finishLoadMoreWithNoMoreData();
                                }
                            } else {
                                ToastUtils.showShort(getActivity(), response.body().msg);
                            }
                        }
                    });
        }
    }
}
