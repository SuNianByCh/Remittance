package com.yaer.remittance.ui.news_modular;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseFragment;
import com.yaer.remittance.ui.login_modular.LoginActivity;
import com.yaer.remittance.utils.AppUtile;
import com.yaer.remittance.utils.SharedPreferencesUtils;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.model.Conversation;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2017/6/18.
 * 消息
 */
public class NewsFragment extends BaseFragment implements IUnReadMessageObserver {
    @BindView(R.id.ct_news)
    CustomTitlebar ct_news;
    //系统消息
    @BindView(R.id.ll_systemmessage)
    LinearLayout ll_systemmessage;
    //交易消息
    @BindView(R.id.ll_transactionmessage)
    LinearLayout ll_transactionmessage;
    /*会话列表*/
    @BindView(R.id.ll_session_list)
    LinearLayout ll_session_list;
    @BindView(R.id.tv_liiebiaoname)
    TextView tv_liiebiaoname;

    private Badge badge;

    @Override
    protected void initData(Bundle arguments) {
        GetMewInfo();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImmersionBar.setTitleBar(getActivity(), ct_news);
    }

    @Override
    protected void initView() {
        //这个是连接融云的方法
        //  RongYongSingleton.getInstance();
        //未读消息
        Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE
        };
        //这个变量也是每次都创建，。。。,,
        //                Conversation.ConversationType.GROUP
        badge = new QBadgeView(getActivity()).bindTarget(tv_liiebiaoname);
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarColorTransformEnable(false)
                .navigationBarColor(R.color.main_tone)
                .init();
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.news_fragment;
    }

    @OnClick({R.id.ll_systemmessage, R.id.ll_transactionmessage, R.id.ll_session_list})
    public void onClick(View v) {
        switch (v.getId()) {
          /*  case R.id.tv_new_Ceshi:
                getJoingroup();
                break;*/
            case R.id.ll_systemmessage:
                goToActivity(SystemMessageActivity.class);
                break;
            case R.id.ll_transactionmessage:
                goToActivity(TransactionMessageActivity.class);
                break;
           /* case R.id.ll_privatemessage:
                goToActivity(PrivateMessageActivity.class);
                break;*/
            case R.id.ll_session_list:
                //这个是启动会话列表
                goToActivity(ConversationListActivity.class);
                break;
            /*  case R.id.ceshi2:
             *//**
             * 这个是启动单聊界面
             * 启动单聊界面。
             *
             * @param context      应用上下文。
             * @param targetUserId 要与之聊天的用户 Id。
             * @param title        聊天的标题，开发者需要在聊天界面通过 intent.getData().getQueryParameter("title")
             *                     获取该值, 再手动设置为聊天界面的标题。
             *//*
                RongIM.getInstance().startPrivateChat(getActivity(), "94", "我是92");
                break;*/
        }
    }


    @Override
    public void onCountChanged(final int i) {
        //显示到这里了，不就是这里吗？
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                badge.setBadgeNumber(i);
            }
        });
        Log.d("test", "onCountChanged: " + i);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//false
            GetMewInfo();
        } else {//true
            //ok,你退出登录试试看
            if (getUserVisibleHint()) {
                int fragments = (int) SharedPreferencesUtils.getData(getActivity(), "fragmentShow", 0);
                if (fragments == 1) {
                    GetMewInfo();
                }
            }
        }
    }

    private void GetMewInfo() {
        String utoken = AppUtile.getTicket(mActivity);
        if (utoken.equals("")) {
            Intent intent = new Intent(mActivity, LoginActivity.class);
            intent.putExtra("type", "3");
            startActivity(intent);
        }
    }
}
