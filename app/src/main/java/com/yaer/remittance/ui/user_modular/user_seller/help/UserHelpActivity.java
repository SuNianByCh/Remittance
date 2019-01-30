package com.yaer.remittance.ui.user_modular.user_seller.help;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Administrator on 2018-09-11.
 * 帮助界面
 */

public class UserHelpActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ct_help)
    CustomTitlebar ct_help;
    ExpandableItemAdapter adapter;
    List list;
    private String hctype;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_help).init();
    }

    @Override
    public void initView() {
        ct_help.setAction(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            hctype = getIntent().getStringExtra("hctype");
        }
        if (hctype.equals("1")) {
            getHelpCenterType(hctype);
        } else {
            getHelpCenterType(hctype);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));//间距设置，完全copy了别人的代码。。
        // recyclerView.setItemAnimator(new SlideInUpAnimator());//这是一个开源的动画效果，非常棒的哦
        list = new ArrayList();
        adapter = new ExpandableItemAdapter(null);
        recyclerView.setAdapter(adapter);

    }

    List<ParentEntity> parentEntities;

    /*获取帮助中心*/
    public void getHelpCenterType(String hctype) {
        OkGo.<BaseMode<List<ParentEntity>>>post(AppApi.BASE_URL + AppApi.GETHELPCENTERTYPE)
                .tag(this)
                .params("hctype", hctype)
                .execute(new JsonCallback<BaseMode<List<ParentEntity>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<ParentEntity>>> response) {
                        Log.e("text", "获取帮助中心: " + response.body().code);
                        parentEntities = response.body().result;
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (parentEntities != null) {
                                for (ParentEntity parentEntity : parentEntities) {
                                    parentEntity.addSubItemAll();
                                }
                            }
                            adapter.addData(parentEntities);
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }
                });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_help;
    }

    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
        }
    }

    @Override
    public void initData() {
    }
}
