package com.yaer.remittance.ui.home_modular.leakhunting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.ui.adapter.GoodshopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author FT
 * @time 2016-07-18 16:49
 * 捡漏
 */

public class LeakFragment extends BaseLazyFragment {
    @BindView(R.id.rv_leak_list)
    RecyclerView mRecyclerView;
    private Context context;
    private int mPage;
    public static final String MERCHANT_DETAILS_PAGE = "MERCHANT_DETAILS_PAGE";
    private LeakListAdapter mAdapter;

    private ArrayList<String> data;

    List<String> mFoodData;
    List<String> mMovieData;
    List<String> mHaveFunData;
    List<String> mEvaluationData;
    List<String> mEvaluationData1;
    List<String> mEvaluationData2;
    List<String> mEvaluationData3;
    List<String> mEvaluationData4;


     public static LeakFragment newInstance(String time){
        Bundle args = new Bundle();
        args.putSerializable("time", time);
        LeakFragment tripFragment = new LeakFragment();
        tripFragment.setArguments(args);
        return tripFragment;
    }

    //假设这个ArrayList就是后面传到Fragment页面中的列表数据哈
    public static LeakFragment newInstance(ArrayList<String> data) {
        //这里传入的是位置，恩，
        Bundle args = new Bundle();
        args.putSerializable(MERCHANT_DETAILS_PAGE, data);
        LeakFragment tripFragment = new LeakFragment();
        tripFragment.setArguments(args);
        return tripFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null) {
            data = (ArrayList<String>) getArguments().getSerializable(MERCHANT_DETAILS_PAGE);
        }
        context = getActivity().getApplicationContext();
        //setData();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.leak_fragment;
    }

/*    @Override
    protected void initView() {
        switch (mPage) {
            case 0:
                initAdapter(mFoodData);
                break;
            case 1:
                initAdapter(mMovieData);
                break;
            case 2:
                initAdapter(mHaveFunData);
                break;
            case 3:
                initAdapter(mEvaluationData);
                break;
            case 4:
                initAdapter(mEvaluationData1);
                break;
            case 5:
                initAdapter(mEvaluationData2);
                break;
            case 6:
                initAdapter(mEvaluationData3);
                break;
            case 7:
                initAdapter(mEvaluationData4);
                break;

        }
    }

    private void setData() {
        mFoodData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mFoodData.add("美食" + i);
        }
        mMovieData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mMovieData.add("电影" + i);
        }
        mHaveFunData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mHaveFunData.add("玩乐" + i);
        }
        mEvaluationData = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            mEvaluationData.add("评价" + i);
        }
        mEvaluationData1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mEvaluationData1.add("评价1" + i);
        }
        mEvaluationData2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mEvaluationData2.add("评价2" + i);
        }
        mEvaluationData3 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            mEvaluationData3.add("评价3" + i);
        }
        mEvaluationData4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mEvaluationData4.add("评价4" + i);
        }
    }*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String time = getArguments().getString("time");
        FragmentActivity activity = getActivity();
        if(time ==null || activity  == null || !( activity instanceof LeakHuntingActivity)){
            //TODO show emptyView;
            return;
        }

        List<LeakHuntingBean> leakHuntingBeans = ((LeakHuntingActivity) activity).getString(time);
        if(leakHuntingBeans == null || leakHuntingBeans.isEmpty()){
            //TODO show emptyView;
            return;

        }

        initAdapter(leakHuntingBeans);

    }

    /**
     * 设置RecyclerView属性
     */
    private void initAdapter( List<LeakHuntingBean> leakHuntingBeans) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new LeakListAdapter();
        mAdapter.addData(leakHuntingBeans);
        mAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mAdapter);//设置adapter

    }
}
