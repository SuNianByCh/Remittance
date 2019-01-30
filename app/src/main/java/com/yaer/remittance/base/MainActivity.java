package com.yaer.remittance.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.yaer.remittance.BaseApplication;
import com.yaer.remittance.R;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.bean.NetEvent;
import com.yaer.remittance.ui.home_modular.HomeFragment;
import com.yaer.remittance.ui.news_modular.NewsFragment;
import com.yaer.remittance.ui.shopping_modular.ShoppingFragment;
import com.yaer.remittance.ui.user_modular.UserFragment;
import com.yaer.remittance.utils.AppManager;
import com.yaer.remittance.utils.NotificationsUtils;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseActivity implements View.OnClickListener, IUnReadMessageObserver {
    private int position;//当前选项状态
    private int showFragment = 0;//记录当前fragment 的索引
    private int goCar = 0;//标识 购物车   等于当前值 表示刷新  大于或者小于 不刷新
    private int goUser = 0;//标识 个人中心 等于当前值 表示刷新  大于或者小于 不刷新
    private long mExitTime = 0;//点击退出状态初始时间
    private int goodsid = 0;
    /*  @BindView(R.id.net_view_rl)
      RelativeLayout net_view_rl;//网络状态栏*/
    private NetReceiver mNetReceiver;//监听网络广播

    @BindView(R.id.rbHome)
    RadioButton rbHome;

    @BindView(R.id.shopping_mall_tv)
    RadioButton shopping_mall_tv;

    @BindView(R.id.message_tv)
    RadioButton message_tv;

    @BindView(R.id.user_tv)
    RadioButton user_tv;//选择按钮

    @BindView(R.id.rg)
    RadioGroup rg;

    public HomeFragment hf;//首页
    private ShoppingFragment sf;//商城界面
    private NewsFragment nf;//消息界面
    private UserFragment uf;//个人界面
    private Badge badge;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rbHome.setOnClickListener(this);
        shopping_mall_tv.setOnClickListener(this);
        message_tv.setOnClickListener(this);
        user_tv.setOnClickListener(this);
        Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE
        };
        //这个变量也是每次都创建，。。。,
        //                Conversation.ConversationType.GROUP
        badge = new QBadgeView(this).bindTarget(message_tv);
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);


        showFragment(0);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initData();
        EventBus.getDefault().register(this);
        /*if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {*/
        if (!NotificationsUtils.isNotificationEnabled(context)) {
            NotificationsUtils.showDelDialog(context, Constant.pushMessage);
        }
        /*}*/
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 9);
        }


    }

    public void initData() {
        initReceive();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbHome:
                showFragment(0);
                break;
            case R.id.shopping_mall_tv:
                showFragment(1);
                break;
            case R.id.message_tv:
                showFragment(2);
                break;
            case R.id.user_tv:
                showFragment(3);
                break;
        }
    }

    public void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        for (int i = 0; i < rg.getChildCount(); i++) {
            View child = rg.getChildAt(i);
            if (child instanceof RadioButton) {
                if (i != index) {
                    ((RadioButton) child).setChecked(false);
                } else {
                    ((RadioButton) child).setChecked(true);
                }
            }
        }
        message_tv.setChecked(false);
        switch (index) {
            case 0:
                rbHome.setChecked(true);
                if (hf == null) {
                    hf = new HomeFragment();
                    ft.add(R.id.fl, hf);
                } else {
                    ft.show(hf);
                }
                showFragment = 0;
                goCar = 1;
                goUser = 1;
                //首页进入后台的时候保存一个值
                break;
            case 1:
                shopping_mall_tv.setChecked(true);
                if (sf == null) {
                    sf = new ShoppingFragment();
                    ft.add(R.id.fl, sf);
                } else {
                    ft.show(sf);
                }
                showFragment = 1;
                goCar = 2;
                goUser = 2;
                break;
            case 2:
                message_tv.setChecked(true);
                if (goUser == 3) {
                    if (nf != null) {
                        ft.show(nf);
                    } else {
                        nf = new NewsFragment();
                        ft.add(R.id.fl, nf);
                    }
                } else {
                    if (nf == null) {
                        nf = new NewsFragment();
                        ft.add(R.id.fl, nf);
                    } else {
                        ft.show(nf);
                    }
                    showFragment = 2;
                    goCar = 3;
                    goUser = 3;
                }
                SharedPreferencesUtils.saveData(context, "fragmentShow", 0);//处理在fragment 购物车模块的 侧滑返回
                break;
            case 3:
                user_tv.setChecked(true);
                if (goUser == 4) {
                    if (uf != null) {
                        ft.show(uf);
                    } else {
                        uf = new UserFragment();
                        ft.add(R.id.fl, uf);
                    }
                } else {
                    if (uf == null) {
                        uf = new UserFragment();
                        ft.add(R.id.fl, uf);
                    } else {
                        ft.show(uf);
                      /*  ft.remove(uf);
                        uf = new UserFragment();
                        ft.add(R.id.fl, uf);*/
                    }
                    showFragment = 3;
                    goCar = 4;
                    goUser = 4;
                }
                SharedPreferencesUtils.saveData(context, "fragmentShow", 0);//处理在fragment 购物车模块的 侧滑返回
                break;
        }
        ft.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentTransaction ft) {
        //如果不为空，就先隐藏起来
        if (hf != null) {
            ft.hide(hf);
        }
        if (sf != null) {
            ft.hide(sf);
        }
        if (nf != null) {
            ft.hide(nf);
        }
        if (uf != null) {
            ft.hide(uf);
        }
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent == null) {
            return;
        } else {
            if (intent.getIntExtra("type", 0) == 1) {
                //从各个页面调回首页
                showFragment(0);
            } else if (intent.getIntExtra("type", 0) == 3) {
                //进入到我的界面
                goUser = 2;
                showFragment(2);
            } else if (intent.getIntExtra("type", 0) == 4) {
                //进入到我的界面
                goUser = 3;
                showFragment(3);
            }
            rg.setVisibility(View.VISIBLE);
            goodsid = 0;
        }
    }

    //初始化网络监听广播
    private void initReceive() {
        mNetReceiver = new NetReceiver();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetReceiver, mFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        if (mNetReceiver != null) {
            unregisterReceiver(mNetReceiver);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent event) {
        if (event == null) {
            return;
        } else {
            getIsNetWork(event.isNet());
        }
    }

    /**
     * 获取网络状态状态 true-有网  false-没网
     *
     * @param isNet
     */
    public void getIsNetWork(Boolean isNet) {
        if (isNet == false) {
            ToastUtils.showToast("似乎已断开互联网的连接。");
        } else {
        }

        //net_view_rl.setVisibility(isNet ? View.GONE : View.VISIBLE);
      /*  net_view_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkUtils.startToSettings(MainActivity.this);
            }
        });*/
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
    protected void onResume() {
        super.onResume();
        //每次回来，获取当前显示的Fragment，调用它的onResume方法进行刷新

        switch (showFragment) {
//            public HomeFragment hf;//首页
//            private ShoppingFragment sf;//商城界面
//            private NewsFragment nf;//消息界面
//            private UserFragment uf;//个人界面
            case 0: {
                //首页，暂时什么也不做
                hf.onResume();
                break;
            }

            case 1: {
                //商城，暂时什么也不做
                sf.onResume();
                break;
            }

            case 2: {
                //消息，暂时什么也不做
                nf.onResume();
                break;
            }

            case 3: {
                //我的，暂时什么也不做
                uf.onResume();
                break;
            }
        }
    }

    @Override
    public void onCountChanged(final int i) {
        //显示到这里了，不就是这里吗？
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                badge.setBadgeNumber(i);
            }
        });
        Log.d("test", "onCountChanged: " + i);
    }

}
