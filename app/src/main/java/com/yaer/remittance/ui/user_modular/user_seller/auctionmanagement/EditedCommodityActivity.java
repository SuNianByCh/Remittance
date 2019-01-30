package com.yaer.remittance.ui.user_modular.user_seller.auctionmanagement;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AddCollectionBean;
import com.yaer.remittance.bean.GoodsClassModelsListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.LvCarIdsDailogAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ImagePickerAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AddCollectionBeanDao;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.CommodityActivity;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.ImageGridActivity;
import com.yaer.remittance.utils.AppPickerView;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoaders;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SelectDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.UIAlertView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑商品
 */
public class EditedCommodityActivity extends BaseActivity implements
        ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener,
        CustomTitlebar.TitleBarOnClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;//允许选择图片最大数
    @BindView(R.id.commodity_recyclerView)
    RecyclerView recyclerView;
    /*发布商品按钮*/
    @BindView(R.id.btn_commodity_confirm)
    Button btn_commodity_confirm;
    /*商品名称*/
    @BindView(R.id.et_Trade_name)
    EditText et_Trade_name;
    /*商品描述*/
    @BindView(R.id.et_Commodity_Description)
    MyEditText et_Commodity_Description;
    /*出售价格*/
    @BindView(R.id.et_Selling_price)
    EditText et_Selling_price;
    /*商品数量*/
    @BindView(R.id.et_commodity_number)
    EditText et_commodity_number;
    /*邮寄费用*/
    @BindView(R.id.et_mail_Cost)
    EditText et_mail_Cost;
    //商品分类
    @BindView(R.id.tv_commodity_type)
    TextView tv_commodity_type;
    /*    @BindView(R.id.btn_commodity_drafts)
        TextView btn_commodity_drafts;*/
    private int uid;//用户id
    private String utoken;//用户身份标识\
    @BindView(R.id.ct_release_commodity)
    CustomTitlebar ct_release_commodity;
    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           /* if (msg.what == 0) {

            }*/
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
        mImmersionBar.titleBar(R.id.ct_release_commodity).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_edited_commodity;
    }

    private String gid;//商品id
    private String gpostage;//快递费
    private int gissoldout;//是否上架下架
    private String gauctiontime;//拍卖时间
    private String gstoptime;//结束拍卖时间
    private String gaddprice;//加价幅度
    private String gstartingprice;//起拍价格
    private String gvideo;//视频
    private String imageitem;//图片
    private int gcid;//商品分类id
    private String gdesc;//拍品详情
    private int gnumber;//库存
    private String gmoney;//金额
    private int gisauction;//是否是商品还是拍品
    private String gname;//商品名称
    //分类id
    private int stype;

    @Override
    public void initView() {
        ct_release_commodity.setAction(this);
        uid = AppUtile.getUid(this);
        utoken = AppUtile.getTicket(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            gid = getIntent().getStringExtra("gid");//商品id
            gpostage = getIntent().getStringExtra("gpostage");//快递费
            gissoldout = getIntent().getIntExtra("gissoldout", 0);//是否上架下架
            selImageList = getIntent().getParcelableArrayListExtra("imageitem");//商品图片
            gvideo = getIntent().getStringExtra("gvideo");//商品视频
            gcid = getIntent().getIntExtra("gcid", 0);//分类id
            gdesc = getIntent().getStringExtra("gdesc");//拍品详情
            gnumber = getIntent().getIntExtra("gnumber", 0);//库存
            gmoney = getIntent().getStringExtra("gmoney");//金额
            gisauction = getIntent().getIntExtra("gisauction", 0);//是否是商品还是拍品
            gname = getIntent().getStringExtra("gname");//商品名称

            et_Trade_name.setText(gname);//商品名称
            et_Commodity_Description.setText(gdesc);//商品描述
            et_Selling_price.setText(gmoney);//商品价格
            et_commodity_number.setText("" + gnumber);//商品数量
            et_mail_Cost.setText(gpostage);
        }
        initImagePicker();
        initWidget();
        getGoodsClassIFicationModels();
    }

    @Override
    public void initData() {

    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaders());   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);               //选中数量限制
        imagePicker.setMultiMode(true);                        //多选
       /* imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素*/
    }

    private void initWidget() {
        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);*/
        if (selImageList == null)
            selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount, recyclerView);
        adapter.setOnItemClickListener(this);
        adapter.setDataChangeLisenter(new ImagePickerAdapter.IDataChangeLisenter() {
            @Override
            public void chanage(ArrayList<ImageItem> datas) {
                selImageList = new ArrayList<>();
                selImageList.addAll(datas);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener
                                            listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    //获取分类集合
    private List<GoodsClassModelsListBean> goodsification = new ArrayList<>();
    List<String> listname = new ArrayList<>();
    /*分类选择标签*/
    private OptionsPickerView pvOptions;

    @OnClick({R.id.btn_commodity_confirm, R.id.tv_commodity_type})
    public void onClick(View v) {
        switch (v.getId()) {
            /*    *//*保存草稿箱*//*
            case R.id.btn_commodity_drafts:
                saveDate();
                break;*/
            case R.id.tv_commodity_type:
                showPickerView(v, listname);
                break;
            /*发布*/
            case R.id.btn_commodity_confirm:
                upLoad();
                break;
        }
    }

    /*判断发布的方法*/
    private void upLoad() {
        if (judInput()) {
            uploadSync();
            photoDiloag = new LoadingDialog2(this, "正在上传...");
            photoDiloag.show();
        }
    }


    public void saveDate() {
        if (judInput()) {
            AddCollectionBean addCollectionBean = new AddCollectionBean();
            addCollectionBean.setSourceType(AddCollectionBean.TYPE_SHANGPIN);
            addCollectionBean.setSaveTime(System.currentTimeMillis());
            addCollectionBean.setImagesList(selImageList);
            addCollectionBean.setGoodsName(et_Trade_name.getText().toString());//商品名称
            addCollectionBean.setGoodsDesc(et_Commodity_Description.getText().toString());
            addCollectionBean.setGoodsPrice(et_Selling_price.getText().toString());
            addCollectionBean.setGoodsNumber(et_commodity_number.getText().toString());
            addCollectionBean.setGoodsType(tv_commodity_type.getText().toString());
            AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean);
            if (AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean)) {
                ToastUtils.showToast("保存成功");
                finish();
            } else {
                ToastUtils.showToast("保存失败");
            }
        }
    }

    private boolean judInput() {
        boolean isRight = false;
        if (!NetworkUtils.isNetworkConnected(this)) {
            ToastUtils.showToast("当前无网络状态，请连接网络");
        } else if (selImageList == null || selImageList.size() == 0) {
            ToastUtils.showToast("请选择图片，上传图片不能为空");
        } else if (!AppUtile.isEditText(et_Trade_name)) {
            ToastUtils.showToast("商品名称不能为空！");
        } else if (!AppUtile.isEditText(et_Commodity_Description)) {
            ToastUtils.showToast("商品描述不能为空！");
        } else if (!AppUtile.isEditText(et_Selling_price)) {
            ToastUtils.showToast("出售价格不能为空！");
        } else if (!AppUtile.isEditText(et_commodity_number)) {
            ToastUtils.showToast("商品数量不能为空！");
        }/*保证金暂时不判断，后期 添加*//* else if (!AppUtile.isEmpty(tv_cropTime)) {
                    ToastUtils.showToast("拍卖时间不能为空！");
                }*//* else if (!AppUtile.isEmpty(tv_commodity_type)) {
            ToastUtils.showToast("商品分类不能为空！");
        } */ else {
            isRight = true;
        }
        return isRight;
    }

    /**
     * dialog 隐藏
     */
    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    /**
     * 发布商品*
     *
     * @param commodity_num //商品数量
     * @param mailcost      //商品邮费
     * @param gname         //商品名称
     * @param gdesc         //商品描述
     * @param gisauction    //商品标识
     * @param gmoney        //出售价格
     * @param data          //图片地址
     * @param gissoldout    //是否下架
     */
    public void GetInsertAuctionGoods(String gname, String gdesc, String gisauction, String
            gmoney, String data, String gissoldout, String commodity_num, String mailcost) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.SELECTINSERTGOODS)
                .tag(this)
                .params("uid", uid)
                .params("utoken", utoken)
                .params("gname", gname)
                .params("gisauction", gisauction)
                .params("gmoney", gmoney)
                .params("gdesc", gdesc)
                .params("gcid", gcid)
                .params("gimg", data)
                .params("gissoldout", gissoldout)
                .params("gnumber", commodity_num)
                .params("gpostage", mailcost)
                .params("gvideo", "")
                .execute(new JsonCallback<BaseMode>(this) {
                    /**
                     * 进入到网络请求启动*/
                    @Override
                    public void onStart(Request<BaseMode, ? extends Request> request) {
                        super.onStart(request);
                        // LoadingDialog.showDialogForLoading(mActivity, null);
                    }

                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            stopDialog();
                            ToastUtils.showShort(EditedCommodityActivity.this, response.body().msg);
                            finish();
                        } else {
                            stopDialog();
                            ToastUtils.showShort(EditedCommodityActivity.this, response.body().msg);
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        // ToastUtils.showShort(getActivity(), response.body().msg);
                        stopDialog();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        stopDialog();
                        //  LoadingDialog.hide();
                    }
                });
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
                    tv_commodity_type.setText(goodsification.get(options1).getGcname());
                    stype = goodsification.get(options1).getGcid();
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
        } else {
            pvOptions.show(view);
        }
    }

    /**
     * 获取商品二级分类
     */
    private void getGoodsClassIFicationModels() {
        String Scid = (String) SharedPreferencesUtils.getData(EditedCommodityActivity.this, "Scid", "");
        OkGo.<BaseMode<List<GoodsClassModelsListBean>>>post(AppApi.BASE_URL + AppApi.GETGOODSCLASSMODELS)
                .tag(this)
                .params("scid", Scid)
                .execute(new JsonCallback<BaseMode<List<GoodsClassModelsListBean>>>(EditedCommodityActivity.this) {
                    @Override
                    public void onSuccess(Response<BaseMode<List<GoodsClassModelsListBean>>> response) {
                        Log.e("test", "onSuccess: " + response.body().code);
                        Log.e("test", "onSuccess: " + response.body().msg);
                        Log.e("test", "onSuccess: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            goodsification = response.body().result;
                            if (!goodsification.equals("") && goodsification.size() > 0) {
                                for (int i = 0; i < goodsification.size(); i++) {
                                    listname.add(goodsification.get(i).getGcname());
                                }
                            }
                        } else {
                            ToastUtils.showShort(EditedCommodityActivity.this, response.body().msg);
                        }
                    }
                });
    }

    private void uploadSync() {
        selImageList = adapter.getImages();//by da ben   姑奶奶上传改的这里,你改的就是拍品的这个图片数据
        if (null == selImageList || selImageList.size() == 0) {
            return;
        } // 上传文件
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean success = true;
                list.clear();
                for (int i = 0; success && i < selImageList.size(); i++) {
                    String path = selImageList.get(i).path;
                    if (path.startsWith("http://") || path.startsWith("https://")) {
                        list.add(path);//修改这里好了
                        continue;//这里回滚了
                    }
                    String userpath = "auction/goods/" + getImageObjectKey();
                    if (!showoos(path, userpath)) {
                        success = false;
                    }
                }
                if (success) {
                    String data = listToString(list);//上传完成的图片集合
                    String gname = et_Trade_name.getText().toString().trim();//商品名称
                    String gdesc = et_Commodity_Description.getText().toString().trim();//商品描述
                    String gmoney = et_Selling_price.getText().toString().trim();//出售价格
                    String commodity_num = et_commodity_number.getText().toString().trim();//商品数量
                    String mailcost = et_mail_Cost.getText().toString().trim();
                    String gisauction = "0";//商品标识
                    String gissoldout = "0";//是否下架
                    //发布的方法
                    GetInsertAuctionGoods(gname, gdesc, gisauction, gmoney, data, gissoldout, commodity_num, mailcost);
                }
            }
        });
        thread.start();
    }

    private OSS oss;
    private String testBucket;
    private String uploadFilePath;
    private ArrayList<String> list = new ArrayList<>();

    /**
     * 阿里云上传文件初始化
     *
     * @param 、、        上下文
     * @param 、、阿里云配置信息
     * @param 、、        文件路径
     */
    private boolean showoos(String filePath, String path) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIUgkh1Pv4ANqb", "VeLY0skMK0qdJ6lkXe5etWPCpaBvIe");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(EditedCommodityActivity.this, "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
        testBucket = "paipinhui";
        this.uploadFilePath = filePath;
        Log.e("test", "showoos: " + path);
        return asyncPutObjectFromLocalFile(path);
    }

    private boolean asyncPutObjectFromLocalFile(String imgUrL) {

        PutObjectRequest put = new PutObjectRequest(testBucket, imgUrL, uploadFilePath);
        try {
            PutObjectResult putResult = oss.putObject(put);
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
            String url = oss.presignPublicObjectURL(testBucket, imgUrL);
            list.add(url);
            return true;
        } catch (final ClientException e) {
            // 本地异常如网络异常等
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    // ToastUtils.showToast(e.getMessage());
                    Toast.makeText(EditedCommodityActivity.this, "本地异常，图片上传失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (final ServiceException e) {
            // 服务异常
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    Toast.makeText(EditedCommodityActivity.this, "服务器异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    //  ToastUtils.showToast(e.getMessage());
                }
            });
        }
        return false;
    }

    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - adapter.getImages().size());
                                Intent intent = new Intent(EditedCommodityActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - adapter.getImages().size());
                                Intent intent1 = new Intent(EditedCommodityActivity.this, ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }
                    }
                }, names);
                break;
            default:
                //打开预览
                ImagePicker.getInstance().setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
                        Glide.with(activity).load(path).into(imageView);
                    }

                    @Override
                    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
                        Glide.with(activity).load(path).into(imageView);
                    }

                    @Override
                    public void clearMemoryCache() {

                    }
                });
                Intent intentPreview = new Intent(EditedCommodityActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    //将list转换为带有 ， 的字符串
    public static String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    private void closeDialog() {
        final UIAlertView delDialog = new UIAlertView(EditedCommodityActivity.this, "温馨提示", "返回数据将不会保存",
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
                                           finish();
                                       }
                                   }
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //  closeDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void performAction(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                finish();
                //closeDialog();
                break;
        }
    }
}


