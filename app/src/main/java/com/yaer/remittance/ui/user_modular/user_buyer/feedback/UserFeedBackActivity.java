package com.yaer.remittance.ui.user_modular.user_buyer.feedback;


import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yaer.remittance.R;
import com.yaer.remittance.api.AppApi;
import com.yaer.remittance.api.Constant;
import com.yaer.remittance.base.BaseActivity;
import com.yaer.remittance.base.BaseMode;
import com.yaer.remittance.callback.JsonCallback;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.ContainsEmojiEditText;
import com.yaer.remittance.view.CustomTitlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-11.
 * 意见反馈
 */

public class UserFeedBackActivity extends BaseActivity implements CustomTitlebar.TitleBarOnClickListener {
    @BindView(R.id.edit_opinion)
    ContainsEmojiEditText edit_opinion;
    @BindView(R.id.btn_submitOpinion)
    Button btn_submitOpinion;
    @BindView(R.id.ct_feedback)
    CustomTitlebar ct_feedback;
    private  String opinionContent;

    //设置沉浸式
    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.ct_feedback).init();
    }
    @Override
    public void initView() {
        ct_feedback.setAction(this);
        opinionContent = edit_opinion.getText().toString();
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_user_feedback;
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

    @OnClick({R.id.btn_submitOpinion})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submitOpinion:
                if (!opinionContent.equals("")||!"".equals(opinionContent)){
                    ToastUtils.showToast("您输入的意见反馈内容为空，请重新输入");
                }else{
                    submitOpinion();
                }
                break;
        }
    }
    /**
     * 提交意见
     */
    private void submitOpinion() {

        OkGo.<BaseMode>post(AppApi.BASE_URL + AppApi.ADDREPORTINFO)
                .tag(this)
                .params("rdesc",opinionContent)
                .params("rtype",2)
                .execute(new JsonCallback<BaseMode>(this) {
                    @Override
                    public void onSuccess(Response<BaseMode> response) {
                        Log.e("text", "添加反馈: " + response.body().code);
                        Log.e("text", "添加反馈: " + response.body().result);
                        if (response.body().code.equals(Constant.SUEECECODE)) {
                            ToastUtils.showShort(UserFeedBackActivity.this,"您已成功提交反馈，谢谢您的参与！");
                            finish();
                        } else {
                            ToastUtils.showShort(UserFeedBackActivity.this, response.body().msg);
                        }
                    }
                });
    }
    }

