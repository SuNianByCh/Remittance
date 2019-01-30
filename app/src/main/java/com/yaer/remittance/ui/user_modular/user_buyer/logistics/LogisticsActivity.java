package com.yaer.remittance.ui.user_modular.user_buyer.logistics;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.bean.MessContent;
import com.yaer.remittance.utils.KdniaoTrackQueryAPI;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 物流信息
 */
public class LogisticsActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_logistics)
    CustomTitlebar ct_logistics;
    /*  @BindView(R.id.x5_webview)
      X5WebView x5_webview;*/
    @BindView(R.id.list_view)
    ListView listView;
    private String Otrackingname;//快递名称
    private String Otrackingnumber;//快递单号
    private String expCode;//快递编码
    //设置沉浸式  就用LISTview恩恩，那你给我看看这个如何让他加载的时候走一下loading,不走的话，这个加载太慢了，还有之前你改的那个loading,点击home键会出现崩溃
    @BindView(R.id.tv_Otrackingname)
    TextView tv_Otrackingname;
    @BindView(R.id.tv_Otrackingnumber)
    TextView tv_Otrackingnumber;
    @BindView(R.id.rl_no_time_express)
    RelativeLayout rl_no_time_express;
    @BindView(R.id.tv_reason)
    TextView tv_reason;

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_logistics).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_logistics;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            MessContent messContent = new Gson().fromJson(val, MessContent.class);
            List<MessContent.TracesBean> list = messContent.getTraces();
            if (list.size() == 0) {
                tv_reason.setText(messContent.getReason());
                rl_no_time_express.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
            } else {
                rl_no_time_express.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
            MessListAdapter adapter = new MessListAdapter(LogisticsActivity.this, list);
            listView.setAdapter(adapter);
        }
    };

    @Override
    public void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Otrackingname = getIntent().getStringExtra("Otrackingname");//快递名称
            Otrackingnumber = getIntent().getStringExtra("Otrackingnumber");//快递单号
        }
        tv_Otrackingname.setText("快递公司：" + Otrackingname);
        tv_Otrackingnumber.setText("快递单号：" + Otrackingnumber);
        if (Otrackingname.equals("顺丰速运")) {
            expCode = "SF";
        } else if (Otrackingname.equals("百世快递")) {
            expCode = "HTKY";
        } else if (Otrackingname.equals("中通快递")) {
            expCode = "ZTO";
        } else if (Otrackingname.equals("申通快递")) {
            expCode = "STO";
        } else if (Otrackingname.equals("圆通速递")) {
            expCode = "YTO";
        } else if (Otrackingname.equals("韵达速递")) {
            expCode = "YD";
        } else if (Otrackingname.equals("邮政快递包裹")) {
            expCode = "YZPY";
        } else if (Otrackingname.equals("EMS")) {
            expCode = "EMS";
        } else if (Otrackingname.equals("天天快递")) {
            expCode = "HHTT";
        } else if (Otrackingname.equals("京东快递")) {
            expCode = "JD";
        } else if (Otrackingname.equals("优速快递")) {
            expCode = "UC";
        } else if (Otrackingname.equals("德邦快递")) {
            expCode = "DBL";
        } else if (Otrackingname.equals("宅急送")) {
            expCode = "ZJS";
        } else if (Otrackingname.equals("TNT快递")) {
            expCode = "TNT";
        } else if (Otrackingname.equals("UPS")) {
            expCode = "UPS";
        } else if (Otrackingname == "DHL") {
            expCode = "DHL";
        } else if (Otrackingname.equals("FEDEX联邦(国内件）")) {
            expCode = "FEDEX";
        } else if (Otrackingname.equals("FEDEX联邦(国际件）")) {
            expCode = "FEDEX_GJ";
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
                try {
                    // String result = api.getOrderTracesByJson("YTO", "M00035484779");
                    String result = api.getOrderTracesByJson(expCode, Otrackingnumber);
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putString("value", result);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        ct_logistics.setAction(this);
        //   x5_webview.loadUrl("file:///android_asset/main.html");
    }


    @Override
    public void initData() {

    }

    @OnClick({})
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    /**
     * 返回键监听
     *
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
      /*  if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (x5_webview != null && x5_webview.canGoBack()) {
                x5_webview.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }*/
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
      /*  //释放资源
        if (x5_webview != null)
            x5_webview.destroy();*/
        super.onDestroy();
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
