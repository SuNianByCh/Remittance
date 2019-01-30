package com.yaer.remittance.ui.shopping_modular.commodity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yaer.remittance.R;

/**
 * Created by Administrator on 2017/6/18.
 * 担保交易说明
 */
public class CommodityDetailsDialog extends Dialog {

    private Context context;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        public void doConfirm();
        public void doCancel();
    }

    public CommodityDetailsDialog(Context context) {
        super(context, R.style.transparentBgDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.commodity_dialog, null);
        setContentView(view);

        TextView tv_commodity_confirm = (TextView) view.findViewById(R.id.tv_commodity_confirm);
        tv_commodity_confirm.setOnClickListener(new clickListener());

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
       /* WindowManager.LayoutParams lp = dialogWindow.getAttributes();*/
    /*    DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6*/
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setAttributes(lp);
    }
    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.tv_commodity_confirm:
                    clickListenerInterface.doCancel();
                    break;
            }
        }
    }
    ;
}
