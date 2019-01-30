package com.yaer.remittance.ui.user_modular.user_buyer.assesspublish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.CustomTitlebar;
import com.yaer.remittance.view.SelectPicPopupWindow;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 发布评价
 */
public class AssessPublishActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener, View.OnClickListener {
    @BindView(R.id.ct_assess_publish)
    CustomTitlebar ct_assess_publish;
    @BindView(R.id.tv_publisher_assess)
    TextView tv_publisher_assess;
    @BindView(R.id.accessbottom)
    LinearLayout accessbottom;
    @BindView(R.id.ratingBar_service)
    RatingBar ratingBar_service;
    @BindView(R.id.choose_image)
    ImageView choose_image;
    @BindView(R.id.image)
    ImageView image;
    private ImageView manner_star1, manner_star2, manner_star3, manner_star4, manner_star5, ambient_star1, ambient_star2, ambient_star3, ambient_star4, ambient_star5;
    private String path;
    private JSONObject albumJson;
    //点击相机按钮弹出的菜单
    private SelectPicPopupWindow picPopupWindow;
    public static final int REQUEST_CODE_ALBUM = 2;        // 本地相册
    public static final String IMAGE_UNSPECIFIED = "image/*";
    private Button up_btn_cancel;
    private Button btn_pick_photo;
    private Button btn_take_photo;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_assess_publish).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_assesspublish;
    }

    @Override
    public void initView() {
        tv_publisher_assess.setOnClickListener(this);
        choose_image.setOnClickListener(this);
        ct_assess_publish.setAction(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publisher_assess:
                //发布评价（上传标签属性，以及图片，文字）
                ToastUtils.showToast("发表成功");
                //String mContent = publish_assess.getText().toString().trim();
                break;
            case R.id.choose_image:
                /*addViewPop();*/
                break;
            case R.id.btn_pick_photo://本地相册
                Intent local = new Intent();
                local.setType(IMAGE_UNSPECIFIED);
                local.setAction(Intent.ACTION_GET_CONTENT);
                local.putExtra("crop", "true");
                local.putExtra("aspectX", 1);
                local.putExtra("aspectY", 1);
                local.putExtra("outputX", 80);
                local.putExtra("outputY", 80);
                local.putExtra("return-data", true);
                startActivityForResult(local, 2);
                picPopupWindow.dismiss();
                break;
            case R.id.btn_take_photo://拍照
                Intent intentpick = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentpick, REQUEST_CODE_ALBUM);
                picPopupWindow.dismiss();
                break;
            case R.id.up_btn_cancel://取消拍照
                picPopupWindow.dismiss();
                break;
        }
    }

    /**
     * 添加pop菜单
     */
    public void addViewPop() {
        View view = this.getLayoutInflater().inflate(R.layout.selector_picpop, null);
        picPopupWindow = new SelectPicPopupWindow(this, view);
        up_btn_cancel = findViewById(R.id.up_btn_cancel);
        btn_pick_photo = findViewById(R.id.btn_pick_photo);
        btn_take_photo = findViewById(R.id.btn_take_photo);
        btn_take_photo.setOnClickListener(this);
        btn_pick_photo.setOnClickListener(this);
        up_btn_cancel.setOnClickListener(this);
        picPopupWindow.showAtLocation(accessbottom, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
        }
        if (resultCode == Activity.RESULT_OK) {//拍照
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                return;
            }
            String name = new android.text.format.DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream OutStream = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/Image/" + name;
            try {
                OutStream = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, OutStream);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    OutStream.flush();
                    OutStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ((ImageView) findViewById(R.id.iv_image)).setImageBitmap(bitmap);// 将图片显示在ImageView里
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
