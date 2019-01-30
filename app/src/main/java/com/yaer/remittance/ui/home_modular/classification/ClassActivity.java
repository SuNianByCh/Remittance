package com.yaer.remittance.ui.home_modular.classification;

import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.bean.CategoryBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.ClassAdapter;
import com.yaer.remittance.ui.adapter.ClassMenuAdapter;
import com.yaer.remittance.ui.home_modular.search.ActivitySearch;
import com.yaer.remittance.ui.user_modular.user_buyer.collect.UserCollectActivity;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ClearEditText;
import com.yaer.remittance.view.LoadingDialog2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类
 */
public class ClassActivity extends BaseActivity {
    private List<String> menuList = new ArrayList<>();//这个是左边
    private List<CategoryBean.ResultBean> homeList = new ArrayList<>(); // 这个是右边
    private List<Integer> showTitle;
    @BindView(R.id.lv_menu)
    ListView lv_menu;
    @BindView(R.id.lv_home)
    ListView lv_home;
    @BindView(R.id.ll_home_class)
    LinearLayout ll_home_class;
    private ClassMenuAdapter menuAdapter;
    private ClassAdapter classAdapter;
    private int currentItem;
    @BindView(R.id.ct_classedittext)
    ClearEditText ct_classedittext;
    //有网络数据显示的
    @BindView(R.id.ll_classlist)
    LinearLayout ll_classlist;
    //有网络数据显示的
    @BindView(R.id.ll_class_nonetwork)
    LinearLayout ll_class_nonetwork;
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
    protected int setLayoutId() {
        return R.layout.activity_class;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ll_home_class).init();
    }

    @Override
    public void initView() {
        //  Fresco.initialize(this);
        menuAdapter = new ClassMenuAdapter(this, menuList);
        lv_menu.setAdapter(menuAdapter);

        classAdapter = new ClassAdapter(this, homeList);
        lv_home.setAdapter(classAdapter);

        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuAdapter.setSelectItem(position);
                menuAdapter.notifyDataSetInvalidated();
                lv_home.setSelection(showTitle.get(position));
            }
        });
        lv_home.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    return;
                }
                int current = showTitle.indexOf(firstVisibleItem);
                if (currentItem != current && current >= 0) {
                    currentItem = current;
                    menuAdapter.setSelectItem(currentItem);
                    menuAdapter.notifyDataSetInvalidated();
                }
            }
        });
        loadData();
    }

    @OnClick({R.id.ll_class, R.id.ct_classedittext})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ct_classedittext:
                goToActivity(ActivitySearch.class);
                break;
            case R.id.ll_class:
                finish();
                break;
        }
    }

    @Override
    public void initData() {

    }

    private void loadData() {
        if (!NetworkUtils.isNetworkConnected(this)) {
            ll_classlist.setVisibility(View.GONE);
            ll_class_nonetwork.setVisibility(View.VISIBLE);
        } else {
            ll_classlist.setVisibility(View.VISIBLE);
            ll_class_nonetwork.setVisibility(View.GONE);
            OkGo.<CategoryBean>post(AppApi.BASE_URL + AppApi.FETSHOPCLASSIFICATIONINFOMODELS)
                    .tag(this)
                    .execute(new JsonCallback<CategoryBean>(this) {
                        @Override
                        public void onStart(Request<CategoryBean, ? extends Request> request) {
                            super.onStart(request);
                            photoDiloag = new LoadingDialog2(ClassActivity.this, "加载中...");
                            photoDiloag.show();
                        }

                        @Override
                        public void onSuccess(Response<CategoryBean> response) {
                            Log.e("text", "商品分类列表: " + response.getRawCall());
                            Log.e("text", "商品分类列表: " + response.body().getResult());
                            if (response.body().getCode().equals(Constant.SUEECECODE)) {
                                homeList.addAll(response.body().getResult());
                                showTitle = new ArrayList<>();
                                for (int i = 0; i < homeList.size(); i++) {
                                    menuList.add(homeList.get(i).getScname());//这个就是左边的title
                                    showTitle.add(i);
                                }
                                menuAdapter.notifyDataSetChanged();
                                classAdapter.notifyDataSetChanged();
                            } else {
                                ToastUtils.showShort(ClassActivity.this, response.body().getMsg());
                            }
                        }

                        @Override
                        public void onError(Response<CategoryBean> response) {
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
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        } else {
            photoDiloag.dismiss();
        }
    }
}
