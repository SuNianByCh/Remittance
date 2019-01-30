package com.yaer.remittance.ui.news_modular;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yaer.remittance.R;
import com.yaer.remittance.base.BaseFragmentActivity;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * 私信会话列表界面
 * Created by ywl on 2016/6/27.
 */
public class ConversationListActivity extends BaseFragmentActivity implements CustomTitlebar.TitleBarOnClickListener {
    private Fragment mConversationListFragment;//会话列表的fragment 的声明
    @BindView(R.id.ct_service_custoser)
    CustomTitlebar ct_service_custoser;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_service_custoser).init();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_service_customer;
    }

    @Override
    public void initView() {
        ct_service_custoser.setAction(this);
        initConversationListFragment();
    }

    @Override
    public void initData() {

    }

    /**
     * 封装的代码加载融云的会话列表的 fragment
     *
     * @return
     */
    private Fragment initConversationListFragment() {
        ConversationListFragment fragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
             /*   .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群組
                .appendQueryParameter(Conversation.ConversationType.CUSTOMER_SERVICE.getName(), "false")//客服会话列表
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")*/
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
               // .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//获取群组消息false取消接受无效
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.CUSTOMER_SERVICE.getName(), "false")

                /*.appendQueryParameter(Conversation.ConversationType.NONE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.CHATROOM.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUSH_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.ENCRYPTED.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")*/
                .build();
        fragment.setUri(uri);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commitAllowingStateLoss();
        return fragment;
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
