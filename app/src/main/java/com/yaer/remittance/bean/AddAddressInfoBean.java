package com.yaer.remittance.bean;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddAddressInfoBean {

    //用户id
    private int uid;
    //用户身份标识
    private String utoken;
    //城市
    private String actiy;
    //街道
    private String asubdistrict;
    //详细地址
    private String adesc;
    //是否为默认地址
    private String aisdefault;
    /*手机号*/
    private String aphone;
    /*收货人姓名*/
    private String aname;

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUtoken() {
        return utoken;
    }

    public void setUtoken(String utoken) {
        this.utoken = utoken;
    }

    public String getActiy() {
        return actiy;
    }

    public void setActiy(String actiy) {
        this.actiy = actiy;
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

    public String getAisdefault() {
        return aisdefault;
    }

    public void setAisdefault(String aisdefault) {
        this.aisdefault = aisdefault;
    }

    public static String getJsonString(AddAddressInfoBean userBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userBean.getUid());
        map.put("utoken", userBean.getUtoken());
        map.put("actiy", userBean.getActiy());
        map.put("asubdistrict", userBean.getAsubdistrict());
        map.put("adesc", userBean.getAdesc());
        map.put("aisdefault", userBean.getAisdefault());
        map.put("aphone", userBean.getAphone());
        map.put("aname", userBean.getAname());
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

}
