package com.yaer.remittance.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.bean.QueryCollectAddressBean;
import com.yaer.remittance.ui.user_modular.setup.UpdateReceicingAddress;
import com.yaer.remittance.ui.user_modular.setup.UserReceivingAddressActivity;
import com.yaer.remittance.utils.ToastUtils;
import com.yaer.remittance.view.WsbTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author chenzheng_java
 * @description 该类的部分实现模仿了SimpleAdapter
 */
public class UserAddressAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<QueryCollectAddressBean> beanList = new ArrayList<>();
    private ViewHolder holder;
    private static UserReceivingAddressActivity maa;

    public UserAddressAdapter(UserReceivingAddressActivity maa, Context context) {
        this.maa = maa;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void addData(List<QueryCollectAddressBean> beanList) {
        if (null == beanList || beanList.size() <= 0) {
            return;
        }
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }

    public void clear() {
        if (null == this.beanList || this.beanList.size() <= 0) {
            return;
        }
        beanList.clear();
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ParentingSaidFragmentViewHolder holder = null;
        if (null == convertView) {
            convertView = mInflater.inflate(R.layout.item_address, parent, false);
            holder = ViewHolder.newInstance(mInflater, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.refresh(mContext, beanList, position);
//        holder.onClick(mContext,convertView,position,beanList);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.address_name)
        TextView address_name;//姓名
        @BindView(R.id.address_tel)
        TextView address_tel;//联系方式
        @BindView(R.id.address_dizhi)
        WsbTextView address_dizhi;//收货地址
        @BindView(R.id.tv_aisdefault)
        TextView tv_aisdefault;
        @BindView(R.id.cb_Default_address)
        CheckBox cb_Default_address;//默认收货地址按钮
        @BindView(R.id.ll_address_up)
        LinearLayout ll_address_up;//编辑
        @BindView(R.id.ll_address_del)
        LinearLayout ll_address_del;//删除

        private Context cont;

        public static ViewHolder newInstance(LayoutInflater inflater, View view) {
            if (inflater == null) {
                return null;
            }
            if (view == null) {
                return null;
            }
            return new ViewHolder(view);
        }

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void refresh(final Context context, final List<QueryCollectAddressBean> beanList, final int position) {
            if (context == null) {
                return;
            } else {
                cont = context;
            }

            if (null == beanList || beanList.size() <= 0) {
                return;
            }

            final QueryCollectAddressBean goodsInfo = beanList.get(position);
//            if (goodsInfo.getTitle() != null && !goodsInfo.getTitle().equals("")) {
//                business_item_goods_name.setText(goodsInfo.getTitle());
//            }

            address_name.setText(goodsInfo.getAname());
            address_tel.setText(goodsInfo.getAphone());
            address_dizhi.setText("地址："+goodsInfo.getAcity() + "  " + goodsInfo.getAdesc());
            if (goodsInfo.getAisdefault().equals("1")) {
                cb_Default_address.setChecked(true);
                tv_aisdefault.setText("默认地址");
            } else {
                cb_Default_address.setChecked(false);
                tv_aisdefault.setText("设置默认地址");
            }


            cb_Default_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (int i = 0; i < beanList.size(); i++) {
                        if (goodsInfo.getAid().equals(beanList.get(i).getAid())) {
                            maa.Defaltaddress(goodsInfo.getAid());
                            // cb_Default_address.setChecked(true);
                        } else {
                            //cb_Default_address.setChecked(false);
                        }
                    }
                }
            });

            ll_address_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    maa.DeleteAddress(goodsInfo.getAid());
                }
            });
            ll_address_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.showToast("点击编辑收货地址");
                    Intent intent = new Intent(context, UpdateReceicingAddress.class);
                    intent.putExtra("aid", goodsInfo.getAid());  //地址id
                    intent.putExtra("acity", goodsInfo.getAcity());    //城市
                    intent.putExtra("asubdistrict", goodsInfo.getAsubdistrict());  //街道如（西三旗）
                    intent.putExtra("adesc", goodsInfo.getAdesc());    //详细地址
                    // intent.putExtra("aisdefault", goodsInfo.getAisdefault());//是否为默认地址 1（为默认地址）0（不是默认地址）
                    intent.putExtra("aphone", goodsInfo.getAphone());  /*手机号*/
                    intent.putExtra("aname", goodsInfo.getAname());   /*名称*/
                    context.startActivity(intent);
                }
            });

        }
    }
}