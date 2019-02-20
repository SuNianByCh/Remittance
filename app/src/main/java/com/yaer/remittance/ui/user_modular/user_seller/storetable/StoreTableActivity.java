package com.yaer.remittance.ui.user_modular.user_seller.storetable;

import android.util.Log;
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
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.WsbGridView;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/15.
 * 个人
 */
public class StoreTableActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.table_view)
    WsbGridView gridView;
    @BindView(R.id.ct_store_table)
    CustomTitlebar ct_store_table;
    private LoadingDialog2 photoDiloag;
    private PictureAdapter pictureAdapter;

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

    private String[] titles = new String[]{
            "今日已付款  ¥95632", "今日已退款  ¥6584", "今日已收款  ¥5862", "今日未发货    122件", "今日待确认    100件", "今日退款中  ¥2065", "今日退货中    305件"
    };

    private Integer[] images = {
            R.drawable.store_fukuan, R.drawable.store_tuikuan,
            R.drawable.store_shoukuan, R.drawable.store_weifahuo,
            R.drawable.store_daiqueren, R.drawable.store_tuikuanzhong, R.drawable.store_tuihuo
    };

    @Override
    protected int setLayoutId() {
        return R.layout.activity_store_table;
    }

    @Override
    public void initView() {
        ct_store_table.setAction(this);
        //   PictureAdapter pictureAdapter = new PictureAdapter(titles, images, this);
        getReportform();
    }

    /**
     * 获取店铺报表信息
     */
    List<TestBean> testBeanList = new ArrayList<>();

    public void getReportform() {
        OkGo.<BaseMode<ReportFormBean>>post(AppApi.BASE_URL + AppApi.GETREPORTFORM)
                .tag(this)
                .params("sid", AppUtile.getScid(this))
                .execute(new JsonCallback<BaseMode<ReportFormBean>>(this) {

                    @Override
                    public void onSuccess(Response<BaseMode<ReportFormBean>> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            Log.e("text", "onSuccess: " + response.body().result);
                            ReportFormBean reportFormBean = new ReportFormBean();
                            reportFormBean = response.body().result;//先不管这个数据
                            TestBean testBean1 = new TestBean();
                            testBean1.setName("今日已退款");//这个name是固定，getReceivables通过这个来判断，
                            testBean1.setPrice(reportFormBean.getRefundmoney());//这是第一个
                            testBeanList.add(testBean1);
                            TestBean testBean2 = new TestBean();
                            testBean2.setName("今日已收款");
                            testBean2.setPrice(reportFormBean.getReceivables());//这是第2个
                            testBeanList.add(testBean2);
                            TestBean testBean3 = new TestBean();
                            testBean3.setName("今日代发货");
                            testBean3.setPrice(reportFormBean.getUnshipped() + "");//这是第2个
                            testBeanList.add(testBean3);
                            TestBean testBean4 = new TestBean();
                            testBean4.setName("今日待确认");
                            testBean4.setPrice(reportFormBean.getUnconfirm() + "");//这是第2个
                            testBeanList.add(testBean4);
                            TestBean testBean5 = new TestBean();
                            testBean5.setName("今日退款申请");
                            testBean5.setPrice(reportFormBean.getReturngoods() + "");//这是第2个
                            testBeanList.add(testBean5);
                            pictureAdapter = new PictureAdapter(testBeanList, StoreTableActivity.this);
                            gridView.setAdapter(pictureAdapter);
                            //这样添加，然后设置到adapter里
                            /*if (response.body().result == null) {
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
                            }*/
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

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
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
/*package com.yaer.remittance.ui.user_modular.user_seller.storetable;

import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.ReportFormBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.user_modular.user_buyer.footprint.UserFootPrintActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.PictureAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.WsbGridView;

import java.util.List;

import butterknife.BindView;

*//**
 * Created by Administrator on 2017/6/15.
 * 卖家店铺报表
 * <p>
 * dialog
 * <p>
 * 获取店铺报表信息
 * <p>
 * dialog 隐藏
 * <p>
 * dialog
 * <p>
 * 获取店铺报表信息
 * <p>
 * dialog 隐藏
 * <p>
 * dialog
 * <p>
 * 获取店铺报表信息
 * <p>
 * dialog 隐藏
 * <p>
 * dialog
 * <p>
 * 获取店铺报表信息
 * <p>
 * dialog 隐藏
 * <p>
 * dialog
 * <p>
 * 获取店铺报表信息
 * <p>
 * dialog 隐藏
 *//*

public class StoreTableActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.table_view)
    WsbGridView gridView;
    @BindView(R.id.ct_store_table)
    CustomTitlebar ct_store_table;
    List<ReportFormBean> installedPackages;
    *//**
 * dialog
 *//*
    private LoadingDialog2 photoDiloag;
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


*//* private String[] titles = new String[]{
              "今日已退款  ¥6584", "今日已收款  ¥5862", "今日代发货    122件", "今日待确认    100件", "今日退货中    305件"
      };*//**//*
//"今日已付款  ¥95632", R.drawable.store_fukuan,, "今日退款中  ¥2065"R.drawable.store_tuikuanzhong,
  *//*
 *//*  private Integer[] images = {
            R.drawable.store_tuikuan,
            R.drawable.store_shoukuan, R.drawable.store_weifahuo,
            R.drawable.store_daiqueren, R.drawable.store_tuihuo
    };*//*


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

    *//**
 * 获取店铺报表信息
 *//*

    public void getReportform() {
        OkGo.<BaseMode<ReportFormBean>>post(AppApi.BASE_URL + AppApi.GETREPORTFORM)
                .tag(this)
                .params("sid", AppUtile.getShopid(this))
                .execute(new JsonCallback<BaseMode<ReportFormBean>>(this) {
                    @Override
                    public void onStart(Request<BaseMode<ReportFormBean>, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(StoreTableActivity.this, "加载中...");
                        photoDiloag.show();
                    }

                    @Override
                    public void onSuccess(Response<BaseMode<ReportFormBean>> response) {ReportFormBean
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
    *//**
 * dialog 隐藏
 *//*
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
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
}*/
