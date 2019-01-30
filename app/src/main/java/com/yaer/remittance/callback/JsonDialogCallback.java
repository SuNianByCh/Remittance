package com.yaer.remittance.callback;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yaer.remittance.utils.ToastUtils;

/**
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 可以变化Dialog
 */
public abstract class JsonDialogCallback<T> extends JsonCallback<T> {

    private KProgressHUD mKProgressHUD;
    private Context context;

    private void initDialog(Context context) {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(context);
        }
    }

    public JsonDialogCallback(Context context) {
        super(context);
        this.context = context;
        initDialog(context);

    }


    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (mKProgressHUD != null && !mKProgressHUD.isShowing()) {
            try {
                mKProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("加载中...").setCancellable(false).show();
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (mKProgressHUD != null && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (response.getException() instanceof Exception) {
            ToastUtils.showShort(context, "数据解析异常，稍后再试！！！");
        }
    }
}
