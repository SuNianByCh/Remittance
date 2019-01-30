package com.yaer.remittance.ui.user_modular.user_seller.addcollection;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
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
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AddCollectionBean;
import com.yaer.remittance.bean.GoodsClassModelsListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.LvCarIdsDailogAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ImagePickerAdapter;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoaders;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SelectDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.UIAlertView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 上传拍品
 */
public class AuctionGoodFragment extends BaseLazyFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //发布拍品按钮
    @BindView(R.id.btn_lotRelease)
    Button btn_lotRelease;
    @BindView(R.id.et_LotName)
    EditText et_LotName;//拍品名称
    @BindView(R.id.et_Lotdetails)
    MyEditText et_Lotdetails;//拍品介绍
    @BindView(R.id.et_LotStarting_Price)
    EditText et_LotStarting_Price;//起拍价格
    @BindView(R.id.et_LotMarkup)
    EditText et_LotMarkup;//加价幅度
    /*   @BindView(R.id.tv_LotBond)
       TextView tv_LotBond;//保证金*/
    @BindView(R.id.tv_StartTime)
    TextView tv_StartTime;//拍卖起始时间
    @BindView(R.id.tv_EndTime)
    TextView tv_EndTime;//拍卖截止时间
    /*拍品分类*/
    @BindView(R.id.tv_auction_good_type)
    TextView tv_auction_good_type;
    /*商品原价格*/
    @BindView(R.id.et_auction_price)
    EditText et_auction_price;
    @BindView(R.id.et_auction_freight)
    EditText et_auction_freight;
/*
    @BindView(R.id.btndrafts)
    TextView btndrafts;
*/
    /* @BindView(R.id.tv_type)
     TextView tv_type;//拍品分类*/
    /*同步到拍场*/
    @BindView(R.id.sc_notice_voice)
    Switch sc_notice_voice;
    private String isaddress = "0";
    private TimePickerView pvTime;//时间选择器
    private OptionsPickerView pvCustomOptions;
    private ArrayList<String> cardItem = new ArrayList<>();
    //private List<GoodsClassIFicationModelsBean> goodsification = new ArrayList<>();
    LvCarIdsDailogAdapter lvCarIdsDailogAdapter;
    private int uid;//用户id
    private String utoken;//用户身份标识
    private int size = 0;
    String starttime;//起始时间
    private Date mStartTime;
    private Date mEndTime;
    String endtime;//结束时间

    private long mTimeStart;
    private long mTimeEnd;
    private long mTodayTime;


    /**
     * dialog
     */
    private LoadingDialog2 photoDiloag;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           /* switch (msg.what) {
                case Constant.UPLOAD_SUCCESS:
                    break;
                case Constant.UPLOAD_LOCAL_ERROR:
                    Toast.makeText(getActivity(), "本地异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.UPLOAD_SERVER_ERROR:
                    Toast.makeText(getActivity(), "服务器异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }*/
            super.handleMessage(msg);
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_auction_good;
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(getActivity());
        utoken = AppUtile.getTicket(getActivity());
        sc_notice_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /*同步到拍场*/
                    isaddress = "1";
                    // showDelDialog();
                } else {
                    /*不同步到拍场*/
                    isaddress = "0";
                }
            }
        });
        //最好放到 Application oncreate执行
        getGoodsClassIFicationModels();
        initImagePicker();
        initWidget();
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
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getActivity(), selImageList, maxImgCount,recyclerView);
        adapter.setOnItemClickListener(this);
        adapter.setDataChangeLisenter(new ImagePickerAdapter.IDataChangeLisenter() {
            @Override
            public void chanage(ArrayList<ImageItem> datas) {
                selImageList = datas;
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    //弹出拍照dialog
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style.transparentFrameWindowStyle, listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
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
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size()-1);
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
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
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
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

    //获取分类集合
    private List<GoodsClassModelsListBean> goodsification = new ArrayList<>();
    List<String> listname = new ArrayList<>();
    /*分类选择标签*/
    private OptionsPickerView pvOptions;
    //分类id
    private int stype;

    @OnClick({R.id.btn_lotRelease, R.id.tv_EndTime, R.id.tv_auction_good_type, R.id.tv_StartTime})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_auction_good_type:
              /*  //获取商品分类
                getGoodsClassIFicationModels();
                View bottomView = View.inflate(getActivity(), R.layout.carids_dialog, null);//填充ListView布局
                RecyclerView lvCarIds = bottomView.findViewById(R.id.lv_carids);//初始化ListView控件
                //lvCarIds.setAdapter(new LvCarIdsDailogAdapter(getActivity()));//ListView设置适配器
                lvCarIds.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                lvCarIdsDailogAdapter = new LvCarIdsDailogAdapter();
                lvCarIds.setAdapter(lvCarIdsDailogAdapter);
                AlertDialog parkIdsdialog = new AlertDialog.Builder(getActivity())
                        .setView(bottomView)//在这里把写好的这个listview的布局加载dialog中
                        .create();
                parkIdsdialog.show();*/
                showPickerView(v, listname);
                break;
            /*保存草稿箱*/
            /*case R.id.btndrafts:
                save();
                break;*/
            /*发布按钮*/
            case R.id.btn_lotRelease:
/*
                if (selImageList.size() == 0) {
                    ToastUtils.showToast("请选择图片，上传图片不能为空");
                } else if (!AppUtile.isEditText(et_Trade_name)) {
                    ToastUtils.showToast("商品名称不能为空！");
                } else if (!AppUtile.isEditText(et_Commodity_Description)) {
                    ToastUtils.showToast("商品描述不能为空！");
                } else if (!AppUtile.isEditText(et_Selling_price)) {
                    ToastUtils.showToast("出售价格不能为空！");
                } else if (!AppUtile.isEditText(et_commodity_number)) {
                    ToastUtils.showToast("商品数量不能为空！");
                }*//*保证金暂时不判断，后期 添加*//**//* else if (!AppUtile.isEmpty(tv_cropTime)) {
                    ToastUtils.showToast("拍卖时间不能为空！");
                }*//* else if (!AppUtile.isEmpty(tv_commodity_type)) {
                    ToastUtils.showToast("商品分类不能为空！");
                } else {
                    if (selImageList.size() != 0) {
                        uploadSync();
                        photoDiloag = new LoadingDialog2(getActivity(), "正在上传...");
                        photoDiloag.show();
                    }
                }*/

               /* if (selImageList.size() == 0) {
                    ToastUtils.showToast("请选择图片，上传图片不能为空");
                } else if (!AppUtile.isEditText(et_LotName)) {
                    ToastUtils.showToast("拍品名称不能为空！");
                } else if (!AppUtile.isEditText(et_Lotdetails)) {
                    ToastUtils.showToast("拍品描述不能为空！");
                } else if (!AppUtile.isEditText(et_LotStarting_Price)) {
                    ToastUtils.showToast("起拍价格不能为空！");
                } else if (!AppUtile.isEditText(et_LotMarkup)) {
                    ToastUtils.showToast("加价幅度不能为空！");
                } else if (!AppUtile.isEditText(et_auction_freight)) {
                    ToastUtils.showToast("运费不能为空！");
                } else if (!AppUtile.isEmpty(tv_StartTime)) {
                    ToastUtils.showToast("拍卖起始时间不能为空！");
                } else if (!AppUtile.isEmpty(tv_EndTime)) {
                    ToastUtils.showToast("拍卖截止时间不能为空！");
                } *//*else if (isaddress.equals("1")) {
                    showDelDialog();
                } *//* else {

                }*/
                //upLoad();
                upLoad();
                break;
            /*拍卖起始时间*/
            case R.id.tv_StartTime:
                initStartTimePicker();
                break;
            /*拍卖截止时间*/
            case R.id.tv_EndTime:
                initCustomTimePicker();
                break;
        }
    }


    private void upLoad() {
        if (jugeInput()) {
            uploadSync();
            photoDiloag = new LoadingDialog2(getActivity(), "正在上传...");
            photoDiloag.show();
        }

    }

    private boolean jugeInput() {
        boolean result = false;
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            ToastUtils.showToast("当前无网络状态，请连接网络");
        } else if (selImageList == null || selImageList.size() == 0) {
            ToastUtils.showToast("请选择图片，上传图片不能为空");
        } else if (!AppUtile.isEditText(et_LotName)) {
            ToastUtils.showToast("拍品名称不能为空！");
        } else if (!AppUtile.isEditText(et_Lotdetails)) {
            ToastUtils.showToast("拍品描述不能为空！");
        } else if (!AppUtile.isEditText(et_LotStarting_Price)) {
            ToastUtils.showToast("起拍价格不能为空！");
        } else if (!AppUtile.isEditText(et_LotMarkup)) {
            ToastUtils.showToast("加价幅度不能为空！");
        } else if (!AppUtile.isEditText(et_auction_freight)) {
            ToastUtils.showToast("运费不能为空！");
        } else if (!AppUtile.isEmpty(tv_StartTime)) {
            ToastUtils.showToast("拍卖起始时间不能为空！");
        } else if (!AppUtile.isEmpty(tv_EndTime)) {
            ToastUtils.showToast("拍卖截止时间不能为空！");
        } else if (!AppUtile.isEmpty(tv_auction_good_type)) {
            ToastUtils.showToast("商品分类不能为空！");
        } else {
            result = true;
        }
        return result;
    }

    private void save() {
        if (jugeInput()) {
            AddCollectionBean addCollectionBean = new AddCollectionBean();
            addCollectionBean.setSourceType(AddCollectionBean.TYPE_PAI_PING);//数据类型
            addCollectionBean.setImagesList(selImageList);//图片
            addCollectionBean.setGoodsName(et_LotName.getText().toString());//拍品名称
            addCollectionBean.setGoodsDesc(et_Lotdetails.getText().toString());//拍品描述
            addCollectionBean.setStartPrice((et_LotStarting_Price.getText().toString()));//起拍价格
            addCollectionBean.setMinAddPrice(et_LotMarkup.getText().toString());//加价幅度
            addCollectionBean.setTrafficPrice(et_auction_price.getText().toString());//运费
            addCollectionBean.setGoodsSailTime(tv_StartTime.getText().toString());//拍卖时间
            addCollectionBean.setEndTime(tv_EndTime.getText().toString());//拍卖截止时间
            addCollectionBean.setSaveTime(System.currentTimeMillis());//保存时间
            AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean);
            if (AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean)) {
                ToastUtils.showToast("save success");
            } else {
                ToastUtils.showToast("save failed");
            }

        }
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
     * 展示选择器
     * 核心代码
     */
    private void showPickerView(View view, List<String> listname) {
       /* //监听选中
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_auction_good_type.setText(goodsification.get(options1).getGcname());
                    stype = goodsification.get(options1).getGcid();
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setTitleText("入驻分类")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(false)//点击背的地方不消失
                    .build();//创建
            //把数据绑定到控件上面
            pvOptions.setPicker(listname);
            //展示
            pvOptions.show(view);
        } else {
            pvOptions.show(view);
        }*/
    }

    private void uploadSync() {
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
                    String userpath = "auction/goods/" + getImageObjectKey();
                    // showoos(path, userpath);
                    if (!showoos(path, userpath)) {
                        success = false;
                    }
                }
                if (success) {
                    String gname = et_LotName.getText().toString().trim();//拍品名称
                    String gmoney = et_auction_price.getText().toString().trim();//"1999.0";//商品价格
                    String gdesc = et_Lotdetails.getText().toString().trim();//拍品详情
                    String gimg = listToString(list);//商品图片
                    String gvideo = "";//商品视频
                    String gstartingprice = et_LotStarting_Price.getText().toString().trim();//商品起拍价格
                    String gaddprice = et_LotMarkup.getText().toString().trim();//商品加价幅度
                    String gcollateral = "1";//= tv_LotBond.getText().toString().trim();//拍卖保证金
                    try {
                        starttime = SystemUtil.dateToStamp1(tv_StartTime.getText().toString().trim());//开始时间戳
                        endtime = SystemUtil.dateToStamp1(tv_EndTime.getText().toString().trim());//结束时间戳
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String gauctiontime = starttime;// tv_StartTime.getText().toString().trim();//拍卖起始时间
                    String gstoptime = endtime;//tv_EndTime.getText().toString().trim();//拍品截止时间
                    String freight = et_auction_freight.getText().toString().trim();//运费
                    String gisauction = "1";//商品标识
                    String gissoldout = "0";//是否下架
                    GetInsertGoods(gname, gisauction, gmoney, gdesc, gimg, gvideo, gstartingprice, gaddprice, gstoptime, gcollateral, gauctiontime, gissoldout, freight);
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
     * 阿里云上传文件初始化正面身份证
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
        oss = new OSSClient(getActivity(), "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
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
           /* if (handler != null) {
                if (list.size() == selImageList.size()) {
                    handler.sendEmptyMessage(Constant.UPLOAD_SUCCESS);
                }
            }*/
            return true;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            //  handler.sendEmptyMessage(Constant.UPLOAD_LOCAL_ERROR);
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    // ToastUtils.showToast(e.getMessage());
                    Toast.makeText(getActivity(), "本地异常，图片上传失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (ServiceException e) {
            // 服务异常
            //handler.sendEmptyMessage(Constant.UPLOAD_SERVER_ERROR);
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    Toast.makeText(getActivity(), "服务器异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    //  ToastUtils.showToast(e.getMessage());
                }
            });
        }
        return false;
    }

    /*拍卖起始时间选择器*/
    private void initStartTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
     /*   Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //这里是选中回调的
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
                String format = simpleDateFormat.format(date);
                tv_StartTime.setText(format);
              *//*  Date datatow=getDateAfter(new Date(System.currentTimeMillis()),3);
                if (!date.after(new Date(System.currentTimeMillis()))) {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                    //ToastUtils.showToast("after true");
                }else if(date.getTime()>datatow.getTime()){
                    ToastUtils.showToast("开始时间最多能预售3天，请重新选择");
                } else {

                }*//*
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .isCyclic(true)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        pvTime.show();*/
    }

    /*拍卖截止时间选择器*/
    private void initCustomTimePicker() {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
       /* Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
                String format = simpleDateFormat.format(date);
                String starttime = tv_StartTime.getText().toString().trim();
                Date startdate = null;//起始时间
                if (!date.after(new Date(System.currentTimeMillis()))) {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                } else {
                    try {
                        startdate = simpleDateFormat.parse(starttime);
                        int daynumber = (int) dateDiff(starttime, format, "yyyy年MM月dd日-HH时mm分");
                        if (date.getTime() <= startdate.getTime()) {
                            ToastUtils.showToast("结束时间不能小于开始时间，请重新选择");
                        } else if (daynumber < 3) {
                            ToastUtils.showToast("开始时间和结束时间，间隔最小为3天");
                        } else if (daynumber > 7) {
                            ToastUtils.showToast("开始时间和结束时间，间隔最大为7天");
                        } else {
                            tv_EndTime.setText(format);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                *//*if (date.after(new Date(System.currentTimeMillis()))) {
                    tv_EndTime.setText(format);
                } else {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                }*//*
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .isCyclic(true)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        pvTime.show();*/
    }

    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day >= 1) {
                return day;
            } else {
                if (day == 0) {
                    return 1;
                } else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }

    /**
     * 获取商品二级分类
     */
    private void getGoodsClassIFicationModels() {
        String Scid = (String) SharedPreferencesUtils.getData(getActivity(), "Scid", "");
        OkGo.<BaseMode<List<GoodsClassModelsListBean>>>post(AppApi.BASE_URL + AppApi.GETGOODSCLASSMODELS)
                .tag(this)
                .params("scid", Scid)
                .execute(new JsonCallback<BaseMode<List<GoodsClassModelsListBean>>>(getActivity()) {
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
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }

    /**
     * 发布商品*
     *
     * @param gname//拍品名称
     * @param gisauction//商品标识
     * @param gmoney//商品价格
     * @param gdesc//拍品详情
     * @param //商品分类id
     * @param gimg//商品图片
     * @param gvideo//商品视频
     * @param gstartingprice//商品起拍价格
     * @param gaddprice//商品加价幅度
     * @param gstoptime//拍品截止时间
     * @param gcollateral//拍卖保证金
     * @param gauctiontime//拍卖起始时间
     * @param gissoldout//是否下架
     * @param freight//运费
     */
    public void GetInsertGoods(String gname, String gisauction, String gmoney, String gdesc, String gimg, String gvideo, String gstartingprice, String gaddprice, String gstoptime, String gcollateral, String gauctiontime, String gissoldout, String freight) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.SELECTINSERTGOODS)
                .tag(this)
                .params("uid", uid)
                .params("utoken", utoken)
                .params("gname", gname)
                .params("gisauction", gisauction)
                .params("gmoney", gmoney)
                .params("gdesc", gdesc)
                .params("gcid", stype)
                .params("gimg", gimg)
                .params("gvideo", gvideo)
                .params("gstartingprice", gstartingprice)
                .params("gaddprice", gaddprice)
                .params("gstoptime", gstoptime)
                .params("gcollateral", gcollateral)
                .params("gauctiontime", gauctiontime)
                .params("gissoldout", gissoldout)
                .params("gpostage", freight)
                .params("gispecial", isaddress)
                .execute(new JsonCallback<BaseMode>(getActivity()) {
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
                            ToastUtils.showShort(getActivity(), response.body().msg);
                            stopDialog();
                            getActivity().finish();
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                            stopDialog();
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        stopDialog();
                        //ToastUtils.showShort(getActivity(), response.body().msg);
                        //LoadingDialog.hide();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //LoadingDialog.hide();
                        stopDialog();
                    }
                });
    }

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "拍品同步到拍卖专场需要交纳" + 500 + "请前往充值页面充值",
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
}
























/*
截止 2019年1月13日12:51:08
package com.yaer.remittance.ui.user_modular.user_seller.addcollection;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
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
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseLazyFragment;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.bean.AddCollectionBean;
import com.yaer.remittance.bean.GoodsClassModelsListBean;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.ui.adapter.LvCarIdsDailogAdapter;
import com.yaer.remittance.ui.user_modular.user_buyer.balance.RechargeActivity;
import com.yaer.remittance.ui.user_modular.user_seller.adapter.ImagePickerAdapter;
import com.yaer.remittance.ui.user_modular.user_seller.enter.UserEnterActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.GlideImageLoaders;
import com.yaer.remittance.utils.LoadingDialog;
import com.yaer.remittance.utils.NetworkUtils;
import com.yaer.remittance.utils.SelectDialog;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.SystemUtil;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.LoadingDialog2;
import com.yaer.remittance.view.MyEditText;
import com.yaer.remittance.view.UIAlertView;

import org.litepal.util.LogUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;


*/
/**
 * 上传拍品
 * <p>
 * dialog
 * <p>
 * dialog 隐藏
 * <p>
 * 展示选择器
 * 核心代码
 * <p>
 * 阿里云上传文件初始化正面身份证
 *
 * @param 、、        上下文
 * @param 、、阿里云配置信息
 * @param 、、        文件路径
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * <p>
 * 得到几天后的时间
 * @param d
 * @param day
 * @return 获取商品二级分类
 * <p>
 * 发布商品*
 * @param gname//拍品名称
 * @param gisauction//商品标识
 * @param gmoney//商品价格
 * @param gdesc//拍品详情
 * @param //商品分类id
 * @param gimg//商品图片
 * @param gvideo//商品视频
 * @param gstartingprice//商品起拍价格
 * @param gaddprice//商品加价幅度
 * @param gstoptime//拍品截止时间
 * @param gcollateral//拍卖保证金
 * @param gauctiontime//拍卖起始时间
 * @param gissoldout//是否下架
 * @param freight//运费
 * <p>
 * 进入到网络请求启动
 * <p>
 * dialog
 * <p>
 * dialog 隐藏
 * <p>
 * 展示选择器
 * 核心代码
 * <p>
 * 阿里云上传文件初始化正面身份证
 * @param 、、        上下文
 * @param 、、阿里云配置信息
 * @param 、、        文件路径
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * <p>
 * 得到几天后的时间
 * @param d
 * @param day
 * @return 获取商品二级分类
 * <p>
 * 发布商品*
 * @param gname//拍品名称
 * @param gisauction//商品标识
 * @param gmoney//商品价格
 * @param gdesc//拍品详情
 * @param //商品分类id
 * @param gimg//商品图片
 * @param gvideo//商品视频
 * @param gstartingprice//商品起拍价格
 * @param gaddprice//商品加价幅度
 * @param gstoptime//拍品截止时间
 * @param gcollateral//拍卖保证金
 * @param gauctiontime//拍卖起始时间
 * @param gissoldout//是否下架
 * @param freight//运费
 * <p>
 * 进入到网络请求启动
 * <p>
 * dialog
 * <p>
 * dialog 隐藏
 * <p>
 * 展示选择器
 * 核心代码
 * <p>
 * 阿里云上传文件初始化正面身份证
 * @param 、、        上下文
 * @param 、、阿里云配置信息
 * @param 、、        文件路径
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * <p>
 * 得到几天后的时间
 * @param d
 * @param day
 * @return 获取商品二级分类
 * <p>
 * 发布商品*
 * @param gname//拍品名称
 * @param gisauction//商品标识
 * @param gmoney//商品价格
 * @param gdesc//拍品详情
 * @param //商品分类id
 * @param gimg//商品图片
 * @param gvideo//商品视频
 * @param gstartingprice//商品起拍价格
 * @param gaddprice//商品加价幅度
 * @param gstoptime//拍品截止时间
 * @param gcollateral//拍卖保证金
 * @param gauctiontime//拍卖起始时间
 * @param gissoldout//是否下架
 * @param freight//运费
 * <p>
 * 进入到网络请求启动
 * <p>
 * dialog
 * <p>
 * dialog 隐藏
 * <p>
 * 展示选择器
 * 核心代码
 * <p>
 * 阿里云上传文件初始化正面身份证
 * @param 、、        上下文
 * @param 、、阿里云配置信息
 * @param 、、        文件路径
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * @description 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 * <p>
 * 得到几天后的时间
 * @param d
 * @param day
 * @return 获取商品二级分类
 * <p>
 * 发布商品*
 * @param gname//拍品名称
 * @param gisauction//商品标识
 * @param gmoney//商品价格
 * @param gdesc//拍品详情
 * @param //商品分类id
 * @param gimg//商品图片
 * @param gvideo//商品视频
 * @param gstartingprice//商品起拍价格
 * @param gaddprice//商品加价幅度
 * @param gstoptime//拍品截止时间
 * @param gcollateral//拍卖保证金
 * @param gauctiontime//拍卖起始时间
 * @param gissoldout//是否下架
 * @param freight//运费
 * <p>
 * 进入到网络请求启动
 *//*

public class AuctionGoodFragment extends BaseLazyFragment implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 9;               //允许选择图片最大数
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //发布拍品按钮
    @BindView(R.id.btn_lotRelease)
    Button btn_lotRelease;
    @BindView(R.id.et_LotName)
    EditText et_LotName;//拍品名称
    @BindView(R.id.et_Lotdetails)
    MyEditText et_Lotdetails;//拍品介绍
    @BindView(R.id.et_LotStarting_Price)
    EditText et_LotStarting_Price;//起拍价格
    @BindView(R.id.et_LotMarkup)
    EditText et_LotMarkup;//加价幅度
    */
/*   @BindView(R.id.tv_LotBond)
       TextView tv_LotBond;//保证金*//*

    @BindView(R.id.tv_StartTime)
    TextView tv_StartTime;//拍卖起始时间
    @BindView(R.id.tv_EndTime)
    TextView tv_EndTime;//拍卖截止时间
    */
/*拍品分类*//*

    @BindView(R.id.tv_auction_good_type)
    TextView tv_auction_good_type;
    */
/*商品原价格*//*

    @BindView(R.id.et_auction_price)
    EditText et_auction_price;
    @BindView(R.id.et_auction_freight)
    EditText et_auction_freight;
    @BindView(R.id.btndrafts)
    TextView btndrafts;
    */
/* @BindView(R.id.tv_type)
     TextView tv_type;//拍品分类*//*

 */
/*同步到拍场*//*

 */
/*
    @BindView(R.id.sc_notice_voice)
    Switch sc_notice_voice;
*//*

    // private String isaddress = "1";
    private TimePickerView pvTime;//时间选择器
    private OptionsPickerView pvCustomOptions;
    private ArrayList<String> cardItem = new ArrayList<>();
    //private List<GoodsClassIFicationModelsBean> goodsification = new ArrayList<>();
    LvCarIdsDailogAdapter lvCarIdsDailogAdapter;
    private int uid;//用户id
    private String utoken;//用户身份标识
    private int size = 0;
    String starttime;//起始时间
    private Date mStartTime;
    private Date mEndTime;
    String endtime;//结束时间
    */
/**
 * dialog
 *//*

    private LoadingDialog2 photoDiloag;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
           */
/* switch (msg.what) {
                case Constant.UPLOAD_SUCCESS:
                    break;
                case Constant.UPLOAD_LOCAL_ERROR:
                    Toast.makeText(getActivity(), "本地异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    break;
                case Constant.UPLOAD_SERVER_ERROR:
                    Toast.makeText(getActivity(), "服务器异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    break;
            }*//*

            super.handleMessage(msg);
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_auction_good;
    }

    @Override
    public void initView() {
        uid = AppUtile.getUid(getActivity());
        utoken = AppUtile.getTicket(getActivity());
*/
/*
        sc_notice_voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    *//*

 */
/*同步到拍场*//*
 */
/*

                    isaddress = "1";
                    //   showDelDialog();
                } else {
                    *//*

 */
/*不同步到拍场*//*
 */
/*
                    isaddress = "0";
                }
            }
        });
*//*

        //最好放到 Application oncreate执行
        getGoodsClassIFicationModels();
        initImagePicker();
        initWidget();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoaders());   //设置图片加载器
        imagePicker.setShowCamera(false);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);               //选中数量限制
        imagePicker.setMultiMode(true);                        //多选
       */
/* imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素*//*

    }

    private void initWidget() {
        */
/*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);*//*

        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(getActivity(), selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    //弹出拍照dialog
    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(getActivity(), R.style.transparentFrameWindowStyle, listener, names);
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
        return dialog;
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
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
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
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
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

    //获取分类集合
    private List<GoodsClassModelsListBean> goodsification = new ArrayList<>();
    List<String> listname = new ArrayList<>();
    */
/*分类选择标签*//*

    private OptionsPickerView pvOptions;
    //分类id
    private int stype;

    @OnClick({R.id.btn_lotRelease, R.id.tv_EndTime, R.id.tv_auction_good_type, R.id.tv_StartTime, R.id.btndrafts})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_auction_good_type:
              */
/*  //获取商品分类
                getGoodsClassIFicationModels();
                View bottomView = View.inflate(getActivity(), R.layout.carids_dialog, null);//填充ListView布局
                RecyclerView lvCarIds = bottomView.findViewById(R.id.lv_carids);//初始化ListView控件
                //lvCarIds.setAdapter(new LvCarIdsDailogAdapter(getActivity()));//ListView设置适配器
                lvCarIds.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                lvCarIdsDailogAdapter = new LvCarIdsDailogAdapter();
                lvCarIds.setAdapter(lvCarIdsDailogAdapter);
                AlertDialog parkIdsdialog = new AlertDialog.Builder(getActivity())
                        .setView(bottomView)//在这里把写好的这个listview的布局加载dialog中
                        .create();
                parkIdsdialog.show();*//*

                showPickerView(v, listname);
                break;
            */
/*保存草稿箱*//*

            case R.id.btndrafts:
                save();
                break;
            */
/*发布按钮*//*

            case R.id.btn_lotRelease:
*/
/*
                if (selImageList.size() == 0) {
                    ToastUtils.showToast("请选择图片，上传图片不能为空");
                } else if (!AppUtile.isEditText(et_Trade_name)) {
                    ToastUtils.showToast("商品名称不能为空！");
                } else if (!AppUtile.isEditText(et_Commodity_Description)) {
                    ToastUtils.showToast("商品描述不能为空！");
                } else if (!AppUtile.isEditText(et_Selling_price)) {
                    ToastUtils.showToast("出售价格不能为空！");
                } else if (!AppUtile.isEditText(et_commodity_number)) {
                    ToastUtils.showToast("商品数量不能为空！");
                }*//*
 */
/*保证金暂时不判断，后期 添加*//*
 */
/**//*
 */
/* else if (!AppUtile.isEmpty(tv_cropTime)) {
                    ToastUtils.showToast("拍卖时间不能为空！");
                }*//*
 */
/* else if (!AppUtile.isEmpty(tv_commodity_type)) {
                    ToastUtils.showToast("商品分类不能为空！");
                } else {
                    if (selImageList.size() != 0) {
                        uploadSync();
                        photoDiloag = new LoadingDialog2(getActivity(), "正在上传...");
                        photoDiloag.show();
                    }
                }*//*


 */
/* if (selImageList.size() == 0) {
                    ToastUtils.showToast("请选择图片，上传图片不能为空");
                } else if (!AppUtile.isEditText(et_LotName)) {
                    ToastUtils.showToast("拍品名称不能为空！");
                } else if (!AppUtile.isEditText(et_Lotdetails)) {
                    ToastUtils.showToast("拍品描述不能为空！");
                } else if (!AppUtile.isEditText(et_LotStarting_Price)) {
                    ToastUtils.showToast("起拍价格不能为空！");
                } else if (!AppUtile.isEditText(et_LotMarkup)) {
                    ToastUtils.showToast("加价幅度不能为空！");
                } else if (!AppUtile.isEditText(et_auction_freight)) {
                    ToastUtils.showToast("运费不能为空！");
                } else if (!AppUtile.isEmpty(tv_StartTime)) {
                    ToastUtils.showToast("拍卖起始时间不能为空！");
                } else if (!AppUtile.isEmpty(tv_EndTime)) {
                    ToastUtils.showToast("拍卖截止时间不能为空！");
                } *//*
 */
/*else if (isaddress.equals("1")) {
                    showDelDialog();
                } *//*
 */
/* else {

                }*//*

                //upLoad();
                upLoad();
                break;
            */
/*拍卖起始时间*//*

            case R.id.tv_StartTime:
                initStartTimePicker();
                break;
            */
/*拍卖截止时间*//*

            case R.id.tv_EndTime:
                initCustomTimePicker();
                break;
        }
    }


    private void upLoad() {
        if (jugeInput()) {
            uploadSync();
            photoDiloag = new LoadingDialog2(getActivity(), "正在上传...");
            photoDiloag.show();
        }

    }

    private boolean jugeInput() {
        boolean result = false;
        if (!NetworkUtils.isNetworkConnected(getActivity())) {
            ToastUtils.showToast("当前无网络状态，请连接网络");
        } else if (selImageList == null || selImageList.size() == 0) {
            ToastUtils.showToast("请选择图片，上传图片不能为空");
        } else if (!AppUtile.isEditText(et_LotName)) {
            ToastUtils.showToast("拍品名称不能为空！");
        } else if (!AppUtile.isEditText(et_Lotdetails)) {
            ToastUtils.showToast("拍品描述不能为空！");
        } else if (!AppUtile.isEditText(et_LotStarting_Price)) {
            ToastUtils.showToast("起拍价格不能为空！");
        } else if (!AppUtile.isEditText(et_LotMarkup)) {
            ToastUtils.showToast("加价幅度不能为空！");
        } else if (!AppUtile.isEditText(et_auction_freight)) {
            ToastUtils.showToast("运费不能为空！");
        } else if (!AppUtile.isEmpty(tv_StartTime)) {
            ToastUtils.showToast("拍卖起始时间不能为空！");
        } else if (!AppUtile.isEmpty(tv_EndTime)) {
            ToastUtils.showToast("拍卖截止时间不能为空！");
        } else if (!AppUtile.isEmpty(tv_auction_good_type)) {
            ToastUtils.showToast("商品分类不能为空！");
        } else {
            result = true;
        }
        return result;
    }

    private void save() {
        if (jugeInput()) {
            AddCollectionBean addCollectionBean = new AddCollectionBean();
            addCollectionBean.setSourceType(AddCollectionBean.TYPE_PAI_PING);//数据类型
            addCollectionBean.setImagesList(selImageList);//图片
            addCollectionBean.setGoodsName(et_LotName.getText().toString());//拍品名称
            addCollectionBean.setGoodsDesc(et_Lotdetails.getText().toString());//拍品描述
            addCollectionBean.setStartPrice((et_LotStarting_Price.getText().toString()));//起拍价格
            addCollectionBean.setMinAddPrice(et_LotMarkup.getText().toString());//加价幅度
            addCollectionBean.setTrafficPrice(et_auction_price.getText().toString());//运费
            addCollectionBean.setGoodsSailTime(tv_StartTime.getText().toString());//拍卖时间
            addCollectionBean.setEndTime(tv_EndTime.getText().toString());//拍卖截止时间
            addCollectionBean.setSaveTime(System.currentTimeMillis());//保存时间
            AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean);
            if (AddCollectionBeanDao.addAddCollectionBeanOrUpdate(addCollectionBean)) {
                ToastUtils.showToast("save success");
            } else {
                ToastUtils.showToast("save failed");
            }

        }
    }

    */
/**
 * dialog 隐藏
 *//*

    private void stopDialog() {
        if (photoDiloag != null && photoDiloag.isShowing()) {
            photoDiloag.dismiss();
        }
    }

    */
/**
 * 展示选择器
 * 核心代码
 *//*

    private void showPickerView(View view, List<String> listname) {
        //监听选中
        if (pvOptions == null) {
            pvOptions = new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    //展示选中数据
                    tv_auction_good_type.setText(goodsification.get(options1).getGcname());
                    stype = goodsification.get(options1).getGcid();
                }
            })
                    .setSelectOptions(0)//设置选择第一个
                    .setTitleText("入驻分类")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                    .setContentTextSize(20)
                    .setOutSideCancelable(false)//点击背的地方不消失
                    .build();//创建
            //把数据绑定到控件上面
            pvOptions.setPicker(listname);
            //展示
            pvOptions.show(view);
        } else {
            pvOptions.show(view);
        }
    }

    private void uploadSync() {
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
                    String userpath = "auction/goods/" + getImageObjectKey();
                    // showoos(path, userpath);
                    if (!showoos(path, userpath)) {
                        success = false;
                    }
                }
                if (success) {
                    String gname = et_LotName.getText().toString().trim();//拍品名称
                    String gmoney = et_auction_price.getText().toString().trim();//"1999.0";//商品价格
                    String gdesc = et_Lotdetails.getText().toString().trim();//拍品详情
                    String gimg = listToString(list);//商品图片
                    String gvideo = "";//商品视频
                    String gstartingprice = et_LotStarting_Price.getText().toString().trim();//商品起拍价格
                    String gaddprice = et_LotMarkup.getText().toString().trim();//商品加价幅度
                    String gcollateral = "1";//= tv_LotBond.getText().toString().trim();//拍卖保证金
                    try {
                        starttime = SystemUtil.dateToStamp1(tv_StartTime.getText().toString().trim());//开始时间戳
                        endtime = SystemUtil.dateToStamp1(tv_EndTime.getText().toString().trim());//结束时间戳
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String gauctiontime = starttime;// tv_StartTime.getText().toString().trim();//拍卖起始时间
                    String gstoptime = endtime;//tv_EndTime.getText().toString().trim();//拍品截止时间
                    String freight = et_auction_freight.getText().toString().trim();//运费
                    String gisauction = "1";//商品标识
                    String gissoldout = "0";//是否下架
                    GetInsertGoods(gname, gisauction, gmoney, gdesc, gimg, gvideo, gstartingprice, gaddprice, gstoptime, gcollateral, gauctiontime, gissoldout, freight);
                }
            }
        });
        thread.start();
    }

    private OSS oss;
    private String testBucket;
    private String uploadFilePath;
    private ArrayList<String> list = new ArrayList<>();

    */
/**
 * 阿里云上传文件初始化正面身份证
 *
 * @param 、、        上下文
 * @param 、、阿里云配置信息
 * @param 、、        文件路径
 *//*

    private boolean showoos(String filePath, String path) {
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("LTAIUgkh1Pv4ANqb", "VeLY0skMK0qdJ6lkXe5etWPCpaBvIe");
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSSLog.enableLog();
        oss = new OSSClient(getActivity(), "oss-cn-qingdao.aliyuncs.com", credentialProvider, conf);
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
           */
/* if (handler != null) {
                if (list.size() == selImageList.size()) {
                    handler.sendEmptyMessage(Constant.UPLOAD_SUCCESS);
                }
            }*//*

            return true;
        } catch (ClientException e) {
            // 本地异常如网络异常等
            //  handler.sendEmptyMessage(Constant.UPLOAD_LOCAL_ERROR);
            e.printStackTrace();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    // ToastUtils.showToast(e.getMessage());
                    Toast.makeText(getActivity(), "本地异常，图片上传失败", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (ServiceException e) {
            // 服务异常
            //handler.sendEmptyMessage(Constant.UPLOAD_SERVER_ERROR);
            Log.e("RequestId", e.getRequestId());
            Log.e("ErrorCode", e.getErrorCode());
            Log.e("HostId", e.getHostId());
            Log.e("RawMessage", e.getRawMessage());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //取消进度条
                    stopDialog();
                    Toast.makeText(getActivity(), "服务器异常，图片上传失败", Toast.LENGTH_SHORT).show();
                    //  ToastUtils.showToast(e.getMessage());
                }
            });
        }
        return false;
    }

    */
/*拍卖起始时间选择器*//*

    private void initStartTimePicker() {
        */
/**
 * @description
 *
 * 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 *//*

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                //这里是选中回调的
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
                String format = simpleDateFormat.format(date);
                Date datatow=getDateAfter(new Date(System.currentTimeMillis()),3);
                if (!date.after(new Date(System.currentTimeMillis()))) {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                    //ToastUtils.showToast("after true");
                }else if(date.getTime()>datatow.getTime()){
                    ToastUtils.showToast("开始时间最多能预售3天，请重新选择");
                } else {
                    tv_StartTime.setText(format);
                }
            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .isCyclic(true)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        pvTime.show();
    }

    */
/*拍卖截止时间选择器*//*

    private void initCustomTimePicker() {
        */
/**
 * @description
 *
 * 注意事项：
 * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
 * 具体可参考demo 里面的两个自定义layout布局。
 * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
 * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
 *//*

        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvTime = new TimePickerView.Builder(getActivity(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
                String format = simpleDateFormat.format(date);
                String starttime=tv_StartTime.getText().toString().trim();
                Date startdate = null;//起始时间
                if (!date.after(new Date(System.currentTimeMillis()))) {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                } else {
                    try {
                        startdate = simpleDateFormat.parse(starttime);
                        int daynumber = (int) dateDiff(starttime, format, "yyyy年MM月dd日-HH时mm分");
                        if (date.getTime() <= startdate.getTime()) {
                            ToastUtils.showToast("结束时间不能小于开始时间，请重新选择");
                        } else if (daynumber < 3) {
                            ToastUtils.showToast("开始时间和结束时间，间隔最小为3天");
                        } else if (daynumber > 7) {
                            ToastUtils.showToast("开始时间和结束时间，间隔最大为7天");
                        } else {
                            tv_EndTime.setText(format);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                */
/*if (date.after(new Date(System.currentTimeMillis()))) {
                    tv_EndTime.setText(format);
                } else {
                    ToastUtils.showToast("不能小于当前时间，请重新选择");
                }*//*

            }
        })
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        ImageView ivCancel = (ImageView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvTime.dismiss();
                            }
                        });
                    }
                })
                .isCyclic(true)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "")
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        pvTime.show();
    }

    */
/**
 * 得到几天后的时间
 *
 * @param d
 * @param day
 * @return
 *//*

    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day >= 1) {
                return day;
            } else {
                if (day == 0) {
                    return 1;
                } else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }

    //通过UserCode 加上日期组装 OSS路径
    public String getImageObjectKey() {
        Date date = new Date();
        return new SimpleDateFormat("yyyyMMddHHmm").format(date) + "-" + UUID.randomUUID().toString() + ".png";
    }

    */
/**
 * 获取商品二级分类
 *//*

    private void getGoodsClassIFicationModels() {
        String Scid = (String) SharedPreferencesUtils.getData(getActivity(), "Scid", "");
        OkGo.<BaseMode<List<GoodsClassModelsListBean>>>post(AppApi.BASE_URL + AppApi.GETGOODSCLASSMODELS)
                .tag(this)
                .params("scid", Scid)
                .execute(new JsonCallback<BaseMode<List<GoodsClassModelsListBean>>>(getActivity()) {
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
                            ToastUtils.showShort(getActivity(), response.body().msg);
                        }
                    }
                });
    }

    */
/**
 * 发布商品*
 *
 * @param gname//拍品名称
 * @param gisauction//商品标识
 * @param gmoney//商品价格
 * @param gdesc//拍品详情
 * @param //商品分类id
 * @param gimg//商品图片
 * @param gvideo//商品视频
 * @param gstartingprice//商品起拍价格
 * @param gaddprice//商品加价幅度
 * @param gstoptime//拍品截止时间
 * @param gcollateral//拍卖保证金
 * @param gauctiontime//拍卖起始时间
 * @param gissoldout//是否下架
 * @param freight//运费
 *//*

    public void GetInsertGoods(String gname, String gisauction, String gmoney, String gdesc, String gimg, String gvideo, String gstartingprice, String gaddprice, String gstoptime, String gcollateral, String gauctiontime, String gissoldout, String freight) {
        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.SELECTINSERTGOODS)
                .tag(this)
                .params("uid", uid)
                .params("utoken", utoken)
                .params("gname", gname)
                .params("gisauction", gisauction)
                .params("gmoney", gmoney)
                .params("gdesc", gdesc)
                .params("gcid", stype)
                .params("gimg", gimg)
                .params("gvideo", gvideo)
                .params("gstartingprice", gstartingprice)
                .params("gaddprice", gaddprice)
                .params("gstoptime", gstoptime)
                .params("gcollateral", gcollateral)
                .params("gauctiontime", gauctiontime)
                .params("gissoldout", gissoldout)
                .params("gpostage", freight)
                // .params("gispecial", isaddress)
                .execute(new JsonCallback<BaseMode>(getActivity()) {
                    */
/**
 * 进入到网络请求启动*//*

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
                            ToastUtils.showShort(getActivity(), response.body().msg);
                            stopDialog();
                            getActivity().finish();
                        } else {
                            ToastUtils.showShort(getActivity(), response.body().msg);
                            stopDialog();
                        }
                    }

                    @Override
                    public void onError(Response<BaseMode> response) {
                        super.onError(response);
                        stopDialog();
                        //ToastUtils.showShort(getActivity(), response.body().msg);
                        //LoadingDialog.hide();
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        //LoadingDialog.hide();
                        stopDialog();
                    }
                });
    }

    private void showDelDialog() {
        final UIAlertView delDialog = new UIAlertView(getActivity(), "温馨提示", "拍品同步到拍卖专场需要交纳" + 500 + "请前往充值页面充值",
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
}
*/
