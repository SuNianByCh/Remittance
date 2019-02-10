package com.yaer.remittance.bean;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GoodsClassIFicationModelsBean {


    /**
     * gcid : 0
     * gcname : 全部
     * gctime :
     * scid : 0
     */

    private int gcid;
    private String gcname;
    private String gctime;
    private int scid;

    public static String getJsonString(GoodsClassIFicationModelsBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("gcid", userBean.getGcid());
        map.put("gcname", userBean.getGcname());
        map.put("gctime", userBean.getGctime());
        map.put("scid", userBean.getScid());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    public int getGcid() {
        return gcid;
    }

    public void setGcid(int gcid) {
        this.gcid = gcid;
    }

    public String getGcname() {
        return gcname;
    }

    public void setGcname(String gcname) {
        this.gcname = gcname;
    }

    public String getGctime() {
        return gctime;
    }

    public void setGctime(String gctime) {
        this.gctime = gctime;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

}
