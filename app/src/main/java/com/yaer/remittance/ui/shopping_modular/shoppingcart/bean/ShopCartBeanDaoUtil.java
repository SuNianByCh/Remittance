package com.yaer.remittance.ui.shopping_modular.shoppingcart.bean;

import android.content.ContentValues;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.utils.Log;
import com.umeng.commonsdk.debug.D;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.UpdateOrDeleteCallback;
import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ShopCartBean的增删改
 */
public class ShopCartBeanDaoUtil {
    /**
     * 保存
     *
     * @param shopCartBean
     */
    public static void saveShopCardBean(ShopCartBean shopCartBean) {
        if (shopCartBean == null || shopCartBean.getShopInfoModelBean() == null) {
            return;
        }
        if (shopCartBean != null && shopCartBean.getShopInfoModelBean() != null && shopCartBean.getGid() != null) {
            if (DataSupport.where("gid=?", shopCartBean.getGid()).findFirst(ShopCartBean.class) == null)
                shopCartBean.save();
            else {
                shopCartBean.updateAll("gid=?", shopCartBean.getGid());
            }
            if (shopCartBean.getShopInfoModelBean() != null
                    && shopCartBean.getShopInfoModelBean().getSid() != null
                    && DataSupport.where("sid=?", shopCartBean.getShopInfoModelBean().getSid()).findFirst(ShopInfoModelBean.class) == null)
                shopCartBean.getShopInfoModelBean().save();
        }
    }

    /**
     *得到商品的list
     *可以用得到数量  ,如
     *   List<ShopCartBean> shopCartBeanList = ShopCartBeanDaoUtil.getShopCartBeanList();
     if(shopCartBeanList != null){
     int size = shopCartBeanList.size();
     }
     * @return --- List<ShopCartBean>，can be null
     */
    public static List<ShopCartBean> getShopCartBeanList() {
        List<ShopCartBean> all = DataSupport.findAll(ShopCartBean.class);
        List<ShopCartBean> more = new ArrayList<>();
        if (all != null) {
            for (ShopCartBean shop : all) {
                if (shop != null)
                    shop.getShopInfoModelBean();
                if (!isContainer(more, shop)) {
                    more.add(shop);
                }
            }
        }
        return more;
    }


    /**
     * @return --- List<ShopCartBean>，can be null
     */
    public static List<ShopCartBean> getShopnList() {
        List<ShopCartBean> all = DataSupport.findAll(ShopCartBean.class);
        return all;
    }


    /**
     * 可能为空
     *
     * @return
     */
    public static String getShopCartBeanListGosn() {
        String json = null;
        List<ShopCartBean> shopCartBeanList = getShopCartBeanList();
        if (shopCartBeanList != null) {
            json = new Gson().toJson(shopCartBeanList);
        }
        return json;
    }

    /**
     * 得到ShopInfoModelBean的List
     *
     * @return
     */
    public static List<ShopInfoModelBean> getShopInfoModelBeanList() {
        List<ShopCartBean> shopCartBeanList = getShopCartBeanList();

        if (shopCartBeanList == null || shopCartBeanList.isEmpty()) {
            return null;
        } else {
            ArrayList<ShopInfoModelBean> shopInfoModelBeans = new ArrayList<>();
            for (ShopCartBean shopCartBean : shopCartBeanList) {
                if (shopCartBean != null && shopCartBean.getShopInfoModelBeanBySql() != null) {
                    shopInfoModelBeans.add(shopCartBean.getShopInfoModelBeanBySql());
                }
            }
            return shopInfoModelBeans;

        }

    }

    public static boolean isContainer(List<ShopCartBean> shopInfoModelBeanList, ShopCartBean shopInfoModelBean) {
        boolean isHase = false;
        if (shopInfoModelBean == null) {
            return false;
        }
        if (shopInfoModelBeanList != null && !shopInfoModelBeanList.isEmpty()) {
            for (ShopCartBean sp : shopInfoModelBeanList) {
                if (TextUtils.equals(sp.getSid(), shopInfoModelBean.getSid())) {
                    isHase = true;
                    break;
                }
            }
        }

        return isHase;
    }


    /**
     * 得到ShopInfoModelBean的Json
     *
     * @return
     */
    public static String getShopInfoModelBeanListJson() {

        List<ShopInfoModelBean> shopInfoModelBeanList = getShopInfoModelBeanList();
        if (shopInfoModelBeanList == null || shopInfoModelBeanList.isEmpty()) {
            return null;
        } else {

            return new Gson().toJson(shopInfoModelBeanList);
        }

    }//


    public static void deleteGoodsById(String gid, String sid) {
        try {
            if (gid == null || sid == null) {
                return;
            }
            DataSupport.deleteAll(ShopCartBean.class, "gid=? and sid=?", gid, sid);
            List<ShopCartBean> shopCartBeans = DataSupport.where("sid=?", sid).find(ShopCartBean.class);
            if (shopCartBeans == null || shopCartBeans.isEmpty()) {
                DataSupport.deleteAll(ShopInfoModelBean.class, "sid=?", sid);
            }

        } catch (Throwable throwable) {
            throwable.toString();
        }


    }


    public static String getShopAndGoodsByIdGson(String sid) {
        if (sid == null) {
            return null;
        }
        List<ShopInfoModelBean> shopInfoModelBeanList = DataSupport.where("sid=?", sid).find(ShopInfoModelBean.class);
        if (shopInfoModelBeanList == null || shopInfoModelBeanList.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < shopInfoModelBeanList.size(); i++) {
                String mySid = shopInfoModelBeanList.get(i).getSid();
                if (sid != null) {
                    shopInfoModelBeanList.get(i).addShopCarBean(DataSupport.where("sid=?", mySid).find(ShopCartBean.class));
                }
            }

            return new Gson().toJson(shopInfoModelBeanList);
        }


    }
    /**
     * 查询json
     * */
    public static String getShopAndGoodsAllGson() {
        List<ShopInfoModelBean> shopInfoModelBeanList = getShopInfoModelBeanList();
        if (shopInfoModelBeanList == null || shopInfoModelBeanList.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < shopInfoModelBeanList.size(); i++) {
                String sid = shopInfoModelBeanList.get(i).getSid();
                if (sid != null) {
                    shopInfoModelBeanList.get(i).addShopCarBean(DataSupport.where("sid=?", sid).find(ShopCartBean.class));
                }
            }
            return new Gson().toJson(shopInfoModelBeanList);
        }
    }

    /**
     * 删除
     *
     * @param gid
     */
    public static void deleteShopCartBean(String gid) {
        if (gid != null) {
            DataSupport.deleteAll(ShopCartBean.class, "gid=?", gid);
        }
    }

    /**
     * 删除全部
     */
    public static void deleteShopCarAll() {
        DataSupport.deleteAll(ShopInfoModelBean.class);
        DataSupport.deleteAll(ShopCartBean.class);
    }

    /**
     * 取得保存的条数
     *
     * @param
     */
    public int getSaveShopCardCount() {
        List<ShopCartBean> shopCartBeanList = getShopCartBeanList();
        return shopCartBeanList == null ? 0 : shopCartBeanList.size();
    }



    //

    /**
     * 更新购物车库存数量，更新库存数量与购买数量
     * 当商品数量或购买数量小于0时，删除该商品
     * @param gid
     * @param gnumber --- 库存数量
     * @param goods_amount---购买数量用的是这个
     */
    public static void updateShopCardBean(String gid,int gnumber,int goods_amount ){
        try {
            if(gid == null){
                return;
            }
            if(goods_amount < 0 || gnumber < 0){
                DataSupport.deleteAll(ShopCartBean.class,"gid=?", gid);
                return;
            }

            List<ShopCartBean> shopCartBeans = DataSupport.where("gid=?", gid).find(ShopCartBean.class);
            if(shopCartBeans == null || shopCartBeans.isEmpty()) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("gnumber",gnumber);
            contentValues.put("goods_amount",goods_amount);
            DataSupport.updateAllAsync(ShopCartBean.class,contentValues,"gid=?",gid);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }


    }


    /**
     * 更新购物车库存数量
     * @param gid
     * @param gnumber---库存数量,小于0时，删除该商品
     */
    public static void updateShopCardBean(String gid,int gnumber){
        try {
            if(gid == null){
                return;
            }
            if(gnumber <0){
                DataSupport.deleteAll(ShopCartBean.class,"gid=?", gid);
                return;
            }

            List<ShopCartBean> shopCartBeans = DataSupport.where("gid=?", gid).find(ShopCartBean.class);
            if(shopCartBeans == null || shopCartBeans.isEmpty()) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("gnumber",gnumber);
            DataSupport.updateAllAsync(ShopCartBean.class,contentValues,"gid=?",gid);
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }


    }
    /**
     * 更新数据
     *
     * @param shopCartBean
     */
    public static void updateShopCardBean(ShopCartBean shopCartBean) {
        if (shopCartBean == null) {
            return;
        }
        if (shopCartBean != null && shopCartBean.getShopInfoModelBean() != null && shopCartBean.getGid() != null) {
            if (DataSupport.where("gid=?", shopCartBean.getGid()).findFirst(ShopCartBean.class) != null) {
                shopCartBean.updateAll("gid=?", shopCartBean.getGid());
            } else {
                saveShopCardBean(shopCartBean);
            }
        }
    }

}
