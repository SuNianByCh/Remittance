package com.yaer.remittance.ui.news_modular;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.ui.news_modular.adapter.UserPrivateMassageAdapter;
import com.yaer.remittance.view.CustomTitlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 私信消息页面
 * Created by ywl on 2016/6/27.
 */
public class PrivateMessageActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.ct_my_private)
    CustomTitlebar ct_my_private;
    private String url;
    private Context context;
    private List<String> mImages = new ArrayList<>();
    @BindView(R.id.rv_private)
    RecyclerView rv_private;
    private UserPrivateMassageAdapter privateMassageAdapter;
    private List<String> PrivateItemList = new ArrayList<>();

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_my_private).init();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_private_message;
    }
    @Override
    public void initView() {
        showdata();
        rv_private.setLayoutManager(new GridLayoutManager(this, 1));
        privateMassageAdapter = new UserPrivateMassageAdapter();
        privateMassageAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rv_private.setAdapter(privateMassageAdapter);
        privateMassageAdapter.setPreLoadNumber(1);
        privateMassageAdapter.setNewData(PrivateItemList);
    }

    @Override
    public void initData() {

    }

    public void showdata() {

        for (int i = 1; i <= 3; i++) {
            PrivateItemList.add("推荐商家关羽大刀" + i);
        }
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538580307560&di=3ec18cbdcd3e085ac849d2b0c8a3ccc4&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0107045624a0b532f87557010e9836.jpg%401280w_1l_2o_100sh.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538580307559&di=16d5d5adfe4a3fab69e67f6d5d83e453&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F0172735542522d0000019ae9f61ce9.jpg%401280w_1l_2o_100sh.png");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538580307559&di=4e0bc3f8ba8ef4af097b0810c56ba12d&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F017a8958103c4ea84a0d304f87633f.jpg");
        mImages.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1538580307559&di=7e0714c5026fcb6b5b8c4b6673d849e0&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F02%2F57%2F0c3e076590681774bf6e895329705545.jpg");
    }



    @Override
    public void performAction(View view) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
