package com.yaer.remittance.ui.user_modular.user_seller.storetable;

import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ReportFormBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.PictureAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.WsbGridView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/15.
 * 卖家店铺报表
 */
public class StoreTableActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.table_view)
    WsbGridView gridView;
    @BindView(R.id.ct_store_table)
    CustomTitlebar ct_store_table;
    List<ReportFormBean> installedPackages;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_store_table).init();
    }

    /* private String[] titles = new String[]{
              "今日已退款  ¥6584", "今日已收款  ¥5862", "今日代发货    122件", "今日待确认    100件", "今日退货中    305件"
      };*///"今日已付款  ¥95632", R.drawable.store_fukuan,, "今日退款中  ¥2065"R.drawable.store_tuikuanzhong,
  /*  private Integer[] images = {
            R.drawable.store_tuikuan,
            R.drawable.store_shoukuan, R.drawable.store_weifahuo,
            R.drawable.store_daiqueren, R.drawable.store_tuihuo
    };*/

    @Override
    protected int setLayoutId() {
        return R.layout.activity_store_table;
    }

    @Override
    public void initView() {
        ct_store_table.setAction(this);
        PictureAdapter pictureAdapter = new PictureAdapter(this, installedPackages);
        gridView.setAdapter(pictureAdapter);
        getReportform();
    }

    /**
     * 获取店铺报表信息
     */
    public void getReportform() {
        OkGo.<BaseMode<ReportFormBean>>post(AppApi.BASE_URL + AppApi.GETREPORTFORM)
                .tag(this)
                .params("sid", AppUtile.getShopid(this))
                .execute(new JsonCallback<BaseMode<ReportFormBean>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<ReportFormBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            if (response.body().result == null) {
                                ToastUtils.showToast(response.body().msg);
                                return;
                            } else {
                                for (int i = 0; i < installedPackages.size(); i++) {
                                    ReportFormBean reportFormBean = new ReportFormBean();
                                    reportFormBean.setRefundmoney(installedPackages.get(i).getRefundmoney());
                                    reportFormBean.setReceivables(installedPackages.get(i).getReceivables());
                                    reportFormBean.setUnshipped(installedPackages.get(i).getUnshipped());
                                    reportFormBean.setUnconfirm(installedPackages.get(i).getUnconfirm());
                                    reportFormBean.setReturngoods(installedPackages.get(i).getReturngoods());
                                    installedPackages.add(reportFormBean);
                                }
                            }
                        } else {
                            ToastUtils.showToast(response.body().msg);
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
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
