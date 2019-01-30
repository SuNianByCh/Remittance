package com.yaer.remittance.bean;

import android.text.TextUtils;

import com.lzy.imagepicker.bean.ImageItem;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 拍品与商品共用的实体类
 * {@link com.yaer.remittance.ui.user_modular.user_seller.addcollection.CommodityFragment,com.yaer.remittance.ui.user_modular.user_seller.addcollection.AuctionGoodFragment}
 */
public class AddCollectionBean extends DataSupport {

    /**
     * 类型，表示是拍品
     */
    public static final int TYPE_PAI_PING = 1;
    /**
     * 类型，商品
     */
    public static final int TYPE_SHANGPIN = 2;

    /**
     * 类型，表示是拍品或商品
     */
    private int sourceType;

    /**
     * 保存时间
     */
    private Long saveTime;


    /**
     * 图片
     */
    private String imges;
    /**
     * 商品名字
     */
    private String goodsName;
    /**
     * 商品描述
     */
    private String goodsDesc;
    /**
     * 价格
     */
    private String goodsPrice;
    /**
     * 数量
     */
    private String goodsNumber;
    /**
     * 拍卖时间 或 拍卖起始时间
     */
    private String goodsSailTime;
    /**
     * 分类
     */
    private String goodsType;

    /**
     * 数量
     */
    private String goodsCount;
    /******************************拍品专有字段********************************
     /**
     *最小加价
     */
    private String minAddPrice;
    /**
     * 起拍价
     */
    private String startPrice;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 商品原价格
     */
    private String trafficPrice;


    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }


    public List<String> getImgesPath() {
        if (TextUtils.isEmpty(getImges())) {
            return null;
        }
        String[] split = getImges().split(",");
        return Arrays.asList(split);
    }

    ArrayList<ImageItem> list;

    public ArrayList<ImageItem> getImageItemList() {
        List<String> imgesPath = getImgesPath();
        if (imgesPath == null || imgesPath.isEmpty()) {
            return new ArrayList<>();
        } else {
            list = new ArrayList<>();
            for (int i = 0; i < imgesPath.size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = imgesPath.get(i);
                list.add(imageItem);
            }
        }
        return list;
    }

    public String getImges() {
        return imges;
    }

    public void setImges(String imges) {
        this.imges = imges;
    }

    public void setImagesList(List<ImageItem> imagesList) {
        if (imagesList == null || imagesList.isEmpty()) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (ImageItem imageItem : imagesList) {
            if (!TextUtils.isEmpty(imageItem.path)) {
                stringBuffer.append(imageItem.path).append(",");
            }
        }
        if (stringBuffer.length() > 2)
            imges = stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsSailTime() {
        return goodsSailTime;
    }

    public void setGoodsSailTime(String goodsSailTime) {
        this.goodsSailTime = goodsSailTime;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getMinAddPrice() {
        return minAddPrice;
    }

    public void setMinAddPrice(String minAddPrice) {
        this.minAddPrice = minAddPrice;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTrafficPrice() {
        return trafficPrice;
    }

    public void setTrafficPrice(String trafficPrice) {
        this.trafficPrice = trafficPrice;
    }

    public Long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Long saveTime) {
        this.saveTime = saveTime;
    }
}
