package com.yaer.remittance.bean;


import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QueryCollectAddressBean implements Serializable {
    //地址id
    public String aid;
    //城市
    public String acity;
    //街道如（西三旗）
    public String asubdistrict;
    //详细地址
    public String adesc;
    /*时间*/
    private String atime;
    //是否为默认地址 1（为默认地址）0（不是默认地址）
    public String aisdefault;
    //用户id
    public String uid;
    /*手机号*/
    private String aphone;
    /*名称*/
    private String aname;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    public String getAsubdistrict() {
        return asubdistrict;
    }

    public void setAsubdistrict(String asubdistrict) {
        this.asubdistrict = asubdistrict;
    }

    public String getAdesc() {
        return adesc;
    }

    public void setAdesc(String adesc) {
        this.adesc = adesc;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public String getAisdefault() {
        return aisdefault;
    }

    public void setAisdefault(String aisdefault) {
        this.aisdefault = aisdefault;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public static String getJsonString(QueryCollectAddressBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("aid", userBean.getAid());
        map.put("acity", userBean.getAcity());
        map.put("asubdistrict", userBean.getAsubdistrict());
        map.put("adesc", userBean.getAdesc());
        map.put("atime", userBean.getAtime());
        map.put("aisdefault", userBean.getAisdefault());
        map.put("uid", userBean.getUid());
        map.put("aphone", userBean.getAphone());
        map.put("aname", userBean.getAname());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

}
