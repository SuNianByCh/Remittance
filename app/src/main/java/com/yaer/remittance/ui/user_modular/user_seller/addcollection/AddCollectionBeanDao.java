package com.yaer.remittance.ui.user_modular.user_seller.addcollection;

import android.support.annotation.NonNull;

import com.yaer.remittance.bean.AddCollectionBean;

import org.litepal.crud.DataSupport;
import org.litepal.util.LogUtil;

import java.util.List;

public class AddCollectionBeanDao {
    /**
     * 保存或更新AddCollectionBean对象
     *
     * @param addCollectionBean
     */
    public static boolean addAddCollectionBeanOrUpdate(@NonNull AddCollectionBean addCollectionBean) {
        try {
            if (!upDate(addCollectionBean)) {
                return addCollectionBean.save();
            } else {
                return true;
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }
    //更新数据
    public static boolean upDate(@NonNull AddCollectionBean addCollectionBean) {
        try {

            AddCollectionBean first = DataSupport.where("saveTime=?", addCollectionBean.getSaveTime().toString()).findFirst(AddCollectionBean.class);
            if (first != null) {
                if (addCollectionBean.updateAll("saveTime=?", addCollectionBean.getSaveTime().toString()) > 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Throwable throwable) {
            return false;
        }

    }


    /**
     * 删除AddCollectionBean对象
     *
     * @param saveTime---int
     */
    public static void deleteAddCollection(long saveTime) {
        try {
            int i = DataSupport.deleteAll(AddCollectionBean.class, "saveTime=?", "" + saveTime);
            LogUtil.d("infos", "" + i);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * 删除全部
     */
    public static void deleteAllAddCollection() {
        try {
            DataSupport.deleteAll(AddCollectionBean.class);
            //LogUtil.d("infos",""+i);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public static List<AddCollectionBean> getSaveList() {

        try {
            boolean exist = DataSupport.isExist(AddCollectionBean.class);
            if (exist) {
                return DataSupport.findAll(AddCollectionBean.class);
            } else {
                return null;
            }
        } catch (Throwable throwable) {
            return null;
        }

    }
}
