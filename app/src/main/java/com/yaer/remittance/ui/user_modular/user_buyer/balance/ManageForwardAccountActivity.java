package com.yaer.remittance.ui.user_modular.user_buyer.balance;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.BankCardInfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.adapter.BankCardInfoAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.UIAlertView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zly on 2015/9/19.
 * 管理提现账号
 */
public class ManageForwardAccountActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener, View.OnClickListener {
    @BindView(R.id.ct_manage_account)
    CustomTitlebar ct_manage_account;
    @BindView(R.id.btn_forward_confirm)
    Button btn_forward_confirm;
    @BindView(R.id.rv_manage_account)
    RecyclerView rv_manage_account;
    private BankCardInfoAdapter mAdapter;
    private List<BankCardInfoBean> ManageForwardItemList = new ArrayList<>();
    private int uid;
    private BottomSheetDialog sheetDialog;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_manage_account).init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        uid = AppUtile.getUid(this);
        GetBankCardInfo();
        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_manage_account.setLayoutManager(layoutManager);
    }

    @Override
    public void initView() {
        ct_manage_account.setAction(this);
    }

    @Override
    public void initData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_manage_forward_account;
    }

    @OnClick({R.id.btn_forward_confirm})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_forward_confirm:
                goToActivity(AddForwardAccountActivity.class);
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

    /**
     * 获取提现账号
     */
    private void GetBankCardInfo() {
        OkGo.<BaseMode<List<BankCardInfoBean>>>post(AppApi.BASE_URL + AppApi.SELECTBANKCRDINFOLIST)
                .tag(this)
                .params("uid", uid)
                .execute(new JsonCallback<BaseMode<List<BankCardInfoBean>>>(this) {
                    @Override
                    public void onSuccess(final Response<BaseMode<List<BankCardInfoBean>>> response) {
                        Log.e("text", "获取提现账号: " + response.body().code);
                        Log.e("text", "获取提现账号: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ManageForwardItemList = response.body().result;
                            mAdapter = new BankCardInfoAdapter(ManageForwardAccountActivity.this, ManageForwardItemList);
                            rv_manage_account.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    // showdialog(ManageForwardItemList.get(position).getBid());
                                    showDelDialog(ManageForwardItemList.get(position).getBid());
                                }
                            });
                        } else {
                            ToastUtils.showShort(ManageForwardAccountActivity.this, response.body().msg);
                        }
                    }
                });
    }

    private void showDelDialog(final String bid) {
        final UIAlertView delDialog = new UIAlertView(ManageForwardAccountActivity.this, "温馨提示", "是否删除银行卡",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           DeleteBankCard(bid);
                                           delDialog.dismiss();
                                       }
                                   }
        );
    }
   /* public void showdialog(final String posion) {
        sheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_manage_forward, null);
        *//*删除*//*
        TextView tvCamera = view.findViewById(R.id.tv_forward_delete);
        tvCamera.setOnClickListener((View.OnClickListener) this);
        TextView tvCancel = view.findViewById(R.id.tv_forward_Cancel);
        tvCancel.setOnClickListener((View.OnClickListener) this);
        sheetDialog.setContentView(view);
        sheetDialog.show();
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteBankCard(posion);
                sheetDialogDismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialogDismiss();
            }
        });
    }*/

  /*  private void sheetDialogDismiss() {
        if (null != sheetDialog && sheetDialog.isShowing()) {
            sheetDialog.dismiss();
        }
    }*/

    /**
     * 删除银行卡
     */
    public void DeleteBankCard(final String did) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.DELETEBANKCARD)
                .tag(this)
                .params("bid", did)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "获取提现账号: " + response.body().code);
                        Log.e("text", "获取提现账号: " + response.body().result);
                        ToastUtils.showShort(ManageForwardAccountActivity.this, response.body().code);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(ManageForwardAccountActivity.this, response.body().msg);
                            GetBankCardInfo();
                            //   mAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtils.showShort(ManageForwardAccountActivity.this, response.body().msg);
                        }
                    }
                });
    }
}

