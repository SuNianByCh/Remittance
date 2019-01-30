package com.yaer.remittance.ui.user_modular.user_seller.drafts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.lzy.imagepicker.bean.ImageItem;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.bean.AddCollectionBean;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.logistics.LogisticsActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.AllOrderActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderCommentActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.order.OrderDetailsActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.DraftsAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AddCollectionBeanDao;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AuctionGoodActivity;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.CommodityActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rong.imkit.RongIM;

/**
 * 草稿箱
 */
public class DraftsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_drafts)
    CustomTitlebar ct_my_drafts;
    @BindView(R.id.rv_drafts)
    RecyclerView rv_drafts;
    private DraftsAdapter draftsAdapter;
    List<AddCollectionBean> saveList;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_my_drafts).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_drafts;
    }

    @Override
    public void initView() {
        ct_my_drafts.setAction(this);
        rv_drafts.setLayoutManager(new LinearLayoutManager(this));
        draftsAdapter = new DraftsAdapter();
        draftsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv_drafts.setAdapter(draftsAdapter);
        initCaChData();
        rv_drafts.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            }

            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int itemViewId = view.getId();
                AddCollectionBean addCollectionBean = draftsAdapter.getData().get(position);
                switch (itemViewId) {
                    /*编辑*/
                    case R.id.tv_drafts_edit:
                        Bundle bundle = new Bundle();
                        if (addCollectionBean.getSourceType() == AddCollectionBean.TYPE_PAI_PING) {
                            ArrayList<ImageItem> list = addCollectionBean.getImageItemList();
                            bundle.putParcelableArrayList("imageitem",  list);
                            bundle.putString("goodsname", addCollectionBean.getGoodsName());//商品名称
                            bundle.putString("goodsdesc", addCollectionBean.getGoodsDesc());//商品描述
                            bundle.putString("goodsprice", addCollectionBean.getGoodsPrice());//商品价格
                            bundle.putString("startprice", addCollectionBean.getStartPrice());//拍品起拍价
                            bundle.putString("minaddprice", addCollectionBean.getMinAddPrice());//拍品加价
                            bundle.putString("sailtime", addCollectionBean.getGoodsSailTime());//拍品起始时间
                            bundle.putString("trafficprice", addCollectionBean.getEndTime());//拍品结束时间
                            goToActivity(AuctionGoodActivity.class, bundle);
                        } else {
                            ArrayList<ImageItem> list = addCollectionBean.getImageItemList();
                            bundle.putParcelableArrayList("imageitem",  list);
                            bundle.putString("goodsname", addCollectionBean.getGoodsName());//商品名称
                            bundle.putString("goodsdesc", addCollectionBean.getGoodsDesc());//商品描述
                            bundle.putString("goodsprice", addCollectionBean.getGoodsPrice());//商品价格
                            bundle.putString("goodscount", addCollectionBean.getGoodsCount());//商品数量
                            goToActivity(CommodityActivity.class, bundle);
                        }
                        break;
                    /*删除*/
                    case R.id.tv_drafts_detele:
                        adapter.remove(position);
                        AddCollectionBeanDao.deleteAddCollection(addCollectionBean.getSaveTime());
                        break;
                }
            }
        });
    }


    private void initCaChData() {
        saveList = AddCollectionBeanDao.getSaveList();
        if (saveList == null || saveList.isEmpty()) {
            //TODO    show Empty view
            ToastUtils.showToast("no 草稿箱");
        } else {
            draftsAdapter.addData(saveList);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
