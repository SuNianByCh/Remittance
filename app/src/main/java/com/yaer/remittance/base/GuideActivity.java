package com.yaer.remittance.base;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yaer.remittance.R;
import com.yaer.remittance.ui.adapter.GuidePageAdapter;
import com.yaer.remittance.utils.AppManager;
import com.yaer.remittance.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by min on 2017/7/29.
 * 引导页
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private long mExitTime = 0;
    @BindView(R.id.img_pass)
    ImageView img_pass;
    @BindView(R.id.guide_vp)
    ViewPager vp;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合
    @BindView(R.id.guide_ll_point)
    ViewGroup vg;//放置圆点

    //实例化原点View
    private ImageView iv_point;
    private ImageView[] ivPointArray;

    @Override
    public void initView() {
        SharedPreferencesUtils.saveData(context, "yidaoye", 1);
        initViewPager();
        initPoint();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.guide_layout;
    }

    @Override
    public void initData() {
        img_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToActivity(MainActivity.class);
                finish();
            }
        });
    }

    /**
     * 加载底部圆点
     */
    private void initPoint() {
        //这里实例化LinearLayout
        vg = (ViewGroup) findViewById(R.id.guide_ll_point);
        //根据ViewPager的item数量实例化数组
        ivPointArray = new ImageView[viewList.size()];
        //循环新建底部圆点ImageView，将生成的ImageView保存到数组中
        int size = viewList.size();
        for (int i = 0; i < size; i++) {
            iv_point = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(18, 18);
            layoutParams.setMargins(10, 0, 10, 0);
            iv_point.setLayoutParams(layoutParams);
            iv_point.setPadding(15, 0, 15, 0);//left,top,right,bottom
            ivPointArray[i] = iv_point;
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if (i == 0) {
                iv_point.setBackgroundResource(R.drawable.dot_unselected);
            } else {
                iv_point.setBackgroundResource(R.drawable.dot_selected);
            }
            //将数组中的ImageView加入到ViewGrou
            vg.addView(ivPointArray[i]);
        }
    }

    /**
     * 加载图片ViewPager
     */
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        //实例化图片资源
        imageIdArray = new int[]{R.drawable.ic_qidong_0, R.drawable.ic_qidong_1, R.drawable.ic_qidong_2, R.drawable.ic_qidong_3, R.drawable.ic_qidong_4};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);
            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再次点击退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            AppManager.getAppManager().AppExit(this);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //循环设置当前页的标记图
        int length = imageIdArray.length;
        for (int i = 0; i < length; i++) {
            ivPointArray[position].setBackgroundResource(R.drawable.dot_unselected);
            if (position != i) {
                ivPointArray[i].setBackgroundResource(R.drawable.dot_selected);
            }
        }
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1) {
            goToActivity(MainActivity.class);
            finish();
            // tv_go.setVisibility(View.VISIBLE);
            // finish();
        } else {
            //tv_go.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
