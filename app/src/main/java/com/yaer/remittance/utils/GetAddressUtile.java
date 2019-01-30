package com.yaer.remittance.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.yaer.remittance.bean.JsonBean;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by min on 2017/9/26.
 * 获取本地地址文件信息
 */
public class GetAddressUtile {
    public static final int MSG_LOAD_DATA = 0x0001;
    public static final int MSG_LOAD_SUCCESS = 0x0002;
    public static final int MSG_LOAD_FAILED = 0x0003;
    private Thread thread;
    private boolean isLoaded = false;
    private Context context;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();//省集合
    private ArrayList<ArrayList<JsonBean.City>> options2Items = new ArrayList<>();//市集合
    private ArrayList<ArrayList<ArrayList<JsonBean.CityBean>>> options3Items = new ArrayList<>();//县集合

    public GetAddressUtile(Context context) {
        this.context = context;
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    public ArrayList<JsonBean> getOptions1Items() {
        return options1Items;
    }

    public ArrayList<ArrayList<JsonBean.City>> getOptions2Items() {
        return options2Items;
    }

    public ArrayList<ArrayList<ArrayList<JsonBean.CityBean>>> getOptions3Items() {
        return options3Items;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    break;

            }
        }
    };

    public void initJsonData() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(context, "province.json");//获取assets目录下的json文件数据
        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<JsonBean.City> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<JsonBean.CityBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                JsonBean.City CityName = jsonBean.get(i).getCityList().get(c);
                CityList.add(CityName);//添加城市
                ArrayList<JsonBean.CityBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getChild() == null
                        || jsonBean.get(i).getCityList().get(c).getChild().size() == 0) {
                    City_AreaList.add(null);
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getChild().size(); d++) {//该城市对应地区所有数据
                        JsonBean.CityBean AreaName = jsonBean.get(i).getCityList().get(c).getChild().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }
}
