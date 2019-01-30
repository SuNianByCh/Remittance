package com.yaer.remittance.ui.user_modular.user_seller.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.yaer.remittance.R;
import com.yaer.remittance.ui.user_modular.user_seller.ItemMoveCallBackImpl;
import com.yaer.remittance.ui.user_modular.user_seller.ItemMoveHelperApi;
import com.yaer.remittance.ui.user_modular.user_seller.addcollection.AuctionGoodFragment;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int maxImgCount;
    private Context mContext;
    private ArrayList<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private boolean isAdded;   //是否额外添加了最后一个图片
    ImageItem e;

    public interface OnRecyclerViewItemClickListener {
        View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

        void onItemClick(View view, int position);
    }

    public int getMaxImgCount() {
        return maxImgCount;
    }

    private IDataChangeLisenter mDataChangeLisenter;

    public void setDataChangeLisenter(IDataChangeLisenter mDataChangeLisenter) {
        this.mDataChangeLisenter = mDataChangeLisenter;
    }

    public interface IDataChangeLisenter {
        void chanage(ArrayList<ImageItem> datas);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }
    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>();
        if(data != null && !data.isEmpty()){
            mData.addAll(data);
        }
        if (mData.contains(e) ) {
            mData.remove(e);
        }
        if (mData.size() < maxImgCount) {
            mData.add(e);
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public ArrayList<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        ArrayList<ImageItem> arrayList = new ArrayList<>();
        arrayList.addAll(mData);
        if (arrayList.contains(e))
            arrayList.remove(e);
        return arrayList;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount, RecyclerView recyclerView) {
        e = new ImageItem();
        e.path ="";
        e.name="";
        e.mimeType="";
        ItemMoveCallBackImpl mMoveCallBack = new ItemMoveCallBackImpl(new ItemMoveHelperApi() {
            @Override
            public void onItemMoved(int fromPosition, int toPosition) {
                Log.i("infos------","位置改变前：" + getImagePAATHstring());
                Collections.swap(mData, fromPosition, toPosition);
                notifyItemMoved(fromPosition, toPosition);
                if (mDataChangeLisenter != null) {
                    mDataChangeLisenter.chanage(getImages());
                }
                Log.i("infos------","after position change：" + getImagePAATHstring());
            }
        });
        ItemTouchHelper touchHelper = new ItemTouchHelper(mMoveCallBack);
        touchHelper.attachToRecyclerView(recyclerView);
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(R.layout.list_item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size() > maxImgCount ? maxImgCount : mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder  {

        private ImageView iv_img;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_img);
        }

        public void bind(final int position) {
            //设置条目的点击事件
            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            View ivDelete = itemView.findViewById(R.id.iv_delete);
            if (item == e) {
                iv_img.setImageResource(R.drawable.selector_image_add);
                if (ivDelete != null)
                    ivDelete.setVisibility(View.INVISIBLE);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) listener.onItemClick(v,  AuctionGoodFragment.IMAGE_ITEM_ADD);
                    }
                });
            } else {
                 //ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_img, 0, 0);
                Glide.with((Activity) mContext).load(item.path).error(R.drawable.error_failed).fitCenter().into(iv_img);
                clickPosition = position;
                ivDelete.setVisibility(View.VISIBLE);
                ivDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    /*    if(mData.size()==maxImgCount && !mData.contains(e)){
                            mData.add(maxImgCount-1,e);
                        }*/
                        mData.remove(position);
                        setImages(mData);
                        notifyDataSetChanged();
                        if (mDataChangeLisenter != null) {
                            mDataChangeLisenter.chanage(mData);
                        }
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) listener.onItemClick(v, position);
                    }
                });
            }
        }


    }

    public  boolean maxlastNotAdd(){
        boolean is = false;
        if(maxImgCount == mData.size() && !mData.contains(e)){
            is = true;
        }
        return is;
    }

    private String getImagePAATHstring(){
        StringBuffer stringBuffer = new StringBuffer();
        for(ImageItem imageItem:getImages()){
            stringBuffer.append(imageItem.path).append(",          ");
        }
        return stringBuffer.toString();
    }
}