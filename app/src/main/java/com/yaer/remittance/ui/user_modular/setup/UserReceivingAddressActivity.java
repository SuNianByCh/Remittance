package com.yaer.remittance.ui.user_modular.setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.QueryCollectAddressBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.UserAddressAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-11.
 * 收货地址
 */
public class UserReceivingAddressActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_receiving_address)
    CustomTitlebar ct_receiving_address;
    @BindView(R.id.lv_user_address)
    ListView lv_user_address;
    private String utoken;
    private int uid;
    private List<QueryCollectAddressBean> Quserylist = new ArrayList<>();
    private UserAddressAdapter mAdapter;
    private String order = "";

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_receiving_address).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.user_receiving_address;
    }


    @Override
    protected void onResume() {
        super.onResume();
        utoken = AppUtile.getTicket(this);
        uid = AppUtile.getUid(this);
        QueryAddress();
        initRecycleView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        //接收从MainaActivity传递的数据
        Intent intent = getIntent();
        if (intent != null) {
            order = intent.getStringExtra("order");
        }
        ct_receiving_address.setAction(this);
        if (order != null && !order.equals("")) {
            lv_user_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("aid", Quserylist.get(position).getAid());
                    intent.putExtra("aname", Quserylist.get(position).getAname());
                    intent.putExtra("aphone", Quserylist.get(position).getAphone());
                    intent.putExtra("acity", Quserylist.get(position).getAcity());
                    intent.putExtra("adesc", Quserylist.get(position).getAdesc());
                    // 专门用于向上一个活动返回数据。第一个参数用于向上一个活动返回结果码，一般只使用RESULT_OK或RESULT_CANCELED这两个值
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        }
    }

    private void initRecycleView() {
        mAdapter = new UserAddressAdapter(this, this);
        lv_user_address.setAdapter(mAdapter);
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                break;
            case R.id.tv_right:
                goToActivity(AddedReceicingAddress.class);
                break;
        }
    }

    /**
     * 查询收货地址信息*
     */
    public void QueryAddress() {
        OkGo.<BaseMode<List<QueryCollectAddressBean>>>post(AppApi.BASE_URL + AppApi.ENQUERYRECEIPTADDRESS)
                .tag(this)
                .params("uid", uid)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode<List<QueryCollectAddressBean>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<QueryCollectAddressBean>>> response) {
                        Log.e("text", "查询收货地址信息: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (Quserylist.size() > 0) {
                                Quserylist.clear();
                            }
                            Quserylist = response.body().result;
                            if (Quserylist != null && Quserylist.size() > 0) {
                                mAdapter.clear();
                                mAdapter.addData(Quserylist);
                            }
                        } else {
                            ToastUtils.showShort(UserReceivingAddressActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 根据诋地址id修改默认收货地址
     * aid 删除条目id
     */
    public void Defaltaddress(String aid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.UPDATEDEFAULT)
                .tag(this)
                .params("uid", uid)
                .params("aid", aid)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "根据诋地址id修改默认收货地址: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            QueryAddress();
                            mAdapter.notifyDataSetChanged();
                            ToastUtils.showShort(UserReceivingAddressActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(UserReceivingAddressActivity.this, response.body().msg);
                        }
                    }
                });
    }

    /**
     * 删除收货地址
     * aid 删除条目id
     */
    public void DeleteAddress(String aid) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEADDRESS)
                .tag(this)
                .params("uid", uid)
                .params("aid", aid)
                .params("utoken", utoken)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "删除收货地址: " + response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            mAdapter.clear();
                            mAdapter.notifyDataSetChanged();
                            QueryAddress();
                            ToastUtils.showShort(UserReceivingAddressActivity.this, response.body().msg);
                        } else {
                            ToastUtils.showShort(UserReceivingAddressActivity.this, response.body().msg);
                        }
                    }
                });
    }
}
