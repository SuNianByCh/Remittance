package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yaer.remittance.R;
import com.yaer.remittance.ui.user_modular.user_seller.storetable.TestBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/27.
 */

public class PictureAdapter extends BaseAdapter {


    private Context context;
    private List<TestBean> pictures = new ArrayList<TestBean>();

    public PictureAdapter(List<TestBean> pictures, Context context) {
        super();
        this.context = context;
        this.pictures = pictures;
    }


    @Override
    public int getCount() {
        if (null != pictures) {
            return pictures.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.picture_item, null);
            // 初始化组件
            viewHolder.title_count = (TextView) convertView.findViewById(R.id.title_count);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 给组件设置资源
        TestBean picture = pictures.get(position);
        viewHolder.title_count.setText(picture.getName());//.setImageResource(picture.getName());
        viewHolder.title.setText(picture.getPrice());

        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public TextView title_count;
    }
}


/*package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.content.Context;
import android.graphics.Picture;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.socialize.media.AppInfo;
import com.yaer.remittance.R;
import com.yaer.remittance.bean.ReportFormBean;

import java.util.ArrayList;
import java.util.List;

*//**
 * Created by admin on 2017/6/27.
 *//*


public class PictureAdapter extends BaseAdapter {
    private Context context;
    private List<ReportFormBean> pictures;


    public PictureAdapter(Context context, List<ReportFormBean> pictures) {
        super();
        this.context = context;
        this.pictures = pictures;
    }

    @Override
    public int getCount() {
        if (null != pictures) {
            return pictures.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return pictures.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            // 获得容器
            convertView = LayoutInflater.from(this.context).inflate(R.layout.picture_item, null);
            // 初始化组件
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            // 给converHolder附加一个对象
            convertView.setTag(viewHolder);
        } else {
            // 取得converHolder附加的对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ReportFormBean appInfo = pictures.get(position);
        viewHolder.title.setText(appInfo.getReceivables());
        return convertView;
    }

    class ViewHolder {
        public TextView title;
        public ImageView image;
    }

    class Picture {

        private String title;
        private int imageId;

        public Picture(String title, Integer imageId) {
            this.imageId = imageId;
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public int getImageId() {
            return imageId;
        }

    }
}*/
