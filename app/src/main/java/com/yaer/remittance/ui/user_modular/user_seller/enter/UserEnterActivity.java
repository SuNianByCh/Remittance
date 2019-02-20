package com.yaer.remittance.ui.user_modular.user_seller.enter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSS;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liji.circleimageview.CircleImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.base.OssUtile;
import com.yaer.remittance.bean.LinkLabelBean;
import com.yaer.remittance.bean.ShopClassIFicationInfoModels;
import com.yaer.remittance.bean.ShopinfoBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.UserEnterListAdapter;
import com.yaer.remittance.ui.user_modular.personal.TakePhotoActivity;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.EventMessage;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.utils.UpLoad;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.UIAlertView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 商家入驻
 */
public class UserEnterActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_enter)
    CustomTitlebar ct_enter;
    /*分类按钮*/
    @BindView(R.id.et_enter_class)
    TextView et_enter_class;
    /*店铺标签按钮*/
    @BindView(R.id.tv_user_enter_label)
    TextView tv_user_enter_label;
    /*头像选择按钮*/
    @BindView(R.id.rl_user_enter_head_portrait)
    RelativeLayout rl_user_enter_head_portrait;
    /*头像控件*/
    @BindView(R.id.user_enter_mCircleImageView)
    CircleImageView user_enter_mCircleImageView;
    //获取分类集合
    private List<ShopClassIFicationInfoModels> goodsification = new ArrayList<>();
    //分类id
    private int stype;
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    List<String> listname = new ArrayList<>();
    /*商户名称*/
    @BindView(R.id.et_enter_name)
    EditText et_enter_name;
    /*店铺图片*/
    @BindView(R.id.iv_shopinfo_image)
    ImageView iv_shopinfo_image;
    /*店铺图片外层*/
    @BindView(R.id.rl_shopinfo_portrait)
    LinearLayout rl_shopinfo_portrait;
    /*头像*/
    // private ArrayList<TImage> imagesTalk;
    // private File image_file;
    //   private String userpath, oosuserpath;
    private String strName = "";
    private String stime;
    //标签
    private List<LinkLabelBean> list = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    private List<String> str = new ArrayList<>();
    private List<String> hashMap = new ArrayList<>();
    private LinkLabelBean linkLabelBean;
    private String lable;
    @BindView(R.id.ben_user_enter_Submission)
    Button ben_user_enter_Submission;
    private Gson gson = new Gson();
    private String rzgetSmoney;
    @BindView(R.id.rz_tv_money)
    TextView rz_tv_money;
    private String Amountmoney = null;//用户余额
    private String shopmoney;
    private String dptxdata = "";//店铺头像
    private String dpbjdata = "";//店铺背景
    /*设置图片路径*/
    private String dptouxiangimg = "", dpbeijingimg = "";
    /*图片上传路径*/
    private String dptouxiangimgs = "", dpbeijingimgs = "";
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                GetupheadPortrait(dptouxiangimgs, dpbeijingimgs);
              /*  String data = listToString(list);
                String gname = et_Trade_name.getText().toString().trim();//商品名称
                String gdesc = et_Commodity_Description.getText().toString().trim();//商品描述
                String gmoney = et_Selling_price.getText().toString().trim();//出售价格
                String commodity_num = et_commodity_number.getText().toString().trim();//商品数量
                String mailcost = et_mail_Cost.getText().toString().trim();
                String gisauction = "0";//商品标识
                String gissoldout = "0";//是否下架
                GetInsertAuctionGoods(gname, gdesc, gisauction, gmoney, data, gissoldout, commodity_num, mailcost);*/
            }
        }
    };

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_enter).init();
    }


    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_enter;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        stime = String.valueOf(System.currentTimeMillis());
        ct_enter.setAction(this);
        // getBalace();//余额查询
        lableshow();//店铺标签
        getGoodsClassIFicationModels();//获取一级分类
    }

    private void lableshow() {
        /*str.add("茶文化");
        str.add("天然玉石");
        str.add("家具");
        str.add("珠串雅玩");
        str.add("美食");
        str.add("金石篆刻");
        str.add("时尚首饰");
        str.add("文房雅玩");
        str.add("民俗工艺");
        str.add("生活百货");*/
        str.add("珠串雅玩");
        str.add("金石篆刻");
        str.add("民俗工艺");
        str.add("鬼斧神雕");
        str.add("古瓷韵味");
        str.add("当代工艺");
        str.add("珠宝玉石");
        str.add("陈设摆件");
        str.add("古董珍品");
        for (int i = 0; i < str.size(); i++) {
            linkLabelBean = new LinkLabelBean();
            linkLabelBean.setStr(str.get(i));
            list.add(linkLabelBean);
        }
    }

    @OnClick({R.id.et_enter_class, R.id.tv_user_enter_label, R.id.rl_user_enter_head_portrait, R.id.ben_user_enter_Submission, R.id.iv_shopinfo_image})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ben_user_enter_Submission:
                if (!NetworkUtils.isNetworkConnected(this)) {
                    ToastUtils.showToast("当前没有连接网络");
                } else if (dptxdata.equals("")) {
                    ToastUtils.showToast("请设置商户头像");
                } else if (dpbjdata.equals("")) {
                    ToastUtils.showToast("请设置店铺背景图");
                } else if (!AppUtile.isEditText(et_enter_name)) {
                    ToastUtils.showToast("请输入商户名称");
                } else if (!AppUtile.isEmpty(tv_user_enter_label)) {
                    ToastUtils.showToast("请选择店铺标签");
                } else if (!AppUtile.isEmpty(et_enter_class)) {
                    ToastUtils.showToast("请选择店铺分类");
                } else {
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dptouxiangimg = "auction/user/" + getImageObjectKey();
                            dpbeijingimg = "auction/user/" + getImageObjectKey();
                            dptouxiang(dptouxiangimg);
                            dpbeijing(dpbeijingimg);
                        }
                    });
                    thread.start();
                }
                break;
            case R.id.rl_user_enter_head_portrait:
                //WsbPopUtile.getInstence(this).showTalkPhoto(rl_user_enter_head_portrait, getTakePhoto(), TypeMessage.oppen);
                UpLoad upLoad = new UpLoad(new UpLoad.ActionCallback() {
                    @Override
                    public void onClickCamera() {
                        startActForResult(1, 101);
                    }

                    @Override
                    public void onClickPhoto() {
                        startActForResult(2, 102);
                    }
                });
                upLoad.uploadImage(this);
                break;
            case R.id.iv_shopinfo_image:
                UpLoad upLoad1 = new UpLoad(new UpLoad.ActionCallback() {
                    @Override
                    public void onClickCamera() {
                        startActForResult(1, 104);
                    }
                    @Override
                    public void onClickPhoto() {
                        startActForResult(2, 105);
                    }
                });
                upLoad1.uploadImage(this);
                break;
            case R.id.et_enter_class:
                showPickerView(v, listname);
                break;
            case R.id.tv_user_enter_label:
                shodialog();
                break;
        }
    }

    private void dptouxiang(String dptouxiangimg) {
        OssUtile ossUtile = new OssUtile(this, dptxdata);
        ossUtile.asyncPutObjectFromLocalFile(dptouxiangimg, EventMessage.Type.DPTOUXIANG);
    }

    private void dpbeijing(String dpbeijingimg) {
        OssUtile ossUtile = new OssUtile(this, dpbjdata);
        ossUtile.asyncPutObjectFromLocalFile(dpbeijingimg, EventMessage.Type.DPBEIJING);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void startActForResult(int flag, int requestCode) {
        startActivityForResult(new Intent(this, TakePhotoActivity.class).putExtra("flag", flag), requestCode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("test", "onActivityResult: " + requestCode);
        Log.e("test", "onActivityResult: " + resultCode);
        Log.e("test", "onActivityResult: " + data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == 101) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                //  ToastUtils.showToast("您没有拍照,请拍照");
            } else {
                Glide.with(this).load(data1).fitCenter().into(user_enter_mCircleImageView);
                dptxdata = data1;
                //showoos(data1, pacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 102) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(user_enter_mCircleImageView);
                dptxdata = data1;
                //showoos(data1, pacardimg);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 104) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                // ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_shopinfo_image);
                dpbjdata = data1;
                //showoos1(data1, pacardimgback);
            }
            Log.v("WZ", data1);
        } else if (requestCode == 105) {
            String data1 = data.getStringExtra("data");
            if (data1.equals("")) {
                //ToastUtils.showToast("您没有选择照片,请选择图片");
            } else {
                Glide.with(this).load(data1).fitCenter().into(iv_shopinfo_image);
                dpbjdata = data1;
            }
            Log.v("WZ", data1);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMessage(EventMessage eventMessage) {
        //用户退出操作
        if (eventMessage != null) {
            int tag = eventMessage.getTag();
            if (tag == EventMessage.Type.DPTOUXIANG) {
                Bundle bundle = eventMessage.getBundle();
                dptouxiangimgs = bundle.getString("img");
            } else if (tag == EventMessage.Type.DPBEIJING) {
                Bundle bundle = eventMessage.getBundle();
                dpbeijingimgs = bundle.getString("img");
            }
            if (handler != null) {
                if (!dptouxiangimgs.equals("") && !dpbeijingimgs.equals("")) {
                    handler.sendEmptyMessage(0);
                }
            }
        }
    }

    /*  @Override
      public void takeImageSuccess(TResult result) {
          imagesTalk = result.getImages();
          strName = imagesTalk.get(imagesTalk.size() - 1).getCompressPath();
          image_file = new File(imagesTalk.get(imagesTalk.size() - 1).getCompressPath());
          Glide.with(this).load(strName).fitCenter().into(user_enter_mCircleImageView);
      }
  */
    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }

    /**
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(View view, List<String> listname) {
        if (pvOptions == null) {
            pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    et_enter_class.setText(goodsification.get(options1).getScname());
                    stype = goodsification.get(options1).getScid();
                    rz_tv_money.setText(goodsification.get(options1).getSmoney());
                    shopmoney = goodsification.get(options1).getSmoney();
                }
            })
                    .setTitleText("入驻分类")
                    .setContentTextSize(20)//设置滚轮文字大小
                    .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                    .setTextColorOut(Color.BLACK)
                    .setTextColorCenter(Color.BLUE)
                    .setSelectOptions(0)//默认选中项
                    .setBgColor(Color.WHITE)
                    .setTitleBgColor(Color.WHITE)
                    .setTitleColor(Color.LTGRAY)
                    .setCancelColor(Color.parseColor("#C2100D"))
                    .setSubmitColor(Color.parseColor("#C2100D"))
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setBackgroundId(0x00000000) //设置外部遮罩颜色
                    .build();
            pvOptions.setPicker(listname);//一级选择器
            pvOptions.show();
        }else{
            pvOptions.show();
        }
    }

    /**
     * 获取商品一级分类
     */
    private void getGoodsClassIFicationModels() {
        OkGo.<BaseMode<List<ShopClassIFicationInfoModels>>>post(AppApi.BASE_URL + AppApi.FETSHOPCLASSIFICATIONINFOMODELS)
                .tag(this)
                .execute(new JsonCallback<BaseMode<List<ShopClassIFicationInfoModels>>>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<ShopClassIFicationInfoModels>>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            goodsification = response.body().result;
                            if (!goodsification.equals("") && goodsification.size() > 0) {
                                for (int i = 0; i < goodsification.size(); i++) {
                                    listname.add(goodsification.get(i).getScname());
                                }
                            }
                        } else {
                            ToastUtils.showShort(UserEnterActivity.this, response.body().msg);
                        }
                    }
                });
    }

    private void shodialog() {
        View view = View.inflate(UserEnterActivity.this, R.layout.user_enter_list_item, null);
        RecyclerView lv = (RecyclerView) view.findViewById(R.id.recyclerview_user_enter_list);
        Button btn_enter_confirm = view.findViewById(R.id.btn_enter_confirm);
        Button btn_enter_cancel = view.findViewById(R.id.btn_enter_cancel);
        final GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        lv.setLayoutManager(mLayoutManager);
        UserEnterListAdapter userEnterListAdapter = new UserEnterListAdapter(getApplicationContext(), list);
        lv.setAdapter(userEnterListAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(UserEnterActivity.this);
        final AlertDialog dialog = builder.setView(view).show();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                list.get(i).setSelect(false);
            }
        }
        btn_enter_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这边其实可以优化 但是时间不够就不再做优化。
                boolean zhishao = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()) {
                        zhishao = true;
                    }
                }
                if (!zhishao) {
                    ToastUtils.showToast("至少选择一个标签。");
                    return;
                }
                hashMap.clear();
                list1.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isSelect()) {
                        list1.add(list.get(i).getStr());
                    }
                }
                if (list1.size() > 0) {
                    for (int i = 0; i < list1.size(); i++) {
                        hashMap.add(list1.get(i));
                    }
                    for (int i = 0; i < hashMap.size(); i++) {
                        hashMap.get(i);
                    }
                }
                StringBuilder images = new StringBuilder();
                for (String s : hashMap) {
                    images.append(s).append(",");
                }
                if (images.length() > 0) {
                    lable = images.toString().substring(0, images.length() - 1);
                } else {
                    lable = "";
                }
                tv_user_enter_label.setText("");
                tv_user_enter_label.setText(lable);
                dialog.cancel();
            }
        });
        btn_enter_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = 0;
        wl.gravity = Gravity.CENTER;
        window.setAttributes(wl);
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
            case R.id.tv_right:
                break;
        }
    }

    public static String listToString3(List list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /*添加商户信息*/
    private void GetupheadPortrait(String dptouxiangimgs, String dpbeijingimgs) {
        String sname = et_enter_name.getText().toString().trim();
        String sgrade = "5.0";
        String slabel = tv_user_enter_label.getText().toString().trim();
        int sid = AppUtile.getUid(this);
        List<ShopinfoBean> shopinfolist = new ArrayList<>();
        ShopinfoBean shopinfoBean = new ShopinfoBean();
        shopinfoBean.setSname(sname);
        shopinfoBean.setSgrade(sgrade);
        shopinfoBean.setStime(stime);
        shopinfoBean.setSlabel(slabel);
        shopinfoBean.setSid(sid);
        shopinfoBean.setSimg(dptouxiangimgs);
        shopinfoBean.setStype(stype);
        shopinfoBean.setSbgimg(dpbeijingimgs);
        shopinfolist.add(shopinfoBean);
        //返回结果转json
        String s = gson.toJson(shopinfoBean);
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.INSERTSHOPINFOMODEL)
                .tag(this)
                .params("shopinfo", s)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        photoDiloag = new LoadingDialog2(UserEnterActivity.this, "加载中...");
                        photoDiloag.show();
                    }
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            stopDialog();
                            ToastUtils.showShort(UserEnterActivity.this, response.body().msg);
                            finish();
                        } else if (response.body().code.equals("507")) {
                            showDelDialog();
                        } else {
                            ToastUtils.showShort(UserEnterActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
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

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    private void showDelDialog() {
        dptouxiangimgs = "";
        dpbeijingimgs = "";
        final UIAlertView delDialog = new UIAlertView(UserEnterActivity.this, "温馨提示", "余额不足" + shopmoney + "请前往充值页面充值",
                "取消", "确认");
        delDialog.show();
        delDialog.setClicklistener(new UIAlertView.ClickListenerInterface() {
                                       @Override
                                       public void doLeft() {
                                           delDialog.dismiss();
                                       }

                                       @Override
                                       public void doRight() {
                                           delDialog.dismiss();
                                           goToActivity(RechargeActivity.class);
                                       }
                                   }
        );
    }

    private String testBucket;
    private OSS oss;
    private String uploadFilePath;

    /*  *//**
     * 阿里云上传文件初始化正面身份证
     *
     * @param 、、        上下文
     * @param 、、阿里云配置信息
     * @param 、、        文件路径
     *//*
    private void showoos(String filePath, String path) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIUgkh1Pv4ANqb", "VeLY0skMK0qdJ6lkXe5etWPCpaBvIe");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(context.getApplicationContext(), "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
        testBucket = "paipinhui";
        this.uploadFilePath = filePath;
        asyncPutObjectFromLocalFile(path, EventMessage.Type.PERSIONIMAGE);
        Log.e("test", "showoos: " + path);
    }

    *//**
     * 从本地文件上传，使用非阻塞的异步接口
     *
     * @param imgUrL 目录
     *//*
    public void asyncPutObjectFromLocalFile(String imgUrL, final int USERIMAGE) {
        // 构造上传请求+getImageObjectKey()
        PutObjectRequest put = new PutObjectRequest(testBucket, imgUrL, uploadFilePath);
        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                Log.e("test", "onSuccess: " + request);
                // LoadingDialog.showDialogForLoading(UserHeadPortraitActivity.this, "上传中....");
            }
        });

        oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.e("test", "onSuccess: " + request.getObjectKey());
                Log.e("test", "onSuccess: " + result);
                oosuserpath = AppApi.ALYOOS + request.getObjectKey();
                if (oosuserpath.equals("")) {
                    ToastUtils.showToast("图片正在上传中....");
                } else {
                    GetupheadPortrait(oosuserpath);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                Log.e("test", "获取数据: " + request);
                Log.e("test", "获取数据: " + clientExcepion);
                Log.e("test", "获取数据: " + serviceException);
                // 请求异常
                if (clientExcepion != null) {
                    ToastUtils.showToast("");
                    ToastUtils.showShort(context, "网络异常,上传图片失败！");
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    ToastUtils.showShort(context, "图片上传失败!");
                }
            }
        });
    }*/
}
